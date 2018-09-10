package com.javarush.task.task31.task3111;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class SearchFileVisitor extends SimpleFileVisitor<Path> {
    private List<Path> foundFiles = new ArrayList<>();
    private String partOfName;
    private String partOfContent;
    private int minSize;
    private int maxSize;

    public List<Path> getFoundFiles() {
        return foundFiles;
    }

    public void setPartOfName(String partOfName) {
        this.partOfName = partOfName;
    }

    public void setPartOfContent(String partOfContent) {
        this.partOfContent = partOfContent;
    }

    public void setMinSize(int minSize) {
        this.minSize = minSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
     //   byte[] content = Files.readAllBytes(file); // размер файла: content.length
        boolean isFitWhithFilter = true;

        if(partOfName != null && isFitWhithFilter)
            isFitWhithFilter = file.getFileName().toString().contains(partOfName);

        if (partOfContent != null && isFitWhithFilter)
            isFitWhithFilter = Files.readAllLines(file).toString().contains(partOfContent);

        if(minSize != 0 && isFitWhithFilter)
            isFitWhithFilter = attrs.size() > minSize;

        if (maxSize != 0 && isFitWhithFilter)
            isFitWhithFilter = attrs.size() < maxSize;

        if (isFitWhithFilter)
            foundFiles.add(file);

        return FileVisitResult.CONTINUE; //super.visitFile(file, attrs);
    }
}
