package ir.maktab.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class DateIsValidDateTest {

    @ParameterizedTest
    @CsvSource({"89,10,2", "98,3,14"})
    void givenNumber_WhereIsValidDateCalls_ThenResponseReturn(int year, int month, int day) {
        boolean result = Date.isValidDate(year,month,day);
        Assertions.assertEquals(true, result);
    }
    @ParameterizedTest
    @CsvSource({"89,13,2", "98,3,33"})
    void givenNumber_WhereIsValidDateCalls_ThenResponseReturnException(int year, int month, int day) {
        RuntimeException exp=Assertions.assertThrows(RuntimeException.class,()->
                Date.isValidDate(year,month,day));
        System.out.println(exp.getMessage());
        Assertions.assertEquals("format date is invalid",exp.getMessage());
    }
}
