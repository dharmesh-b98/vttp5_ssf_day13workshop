package vttp5_ssf_day13workshop.vttp5_ssf_day13workshop.service;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Repository;

import vttp5_ssf_day13workshop.vttp5_ssf_day13workshop.model.Person;

@Repository
public class DataService {
    public DataService(){

    }

    public String getHexCodePathName(String dataDir){
        //creating random 8 digit hex string
        Random random = new Random();
        int randomInt = random.nextInt(00000000,99999999);
        String randomHex = Integer.toHexString(randomInt);
        String path = dataDir + "/" + randomHex + ".txt";
        return path;
    }

    public boolean writeData(Person person, File file) throws IOException{
        
        if (!(file.exists())){
            file.createNewFile();
        }

        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);

        bw.write(person.getName());
        bw.newLine();
        bw.write(person.getEmail());
        bw.newLine();
        bw.write(String.valueOf(person.getPhoneNumber()));
        bw.newLine();
        bw.write(String.valueOf(person.getDateOfBirth().getTime())); //writes in long
        bw.flush();
        bw.close();
        fw.close();

        return true;
    }

    public Person readData(File file) throws IOException{

        if (!(file.exists())){
            return null;
        }

        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        
        String name=br.readLine();
        String email=br.readLine();
        Integer phoneNumber=Integer.valueOf(br.readLine());
        Long d= Long.valueOf(br.readLine());
        Date dateOfBirth = new Date(d);

        Person person = new Person(name,email,phoneNumber,dateOfBirth);
        
        br.close();
        return person;
    }


    public boolean isAgeWithinRange(Date dateOfBirth){

        Date currentDate = Calendar.getInstance().getTime();
        Long millisecondsDiff = currentDate.getTime() - dateOfBirth.getTime();

        TimeUnit timeUnit = TimeUnit.DAYS;
        Long daysDiff = timeUnit.convert(millisecondsDiff, TimeUnit.MILLISECONDS);
        Long yearsDiff = daysDiff/365;
        System.out.println("\n\n\n\n" + daysDiff + "\n\n\n\n" + yearsDiff + "\n\n\n\n");
        if (yearsDiff < 10 || yearsDiff > 100 ){
            return false;
        }
        return true;
    }


}
