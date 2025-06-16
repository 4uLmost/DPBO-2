import java.util.ArrayList;
import java.util.Scanner;

public class Register extends User {
    private String address;
    
    private static ArrayList<String> registeredNames = new ArrayList<>();

    // Ubah tipe phoneNumber dari int ke long di konstruktor
    public Register() throws Exception {
        super(id, name, email, password, phoneNumber, gender, birthday);
        if (!email.endsWith("@gmail.com")) {
            throw new Exception("Email harus menggunakan domain @gmail.com");
        }
        this.address = address;
        registeredNames.add(name);
        System.out.println("Registrasi berhasil.");
    }

    // Konstruktor tambahan agar sesuai dengan MainRegister
    public Register(String name, String email, String address, String password) throws Exception {
        super(registeredNames.size() + 1, name, email, password, 0, 'U', ""); // default values
        if (!email.endsWith("@gmail.com")) {
            throw new Exception("Email harus menggunakan domain @gmail.com");
        }
        // Hapus pemeriksaan nama unik
        this.address = address;
        registeredNames.add(name);
        System.out.println("Registrasi berhasil.");
    }

    public static boolean AlreadyUse(String name) {
        // Tidak perlu lagi digunakan, tapi tetap ada untuk kompatibilitas
        return false;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    public void setPassword(String newPassword) {
        this.password = newPassword;
    }

    public void printInfo() {
        System.out.println("===== INFO REGISTRASI =====");
        System.out.println("Nama: " + getName());
        System.out.println("Email: " + getEmail());
        System.out.println("Alamat: " + address);
        System.out.println("Nomor Telepon: " + getPhoneNumber());
        System.out.println("Jenis Kelamin: " + getGender());
        System.out.println("Tanggal Lahir: " + getBirthday());
        System.out.println("===========================");
    }

    public void registerInput(){
        Scanner scanner = new Scanner(System.in);
        ArrayList<Register> daftarRegister = new ArrayList<>();
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
                            
                        )
            // id
            // gunakan long, jangan cast ke int
            ;
                        daftarRegister.add(reg);
                        reg.printInfo();
                        System.out.println("Silakan login untuk melanjutkan.");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

    }
}
