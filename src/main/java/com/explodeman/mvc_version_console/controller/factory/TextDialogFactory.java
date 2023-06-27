package com.explodeman.mvc_version_console.controller.factory;

import com.explodeman.mvc_version.controller.factory.DialogFactory;
import com.explodeman.mvc_version.model.board.Board;
import com.explodeman.mvc_version.model.board.Position;
import com.explodeman.mvc_version.model.player.Player;
import com.explodeman.mvc_version.view.Printer;
import com.explodeman.mvc_version.view.Reader;
import com.explodeman.mvc_version.view.dialog_view.DialogView;
import com.explodeman.mvc_version_console.view.text_dialog_view.TextDialogViewPosition;

import java.util.function.Function;

public class TextDialogFactory implements DialogFactory {
    private final Printer printer;
    private final Reader reader;
    private final Board board;
    private final char startBoardNumerationSymbol;

    public TextDialogFactory(Printer printer, Reader reader, Board board, char startBoardNumerationSymbol) {
        this.printer = printer;
        this.reader = reader;
        this.board = board;
        this.startBoardNumerationSymbol = startBoardNumerationSymbol;
    }
    @Override
    public DialogView<Position> getDvPoint(Player player) {
        String tittle = String.format("Player %c, enter you move: ", player.getFigure().getSymbol());
        String fail = "illegal move, try again";
        return new TextDialogViewPosition(printer, reader, tittle, fail, mapper, p-> p.row >= 0 && p.column >=0 && p.row < board.side() && p.column < board.side());
    }

    private final Function<String, Position> mapper = new Function<>() {
        @Override
        public Position apply(String s) {
            if(s.length() != 1) {
                throw new NumberFormatException();
            }
            int number = s.charAt(0) - startBoardNumerationSymbol;
            int row = number / board.side();
            int column = number % board.side();
            return new Position(row, column);
        }
    };
}
