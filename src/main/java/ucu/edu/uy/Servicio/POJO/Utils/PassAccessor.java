package ucu.edu.uy.Servicio.POJO.Utils;

import org.identityconnectors.common.security.GuardedString.Accessor;

public class PassAccessor implements Accessor {

    private String pass;

    @Override
    public void access(char[] passwdChars) {
        System.out.println(passwdChars.toString());
        this.pass = String.valueOf(passwdChars);
    }

    public String getPass() {
        return pass;
    }
}
