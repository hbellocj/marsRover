package com.thought.works.mars.rover;

import java.util.Arrays;

public enum Cardinals {
    N,
    S,
    E,
    O;

    public boolean isInCardinalsEnum(String c) {
        return c.equals(Arrays.toString(Cardinals.values()));
    }


}
