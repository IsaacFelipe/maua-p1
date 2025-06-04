package servidor;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class GerenciadorSalas {
    
    // Mapa de salas ativas (nome da sala -> objeto Sala)
    private Map<String, Sala> salas;
    
    // Mapa de sessões de jogadores (nome de usuário -> objeto Sessao)
    private Map<String, Sessao> sessoes;
    
    public GerenciadorSalas() {
        this.salas = new ConcurrentHashMap<>();
        this.sessoes = new ConcurrentHashMap<>();
        
        // Cria algumas salas padrão
        criarSala("Iniciantes", 50, 3);
        criarSala("Intermediários", 100, 5);
        criarSala("Avançados", 500, 10);
    }
    
    // Cria uma nova sala
    public synchronized Sala criarSala(String nome, int maxNumero, int maxJogadores) {
        if (salas.containsKey(nome)) {
            return null; // Sala já existe
        }
        
        Sala novaSala = new Sala(nome, maxNumero, maxJogadores);
        salas.put(nome, novaSala);
        return novaSala;
    }
    
    // Obtém uma sala pelo nome
    public Sala getSala(String nome) {
        return salas.get(nome);
    }
    
    // Lista todas as salas disponíveis
    public List<Sala> listarSalas() {
        return new ArrayList<>(salas.values());
    }
    
    // Registra uma nova sessão de jogador
    public synchronized void registrarSessao(String username, TratadorDeCliente tratador) {
        Sessao sessao = new Sessao(username, tratador);
        sessoes.put(username, sessao);
    }
    
    // Remove uma sessão de jogador
    public synchronized void removerSessao(String username) {
        Sessao sessao = sessoes.get(username);
        if (sessao != null) {
            // Se o jogador estava em uma sala, remove-o
            if (sessao.getSalaAtual() != null) {
                sessao.getSalaAtual().removerJogador(username);
            }
            sessoes.remove(username);
        }
    }
    
    // Obtém uma sessão pelo nome de usuário
    public Sessao getSessao(String username) {
        return sessoes.get(username);
    }
    
    // Envia uma mensagem para todos os jogadores em uma sala
    public void broadcastParaSala(String nomeSala, String mensagem) {
        Sala sala = salas.get(nomeSala);
        if (sala != null) {
            for (String jogador : sala.getJogadores()) {
                Sessao sessao = sessoes.get(jogador);
                if (sessao != null) {
                    sessao.enviarMensagem(mensagem);
                }
            }
        }
    }
    
    // Envia uma mensagem para todos os jogadores conectados
    public void broadcastGlobal(String mensagem) {
        for (Sessao sessao : sessoes.values()) {
            sessao.enviarMensagem(mensagem);
        }
    }
    
    // Classe interna para representar uma sala de jogo
    public static class Sala {
        private String nome;
        private int maxNumero;
        private int maxJogadores;
        private Set<String> jogadores;
        private int numeroSecreto;
        private boolean jogoEmAndamento;
        private Random random;
        
        public Sala(String nome, int maxNumero, int maxJogadores) {
            this.nome = nome;
            this.maxNumero = maxNumero;
            this.maxJogadores = maxJogadores;
            this.jogadores = new HashSet<>();
            this.random = new Random();
            this.jogoEmAndamento = false;
            gerarNovoNumero();
        }
        
        // Gera um novo número secreto para a sala
        public void gerarNovoNumero() {
            this.numeroSecreto = random.nextInt(maxNumero) + 1;
        }
        
        // Adiciona um jogador à sala
        public synchronized boolean adicionarJogador(String username) {
            if (jogadores.size() >= maxJogadores) {
                return false; // Sala cheia
            }
            jogadores.add(username);
            return true;
        }
        
        // Remove um jogador da sala
        public synchronized void removerJogador(String username) {
            jogadores.remove(username);
            
            // Se não houver mais jogadores, reinicia o jogo
            if (jogadores.isEmpty()) {
                jogoEmAndamento = false;
                gerarNovoNumero();
            }
        }
        
        // Getters
        public String getNome() {
            return nome;
        }
        
        public int getMaxNumero() {
            return maxNumero;
        }
        
        public int getMaxJogadores() {
            return maxJogadores;
        }
        
        public Set<String> getJogadores() {
            return new HashSet<>(jogadores); // Retorna uma cópia para evitar modificação externa
        }
        
        public int getNumeroSecreto() {
            return numeroSecreto;
        }
        
        public boolean isJogoEmAndamento() {
            return jogoEmAndamento;
        }
        
        public void setJogoEmAndamento(boolean jogoEmAndamento) {
            this.jogoEmAndamento = jogoEmAndamento;
        }
        
        public int getNumJogadores() {
            return jogadores.size();
        }
        
        @Override
        public String toString() {
            return nome + " (" + jogadores.size() + "/" + maxJogadores + " jogadores, máx: " + maxNumero + ")";
        }
    }
    
    // Classe interna para representar uma sessão de jogador
    public static class Sessao {
        private String username;
        private TratadorDeCliente tratador;
        private Sala salaAtual;
        
        public Sessao(String username, TratadorDeCliente tratador) {
            this.username = username;
            this.tratador = tratador;
            this.salaAtual = null;
        }
        
        // Envia uma mensagem para o jogador
        public void enviarMensagem(String mensagem) {
            tratador.enviarMensagem(mensagem);
        }
        
        // Define a sala atual do jogador
        public void setSalaAtual(Sala sala) {
            this.salaAtual = sala;
        }
        
        // Getters
        public String getUsername() {
            return username;
        }
        
        public Sala getSalaAtual() {
            return salaAtual;
        }
    }
}
