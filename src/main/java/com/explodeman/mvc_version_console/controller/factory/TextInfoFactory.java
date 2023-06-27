package com.explodeman.mvc_version_console.controller.factory;

import com.explodeman.mvc_version.controller.factory.InfoFactory;
import com.explodeman.mvc_version.model.Figure;
import com.explodeman.mvc_version.model.board.Board;
import com.explodeman.mvc_version.model.board.Position;
import com.explodeman.mvc_version.model.player.*;
import com.explodeman.mvc_version.view.Printer;
import com.explodeman.mvc_version.view.info_view.InfoView;
import com.explodeman.mvc_version.view.info_view.InfoViewImp;

import java.util.ArrayList;
import java.util.List;

public class TextInfoFactory implements InfoFactory {
    private final Printer printer;
    private final Board<Figure> board;
    private final char startBoardNumerationSymbol;

    public TextInfoFactory(Printer printer, Board<Figure> board, char startBoardNumerationSymbol) {
        this.printer = printer;
        this.board = board;
        this.startBoardNumerationSymbol = startBoardNumerationSymbol;
    }

    @Override
    public InfoView winInfo(Player winPlayer) {
        String message = "GAME OVER: Winner is " + winPlayer.getFigure().getSymbol();
        return new InfoViewImp(printer, message);
    }

    @Override
    public InfoView drawInfo() {
        String message = "GAME OVER: draw";
        return new InfoViewImp(printer, message);
    }

    @Override
    public InfoView botMoveInfo(Bot bot, Position position) {
        int number = position.row * board.side() + position.column;
        char symbol = (char) (startBoardNumerationSymbol + number);
        String message = String.format("Bot %s, enter you move: %d", bot.getFigure().toString(), number);
        return new InfoViewImp(printer, message);
    }

    @Override
    public InfoView boardInfo() {
        List<String> strings = new ArrayList<>();
        strings.add("");

        String format = " %c ";
        int number = 0;
        for (int row = 0; row < board.side(); row++) {
            StringBuilder line = new StringBuilder();
            for (int column = 0; column < board.side(); column++) {
                char symbol = board.isFree(row, column) ? defaultNumerationSymbol(number, startBoardNumerationSymbol) : board.get(row, column).getSymbol();
                number++;
                line.append(String.format(format, symbol));
                if (column != board.side() - 1) {
                    line.append("|");
                }
            }
            strings.add(line.toString());
            if (row < board.side() - 1) {
                strings.add("---+---+---");
            }
        }
        strings.add("");

        return new InfoViewImp(printer, strings);
    }

    private static char defaultNumerationSymbol(int number, char startBoardNumerationSymbol) {
        return (char) (startBoardNumerationSymbol + number);
    }
}
