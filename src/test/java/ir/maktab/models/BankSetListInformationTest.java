package ir.maktab.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

public class BankSetListInformationTest {
    File infoFile;
    Bank bank;

    @BeforeEach
    void init() {
        System.out.println("before each test ");
        infoFile = new File("resource/info.csv");
        bank = new Bank();
    }

    @Test
    void givenNumber_WhereSetListInformationCalls_ThenResponseReturn() {
        List<String> list = List.of("1 3 98 sara java", "2 5 65 reza office", "5 3 98 sara java");
        bank.setListInformation(list);
        long result = infoFile.length();
        Assertions.assertTrue(true, String.valueOf(result > 0));
    }

}
