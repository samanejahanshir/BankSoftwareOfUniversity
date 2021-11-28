package ir.maktab.models;

import ir.maktab.exceptions.InvalidInputException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

public class Bank {
    public static Set<Person> personSet = new HashSet<>();
    public static Set<Disc> discSet = new HashSet<>();
    static final String PATH_FILE_INFO="resource/info.csv";
//static final String ORDER_INPUTS_INFO_FILE="day month year personName softwareName";
    // public static Map<Person, HashSet<Borrow>> borrowMap = new HashMap<>();
    public void setListInformation(List<String> events){
        File infoFile=new File(PATH_FILE_INFO);
        try(OutputStream infoWrite=new FileOutputStream(infoFile,true)) {
            for (String event : events) {
                try {
                    checkValidation(event);
                    infoWrite.write(("\n" + event).getBytes());
                }catch (InvalidInputException e){
                    System.out.println(event+" : "+ e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println( e.getMessage());
        }
    }

    private void checkValidation(String event) {
                String[] eventSplit = event.split(" ");
                Date.isValidDate(Integer.parseInt(eventSplit[2]), Integer.parseInt(eventSplit[1]), Integer.parseInt(eventSplit[0]));
                checkValidationName(eventSplit[3]);
                checkValidationName(eventSplit[4]);
    }

    private void checkValidationName(String name) {
            if (name.length() < 2 ) {
                throw new InvalidInputException("length of name < 2 !! ");
            }
            if (!(Pattern.matches("[a-zA-Z]{2,20}",name))){
                throw new InvalidInputException("there are a number or unauthorized character ");
            }
    }

    public void getPersonAmountFine(){

    }
    public void getBorrowedSoftware(){

    }


}
