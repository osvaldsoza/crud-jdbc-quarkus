package br.com.osvaldsoza.resource;

import br.com.osvaldsoza.model.Person;
import br.com.osvaldsoza.service.PersonService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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
//
//    @POST
//    @Produces(MediaType.APPLICATION_JSON)
//    public Person post(Person person) {
//        return personRepository.insert(
//                new Person(UUID.randomUUID(), person.getName(), person.getAge())
//        );
//    }
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
