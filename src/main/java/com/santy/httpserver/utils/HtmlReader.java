package com.santy.httpserver.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class HtmlReader {
    public String getHtmlFile(String fileName) {
        try (InputStream inputStream = HtmlReader.class.getClassLoader().getResourceAsStream(fileName)) {
            if (inputStream == null) {
                throw new RuntimeException("File not found: " + fileName);
            }
            return new String(inputStream.readAllBytes());
        } catch (IOException e) {
            throw new RuntimeException("Failed to read the file: " + fileName, e);
        }
    }
}