package com.github.kelebra.security.identifier;

import com.github.kelebra.security.identifier.exceptions.InvalidCheckDigitInSecurityIdentifier;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SedolTest {

    private static final String VALID_SEDOL = "0263494";
    private static final String SEDOL_WITHBAD_CHECK_DIGIT = "0263495";

    @Test
    public void sedolShouldBeCreatedFromValidValue() {
        assertThat(Sedol.from(VALID_SEDOL).toString()).isEqualTo(VALID_SEDOL);
    }

    @Test(expected = InvalidCheckDigitInSecurityIdentifier.class)
    public void sedolShouldNotBeCreatedFromMalformedValue() {
        String malformed = VALID_SEDOL.substring(1, 2);
        Sedol.from(malformed);
    }

    @Test
    public void sedolShouldBeCreatedFromMalformedCheckDigitValue() {
        assertThat(new SedolBuilder().withoutCheckOfCheckDigit().build(SEDOL_WITHBAD_CHECK_DIGIT).toString()).isEqualTo(SEDOL_WITHBAD_CHECK_DIGIT);
    }
}
