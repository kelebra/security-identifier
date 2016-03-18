package org.security.identifier;

import org.junit.Test;
import org.security.identifier.exceptions.InvalidCheckDigitInSecurityIdentifier;

import static org.assertj.core.api.Assertions.assertThat;

public class CusipTest {

    private static final String AAPL = "037833100";

    @Test
    public void cusipShouldBeCreatedFromValidAppleCusip() {
        assertThat(Cusip.from(AAPL).toString()).isEqualTo(AAPL);
    }

    @Test
    public void cusipShouldBeCreatedFromValidShortenAppleCusip() {
        assertThat(Cusip.from(AAPL.substring(1)).toString()).isEqualTo(AAPL);
    }

    @Test(expected = InvalidCheckDigitInSecurityIdentifier.class)
    public void cusipShouldNotBeCreatedFromMalformedAppleCusip() {
        Cusip.from(AAPL.substring(0, 5));
    }
}
