package client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client implements Runnable{
    private final PrintWriter printWriter;
    private final Scanner scanner;
    private final Socket socket;
    private static Client client;
    public Client(Socket socket) throws IOException {
        this.socket = socket;
        this.printWriter = new PrintWriter(socket.getOutputStream());
        this.scanner = new Scanner(socket.getInputStream());
        Client.client = this;
    }

    public void init() throws IOException {
        sendMessage("message from client");

        while (true){
            String input = scanner.nextLine();
            System.out.println(input);
        }
    }

    public void sendMessage(String message){
        printWriter.println(message);
        printWriter.flush();
    }


    @Override
    public void run() {
        try {
            init();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
