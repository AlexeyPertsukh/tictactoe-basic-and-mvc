package com.explodeman.mvc_version_terminal.controller.factory;

import com.explodeman.mvc_version.controller.factory.InfoFactory;
import com.explodeman.mvc_version.model.Figure;
import com.explodeman.mvc_version.model.board.Board;
import com.explodeman.mvc_version.model.board.Position;
import com.explodeman.mvc_version.model.player.*;
import com.explodeman.mvc_version.view.Printer;
import com.explodeman.mvc_version.view.info_view.InfoView;
import com.explodeman.mvc_version.view.info_view.InfoViewImp;
import com.explodeman.mvc_version_terminal.model.Storage;
import com.explodeman.tic_tac_terminal.ProtocolToTerminal;

public class TermInfoFactory implements InfoFactory {

    private final Printer printer;
    private final Board<Figure> board;
    private final Player playerX;
    private final Player player0;
    private final Storage<Long> storage;

    public TermInfoFactory(Printer printer, Board<Figure> board, Player playerX, Player player0, Storage<Long> storage) {
        this.printer = printer;
        this.board = board;
        this.playerX = playerX;
        this.player0 = player0;
        this.storage = storage;
    }

    @Override
    public InfoView winInfo(Player winPlayer) {
        storage.and(ProtocolToTerminal.MASK_MOVES);
        long winBits = winPlayer == playerX ? ProtocolToTerminal.winX() : ProtocolToTerminal.win0();
        storage.or(winBits);

        return new InfoViewImp(printer, String.valueOf(storage.get()));
    }

    @Override
    public InfoView drawInfo() {

        storage.and(ProtocolToTerminal.MASK_MOVES);
        storage.or(ProtocolToTerminal.draw());

        return new InfoViewImp(printer, String.valueOf(storage.get()));
    }

    @Override
    public InfoView botMoveInfo(Bot bot, Position position) {
        return new InfoView() {
            @Override
            public void show() {
                //none
            }
        };
    }

    @Override
    public InfoView boardInfo() {
        long moves = 0L;
        for (int row = 0; row < board.side(); row++) {
            for (int column = 0; column < board.side(); column++) {
                Figure figure = board.get(row, column);
                if(figure == playerX.getFigure()) {
                    moves |= ProtocolToTerminal.moveX(row * board.side() + column);
                } else if(figure == player0.getFigure()) {
                    moves |= ProtocolToTerminal.move0(row * board.side() + column);
                }
            }
        }

        storage.and(ProtocolToTerminal.MASK_OTHER);
        storage.or(moves);

        return new InfoViewImp(printer, String.valueOf(storage.get()));
    }
}
