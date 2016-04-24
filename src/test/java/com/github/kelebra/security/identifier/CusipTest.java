package com.github.kelebra.security.identifier;

import com.github.kelebra.security.identifier.exceptions.InvalidCheckDigitInSecurityIdentifier;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import com.github.kelebra.security.identifier.util.Cusips;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

public class CusipTest {

    private static final String AAPL = "037833100";

    @Test
    public void cusipShouldBeCreatedFromValidAppleCusip() {
        Assertions.assertThat(Cusip.from(AAPL).toString()).isEqualTo(AAPL);
    }

    @Test
    public void cusipShouldBeCreatedFromValidShortenAppleCusip() {
        Assertions.assertThat(Cusip.from(AAPL.substring(1)).toString()).isEqualTo(AAPL);
    }

    @Test(expected = InvalidCheckDigitInSecurityIdentifier.class)
    public void cusipShouldNotBeCreatedFromMalformedAppleCusip() {
        Cusip.from(AAPL.substring(0, 5));
    }

    @Test
    public void allCusipsShouldBeParsed() {
        Map<String, Class> unparsedCusips = new HashMap<String, Class>();
        final Collection<String> allCusips = Cusips.all();
        System.out.println("Total amount of cusips to be parsed " + allCusips.size());
        for (String cusip : allCusips) {
            try {
                Cusip.from(cusip);
            } catch (Exception any) {
                unparsedCusips.put(cusip.substring(0, 6) + " " + cusip.substring(6), any.getClass());
            }
        }
        if (!unparsedCusips.isEmpty()) {
            fail("Was not able to parse " + unparsedCusips.size() + " cusips.\nThe ones causing errors: " + unparsedCusips);
        }
    }
}
