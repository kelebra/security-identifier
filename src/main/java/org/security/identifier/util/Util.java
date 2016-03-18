package org.security.identifier.util;

import java.util.*;

public class Util {

    private static char ZERO = '0';

    private static Set<String> KNOWN_COUNTRIES = new HashSet<String>();

    static {
        Collections.addAll(KNOWN_COUNTRIES, Locale.getISOCountries());
    }

    public static List<Integer> toInts(String value) {
        if (value == null) return Collections.emptyList();
        List<Integer> numericValue = new ArrayList<Integer>();
        for (int i = 0; i < value.length(); i++) {
            int intValue = characterCode(value.charAt(i));
            if (intValue >= 10) {
                numericValue.add(intValue / 10);
                intValue = intValue % 10;
            }
            numericValue.add(intValue);
        }
        return numericValue;
    }

    public static boolean countryCodeIsValid(String value) {
        return value != null && value.length() == 2 && KNOWN_COUNTRIES.contains(value);
    }

    public static String padLeftWithZeroes(String value, int requiredSize) {
        int zeroesSize = requiredSize - value.length();
        StringBuilder builder = new StringBuilder(requiredSize);
        for (int i = 0; i < zeroesSize; i++) {
            builder.append(ZERO);
        }
        return builder.append(value).toString();
    }

    public static boolean isAlphaNumeric(String input) {
        if (input == null) return false;
        for (int i = 0; i < input.length(); i++) {
            if (!Character.isLetterOrDigit(input.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static int characterCode(char character) {
        if (Character.isDigit(character)) {
            return Character.digit(character, 10);
        } else {
            char startChar = 'A';
            int javaOffset = (int) character - (int) startChar;
            return 10 + javaOffset;
        }
    }
}
