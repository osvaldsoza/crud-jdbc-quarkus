package br.com.osvaldsoza.util.querys;

public class PersonQueryUtil {

    private static final String FIND_BY_ID = "SELECT * FROM people WHERE id = ?";
    private static final String FIND_ALL = "SELECT * FROM people";

    public static String getFindAll() {
        return FIND_ALL;
    }

    public static String getFindById() {
        return FIND_BY_ID;
    }
}
