package br.com.osvaldsoza.exception;

import java.sql.SQLException;

public class PersistenceException extends RuntimeException {
    public PersistenceException(SQLException e) {
        super(e);
    }
}
