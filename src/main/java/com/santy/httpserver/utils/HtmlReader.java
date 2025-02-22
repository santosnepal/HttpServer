package com.santy.httpserver.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

public class HtmlReader {
    public String getHtmlFile(String fileName){
        String filePath = Objects.requireNonNull(HtmlReader.class.getClassLoader().getResource(fileName)).getPath();
        try {
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            throw new RuntimeException("Failed to read the file: " + fileName, e);
        }
    }
}
