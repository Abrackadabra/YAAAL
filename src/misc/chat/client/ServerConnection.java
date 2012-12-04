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
    Socket socket;
    InputStream in;
    OutputStream out;
    String nickName;
    boolean activeConnection = true;
    Client client;

    public ServerConnection(String address, int port, String nickName, Client client) throws IOException {
        this.nickName = nickName;
        this.client = client;

        socket = new Socket(address, port);

        in = socket.getInputStream();
        out = socket.getOutputStream();

        listeningThread = new ListeningThread(this);
        listeningThread.start();

        hello();
    }

    public void setActiveConnection(boolean activeConnection) {
        if (activeConnection) {
            listeningThread.flush();
        }
        this.activeConnection = activeConnection;
    }

    public InputStream getIn() {
        return in;
    }

    public boolean isActiveConnection() {
        return activeConnection;
    }

    void hello() throws IOException {
        out.write(MessageUtils.hello(nickName));
        out.flush();
    }

    public void post(String message) throws IOException {
        out.write(MessageUtils.message(nickName, message));
        out.flush();
    }

    @Override
    public String toString() {
        return socket.getInetAddress().toString() + ":" + socket.getPort();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj;
    }

    boolean dying = false;

    public void disconnect() {
        dying = true;
        client.dropServer(this);
        try {
            out.write(MessageUtils.bye());
            out.flush();
        } catch (Exception e) {
        }

        listeningThread.flush();
        listeningThread.interrupt();

        try {
            socket.close();
        } catch (Exception e) {
        }

        try {
            in.close();
        } catch (Exception e) {
        }

        try {
            out.close();
        } catch (Exception e) {
        }
        this.toString();
        System.out.println("You got disconnected from " + this.toString());
    }

    ListeningThread listeningThread;
}