// Pesanan.java (File Baru)
import java.util.ArrayList;

public class Pesanan {
    private int id;
    private User user; // Menyimpan info user yang memesan
    private ArrayList<CartItem> items; // Menyimpan item yang dibeli
    private int totalHarga;
    private String metodePembayaran;
    private String status;

    public Pesanan(int id, User user, ArrayList<CartItem> items, int totalHarga, String metodePembayaran) {
        this.id = id;
        this.user = user;
        this.items = items;
        this.totalHarga = totalHarga;
        this.metodePembayaran = metodePembayaran;
        this.status = "Pesanan sedang di Proses"; // Status default
    }

    // Getters untuk mengakses data
    public int getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }
    
    // Setter untuk mengubah status
    public void setStatus(String status) {
        this.status = status;
    }

    public User getUser() {
    return user;
    }

    public int getTotalHarga() {
        return totalHarga;
    }

    // Metode untuk menampilkan info pesanan dengan rapi
    public void printInfo() {
        System.out.println("--------------------------------------------------");
        System.out.println("ID Pesanan       : " + this.id);
        System.out.println("Nama Pemesan     : " + this.user.getName());
        System.out.println("Metode Pembayaran: " + this.metodePembayaran);
        System.out.println("Total Harga      : Rp" + this.totalHarga);
        System.out.println("Status           : " + this.status);
        System.out.println("Detail Pesanan:");
        for (CartItem item : items) {
            System.out.printf("  - %s (x%d) = Rp%d\n", item.getBrand(), item.getQuantity(), item.getTotalPrice());
        }
        System.out.println("--------------------------------------------------");
    }
}