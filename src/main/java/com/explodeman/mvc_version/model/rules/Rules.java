package com.explodeman.mvc_version.model.rules;
import com.explodeman.mvc_version.model.board.Board;
public class Rules {
    private static final int[] WIN_TEMPLATES = new int[]{
            0b000_000_000_111,
            0b000_000_111_000,
            0b000_111_000_000,

            0b000_001_001_001,
            0b000_010_010_010,
            0b000_100_100_100,

            0b000_100_010_001,
            0b000_001_010_100,
    };

    public int[] winTemplates() {
        return WIN_TEMPLATES;
    }

    public boolean isWin(Board board, Object value) {
        int[] winTemplates = getWinTemplates();
        int template = boardToInt(board, value);
        for (int winTemplate : winTemplates) {
            if ((template & winTemplate) == winTemplate) {
                return true;
            }
        }
        return false;
    }

    public boolean isDraw(Board board, Object value) {
        if (isWin(board, value)) {
            return false;
        }
        int template = boardToInt(board, board.emptyValue());
        return template == 0;
    }

    private int[] getWinTemplates() {
        return WIN_TEMPLATES;
    }

    private int boardToInt(Board board, Object value) {
        int out = 0;
        int mask = 1;
        for (int row = 0; row < board.side(); row++) {
            for (int column = 0; column < board.side(); column++) {
                if (board.get(row, column) == value) {
                    out |= mask;
                }
                mask = mask << 1;
            }
        }
        return out;
    }

}
