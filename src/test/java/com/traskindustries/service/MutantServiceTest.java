package com.traskindustries.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;



public class MutantServiceTest {
	
	private static MutantService mutantService = new MutantService();

	@Test
	public void testFindXGenReturnsTrueWhenHorizontalSequenceFoundInImpairMatrix() {
		final String[] dna = {
				//       01234
				".....",
				".....",
				"TTTT.",
				".TTTT",
				"....."};
		testService(dna, true);
	}


	@Test
	public void testFindXGenReturnsTrueWhenHorizontalSequenceFound() {
		final String[] dna = {
				//       012345
				"......",
				"......",
				"......",
				"TTTT..",
				".TTTT.",
				"......"};
		testService(dna, true);
	}

	@Test
	public void testFindXGenReturnsFalseWhenNoHorizontalSequenceFound() {
		final String[] dna = {
				//       012345
				"......",
				"......",
				"..TTTT",
				"......",
				".TTT..",
				"......"};
		testService(dna, false);
	}

	@Test
	public void testFindXGenReturnsTrueWhenVerticalSequenceFound() {
		final String[] dna = {
				"......",
				"......",
				//       012345
				".T...T",
				".T...T",
				".T...T",
				".T...T"};
		testService(dna, true);
	}

	@Test
	public void testFindXGenReturnsFalseWhenNoVerticalSequenceFound() {
		final String[] dna = {
				//       012345
				"......",
				"......",
				".T...T",
				".....T",
				".T...T",
				".T...T"};
		testService(dna, false);
	}

	@Test
	public void testFindXGenReturnsTrueWhenVerticalAndHorizontalSequenceFound() {
		final String[] dna = {
				//       012345
				"......",
				"......",
				".T...T",
				"..TTTT",
				".T...T",
				".T...T"};
		testService(dna, true);
	}

	@Test
	public void testFindXGenReturnsTrueWhenDiagonal_NW_SE_SequenceFound() {
		final String[] dna = {
				//       012345
				"......",
				"......",
				".AT...",
				"..AT..",
				"...AT.",
				"....AT"};
		testService(dna, true);
	}

	@Test
	public void testFindXGenReturnsTrueWhenDiagonal_NE_SW_SequenceFound() {
		final String[] dna = {
				//       012345
				"......",
				"......",
				"....AT",
				"...AT.",
				"..AT..",
				".AT..."};
		testService(dna, true);
	}

	@Test
	public void testFindXGenReturnsTrueWhenCombinedSequenceFound() {
		final String[] dna = {
				//       012345
				"......",
				"......",
				"A....T",
				"A...T.",
				"A..T..",
				"A.TTTT"};
		testService(dna, true);
	}

	private void testService(String[] dna, boolean expectedResult) {
		assertThat(
				mutantService
				.isMutant(dna))
		.isEqualTo(expectedResult);
	}
}