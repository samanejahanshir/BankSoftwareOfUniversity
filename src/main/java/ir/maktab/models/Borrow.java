package ir.maktab.models;

import java.util.Objects;

public class Borrow {
    private  String discName;
    private  Date date;

    public Borrow(String disc, Date date) {
        this.discName = disc;
        this.date = date;
    }

    public String getDiscName() {
        return discName;
    }

    public Date getDate() {
        return date;
    }
    public  boolean isLate(Date deliveryDate){
        //////TODO
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Borrow borrow = (Borrow) o;
        return discName.equals(borrow.discName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(discName);
    }
}
