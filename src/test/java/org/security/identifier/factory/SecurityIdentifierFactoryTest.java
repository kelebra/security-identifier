package org.security.identifier.factory;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.security.identifier.exceptions.UnknownSecurityIdentifierType;

import java.util.Arrays;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(Parameterized.class)
public class SecurityIdentifierFactoryTest {

    private final String securityIdentifier;
    private final SecurityIdentifierType securityIdentifierType;

    public SecurityIdentifierFactoryTest(String securityIdentifier, SecurityIdentifierType securityIdentifierType) {
        this.securityIdentifier = securityIdentifier;
        this.securityIdentifierType = securityIdentifierType;
    }

    @Parameterized.Parameters(name = "{1} - {0}")
    public static Collection primeNumbers() {
        return Arrays.asList(new Object[][]{
                {"037833100", SecurityIdentifierType.CUSIP},
                {"US0378331005", SecurityIdentifierType.ISIN},
                {"0263494", SecurityIdentifierType.SEDOL}
        });
    }

    @Test
    public void factoryShouldDetermineCusipType() throws UnknownSecurityIdentifierType {
        assertThat(SecurityIdentifierFactory.getType(securityIdentifier)).isEqualTo(securityIdentifierType);
    }
}
