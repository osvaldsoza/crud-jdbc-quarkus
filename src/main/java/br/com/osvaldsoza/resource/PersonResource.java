package br.com.osvaldsoza.resource;

import br.com.osvaldsoza.model.Person;
import br.com.osvaldsoza.service.PersonService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/people")
public class PersonResource {

    @Inject
    private PersonService personService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Person> all() {
        return personService.findAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Person get(@PathParam("id") int id) {
        return personService.findById(id);
    }

    @GET
    @Path("/name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Person getByName(@PathParam("name") String name) {
        return personService.findByName(name);
    }
//
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public int post(Person person) {
        return personService.insert(person);

    }
//
//    @PUT
//    @Path("/{id}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Person put(@PathParam("id") UUID id, Person person) {
//        if (personRepository.findById(id) == null) {
//            throw new PersonNotFoundException(id);
//        }
//        return personRepository.update(new Person(id, person.getName(), person.getAge()));
//    }
//
//    @DELETE
//    @Path("/{id}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public void delete(@PathParam("id") UUID id) {
//        if (personRepository.findById(id) == null) {
//            throw new PersonNotFoundException(id);
//        }
//        personRepository.deleteById(id);
//    }
}
