package com.traskindustries.service;


public class GeneticAccumulator {
    private int genMatch = 0;
    private int horizontalCounter = 0;
    private int[] verticalCounter;
    private int[] nwToseCounter;
    private int[] neToswCounter;
    public GeneticAccumulator initialize(final int genSize) {
        verticalCounter =
                new int[genSize];
        nwToseCounter =
                new int[genSize * 2];
        neToswCounter =
                new int[genSize * 2];
        return this;
    }
    public GeneticAccumulator compareHorizontal(final char x,
                                                       final char y) {
        if (x == y && x != '.') {
            horizontalCounter =
                    (horizontalCounter == 0) ? 2
                            : horizontalCounter + 1;
            if (horizontalCounter == 4) {
                genMatch++;
            }
        } else {
            horizontalCounter = 0;
        }
        return this;
    }
    public GeneticAccumulator compareVertical(final char x,
                                                     final char y,
                                                     int position) {
        if (x == y && x != '.') {
            verticalCounter[position] =
                    (verticalCounter[position] == 0) ? 2
                            : verticalCounter[position] + 1;
            if (verticalCounter[position] == 4) {
                genMatch++;
            }
        } else {
            verticalCounter[position] = 0;
        }
        return this;
    }
    public GeneticAccumulator compareNwToSe(final char x,
                                                   final char y,
                                                   int position) {
        if (x == y && x != '.') {
            nwToseCounter[position] =
                    (nwToseCounter[position] == 0) ? 2
                            : nwToseCounter[position] + 1;
            if (nwToseCounter[position] == 4) {
                genMatch++;
            }
        } else {
            nwToseCounter[position] = 0;
        }
        return this;
    }
    public GeneticAccumulator compareNeToSw(final char x,
                                                   final char y,
                                                   int position) {
        if (x == y && x != '.') {
            neToswCounter[position] =
                    (neToswCounter[position] == 0) ? 2
                            : neToswCounter[position] + 1;
            if (neToswCounter[position] == 4) {
                genMatch++;
            }
        } else {
            neToswCounter[position] = 0;
        }
        return this;
    }
    public GeneticAccumulator resetHorizontalCounter() {
        horizontalCounter=0;
        return this;
    }
    public boolean verifyCounters() {
        return genMatch > 1;
    }
}
