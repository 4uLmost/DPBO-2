public class Refill implements Product {
	private int id;
	private double volume;
	private String brand;
	private int refillPrice;
	private Galon galon; 

	public Refill(int id, double volume, String brand, Galon galon, int refillPrice) {
		this.id = id;
		this.volume = volume;
		this.brand = brand;
		this.galon = galon;
		this.refillPrice = refillPrice;
	}

	@Override
	public int getId() {
		return id;
	}

	public double getVolume() {
		return volume;
	}

	@Override
	public String getBrand() {
		return brand;
	}

	@Override
	public int getStock() {
		return (galon != null) ? galon.getStock() : 0;
	}

	@Override
	public void reduceStock(int amount) {
		if (galon != null) {
			galon.reduceStock(amount);
		}
	}

	@Override
	public int getPrice() {
		return refillPrice;
	}

	public void setPrice(int hargaBaru) {
        this.refillPrice = hargaBaru;
    }

	public Galon getGalon() {
		return galon;
	}

	public void printRefillInfo() {
		System.out.printf("| %-3d | %-20s | %-7.1f | Rp%-10d | %-5d |\n",
				id, brand, volume, getPrice(), getStock());
	}
}