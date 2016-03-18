package org.security.identifier;

import org.junit.Test;
import org.security.identifier.exceptions.InvalidCheckDigitInSecurityIdentifier;

import static org.assertj.core.api.Assertions.assertThat;

public class SedolTest {

    private static final String VALID_SEDOL = "0263494";

    @Test
    public void sedolShouldBeCreatedFromValidValue() {
        assertThat(Sedol.from(VALID_SEDOL).toString()).isEqualTo(VALID_SEDOL);
    }

    @Test(expected = InvalidCheckDigitInSecurityIdentifier.class)
    public void sedolShouldNotBeCreatedFromMalformedValue() {
        String malformed = VALID_SEDOL.substring(1, 2);
        Sedol.from(malformed);
    }
}
