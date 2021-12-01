package ir.maktab.models;

import java.util.HashSet;
import java.util.Set;

public class Bank {
    public static Set<Person> personSet = new HashSet<>();
    public static Set<Disc> discSet;
    public static int fine = 200;
    public static final String PATH_FILE_INFO = "resource/info.csv";
    public static final String TITr_INPUT = "day month year personName softwareName\n";

    static {
        discSet = Set.of(new Disc("java")
                , new Disc("office")
                , new Disc("photoshop")
                , new Disc("powerpoint")
                , new Disc("whats app")
                , new Disc("maya"));
    }
}
