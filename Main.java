import java.util.*;

public class Main {
    private static ArrayList<Register> daftarRegister = new ArrayList<>();
    private static User currentUser = null;
    // Tambahkan objek admin dan chat channel
    private static Admin admin = new Admin(1, "Admin", "admin@example.com", "admin123", "Konsultansi");
    private static ChatChannel chatChannel = null;

    // LETAKKAN METODE BARU INI DI DALAM KELAS MAIN, TAPI DI LUAR METODE MAIN
private static void showLoginRegisterMenu(Scanner scanner, ArrayList<Register> daftarRegister, KelolaStok kelolaStok, Reward[] rewards, AuthService authService) {
    while (currentUser == null) {
        System.out.println("\n===== SELAMAT DATANG DI isiAir =====");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Lupa Password");
        System.out.println("4. Keluar");
        System.out.print("Pilih menu: ");
        String menuInput = scanner.nextLine();
        int menu;
        try {
            menu = Integer.parseInt(menuInput);
        } catch (NumberFormatException e) {
            System.out.println("Pilihan anda harus angka sesuai yang ada di menu");
            continue;
        }

        if (menu == 1) {
            // Logika Register (sama seperti yang sudah ada)
            try {
                String name;
                while (true) {
                    System.out.print("Nama: ");
                    name = scanner.nextLine();
                    if (name.isEmpty()) {
                        System.out.println("Nama tidak boleh kosong, Harap isi terlebih dahulu");
                    } else {
                        break;
                    }
                }
                String email;
                while (true) {
                    System.out.print("Email (gunakan @gmail.com): ");
                    email = scanner.nextLine();
                    if (email.isEmpty()) {
                        System.out.println("Email tidak boleh kosong, Harap isi terlebih dahulu");
                    } else if (!email.endsWith("@gmail.com")) {
                        System.out.println("Email harus menggunakan domain @gmail.com");
                    } else {
                        boolean emailExists = false;
                        for (Register reg : daftarRegister) {
                            if (reg.getEmail().equals(email)) {
                                emailExists = true;
                                break;
                            }
                        }
                        if (emailExists) {
                            System.out.println("Email sudah terdaftar, silakan gunakan email lain.");
                        } else {
                            break;
                        }
                    }
                }
                String address;
                while (true) {
                    System.out.print("Alamat: ");
                    address = scanner.nextLine();
                    if (address.isEmpty()) {
                        System.out.println("Alamat tidak boleh kosong, Harap isi terlebih dahulu");
                    } else {
                        break;
                    }
                }
                String password;
                while (true) {
                    System.out.print("Password: ");
                    password = scanner.nextLine();
                    if (password.isEmpty()) {
                        System.out.println("Password tidak boleh kosong, Harap isi terlebih dahulu");
                    } else {
                        break;
                    }
                }
                String phoneInput;
                long phoneNumber;
                while (true) {
                    System.out.print("Nomor Telepon: ");
                    phoneInput = scanner.nextLine();
                    if (phoneInput.isEmpty()) {
                        System.out.println("Nomor Telepon tidak boleh kosong, Harap isi terlebih dahulu");
                        continue;
                    }
                    if (!phoneInput.matches("\\d+")) {
                        System.out.println("Harap masukan nomor telepon yang benar");
                        continue;
                    }
                    if (phoneInput.length() > 13) {
                        System.out.println("Maksimal nomor telepon hanya 13 digit");
                        continue;
                    }
                    boolean phoneExists = false;
                    for (Register reg : daftarRegister) {
                        if (String.valueOf(reg.getPhoneNumber()).equals(phoneInput)) {
                            phoneExists = true;
                            break;
                        }
                    }
                    if (phoneExists) {
                        System.out.println("nomor telepon sudah digunakan");
                        continue;
                    }
                    try {
                        phoneNumber = Long.parseLong(phoneInput);
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Nomor telepon tidak valid. Gunakan hanya angka.");
                    }
                }
                String genderInput;
                char gender;
                while (true) {
                    System.out.print("Jenis Kelamin (L/P): ");
                    genderInput = scanner.nextLine();
                    if (genderInput.isEmpty()) {
                        System.out.println("Jenis Kelamin tidak boleh kosong, Harap isi terlebih dahulu");
                        continue;
                    }
                    char genderChar = Character.toUpperCase(genderInput.charAt(0));
                    if (genderChar != 'L' && genderChar != 'P') {
                        System.out.println("Yang anda masukan tidak valid, Harap masukaln (L/P)");
                        continue;
                    }
                    gender = genderChar;
                    break;
                }
                String birthday;
                while (true) {
                    System.out.print("Tanggal Lahir (yyyy-mm-dd): ");
                    birthday = scanner.nextLine();
                    if (birthday.isEmpty()) {
                        System.out.println("Tanggal Lahir tidak boleh kosong, Harap isi terlebih dahulu");
                        continue;
                    }
                    String digitsOnly = birthday.replaceAll("[^0-9]", "");
                    if (digitsOnly.length() != 8) {
                        System.out.println("Tanggal lahir harus 8 digit angka (format: yyyy-mm-dd atau yyyymmdd)");
                        continue;
                    }
                    if (!digitsOnly.matches("\\d{8}")) {
                        System.out.println("Tanggal lahir harus berupa angka");
                        continue;
                    }
                    if (!birthday.contains("-")) {
                        birthday = digitsOnly.substring(0, 4) + "-" + digitsOnly.substring(4, 6) + "-" + digitsOnly.substring(6, 8);
                    }
                    break;
                }

                Register reg = new Register(
                    daftarRegister.size() + 1,
                    name,
                    email,
                    password,
                    phoneNumber,
                    gender,
                    birthday,
                    address
                );
                daftarRegister.add(reg);
                reg.printInfo();
                System.out.println("Silakan login untuk melanjutkan.");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else if (menu == 2) {
            // Logika Login (sama seperti yang sudah ada)
            while (true) {
                System.out.println("Ketik 0 pada Email atau Password untuk kembali.");
                String email;
                while (true) {
                    System.out.print("Email: ");
                    email = scanner.nextLine();
                    if (email.equals("0")) break;
                    if (email.isEmpty()) {
                        System.out.println("email tidak boleh kosong");
                        continue;
                    }
                    if (!email.endsWith("@gmail.com") && !email.equals("admin")) {
                        System.out.println("harus menggunakan domain @gmail.com");
                        continue;
                    }
                    break;
                }
                if (email.equals("0")) break;
                String password;
                while (true) {
                    System.out.print("Password: ");
                    password = scanner.nextLine();
                    if (password.equals("0")) break;
                    if (password.isEmpty()) {
                        System.out.println("password tidak boleh kosong");
                        continue;
                    }
                    break;
                }
                if (password.equals("0")) break;

                // Cek admin login
                if (email.equals("admin") && password.equals("admin")) {
                    Admin admin = new Admin(0, "Admin", "admin", "admin", "General");
                    int choice = -1;
                    do {
                        System.out.println("=== Menu Admin ===");
                        System.out.println("1. Kelola Stok Galon");
                        System.out.println("2. Kelola Harga Galon");
                        System.out.println("3. Kelola Reward");
                        System.out.println("0. Keluar");
                        System.out.print("Pilih menu: ");
                        try {
                            choice = Integer.parseInt(scanner.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("Pilihan tidak valid.");
                            continue;
                        }
                        switch (choice) {
                            case 1:
                                kelolaStok.tampilkanStokGalon();
                                System.out.print("Masukkan ID galon yang ingin diubah stoknya (0 untuk batal): ");
                                int idGalon = scanner.nextInt();
                                scanner.nextLine();
                                if (idGalon == 0) break;
                                System.out.print("Tambah stok (+) atau kurangi stok (-): ");
                                String opGalon = scanner.nextLine();
                                System.out.print("Jumlah: ");
                                int jumlahGalon = scanner.nextInt();
                                scanner.nextLine();
                                if (opGalon.equals("+")) {
                                    if (kelolaStok.tambahStokGalon(idGalon, jumlahGalon)) {
                                        System.out.println("Stok galon berhasil ditambah.");
                                    } else {
                                        System.out.println("ID galon tidak ditemukan.");
                                    }
                                } else if (opGalon.equals("-")) {
                                    if (kelolaStok.kurangiStokGalon(idGalon, jumlahGalon)) {
                                        System.out.println("Stok galon berhasil dikurangi.");
                                    } else {
                                        System.out.println("ID galon tidak ditemukan atau stok kurang.");
                                    }
                                } else {
                                    System.out.println("Operasi tidak valid.");
                                }
                                break;
                            case 2:
                                kelolaStok.tampilkanStokGalon();
                                System.out.print("Masukkan ID galon yang ingin diubah harganya (0 untuk batal): ");
                                int idHarga = scanner.nextInt();
                                scanner.nextLine();
                                if (idHarga == 0) break;
                                System.out.print("Masukkan harga baru: ");
                                int hargaBaru = scanner.nextInt();
                                scanner.nextLine();
                                if (kelolaStok.ubahHargaGalon(idHarga, hargaBaru)) {
                                    System.out.println("Harga galon berhasil diubah.");
                                } else {
                                    System.out.println("ID galon tidak ditemukan.");
                                }
                                break;
                            case 3:
                                kelolaStok.tampilkanReward(rewards);
                                System.out.print("Masukkan ID reward yang ingin diubah (0 untuk batal): ");
                                int idReward = scanner.nextInt();
                                scanner.nextLine();
                                if (idReward == 0) break;
                                System.out.print("Masukkan nama baru: ");
                                String namaReward = scanner.nextLine();
                                System.out.print("Masukkan poin baru: ");
                                int poinReward = scanner.nextInt();
                                scanner.nextLine();
                                System.out.print("Masukkan deskripsi baru: ");
                                String descReward = scanner.nextLine();
                                if (kelolaStok.ubahReward(rewards, idReward, namaReward, poinReward, descReward)) {
                                    System.out.println("Reward berhasil diubah.");
                                } else {
                                    System.out.println("ID reward tidak ditemukan.");
                                }
                                break;
                            case 0:
                                System.out.println("Keluar dari menu admin.");
                                break;
                            default:
                                System.out.println("Pilihan tidak valid.");
                        }
                    } while (choice != 0);
                    break;
                }

                Register loggedInUser = authService.login(email, password, daftarRegister);

                if (loggedInUser != null) {
                    // Jika login berhasil
                    currentUser = loggedInUser;
                    System.out.println("Login berhasil. Selamat datang, " + currentUser.getName());
                    chatChannel = new ChatChannel(1, admin, currentUser);
                    break; // Keluar dari loop login
                } else {
                    // Jika login gagal
                    // Kita bisa cek apakah emailnya ada tapi passwordnya salah
                    if (authService.isEmailTaken(email, daftarRegister)) {
                        System.out.println("Password salah.");
                    } else {
                        System.out.println("Email belum terdaftar.");
                    }
                    continue;
                }
            }
        } else if (menu == 3) {
            // Logika Lupa Password (sama seperti yang sudah ada)
            System.out.print("Masukkan email user: ");
            String email = scanner.nextLine();
            Register reg = null;
            for (Register r : daftarRegister) {
                if (r.getEmail().equals(email)) {
                    reg = r;
                    break;
                }
            }
            if (reg == null) {
                System.out.println("User tidak ditemukan.");
                continue;
            }
            ForgotPassword fp = new ForgotPassword(reg.getId(), reg.getName(), reg.getEmail(), reg.getPassword(), reg.getPhoneNumber(), reg.getGender(), reg.getBirthday());
            System.out.println("Kode OTP Anda: " + fp.getOTPCode());
            System.out.print("Masukkan OTP: ");
            int otp = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Masukkan password baru: ");
            String newPass = scanner.nextLine();
            fp.resetPasswordWithOTP(otp, newPass);
            reg.setPassword(newPass);
        } else if (menu == 4) {
            System.out.println("Terima kasih telah menggunakan isiAir!");
            System.exit(0); // Keluar dari program
        } else {
            System.out.println("Menu tidak tersedia, silahkan pilih sesuai nomor pada menu");
        }
    }
}

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            AuthService authService = new AuthService();

            // Tambahkan akun test ke daftarRegister
            try {
                daftarRegister.add(new Register(
                    
                )
                // id
                // nomor telepon contoh
                );
            } catch (Exception e) {
                // Tidak perlu aksi, hanya untuk inisialisasi test
            }

            // GUNAKAN ArrayList TUNGGAL INI
            ArrayList<Product> allProducts = new ArrayList<>();

            // Tambahkan Galon
            Galon aquaGalon = new Galon(1, 19.0, "Aqua", 50000, 10);
            Galon clubGalon = new Galon(2, 19.0, "Club", 45000, 0);
            Galon ron88Galon = new Galon(3, 19.0, "ron 88", 45000, 11);

            allProducts.add(aquaGalon);
            allProducts.add(clubGalon);
            allProducts.add(ron88Galon);

            // Tambahkan Refill
            allProducts.add(new Refill(1, 19.0, "Aqua", aquaGalon, 12000));
            allProducts.add(new Refill(2, 19.0, "Club", clubGalon, 10000));
            allProducts.add(new Refill(3, 19.0, "ron 88", ron88Galon, 7000));
            allProducts.add(new Refill(4, 19.0, "Air Depot (IsiAir)", null, 6000));

            // Tambahkan objek kelola stok
            KelolaStok kelolaStok = new KelolaStok(allProducts);

            // Data reward
            Reward[] rewards = {
                new Reward(101, "Gratis Ongkir", 100, "Berlaku untuk 1x refill galon"),
                new Reward(102, "Gratis Refil", 50, "Refil galon gratis 1x"),
                new Reward(103, "Promo Diskon", 25, "Diskon 10% untuk isi ulang berikutnya")
            };

            // Set point user menjadi 100 (bukan 0)
            if (currentUser instanceof User) {
                ((User) currentUser).setPoints(100);
            } else if (currentUser instanceof Register) {
                try {
                    ((Register) currentUser).setPoints(100);
                } catch (Exception e) {
                    // Jika tidak ada method setPoints, abaikan
                }
            }

            // Data delivery
            HashMap<Integer, Delivery> orders = new HashMap<>();

            // Tambahkan keranjang global
            Keranjang keranjang = new Keranjang();

            showLoginRegisterMenu(scanner, daftarRegister, kelolaStok, rewards, authService);

            // === MENU UTAMA SETELAH LOGIN ===
            while (true) {
                System.out.println("\n===== MENU UTAMA isiAir =====");
                System.out.println("1. Beli galon");
                System.out.println("2. Isi Ulang galon");
                System.out.println("3. Pesanan Saya");
                System.out.println("4. Keranjang");
                System.out.println("5. Chat");
                System.out.println("6. Profile"); // Tambahkan menu Profile
                System.out.println("7. Reward");
                System.out.println("8. Logout");
                System.out.println("9. Keluar");
                System.out.print("Pilih menu: ");
                String menuInput = scanner.nextLine();
                int menu;
                try {
                    menu = Integer.parseInt(menuInput);
                } catch (NumberFormatException e) {
                    System.out.println("Pilihan anda harus angka sesuai yang ada di menu");
                    continue;
                }

                if (menu == 1) {
                    // Beli galon
                    while (true) {
                        System.out.println("\n========== DAFTAR GALON ==========");
                        System.out.printf("| %-3s | %-10s | %-7s | %-18s | %-5s |\n",
                                "ID", "Brand", "Volume", "Harga dengan Galon", "Stok");
                        System.out.println("-------------------------------------------------------------");
                        // Loop melalui SEMUA produk, tapi hanya tampilkan GALON
                        for (Product product : allProducts) {
                            if (product instanceof Galon) {
                                Galon g = (Galon) product; // Cast ke Galon untuk akses metode spesifik
                                System.out.printf("| %-3d | %-10s | %-7.1f | Rp%-16d | %-5d |\n",
                                        g.getId(), g.getBrand(), g.getVolume(), g.getPrice(), g.getStock());
                            }
                        }
                        // Hapus opsi 2, hanya tampilkan 0
                        System.out.println("0. Kembali ke menu utama");
                        int pilihan;
                        Galon galonDipilih = null;
                        while (true) {
                            System.out.print("Pilih Id Galon: ");
                            pilihan = scanner.nextInt();
                            scanner.nextLine();
                            if (pilihan == 0) {
                                break; // Keluar dari loop pemilihan jika input 0
                            }

                            Product produkDitemukan = null;
                            // Cari di dalam allProducts
                            for (Product p : allProducts) {
                                // Syaratnya: ID harus cocok DAN tipenya harus Galon
                                if (p.getId() == pilihan && p instanceof Galon) {
                                    produkDitemukan = p;
                                    break;
                                }
                            }

                            // Jika setelah dicari tidak ketemu atau tipenya salah
                            if (produkDitemukan == null) {
                                System.out.println("Pilihan tidak valid.");
                                continue; // Ulangi pertanyaan
                            }

                            // Jika produk ditemukan, cast dan cek stok
                            galonDipilih = (Galon) produkDitemukan;
                            if (galonDipilih.getStock() == 0) {
                                System.out.println("Mohon maaf barang yang anda pilih tidak tersedia, Harap pilih kembali");
                                galonDipilih = null; // Reset pilihan
                                continue; // Ulangi pertanyaan
                            }
                            
                            // Jika semua valid, keluar dari loop pemilihan
                            break;
                        }

                        if (pilihan == 0) {
                            break; // Kembali ke menu utama
                        }
                        int hargaDenganGalon = galonDipilih.getPrice();
                        int jumlahBeli;
                        while (true) {
                            System.out.print("Masukkan jumlah yang ingin dibeli: ");
                            jumlahBeli = scanner.nextInt();
                            scanner.nextLine();
                            if (jumlahBeli > galonDipilih.getStock()) {
                                System.out.println("Mohon maaf sisa barang yang anda pilih tinggal " + galonDipilih.getStock() + ", Silahkan masukan stok yang tersedia");
                            } else if (jumlahBeli <= 0) {
                                System.out.println("Jumlah harus lebih dari 0.");
                            } else {
                                break;
                            }
                        }
                        galonDipilih.reduceStock(jumlahBeli); // Kurangi stok
                        CartItem itemBaru = new CartItem(galonDipilih, jumlahBeli, hargaDenganGalon, "Dengan galon baru");
                        keranjang.tambahItem(itemBaru);
                        System.out.println("Berhasil menambahkan ke keranjang.");
                    }
                } else if (menu == 2) {
                    // Refil galon
                    while (true) {
                        System.out.println("\n========== ISI ULANG GALON ==========");
                        System.out.printf("| %-3s | %-20s | %-7s | %-12s | %-5s |\n",
                                "ID", "Brand", "Volume", "Harga Refill", "Stok");
                        System.out.println("---------------------------------------------------------------------");
                        // Loop melalui SEMUA produk, tapi hanya tampilkan REFILL
                        for (Product product : allProducts) {
                            if (product instanceof Refill) {
                                Refill r = (Refill) product; // Cast ke Refill
                                // Karena method printRefillInfo() spesifik, kita cetak manual di sini
                                System.out.printf("| %-3d | %-20s | %-7.1f | Rp%-10d | %-5d |\n",
                                    r.getId(), r.getBrand(), r.getVolume(), r.getPrice(), r.getStock());
                            }
                        }
                        // Langsung input ID galon
                        System.out.println("0. Kembali ke menu utama");
                        int pilihanGalon;
                        Refill refillDipilih = null;
                        while (true) {
                            System.out.print("Pilih Id Galon: ");
                            pilihanGalon = scanner.nextInt();
                            scanner.nextLine();
                            if (pilihanGalon == 0) {
                                break; // Keluar dari loop pemilihan jika input 0
                            }

                            Product produkDitemukan = null;
                            // Cari di dalam allProducts
                            for (Product p : allProducts) {
                                // Syaratnya: ID harus cocok DAN tipenya harus Refill
                                if (p.getId() == pilihanGalon && p instanceof Refill) {
                                    produkDitemukan = p;
                                    break;
                                }
                            }

                            // Jika setelah dicari tidak ketemu atau tipenya salah
                            if (produkDitemukan == null) {
                                System.out.println("Pilihan tidak valid.");
                                continue; // Ulangi pertanyaan
                            }
                            
                            // Jika produk ditemukan, cast dan cek stok
                            refillDipilih = (Refill) produkDitemukan;
                            if (refillDipilih.getStock() == 0) {
                                System.out.println("Mohon maaf barang yang anda pilih tidak tersedia, Harap pilih kembali");
                                refillDipilih = null; // Reset pilihan
                                continue; // Ulangi pertanyaan
                            }

                            // Jika semua valid, keluar dari loop pemilihan
                            break;
                        }

                        if (pilihanGalon == 0) {
                            break; // Kembali ke menu utama
                        }
                        int jumlahRefill;
                        while (true) {
                            System.out.print("Jumlah galon yang ingin di isi ulang: ");
                            jumlahRefill = scanner.nextInt();
                            scanner.nextLine();
                            // Sinkronisasi stok: jika ada galon terkait, cek stok galon
                            if (refillDipilih.getGalon() != null) {
                                if (jumlahRefill > refillDipilih.getGalon().getStock()) {
                                    System.out.println("Mohon maaf sisa barang yang anda pilih tinggal " + refillDipilih.getGalon().getStock() + ", Silahkan masukan stok yang tersedia");
                                } else if (jumlahRefill <= 0) {
                                    System.out.println("Jumlah harus lebih dari 0.");
                                } else {
                                    break;
                                }
                            } else {
                                // Untuk Air Depot (tanpa galon terkait)
                                if (jumlahRefill > refillDipilih.getStock()) {
                                    System.out.println("Mohon maaf sisa barang yang anda pilih tinggal " + refillDipilih.getStock() + ", Silahkan masukan stok yang tersedia");
                                } else if (jumlahRefill <= 0) {
                                    System.out.println("Jumlah harus lebih dari 0.");
                                } else {
                                    break;
                                }
                            }
                        }
                        // Sinkronisasi stok: jika ada galon terkait, kurangi stok galon
                        if (refillDipilih.getGalon() != null) {
                            refillDipilih.getGalon().reduceStock(jumlahRefill);
                        } else {
                            // Untuk Air Depot (tanpa galon terkait), kurangi stok refill sendiri
                            refillDipilih.reduceStock(jumlahRefill);
                        }
                        CartItem itemBaru = new CartItem(refillDipilih, jumlahRefill, refillDipilih.getPrice(), "Isi ulang");
                        keranjang.tambahItem(itemBaru);
                        System.out.println("Berhasil menambahkan ke keranjang.");
                    }
                } else if (menu == 3) {
                    // Pesanan Saya
                    while (true) {
                        System.out.print("Masukkan ID Pesanan (tekan ENTER untuk kembali): ");
                        String input = scanner.nextLine();
                        if (input.isEmpty()) break;
                        int id;
                        try {
                            id = Integer.parseInt(input);
                        } catch (NumberFormatException e) {
                            System.out.println("ID harus berupa angka! Silakan coba lagi.");
                            continue;
                        }
                        if (orders.containsKey(id) && orders.get(id).isCompleted()) {
                            System.out.println("Pesanan ID " + id + " sudah sampai.");
                        } else {
                            Delivery delivery = orders.getOrDefault(id, new Delivery(id));
                            delivery.updateStatus();
                            delivery.printInfo();
                            if (!delivery.isCompleted()) {
                                orders.put(id, delivery);
                            }
                        }
                        System.out.println("Terima kasih!");
                    }
                } else if (menu == 4) {
                    // Keranjang
                    while (true) {
                        System.out.println("\n===== KERANJANG ANDA =====");
                        keranjang.tampilkanKeranjang();
                        System.out.println("1. Checkout");
                        System.out.println("2. Kembali");
                        System.out.println("3. Hapus item");
                        System.out.println("4. Edit item");
                        System.out.print("Pilih menu: ");
                        String pilihKeranjang = scanner.nextLine();
                        if (pilihKeranjang.equals("1")) {
                            if (keranjang.isEmpty()) { // <-- Langsung cek dari objek keranjang
                                System.out.println("keranjang kosong");
                            } else {
                                // Hitung total harga dengan cara yang jauh lebih sederhana
                                int totalHarga = keranjang.getTotalHarga(); // <-- Total harga didapat dari metode baru di Keranjang

                                System.out.println("Total harga belanjaan anda: Rp" + totalHarga);
                                System.out.println("Pilih metode pembayaran:");
                                System.out.println("1. QRIS");
                                System.out.println("2. Cash");
                                System.out.print("Pilihan (1/2): ");
                                int pilihanBayar = Integer.parseInt(scanner.nextLine());
                                String metodePembayaran;
                                switch (pilihanBayar) {
                                    case 1: metodePembayaran = "QRIS"; break;
                                    case 2: metodePembayaran = "Cash"; break;
                                    default: metodePembayaran = "QRIS";
                                }
                                String status = "Menunggu";
                                int pembayaranId = (int)(Math.random() * 100000); // ID random
                                if ("QRIS".equals(metodePembayaran)) {
                                    QRIS qris = new QRIS(totalHarga, metodePembayaran, status, pembayaranId, pembayaranId);
                                    qris.tampilkanQr();
                                    System.out.println("======= QR CODE ======");
                                    System.out.println("| [] []   []   [] [] |");
                                    System.out.println("|   [] [] [] []   [] |");
                                    System.out.println("| []   []   [] []    |");
                                    System.out.println("|   [] []   []   []  |");
                                    System.out.println("| []   [] []   [] [] |");
                                    System.out.println("======================");
                                    qris.prosesPembayaran();
                                    qris.verifikasiPembayaran();
                                    qris.printInfo();
                                } else if ("Cash".equals(metodePembayaran)) {
                                    Pembayaran pembayaran = new Pembayaran(totalHarga, metodePembayaran, status, pembayaranId);
                                    pembayaran.prosesPembayaran();
                                    System.out.println("Terimakasih sudah membeli, jangan lupa bayar ya!");
                                    pembayaran.printInfo();
                                }
                                
                                // Setelah pembayaran berhasil, kita cukup mengosongkan keranjang
                                keranjang.kosongkanKeranjang(); // <-- Cukup kosongkan keranjang saja
                            }
                        } else if (pilihKeranjang.equals("2")) {
                            break;
                        } else if (pilihKeranjang.equals("3")) {
                            // Hapus item
                            keranjang.tampilkanKeranjang();
                            System.out.print("Masukkan nomor item yang ingin dihapus (0 untuk batal): ");
                            int nomorHapus = scanner.nextInt();
                            scanner.nextLine();
                            if (nomorHapus == 0) {
                                System.out.println("Batal menghapus item.");
                                continue;
                            }
                            keranjang.hapusItem(nomorHapus); 
                            System.out.println("Item berhasil dihapus.");
                        } else if (pilihKeranjang.equals("4")) {
                            // Edit item
                            keranjang.tampilkanKeranjang();
                            System.out.print("Masukkan nomor item yang ingin diedit (0 untuk batal): ");
                            int nomorEdit = scanner.nextInt();
                            scanner.nextLine();
                            if (nomorEdit == 0) {
                                System.out.println("Batal mengedit item.");
                                continue;
                            }
                            System.out.print("Masukkan jumlah baru: ");
                            int jumlahBaru = scanner.nextInt();
                            scanner.nextLine();
                            if (jumlahBaru <= 0) {
                                System.out.println("Jumlah harus lebih dari 0.");
                            } else {
                                keranjang.editItem(nomorEdit, jumlahBaru);
                                System.out.println("Item berhasil diedit.");
                            }
                        } else {
                            System.out.println("Pilihan tidak valid.");
                        }
                    }
                } else if (menu == 5) {
                    // Chat interaktif antara user dan admin
                    if (chatChannel == null) {
                        chatChannel = new ChatChannel(1, admin, currentUser);
                    }
                    // Admin menyapa user jika chat kosong
                    if (chatChannel.isEmpty()) {
                        chatChannel.sendMessage("Halo " + currentUser.getName() + ", apakah ada yang bisa saya bantu?", admin);
                    }
                    while (true) {
                        chatChannel.printChatHistory();
                        System.out.print("\nUser, masukkan pesan ('exit' untuk keluar): ");
                        String message = scanner.nextLine();
                        if (message.equalsIgnoreCase("exit")) {
                            break;
                        }
                        chatChannel.sendMessage(message, currentUser);
                        chatChannel.sendMessage("Baik kak permintaan anda akan kami proses", admin);
                    }
                    System.out.println("Percakapan berakhir.");
                } else if (menu == 6) {
                    // Profile
                    System.out.println("\n=== Informasi Profil ===");
                    System.out.println("ID: " + currentUser.getId());
                    System.out.println("Nama: " + currentUser.getName());
                    System.out.println("Email: " + currentUser.getEmail());
                    System.out.println("Password: " + currentUser.getPassword());
                    if (currentUser instanceof Register) {
                        Register reg = (Register) currentUser;
                        System.out.println("Nomor Telepon: " + reg.getPhoneNumber());
                        System.out.println("Jenis Kelamin: " + reg.getGender());
                        System.out.println("Tanggal Lahir: " + reg.getBirthday());
                        System.out.println("Alamat: " + reg.getAddress());
                    }
                } else if (menu == 7) {
                    // Fitur reward (ganti notifikasi)
                    // Set poin user menjadi 100 sebelum menu reward
                    if (currentUser instanceof User) {
                        ((User) currentUser).setPoints(100);
                    } else if (currentUser instanceof Register) {
                        try {
                            ((Register) currentUser).setPoints(100);
                        } catch (Exception e) {
                            // Jika tidak ada method setPoints, abaikan
                        }
                    }
                    int choiceReward = -1;
                    do {
                        System.out.println("\n-------------- MENU REWARD --------------");
                        System.out.println("Selamat Datang Dihalaman Reward Kami " + currentUser.getName());
                        int userPoints = 0;
                        if (currentUser instanceof User) {
                            userPoints = ((User) currentUser).getPoints();
                        } else if (currentUser instanceof Register) {
                            try {
                                userPoints = ((Register) currentUser).getPoints();
                            } catch (Exception e) {
                                // Jika tidak ada, default 0
                            }
                        }
                        System.out.println("Poin Anda saat ini: " + userPoints);
                        for (int i = 0; i < rewards.length; i++) {
                            System.out.println((i + 1) + ". " + rewards[i].getNama() + " (" + rewards[i].getPoin() + " poin)");
                        }
                        System.out.println("0. Keluar");
                        System.out.print("Pilih reward yang ingin ditebus (0/" + (rewards.length > 0 ? "1" : "") + (rewards.length > 1 ? "/2" : "") + (rewards.length > 2 ? "/3" : "") + "): ");
                        try {
                            choiceReward = Integer.parseInt(scanner.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("Pilihan tidak valid.");
                            continue;
                        }

                        Reward selectedReward = null;
                        if (choiceReward > 0 && choiceReward <= rewards.length) {
                            selectedReward = rewards[choiceReward - 1];
                        } else if (choiceReward == 0) {
                            System.out.println("Terima kasih telah menggunakan sistem reward.");
                            break;
                        } else {
                            System.out.println("Pilihan tidak valid.");
                            continue;
                        }

                        if (selectedReward != null) {
                            System.out.println("\n----------- Proses Redeem ---------------");
                            selectedReward.redeem(currentUser);
                            int sisaPoin = 0;
                            if (currentUser instanceof User) {
                                sisaPoin = ((User) currentUser).getPoints();
                            } else if (currentUser instanceof Register) {
                                try {
                                    sisaPoin = ((Register) currentUser).getPoints();
                                } catch (Exception e) {}
                            }
                            System.out.println("Sisa poin Anda: " + sisaPoin);
                        }
                    } while (choiceReward != 0);
                } else if (menu == 8) {
                    // Logout
                    System.out.println("Anda telah logout.");
                    currentUser = null;
                    showLoginRegisterMenu(scanner, daftarRegister, kelolaStok, rewards, authService);
                }else if (menu == 9) {
                    System.out.println("Terima kasih telah menggunakan isiAir!");
                    break;
                } else {
                    System.out.println("Menu tidak tersedia, silahkan pilih sesuai nomor pada menu");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


