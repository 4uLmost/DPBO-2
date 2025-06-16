// KelolaStok.java
import java.util.ArrayList;

public class KelolaStok {
    private ArrayList<Product> products;

    public KelolaStok(ArrayList<Product> products) {
        this.products = products;
    }

    public void tampilkanSemuaProduk() {
        System.out.println("\n====================== DAFTAR SEMUA PRODUK ======================");
        System.out.printf("| %-3s | %-20s | %-10s | %-12s | %-5s |\n",
                "ID", "Brand", "Tipe", "Harga", "Stok");
        System.out.println("-------------------------------------------------------------------");
        for (Product p : products) {
            String tipe = (p instanceof Galon) ? "Galon Baru" : "Isi Ulang";
            System.out.printf("| %-3d | %-20s | %-10s | Rp%-10d | %-5d |\n",
                    p.getId(), p.getBrand(), tipe, p.getPrice(), p.getStock());
        }
        System.out.println("-------------------------------------------------------------------");
    }
    
    public Product findProductById(int id) {
        for (Product p : products) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null; 
    }

    // --- METODE BARU DI BAWAH INI ---

    /**
     * Menghasilkan ID unik baru untuk produk berikutnya.
     */
    public int getNewProductId() {
        int maxId = 0;
        for (Product p : products) {
            if (p.getId() > maxId) {
                maxId = p.getId();
            }
        }
        return maxId + 1;
    }

    /**
     * Menambahkan produk baru ke dalam daftar.
     */
    public void tambahProduk(Product product) {
        products.add(product);
        System.out.println("Produk baru '" + product.getBrand() + "' berhasil ditambahkan.");
    }

    /**
     * Menghapus produk berdasarkan ID.
     * Juga akan menghapus Refill yang terkait jika yang dihapus adalah Galon.
     */
    public boolean hapusProduk(int id) {
        Product produkDihapus = findProductById(id);
        if (produkDihapus == null) {
            return false; // Produk tidak ditemukan
        }
        
        // Jika yang dihapus adalah Galon, hapus juga Refill yang terikat padanya
        if (produkDihapus instanceof Galon) {
            products.removeIf(p -> (p instanceof Refill && ((Refill) p).getGalon() == produkDihapus));
        }
        
        products.remove(produkDihapus);
        return true;
    }
}