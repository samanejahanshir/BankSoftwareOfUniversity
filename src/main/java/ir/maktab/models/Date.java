package ir.maktab.models;

public class Date {
    private  int year;
    private  int month;
    private  int day;

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
    public boolean isValidDate(int year, int month, int day) {
        if (year > 0 && year <= 9999 && month > 0 && month <= 12) {
            if (month > 0 && month < 7 && day > 0 && day <=31) {
                return true;
            } else if (month >6 && month < 12 && day > 0 && day <= 30) {
                return true;
            } else if (month == 12 && day > 0 && day <= 29) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return this.getDay() + " " + this.getMonth() + " " + this.getYear();
    }
}
