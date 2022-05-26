package br.com.osvaldsoza;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/people")
public class PersonResource {

    @Inject
    private PersonRepositoryImpl personRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Person> all() {
        return personRepository.findAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Person get(@PathParam("id") int id) {
        return personRepository.findById(id);
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
