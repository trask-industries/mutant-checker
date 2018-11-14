package com.traskindustries.genetics.mutants;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class XGenIdentificationAccumulatorTests {

    @Test
    public void testFindXGenReturnsTrueWhenHorizontalSequenceFound() {
        final String[] dna = {
        //       012345
                "......",
                "TTTT..",
                ".TTTT.",
                "......"};
        testAccumulator(dna, true);
    }

    @Test
    public void testFindXGenReturnsFalseWhenNoHorizontalSequenceFound() {
        final String[] dna = {
        //       012345
                "..TTTT",
                "......",
                ".TTT..",
                "......"};
        testAccumulator(dna, false);
    }

    @Test
    public void testFindXGenReturnsTrueWhenVerticalSequenceFound() {
        final String[] dna = {
        //       012345
                ".T...T",
                ".T...T",
                ".T...T",
                ".T...T"};
        testAccumulator(dna, true);
    }

    @Test
    public void testFindXGenReturnsFalseWhenNoVerticalSequenceFound() {
        final String[] dna = {
        //       012345
                ".T...T",
                ".....T",
                ".T...T",
                ".T...T"};
        testAccumulator(dna, false);
    }

    @Test
    public void testFindXGenReturnsTrueWhenVerticalAndHorizontalSequenceFound() {
        final String[] dna = {
        //       012345
                ".T...T",
                "..TTTT",
                ".T...T",
                ".T...T"};
        testAccumulator(dna, true);
    }

    @Test
    public void testFindXGenReturnsTrueWhenDiagonal_NW_SE_SequenceFound() {
        final String[] dna = {
        //       012345
                ".AT...",
                "..AT..",
                "...AT.",
                "....AT"};
        testAccumulator(dna, true);
    }

    @Test
    public void testFindXGenReturnsTrueWhenDiagonal_NE_SW_SequenceFound() {
        final String[] dna = {
        //       012345
                "....AT",
                "...AT.",
                "..AT..",
                ".AT..."};
        testAccumulator(dna, true);
    }

    @Test
    public void testFindXGenReturnsTrueWhenCombinedSequenceFound() {
        final String[] dna = {
        //       012345
                "A....T",
                "A...T.",
                "A..T..",
                "A.TTTT"};
        testAccumulator(dna, true);
    }

    private void testAccumulator(String[] dna, boolean expectedResult) {
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
