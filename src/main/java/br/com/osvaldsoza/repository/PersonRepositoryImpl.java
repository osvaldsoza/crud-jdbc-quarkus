package br.com.osvaldsoza.repository;


import br.com.osvaldsoza.exception.PersistenceException;
import br.com.osvaldsoza.model.Person;
import br.com.osvaldsoza.util.querys.PersonQueryUtil;
import br.com.osvaldsoza.util.ConnectionDB;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class PersonRepositoryImpl implements PersonRepository {

    @Inject
    private DataSource dataSource;

    public List<Person> findAll() {
        var persons = new ArrayList<Person>();
        try (Connection connection = ConnectionDB.get(dataSource);
             PreparedStatement statement = connection.prepareStatement(PersonQueryUtil.getFindAll())) {

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
        try (Connection connection = ConnectionDB.get(dataSource);
             PreparedStatement statement = connection.prepareStatement(PersonQueryUtil.getFindById())) {

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
