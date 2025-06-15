public class Reward {
	private int id;
    private String name;
    private int pointCost;
    private String description;
    private User receiver;

    public Reward(int id, String name, int pointCost, String description) {
        this.id = id;
        this.name = name;
        this.pointCost = pointCost;
        this.description = description;
        this.receiver = null;
    }

    public void redeem(User user) {
        if (user.getPoints() >= pointCost) {
            user.decreasePoints(pointCost);
            this.receiver = user;
            System.out.println("Reward \"" + name + "\" berhasil ditukar!");
        } else {
            System.out.println("Poin tidak mencukupi untuk menukar reward.");
        }
    }

    public void printInfo() {
        System.out.println("Reward: " + name + ", Poin: " + pointCost);
        if (receiver != null) {
            System.out.println("Sudah ditebus oleh: " + receiver.getName());
        } else {
            System.out.println("Belum ditebus.");
        }
    }

    public String getName() {
        return this.name;
    }

    public int getPointCost() {
        return this.pointCost;
    }
}