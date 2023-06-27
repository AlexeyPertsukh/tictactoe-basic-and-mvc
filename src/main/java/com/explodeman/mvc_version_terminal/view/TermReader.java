package com.explodeman.mvc_version_terminal.view;

import com.explodeman.file_rw.MyFileReader;
import com.explodeman.mvc_version.view.Reader;

import java.util.List;

public class TermReader implements Reader {
    private final MyFileReader fileReader;
    private long lastFileModify;

    public TermReader(MyFileReader fileReader) {
        this.fileReader = fileReader;
        this.lastFileModify = fileReader.lastFileModified();
    }

    private String readLastLine() {
        List<String> list = fileReader.read();
        return list.get(list.size() - 1);
    }

    @Override
    public String input() {
        while (true) {
            long currentFileModify = fileReader.lastFileModified();
            if (lastFileModify != currentFileModify) {
                lastFileModify = currentFileModify;
                try {
                    return readLastLine();
                } catch (IndexOutOfBoundsException ignored) {}

            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
