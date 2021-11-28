package ir.maktab.models;

import java.util.*;

public class Person {
    private  String name;
    private List<Borrow> borrowSet=new ArrayList<>();

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public  int getLateDate(){
        ////////////TODO
        return  0;
    }
    public  void borrow(Disc disc,Date date){
        Borrow borrow=new Borrow(disc.getName(),date);
        borrowSet.add(borrow);

    }
    public  void deliver(Disc disc,Date date){

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
                '}'+"\n";
    }
}
