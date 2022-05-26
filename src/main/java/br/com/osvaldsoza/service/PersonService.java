package br.com.osvaldsoza.service;

import br.com.osvaldsoza.model.Person;
import br.com.osvaldsoza.repository.PersonRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class PersonService {

    @Inject
    private PersonRepository personRepository;

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Person findById(int id) {
        return personRepository.findById(id);
    }
}
