package com.explodeman.mvc_version_console.view;

import com.explodeman.mvc_version.view.Printer;

public class ConsolePrinter implements Printer {
    @Override
    public void output(String message) {
        System.out.println(message);
    }
}
