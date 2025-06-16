public class Reward {
	private int id;
    private String nama;
    private int poin;
    private String deskripsi;
    private User receiver;

    public Reward(int id, String name, int pointCost, String description) {
        this.id = id;
        this.nama = name;
        this.poin = pointCost;
        this.deskripsi = description;
        this.receiver = null;
    }

    public void redeem(User user) {
        if (user.getPoints() >= poin) {
            user.decreasePoints(poin);
            this.receiver = user;
            System.out.println("Reward \"" + nama + "\" berhasil ditukar!");
        } else {
            System.out.println("Poin tidak mencukupi untuk menukar reward.");
        }
    }

    public void printInfo() {
        System.out.println("Reward: " + nama + ", Poin: " + poin);
        if (receiver != null) {
            System.out.println("Sudah ditebus oleh: " + receiver.getName());
        } else {
            System.out.println("Belum ditebus.");
        }
    }

    public int getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getPoin() {
        return poin;
    }

    public void setPoin(int poin) {
        this.poin = poin;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }
}