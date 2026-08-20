package uz.imaan.entity;

public class CartItem {
    private Maxsulot maxsulot;
    private int quantity;

    public CartItem(Maxsulot maxsulot, int quantity) {
        this.maxsulot = maxsulot;
        this.quantity = quantity;
    }

    public Maxsulot getMaxsulot() {
        return maxsulot;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public double jamiNarxi(){
        return maxsulot.getPrice() * quantity;
    }
}













