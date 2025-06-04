package cliente;

import java.io.*;
import java.net.*;

public class ClienteBasico {
    
    // Endereço IP e porta do servidor
    private static final String SERVIDOR_IP = "localhost";
    private static final int SERVIDOR_PORTA = 12345;
    
    public static void main(String[] args) {
        try {
            // Conecta ao servidor
            System.out.println("Conectando ao servidor...");
            Socket socket = new Socket(SERVIDOR_IP, SERVIDOR_PORTA);
            System.out.println("Conectado ao servidor!");
            
            // Configura streams para comunicação com o servidor
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter saida = new PrintWriter(socket.getOutputStream(), true);
            
            // Recebe mensagem de boas-vindas do servidor
            String mensagemServidor = entrada.readLine();
            System.out.println("Servidor: " + mensagemServidor);
            
            // Envia uma mensagem ao servidor
            saida.println("Olá, servidor! Estou pronto para jogar!");
            
            // Recebe resposta do servidor
            mensagemServidor = entrada.readLine();
            System.out.println("Servidor: " + mensagemServidor);
            
            // Fecha as conexões
            entrada.close();
            saida.close();
            socket.close();
            
        } catch (IOException e) {
            System.out.println("Erro no cliente: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
