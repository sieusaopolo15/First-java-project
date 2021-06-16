	package OOP;

public class NhapDanhSachHoaDon {
	public void NhapDanhSach() {
		DanhSachHoaDon dshd = new DanhSachHoaDon();
		//dshd.NhapDanhSach();
		dshd.DocFileJava("HoaDon.txt");
		dshd.XuatDanhSach();
		
		dshd.ThaoTac();
	}
	public void ThongKe() {
		DanhSachHoaDon dshd = new DanhSachHoaDon();
		dshd.ThongKe();
	}
}
