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
	public void testFindXGenReturnsTrueHorizontalSequence() {
		String genPair="TAAAACGCTTTT";
		Accumulator accumulator = new Accumulator(6);
		assertThat(mutantService.findXGen(genPair, accumulator, 0)).isTrue();
	}

}
