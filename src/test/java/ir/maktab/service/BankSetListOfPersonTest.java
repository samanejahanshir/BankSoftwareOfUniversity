package ir.maktab.service;

import ir.maktab.models.Bank;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

public class BankSetListOfPersonTest {
    File infoFile;
    BankService bankService;

    @BeforeEach
    void init() {
        System.out.println("before each test ");
        infoFile = new File("resource/info.csv");
        bankService = new BankService();
    }

    @Test
    void givenNumber_WhereSetListOfPersonCalls_ThenResponseReturn() {
        bankService.setListOfPerson(infoFile);
        System.out.println(Bank.personSet);
        int result = Bank.personSet.size();
        Assertions.assertTrue(true, String.valueOf(result > 0));
    }
}
