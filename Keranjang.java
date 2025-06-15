import java.util.ArrayList;

public class Keranjang {
    private ArrayList<String> items = new ArrayList<>();

    public void tambahItem(String itemBaru) {
        // Format item: "Brand xJumlah (Keterangan) - RpTotal"
        // Cek apakah sudah ada item dengan Brand dan Keterangan yang sama
        String[] partsBaru = itemBaru.split(" x| \\(|\\) - Rp");
        String brandBaru = partsBaru.length > 0 ? partsBaru[0].trim() : "";
        String ketBaru = partsBaru.length > 2 ? partsBaru[2].trim() : "";

        for (int i = 0; i < items.size(); i++) {
            String item = items.get(i);
            String[] parts = item.split(" x| \\(|\\) - Rp");
            String brand = parts.length > 0 ? parts[0].trim() : "";
            String ket = parts.length > 2 ? parts[2].trim() : "";
            if (brand.equalsIgnoreCase(brandBaru) && ket.equalsIgnoreCase(ketBaru)) {
                // Sudah ada, tambahkan jumlah dan harga
                int jumlahLama = Integer.parseInt(parts[1].trim());
                int jumlahBaru = Integer.parseInt(partsBaru[1].trim());
                int totalJumlah = jumlahLama + jumlahBaru;
                int hargaLama = Integer.parseInt(parts[3].trim());
                int hargaBaru = Integer.parseInt(partsBaru[3].trim());
                int totalHarga = hargaLama + hargaBaru;
                String itemBaruGabung = brand + " x" + totalJumlah + " (" + ket + ") - Rp" + totalHarga;
                items.set(i, itemBaruGabung);
                return;
            }
        }
        // Jika belum ada, tambahkan baru
        items.add(itemBaru);
    }

    public void tampilkanKeranjang(ArrayList<String> daftarPembelianGalon, Galon[] galons) {
        if (items.isEmpty()) {
            System.out.println("keranjang kosong");
            return;
        }
        System.out.println("---------------------------------------------------------------");
        System.out.printf("| %-3s | %-12s | %-6s | %-18s | %-12s |\n", "No", "Nama Galon", "Jumlah", "Keterangan", "Total Harga");
        System.out.println("---------------------------------------------------------------");
        int no = 1;
        int totalSemua = 0;
        for (String item : items) {
            String[] parts = item.split(" x| \\(|\\) - Rp");
            String nama = parts.length > 0 ? parts[0] : "";
            String jumlah = parts.length > 1 ? parts[1] : "";
            String ket = parts.length > 2 ? parts[2] : "";
            String harga = parts.length > 3 ? "Rp" + parts[3] : "";
            int hargaInt = parts.length > 3 ? Integer.parseInt(parts[3]) : 0;
            totalSemua += hargaInt;
            System.out.printf("| %-3d | %-12s | %-6s | %-18s | %-12s |\n", no++, nama, jumlah, ket, harga);
        }
        System.out.println("---------------------------------------------------------------");
        System.out.printf("%45s : Rp%d\n", "Total Semua", totalSemua);
        System.out.println("---------------------------------------------------------------");
    }

    // Overload agar tetap kompatibel dengan pemanggilan lama
    public void tampilkanKeranjang() {
        tampilkanKeranjang(this.items, new Galon[0]);
    }

    public void kosongkanKeranjang() {
        items.clear();
    }

    public ArrayList<String> getItems() {
        return items;
    }

    public void hapusItem(int nomor) {
        if (nomor >= 1 && nomor <= items.size()) {
            items.remove(nomor - 1);
        } else {
            System.out.println("Nomor item tidak valid.");
        }
    }

    // Tambahkan parameter galons dan refills untuk update stok saat hapus/edit
    public void hapusItem(int nomor, Galon[] galons, Refill[] refills) {
        if (nomor >= 1 && nomor <= items.size()) {
            String item = items.get(nomor - 1);
            // Format: Brand xJumlah (Keterangan) - RpTotal
            String[] parts = item.split(" x| \\(|\\) - Rp");
            String brand = parts.length > 0 ? parts[0].trim() : "";
            String ket = parts.length > 2 ? parts[2].trim() : "";
            int jumlah = Integer.parseInt(parts[1].trim());
            // Kembalikan stok ke galon/refill
            if (ket.equalsIgnoreCase("Dengan galon baru")) {
                for (Galon g : galons) {
                    if (g.getBrand().equalsIgnoreCase(brand)) {
                        g.reduceStock(-jumlah); // tambah stok
                        break;
                    }
                }
            } else if (ket.equalsIgnoreCase("Isi ulang")) {
                for (Refill r : refills) {
                    if (r.getBrand().equalsIgnoreCase(brand)) {
                        r.reduceStock(-jumlah); // tambah stok
                        break;
                    }
                }
            }
            items.remove(nomor - 1);
        } else {
            System.out.println("Nomor item tidak valid.");
        }
    }

    public void editItem(int nomor, int jumlahBaru) {
        if (nomor >= 1 && nomor <= items.size()) {
            String item = items.get(nomor - 1);
            // Format: Brand xJumlah (Keterangan) - RpTotal
            String[] parts = item.split(" x| \\(|\\) - Rp");
            String brand = parts.length > 0 ? parts[0].trim() : "";
            String ket = parts.length > 2 ? parts[2].trim() : "";
            int hargaSatuan = 0;
            int jumlahLama = Integer.parseInt(parts[1].trim());
            int hargaLama = Integer.parseInt(parts[3].trim());
            if (jumlahLama > 0) {
                hargaSatuan = hargaLama / jumlahLama;
            }
            int totalHargaBaru = hargaSatuan * jumlahBaru;
            String itemBaru = brand + " x" + jumlahBaru + " (" + ket + ") - Rp" + totalHargaBaru;
            items.set(nomor - 1, itemBaru);
        } else {
            System.out.println("Nomor item tidak valid.");
        }
    }

    public void editItem(int nomor, int jumlahBaru, Galon[] galons, Refill[] refills) {
        if (nomor >= 1 && nomor <= items.size()) {
            if (jumlahBaru <= 0) {
                // Pesan sudah ditangani di Main.java, tidak perlu ulang di sini
                return;
            }
            String item = items.get(nomor - 1);
            // Format: Brand xJumlah (Keterangan) - RpTotal
            String[] parts = item.split(" x| \\(|\\) - Rp");
            String brand = parts.length > 0 ? parts[0].trim() : "";
            String ket = parts.length > 2 ? parts[2].trim() : "";
            int jumlahLama = Integer.parseInt(parts[1].trim());
            int hargaLama = Integer.parseInt(parts[3].trim());
            int hargaSatuan = jumlahLama > 0 ? hargaLama / jumlahLama : 0;
            int totalHargaBaru = hargaSatuan * jumlahBaru;

            int selisih = jumlahBaru - jumlahLama;
            boolean stokCukup = true;

            if (ket.equalsIgnoreCase("Dengan galon baru")) {
                for (Galon g : galons) {
                    if (g.getBrand().equalsIgnoreCase(brand)) {
                        if (selisih > 0 && g.getStock() < selisih) {
                            System.out.println("Stok galon tidak mencukupi. Sisa stok: " + g.getStock());
                            stokCukup = false;
                        } else {
                            g.reduceStock(selisih);
                        }
                        break;
                    }
                }
            } else if (ket.equalsIgnoreCase("Isi ulang")) {
                for (Refill r : refills) {
                    if (r.getBrand().equalsIgnoreCase(brand)) {
                        if (selisih > 0 && r.getStock() < selisih) {
                            System.out.println("Stok refill tidak mencukupi. Sisa stok: " + r.getStock());
                            stokCukup = false;
                        } else {
                            r.reduceStock(selisih);
                        }
                        break;
                    }
                }
            }

            if (!stokCukup) {
                System.out.println("Edit gagal karena stok tidak cukup.");
                return;
            }

            String itemBaru = brand + " x" + jumlahBaru + " (" + ket + ") - Rp" + totalHargaBaru;
            items.set(nomor - 1, itemBaru);
        } else {
            System.out.println("Nomor item tidak valid.");
        }
    }
}
