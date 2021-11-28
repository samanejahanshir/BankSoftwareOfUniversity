package ir.maktab.models;

import ir.maktab.exceptions.InvalidInputException;

public class Date {
    private  int year;
    private  int month;
    private  int day;

    public Date(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
    public static boolean isValidDate(int year, int month, int day) {
        if (year > 0 && year <= 9999 && month > 0 && month <= 12) {
            if (month > 0 && month < 7 && day > 0 && day <=31) {
                return true;
            } else if (month >6 && month < 12 && day > 0 && day <= 30) {
                return true;
            } else if (month == 12 && day > 0 && day <= 29) {
                return true;
            } else {
                throw  new InvalidInputException("format date is invalid");
            }
        }
        throw  new InvalidInputException("format date is invalid");

    }

    @Override
    public String toString() {
        return this.getYear() + "/" + this.getMonth() + "/" + this.getDay();
    }
}
