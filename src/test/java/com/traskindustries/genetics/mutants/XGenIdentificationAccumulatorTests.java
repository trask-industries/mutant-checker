package com.traskindustries.genetics.mutants;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class XGenIdentificationAccumulatorTests {

    @Test
    public void testFindXGenReturnsTrueWhenVerticalSequenceFound() {
        final String[] dna = {
                //               012345
                ".T...T",
                ".T...T",
                ".T...T",
                ".T...T"};
        testFindXGen(dna, true);
    }

    private void testFindXGen(String[] dna, boolean expectedResult) {
        final XGenIdentificationAccumulator accumulator =
                new XGenIdentificationAccumulator();
        accumulator
            .apply(dna[0], dna[1]);
        accumulator
            .apply(dna[1] , dna[2]);
        boolean result =
                accumulator
                .apply(dna[2] , dna[3]);
        assertThat(result).isEqualTo(expectedResult);
    }

}
