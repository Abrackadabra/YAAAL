package misc.chat.client;

import misc.chat.MessageType;

import java.io.*;
import java.net.SocketException;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * Author: Abrackadabra
 * Date: 05/12/12
 * Time: 00:24
 */
public class ListeningThread extends Thread {
    ServerConnection server;

    public ListeningThread(ServerConnection server) {
        this.server = server;
    }

    @Override
    public void run() {
        try {
            while (true) {
                getMessage();
            }
        } catch (IOException e) {
            if (!(e instanceof SocketException)) { // to hide SocketException when .interrupt()
                System.err.println(e.getMessage());
            }
            if (!server.dying) {
                server.disconnect();
            }
        }
    }

    StringBuilder stringBuilder = new StringBuilder();

    void getMessage() throws IOException {
        byte type = read();
        byte messageCount = read();
        ArrayList<byte[]> messages = new ArrayList<byte[]>();
        for (int i = 0; i < messageCount; i++) {
            int length = readInt();
            byte[] message = new byte[length];
            for (int j = 0; j < length; j++) {
                message[j] = read();
            }
            messages.add(message);
        }

        if (type == MessageType.ERROR.getId()) {
            throw new IOException(new String(messages.get(0)));
        }

        if (type == MessageType.BYE.getId()) {
            throw new IOException("Server dropped connection");
        }

        if (type != MessageType.MESSAGE.getId()) {
            throw new IOException("Unknown message type");
        }
        if (messageCount != 2) {
            throw new IOException("Wrong message format");
        }

        stringBuilder.append('<');
        stringBuilder.append(new String(messages.get(0)));
        stringBuilder.append('>');
        stringBuilder.append(' ');
        stringBuilder.append(new String(messages.get(1)));
        stringBuilder.append('\n');

        if (server.isActiveConnection()) {
            flush();
        }
    }

    public void flush() {
        System.out.print(stringBuilder.toString());
        stringBuilder = new StringBuilder();
    }

    byte read() throws IOException {
        int b = server.getIn().read();
        if (b == -1) {
            throw new IOException("Could not read");
        }
        return (byte) b;
    }

    int readInt() throws IOException {
        int i = 0;
        i += read() << 24;
        i += read() << 16;
        i += read() << 8;
        i += read();
        return i;
    }
}
