public class Barang {
    private String itemName;
    private String itemCode;
    private double price;
    private int quantity;

    public Barang(String itemName, String itemCode2, double price, int quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getItemName() {
        return itemName;
    }

     public String getitemCode() {
        return itemCode;
    }

}