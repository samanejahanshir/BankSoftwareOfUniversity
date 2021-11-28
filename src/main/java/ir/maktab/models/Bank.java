package ir.maktab.models;

import ir.maktab.exceptions.InvalidInputException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;

public class Bank {
    public static Set<Person> personSet = new HashSet<>();
    public static Set<Disc> discSet = new HashSet<>();

    public Bank() {
        discSet = Set.of(new Disc("java")
                , new Disc("office")
                , new Disc("photoshop")
                , new Disc("powerpoint"));
    }

    static final String PATH_FILE_INFO = "resource/info.csv";

    //static final String ORDER_INPUTS_INFO_FILE="day month year personName softwareName";
    // public static Map<Person, HashSet<Borrow>> borrowMap = new HashMap<>();
    public void setListInformation(List<String> events) {
        File infoFile = new File(PATH_FILE_INFO);
        try (OutputStream infoFileWrite = new FileOutputStream(infoFile, true)) {
            for (String event : events) {
                try {
                    checkValidation(event);
                    infoFileWrite.write(("\n" + event).getBytes());
                } catch (RuntimeException e) {
                    System.out.println(event + " : " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        setListOfPerson(infoFile);
    }

    private void setListOfPerson(File infoFile) {
        try (InputStream infoFileRead = new FileInputStream(infoFile)) {
            Scanner scanner = new Scanner(infoFileRead);
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String[] eventLine = scanner.nextLine().split(" ");
                if (personSet.contains(new Person(eventLine[3]))) {
                    Person person = personSet.stream().filter(i -> i.getName().equalsIgnoreCase(eventLine[3])).findAny().get();
                    personSet.remove(person);
                    person.borrow(new Disc(eventLine[4])
                            , new Date(Integer.parseInt(eventLine[2]), Integer.parseInt(eventLine[1]), Integer.parseInt(eventLine[0])));
                    personSet.add(person);
                } else {
                    Person person=new Person(eventLine[3]);
                    person.borrow(new Disc(eventLine[4])
                            , new Date(Integer.parseInt(eventLine[2]), Integer.parseInt(eventLine[1]), Integer.parseInt(eventLine[0])));
                    personSet.add(person);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(personSet);
    }

    private void checkValidation(String event) {
        String[] eventSplit = event.split(" ");
        Date.isValidDate(Integer.parseInt(eventSplit[2]), Integer.parseInt(eventSplit[1]), Integer.parseInt(eventSplit[0]));
        checkValidationName(eventSplit[3]);
        checkValidationName(eventSplit[4]);
        if (!discSet.contains(new Disc(eventSplit[4]))) {
            throw new RuntimeException(eventSplit[4] + " there isn't in list of software !");
        }
    }

    private void checkValidationName(String name) {
        if (name.length() < 2) {
            throw new InvalidInputException("length of name < 2 !! ");
        }
        if (!(Pattern.matches("[a-zA-Z]{2,20}", name))) {
            throw new InvalidInputException("there are a number or unauthorized character ");
        }
    }

    public void getPersonAmountFine() {

    }

    public void getBorrowedSoftware() {


    }


}
