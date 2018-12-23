package com.javarush.task.task38.task3804;

public class ExceptionFactory {

    public static Throwable createExeption(Enum arg) throws Exception{
        if(arg != null) {
            String message = arg.toString();
            message = message.substring(0, 1) + message.substring(1).replaceAll("_", " ").toLowerCase();

            if(arg instanceof ExceptionApplicationMessage)
                return new Exception(message);

            if(arg instanceof ExceptionDBMessage)
                return new RuntimeException(message);

            if(arg instanceof ExceptionUserMessage)
                return new Error(message);

        }
        return new IllegalArgumentException();
    }

}
