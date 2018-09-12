package com.javarush.task.task31.task3110;


import com.javarush.task.task31.task3110.command.ExitCommand;

import java.io.*;

import java.io.IOException;
import java.nio.file.Paths;

public class Archiver {

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Schreiben Sie bitte Pfad zum Archive mit nameOfFile.zip am Ende an, thausend Dank...");
        ZipFileManager zipFileManager = null;

        try {
            zipFileManager = new ZipFileManager(Paths.get(reader.readLine()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Schreiben Sie bitte Pfad zum File an, der muss archiviert werden, thausend Dank...");

        try {
            zipFileManager.createZip(Paths.get(reader.readLine()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        ExitCommand exitCommand = new ExitCommand();
        try {
            exitCommand.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
