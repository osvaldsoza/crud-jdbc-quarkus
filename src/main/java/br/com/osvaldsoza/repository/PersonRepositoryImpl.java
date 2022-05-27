package br.com.osvaldsoza.repository;


import br.com.osvaldsoza.exception.PersistenceException;
import br.com.osvaldsoza.model.Person;
import br.com.osvaldsoza.model.querys.PersonQuery;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static br.com.osvaldsoza.model.querys.PersonQuery.*;
import static br.com.osvaldsoza.util.ConnectionDB.getConnection;

@ApplicationScoped
public class PersonRepositoryImpl implements PersonRepository {

    @Inject
    private DataSource dataSource;

    @Override
    public List<Person> findAll() {
        var persons = new ArrayList<Person>();
        try (Connection connection = getConnection(dataSource);
             PreparedStatement statement = connection.prepareStatement(FIND_ALL.getQuery())) {

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

    @Override
    public Person findById(int id) {
        return queryByOneParam(id, FIND_BY_ID);
    }

    @Override
    public Person findByName(String name) {
        return queryByOneParam(name, FIND_BY_NAME);
    }

    @Override
    public int insert(Person person) {
        try (Connection connection = getConnection(dataSource);
             PreparedStatement statement = connection.prepareStatement(INSERT.getQuery())) {
            statement.setObject(1, person.getId());
            statement.setObject(2, person.getName());
            statement.setObject(3, person.getAge());

           return statement.executeUpdate();

        } catch (SQLException e) {
            throw new PersistenceException(e);
        }

    }

    private Person queryByOneParam(Object param, PersonQuery personQuery) {
        try (Connection connection = getConnection(dataSource);
             PreparedStatement statement = connection.prepareStatement(personQuery.getQuery())) {

            if (param instanceof String) {
                param = param + "%";
            }
            statement.setObject(1, param);

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
