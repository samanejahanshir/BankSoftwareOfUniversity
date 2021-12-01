package ir.maktab.models;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Person {
    private String name;
    private List<Borrow> borrowSet = new ArrayList<>();

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public long getLateDate(String name, int index) throws ParseException {
        long day = 0;
        for (int j = index + 1; j < borrowSet.size(); j++) {
            if (name.equalsIgnoreCase(borrowSet.get(j).getDiscName())) {
                if (borrowSet.get(index).isLate(borrowSet.get(j).getDate())) {
                    day = Date.differenceDates(borrowSet.get(index).getDate(), borrowSet.get(j).getDate());
                }
            }
        }
        return day;
    }

    public List<Borrow> getBorrowList() {
        return borrowSet;
    }

    public void borrow(Disc disc, Date date) {
        Borrow borrow = new Borrow(disc.getName(), date);
        borrowSet.add(borrow);

    }

    public void deliver(Disc disc, Date date) {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return name.equals(person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", borrowSet=" + borrowSet +
                '}' + "\n";
    }
}
