package com.github.kelebra.security.identifier;

import com.github.kelebra.security.identifier.exceptions.CountryCodeNotValid;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class IsinTest {

    private static final String AAPL = "US0378331005";
    private static final String AAPL_WITHBAD_CHECK_DIGIT = "US0378331006";

    @Test
    public void isinShouldBeCreatedFromValidValue() {
        assertThat(Isin.from(AAPL).toString()).isEqualTo(AAPL);
    }

    @Test(expected = CountryCodeNotValid.class)
    public void isinShouldNotBeCreatedFromMalformedValue() {
        Isin.from(AAPL.substring(2));
    }

    @Test
    public void isinShouldBeCreatedFromMalformedCheckDigit() {
        assertThat(new IsinBuilder().withoutCheckOfCheckDigit().build(AAPL_WITHBAD_CHECK_DIGIT).toString()).isEqualTo(AAPL_WITHBAD_CHECK_DIGIT);
    }
}
