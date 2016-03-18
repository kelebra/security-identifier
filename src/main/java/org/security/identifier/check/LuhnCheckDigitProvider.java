package org.security.identifier.check;

import java.util.List;

import static org.security.identifier.util.Util.toInts;

public class LuhnCheckDigitProvider implements CheckDigitProvider {

    public static final LuhnCheckDigitProvider INSTANCE = new LuhnCheckDigitProvider();

    @Override
    public char checkDigit(String body) {
        List<Integer> numericValue = toInts(body);
        // Luhn's algorithm implementation
        int sum = 0;
        boolean alternate = true;
        for (int i = 0; i < numericValue.size(); i++) {
            int n = numericValue.get(i);
            if (alternate) {
                n *= 2;
            }
            sum += (n / 10 + n % 10);
            alternate = !alternate;
        }
        return Character.forDigit(sum % 10, 10);
    }
}
