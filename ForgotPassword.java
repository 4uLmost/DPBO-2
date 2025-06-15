import java.util.Random;

public class ForgotPassword extends User {
    private int OTPCode;
    
    private static final Random RANDOM = new Random();

    public ForgotPassword(int id, String name, String email, String password, long phoneNumber, char gender, String birthday) {
        super(id, name, email, password, phoneNumber, gender, birthday);
        this.OTPCode = generateOTP();
    }

    private int generateOTP() {
        return 1000 + RANDOM.nextInt(9000);
    }

    public int getOTPCode() {
        return OTPCode;
    }

    public void resetPasswordWithOTP(int inputOTP, String newPassword) {
        if (inputOTP == OTPCode) {
            super.resetPassword(newPassword);
            System.out.println("Password berhasil diubah dengan OTP.");
        } else {
            System.out.println("Kode OTP tidak valid.");
        }
    }

    @Override
    public int getId() {
        return super.getId();
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public String getEmail() {
        return super.getEmail();
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }
}
