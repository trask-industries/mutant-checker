package com.traskindustries.service;

import java.util.Objects;

import com.traskindustries.genetics.mutants.XGenIdentificationAccumulator;
import com.traskindustries.genetics.DNAStream;
import org.springframework.stereotype.Service;

import com.traskindustries.exception.DNAIncoherenceException;

@Service
public class MutantService {

	public boolean isMutant(String[] dna) {
		Objects.requireNonNull(dna);
		if (dna.length == 0
			|| dna.length != dna[0].length()) {
			throw new DNAIncoherenceException();
		}
		return DNAStream
				.fromStringArray(dna)
				.containsGen(
						new XGenIdentificationAccumulator());
	}
}
