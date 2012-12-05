package misc.chat.client;

import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * Author: Abrackadabra
 * Date: 04/12/12
 * Time: 18:35
 */

class ServerConnection {
    private String nickName;
    private Client client;

    private String id;

    private boolean alive = true;

    private CommunicationThread communicationThread;

    ServerConnection(String address, int port, String nickName, Client client) {
        this.nickName = nickName;
        this.client = client;

        try {
            Socket socket = new Socket(address, port);

            id = socket.getInetAddress().toString() + ":" + socket.getPort();

            communicationThread = new CommunicationThread(socket, this);
            communicationThread.start();
        } catch (Exception e) {
            disconnect();
        }
    }

    boolean isAlive() {
        return alive;
    }

    boolean isCurrentServer() {
        return client.isCurrentServer(this);
    }

    String getNickName() {
        return nickName;
    }

    void disconnect() {
        if (!alive) {
            return;
        }

        alive = false;

        try {
            communicationThread.bye();
        } catch (Exception e) {
        }

        communicationThread.flush();
        communicationThread.interrupt();

        System.out.println("You got disconnected from " + this);

        client.validateServers();
    }

    void post(String message) {
        try {
            communicationThread.post(message);
        } catch (Exception e) {
            disconnect();
        }
    }

    @Override
    public String toString() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj;
    }
}