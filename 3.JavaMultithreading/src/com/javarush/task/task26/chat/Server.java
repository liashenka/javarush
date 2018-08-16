package com.javarush.task.task30.task3008;

import java.io.Console;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//
public class Server {
    private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>(); // key - name of client  ,
    // value - connection with him


    public static void main(String[] args) throws IOException {

        System.out.println("Enter the server port...");
        try (ServerSocket serverSocket = new ServerSocket(ConsoleHelper.readInt())) {
            System.out.println("Waiting for a client...");

            while (true) {
                new Handler(serverSocket.accept()).start();
            }
        } catch (IOException e) {
            System.out.println("Error obtaining server socket...");
        }
    }


    private static class Handler extends Thread {  //реализиует протокол общения с клиентом
        private Socket socket;

        public Handler(Socket socket) {
            this.socket = socket;
        }


        public void run() {
            if (socket != null && socket.getRemoteSocketAddress() != null) {
                ConsoleHelper.writeMessage("Established a new connection to a remote socket address: " + socket.getRemoteSocketAddress());
            }
            String userName = null;
            try (Connection connection = new Connection(socket)) {
                userName = serverHandshake(connection);
                sendBroadcastMessage(new Message(MessageType.USER_ADDED, userName));
                sendListOfUsers(connection, userName);
                serverMainLoop(connection, userName);
            } catch (IOException | ClassNotFoundException e) {
                ConsoleHelper.writeMessage("An error occurred while communicating with the remote address");
            } finally {
                if (userName != null) {
                    connectionMap.remove(userName);
                    sendBroadcastMessage(new Message(MessageType.USER_REMOVED, userName));
                }
                ConsoleHelper.writeMessage("Closed connection to a remote socket address");
            }
        }


        private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException {
            while (true) {
                StringBuffer textMessage = new StringBuffer();
                Message message = connection.receive();
                if (message.getType() == MessageType.TEXT) {
                    textMessage.append(userName + ": " + message.getData());
                    sendBroadcastMessage(new Message(MessageType.TEXT, textMessage.toString()));
                } else {
                    ConsoleHelper.writeMessage("Error: message must contain text.");
                }
            }
        }


        private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException {
            String name;
            while (true) {
                connection.send(new Message(MessageType.NAME_REQUEST));
                Message message = connection.receive();
                if (message.getType() == MessageType.USER_NAME) {
                    name = message.getData();
                    if (!name.isEmpty() && !connectionMap.containsKey(name)) {
                        connectionMap.put(name, connection);
                        connection.send(new Message(MessageType.NAME_ACCEPTED));
                        return name;
                    }
                }
            }
        }


        private void sendListOfUsers(Connection connection, String userName) throws IOException {
            for (ConcurrentHashMap.Entry<String, Connection> sendListOfUsers : connectionMap.entrySet()) {
                if (userName != sendListOfUsers.getKey()) {
                    connection.send(new Message(MessageType.USER_ADDED, sendListOfUsers.getKey()));
                }
            }
        }

    }


    public static void sendBroadcastMessage(Message message) {

        for (Map.Entry<String, Connection> entry : connectionMap.entrySet()) {
            try {
                entry.getValue().send(message);
            } catch (IOException e) {
                System.out.println("Messages not sent.");
            }
        }
    }
}
