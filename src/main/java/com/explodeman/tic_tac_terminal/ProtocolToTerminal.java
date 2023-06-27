package com.explodeman.tic_tac_terminal;

public class ProtocolToTerminal {
    public final static long MASK_MOVES = 0b00000000_00000011_11111111_11111111;
    public final static long MASK_OTHER = 0b00011111_00000000_00000000_00000000;
    private final static long MASK_MOVE_X = 1L;
    private final static long MASK_MOVE_0 = 0b10_00000000;

    private final static long MASK_NEED_MOVE_X = 0b0001_00000000_00000000_00000000;
    private final static long MASK_NEED_MOVE_0 = 0b0010_00000000_00000000_00000000;
    private final static long MASK_WIN_X = 0b0_00000100_00000000_00000000_00000000;
    private final static long MASK_WIN_0 = 0b0_00001000_00000000_00000000_00000000;
    private final static long MASK_DRAW = 0b0_00010000_00000000_00000000_00000000;


    private ProtocolToTerminal() {
    }

    public static boolean isMoveX(int num, long code) {
        long mask = MASK_MOVE_X << (num);
        return (code & mask) != 0;
    }

    public static boolean isMove0(int num, long code) {
        long mask = MASK_MOVE_0 << (num);
        return (code & mask) != 0;
    }

    public static boolean isNeedMoveX(long code) {
        return (code & MASK_NEED_MOVE_X) != 0;
    }

    public static boolean isNeedMove0(long code) {
        return (code & MASK_NEED_MOVE_0) != 0;
    }

    public static boolean isWinX(long code) {
        return (code & MASK_WIN_X) != 0;
    }

    public static boolean isWin0(long code) {
        return (code & MASK_WIN_0) != 0;
    }

    public static boolean isDraw(long code) {
        return (code & MASK_DRAW) != 0;
    }

    public static long moveX(int num) {
        return MASK_MOVE_X << (num);
    }

    public static long move0(int num) {
        return MASK_MOVE_0 << (num);
    }

    public static long needMoveX() {
        return MASK_NEED_MOVE_X;
    }

    public static long needMove0() {
        return MASK_NEED_MOVE_0;
    }

    public static long winX() {
        return MASK_WIN_X;
    }

    public static long win0() {
        return MASK_WIN_0;
    }
    public static long draw() {
        return MASK_DRAW;
    }

}
