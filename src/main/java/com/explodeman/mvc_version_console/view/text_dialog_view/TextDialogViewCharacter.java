package com.explodeman.mvc_version_console.view.text_dialog_view;

import com.explodeman.mvc_version.view.Printer;
import com.explodeman.mvc_version.view.Reader;

import java.util.function.Function;
import java.util.function.Predicate;

public class TextDialogViewCharacter extends AbstractTextDialogView<Character> {

    public TextDialogViewCharacter(Printer printer, Reader reader, String title, String fail, Predicate<Character> check) {
        super(printer, reader, title, fail, createMapper(), check);
    }

    private static Function<String, Character> createMapper() {
        return s -> {
            if (s.length() != 1) {
                throw new NumberFormatException();
            }
            return s.charAt(0);
        };
    }

}
