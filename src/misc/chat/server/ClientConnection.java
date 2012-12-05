package misc.chat.server;

import java.io.*;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * Author: Abrackadabra
 * Date: 05/12/12
 * Time: 13:04
 */
class ClientConnection {
    private Server server;
    private String nickName;
    private CommunicationThread communicationThread;
    private boolean alive = true;

    boolean isAlive() {
        return alive;
    }

    void setNickName(String nickName) {
        if (!server.isCorrectNickName(nickName)) {
            disconnect();
        }

        this.nickName = nickName;
    }

    ClientConnection(Socket socket, Server server) {
        this.server = server;

        try {
            communicationThread = new CommunicationThread(socket, this);
            communicationThread.start();
        } catch (IOException e) {
            e.printStackTrace();
            disconnect(e.getMessage());
        }
    }

    void disconnect() {
        disconnect(null);
    }

    void disconnect(String message) {
        alive = false;

        System.err.println(message);

        if (message != null) {
            //senderror
        }

        //comT.bye();

        if (communicationThread != null) {
            communicationThread.interrupt();
        }

        server.validateClients();
    }

    @Override
    public int hashCode() {
        return nickName.hashCode();
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ClientConnection)) {
            return false;
        }
        ClientConnection client = (ClientConnection) object;
        return nickName.equals(client.nickName);
    }
}