package uz.imaan.entity;

public class Firma {
    private int id;
    private String name;
    private String logoFileId;

    public Firma(int id, String name, String logoFileId) {
        this.id = id;
        this.name = name;
        this.logoFileId = logoFileId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLogoFileId() {
        return logoFileId;
    }

    @Override
    public String toString() {
        return name;
    }
}

















