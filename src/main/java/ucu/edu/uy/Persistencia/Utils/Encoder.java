package ucu.edu.uy.Persistencia.Utils;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;

import org.identityconnectors.common.security.GuardedString;
import org.identityconnectors.common.security.SecurityUtil;

import ucu.edu.uy.Servicio.POJO.Utils.PassAccessor;

public class Encoder {

    public static String encode(String password) throws NoSuchAlgorithmException {
        return SecurityUtil.computeBase64SHA1Hash(password.toCharArray());
    }

    public static String decode(GuardedString password) throws NoSuchAlgorithmException {
        PassAccessor accessor = new PassAccessor();
        password.access(accessor);
        return accessor.getPass();
    }

    public static String getHash(GuardedString password) throws NoSuchAlgorithmException {
        return encode(decode(password));
    }

}
