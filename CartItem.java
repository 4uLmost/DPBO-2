public class CartItem {
    private Object product; // Bisa berupa Galon atau Refill
    private int quantity;
    private int unitPrice;
    private String description;

    public CartItem(Object product, int quantity, int unitPrice, String description) {
        this.product = product;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.description = description;
    }

    public Object getProduct() {
        return product;
    }

    public String getBrand() {
        if (product instanceof Galon) {
            return ((Galon) product).getBrand();
        } else if (product instanceof Refill) {
            return ((Refill) product).getBrand();
        }
        return "Unknown";
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public String getDescription() {
        return description;
    }
    
    public int getTotalPrice() {
        return unitPrice * quantity;
    }
    
    // Metode untuk menambah jumlah
    public void addQuantity(int amount) {
        this.quantity += amount;
    }
}