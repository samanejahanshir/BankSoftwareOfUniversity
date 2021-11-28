package ir.maktab.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BankGetPersonAmountFineTest {
    Bank bank;

    @BeforeEach
    void init() {
        System.out.println("before each test ");
        bank = new Bank();
    }

    @Test
    void givenNumber_WhereGetLateDateCalls_ThenResponseReturn() {
        for (String s : bank.getPersonAmountFine()) {
            System.out.println(s);
        }
    }
}