package com.traskindustries.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.traskindustries.service.MutantService.Accumulator;


public class MutantServiceTest {
	
	private static MutantService mutantService = new MutantService();
	
	@Test
	public void testFindXGenReturnsTrueWhenHorizontalSequenceFound() {
		final Accumulator accumulator = new Accumulator(6);
		final String[] dna = {
		//               012345
						".TTTT.",
						"..TTTT"};
		final String genPair = dna[0] + dna[1];
		assertThat(mutantService.findXGen(genPair, accumulator, 0, true)).isTrue();
	}

	@Test
	public void testFindXGenReturnsFalseWhenNoHorizontalSequenceFound() {
		final Accumulator accumulator = new Accumulator(6);
		final String[] dna = {
		//               012345
						".TTT..",
						"..TTTT"};
		final String genPair = dna[0] + dna[1];
		assertThat(mutantService.findXGen(genPair, accumulator, 0, true)).isFalse();
	}
	
	@Test
	public void testFindXGenReturnsTrueWhenVerticalSequenceFound() {
		final Accumulator accumulator = new Accumulator(6);
		final String[] dna = {
		//               012345
						".T...T",
						".T...T",
						".T...T",
						".T...T"};
		final String genPair1 = dna[0] + dna[1];
		mutantService.findXGen(genPair1, accumulator, 0, false);
		final String genPair2 = dna[1] + dna[2];
		mutantService.findXGen(genPair2, accumulator, 1, false);
		final String genPair3 = dna[2] + dna[3];
		assertThat(mutantService.findXGen(genPair3, accumulator, 2, true)).isTrue();
	}

	@Test
	public void testFindXGenReturnsFalseWhenNoVerticalSequenceFound() {
		final Accumulator accumulator = new Accumulator(6);
		final String[] dna = {
		//               012345
						".T...T",
						".....T",
						".T...T",
						".T...T"};
		final String genPair1 = dna[0] + dna[1];
		mutantService.findXGen(genPair1, accumulator, 0, false);
		final String genPair2 = dna[1] + dna[2];
		mutantService.findXGen(genPair2, accumulator, 1, false);
		final String genPair3 = dna[2] + dna[3];
		assertThat(mutantService.findXGen(genPair3, accumulator, 2, true)).isFalse();
	}
	
	@Test
	public void testFindXGenReturnsTrueWhenVerticalAndHorizontalSequenceFound() {
		Accumulator accumulator = new Accumulator(6);
		//               012345012345
		String genPair1="ATGCAGCTACCT";
		mutantService.findXGen(genPair1, accumulator, 0, false);
		String genPair2="TTTTCTATGTAG";
		mutantService.findXGen(genPair2, accumulator, 1, false);
		String genPair3="ATGCAGCTACCT";
		assertThat(mutantService.findXGen(genPair3, accumulator, 2, true)).isTrue();
	}

	@Test
	public void testFindXGenReturnsTrueWhenDiagonal_NW_SW_SequenceFound() {
		final Accumulator accumulator = new Accumulator(6);
		final String[] dna = {
		//               012345
						"TATTTT",
				        "TTATTT",
		                "TTTATT",
						"TTTTAT"};
		final String genPair1 = dna[0] + dna[1];
		mutantService.findXGen(genPair1, accumulator, 0, false);
		final String genPair2 = dna[1] + dna[2];
		mutantService.findXGen(genPair2, accumulator, 1, false);
		final String genPair3 = dna[2] + dna[3];
		assertThat(mutantService.findXGen(genPair3, accumulator, 2, true)).isTrue();
	}

}
