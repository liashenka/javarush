package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ResourceBundle;

import static com.javarush.task.task26.task2613.CashMachine.RESOURCE_PATH;

public class ConsoleHelper {
    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));
    private static ResourceBundle res = ResourceBundle.getBundle(RESOURCE_PATH + "common_en");

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws InterruptOperationException {
        String input = null;
        try {
            input = bis.readLine();
            if (input.equalsIgnoreCase("exit")) {
                throw new InterruptOperationException();
            }
        } catch (IOException ignored) {}

        return input;
    }

    public static String askCurrencyCode() throws InterruptOperationException {
        {
            String code = null;
            writeMessage(res.getString("choose.currency.code"));
            while (true) {
                code = readString();
                if (code.length() == 3)
                    break;
                else
                    writeMessage(res.getString("choose.operation"));

            }
            return code.toUpperCase();
        }
    }

    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException {
        writeMessage(String.format(res.getString("choose.denomination.and.count.format"), currencyCode));
        String[] twoDigits;
        int nominal;
        int total;

        while (true) {
            try {
                twoDigits = readString().split(" ");
                nominal = Integer.parseInt(twoDigits[0]);
                total = Integer.parseInt(twoDigits[1]);

            } catch (Exception e) {
                writeMessage(res.getString("invalid.data"));
                continue;
            }
            if(nominal <= 0 || total <= 0){
                writeMessage(res.getString("invalid.data"));
                continue;
            }
            break;
        }
        return twoDigits;
    }

    public static Operation askOperation() throws InterruptOperationException{
        Operation operation = null;

        while (true) {
            writeMessage("Select operation: 1 - " + res.getString("operation.INFO") +
            ", 2 - " + res.getString("operation.DEPOSIT") +
            ", 3 - " + res.getString("operation.WITHDRAW") +
            ", 4 - " + res.getString("operation.EXIT"));
            try {
                operation = Operation.getAllowableOperationByOrdinal(Integer.parseInt(readString()));
            } catch (IllegalArgumentException e) {
                writeMessage(res.getString("choose.operation"));
                continue;
            }
            writeMessage(res.getString("the.end"));
            break;
        }
        return operation;
    }

    public static void printExitMessage(){
        writeMessage("Chooooose");
    }
}
