package org.security.identifier;

import org.security.identifier.check.CheckDigitProvider;
import org.security.identifier.check.LuhnCheckDigitProvider;
import org.security.identifier.check.ModuloTenCheckDigitProvider;
import org.security.identifier.exceptions.InvalidSedolValueProvided;
import org.security.identifier.generic.SecurityIdentifier;
import org.security.identifier.util.InstanceProvider;

import static org.security.identifier.util.Util.padLeftWithZeroes;

public class Sedol extends SecurityIdentifier {

    private static final String UNITED_KINGDOM_COUNTRY_CODE = "GB";
    private static final int LENGTH = 7;
    private static final CheckDigitProvider CHECK_DIGIT_PROVIDER =
            new ModuloTenCheckDigitProvider(new int[]{1, 3, 1, 7, 3, 9, 1}, false);
    private static final InstanceProvider<Sedol> INSTANCE_PROVIDER = new InstanceProvider<Sedol>() {
        @Override
        public Sedol provide(String body, char checkDigit) {
            return new Sedol(body, checkDigit);
        }
    };

    public static Sedol from(String value) {
        return from(
                false,
                true,
                value.substring(0, value.length() - 1),
                value.charAt(value.length() - 1),
                LENGTH,
                CHECK_DIGIT_PROVIDER,
                InvalidSedolValueProvided.INSTANCE,
                INSTANCE_PROVIDER
        );
    }

    private Sedol(String body, char checkDigit) {
        super(body, checkDigit);
    }

    @Override
    public Isin isin(String countryCode) {
        if (!UNITED_KINGDOM_COUNTRY_CODE.equals(countryCode)) {
            throw new UnsupportedOperationException("Sedol cannot be converted to ISIN");
        } else {
            String isinBody = countryCode + padLeftWithZeroes(body(), Isin.BODY_LENGTH);
            return Isin.from(isinBody + LuhnCheckDigitProvider.INSTANCE.checkDigit(isinBody));
        }
    }

    @Override
    public Sedol sedol() {
        return this;
    }

    @Override
    public Cusip cusip() {
        throw new UnsupportedOperationException("Sedol cannot be converted to CUSIP");
    }
}
