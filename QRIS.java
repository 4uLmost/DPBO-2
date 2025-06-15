public class QRIS extends Pembayaran {
    private int qrisID;

    public QRIS(int price, String metodePembayaran, String status, int id, int qrisID) {
        super(price, metodePembayaran, status, id);
        this.qrisID = qrisID;
    }

    public void tampilkanQr() {
        System.out.println("Silakan scan QR berikut untuk pembayaran ID: " + qrisID);
    }

    public void verifikasiPembayaran() {
        System.out.println("Pembayaran QRIS diverifikasi untuk ID: " + qrisID);
    }
}
