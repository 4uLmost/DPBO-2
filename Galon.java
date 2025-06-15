public class Galon {
	private int id;
	private double volume;
	private String brand;
	private int price;
	private int stock;
	
	
	public Galon(int id, double volume, String brand, int price, int stock) {
		super();
		this.id = id;
		this.volume = volume;
		this.brand = brand;
		this.price = price;
		this.stock = stock;
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



	public int getPrice() {
		return price;
	}


	public int getStock() {
		return stock;
	}
	
	public void reduceStock( int qty) {
		if (stock >= qty) {
			stock -= qty;
		}
	}
	
	public void tambahStock(int jumlah) {
        this.stock += jumlah;
    }
	
	public void printInfo() {
		 System.out.printf("| %-3d | %-10s | %-6.1fL | Rp%-8d | %-4d |\n",
	                id, brand, volume, price, stock);
	}
	
	// Tambahkan setter untuk price
    public void setPrice(int hargaBaru) {
        this.price = hargaBaru;
    }
}
