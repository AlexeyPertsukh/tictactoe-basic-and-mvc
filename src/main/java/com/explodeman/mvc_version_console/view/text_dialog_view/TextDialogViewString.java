package com.explodeman.mvc_version_console.view.text_dialog_view;

import com.explodeman.mvc_version.view.Printer;
import com.explodeman.mvc_version.view.Reader;

import java.util.function.Predicate;

public class TextDialogViewString extends AbstractTextDialogView<String> {

    public TextDialogViewString(Printer printer, Reader reader, String title, String fail, Predicate<String> check) {
        super(printer, reader, title, fail, s -> s, check);
    }

}
