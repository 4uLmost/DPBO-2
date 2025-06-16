import java.util.ArrayList;

public class Register extends User {
    private String address;
    
    private static ArrayList<String> registeredNames = new ArrayList<>();

    public Register(int id, String name, String email, String password, long phoneNumber, char gender, String birthday, String address) throws Exception {
        super(id, name, email, password, phoneNumber, gender, birthday);
        if (!email.endsWith("@gmail.com")) {
            throw new Exception("Email harus menggunakan domain @gmail.com");
        }
        this.address = address;
        registeredNames.add(name);
        System.out.println("Registrasi berhasil.");
    }

    public Register(String name, String email, String address, String password) throws Exception {
        super(registeredNames.size() + 1, name, email, password, 0, 'U', ""); // default values
        if (!email.endsWith("@gmail.com")) {
            throw new Exception("Email harus menggunakan domain @gmail.com");
        }
        this.address = address;
        registeredNames.add(name);
        System.out.println("Registrasi berhasil.");
    }

    public static boolean AlreadyUse(String name) {
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
}