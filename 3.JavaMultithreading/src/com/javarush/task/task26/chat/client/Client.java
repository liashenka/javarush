package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.Connection;
import com.javarush.task.task30.task3008.ConsoleHelper;
import com.javarush.task.task30.task3008.Message;
import com.javarush.task.task30.task3008.MessageType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {
    protected Connection connection;
    private volatile boolean clientConnected = false;

    public static void main(String[] args) throws IOException {
        Client client = new Client();
        client.run();
    }

    public void run() throws IOException {
        // boolean loop = true;
        SocketThread socketThread = getSocketThread();
        socketThread.setDaemon(true);   //при выходе из программы вспомогательный поток прервется автоматически
        socketThread.start();
        try {
            synchronized (this) {
                wait();
            }
        } catch (InterruptedException e) {
            System.out.println("Es wurde ein Fehler getroffen, es tut mir leid");
            System.exit(1);
        }
        if (clientConnected) {
            ConsoleHelper.writeMessage("Соединение установлено. Для выхода наберите команду 'exit'.");
            while (clientConnected) {
                String message = ConsoleHelper.readString();
                if (message == "exit") break;
                else {
                    if (shouldSendTextFromConsole()) {
                        sendTextMessage(message);
                    }
                }
            }
        } else {
            ConsoleHelper.writeMessage("Произошла ошибка во время работы клиента.");
        }
    }

    protected String getServerAddress() throws IOException {
        String serverAddress = null;
        ConsoleHelper.writeMessage("Enter server address to start...");
        serverAddress = ConsoleHelper.readString();

        return serverAddress;
    }

    protected int getServerPort() throws IOException {
        ConsoleHelper.writeMessage("Enter server port...");
        return ConsoleHelper.readInt();
    }

    protected String getUserName() throws IOException {
        ConsoleHelper.writeMessage("Entern Sie bitte Username...:)");
        return ConsoleHelper.readString();
    }

    protected boolean shouldSendTextFromConsole() { //Этот метод может быть переопределен,
        // если мы будем писать какой-нибудь другой клиент, унаследованный от нашего,
        // который не должен отправлять введенный в консоль текст.
        return true;
    }

    protected SocketThread getSocketThread() {
        return new SocketThread();
    }

    protected void sendTextMessage(String text) {
        try {
            connection.send(new Message(MessageType.TEXT, text));
        } catch (IOException e) {
            ConsoleHelper.writeMessage("A connection fails to send your message...");
            clientConnected = false;
        }
    }

    public class SocketThread extends Thread {
        protected void processIncomingMessage(String message) {
            ConsoleHelper.writeMessage(message);
        }

        protected void informAboutAddingNewUser(String userName) {
            ConsoleHelper.writeMessage(userName + " joined the chat.");
        }

        protected void informAboutDeletingNewUser(String userName) {
            ConsoleHelper.writeMessage(userName + " left the chat.");
        }

        protected void notifyConnectionStatusChanged(boolean clientConnected) {
            Client.this.clientConnected = clientConnected;
            synchronized (Client.this) {
                Client.this.notify();
            }
        }

        protected void clientHandshake() throws IOException, ClassNotFoundException {
            Message message;

            while (true) {
                message = connection.receive();
                if (message.getType() == MessageType.NAME_REQUEST) {
                    connection.send(new Message(MessageType.USER_NAME, getUserName()));
                } else {
                    if (message.getType() == MessageType.NAME_ACCEPTED) {
                        notifyConnectionStatusChanged(true);
                        break;
                    } else {
                        throw new IOException("Unexpected MessageType");
                    }
                }
            }
        }

        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            Message message;
            while (true) {
                message = connection.receive();
                if (message.getType() == MessageType.TEXT) {
                    processIncomingMessage(message.getData());
                } else {
                    if (message.getType() == MessageType.USER_ADDED) {
                        informAboutAddingNewUser(message.getData());
                    } else {
                        if (message.getType() == MessageType.USER_REMOVED) {
                            informAboutDeletingNewUser(message.getData());
                        } else {
                            break;
                        }
                    }
                }
            }
            throw new IOException("Unexpected MessageType");
        }

        public void run(){

            try {
                String ipAdress = getServerAddress();
                int serverPort = getServerPort();
                Socket socket = new Socket(ipAdress, serverPort);
                connection = new Connection(socket);
                clientHandshake();
                clientMainLoop();
            } catch (IOException e) {
                notifyConnectionStatusChanged(false);
            } catch (ClassNotFoundException e) {
                notifyConnectionStatusChanged(false);
            }
        }
    }
}
