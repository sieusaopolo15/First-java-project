package OOP;

public class NhapDanhSachNhanVien {
	public void NhapDanhSach() {
		DanhSachNhanVien dsnv = new DanhSachNhanVien();
		//dsnv.NhapDanhSachNV();
		dsnv.DocFileJava("NhanVien.txt");
		dsnv.XuatDanhSachNV();
		dsnv.ThaoTac();
	}
}
