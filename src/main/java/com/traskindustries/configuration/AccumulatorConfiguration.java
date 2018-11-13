package com.traskindustries.configuration;

import com.traskindustries.service.GeneticAccumulator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AccumulatorConfiguration {

    @Bean()
    public GeneticAccumulator geneticAccumulator() {
        return new GeneticAccumulator();
    }
}
