package br.com.osvaldsoza;


import javax.enterprise.context.ApplicationScoped;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class PersonRepositoryImpl extends PersonRepository {
    private static final String FIND_BY_ID = "SELECT * FROM people WHERE id = ?";
    private static final String FIND_ALL = "SELECT * FROM people";

    private final DataSource dataSource;

    public PersonRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private Connection getConnection() {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public List<Person> findAll() {
        var persons = new ArrayList<Person>();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL)) {

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    var person = getResultSetPerson(resultSet);
                    persons.add(person);
                }
            }

        } catch (SQLException e) {
            throw new PersistenceException(e);
        }
        return persons;
    }


    public Person findById(int id) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ID)) {

            statement.setObject(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return getResultSetPerson(resultSet);
                }
            }

        } catch (SQLException e) {
            throw new PersistenceException(e);
        }
        return null;
    }

    private Person getResultSetPerson(ResultSet resultSet) throws SQLException {
        return new Person(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getInt("age"));
    }
}
