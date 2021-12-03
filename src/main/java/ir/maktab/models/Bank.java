package ir.maktab.models;

import java.util.HashSet;
import java.util.Set;

public class Bank {
    public static Set<Person> personSet = new HashSet<>();
    public static Set<DiscImp> discSet;
    public static int fine = 200;
    public static final String PATH_FILE_INFO = "resource/info.csv";
    public static final String TITr_INPUT = "day month year personName softwareName\n";

    static {
       /* discSet = Set.of(new DiscImp("java")
                , new DiscImp("office")
                , new DiscImp("photoshop")
                , new DiscImp("powerpoint")
                , new DiscImp("whats app")
                , new DiscImp("maya"));*/
        discSet = Set.of(DiscFactory.getDiscByType(TypeDisc.CD,"java")
                ,DiscFactory.getDiscByType(TypeDisc.CD,"office")
                , DiscFactory.getDiscByType(TypeDisc.DVD,"photoshop")
                , DiscFactory.getDiscByType(TypeDisc.DVD,"powerpoint")
                , DiscFactory.getDiscByType(TypeDisc.CD,"whats app")
                ,DiscFactory.getDiscByType(TypeDisc.DVD,"maya"));
    }
}
