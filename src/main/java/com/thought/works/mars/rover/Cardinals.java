package com.thought.works.mars.rover;

import java.util.Arrays;
import java.util.List;

public enum Cardinals {
    N,
    S,
    E,
    O;


    public static Cardinals convertCharToCardinal(char c) {
        switch (c) {
            case 'N':
                return Cardinals.N;
            case 'S':
                return Cardinals.S;
            case 'E':
                return Cardinals.E;
            case 'O':
                return Cardinals.O;
            default:
                return null;
        }
    }

    public static Cardinals getRotatedCardinal(Cardinals cardinal, boolean left) {
        List<Cardinals> cardinals = Arrays.asList(N, E, S, O);
        if (left) {
            return cardinal.equals(N) ? O : cardinals.get(cardinals.indexOf(cardinal) - 1);
        }
        return cardinal.equals(O) ? N : cardinals.get(cardinals.indexOf(cardinal) + 1);
    }

}
