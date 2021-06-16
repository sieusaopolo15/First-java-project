package OOP;

import java.util.Scanner;
import java.io.*;

public class ChiTietPhieuNhapHang {
	DanhSachXe dsx = new DanhSachXe();
	DanhSachNhanVien dsnv = new DanhSachNhanVien();
	
	private String maphieu, tennv, thuonghieu, tensp, loaixe;
	private int sl;
	private float gia;
	private double thanhtien;
	Scanner sc = new Scanner(System.in);
	
	//CONSTRUCTOR
	public ChiTietPhieuNhapHang() {
		
	}
	public ChiTietPhieuNhapHang(String mp, String tennv, String thuonghieu, String xuatxu, String loaixe, int soluong, float g, double thanhtien) {
		maphieu = mp;
		this.tennv = tennv;
		this.thuonghieu = thuonghieu;
		this.tensp = xuatxu;
		this.loaixe = loaixe;
		sl = soluong;
		gia = g;
		this.thanhtien = thanhtien;
	}
	
	//SETTER
	public void setMaphieu(String maphieu) {
		this.maphieu = maphieu;
	}
	public void setTenNV(String tennv) {
		this.tennv = tennv;
	}
	public void setThuonghieu(String tensp) {
		this.thuonghieu = tensp;
	}
	public void setXuatxu(String xuatxu) {
		this.tensp = xuatxu;
	}
	public void setLoaiXe(String loaixe) {
		this.loaixe = loaixe;
	}
	public void setSl(int sl) {
		this.sl = sl;
	}
	public void setGia(float gia) {
		this.gia = gia;
	}
	public void setThanhTien(double tt) {
		thanhtien = tt;
	}
	
	//GETTER
	public String getMaphieu() {
		return maphieu;
	}
	public String getThuonghieu() {
		return thuonghieu;
	}
	public String getXuatxu() {
		return tensp;
	}
	public String getLoaixe() {
		return loaixe;
	}
	public int getSl() {
		return sl;
	}
	public float getGia() {
		return gia;
	}
	public double getTT() {
		return thanhtien;
	}
	
	//NHẬP XUẤT CHI TIẾT PHIẾU NHẬP
	public void Nhap() {
		System.out.println("Trước khi nhập mã phiếu, không được nhập ít hơn 3 hoặc nhiều hơn 4 kí tự");
		do {
			System.out.print("Nhập vào mã phiếu: ");
			maphieu = sc.nextLine();
			if(maphieu.length() < 3 || maphieu.length() > 4)
				System.err.println("\nBạn đã nhập thiếu hoặc thừa mã phiếu");
		}while(maphieu.length() < 3 || maphieu.length() > 4);
		
		do {
			dsnv.DocFileJava("NhanVien.txt");
			dsnv.XuatDanhSachNV();
			System.out.print("Nhập mã nhân viên phụ trách: ");
			tennv = sc.nextLine();
			if(dsnv.Search_MNV(tennv) == null) {
				System.err.println("\nMã nhân viên không có trong danh sách!!!");
			}
		}while(dsnv.Search_MNV(tennv) == null);
		tennv = dsnv.Search_MNV(tennv);
		
		dsx.DocFileJava("Xe.txt");
		dsx.XuatDanhSach();
		System.out.print("\nNhập vào thương hiệu: ");
		thuonghieu = sc.nextLine();
		
		
		System.err.println("Tên của sản phẩm phải có thương hiệu trong đó. Nếu không thì tên sẽ không hợp lệ");
		do {
			System.out.println("Thương hiệu mà bạn vừa nhập là: " + thuonghieu);
			System.out.print("Tên của sản phầm là: ");
			tensp = sc.nextLine();
			if(tensp.indexOf(thuonghieu) == -1)
				System.err.println("\nTên sản phẩm không hợp lệ!!!");
		}while(tensp.indexOf(thuonghieu) == -1);
		
		do {
			System.err.println("\t\t\t\t\t========LƯU Ý========");
			System.out.println("\n1.Nếu muốn nhập vào thuôc tính xe đạp điện thì nhập theo cú pháp: 'xe dap dien'");
			System.out.println("Nếu muốn nhập vào thuộc tính là xe hơi thì nhập vào cú pháp: 'san' hoặc 'auto'");
			System.out.print("Loại xe: ");
			loaixe = sc.nextLine();
			if(!"xe dap dien".equals(loaixe) && (!"san".equals(loaixe) && !"auto".equals(loaixe)))
				System.err.println("\nBạn đã nhập sai dữ liệu");
		}while(!"xe dap dien".equals(loaixe) && (!"san".equals(loaixe) && !"auto".equals(loaixe)));
		do {
			dsx.DocFileJava("Xe.txt");
			dsx.DanhSach_SL();
			System.out.format("\nTên của sản phẩm vừa nhập về là: " + tensp);
			System.out.print("\nNhập số lượng nhập hàng về: ");
			sl = sc.nextInt();
			if(!dsx.Search_SLPNH(sl, thuonghieu, tensp)){
				System.err.print("\nSố lượng không được phép = 0. Xin mời nhập lại!!!");
				System.err.println();
			}
		}while(!dsx.Search_SLPNH(sl, thuonghieu, tensp));
		sc.nextLine();
		
		
		System.out.print("Giá của mỗi chiếc xe nhập về: ");
		gia = sc.nextFloat();
		
		thanhtien = sl * gia;
		
		thuonghieu.toUpperCase();
	}
	public void Xuat() {
		System.out.format(" %8s | %12s | %32s | %30s | %15s | %8s | %9s | %10s ||\n", maphieu, thuonghieu, tensp, tennv, loaixe, sl, gia, thanhtien);
	}
	//GHI FILE
	public void GhiFile(String filename) throws IOException {
		DataOutputStream dos = new DataOutputStream(new FileOutputStream(filename, Boolean.TRUE));
		dos.writeUTF(maphieu);
		dos.writeUTF(tennv);
		dos.writeUTF(thuonghieu);
		dos.writeUTF(tensp);
		dos.writeUTF(loaixe);
		dos.writeInt(sl);
		dos.writeFloat(gia);
		dos.writeDouble(thanhtien);
		dos.close();
	}
}
