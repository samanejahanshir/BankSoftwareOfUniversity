package ir.maktab.view;

import ir.maktab.exceptions.InvalidInputException;
import ir.maktab.models.Bank;

import java.util.InputMismatchException;
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
            System.out.println("1.enter list information\n2.show The amount of fines\n3.list of borrowed software\n4.exit");
            try {
                switch (scanner.nextInt()) {
                    case 1:
                        bank.setListInformation();
                        break;
                    case 2:
                        bank.getPersonAmountFine();
                        break ;
                    case 3:
                        bank.getBorrowedSoftware();
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
}
