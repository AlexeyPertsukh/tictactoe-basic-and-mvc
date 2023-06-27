package com.explodeman.mvc_version_terminal.view.term_dialog_view;

import com.explodeman.mvc_version.model.board.Position;
import com.explodeman.mvc_version.view.Printer;
import com.explodeman.mvc_version.view.Reader;
import com.explodeman.mvc_version.view.dialog_view.DialogView;

public class TermDialogViewPosition implements DialogView<Position> {

    private final Printer printer;
    private final Reader reader;
    private final long tittle;

    public TermDialogViewPosition(Printer printer, Reader reader, long tittle) {
        this.printer = printer;
        this.reader = reader;
        this.tittle = tittle;
    }

    @Override
    public Position input() {
        printer.output(String.valueOf(tittle));
        String s = reader.input();
        int number = Integer.parseInt(s);
        return new Position(number / 3, number % 3);
    }
}
