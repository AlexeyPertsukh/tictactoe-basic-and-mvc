package com.explodeman.mvc_version_console.view.text_dialog_view;

import com.explodeman.mvc_version.view.Printer;
import com.explodeman.mvc_version.view.Reader;
import com.explodeman.mvc_version.view.dialog_view.DialogView;

import java.util.function.Function;
import java.util.function.Predicate;

public abstract class AbstractTextDialogView<T> implements DialogView<T> {
    protected final Printer printer;
    protected final Reader reader;
    private final String title;
    private final String fail;
    protected final Function<String, T> mapper;
    protected final Predicate<T> check;

    public AbstractTextDialogView(Printer printer, Reader reader, String title, String fail, Function<String, T> mapper, Predicate<T> check) {
        this.printer = printer;
        this.reader = reader;
        this.title = title;
        this.fail = fail;
        this.mapper = mapper;
        this.check = check;
    }

    @Override
    public T input() {
        showTitle();
        while (true) {
            String key = reader.input();
            try {
                T result = mapper.apply(key);
                if (check.test(result)) {
                    return result;
                }
            } catch (NumberFormatException ignored) {
            }
            showFail();
        }
    }

    protected void showTitle() {
        printer.output(title);
    }

    protected void showFail() {
        printer.output(fail);
    }

}
