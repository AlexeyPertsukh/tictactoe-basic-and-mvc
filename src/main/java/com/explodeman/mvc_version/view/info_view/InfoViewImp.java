package com.explodeman.mvc_version.view.info_view;

import com.explodeman.mvc_version.view.Printer;

import java.util.ArrayList;
import java.util.List;

public class InfoViewImp implements InfoView {
    private final Printer printer;
    private final List<String> messages;

    public InfoViewImp(Printer printer, List<String> messages) {
        this.printer = printer;
        this.messages = messages;
    }

    public InfoViewImp(Printer printer, String message) {
        this.printer = printer;
        this.messages = new ArrayList<>();
        messages.add(message);
    }

    @Override
    public void show() {
        for (String s : messages) {
            printer.output(s);
        }
    }

}
