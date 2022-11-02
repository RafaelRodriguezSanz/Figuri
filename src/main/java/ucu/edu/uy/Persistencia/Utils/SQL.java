package ucu.edu.uy.Persistencia.Utils;

import java.util.Scanner;

public class SQL {
    public static String getQuery(String sqlScriptName) {
        Scanner scanner = new Scanner(
                SQL.class.getClassLoader().getResourceAsStream(String.format("sql/%s.sql", sqlScriptName)))
                .useDelimiter("\\A");
        return scanner.hasNext() ? scanner.next() : "";
    }

    public static void populateQuery(String query, String... values) {
        query = String.format(query, values);
    }
}
