package vttp5_ssf_day13workshop.vttp5_ssf_day13workshop.repo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import vttp5_ssf_day13workshop.vttp5_ssf_day13workshop.model.Person;

@Repository
public class PersonRepo {
    
    private List<Person> personList = new ArrayList<>();

    public PersonRepo(){
    }

    public List<Person> getPersonList() {
        return personList;
    }

    

    
}
