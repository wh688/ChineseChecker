package hw1;

import java.io.Serializable;

public enum Gamer implements Serializable {

        W, 
        B,
        I;

        public static final Gamer EMPTY = null;
        public static final Gamer _ = EMPTY;
        public static final Gamer WHITE = W;
        public static final Gamer BLACK = B;
        public static final Gamer INVALID = I;
        public static final Gamer O = WHITE;
        public static final Gamer X = BLACK;

        public boolean isWhite() {
                return this == WHITE;
        }

        public boolean isBlack() {
                return this == BLACK;
        }
        
        public boolean isInvalid() {
            return this == INVALID;
        }
        
        public Gamer getOpponent() {
                return this == WHITE ? BLACK : WHITE;
        }


}

