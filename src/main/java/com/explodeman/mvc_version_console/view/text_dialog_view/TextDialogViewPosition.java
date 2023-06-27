package com.explodeman.mvc_version_console.view.text_dialog_view;

import com.explodeman.mvc_version.model.board.Position;
import com.explodeman.mvc_version.view.Printer;
import com.explodeman.mvc_version.view.Reader;

import java.util.function.Function;
import java.util.function.Predicate;

public class TextDialogViewPosition extends AbstractTextDialogView<Position> {

    public TextDialogViewPosition(Printer printer, Reader reader, String title, String fail, Function<String, Position> mapper, Predicate<Position> check) {
        super(printer, reader, title, fail, mapper, check);
    }

}
