public class Pembayaran {
    private int price;
    private int id;
    private String metodePembayaran;
    private String status;

    public Pembayaran(int price, String metodePembayaran, String status, int id) {
        this.price = price;
        this.metodePembayaran = metodePembayaran;
        this.status = status;
        this.id = id;
    }

    public int getPrice() {
        return price;
    }
    public String getStatus() {
        return status;
    }
    public String getMetodePembayaran() {
        return metodePembayaran;
    }
    public void prosesPembayaran() {
        System.out.println("Menunggu pembayaran dari " + this.id);
    }
    public void printInfo() {
        System.out.println("Pembayaran id: " + this.id + " sudah selesai");
    }
}


