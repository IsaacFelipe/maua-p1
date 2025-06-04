package servidor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    
    private static final String URL = "jdbc:mysql://localhost:3306/jogo_adivinhacao";
    private static final String USUARIO = "root"; // Substitua pelo seu usuário MySQL
    private static final String SENHA = ""; // Substitua pela sua senha MySQL
    
    private Connection conexao;
    
    // Inicializa a conexão com o banco de dados
    public void conectar() throws SQLException {
        if (conexao == null || conexao.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
                System.out.println("Conexão com o banco de dados estabelecida.");
            } catch (ClassNotFoundException e) {
                throw new SQLException("Driver JDBC do MySQL não encontrado", e);
            }
        }
    }
    
    // Fecha a conexão com o banco de dados
    public void desconectar() throws SQLException {
        if (conexao != null && !conexao.isClosed()) {
            conexao.close();
            System.out.println("Conexão com o banco de dados fechada.");
        }
    }
    
    // Verifica se um usuário existe e se a senha está correta
    public boolean autenticarUsuario(String username, String senha) throws SQLException {
        String sql = "SELECT * FROM usuarios WHERE username = ? AND senha = ?";
        
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, senha); // Em um sistema real, use hash+salt
            
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next(); // Retorna true se encontrou um usuário
            }
        }
    }
    
    // Registra um novo usuário
    public boolean registrarUsuario(String username, String senha) throws SQLException {
        // Verifica se o usuário já existe
        String checkSql = "SELECT * FROM usuarios WHERE username = ?";
        try (PreparedStatement checkStmt = conexao.prepareStatement(checkSql)) {
            checkStmt.setString(1, username);
            try (ResultSet rs = checkStmt.executeQuery()) {
                if (rs.next()) {
                    return false; // Usuário já existe
                }
            }
        }
        
        // Insere o novo usuário
        String insertSql = "INSERT INTO usuarios (username, senha) VALUES (?, ?)";
        try (PreparedStatement insertStmt = conexao.prepareStatement(insertSql)) {
            insertStmt.setString(1, username);
            insertStmt.setString(2, senha); // Em um sistema real, use hash+salt
            
            int linhasAfetadas = insertStmt.executeUpdate();
            return linhasAfetadas > 0;
        }
    }
    
    // Obtém o ID de um usuário pelo nome
    public int getUsuarioId(String username) throws SQLException {
        String sql = "SELECT id FROM usuarios WHERE username = ?";
        
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, username);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                } else {
                    return -1; // Usuário não encontrado
                }
            }
        }
    }
    
    // Registra uma nova partida
    public void registrarPartida(int usuarioId, int numeroSecreto, int tentativas, 
                                int tempoSegundos, boolean vitoria) throws SQLException {
        String sql = "INSERT INTO partidas (usuario_id, numero_secreto, tentativas, " +
                     "tempo_segundos, vitoria) VALUES (?, ?, ?, ?, ?)";
        
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, usuarioId);
            stmt.setInt(2, numeroSecreto);
            stmt.setInt(3, tentativas);
            stmt.setInt(4, tempoSegundos);
            stmt.setBoolean(5, vitoria);
            
            stmt.executeUpdate();
        }
        
        // Se o jogador venceu, atualiza o ranking
        if (vitoria) {
            atualizarRanking(usuarioId, calcularPontuacao(tentativas, tempoSegundos));
        }
    }
    
    // Calcula a pontuação baseada no número de tentativas e tempo
    private int calcularPontuacao(int tentativas, int tempoSegundos) {
        // Fórmula simples: mais pontos para menos tentativas e menos tempo
        return Math.max(1000 - (tentativas * 50) - (tempoSegundos * 5), 100);
    }
    
    // Atualiza o ranking de um jogador
    private void atualizarRanking(int usuarioId, int pontuacao) throws SQLException {
        // Verifica se o jogador já está no ranking
        String checkSql = "SELECT pontuacao FROM ranking WHERE usuario_id = ?";
        
        try (PreparedStatement checkStmt = conexao.prepareStatement(checkSql)) {
            checkStmt.setInt(1, usuarioId);
            
            try (ResultSet rs = checkStmt.executeQuery()) {
                if (rs.next()) {
                    int pontuacaoAtual = rs.getInt("pontuacao");
                    
                    // Só atualiza se a nova pontuação for maior
                    if (pontuacao > pontuacaoAtual) {
                        String updateSql = "UPDATE ranking SET pontuacao = ?, " +
                                          "data_atualizacao = CURRENT_TIMESTAMP WHERE usuario_id = ?";
                        
                        try (PreparedStatement updateStmt = conexao.prepareStatement(updateSql)) {
                            updateStmt.setInt(1, pontuacao);
                            updateStmt.setInt(2, usuarioId);
                            updateStmt.executeUpdate();
                        }
                    }
                } else {
                    // Jogador não está no ranking, insere novo registro
                    String insertSql = "INSERT INTO ranking (usuario_id, pontuacao) VALUES (?, ?)";
                    
                    try (PreparedStatement insertStmt = conexao.prepareStatement(insertSql)) {
                        insertStmt.setInt(1, usuarioId);
                        insertStmt.setInt(2, pontuacao);
                        insertStmt.executeUpdate();
                    }
                }
            }
        }
    }
    
    // Obtém o top 10 do ranking
    public List<RankingEntry> getTopRanking(int limit) throws SQLException {
        String sql = "SELECT u.username, r.pontuacao FROM ranking r " +
                     "JOIN usuarios u ON r.usuario_id = u.id " +
                     "ORDER BY r.pontuacao DESC LIMIT ?";
        
        List<RankingEntry> ranking = new ArrayList<>();
        
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, limit);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String username = rs.getString("username");
                    int pontuacao = rs.getInt("pontuacao");
                    ranking.add(new RankingEntry(username, pontuacao));
                }
            }
        }
        
        return ranking;
    }
    
    // Classe interna para representar uma entrada no ranking
    public static class RankingEntry {
        private String username;
        private int pontuacao;
        
        public RankingEntry(String username, int pontuacao) {
            this.username = username;
            this.pontuacao = pontuacao;
        }
        
        public String getUsername() {
            return username;
        }
        
        public int getPontuacao() {
            return pontuacao;
        }
        
        @Override
        public String toString() {
            return username + ": " + pontuacao + " pontos";
        }
    }
}
