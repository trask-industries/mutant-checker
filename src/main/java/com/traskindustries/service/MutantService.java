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
			|| dna.length != dna[0].length() ) {
			throw new DNAIncoherenceException();
		}
		return DNAStream
			.fromStringArray(dna)
			.containsGen(
				new XGenIdentificationAccumulator());
/*


		final Integer referenceGenSize = 
				dna[0].length();
		String lastGen = 
				dna[0];
		final GeneticAccumulator accumulator =
				new GeneticAccumulator()
				.initialize(referenceGenSize);
		for(int i=1; i<dna.length; i++) {
			final String gen = 
					dna[i];
			validateGenSizeIsCoherent(
					referenceGenSize,
					gen);
			final String genPair = 
					lastGen
					.concat(gen);
			if (findXGen(
					genPair, 
					accumulator,
					i,
					i == dna.length-1)) {
				return true;
			}
			lastGen = gen;
		}
		return false;
		*/
	}
	
	private void validateGenSizeIsCoherent(final Integer referenceGenSize,
									  	   final String gen) {
		if (!referenceGenSize.equals(gen.length())) {
			throw new DNAIncoherenceException();
		}
	}
	
	public boolean findXGen(final String genPair, 
						  final GeneticAccumulator accumulator,
						  final int genNumber,
						  final boolean isLastPair) {
		final char[] bases = genPair.toCharArray();
		final int genSize = bases.length / 2;
		accumulator
		.resetHorizontalCounter();
		accumulator
		.compareVertical(bases[0], bases[genSize], 0);
		for (int i=1; i<genSize; i++) {
			accumulator
			.compareHorizontal(bases[i-1], bases[i]);
			accumulator
			.compareVertical(bases[i], bases[i+genSize], i);
			if(i<genPair.length()-1) {
				accumulator
				.compareNwToSe(bases[i-1],  bases[i+genSize], i-1-genNumber+genSize);
			}
			accumulator
			.compareNeToSw(bases[i],  bases[i-1+genSize], i+genNumber);
			if (accumulator
				.verifyCounters()) {
				return true;
			}
		}
		if (isLastPair) {
			for (int i=genSize+1; i<genPair.length(); i++) {
				accumulator
				.compareHorizontal(bases[i-1], bases[i]);
				if (accumulator
					.verifyCounters()) {
						return true;
					}
			}
		}
		return false;
	}
}
