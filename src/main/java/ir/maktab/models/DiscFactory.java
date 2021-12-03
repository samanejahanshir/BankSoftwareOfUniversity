package ir.maktab.models;

public class DiscFactory {
    public static DiscImp getDiscByType(TypeDisc typeDisc, String name){
        DiscImp disc;
        switch (typeDisc){
            case CD:
                disc=new CD(name);
                break;
            case DVD:
                disc=new DVD(name);
                break;
            default:
                throw new RuntimeException("this type disc not exist ! ");
        }
        return  disc;
    }
}
