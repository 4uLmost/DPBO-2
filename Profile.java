public class Profile implements Identity {
    private int id;
    private String name, email;
    protected String password;
    
    public Profile(int id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public boolean checkPassword(String inputPassword) {
        return inputPassword.equals(password);
    }

    public void resetPassword(String newPassword) {
        this.password = newPassword;
        System.out.println("Password berhasil diubah");
    }
}
