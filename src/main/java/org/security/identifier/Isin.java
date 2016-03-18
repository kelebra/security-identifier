package org.security.identifier;

import org.security.identifier.check.LuhnCheckDigitProvider;
import org.security.identifier.exceptions.InvalidIsinValueProvided;
import org.security.identifier.generic.SecurityIdentifier;
import org.security.identifier.util.InstanceProvider;

public class Isin extends SecurityIdentifier {

    private static int LENGTH = 12;
    protected static int BODY_LENGTH = 9;
    private static final InstanceProvider<Isin> INSTANCE_PROVIDER = new InstanceProvider<Isin>() {
        @Override
        public Isin provide(String body, char checkDigit) {
            return new Isin(body, checkDigit);
        }
    };

    public static Isin from(String value) {
        return from(
                true,
                true,
                value.substring(0, value.length() - 1),
                value.charAt(value.length() - 1),
                LENGTH,
                LuhnCheckDigitProvider.INSTANCE,
                InvalidIsinValueProvided.INSTANCE,
                INSTANCE_PROVIDER
        );
    }

    private Isin(String body, char checkDigit) {
        super(body, checkDigit);
    }

    @Override
    public Isin isin(String countryCode) {
        return this;
    }

    @Override
    public Sedol sedol() {
        return Sedol.from(body());
    }

    @Override
    public Cusip cusip() {
        return Cusip.from(body());
    }

    public String countryCode() {
        return body().substring(0, 2);
    }
}
