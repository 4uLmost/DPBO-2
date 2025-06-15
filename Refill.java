public class Refill {
	private int id;
	private double volume;
	private String brand;
	private int refillPrice;
	private int stock;

	public Refill(int id, double volume, String brand, int stock, int refillPrice) {
		this.id = id;
		this.volume = volume;
		this.brand = brand;
		this.stock = stock;
		this.refillPrice = refillPrice;
	}

	public int getId() {
		return id;
	}

	public double getVolume() {
		return volume;
	}

	public String getBrand() {
		return brand;
	}

	public int getStock() {
		return stock;
	}

	public int getRefillPrice() {
		return refillPrice;
	}

	public void reduceStock(int qty) {
		if (stock >= qty) {
			stock -= qty;
		}
	}

	public void printRefillInfo() {
		System.out.printf("| %-3d | %-20s | %-7.1f | Rp%-10d | %-5d |\n",
				id, brand, volume, refillPrice, stock);
	}
}
