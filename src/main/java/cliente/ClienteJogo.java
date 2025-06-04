package cliente;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClienteJogo {
    
    private static final String SERVIDOR_IP = "localhost";
    private static final int SERVIDOR_PORTA = 12345;
    
    public static void main(String[] args) {
        try {
            // Conecta ao servidor
            System.out.println("Conectando ao servidor do jogo...");
            Socket socket = new Socket(SERVIDOR_IP, SERVIDOR_PORTA);
            System.out.println("Conectado ao servidor!");
            
            // Configura streams para comunicação
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter saida = new PrintWriter(socket.getOutputStream(), true);
            
            // Scanner para ler entrada do usuário
            Scanner scanner = new Scanner(System.in);
            
            // Thread para receber mensagens do servidor
            Thread receptorMensagens = new Thread(() -> {
                try {
                    String mensagemServidor;
                    while ((mensagemServidor = entrada.readLine()) != null) {
                        if (mensagemServidor.startsWith("INFO:")) {
                            System.out.println("[INFO] " + mensagemServidor.substring(5));
                        } else if (mensagemServidor.startsWith("RESULTADO:")) {
                            System.out.println("[RESULTADO] " + mensagemServidor.substring(10));
                        } else if (mensagemServidor.startsWith("VITORIA:")) {
                            System.out.println("[VITÓRIA] " + mensagemServidor.substring(8));
                        } else if (mensagemServidor.startsWith("CHAT:")) {
                            System.out.println("[CHAT] " + mensagemServidor.substring(5));
                        } else {
                            System.out.println(mensagemServidor);
                        }
                    }
                } catch (IOException e) {
                    System.out.println("Conexão com o servidor perdida: " + e.getMessage());
                }
            });
            receptorMensagens.start();
            
            // Loop principal para enviar comandos ao servidor
            boolean conectado = true;
            
            while (conectado) {
                System.out.print("> ");
                String entradaUsuario = scanner.nextLine().trim();
                
                if (entradaUsuario.equalsIgnoreCase("sair")) {
                    saida.println("SAIR");
                    conectado = false;
                } else if (entradaUsuario.equalsIgnoreCase("ajuda")) {
                    exibirAjuda();
                } else if (entradaUsuario.startsWith("chat ")) {
                    String mensagem = entradaUsuario.substring(5);
                    saida.println("CHAT:" + mensagem);
                } else if (entradaUsuario.startsWith("global ")) {
                    String mensagem = entradaUsuario.substring(7);
                    saida.println("CHAT_GLOBAL:" + mensagem);
                } else if (entradaUsuario.startsWith("adivinhar ")) {
                    try {
                        int palpite = Integer.parseInt(entradaUsuario.substring(10));
                        saida.println("ADIVINHAR:" + palpite);
                    } catch (NumberFormatException e) {
                        System.out.println("Por favor, digite um número válido após 'adivinhar'.");
                    }
                } else {
                    // Envia o comando como está
                    saida.println(entradaUsuario.toUpperCase());
                }
            }
            
            // Aguarda a thread de recepção terminar
            receptorMensagens.join(2000); // Espera até 2 segundos
            
            // Fecha recursos
            scanner.close();
            entrada.close();
            saida.close();
            socket.close();
            
        } catch (IOException | InterruptedException e) {
            System.out.println("Erro no cliente: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private static void exibirAjuda() {
        System.out.println("\n=== COMANDOS DISPONÍVEIS ===");
        System.out.println("LOGIN:username:senha - Fazer login");
        System.out.println("REGISTRAR:username:senha - Registrar novo usuário");
        System.out.println("LISTAR_SALAS - Listar salas disponíveis");
        System.out.println("ENTRAR:nomeSala - Entrar em uma sala");
        System.out.println("CRIAR_SALA:nome:maxNumero:maxJogadores - Criar uma nova sala");
        System.out.println("SAIR_SALA - Sair da sala atual");
        System.out.println("JOGADORES - Listar jogadores na sala atual");
        System.out.println("RANKING - Ver o ranking global");
        System.out.println("adivinhar numero - Tentar adivinhar o número");
        System.out.println("chat mensagem - Enviar mensagem para a sala atual");
        System.out.println("global mensagem - Enviar mensagem para todos os jogadores");
        System.out.println("ajuda - Exibir esta ajuda");
        System.out.println("sair - Encerrar o jogo");
        System.out.println("===========================\n");
    }
}
