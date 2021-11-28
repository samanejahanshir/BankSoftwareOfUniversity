package ir.maktab.view;

import ir.maktab.exceptions.InvalidInputException;
import ir.maktab.models.Bank;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
   static Scanner scanner=new Scanner(System.in);
   static Bank bank=new Bank();
    public static void main(String[] args) {
        System.out.println("---------- Welcome ----------");
        System.out.println("enter user name :");
        String userName=scanner.next();
        System.out.println("enter password :");
        String password=scanner.next();
        if(userName.equalsIgnoreCase("admin") && password.equalsIgnoreCase("admin")){
            showMenu();
        }else {
            System.out.println("you can't entered ! userName or pass is invalid ");
        }
    }

    private static void showMenu() {
        outer:
        while (true) {
            printLine();
            System.out.println("1.enter list information\n2.show The amount of fines\n3.list of borrowed software\n4.exit");
            try {
                switch (scanner.nextInt()) {
                    case 1:
                        enterListInformation();
                        break;
                    case 2:
                        printLine();
                        System.out.println("Fines : ");
                        for (String personFine : bank.getPersonAmountFine()) {
                            System.out.println(personFine);
                        }
                        break ;
                    case 3:
                        printLine();
                        System.out.println("Borrowed software : ");
                        for (String borrowed : bank.getBorrowedSoftware()) {
                            System.out.println(borrowed);
                        }
                        break ;
                    case 4:
                        break outer;
                    default:
                        throw new InvalidInputException("enter 1 - 4 please ");

                }
            } catch (InvalidInputException | NumberFormatException | InputMismatchException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void enterListInformation() {
        try{
            List<String> events=new ArrayList<>();
            System.out.println("enter count of line and amount of fine : count fine");
            scanner.nextLine();
            String inputLine1=scanner.nextLine();
            int countEvent=Integer.parseInt(inputLine1.split(" ")[0]);
            int amountFine=Integer.parseInt(inputLine1.split(" ")[1]);
            for(int i=0;i<countEvent;i++){
                System.out.println("enter : day month year personName softwareName");
                events.add(scanner.nextLine());
            }
            bank.fine=amountFine;
            bank.setListInformation(events);
        }catch (InputMismatchException | NumberFormatException e){
            System.out.println(e.getMessage());
        }

    }
    public  static void printLine(){
        System.out.println("-----------------------------");
    }
}
