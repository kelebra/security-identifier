package org.security.identifier.check;

import static java.lang.Math.min;
import static org.security.identifier.util.Util.characterCode;

public class ModuloTenCheckDigitProvider implements CheckDigitProvider {

    private final int[] weights;
    private final boolean moduloSum;

    public ModuloTenCheckDigitProvider(int[] weights, boolean moduloSum) {
        this.weights = weights;
        this.moduloSum = moduloSum;
    }

    @Override
    public char checkDigit(String body) {
        int sum = 0;
        int min = min(body.length(), weights.length);
        for (int i = 0; i < min; i++) {
            int current = weights[i] * characterCode(body.charAt(i));
            if (moduloSum) {
                sum += (current % 10 + current / 10) % 10;
            } else {
                sum += current;
            }
        }
        return (char) ('0' + (10 - sum % 10) % 10);
    }
}
