package com.explodeman.mvc_version_terminal.view;

import com.explodeman.file_rw.MyFileWriter;
import com.explodeman.mvc_version.view.Printer;

public class TermPrinter implements Printer {
    private final MyFileWriter writer;

    public TermPrinter(MyFileWriter writer) {
        this.writer = writer;
    }

    @Override
    public void output(String message) {
        writer.write("\n" + message);
    }
}
