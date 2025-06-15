public class ChatChannel {
	private int id;
	private Admin admin;
	private User user;
	
	public ChatChannel(int id, Admin admin, User user) {
		super();
		this.id = id;
		this.admin = admin;
		this.user = user;
	}
	
	public void printInfo() {
		System.out.println("id: " + id);
		System.out.println("Admin: " + admin.getName());
		System.out.println("User: " + user.getName());
	}
}
