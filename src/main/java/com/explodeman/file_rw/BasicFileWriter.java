package com.explodeman.file_rw;

import java.io.FileWriter;
import java.io.IOException;

public class BasicFileWriter implements MyFileWriter{
    private final FileWriter writer;

    public BasicFileWriter(String filename) {
        try {
            writer = new FileWriter(filename);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

//        try(FileWriter writer = new FileWriter("notes3.txt", false))
//        {
//            // запись всей строки
//            String text = "Hello Gold!";
//            writer.write(text);
//            // запись по символам
//            writer.append('\n');
//            writer.append('E');
//
//            writer.flush();
//        }
//        catch(IOException ex){
//
//            System.out.println(ex.getMessage());
//        }
    }

    @Override
    public void write(String message) {
        try {
            writer.write(message);
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
