package hw1;

import static hw1.Gamer._;
import static hw1.Gamer.I;
import static hw1.Gamer.B;
import static hw1.Gamer.W;


public class CheckerBoard {
	public static final int ROWS = 17;
    public static final int COLS = 17;
    public static final Gamer[][] INIT_BOARD = {
       // 0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15 16 
        { I, I, I, I, B, I, I, I, I, I, I, I, I, I, I, I, I }, // 0
        { I, I, I, I, B, B, I, I, I, I, I, I, I, I, I, I, I }, // 1
        { I, I, I, I, B, B, B, I, I, I, I, I, I, I, I, I, I }, // 2
        { I, I, I, I, B, B, B, B, I, I, I, I, I, I, I, I, I }, // 3
        { _, _, _, _, _, _, _, _, _, _, _, _, _, I, I, I, I }, // 4
        { I, _, _, _, _, _, _, _, _, _, _, _, _, I, I, I, I }, // 5
        { I, I, _, _, _, _, _, _, _, _, _, _, _, I, I, I, I }, // 6
        { I, I, I, _, _, _, _, _, _, _, _, _, _, I, I, I, I }, // 7
        { I, I, I, I, _, _, _, _, _, _, _, _, _, I, I, I, I }, // 8
        { I, I, I, I, _, _, _, _, _, _, _, _, _, _, I, I, I }, // 9
        { I, I, I, I, _, _, _, _, _, _, _, _, _, _, _, I, I }, // 10
        { I, I, I, I, _, _, _, _, _, _, _, _, _, _, _, _, I }, // 11
        { I, I, I, I, _, _, _, _, _, _, _, _, _, _, _, _, _ }, // 12
        { I, I, I, I, I, I, I, I, I, W, W, W, W, I, I, I, I }, // 13
        { I, I, I, I, I, I, I, I, I, I, W, W, W, I, I, I, I }, // 14
        { I, I, I, I, I, I, I, I, I, I, I, W, W, I, I, I, I }, // 15
        { I, I, I, I, I, I, I, I, I, I, I, I, W, I, I, I, I }, // 16

    };
    static boolean inBoard(Position pos) {
        return pos.row <= ROWS - 1 && pos.row >= 0
        		&& pos.col <= COLS - 1 && pos.col >= 0;
    }
}
