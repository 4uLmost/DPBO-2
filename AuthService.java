import java.util.ArrayList;

public class AuthService {

    /**
     * Metode untuk memproses login pengguna.
     * @param email Email yang dimasukkan pengguna.
     * @param password Password yang dimasukkan pengguna.
     * @param daftarRegister Daftar semua pengguna yang terdaftar.
     * @return Mengembalikan objek Register jika login berhasil, jika tidak null.
     */
    public Register login(String email, String password, ArrayList<Register> daftarRegister) {
        for (Register reg : daftarRegister) {
            if (reg.getEmail().equals(email) && reg.getPassword().equals(password)) {
                return reg; // Login berhasil, kembalikan objek user
            }
        }
        return null; // Login gagal
    }

    /**
     * Metode untuk mengecek apakah email sudah digunakan.
     * @param email Email yang akan dicek.
     * @param daftarRegister Daftar semua pengguna yang terdaftar.
     * @return true jika sudah digunakan, false jika belum.
     */
    public boolean isEmailTaken(String email, ArrayList<Register> daftarRegister) {
        for (Register reg : daftarRegister) {
            if (reg.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }
    
    // Anda bisa memindahkan logika validasi lainnya ke sini di kemudian hari
    // misalnya, validasi nomor telepon, dll.
}