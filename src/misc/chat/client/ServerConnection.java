package misc.chat.client;

import misc.chat.MessageUtils;

import java.io.*;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * Author: Abrackadabra
 * Date: 04/12/12
 * Time: 18:35
 */

class ServerConnection {
    private Socket socket;
    private String nickName;
    private Client client;

    private boolean alive = true;

    private CommunicationThread communicationThread;

    ServerConnection(String address, int port, String nickName, Client client) {
        this.nickName = nickName;
        this.client = client;

        try {
            socket = new Socket(address, port);

            communicationThread = new CommunicationThread(this);
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

    Socket getSocket() {
        return socket;
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

        try {
            socket.close();
        } catch (Exception e) {
        }
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
        return socket.getInetAddress().toString() + ":" + socket.getPort();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj;
    }
}