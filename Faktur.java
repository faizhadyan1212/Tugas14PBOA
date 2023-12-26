public class Faktur extends Barang implements TotalBayar{
    private String noFaktur;
    private String customerName;
    private String NoHP;
    private String address;

    public Faktur(String noFaktur, String customerName, String itemName, String itemCode, double price, int quantity,String NoHP,String address) {
        super(itemName, itemCode, price, quantity);
        this.noFaktur = noFaktur;
        this.customerName = customerName;
        this.NoHP = NoHP;
        this.address = address;
    }

    public String getnoFaktur() {
        return noFaktur;
    }

    public String getCustomerName() {
        return customerName;
    }

     public String getnoHP() {
        return NoHP;
    }

    public String getaddress() {
        return address;
    }
    
    @Override
    public double calculateTotal() {
        return getPrice() * getQuantity();
    }
}