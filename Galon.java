// Galon.java
public class Galon implements Product {
	private int id;
	private double volume;
	private String brand;
	private int price;
	private int stock;
	
	public Galon(int id, double volume, String brand, int price, int stock) {
		this.id = id;
		this.volume = volume;
		this.brand = brand;
		this.price = price;
		this.stock = stock;
	}
	
	@Override
	public int getId() { return id; }
	public double getVolume() { return volume; }
	@Override
	public String getBrand() { return brand; }
	@Override
	public int getPrice() { return price; }
	@Override
	public int getStock() { return stock; }
	
	@Override
	public void reduceStock(int qty) {
		if (this.stock >= qty) {
			this.stock -= qty;
		}
	}
	
    @Override
	public void tambahStock(int jumlah) {
        this.stock += jumlah;
    }
	
    @Override
    public void setPrice(int hargaBaru) {
        this.price = hargaBaru;
    }

    @Override
    public void setBrand(String brand) {
        this.brand = brand;
    }
}