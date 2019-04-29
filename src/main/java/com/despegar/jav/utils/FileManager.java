package com.despegar.jav.utils;


import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FileManager {
    public Path writeFile(String fileName, List<String> lines) {
        Path file = Paths.get(System.getProperty("user.dir") + "/" + fileName);
        try {
            return Files.write(file, lines, Charset.forName("UTF-8"));
        } catch (IOException e) {
            throw new RuntimeException("Error writing file");
        }
    }

    public String fileToText(String path) throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader(path));
        List<String> lines = reader.lines().collect(Collectors.toList());

        StringBuilder sb = new StringBuilder();
        lines.forEach(line -> sb.append(line).append("\n"));

        return sb.toString();
    }
}