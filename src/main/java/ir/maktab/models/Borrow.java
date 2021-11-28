package ir.maktab.models;

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

}
