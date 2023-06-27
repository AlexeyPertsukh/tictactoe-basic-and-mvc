package com.explodeman.mvc_version.controller.factory;

import com.explodeman.mvc_version.model.board.Position;
import com.explodeman.mvc_version.model.player.Player;
import com.explodeman.mvc_version.view.dialog_view.DialogView;

public interface DialogFactory {
    DialogView<Position> getDvPoint(Player player);
}
