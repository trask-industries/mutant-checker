package com.traskindustries.genetics.mutants;

import com.traskindustries.exception.DNAIncoherenceException;
import com.traskindustries.genetics.GenIdentificationAccumulator;

public class XGenIdentificationAccumulator implements GenIdentificationAccumulator {
    private boolean initialized = false;
    private Integer referenceGenSize = 0;
    private int sequenceMatchCount = 0;
    private int horizontalCounter = 0;
    private int[] verticalCounter;
    private int[] nwToseCounter;
    private int[] neToswCounter;

    @Override
    public Boolean apply(
            final String x,
            final String y) {
        if (!initialized) {
            initialize(x);
        }
        validateGenSizeIsCoherent(y);
        return genIdentified();
    }

    public void initialize(
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

    public boolean genIdentified() {
        return sequenceMatchCount > 1;
    }
}
