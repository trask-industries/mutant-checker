package com.traskindustries.genetics.mutants;

import com.traskindustries.service.GeneticAccumulator;
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
        testFindXGen(dna, true);
    }

    @Test
    public void testFindXGenReturnsFalseWhenNoHorizontalSequenceFound() {
        final String[] dna = {
        //       012345
                "..TTTT",
                "......",
                ".TTT..",
                "......"};
        testFindXGen(dna, false);
    }

    @Test
    public void testFindXGenReturnsTrueWhenVerticalSequenceFound() {
        final String[] dna = {
        //       012345
                ".T...T",
                ".T...T",
                ".T...T",
                ".T...T"};
        testFindXGen(dna, true);
    }

    @Test
    public void testFindXGenReturnsFalseWhenNoVerticalSequenceFound() {
        final String[] dna = {
        //       012345
                ".T...T",
                ".....T",
                ".T...T",
                ".T...T"};
        testFindXGen(dna, false);
    }

    @Test
    public void testFindXGenReturnsTrueWhenVerticalAndHorizontalSequenceFound() {
        final String[] dna = {
        //       012345
                ".T...T",
                "..TTTT",
                ".T...T",
                ".T...T"};
        testFindXGen(dna, true);
    }

    @Test
    public void testFindXGenReturnsTrueWhenDiagonal_NW_SE_SequenceFound() {
        final String[] dna = {
        //       012345
                ".A....",
                "..A...",
                "...A..",
                "....A."};
        testFindXGen(dna, true);
    }

    @Test
    public void testFindXGenReturnsTrueWhenDiagonal_NE_SW_SequenceFound() {
        final String[] dna = {
        //       012345
                "....A.",
                "...A..",
                "..A...",
                ".A...."};
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
