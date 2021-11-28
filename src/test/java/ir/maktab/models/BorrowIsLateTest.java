package ir.maktab.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

public class BorrowIsLateTest {
    Borrow borrow;
    @BeforeEach
    void init() {
        System.out.println("before each test ");
        borrow=new Borrow("java",new Date(99,8,8));
    }
    @Test
    void givenNumber_WhereIsLateCalls_ThenResponseTrueReturn() throws ParseException {
       Date date=new Date(99,8,18);
       boolean result=borrow.isLate(date);
        Assertions.assertTrue(result);
    }

    @Test
    void givenNumber_WhereIsLateCalls_ThenResponseFalseReturn() throws ParseException {
        Date date=new Date(99,8,10);
        boolean result=borrow.isLate(date);
        Assertions.assertFalse(result);
    }
}
