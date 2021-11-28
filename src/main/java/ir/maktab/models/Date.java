package ir.maktab.models;

import ir.maktab.exceptions.InvalidInputException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

public class Date {
    private int year;
    private int month;
    private int day;

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
            if (month > 0 && month < 7 && day > 0 && day <= 31) {
                return true;
            } else if (month > 6 && month < 12 && day > 0 && day <= 30) {
                return true;
            } else if (month == 12 && day > 0 && day <= 29) {
                return true;
            } else {
                throw new InvalidInputException("format date is invalid");
            }
        }
        throw new InvalidInputException("format date is invalid");
    }

    public static long differenceDates(Date date1, Date date2) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yy/M/d");
        java.util.Date startDate = dateFormat.parse(date1.toString());
        java.util.Date endDate = dateFormat.parse(date2.toString());
        long difference_In_Time = endDate.getTime() - startDate.getTime();
        return TimeUnit.MILLISECONDS.toDays(difference_In_Time) % 365;

    }

    @Override
    public String toString() {
        return this.getYear() + "/" + this.getMonth() + "/" + this.getDay();
    }
}
