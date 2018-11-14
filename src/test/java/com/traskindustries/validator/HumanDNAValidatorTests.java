package com.traskindustries.validator;


import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HumanDNAValidatorTests {

    private static final HumanDNAValidator humanDNAValidator = new HumanDNAValidator();

    @Test
    public void testValidatorReturnsTrueWithValidHumanDNA() {
        final String[] dna = {
        //       012345
                "ATCGAT",
                "CGCTCA",
                "TGTCTA",
                "GATTAC"};
        assertThat(humanDNAValidator.isValid(dna, null)).isTrue();
    }

    @Test
    public void testValidatorReturnsFalseWithNotHumanDNA() {
        final String[] dna = {
                //       012345
                "ATCGAT",
                "CGXTCA",
                "TGTCTA",
                "GATTAC"};
        assertThat(humanDNAValidator.isValid(dna, null)).isFalse();
    }

}
