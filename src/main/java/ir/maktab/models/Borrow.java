package ir.maktab.models;

import java.text.ParseException;
import java.util.Objects;

public class Borrow {
    private String discName;
    private Date date;

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

    public boolean isLate(Date deliveryDate) throws ParseException {
        if (Date.differenceDates(date, deliveryDate) >= 7) {
            return true;
        }
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

    @Override
    public String toString() {
        return "Borrow{" +
                "discName='" + discName + '\'' +
                ", date=" + date +
                '}';
    }
}
