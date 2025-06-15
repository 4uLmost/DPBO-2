import java.util.Scanner;
import java.util.HashMap;

public class Delivery {
    private int id;
    private int statusIndex;
    private String[] statusList = {
            "Pesanan Anda akan disiapkan",
            "Pesanan Anda sedang dalam perjalanan",
            "Pesanan Anda sudah sampai"
    };

    public Delivery(int id) {
        this.id = id;
        this.statusIndex = 0;
    }

    public void updateStatus() {
        if (statusIndex < statusList.length) {
            statusIndex++;
        } else {
            System.out.println("Pesanan sudah diterima, tidak ada pembaruan status lagi.");
        }
    }

    public void printInfo() {
    	System.out.println("");
    	System.out.println("====================STATUS=======================");
        System.out.println("ID Pesanan: " + id);
        System.out.println("Status Pengiriman: " + statusList[statusIndex - 1]);
    }

    public boolean isCompleted() {
        return statusIndex >= statusList.length;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashMap<Integer, Delivery> orders = new HashMap<>();

        while (true) {
            System.out.print("Masukkan ID Pesanan (tekan ENTER untuk keluar): ");
            String input = scanner.nextLine();
            if (input.isEmpty()) {
                break; // Keluar dari loop jika user hanya menekan ENTER
            }

            int id;
            try {
                id = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("ID harus berupa angka! Silakan coba lagi.");
                System.out.println();
                continue;
            }

            // Jika ID sudah selesai, tetap tampilkan status "Pesanan Anda sudah sampai"
            if (orders.containsKey(id) && orders.get(id).isCompleted()) {
                System.out.println("Pesanan ID " + id + " sudah sampai.");
            } else {
                Delivery delivery = orders.getOrDefault(id, new Delivery(id));
                delivery.updateStatus();
                delivery.printInfo();

                if (!delivery.isCompleted()) {
                    orders.put(id, delivery);
                }
            }
            System.out.println("Terima kasih!");
            System.out.println();
        }

        scanner.close();
        System.out.println("Program selesai.");
    }
}