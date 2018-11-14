package com.traskindustries.genetics;

import java.util.Arrays;
import java.util.List;

public class DNAStream {
    final private List<String> dna;

    private DNAStream(String[] dna) {
        this.dna = Arrays.asList(dna);
    }

    public static DNAStream fromStringArray(String[] dna) {
       return new DNAStream(dna);
    }

    public boolean containsGen(GenIdentificationAccumulator accumulator) {
        boolean foundAny = false;
        boolean genDetected = false;
        String lastGen = null;
          for (String currentGen : this.dna) {
              if (!foundAny) {
                  foundAny = true;
                  lastGen = currentGen;
              }
              else {
                  genDetected = accumulator.apply(lastGen, currentGen);
                  if (genDetected) {
                      break;
                  }
                  lastGen = currentGen;
              }
          }
          return foundAny ? genDetected : false;
    }
}