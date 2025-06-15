import java.util.ArrayList;
// import Register jika di package berbeda, contoh:
// import your.package.Register;

public class Login {
    private String username;
    private String password;

    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Method untuk login menggunakan data dari ArrayList Register
    public static boolean login(String username, String password, ArrayList<Register> daftarRegister) {
        for (Register reg : daftarRegister) {
            // Pastikan Register punya getUsername() dan getPassword()
            if (reg.getName().equals(username) && reg.getPassword().equals(password)) {
                return true; // Login berhasil
            }
        }
        return false; // Login gagal
    }
}
