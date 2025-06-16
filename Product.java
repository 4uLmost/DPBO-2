public interface Product {
    int getId();
    String getBrand();
    int getPrice();
    int getStock();
    void reduceStock(int quantity);
    void tambahStock(int quantity);
    void setBrand(String brand);
    void setPrice(int price);
}