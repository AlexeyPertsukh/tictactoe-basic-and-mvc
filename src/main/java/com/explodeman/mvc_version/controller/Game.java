package com.explodeman.mvc_version.controller;

import com.explodeman.mvc_version.controller.factory.DialogFactory;
import com.explodeman.mvc_version.controller.factory.InfoFactory;
import com.explodeman.mvc_version.model.board.Position;
import com.explodeman.mvc_version.model.board.TicTacBoard;
import com.explodeman.mvc_version.model.player.*;
import com.explodeman.mvc_version.model.rules.Rules;
import com.explodeman.mvc_version.view.dialog_view.DialogView;
import com.explodeman.mvc_version.view.info_view.InfoView;

public class Game {
    private final TicTacBoard board;
    private final Player firstPlayer;
    private final Player secondPlayer;
    private final Rules rules;

    private Player currentPlayer;
    private final InfoFactory infoFactory;
    private final DialogFactory dialogFactory;


    public Game(TicTacBoard board, Player firstPlayer, Player secondPlayer, Rules rules, InfoFactory infoFactory, DialogFactory dialogFactory) {
        this.board = board;
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        this.rules = rules;


        this.dialogFactory = dialogFactory;
        this.infoFactory = infoFactory;

        currentPlayer = firstPlayer;
    }

    public void go() {
        showBoard();

        while (true) {

            Position position = inputMove();
            if(currentPlayer instanceof  Bot) {
                Bot bot = (Bot) currentPlayer;
                InfoView botMoveInfo = infoFactory.botMoveInfo(bot, position);
                botMoveInfo.show();
            }

            board.put(position, currentPlayer.getFigure());

            showBoard();

            if (isWin()) {
                InfoView winInfo = infoFactory.winInfo(currentPlayer);
                winInfo.show();
                break;
            }
            if (isDraw()) {
                InfoView drawInfo = infoFactory.drawInfo();
                drawInfo.show();
                break;
            }

            currentPlayer = otherPlayer();
        }

    }

    private Player otherPlayer() {
        return currentPlayer == firstPlayer ? secondPlayer : firstPlayer;
    }

    private Position inputMove() {
        if(currentPlayer instanceof Bot) {
            Bot bot = (Bot) currentPlayer;
            return bot.input();
        }
        while (true) {
            DialogView<Position> dialog = dialogFactory.getDvPoint(currentPlayer);
            Position position = dialog.input();
            if(board.isFree(position)) {
                return position;
            }
        }
    }

    private boolean isWin() {
        return rules.isWin(board, currentPlayer.getFigure());
    }

    private boolean isDraw() {
        return rules.isDraw(board, currentPlayer.getFigure());
    }

    private void showBoard() {
        InfoView boardInfo = infoFactory.boardInfo();
        boardInfo.show();
    }

}
