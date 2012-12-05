package misc.chat.client;

import misc.chat.Message;
import misc.chat.MessageType;
import misc.chat.MessageUtils;

import java.io.*;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * Author: Abrackadabra
 * Date: 05/12/12
 * Time: 00:24
 */
class CommunicationThread extends Thread {
    private ServerConnection server;
    private InputStream in;
    private OutputStream out;

    CommunicationThread(ServerConnection server) throws IOException {
        this.server = server;

        in = server.getSocket().getInputStream();
        out = server.getSocket().getOutputStream();
    }

    @Override
    public void run() {
        try {
            hello();
            while (true) {
                getMessage();
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
            server.disconnect();
        }
    }

    void hello() throws IOException {
        out.write(MessageUtils.hello(server.getNickName()));
        out.flush();
    }

    void post(String message) throws IOException {
        out.write(MessageUtils.message(server.getNickName(), message));
        out.flush();
    }

    void bye() throws IOException {
        out.write(MessageUtils.bye());
        out.flush();
    }

    private StringBuilder buffer = new StringBuilder();

    void getMessage() throws IOException {
        Message message = Message.readMessage(in);

        switch (message.getType()) {
            case ERROR:
                throw new IOException(message.getContents()[0]);
            case BYE:
                throw new IOException("Server dropped connection");
            case HELLO:
                throw new IOException("Unexpected behaviour");
            case MESSAGE:
                if (message.getContents().length != 2)
                    throw new IOException("Wrong message format");
        }

        buffer.append('<');
        buffer.append(message.getContents()[0]);
        buffer.append('>');
        buffer.append(' ');
        buffer.append(message.getContents()[1]);
        buffer.append('\n');

        if (server.isCurrentServer()) {
            flush();
        }
    }

    public void flush() {
        System.out.print(buffer.toString());
        buffer = new StringBuilder();
    }
}
