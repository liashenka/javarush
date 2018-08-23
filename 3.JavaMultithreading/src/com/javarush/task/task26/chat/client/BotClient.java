package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.Connection;
import com.javarush.task.task30.task3008.ConsoleHelper;
import com.javarush.task.task30.task3008.Message;
import com.javarush.task.task30.task3008.MessageType;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class BotClient extends Client {
    @Override
    protected SocketThread getSocketThread() {
        return new BotSocketThread();
    }

    @Override
    protected boolean shouldSendTextFromConsole() {
        return false;
    }

    @Override
    protected String getUserName() {
        return "date_bot_" + (int) (Math.random() * 100);
    }


    public class BotSocketThread extends SocketThread {

        @Override
        protected void clientMainLoop() {
            sendTextMessage("Привет чатику. Я бот. Понимаю команды: дата, день, месяц, год, время, час, минуты, секунды.");
            //б) Вызови реализацию clientMainLoop() родительского класса.
            try {
                super.clientMainLoop();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void processIncomingMessage(String message) {
            if (message != null)
                ConsoleHelper.writeMessage(message);

            SimpleDateFormat format = null;

            if (message.contains(": ")) {
                String[] array = message.split(": ");
                String senderName = array[0];
                String text = array[1];

                switch (text) {
                    case "дата":
                        format = new SimpleDateFormat("d.MM.YYYY");
                        break;
                    case "день":
                        format = new SimpleDateFormat("d");
                        break;
                    case "месяц":
                        format = new SimpleDateFormat("MMMM");
                        break;
                    case "год":
                        format = new SimpleDateFormat("YYYY");
                        break;
                    case "время":
                        format = new SimpleDateFormat("H:mm:ss");
                        break;
                    case "час":
                        format = new SimpleDateFormat("H");
                        break;
                    case "минуты":
                        format = new SimpleDateFormat("m");
                        break;
                    case "секунды":
                        format = new SimpleDateFormat("s");
                        break;

                }
                if(format != null) sendTextMessage(String.format("Информация для %s: %s", senderName, format.format(Calendar.getInstance().getTime())));
            }
        }
    }

    public static void main(String[] args) {
        BotClient botClient = new BotClient();
        try {
            botClient.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
