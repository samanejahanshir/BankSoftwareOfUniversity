package ir.maktab.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

public class DateDifferenceDatesTest {
   @Test
    void givenNumber_WhereDifferenceDatesCalls_ThenResponseReturn() throws ParseException {
        Date date1=new Date(98,7,7);
        Date date2=new Date(98,7,15);
        long result = Date.differenceDates(date1,date2);
        Assertions.assertEquals(8, result);
       Date date3=new Date(98,7,10);
       Date date4=new Date(98,7,23);
       long result2 = Date.differenceDates(date3,date4);
       Assertions.assertEquals(13, result2);
    }
}
