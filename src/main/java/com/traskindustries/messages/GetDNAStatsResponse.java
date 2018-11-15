package com.traskindustries.messages;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetDNAStatsResponse {

    @JsonProperty("count_mutant_dna")
    private long mutants;

    @JsonProperty("count_human_dna")
    private long humans;

    @JsonProperty("ratio")
    private Double ratio;

    public static class Builder {
        private long mutants;
        private long humans;
        public Builder mutants(long mutants) {
            this.mutants = mutants;
            return this;
        }
        public Builder humans(long humans) {
            this.humans = humans;
            return this;
        }
        public GetDNAStatsResponse build() {
            final BigDecimal mutants =
                    BigDecimal
                    .valueOf(this.mutants);
            final BigDecimal humans =
                    BigDecimal
                    .valueOf(this.humans);
            final BigDecimal ratio =
                    this.humans > 0.00 && this.mutants > 0.00 ?
                    mutants
                    .divide(
                        humans, 2,
                        RoundingMode.HALF_UP) : BigDecimal.ZERO;

            return new GetDNAStatsResponse(
                    this.mutants,
                    this.humans,
                    ratio.doubleValue()
            );
        }
    }
}
