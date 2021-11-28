package ir.maktab.models;

import ir.maktab.exceptions.InvalidInputException;

import java.io.*;
import java.text.ParseException;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Bank {
    public static Set<Person> personSet = new HashSet<>();
    public static Set<Disc> discSet = new HashSet<>();
    public int fine = 200;
    static final String PATH_FILE_INFO = "resource/info.csv";
    static final String TITr_INPUT = "day month year personName softwareName\n";

    public Bank() {
        discSet = Set.of(new Disc("java")
                , new Disc("office")
                , new Disc("photoshop")
                , new Disc("powerpoint"));
    }

    public void setListInformation(List<String> events) {
        File infoFile = new File(PATH_FILE_INFO);
        try (OutputStream infoFileWrite = new FileOutputStream(infoFile, true)) {
            if (infoFile.length() == 0) {
                infoFileWrite.write(TITr_INPUT.getBytes());
            }
            for (String event : events) {
                try {
                    checkValidation(event);
                    infoFileWrite.write((event+"\n").getBytes());
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
                    Person person = new Person(eventLine[3]);
                    person.borrow(new Disc(eventLine[4])
                            , new Date(Integer.parseInt(eventLine[2]), Integer.parseInt(eventLine[1]), Integer.parseInt(eventLine[0])));
                    personSet.add(person);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public List<String> getPersonAmountFine() {
        if (personSet.isEmpty()) {
            setListOfPerson(new File(PATH_FILE_INFO));
        }
        int totalFine = 0;
        List<String> fineList = new ArrayList<>();
        try {
            for (Person person : personSet) {
                for (int i = 0; i < person.getBorrowList().size() - 1; i++) {
                    long day = person.getLateDate(person.getBorrowList().get(i).getDiscName(), i);
                    if (day > 0) {
                        totalFine += (day - 7) * fine;
                    }
                }
                fineList.add(person.getName() + " : " + totalFine);
                totalFine = 0;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return fineList;
    }

    public List<String> getBorrowedSoftware() {
        if (personSet.isEmpty()) {
            setListOfPerson(new File(PATH_FILE_INFO));
        }
        List<String> borrowedSoftware = new ArrayList<>();
        long countOneSoftware = 0;
        List<List<Borrow>> listListBorrow = personSet.stream()
                .map(person -> person.getBorrowList()).collect(Collectors.toList());
        for (Disc disc : discSet) {
            for (List<Borrow> borrows : listListBorrow) {
                for (Borrow borrow : borrows) {
                    if (borrow.getDiscName().equalsIgnoreCase(disc.getName())) {
                        countOneSoftware += 1;
                    }
                }
            }
            if (countOneSoftware % 2 != 0) {
                borrowedSoftware.add(disc.getName());
            }
            countOneSoftware = 0;
        }
        return borrowedSoftware;
    }
}
