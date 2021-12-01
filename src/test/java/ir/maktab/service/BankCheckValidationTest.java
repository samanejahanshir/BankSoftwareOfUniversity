package ir.maktab.service;

import ir.maktab.exceptions.InvalidInputException;
import ir.maktab.models.Bank;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class BankCheckValidationTest {
    BankService bankService;

    @BeforeEach
    void init() {
        System.out.println("before each test ");
        bankService = new BankService();
    }

    @ParameterizedTest
    @CsvSource({"1 2 56 sahar java", "3 5 78 reza office"})
    void givenNumber_WhereCheckValidationCalls_ThenResponseReturn(String name) {
        bankService.checkValidation(name);
    }

    @ParameterizedTest
    @CsvSource({"1 3 45 ss2 java", "1 2 56 rez@s ja3"})
    void givenNumber_WhereCheckValidationCalls_ThenResponseReturnException(String name) {
        InvalidInputException exp = Assertions.assertThrows(InvalidInputException.class, () ->
                bankService.checkValidation(name));
        System.out.println(exp.getMessage());
        Assertions.assertEquals("there are a number or unauthorized character ", exp.getMessage());
    }

    @ParameterizedTest
    @CsvSource({"1 3 67 s java", "4 6 89 r j"})
    void givenNumber_WhereCheckValidationCalls_ThenResponseLengthReturnException(String name) {
        InvalidInputException exp = Assertions.assertThrows(InvalidInputException.class, () ->
                bankService.checkValidation(name));
        System.out.println(exp.getMessage());
        Assertions.assertEquals("length of name < 2 !! ", exp.getMessage());
    }

}
