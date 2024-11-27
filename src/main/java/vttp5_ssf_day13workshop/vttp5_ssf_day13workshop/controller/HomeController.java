package vttp5_ssf_day13workshop.vttp5_ssf_day13workshop.controller;


import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import vttp5_ssf_day13workshop.vttp5_ssf_day13workshop.model.Person;
import vttp5_ssf_day13workshop.vttp5_ssf_day13workshop.service.DataService;
import vttp5_ssf_day13workshop.vttp5_ssf_day13workshop.service.PersonService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;





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

        //other custom errors
        if (dataservice.isAgeWithinRange(person.getDateOfBirth()) == false){
            ObjectError err = new ObjectError("globalError", "Age needs to be between 10 and 100 years old");
            result.addError(err);
            return "personcreate";
        }
        

        String path = dataservice.getHexCodePathName(dataDir);
        File file = new File(path);

        dataservice.writeData(person, file);
        System.out.println ("person created");
        return "redirect:/";
    }


    @GetMapping("/contact/{id}")
    public String getContactPerson(@PathVariable String id, Model model) throws IOException {
        File file = new File(dataDir + "/" +id + ".txt");

        Person person = dataservice.readData(file);
        if (person == null){
            return "cannotfind"; //not sure how to do this
        }
        
        model.addAttribute("person", person);
        return "personinfo";
    }

    @GetMapping("/contacts")
    public String getContacts( Model model) throws IOException {
        List<String> idList = personservice.loadIds(dataDir);
        model.addAttribute("idList", idList);
        return "contacts";
        
    }
    
    
    

    
}
