package ir.maktab.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

public class PersonGetLateDateTest {
    Person person;
    @BeforeEach
    void init() {
        System.out.println("before each test ");
        person=new Person("sara");
    }
    @Test
    void givenNumber_WhereGetLateDateCalls_ThenResponseReturn() throws ParseException {
       person.borrow(new DiscImp("java"),new Date(99,7,6));
        person.borrow(new DiscImp("java"),new Date(99,7,17));
        long day=person.getLateDate("java",0);
        Assertions.assertEquals(11, day);



    }
}
