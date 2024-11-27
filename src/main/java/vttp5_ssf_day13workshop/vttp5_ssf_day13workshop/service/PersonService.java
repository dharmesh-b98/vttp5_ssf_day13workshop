package vttp5_ssf_day13workshop.vttp5_ssf_day13workshop.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp5_ssf_day13workshop.vttp5_ssf_day13workshop.model.Person;
import vttp5_ssf_day13workshop.vttp5_ssf_day13workshop.repo.PersonRepo;

@Service
public class PersonService {
    
    @Autowired
    PersonRepo personrepo;

    @Autowired
    DataService dataservice;

    List<Person> personList = new ArrayList<>();

    public PersonService(PersonRepo personrepo) {
        this.personrepo = personrepo;
    }

    public List<Person> getPersonList() {
        return personrepo.getPersonList();
    }

    public List<Person> loadContacts(String dataDir) throws IOException{
        File dataDirF = new File(dataDir);
        File[] contacts = dataDirF.listFiles();
        for (File file : contacts){
            Person person = dataservice.readData(file);
            this.personList.add(person);
        }
        return this.personList;
            
    }

    public List<String> loadIds(String dataDir) throws IOException{
        List<String> idList = new ArrayList<>();
        File dataDirF = new File(dataDir);
        File[] contacts = dataDirF.listFiles();
        for (File file : contacts){
            String filename = file.getName().split("\\.")[0];
            idList.add(filename);
        }
        return idList;
            
    }
    
}
