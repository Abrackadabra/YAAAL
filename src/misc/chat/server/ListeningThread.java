package misc.chat.server;

import java.io.*;
import java.net.ServerSocket;

/**
 * Created with IntelliJ IDEA.
 * Author: Abrackadabra
 * Date: 05/12/12
 * Time: 13:09
 */
class ListeningThread extends Thread {
    private Server server;
    private int port;

    ListeningThread(int port, Server server) {
        this.port = port;
        this.server = server;
    }

    ServerSocket serverSocket;

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(port);
            while (true) {
                server.addClient(new ClientConnection(serverSocket.accept(), server));
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    void close() {
        try {
            serverSocket.close();
        } catch (IOException e) {
        }
    }
}
