package ucu.edu.uy.Servicio.POJO;

import lombok.Getter;
import ucu.edu.uy.Servicio.POJO.Utils.Validator;

public class Telefono {

    private static String URUGUAY_PREFIX = "+598";
    private static int CANT_DIGITOS = 8;

    @Getter
    int[] digitos = new int[CANT_DIGITOS];

    public Telefono(String number) {
        Validator validator = new Validator();
        number = validator.cleanNumber(number);
        this.digitos[0] = Character.getNumericValue(number.charAt(0));
        this.digitos[1] = Character.getNumericValue(number.charAt(1));
        this.digitos[2] = Character.getNumericValue(number.charAt(2));
        this.digitos[3] = Character.getNumericValue(number.charAt(3));
        this.digitos[4] = Character.getNumericValue(number.charAt(4));
        this.digitos[5] = Character.getNumericValue(number.charAt(5));
        this.digitos[6] = Character.getNumericValue(number.charAt(6));
        this.digitos[7] = Character.getNumericValue(number.charAt(7));
    }

    public boolean isValid() {
        return true; // TODO: Validate number
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(URUGUAY_PREFIX);
        builder.append(String.valueOf(digitos[0]));
        builder.append(String.valueOf(digitos[1]));
        builder.append(String.valueOf(digitos[2]));
        builder.append(String.valueOf(digitos[3]));
        builder.append(String.valueOf(digitos[4]));
        builder.append(String.valueOf(digitos[5]));
        builder.append(String.valueOf(digitos[6]));
        builder.append(String.valueOf(digitos[7]));
        return builder.toString();
    }

    public int getDigitos() {
        int digitos = 0;
        for (int i = 0; i < CANT_DIGITOS; i++) {
            digitos += (10 ^ i) * this.digitos[i];
        }
        return digitos;
    }
}
