package org.chinesechecker.client;

public class IllegalMove extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public IllegalMove(String msg) {
            super(msg);
    }
}

