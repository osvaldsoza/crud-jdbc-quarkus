package br.com.osvaldsoza;

import java.sql.SQLException;

public class PersistenceException extends RuntimeException {
    public PersistenceException(SQLException e) {
        super(e);
    }
}
