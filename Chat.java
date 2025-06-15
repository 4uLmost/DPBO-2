public class Chat {
	private String message;
	private Profile sender;
	private ChatChannel channel;
	
	public Chat(String message, Profile sender, ChatChannel channel) {
		super();
		this.message = message;
		this.sender = sender;
		this.channel = channel;
	}

	public String getMessage() {
		return message;
	}

	public Profile getSender() {
		return sender;
	}
	
	public void printInfo() {
		System.out.println("From: " + sender.getName());
		System.out.println("Message:\n" + message);
	}
}
