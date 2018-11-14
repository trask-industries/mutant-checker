package com.traskindustries.genetics;

import com.traskindustries.genetics.mutants.XGenIdentificationAccumulator;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DNAStreamTest {

    @Test
    public void testFindXGenReturnsTrueWhenHorizontalSequenceFound() {
        final String[] dna = {
                //       012345
                "......",
                "TTTT..",
                ".TTTT.",
                "......"};
        testDNAStreamWithXGenIdentificationAccumulator(dna, true);
    }

    @Test
    public void testFindXGenReturnsFalseWhenNoHorizontalSequenceFound() {
        final String[] dna = {
                //       012345
                "..TTTT",
                "......",
                ".TTT..",
                "......"};
        testDNAStreamWithXGenIdentificationAccumulator(dna, false);
    }

    @Test
    public void testFindXGenReturnsTrueWhenVerticalSequenceFound() {
        final String[] dna = {
                //       012345
                ".T...T",
                ".T...T",
                ".T...T",
                ".T...T"};
        testDNAStreamWithXGenIdentificationAccumulator(dna, true);
    }

    @Test
    public void testFindXGenReturnsFalseWhenNoVerticalSequenceFound() {
        final String[] dna = {
                //       012345
                ".T...T",
                ".....T",
                ".T...T",
                ".T...T"};
        testDNAStreamWithXGenIdentificationAccumulator(dna, false);
    }

    @Test
    public void testFindXGenReturnsTrueWhenVerticalAndHorizontalSequenceFound() {
        final String[] dna = {
                //       012345
                ".T...T",
                "..TTTT",
                ".T...T",
                ".T...T"};
        testDNAStreamWithXGenIdentificationAccumulator(dna, true);
    }

    @Test
    public void testFindXGenReturnsTrueWhenDiagonal_NW_SE_SequenceFound() {
        final String[] dna = {
                //       012345
                ".AT...",
                "..AT..",
                "...AT.",
                "....AT"};
        testDNAStreamWithXGenIdentificationAccumulator(dna, true);
    }

    @Test
    public void testFindXGenReturnsTrueWhenDiagonal_NE_SW_SequenceFound() {
        final String[] dna = {
                //       012345
                "....AT",
                "...AT.",
                "..AT..",
                ".AT..."};
        testDNAStreamWithXGenIdentificationAccumulator(dna, true);
    }

    @Test
    public void testFindXGenReturnsTrueWhenCombinedSequenceFound() {
        final String[] dna = {
                //       012345
                "A....T",
                "A...T.",
                "A..T..",
                "A.TTTT"};
        testDNAStreamWithXGenIdentificationAccumulator(dna, true);
    }

    private void testDNAStreamWithXGenIdentificationAccumulator(String[] dna, boolean expectedResult) {
        final DNAStream dnaStream =
                DNAStream.fromStringArray(dna);
        final boolean result =
                dnaStream
                .containsGen(new XGenIdentificationAccumulator());
        assertThat(result).isEqualTo(expectedResult);
    }

}
