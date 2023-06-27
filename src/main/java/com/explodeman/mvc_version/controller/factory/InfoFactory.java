package com.explodeman.mvc_version.controller.factory;

import com.explodeman.mvc_version.model.board.Position;
import com.explodeman.mvc_version.model.player.*;
import com.explodeman.mvc_version.view.info_view.InfoView;

public interface InfoFactory {
    InfoView winInfo(Player winPlayer);
    InfoView drawInfo();
    InfoView botMoveInfo(Bot bot, Position position);
    InfoView boardInfo();
}
