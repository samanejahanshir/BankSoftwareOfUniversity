package ir.maktab.models;

import java.util.Objects;

public  class DiscImp implements  Disc {
    private String name;
    private TypeDisc type;

    public DiscImp(String name) {
        this.name = name;
    }
    public DiscImp(String name,TypeDisc type){
        this.name=name;
        this.type=type;
    }

    public String getName() {
        return name;
    }

    public TypeDisc getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiscImp disc = (DiscImp) o;
        return name.equals(disc.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Disc{" +
                "name='" + name + '\'' +
                ", type=" + type +
                '}';
    }
}
