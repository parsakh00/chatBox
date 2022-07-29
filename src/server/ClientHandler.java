package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler implements Runnable{

    private final Socket socket;
    private final PrintWriter out;
    private final String authToken;

    public ClientHandler(Socket socket, String authToken) throws IOException {
        this.socket = socket;
        this.authToken = authToken;
        out = new PrintWriter(socket.getOutputStream());
    }

    public String getAuthToken() {
        return authToken;
    }

    @Override
    public void run() {
        System.out.println("new client handler is running...");
        try {
            Scanner in = new Scanner(socket.getInputStream());
            while (true){
                System.out.println("client handler is waiting for message...");
                String messageFromClient = in.nextLine();
                System.out.println("message from client : " + messageFromClient);
                sendMessage("your token is : " + getAuthToken());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendMessage(String message){
        out.println(message);
        out.flush();
    }

    public void kill() throws IOException {
        socket.close();
    }
}
