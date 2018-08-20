package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.Connection;
import com.javarush.task.task30.task3008.ConsoleHelper;
import com.javarush.task.task30.task3008.Message;
import com.javarush.task.task30.task3008.MessageType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Client {
    protected Connection connection;
    private volatile boolean clientConnected = false;

    public static void main(String[] args) throws IOException {
        Client client = new Client();
        client.run();
    }

    public void run() throws IOException {
        boolean loop = true;
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

    }
}
