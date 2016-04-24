package org.security.identifier;

import org.junit.Test;
import org.security.identifier.exceptions.CountryCodeNotValid;

import static org.assertj.core.api.Assertions.assertThat;

public class IsinTest {

    private static final String AAPL = "US0378331005";

    @Test
    public void isinShouldBeCreatedFromValidValue() {
        assertThat(Isin.from(AAPL).toString()).isEqualTo(AAPL);
    }

    @Test(expected = CountryCodeNotValid.class)
    public void isinShouldNotBeCreatedFromMalformedValue() {
        Isin.from(AAPL.substring(2));
    }
}
