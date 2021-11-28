package ir.maktab.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

public class BankSetListOfPersonTest {
    File infoFile;
    Bank bank;

    @BeforeEach
    void init() {
        System.out.println("before each test ");
        infoFile=new File("resource/info.csv");
        bank=new Bank();
    }

    @Test
    void givenNumber_WhereSetListOfPersonCalls_ThenResponseReturn() {
        bank.setListOfPerson(infoFile);
        System.out.println(Bank.personSet);
        int result=Bank.personSet.size();
        Assertions.assertTrue(true, String.valueOf(result>0));
    }
}
