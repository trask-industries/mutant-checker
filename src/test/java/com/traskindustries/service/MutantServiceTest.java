package com.traskindustries.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.traskindustries.service.MutantService.Accumulator;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MutantServiceTest {
	
	@Autowired
	MutantService mutantService;
	
	@Test
	public void testFindXGenReturnsTrueWhenHorizontalSequenceFound() {
		Accumulator accumulator = new Accumulator(6);
		String genPair="TAAAACGCTTTT";
		assertThat(mutantService.findXGen(genPair, accumulator, 0, true)).isTrue();
	}

	@Test
	public void testFindXGenReturnsFalseWhenNoHorizontalSequenceFound() {
		Accumulator accumulator = new Accumulator(6);
		String genPair="TAAATCGCTTTT";
		assertThat(mutantService.findXGen(genPair, accumulator, 0, true)).isFalse();
	}
	
	@Test
	public void testFindXGenReturnsTrueWhenVerticalSequenceFound() {
		Accumulator accumulator = new Accumulator(6);
		//               012345012345
		String genPair1="ATGCAGCTACCT";
		mutantService.findXGen(genPair1, accumulator, 0, false);
		String genPair2="CTACCTATGCAG";
		mutantService.findXGen(genPair2, accumulator, 0, false);
		String genPair3="ATGCAGCTACCT";
		assertThat(mutantService.findXGen(genPair3, accumulator, 0, true)).isTrue();
	}

	@Test
	public void testFindXGenReturnsFalseWhenNoVerticalSequenceFound() {
		Accumulator accumulator = new Accumulator(6);
		//               012345012345
		String genPair1="ATGCAGCTACCT";
		mutantService.findXGen(genPair1, accumulator, 0, false);
		String genPair2="CTACCTATGCAG";
		mutantService.findXGen(genPair2, accumulator, 0, false);
		String genPair3="ATGCAGCCACCT";
		assertThat(mutantService.findXGen(genPair3, accumulator, 0, true)).isFalse();
	}
	
	@Test
	public void testFindXGenReturnsTrueVerticalAndHorizontalSequenceFound() {
		Accumulator accumulator = new Accumulator(6);
		//               012345012345
		String genPair1="ATGCAGCTACCT";
		mutantService.findXGen(genPair1, accumulator, 0, false);
		String genPair2="TTTTCTATGTAG";
		mutantService.findXGen(genPair2, accumulator, 0, false);
		String genPair3="ATGCAGCTACCT";
		assertThat(mutantService.findXGen(genPair3, accumulator, 0, true)).isTrue();
	}

}
