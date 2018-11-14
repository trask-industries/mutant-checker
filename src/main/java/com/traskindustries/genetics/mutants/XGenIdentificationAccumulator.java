package com.traskindustries.genetics.mutants;

import com.traskindustries.exception.DNAIncoherenceException;
import com.traskindustries.genetics.GenIdentificationAccumulator;

public class XGenIdentificationAccumulator implements GenIdentificationAccumulator {
    private boolean initialized = false;
    private Integer referenceGenSize = 0;
    private int sequenceMatchCount = 0;
    private int horizontalCounterX = 0;
    private int horizontalCounterY = 0;
    private int[] verticalCounter;
    private int[] nwToseCounter;
    private int[] neToswCounter;
    private int genNumber = 0;
    private boolean firstReduction = true;

    @Override
    public Boolean apply(
            final String x,
            final String y) {
        boolean isGenIdentified = false;
        if (!initialized) {
            initialize(x);
        }
        validateGenSizeIsCoherent(y);
        resetHorizontalCounter();
        final char[] bases =
                x.concat(y).toCharArray();
        final int genSize =
                (bases.length / 2);
        verticalCounter[0] =
            compareBases(bases[0], bases[genSize], verticalCounter[0]);
        for (int i = 1; i < genSize; i++) {
            verticalCounter[i] =
                compareBases(bases[i], bases[i+genSize], verticalCounter[i]);
            if (firstReduction) {
                horizontalCounterX =
                    compareBases(bases[i - 1], bases[i], horizontalCounterX);
            }
            horizontalCounterY =
                compareBases(bases[i-1+genSize], bases[i+genSize], horizontalCounterY);
            if(i < bases.length - 1) {
                int offset =
                    i - 1 - genNumber + genSize;
                nwToseCounter[offset] =
                    compareBases(bases[i-1], bases[i+genSize], nwToseCounter[offset]);
            }
            int offset =
                    i + genNumber;
            neToswCounter[offset] =
                compareBases(bases[i], bases[i-1+genSize], neToswCounter[offset]);
            isGenIdentified =
                genIdentified();
            if (isGenIdentified) {
                break;
            }
        }
        genNumber++;
        firstReduction = false;
        return isGenIdentified;
    }

    private void initialize(
            final String firstGen) {
        referenceGenSize =
                firstGen.length();
        verticalCounter =
            new int[referenceGenSize];
        nwToseCounter =
            new int[referenceGenSize * 2];
        neToswCounter =
            new int[referenceGenSize * 2];
        initialized =
            true;
    }

    private void validateGenSizeIsCoherent(
            final String gen) {
        if (!referenceGenSize
            .equals(
                gen.length())) {
            throw new DNAIncoherenceException();
        }
    }

    private boolean genIdentified() {
        return sequenceMatchCount > 1;
    }

    private void resetHorizontalCounter() {
        horizontalCounterX = 0;
        horizontalCounterY = 0;
    }

    private int compareBases(
            final char x,
            final char y,
            final int lastCounter) {
        if (x == y && x != '.') {
            int result =
                (lastCounter == 0) ? 2 : lastCounter + 1;
            if (result == 4) {
                sequenceMatchCount++;
            }
            return result;
        } else {
           return 0;
        }
    }
}
