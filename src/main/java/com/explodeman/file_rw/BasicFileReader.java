package com.explodeman.file_rw;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BasicFileReader implements MyFileReader {

    private final File file;

    public BasicFileReader(String filename) {
        file = new File(filename);
    }

    @Override
    public List<String> read() {
        List<String> words = new ArrayList<>();

        Scanner sc = null;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("read file error: " + file.getName());
        }

        while (sc.hasNext()) {
            String word = sc.nextLine();
            words.add(word);
        }
        return words;
    }

    @Override
    public long lastFileModified() {
        return file.lastModified();
    }

}
