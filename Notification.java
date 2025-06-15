import java.util.Date;

public class Notification {
	private int id;
	private String recipient;
	private String message;
	private Date sentAt;
	public Notification(int id, String recipient, String message, Date sentAt) {
		super();
		this.id = id;
		this.recipient = recipient;
		this.message = message;
		this.sentAt = sentAt;
	}
	public void send() {
		System.out.println("Pesan dikirim ke " + this.recipient + " : " + this.message);
	}
	public void printInfo() {
		System.out.println("----Notification Info----");
		System.out.println("ID: " + this.id);
		System.out.println("Recipient: " + this.recipient);
		System.out.println("Message: " + this.message);
		if (this.sentAt != null) {
		System.out.println("Sent At: " + this.sentAt);
		} else {
			System.out.println("Belum terkirim");
		}
		System.out.println("------------------------");
	}
	
}
	
