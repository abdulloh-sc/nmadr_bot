package uz.imaan.entity;

public class Maxsulot {
    private int id;
    private int firmaId;
    private String name;
    private double price;
    private String photoFileId;

    public Maxsulot(int id, int firmaId, String name, double price, String photoFileId) {
        this.id = id;
        this.firmaId = firmaId;
        this.name = name;
        this.price = price;
        this.photoFileId = photoFileId;
    }

    public int getId() {
        return id;
    }

    public int getFirmaId() {
        return firmaId;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getPhotoFileId() {
        return photoFileId;
    }

}
















