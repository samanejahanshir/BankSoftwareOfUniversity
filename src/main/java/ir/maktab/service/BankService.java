package ir.maktab.service;

import ir.maktab.exceptions.InvalidInputException;
import ir.maktab.models.*;

import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class BankService {
    public void setListInformation(List<String> events) {
        File infoFile = new File(Bank.PATH_FILE_INFO);
        try (OutputStream infoFileWrite = new FileOutputStream(infoFile, true)) {
            if (infoFile.length() == 0) {
                infoFileWrite.write(Bank.TITr_INPUT.getBytes());
            }
            for (String event : events) {
                try {
                    checkValidation(event);
                    infoFileWrite.write((event + "\n").getBytes());
                } catch (RuntimeException e) {
                    System.out.println(event + " : " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        setListOfPerson(infoFile);
    }

    public void setListOfPerson(File infoFile) {
        try (InputStream infoFileRead = new FileInputStream(infoFile)) {
            Scanner scanner = new Scanner(infoFileRead);
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String[] eventLine = scanner.nextLine().split(" ");
                if (Bank.personSet.contains(new Person(eventLine[3]))) {
                    Person person = Bank.personSet.stream().filter(i -> i.getName().equalsIgnoreCase(eventLine[3])).findAny().get();
                    Bank.personSet.remove(person);
                    person.borrow(new DiscImp(eventLine[4])
                            , new Date(Integer.parseInt(eventLine[2]), Integer.parseInt(eventLine[1]), Integer.parseInt(eventLine[0])));
                    Bank.personSet.add(person);
                } else {
                    Person person = new Person(eventLine[3]);
                    person.borrow(new DiscImp(eventLine[4])
                            , new Date(Integer.parseInt(eventLine[2]), Integer.parseInt(eventLine[1]), Integer.parseInt(eventLine[0])));
                    Bank.personSet.add(person);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void checkValidation(String event) {
        String[] eventSplit = event.split(" ");
        Date.isValidDate(Integer.parseInt(eventSplit[2]), Integer.parseInt(eventSplit[1]), Integer.parseInt(eventSplit[0]));
        checkValidationName(eventSplit[3]);
        checkValidationName(eventSplit[4]);
        if (!Bank.discSet.stream().filter(discImp -> discImp.getName().equalsIgnoreCase(eventSplit[4])).findAny().isPresent()) {
            throw new RuntimeException(eventSplit[4] + " there isn't in list of software !");
        }
    }

    public void checkValidationName(String name) {
        if (name.length() < 2) {
            throw new InvalidInputException("length of name < 2 !! ");
        }
        if (!(Pattern.matches("[a-zA-Z]{2,20}", name))) {
            throw new InvalidInputException("there are a number or unauthorized character ");
        }
    }

    public List<String> getPersonAmountFine() {
        if (Bank.personSet.isEmpty()) {
            setListOfPerson(new File(Bank.PATH_FILE_INFO));
        }
        int totalFine = 0;
        List<String> fineList = new ArrayList<>();
        try {
            for (Person person : Bank.personSet) {
                for (int i = 0; i < person.getBorrowList().size() - 1; i++) {
                    long day = person.getLateDate(person.getBorrowList().get(i).getDiscName(), i);
                    if (day > 0) {
                        totalFine += (day - 7) * Bank.fine;
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
        if (Bank.personSet.isEmpty()) {
            setListOfPerson(new File(Bank.PATH_FILE_INFO));
        }
        List<String> borrowedSoftware = new ArrayList<>();
        long countOneSoftware = 0;
        List<List<Borrow>> listListBorrow = Bank.personSet.stream()
                .map(person -> person.getBorrowList()).collect(Collectors.toList());
        for (DiscImp disc : Bank.discSet) {
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
