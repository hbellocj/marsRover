package com.thought.works.mars.rover;

import java.util.Arrays;
import java.util.List;

public enum Cardinals {
    N,
    S,
    E,
    W;


    public static Cardinals convertCharToCardinal(char c) {
        switch (c) {
            case 'N':
                return Cardinals.N;
            case 'S':
                return Cardinals.S;
            case 'E':
                return Cardinals.E;
            case 'W':
                return Cardinals.W;
            default:
                return null;
        }
    }

    public static Cardinals getRotatedCardinal(Cardinals cardinal, boolean left) {
        List<Cardinals> cardinals = Arrays.asList(N, E, S, W);
        if (left) {
            return cardinal.equals(N) ? W : cardinals.get(cardinals.indexOf(cardinal) - 1);
        }
        return cardinal.equals(W) ? N : cardinals.get(cardinals.indexOf(cardinal) + 1);
    }

}
