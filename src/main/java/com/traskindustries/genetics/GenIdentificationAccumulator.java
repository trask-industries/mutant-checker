package com.traskindustries.genetics;

import java.util.function.BiFunction;

@FunctionalInterface
public interface GenIdentificationAccumulator extends BiFunction<String,String, Boolean> {

}
