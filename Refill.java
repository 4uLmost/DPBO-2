// Refill.java
public class Refill implements Product {
	private int id;
	private double volume;
	private String brand;
	private int refillPrice;
	private Galon galon; 
    private int stock; // Stok mandiri untuk refill seperti Air Depot

	// Konstruktor diubah untuk menerima stok mandiri
	public Refill(int id, double volume, String brand, Galon galon, int refillPrice, int initialStock) {
		this.id = id;
		this.volume = volume;
		this.brand = brand;
		this.galon = galon;
		this.refillPrice = refillPrice;
        // Jika tidak terikat galon, gunakan stok mandiri
		this.stock = (galon == null) ? initialStock : 0;
	}

	@Override
	public int getId() { return id; }
	public double getVolume() { return volume; }
	@Override
	public String getBrand() { return brand; }

	@Override
	public int getStock() {
		return (galon != null) ? galon.getStock() : this.stock;
	}

	@Override
	public void reduceStock(int amount) {
		if (galon != null) {
			galon.reduceStock(amount);
		} else {
            if(this.stock >= amount) {
			    this.stock -= amount;
            }
		}
	}

    @Override
    public void tambahStock(int amount) {
        if(galon != null) {
            galon.tambahStock(amount);
        } else {
            this.stock += amount;
        }
    }

	@Override
	public int getPrice() {
		return refillPrice;
	}

    @Override
	public void setPrice(int hargaBaru) {
        this.refillPrice = hargaBaru;
    }

    @Override
    public void setBrand(String brand) {
        this.brand = brand;
    }

	public Galon getGalon() {
		return galon;
	}
}