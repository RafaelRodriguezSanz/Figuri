package ucu.edu.uy.Servicio.POJO.Utils;

import java.util.Random;

// Extracted From https://github.com/fabdelgado/ciuy/tree/main/src/main/java/com/fabdelgado/ciuy

public class Validator {
    public boolean validateCi(String ci) {
        String cleanCi = cleanNumber(ci);
        char validationDigit = cleanCi.charAt(cleanCi.length() - 1);

        return Character.getNumericValue(validationDigit) == this.validationDigit(cleanCi);
    }

    public static String cleanNumber(String ci) {
        return ci.replaceAll("[^0-9]", "");
    }

    private Integer validationDigit(String ci) {
        String cleanCi = cleanNumber(ci);
        int a = 0;
        String baseNumber = "2987634";
        String addZeros = String.format("%8s", cleanCi).replace(" ", "0");

        for (int i = 0; i < baseNumber.length(); i++) {
            int baseDigit = Character.getNumericValue(baseNumber.charAt(i));
            int ciWithZeros = Character.getNumericValue(addZeros.charAt(i));
            a += (baseDigit * ciWithZeros) % 10;
        }
        return a % 10 == 0 ? 0 : 10 - a % 10;
    }

    public String randomCi() {
        int randomNumber = 10000000 + new Random().nextInt(90000000);
        String ci = String.valueOf(randomNumber).substring(0, 7)
                + this.validationDigit(String.valueOf(randomNumber));
        return ci;
    }
}
