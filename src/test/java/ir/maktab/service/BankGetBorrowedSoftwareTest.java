package ir.maktab.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BankGetBorrowedSoftwareTest {
    BankService bankService;

    @BeforeEach
    void init() {
        System.out.println("before each test ");
        bankService = new BankService();
    }

    @Test
    void givenNumber_WhereGetLateDateCalls_ThenResponseReturn() {
        for (String s : bankService.getBorrowedSoftware()) {
            System.out.println(s);
        }
    }
}
