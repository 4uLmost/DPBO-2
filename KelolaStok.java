import java.util.ArrayList;

public class KelolaStok {
    private ArrayList<Product> products;

    // Konstruktor diubah untuk menerima satu ArrayList<Product>
    public KelolaStok(ArrayList<Product> products) {
        this.products = products;
    }

    // Menampilkan stok, dipisahkan berdasarkan tipe produk
    public void tampilkanStokGalon() {
        System.out.println("\n=== Stok Galon ===");
        System.out.printf("| %-3s | %-10s | %-18s | %-5s |\n",
                "ID", "Brand", "Harga dengan Galon", "Stok");
        System.out.println("-----------------------------------------------------");
        for (Product p : products) {
            if (p instanceof Galon) {
                System.out.printf("| %-3d | %-10s | Rp%-16d | %-5d |\n",
                        p.getId(), p.getBrand(), p.getPrice(), p.getStock());
            }
        }
    }
    
    // Metode ini sekarang menjadi tidak relevan karena stok refill menyatu dengan galon,
    // tapi kita biarkan kosong agar tidak error jika dipanggil
    public void tampilkanStokRefill() {
        // Tidak perlu lagi karena stok refill tergantung galon utama
        System.out.println("Info stok refill dapat dilihat pada stok galon.");
    }

    // Logika diubah untuk mencari dari satu list product
    public boolean tambahStokGalon(int id, int jumlah) {
        for (Product p : products) {
            if (p instanceof Galon && p.getId() == id) {
                p.reduceStock(-jumlah); // Logika reduceStock(-x) akan menambah stok
                return true;
            }
        }
        return false;
    }

    public boolean kurangiStokGalon(int id, int jumlah) {
        for (Product p : products) {
            if (p instanceof Galon && p.getId() == id && p.getStock() >= jumlah) {
                p.reduceStock(jumlah);
                return true;
            }
        }
        return false;
    }

    public boolean ubahHargaGalon(int id, int hargaBaru) {
        for (Product p : products) {
            if (p instanceof Galon && p.getId() == id) {
                ((Galon) p).setPrice(hargaBaru); // Perlu cast untuk akses setPrice di Galon
                return true;
            }
        }
        return false;
    }
    
    // Metode untuk mengelola Reward (tidak perlu diubah)
    public void tampilkanReward(Reward[] rewards) {
        System.out.println("\n=== Daftar Reward ===");
        System.out.printf("| %-5s | %-20s | %-8s | %-30s |\n", "ID", "Nama", "Poin", "Deskripsi");
        System.out.println("--------------------------------------------------------------------------");
        for (Reward r : rewards) {
            System.out.printf("| %-5d | %-20s | %-8d | %-30s |\n", r.getId(), r.getNama(), r.getPoin(), r.getDeskripsi());
        }
    }

    public boolean ubahReward(Reward[] rewards, int id, String nama, int poin, String deskripsi) {
        for (Reward r : rewards) {
            if (r.getId() == id) {
                r.setNama(nama);
                r.setPoin(poin);
                r.setDeskripsi(deskripsi);
                return true;
            }
        }
        return false;
    }
}