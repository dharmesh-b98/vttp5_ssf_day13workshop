package vttp5_ssf_day13workshop.vttp5_ssf_day13workshop.controller;


import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
//import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import vttp5_ssf_day13workshop.vttp5_ssf_day13workshop.model.Person;
import vttp5_ssf_day13workshop.vttp5_ssf_day13workshop.service.DataService;
import vttp5_ssf_day13workshop.vttp5_ssf_day13workshop.service.PersonService;

import org.springframework.web.bind.annotation.GetMapping;



@Controller
@RequestMapping("")
public class HomeController {

    @Autowired
    DataService dataservice;

    @Autowired
    PersonService personservice;

    @Value("${dataDir}")
    private String dataDir;;  //Getting values from application properties, See how to put into application properties in the main function

    @GetMapping("")
    public String getHome(Model model) {
        //System.out.println("OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO" + dataDir);
        //System.out.println("\n\n\n\n\n\n\n\n");
        return "home";
    }
    
    @GetMapping("/contact")
    public String getContact(Model model) {
        Person person = new Person();
        model.addAttribute("person", person);
        return "personcreate";
    }

    @PostMapping("/contact")
    public String postContact(@Valid @ModelAttribute Person person, BindingResult result, Model model) throws IOException {

        if(result.hasErrors()){
            return "personcreate";
        }

        //Global error example
        /* if (dataservice.isAgeWithinRange(person.getDateOfBirth()) == false){
            ObjectError err = new ObjectError("globalError", "Age needs to be between 10 and 100 years old");
            result.addError(err);
            return "personcreate";
        } */
        

        String path = dataservice.getHexCodePathName(dataDir);
        File file = new File(path);

        dataservice.writeData(person, file);
        System.out.println ("person created");
        return "redirect:/";
        //return HttpStatus.CREATED + "Person " + person.getName() + " is successfully created";
    }


    @GetMapping("/contact/{id}")
    public String getContactPerson(@PathVariable String id, Model model) throws IOException {
        File file = new File(dataDir + "/" +id + ".txt");
        
        if (file.exists()){
            Person person = dataservice.readData(file);
            model.addAttribute("person", person);
            return "personinfo";
        }
        else{
            //return HttpStatus.NOT_FOUND + ": could not find file !! "; //returning HTTPS_STATUS 
            return "cannotfind";
        }
        
    }

    @GetMapping("/contacts")
    public String getContacts( Model model) throws IOException {
        List<String> idList = personservice.loadIds(dataDir);
        model.addAttribute("idList", idList);
        return "contacts";
        
    }
    
    
    

    
}


