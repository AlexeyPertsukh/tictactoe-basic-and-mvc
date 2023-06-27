package com.explodeman.mvc_version_console.controller;


import com.explodeman.mvc_version.controller.Game;
import com.explodeman.mvc_version.controller.factory.DialogFactory;
import com.explodeman.mvc_version.controller.factory.InfoFactory;
import com.explodeman.mvc_version.model.Figure;
import com.explodeman.mvc_version.model.board.TicTacBoard;
import com.explodeman.mvc_version.model.player.Player;
import com.explodeman.mvc_version.model.rules.Rules;
import com.explodeman.mvc_version.view.Printer;
import com.explodeman.mvc_version.view.Reader;
import com.explodeman.mvc_version_console.controller.factory.TextDialogFactory;
import com.explodeman.mvc_version_console.controller.factory.TextInfoFactory;
import com.explodeman.mvc_version_console.view.ConsolePrinter;
import com.explodeman.mvc_version_console.view.KeyboardReader;

public class MainNumericBoard {
    public static void main(String[] args) {

        Printer printer = new ConsolePrinter();
        Reader reader = new KeyboardReader();
        TicTacBoard board = new TicTacBoard(Figure.NULL);
        Player playerX = new Player(Figure.X);
        Player player0 = new Player(Figure.ZERO);

        Rules rules = new Rules();

        char startBoardNumerationSymbol = '1';

        InfoFactory infoFactory = new TextInfoFactory(printer, board, startBoardNumerationSymbol);
        DialogFactory dialogFactory = new TextDialogFactory(printer, reader, board, startBoardNumerationSymbol);

        Game game = new Game(board, playerX, player0, rules, infoFactory, dialogFactory);
        game.go();
    }
}
