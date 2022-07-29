package client;

import constants.Constants;

import java.io.IOException;
import java.net.Socket;

public class ClientMain1 {

    public static void main(String[] args) throws IOException {
        int port = Constants.CONFIG.getProperty(Integer.class, "serverPort");
        Client client = new Client(new Socket("localhost", port));
        new Thread(client).start();
    }

}
