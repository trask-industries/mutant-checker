package com.traskindustries.service;

import java.util.Arrays;

import org.springframework.stereotype.Service;

import com.traskindustries.exception.DNAIncoherenceException;

@Service
public class MutantService {
	public boolean isMutant(String[] dna) {
		if (null == dna 
			|| dna.length == 0 
			|| dna.length != dna[0].length() ) {
			throw new DNAIncoherenceException();
		}
		final Integer referenceGenSize = 
				dna[0].length();
		String lastGen = 
				dna[0];
		final Accumulator accumulator = 
				new Accumulator(referenceGenSize);
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
					i)) {
				return true;
			}
			lastGen = gen;
		}
		return false;
	}
	
	private void validateGenSizeIsCoherent(final Integer referenceGenSize,
									  	   final String gen) {
		if (!referenceGenSize.equals(gen.length())) {
			throw new DNAIncoherenceException();
		}
	}
	
	public boolean findXGen(final String genPair, 
						  final Accumulator accumulator,
						  final int genNumber) {
		final char[] bases = genPair.toCharArray();
		final int genSize = bases.length / 2;
		accumulator
		.resetHorizontalCounter();
		for (int i=1; i<genSize; i++) {
			accumulator
			.compareHorizontal(bases[i-1], bases[i]);
			accumulator
			.compareVertical(bases[i-1], bases[i-1+genSize], i-1);
			if(i<genPair.length()-1) {
				accumulator
				.compareNwToSe(bases[i-1],  bases[i+genSize], i-1-genNumber);
			}
			accumulator
			.compareNeToSw(bases[i],  bases[i-1+genSize], i+genNumber);
			if (accumulator
				.verifyCounters()) {
				return true;
			}
		}
		return false;
	}
	
	public static class Accumulator {
		private int genMatch = 0;
		private int horizontalCounter = 0;
		private int[] verticalCounter;
		private int[] nwToseCounter;
		private int[] neToswCounter;
		public Accumulator(final int genSize) {
			verticalCounter = 
					new int[genSize];
			nwToseCounter = 
					new int[genSize];
			neToswCounter = 
					new int[genSize];
		}
		public Accumulator compareHorizontal(final char x,
											 final char y) {
			if (x == y) {
				horizontalCounter =
						(horizontalCounter == 0) ? 2
						: horizontalCounter + 1;
				if (horizontalCounter == 4) {
					genMatch++;
				}
			} else {
				horizontalCounter = 0;
			}
			return this;
		}
		public Accumulator compareVertical(final char x,
										   final char y,
										   int position) {
			if (x == y) {
				verticalCounter[position] =
						(verticalCounter[position] == 0) ? 2
						: verticalCounter[position] + 1;
			} else {
				verticalCounter[position] = 0;
			}
			return this;
		}
		public Accumulator compareNwToSe(final char x,
			   							 final char y,
		   								 int position) {
			if (x == y) {
				nwToseCounter[position] =
						(nwToseCounter[position] == 0) ? 2
						: nwToseCounter[position] + 1;
			} else {
				nwToseCounter[position] = 0;
			}
			return this;
		}
		public Accumulator compareNeToSw(final char x,
					 					 final char y,
					 					 int position) {
			if (x == y) {
				neToswCounter[position] =
						(neToswCounter[position] == 0) ? 2
						: neToswCounter[position] + 1;
			} else {
				neToswCounter[position] = 0;
			}
			return this;
		}					
		public Accumulator resetHorizontalCounter() {
			horizontalCounter=0;
			return this;
		}
		public boolean verifyCounters() {
			return genMatch > 0;
			/*
			int totalCounter = horizontalCounter
				+ Arrays.stream(verticalCounter).max().getAsInt()
				+ Arrays.stream(nwToseCounter).max().getAsInt()
				+ Arrays.stream(neToswCounter).max().getAsInt();
			return totalCounter >= 8;
			*/
		}
	}
}
