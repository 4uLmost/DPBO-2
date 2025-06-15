public class KelolaStok {
    private Galon[] galons;
    private Refill[] refills;

    public KelolaStok(Galon[] galons, Refill[] refills) {
        this.galons = galons;
        this.refills = refills;
    }

    public void tampilkanStokGalon() {
        System.out.println("\n=== Stok Galon ===");
        System.out.printf("| %-3s | %-10s | %-7s | %-18s | %-5s |\n",
                "ID", "Brand", "Volume", "Harga dengan Galon", "Stok");
        System.out.println("-------------------------------------------------------------");
        for (Galon g : galons) {
            System.out.printf("| %-3d | %-10s | %-7.1f | Rp%-16d | %-5d |\n",
                    g.getId(), g.getBrand(), g.getVolume(), g.getPrice(), g.getStock());
        }
    }

    public void tampilkanStokRefill() {
        System.out.println("\n=== Stok Refill ===");
        System.out.printf("| %-3s | %-20s | %-7s | %-12s | %-5s |\n",
                "ID", "Brand", "Volume", "Harga Refill", "Stok");
        System.out.println("---------------------------------------------------------------------");
        for (Refill r : refills) {
            System.out.printf("| %-3d | %-20s | %-7.1f | Rp%-10d | %-5d |\n",
                    r.getId(), r.getBrand(), r.getVolume(), r.getRefillPrice(), r.getStock());
        }
    }

    public boolean tambahStokGalon(int id, int jumlah) {
        for (Galon g : galons) {
            if (g.getId() == id) {
                g.tambahStock(jumlah);
                return true;
            }
        }
        return false;
    }

    public boolean tambahStokRefill(int id, int jumlah) {
        // Ubah menjadi mengubah harga refill, bukan stok
        for (Refill r : refills) {
            if (r.getId() == id) {
                r.setRefillPrice(jumlah); // jumlah di sini dianggap sebagai harga baru
                return true;
            }
        }
        return false;
    }

    public boolean kurangiStokGalon(int id, int jumlah) {
        for (Galon g : galons) {
            if (g.getId() == id && g.getStock() >= jumlah) {
                g.reduceStock(jumlah);
                return true;
            }
        }
        return false;
    }

    public boolean kurangiStokRefill(int id, int jumlah) {
        for (Refill r : refills) {
            if (r.getId() == id && r.getStock() >= jumlah) {
                r.reduceStock(jumlah);
                return true;
            }
        }
        return false;
    }

    // Tambahkan method untuk mengubah harga galon
    public boolean ubahHargaGalon(int id, int hargaBaru) {
        for (Galon g : galons) {
            if (g.getId() == id) {
                g.setPrice(hargaBaru);
                return true;
            }
        }
        return false;
    }

    // Tampilkan reward
    public void tampilkanReward(Reward[] rewards) {
        System.out.println("\n=== Daftar Reward ===");
        System.out.printf("| %-5s | %-20s | %-8s | %-30s |\n", "ID", "Nama", "Poin", "Deskripsi");
        System.out.println("--------------------------------------------------------------------------");
        for (Reward r : rewards) {
            System.out.printf("| %-5d | %-20s | %-8d | %-30s |\n", r.getId(), r.getNama(), r.getPoin(), r.getDeskripsi());
        }
    }

    // Ubah reward
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
