public class Refill {
	private int id;
	private double volume;
	private String brand;
	private int stock;
	private int refillPrice;
	private Galon galon; // referensi ke galon utama (bisa null jika tidak ada galon terkait)

	public Refill(int id, double volume, String brand, Galon galon, int refillPrice) {
		this.id = id;
		this.volume = volume;
		this.brand = brand;
		this.galon = galon;
		this.refillPrice = refillPrice;
		// Sinkronisasi stok: jika ada galon terkait, gunakan stok galon, jika tidak, stok sendiri
		this.stock = (galon != null) ? galon.getStock() : 0;
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
		// Sinkronisasi stok: jika ada galon terkait, gunakan stok galon
		return (galon != null) ? galon.getStock() : stock;
	}

	public void reduceStock(int amount) {
		if (galon != null) {
			galon.reduceStock(amount);
		} else {
			stock -= amount;
		}
	}

	public int getRefillPrice() {
		return refillPrice;
	}

	public void setRefillPrice(int hargaBaru) {
        this.refillPrice = hargaBaru;
    }

	public Galon getGalon() {
		return galon;
	}

	public void printRefillInfo() {
		System.out.printf("| %-3d | %-20s | %-7.1f | Rp%-10d | %-5d |\n",
				id, brand, volume, refillPrice, getStock());
	}
}
