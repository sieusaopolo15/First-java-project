package OOP;

public class NhapDanhSachKhachHang {
	public void NhapDanhSach() {
		DanhSachKhachHang dskh = new DanhSachKhachHang();	
		//dskh.NhapDanhSach();
		dskh.DocFileJava("KhachHang.txt");
		dskh.XuatDanhSach();
		
		dskh.ThaoTac();
	}
}
