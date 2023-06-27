package com.explodeman.mvc_version_terminal.controller;


import com.explodeman.file_rw.BasicFileReader;
import com.explodeman.file_rw.BasicFileWriter;
import com.explodeman.file_rw.MyFileReader;
import com.explodeman.file_rw.MyFileWriter;
import com.explodeman.mvc_version.controller.Game;
import com.explodeman.mvc_version.controller.factory.DialogFactory;
import com.explodeman.mvc_version.controller.factory.InfoFactory;
import com.explodeman.mvc_version.model.Figure;
import com.explodeman.mvc_version.model.board.TicTacBoard;
import com.explodeman.mvc_version.model.player.Player;
import com.explodeman.mvc_version.model.rules.Rules;
import com.explodeman.mvc_version.view.Printer;
import com.explodeman.mvc_version.view.Reader;
import com.explodeman.mvc_version_terminal.controller.factory.TermDialogFactory;
import com.explodeman.mvc_version_terminal.controller.factory.TermInfoFactory;
import com.explodeman.mvc_version_terminal.model.Storage;
import com.explodeman.mvc_version_terminal.model.StorageLong;
import com.explodeman.mvc_version_terminal.view.TermPrinter;
import com.explodeman.mvc_version_terminal.view.TermReader;

public class MainTerm {
    public static void main(String[] args) {
        MyFileReader fileReader = new BasicFileReader(System.getProperty("user.dir") + "\\src\\main\\java\\com\\explodeman\\tic_tac_terminal\\from_terminal.txt");
        MyFileWriter fileWriter = new BasicFileWriter(System.getProperty("user.dir") + "\\src\\main\\java\\com\\explodeman\\tic_tac_terminal\\to_terminal.txt");

        Printer printer = new TermPrinter(fileWriter);
        Reader reader = new TermReader(fileReader);

        TicTacBoard board = new TicTacBoard(Figure.NULL);
        Player playerX = new Player(Figure.X);
        Player player0 = new Player(Figure.ZERO);

        Rules rules = new Rules();

        Storage<Long> storage = new StorageLong();

        InfoFactory infoFactory = new TermInfoFactory(printer, board, playerX, player0, storage);
        DialogFactory dialogFactory = new TermDialogFactory(printer, reader, playerX, player0, storage);

        Game game = new Game(board, playerX, player0, rules, infoFactory, dialogFactory);
        game.go();
    }
}
