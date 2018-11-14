package com.traskindustries.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.regex.Pattern;

public class HumanDNAValidator implements ConstraintValidator<MustBeHumanDNA, String[]> {

    private static final String HUMAN_DNA_BASES = "^[ATCG]+$";
    private static final Pattern HumanDNAPattern = Pattern.compile(HUMAN_DNA_BASES);

    @Override
    public void initialize(MustBeHumanDNA mustBeHumanDNA) {
    }

    @Override
    public boolean isValid(String[] genes, ConstraintValidatorContext constraintValidatorContext) {
        return Arrays
                .asList(genes)
                .stream()
               .allMatch(gen -> {
                   boolean matches = HumanDNAPattern.matcher(gen).matches();
                   return matches;
               });
    }
}
