package ucu.edu.uy.Persistencia.Utils;

import org.apache.commons.codec.binary.Base64;

public class Encoder {

    public static String encode(String value) {
        return new String(Base64.encodeBase64(value.getBytes()));
    }

    public static String decode(String value) {
        return new String(Base64.decodeBase64(value.getBytes()));
    }
}
