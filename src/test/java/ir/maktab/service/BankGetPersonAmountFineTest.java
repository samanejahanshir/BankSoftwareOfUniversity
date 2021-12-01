package ir.maktab.service;

import ir.maktab.models.Bank;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BankGetPersonAmountFineTest {
    BankService bankService;

    @BeforeEach
    void init() {
        System.out.println("before each test ");
        bankService = new BankService();
    }

    @Test
    void givenNumber_WhereGetLateDateCalls_ThenResponseReturn() {
        for (String s : bankService.getPersonAmountFine()) {
            System.out.println(s);
        }
    }
}