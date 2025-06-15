class Chat {
    private String message;
    private Profile sender;

    public Chat(String message, Profile sender) {
        this.message = message;
        this.sender = sender;
    }

    public void printInfo() {
        System.out.println(sender.getName() + ": " + message);
    }
}