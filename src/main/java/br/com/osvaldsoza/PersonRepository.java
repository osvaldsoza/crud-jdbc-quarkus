package br.com.osvaldsoza;

import java.util.ArrayList;
import java.util.List;

public abstract class PersonRepository {

     List<Person> findAll(){
         return new ArrayList<Person>();
     };
    Person findById(int id){
        return new Person();
    };

    void teste(){

    }
}
