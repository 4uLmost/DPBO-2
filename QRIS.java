public class QRIS extends Pembayaran{
	private int qrisID;
	
	public QRIS(int price, String metodePembayaran, String status, int id, int qrisID) {
		super(price, "QRIS", status, id);
		this.qrisID = qrisID;
	}
	public void tampilkanQr() {
		System.out.println("Tampilankan QR untuk id: " + this.qrisID);
	}
	public void verifikasiPembayaran() {
		System.out.println("Pembayaran QRIS untuk id: " + this.qrisID + " sudah terverifikasi");
	}
	
}
