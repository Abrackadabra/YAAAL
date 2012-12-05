package misc.chat.server;

import java.io.*;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * Author: Abrackadabra
 * Date: 04/12/12
 * Time: 16:57
 */
public class Server {
    public static void main(String[] args) {
        new Server().run();
    }

    private void run() {
        takenNickNames.add("server");

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String command = null;
            try {
                command = input.readLine();
            } catch (IOException e) {
                System.err.println("Could not read from cin");
                System.exit(1);
            }
            processCommand(command);
        }
    }

    private ArrayList<ClientConnection> clients = new ArrayList<ClientConnection>();
    private HashSet<String> takenNickNames = new HashSet<String>();
    private ListeningThread listeningThread;

    void addClient(ClientConnection clientConnection) {
        if (!clientConnection.isAlive()) return;

        clients.add(clientConnection);
        takenNickNames.add(clientConnection.getNickName());

        announce("server", clientConnection.getNickName() + " has connected!");
    }

    private void processCommand(String command) {
        if (command.length() > 0 && command.charAt(0) == '/') {
            if (command.matches("/listen\\s\\d+")) {
                listen(Integer.parseInt(command.split(" ")[1]));
            } else if (command.matches("/stop")) {
                stop();
            } else if (command.matches("/list")) {
            } else if (command.matches("/send\\s\\d+\\s.+")) {
            } else if (command.matches("/sendall\\s.+")) {
            } else if (command.matches("/kill\\s\\d+")) {
            } else if (command.matches("/exit")) {
            } else {
                System.err.println("Wrong syntax");
            }
        } else {
            System.err.println("One does not simply chat from with a chat server");
        }
    }

    boolean isCorrectNickName(String nickName) {
        return !takenNickNames.contains(nickName);
    }

    void announce(String nickName, String message) {
        System.out.println("<" + nickName + "> " + message);
        for (ClientConnection client : clients) {
            client.sendMessage(nickName, message);
        }
    }

    private void listen(int port) {
        stop();

        System.out.println("Started listening port " + port);

        listeningThread = new ListeningThread(port, this);
        listeningThread.start();
    }

    private void stop() {
        if (listeningThread != null) {
            listeningThread.interrupt();
            listeningThread = null;

            System.out.println("Stopped listening");
        }
        for (ClientConnection client : clients) {
            client.disconnect("Server is shutting down");
        }
        validateClients();
    }

    void validateClients() {
        ArrayList<ClientConnection> newClients = new ArrayList<ClientConnection>();
        for (ClientConnection client : clients) {
            if (client.isAlive()) {
                newClients.add(client);
            } else {
                takenNickNames.remove(client.getNickName());
            }
        }
        clients = newClients;
    }
}
