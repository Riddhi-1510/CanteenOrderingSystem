public class EachItemData {
    private int id;
    private String item_name;
    private int qty;
    private double price;
    
    @Override
    public String toString() {
        return "EachItemData [id=" + id + ", item_name=" + item_name + ", qty=" + qty + ", price=" + price + "]";
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getItem_name() {
        return item_name;
    }
    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }
    public int getQty() {
        return qty;
    }
    public void setQty(int qty) {
        this.qty = qty;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    
}
