package com.traskindustries.messages;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.traskindustries.validator.MustBeHumanDNA;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class VerifyIsMutantRequest {

    @NotNull
    @NotEmpty
    @MustBeHumanDNA
    private String[] dna;
}
