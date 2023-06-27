package com.explodeman.mvc_version_terminal.controller.factory;

import com.explodeman.mvc_version.controller.factory.DialogFactory;
import com.explodeman.mvc_version.model.board.Position;
import com.explodeman.mvc_version.model.player.Player;
import com.explodeman.mvc_version.view.Printer;
import com.explodeman.mvc_version.view.Reader;
import com.explodeman.mvc_version.view.dialog_view.DialogView;
import com.explodeman.mvc_version_terminal.model.Storage;
import com.explodeman.mvc_version_terminal.view.term_dialog_view.TermDialogViewPosition;
import com.explodeman.tic_tac_terminal.ProtocolToTerminal;

public class TermDialogFactory implements DialogFactory {
    private final Printer printer;
    private final Reader reader;
    private final Player playerX;
    private final Player player0;
    private final Storage<Long> storage;

    public TermDialogFactory(Printer printer, Reader reader, Player playerX, Player player0, Storage<Long> storage) {
        this.printer = printer;
        this.reader = reader;
        this.playerX = playerX;
        this.player0 = player0;
        this.storage = storage;
    }

    @Override
    public DialogView<Position> getDvPoint(Player player) {
        storage.and(ProtocolToTerminal.MASK_MOVES);

        if(player == playerX) {
            storage.or(ProtocolToTerminal.needMoveX());
        } else if(player == player0){
            storage.or(ProtocolToTerminal.needMove0());
        } else {
            throw new IllegalArgumentException("illegal player: " + player);
        }

        return new TermDialogViewPosition(printer, reader, storage.get());
    }
}
