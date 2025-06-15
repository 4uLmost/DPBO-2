import java.util.ArrayList;
import java.util.Iterator;

public class Keranjang {
    private ArrayList<CartItem> items = new ArrayList<>();

    public void tambahItem(CartItem newItem) {
        // Cek apakah item dengan produk yang sama sudah ada di keranjang
        for (CartItem item : items) {
            // Kita bandingkan berdasarkan objek produk dan deskripsinya
            if (item.getProduct() == newItem.getProduct() && item.getDescription().equals(newItem.getDescription())) {
                item.addQuantity(newItem.getQuantity()); // Jika sudah ada, cukup tambahkan jumlahnya
                return;
            }
        }
        // Jika belum ada, tambahkan item baru ke keranjang
        items.add(newItem);
    }

    public void tampilkanKeranjang() {
        if (items.isEmpty()) {
            System.out.println("Keranjang kosong");
            return;
        }
        System.out.println("---------------------------------------------------------------");
        System.out.printf("| %-3s | %-12s | %-6s | %-18s | %-12s |\n", "No", "Nama Galon", "Jumlah", "Keterangan", "Total Harga");
        System.out.println("---------------------------------------------------------------");
        int no = 1;
        int totalSemua = 0;
        for (CartItem item : items) {
            System.out.printf("| %-3d | %-12s | %-6d | %-18s | Rp%-11d |\n", 
                no++, 
                item.getBrand(), 
                item.getQuantity(), 
                item.getDescription(), 
                item.getTotalPrice());
            totalSemua += item.getTotalPrice();
        }
        System.out.println("---------------------------------------------------------------");
        System.out.printf("%56s : Rp%d\n", "Total Semua", totalSemua);
        System.out.println("---------------------------------------------------------------");
    }

    public void kosongkanKeranjang() {
        items.clear();
    }

    public ArrayList<CartItem> getItems() {
        return items;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }
    
    public int getTotalHarga() {
        int totalSemua = 0;
        for(CartItem item : items) {
            totalSemua += item.getTotalPrice();
        }
        return totalSemua;
    }

    public void hapusItem(int nomor) {
        if (nomor >= 1 && nomor <= items.size()) {
            CartItem itemDihapus = items.get(nomor - 1);
            
            // Kembalikan stok
            Object product = itemDihapus.getProduct();
            if (product instanceof Galon) {
                ((Galon) product).reduceStock(-itemDihapus.getQuantity()); // Menambah stok
            } else if (product instanceof Refill) {
                // Untuk refill, kita kembalikan stok ke galon aslinya jika ada
                Refill refill = (Refill) product;
                if (refill.getGalon() != null) {
                    refill.getGalon().reduceStock(-itemDihapus.getQuantity());
                } else {
                    // Jika refill mandiri (seperti Air Depot)
                    refill.reduceStock(-itemDihapus.getQuantity());
                }
            }
            items.remove(nomor - 1);
            System.out.println("Item berhasil dihapus.");
        } else {
            System.out.println("Nomor item tidak valid.");
        }
    }

    public void editItem(int nomor, int jumlahBaru) {
    if (nomor >= 1 && nomor <= items.size()) {
        if (jumlahBaru <= 0) {
            System.out.println("Jumlah harus lebih dari 0.");
            return;
        }

        CartItem item = items.get(nomor - 1);
        Product product = (Product) item.getProduct();
        int jumlahLama = item.getQuantity();
        int selisih = jumlahBaru - jumlahLama;

        if (selisih > 0 && product.getStock() < selisih) {
            System.out.println("Stok tidak mencukupi. Sisa stok: " + product.getStock());
            return;
        }
        
        product.reduceStock(selisih);
        item.setQuantity(jumlahBaru);
        System.out.println("Item berhasil diedit.");

    } else {
        System.out.println("Nomor item tidak valid.");
    }
}
}