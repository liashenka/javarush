package com.javarush.task.task33.task3310.strategy;

import com.javarush.task.task33.task3310.ExceptionHandler;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileBucket {
    Path path;

    public FileBucket() {
        try {
            path = Files.createTempFile(null, null );
            Files.deleteIfExists(path);
            Files.createFile(path);
        } catch (IOException e) {
            ExceptionHandler.log(e);
        }
        path.toFile().deleteOnExit();
    }

    public long getFileSize(){
        long size = 0;
        try {
            size = Files.size(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return size;
    }

    public void putEntry(Entry entry){
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(Files.newOutputStream(path));
            objectOutputStream.writeObject(entry);
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Entry getEntry(){
        Entry entry = null;
        if(getFileSize() == 0)
            return null;

        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(Files.newInputStream(path));
            entry = (Entry) objectInputStream.readObject();
            objectInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return entry;
    }

    public void remove(){
        try {
            Files.delete(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
