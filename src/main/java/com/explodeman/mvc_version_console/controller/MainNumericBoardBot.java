package com.explodeman.mvc_version_console.controller;

import com.explodeman.mvc_version.controller.Game;
import com.explodeman.mvc_version.controller.factory.DialogFactory;
import com.explodeman.mvc_version.controller.factory.InfoFactory;
import com.explodeman.mvc_version.model.Figure;
import com.explodeman.mvc_version.model.ai.Ai;
import com.explodeman.mvc_version.model.ai.BasicAi;
import com.explodeman.mvc_version.model.board.TicTacBoard;
import com.explodeman.mvc_version.model.player.*;
import com.explodeman.mvc_version.model.rules.Rules;
import com.explodeman.mvc_version.view.Printer;
import com.explodeman.mvc_version.view.Reader;
import com.explodeman.mvc_version_console.controller.factory.TextDialogFactory;
import com.explodeman.mvc_version_console.controller.factory.TextInfoFactory;
import com.explodeman.mvc_version_console.view.ConsolePrinter;
import com.explodeman.mvc_version_console.view.KeyboardReader;

public class MainNumericBoardBot {
    public static void main(String[] args) {

        Printer printer = new ConsolePrinter();
        Reader reader = new KeyboardReader();
        TicTacBoard board = new TicTacBoard(Figure.NULL);
        Player playerX = new Player(Figure.X);
        Ai ai = new BasicAi(board);
        Player player0 = new Bot(Figure.ZERO, ai);

        Rules rules = new Rules();

        char startBoardNumerationSymbol = '1';

        InfoFactory infoFactory = new TextInfoFactory(printer, board, startBoardNumerationSymbol);
        DialogFactory dialogFactory = new TextDialogFactory(printer, reader, board, startBoardNumerationSymbol);

        Game game = new Game(board, playerX, player0, rules, infoFactory, dialogFactory);
        game.go();
    }
}
