public interface Product {
    int getId();
    String getBrand();
    int getPrice();
    int getStock();
    void reduceStock(int quantity);
}