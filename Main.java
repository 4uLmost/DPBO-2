import java.util.*;

public class Main {
    private static ArrayList<Register> daftarRegister = new ArrayList<>();
    private static User currentUser = null;
    // Tambahkan objek admin dan chat channel
    private static Admin admin = new Admin(1, "Admin", "admin@example.com", "admin123", "Konsultansi");
    private static ChatChannel chatChannel = null;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Tambahkan akun test ke daftarRegister
        try {
            daftarRegister.add(new Register(
                999, // id
                "Test User",
                "testuser@gmail.com",
                "test123",
                8123456789012L, // nomor telepon contoh
                'L',
                "2000-01-01",
                "Jl. Test No. 1"
            ));
        } catch (Exception e) {
            // Tidak perlu aksi, hanya untuk inisialisasi test
        }

        // Data galon (stok utama)
        Galon[] galons = {
            new Galon(1, 19.0, "Aqua", 50000, 10),
            new Galon(2, 19.0, "Club", 45000, 0),
            new Galon(3, 19.0, "ron 88", 45000, 11)
        };

        // Data refill, gunakan referensi ke galon yang sama untuk sinkronisasi stok
        Refill[] refills = {
            new Refill(1, 19.0, "Aqua", galons[0], 12000),
            new Refill(2, 19.0, "Club", galons[1], 10000),
            new Refill(3, 19.0, "ron 88", galons[2], 7000),
            new Refill(4, 19.0, "Air Depot (IsiAir)", null, 6000) // Tidak ada galon terkait
        };

        // Data reward
        Reward[] rewards = {
            new Reward(101, "Gratis Ongkir", 100, "Berlaku untuk 1x refill galon"),
            new Reward(102, "Gratis Refil", 50, "Refil galon gratis 1x"),
            new Reward(103, "Promo Diskon", 25, "Diskon 10% untuk isi ulang berikutnya")
        };

        // Data delivery
        HashMap<Integer, Delivery> orders = new HashMap<>();

        // Tambahkan keranjang global
        Keranjang keranjang = new Keranjang();

        // ArrayList untuk menampung data pembelian dari menu beli galon
        ArrayList<String> daftarPembelianGalon = new ArrayList<>();

        // === LOGIN/REGISTER LOOP ===
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
                // Register
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
                        // Hilangkan semua karakter selain angka
                        String digitsOnly = birthday.replaceAll("[^0-9]", "");
                        if (digitsOnly.length() != 8) {
                            System.out.println("Tanggal lahir harus 8 digit angka (format: yyyy-mm-dd atau yyyymmdd)");
                            continue;
                        }
                        if (!digitsOnly.matches("\\d{8}")) {
                            System.out.println("Tanggal lahir harus berupa angka");
                            continue;
                        }
                        // Jika user tidak pakai '-', formatkan ke yyyy-mm-dd
                        if (!birthday.contains("-")) {
                            birthday = digitsOnly.substring(0, 4) + "-" + digitsOnly.substring(4, 6) + "-" + digitsOnly.substring(6, 8);
                        }
                        break;
                    }

                    Register reg = new Register(
                        daftarRegister.size() + 1, // id
                        name,
                        email,
                        password,
                        phoneNumber, // gunakan long, jangan cast ke int
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
                // Login
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
                        // Admin login: bypass domain check
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
                        // Buat objek Admin (data bisa disesuaikan jika perlu)
                        Admin admin = new Admin(0, "Admin", "admin", "admin", "General");
                        int choice = -1;
                        do {
                            System.out.println("=== Menu Admin ===");
                            System.out.println("1. Admin");
                            System.out.println("2. Kurir");
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
                                    // Tampilkan submenu admin
                                    int adminMenu = -1;
                                    do {
                                        System.out.println("=== Admin ===");
                                        System.out.println("1. Kelola Stok");
                                        System.out.println("2. Kelola Pesanan");
                                        System.out.println("0. Keluar");
                                        System.out.print("Pilih menu: ");
                                        try {
                                            adminMenu = Integer.parseInt(scanner.nextLine());
                                        } catch (NumberFormatException e) {
                                            System.out.println("Pilihan tidak valid.");
                                            continue;
                                        }
                                        switch (adminMenu) {
                                            case 1:
                                                System.out.println("Kelola Stok");
                                                // Tambahkan logika kelola stok di sini jika diperlukan
                                                break;
                                            case 2:
                                                System.out.println("Kelola Pesanan");
                                                // Tambahkan logika kelola pesanan di sini jika diperlukan
                                                break;
                                            case 0:
                                                System.out.println("Keluar dari menu admin.");
                                                break;
                                            default:
                                                System.out.println("Pilihan tidak valid.");
                                        }
                                    } while (adminMenu != 0);
                                    break;
                                case 2:
                                    System.out.println("Kurir");
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

                    Register regFound = null;
                    for (Register reg : daftarRegister) {
                        if (reg.getEmail().equals(email)) {
                            regFound = reg;
                            break;
                        }
                    }
                    if (regFound == null) {
                        System.out.println("Email belum terdaftar.");
                        continue;
                    } else if (!regFound.getPassword().equals(password)) {
                        System.out.println("Email atau password salah.");
                        continue;
                    } else {
                        currentUser = regFound;
                        System.out.println("Login berhasil. Selamat datang, " + currentUser.getName());
                        // Inisialisasi chat channel untuk user yang login
                        chatChannel = new ChatChannel(1, admin, currentUser);
                        break;
                    }
                }
            } else if (menu == 3) {
                // Lupa Password (OTP)
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
                scanner.close();
                return;
            } else {
                System.out.println("Menu tidak tersedia, silahkan pilih sesuai nomor pada menu");
            }
        }

        // === MENU UTAMA SETELAH LOGIN ===
        while (true) {
            System.out.println("\n===== MENU UTAMA isiAir =====");
            System.out.println("1. Beli galon");
            System.out.println("2. Isi Ulang galon");
            System.out.println("3. Pesanan Saya");
            System.out.println("4. Keranjang");
            System.out.println("5. Chat");
            System.out.println("6. Profile"); // Tambahkan menu Profile
            System.out.println("7. Notifikasi");
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
                    for (Galon g : galons) {
                        int hargaDenganGalon = g.getPrice();
                        System.out.printf("| %-3d | %-10s | %-7.1f | Rp%-16d | %-5d |\n",
                                g.getId(), g.getBrand(), g.getVolume(), hargaDenganGalon, g.getStock());
                    }
                    // Langsung input ID galon
                    System.out.println("0. Kembali ke menu utama");
                    int pilihan;
                    Galon galonDipilih = null;
                    while (true) {
                        System.out.print("Pilih Id Galon: ");
                        pilihan = scanner.nextInt();
                        scanner.nextLine();
                        if (pilihan == 0) {
                            break;
                        }
                        galonDipilih = null;
                        for (Galon g : galons) {
                            if (g.getId() == pilihan) {
                                galonDipilih = g;
                                break;
                            }
                        }
                        if (galonDipilih == null) {
                            System.out.println("Pilihan tidak valid.");
                            continue;
                        }
                        if (galonDipilih.getStock() == 0) {
                            System.out.println("Mohon maaf barang yang anda pilih tidak tersedia, Harap pili kembali");
                            continue;
                        }
                        break;
                    }
                    if (pilihan == 0) {
                        break;
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
                    galonDipilih.reduceStock(jumlahBeli); // stok galon berkurang
                    String item = galonDipilih.getBrand() + " x" + jumlahBeli + " (Dengan galon baru) - Rp" + (hargaDenganGalon * jumlahBeli);
                    keranjang.tambahItem(item);
                    daftarPembelianGalon.add(item); // Tambahkan ke daftar pembelian
                    System.out.println("berhasil menambahkan ke keranjang");
                }
            } else if (menu == 2) {
                // Refil galon
                while (true) {
                    System.out.println("\n========== ISI ULANG GALON ==========");
                    System.out.printf("| %-3s | %-20s | %-7s | %-12s | %-5s |\n",
                            "ID", "Brand", "Volume", "Harga Refill", "Stok");
                    System.out.println("---------------------------------------------------------------------");
                    for (Refill r : refills) {
                        r.printRefillInfo();
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
                            break;
                        }
                        refillDipilih = null;
                        for (Refill r : refills) {
                            if (r.getId() == pilihanGalon) {
                                refillDipilih = r;
                                break;
                            }
                        }
                        if (refillDipilih == null) {
                            System.out.println("Pilihan tidak valid.");
                            continue;
                        }
                        // Sinkronisasi stok: jika ada galon terkait, cek stok galon
                        if (refillDipilih.getGalon() != null && refillDipilih.getGalon().getStock() == 0) {
                            System.out.println("Mohon maaf barang yang anda pilih tidak tersedia, Harap pili kembali");
                            continue;
                        }
                        // Jika tidak ada galon terkait, cek stok refill sendiri (untuk Air Depot)
                        if (refillDipilih.getGalon() == null && refillDipilih.getStock() == 0) {
                            System.out.println("Mohon maaf barang yang anda pilih tidak tersedia, Harap pili kembali");
                            continue;
                        }
                        break;
                    }
                    if (pilihanGalon == 0) {
                        break;
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
                    String item = refillDipilih.getBrand() + " x" + jumlahRefill + " (Isi ulang) - Rp" + (refillDipilih.getRefillPrice() * jumlahRefill);
                    keranjang.tambahItem(item);
                    daftarPembelianGalon.add(item); // Tambahkan ke daftar pembelian
                    System.out.println("berhasil menambahkan ke keranjang");
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
                    keranjang.tampilkanKeranjang(daftarPembelianGalon, galons);
                    System.out.println("1. Checkout");
                    System.out.println("2. Kembali");
                    System.out.println("3. Hapus item");
                    System.out.println("4. Edit item");
                    System.out.print("Pilih menu: ");
                    String pilihKeranjang = scanner.nextLine();
                    if (pilihKeranjang.equals("1")) {
                        if (daftarPembelianGalon.isEmpty()) {
                            System.out.println("keranjang kosong");
                        } else {
                            System.out.println("Checkout berhasil. Terima kasih telah berbelanja!");
                            daftarPembelianGalon.clear();
                            keranjang.kosongkanKeranjang();
                        }
                    } else if (pilihKeranjang.equals("2")) {
                        break;
                    } else if (pilihKeranjang.equals("3")) {
                        // Hapus item
                        keranjang.tampilkanKeranjang(daftarPembelianGalon, galons);
                        System.out.print("Masukkan nomor item yang ingin dihapus (0 untuk batal): ");
                        int nomorHapus = scanner.nextInt();
                        scanner.nextLine();
                        if (nomorHapus == 0) {
                            System.out.println("Batal menghapus item.");
                            continue;
                        }
                        keranjang.hapusItem(nomorHapus, galons, refills);
                        System.out.println("Item berhasil dihapus.");
                    } else if (pilihKeranjang.equals("4")) {
                        // Edit item
                        keranjang.tampilkanKeranjang(daftarPembelianGalon, galons);
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
                            keranjang.editItem(nomorEdit, jumlahBaru, galons, refills);
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
                // Notifikasi
                System.out.println("Fitur notifikasi belum tersedia.");
            } else if (menu == 8) {
                // Logout
                System.out.println("Anda telah logout.");
                currentUser = null;
                // Kembali ke login/register
                while (currentUser == null) {
                    System.out.println("\n===== SELAMAT DATANG DI isiAir =====");
                    System.out.println("1. Register");
                    System.out.println("2. Login");
                    System.out.println("3. Lupa Password");
                    System.out.println("4. Keluar");
                    System.out.print("Pilih menu: ");
                    String menuInputLogout = scanner.nextLine();
                    int menuLogout;
                    try {
                        menuLogout = Integer.parseInt(menuInputLogout);
                    } catch (NumberFormatException e) {
                        System.out.println("Pilihan anda harus angka sesuai yang ada di menu");
                        continue;
                    }

                    if (menuLogout == 1) {
                        // Register
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
                            String phoneInput2;
                            long phoneNumber2;
                            while (true) {
                                System.out.print("Nomor Telepon: ");
                                phoneInput2 = scanner.nextLine();
                                if (phoneInput2.isEmpty()) {
                                    System.out.println("Nomor Telepon tidak boleh kosong, Harap isi terlebih dahulu");
                                    continue;
                                }
                                if (!phoneInput2.matches("\\d+")) {
                                    System.out.println("Harap masukan nomor telepon yang benar");
                                    continue;
                                }
                                if (phoneInput2.length() > 13) {
                                    System.out.println("Maksimal nomor telepon hanya 13 digit");
                                    continue;
                                }
                                boolean phoneExists = false;
                                for (Register reg : daftarRegister) {
                                    if (String.valueOf(reg.getPhoneNumber()).equals(phoneInput2)) {
                                        phoneExists = true;
                                        break;
                                    }
                                }
                                if (phoneExists) {
                                    System.out.println("nomor telepon sudah digunakan");
                                    continue;
                                }
                                try {
                                    phoneNumber2 = Long.parseLong(phoneInput2);
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
                                // Hilangkan semua karakter selain angka
                                String digitsOnly = birthday.replaceAll("[^0-9]", "");
                                if (digitsOnly.length() != 8) {
                                    System.out.println("Tanggal lahir harus 8 digit angka (format: yyyy-mm-dd atau yyyymmdd)");
                                    continue;
                                }
                                if (!digitsOnly.matches("\\d{8}")) {
                                    System.out.println("Tanggal lahir harus berupa angka");
                                    continue;
                                }
                                // Jika user tidak pakai '-', formatkan ke yyyy-mm-dd
                                if (!birthday.contains("-")) {
                                    birthday = digitsOnly.substring(0, 4) + "-" + digitsOnly.substring(4, 6) + "-" + digitsOnly.substring(6, 8);
                                }
                                break;
                            }

                            Register reg = new Register(
                                daftarRegister.size() + 1, // id
                                name,
                                email,
                                password,
                                phoneNumber2, // gunakan long, jangan cast ke int
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
                    } else if (menuLogout == 2) {
                        // Login
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
                                // Admin login: bypass domain check
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
                                    System.out.println("1. Admin");
                                    System.out.println("2. Kurir");
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
                                            // Tampilkan submenu admin
                                            int adminMenu = -1;
                                            do {
                                                System.out.println("=== Admin ===");
                                                System.out.println("1. Kelola Stok");
                                                System.out.println("2. Kelola Pesanan");
                                                System.out.println("0. Keluar");
                                                System.out.print("Pilih menu: ");
                                                try {
                                                    adminMenu = Integer.parseInt(scanner.nextLine());
                                                } catch (NumberFormatException e) {
                                                    System.out.println("Pilihan tidak valid.");
                                                    continue;
                                                }
                                                switch (adminMenu) {
                                                    case 1:
                                                        System.out.println("Kelola Stok");
                                                        // Tambahkan logika kelola stok di sini jika diperlukan
                                                        break;
                                                    case 2:
                                                        System.out.println("Kelola Pesanan");
                                                        // Tambahkan logika kelola pesanan di sini jika diperlukan
                                                        break;
                                                    case 0:
                                                        System.out.println("Keluar dari menu admin.");
                                                        break;
                                                    default:
                                                        System.out.println("Pilihan tidak valid.");
                                                }
                                            } while (adminMenu != 0);
                                            break;
                                        case 2:
                                            System.out.println("Kurir");
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

                            Register regFound = null;
                            for (Register reg : daftarRegister) {
                                if (reg.getEmail().equals(email)) {
                                    regFound = reg;
                                    break;
                                }
                            }
                            if (regFound == null) {
                                System.out.println("Email belum terdaftar.");
                                continue;
                            } else if (!regFound.getPassword().equals(password)) {
                                System.out.println("Email atau password salah.");
                                continue;
                            } else {
                                currentUser = regFound;
                                System.out.println("Login berhasil. Selamat datang, " + currentUser.getName());
                                // Inisialisasi chat channel untuk user yang login
                                chatChannel = new ChatChannel(1, admin, currentUser);
                                break;
                            }
                        }
                    } else if (menuLogout == 3) {
                        // Lupa Password (OTP)
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
                    } else if (menuLogout == 4) {
                        System.out.println("Terima kasih telah menggunakan isiAir!");
                        scanner.close();
                        return;
                    } else {
                        System.out.println("Menu tidak tersedia, silahkan pilih sesuai nomor pada menu");
                    }
                }
            } else if (menu == 9) {
                System.out.println("Terima kasih telah menggunakan isiAir!");
                break;
            } else {
                System.out.println("Menu tidak tersedia, silahkan pilih sesuai nomor pada menu");
            }
        }
        scanner.close();
    }
}
