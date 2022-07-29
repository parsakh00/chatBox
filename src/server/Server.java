package server;

import authToken.AuthenticationToken;
import constants.Constants;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    private final ArrayList<ClientHandler> clientHandlers;
    private static Server server;

    public Server() {
        clientHandlers = new ArrayList<>();
        Server.server = this;
    }

    public void init(){
        System.out.println("server is running...");
        try {
            int port = Constants.CONFIG.getProperty(Integer.class, "serverPort");
            ServerSocket serverSocket = new ServerSocket(port);
            while (true){
                System.out.println("waiting for connection...");
                Socket socket = serverSocket.accept();
                addNewClientHandler(socket);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void addNewClientHandler(Socket socket) throws IOException {
        ClientHandler clientHandler = new ClientHandler(socket, AuthenticationToken.generateNewToken());
        if (clientHandlers.size() < 5) {
            System.out.println("new connection accepted");
            clientHandlers.add(clientHandler);
            new Thread(clientHandler).start();
        }
        else if(clientHandlers.size() == 5){
            //ToDo start group chat
        }
        else{
            clientHandler.sendMessage("server is full");
            clientHandler.kill();
        }
    }
}
