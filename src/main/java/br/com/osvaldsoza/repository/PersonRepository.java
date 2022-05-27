package br.com.osvaldsoza.repository;

import br.com.osvaldsoza.model.Person;

import java.util.List;

public interface PersonRepository {

    List<Person> findAll();
    Person findById(int id);
    Person findByName(String id);
    int insert(Person person);

}
