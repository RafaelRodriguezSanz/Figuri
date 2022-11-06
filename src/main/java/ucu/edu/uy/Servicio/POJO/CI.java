package ucu.edu.uy.Servicio.POJO;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import ucu.edu.uy.Servicio.POJO.Utils.Validator;

public class CI {

    private static int CANT_DIGITOS = 8;

    int[] digitos = new int[CANT_DIGITOS];
    @Getter
    @Setter(AccessLevel.PRIVATE)
    int digitoVerificador;

    public CI(String ci) {
        Validator validator = new Validator();
        ci = validator.cleanNumber(ci);
        digitos[0] = Character.getNumericValue(ci.charAt(0));
        digitos[1] = Character.getNumericValue(ci.charAt(1));
        digitos[2] = Character.getNumericValue(ci.charAt(2));
        digitos[3] = Character.getNumericValue(ci.charAt(3));
        digitos[4] = Character.getNumericValue(ci.charAt(4));
        digitos[5] = Character.getNumericValue(ci.charAt(5));
        digitos[6] = Character.getNumericValue(ci.charAt(6));
        setDigitoVerificador(Character.getNumericValue(ci.charAt(7)));
    }

    public boolean isValid() {
        Validator validator = new Validator();
        String ci = validator.cleanNumber(this.toString());
        return validator.validateCi(ci);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.valueOf(digitos[0]));
        builder.append('.');
        builder.append(String.valueOf(digitos[1]));
        builder.append(String.valueOf(digitos[2]));
        builder.append(String.valueOf(digitos[3]));
        builder.append('.');
        builder.append(String.valueOf(digitos[4]));
        builder.append(String.valueOf(digitos[5]));
        builder.append(String.valueOf(digitos[6]));
        builder.append('-');
        builder.append(String.valueOf(digitos[7]));
        return builder.toString();
    }

    public Integer toInteger() {
        Integer res = 0;
        for (int i = 0; i < digitos.length; i++) {
            res += (10 ^ i) * digitos[i];
        }
        return res;
    }

    public int getDigitos() {
        int digitos = 0;
        for (int i = 0; i < CANT_DIGITOS; i++) {
            digitos += (10 ^ i) * this.digitos[i];
        }
        return digitos;
    }

}
