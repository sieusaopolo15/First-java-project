package OOP;

import java.util.Scanner;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ChiTietHoaDon {
	DanhSachNhanVien dsnv = new DanhSachNhanVien();
	DanhSachXe dsx = new DanhSachXe();
	DanhSachKhachHang dskh = new DanhSachKhachHang();
	DanhSachHoaDon dshd = new DanhSachHoaDon();
	
	SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	private String mahd, makh, tenkh, masp, ngaylaphoadon, nhanvien;
	private int sl, giamgia;
	private float dongia;
	private double thanhtien;
	Scanner sc = new Scanner(System.in);
	//CONSTRUCTOR
	public ChiTietHoaDon() {

	}
	public ChiTietHoaDon(String mahd, String makh, String tenkh, String nhanvien, String ngaylaphoadon, String masp, int sl, float dongia, int giamgia, double thanhtien) {
		this.mahd = mahd;
		this.makh = makh;
		this.tenkh = tenkh;
		this.nhanvien = nhanvien;
		this.ngaylaphoadon = ngaylaphoadon;
		this.masp = masp;
		this.sl = sl;
		this.dongia = dongia;
		this.giamgia = giamgia;
		this.thanhtien = thanhtien;
		
	}
		
	//SETTER
	public void setMahd(String mahd) {
		this.mahd = mahd;
	}
	public void setMakh(String makh) {
		this.makh = makh;
	}
	public void setTenkh(String tenkh) {
		this.tenkh = tenkh;
	}
	public void setMasp(String masp) {
		this.masp = masp;
	}
	public void setNgaylaphoadon(String ngaylaphoadon) {
		this.ngaylaphoadon = ngaylaphoadon;
	}
	public void setNhanvien(String nhanvien) {
		this.nhanvien = nhanvien;
	}
	public void setDongia(float dongia) {
		this.dongia = dongia;
	}
	public void setSl(int sl) {
		this.sl = sl;
	}
	public void setGiamgia(int giamgia) {
		this.giamgia = giamgia;
	}
	
	//GETTER
	public String getMahd() {
		return mahd;
	}
	public String getMakh() {
		return makh;
	}
	public String getTenkh() {
		return tenkh;
	}
	public String getMasp() {
		return masp;
	}
	public String getNgaylaphoadon() {
		return ngaylaphoadon;
	}
	public String getNhanvien() {
		return nhanvien;
	}
	public float getDongia() {
		return dongia;
	}
	public int getSl() {
		return sl;
	}
	public int getGiamgia() {
		return giamgia;
	}
	//HÀM NHẬP XUẤT
	public boolean CheckDate(String date) {
		df.setLenient(false);
		try {
			df.parse(date);
		}catch(ParseException e) {
			return false;
		}
		return true;
	}
	
	public void Nhap() {
		do {
			System.out.print("\nNhập vào mã hoá đơn: ");
			mahd = sc.nextLine();
			if(mahd.length() < 3 || mahd.length() > 4)
				System.err.println("Mã hoá đơn bị thiếu hoặc thừa kí tự");;
		}while(mahd.length() < 3 || mahd.length() > 4);
		
		
		do {
			dskh.DocFileJava("KhachHang.txt");
			dskh.XuatDanhSach();
			System.out.print("Nhập vào mã khách hàng: ");
			makh = sc.nextLine();
			if(dskh.TruyenDuLieu_KH(makh) == null)
				System.err.println("\nMã khách hàng mà bạn vừa nhập không hợp lệ hoặc không có trong danh sách!!!");
		}while(dskh.TruyenDuLieu_KH(makh) == null);
		tenkh = dskh.TruyenDuLieu_KH(makh);
		
		do {
			dsx.DocFileJava("Xe.txt");
			dsx.XuatDanhSach();
			System.out.print("Nhập vào mã sản phẩm: ");
			masp = sc.nextLine();
			if(!dsx.Search_MaSP(masp)) {
				System.err.println("\nKhông tìm thấy mã sản phẩm mà bạn vừa nhập!!!");
			}
		}while(!dsx.Search_MaSP(masp));
		
		System.err.println("\nNgày nhập hoá đơn phải hợp lệ theo cú pháp dd/MM/yyyy. Nếu không sẽ báo lỗi");
		System.err.println();
		do {
			System.out.print("\nNgày lập hoá đơn: ");
			ngaylaphoadon = sc.nextLine();
			
			if(!CheckDate(ngaylaphoadon)) {
				System.err.println("Ngày tháng năm không hợp lê. Xin mời nhập lại!!!");
				System.err.println();
			}
				
		}while(!CheckDate(ngaylaphoadon));
		
		
		do {
			dsnv.DocFileJava("NhanVien.txt");
			dsnv.XuatDanhSachNV();
			System.out.print("Nhập mã nhân viên phụ trách: ");
			nhanvien = sc.nextLine();
			if(dsnv.Search_MNV(nhanvien) == null) {
				System.err.println("\nMã nhân viên không có trong danh sách!!!");
			}
		}while(dsnv.Search_MNV(nhanvien) == null);
		nhanvien = dsnv.Search_MNV(nhanvien);
		
		System.err.println();
		System.err.format("Số lượng xe của khách hàng đã đặt không được quá số lượng xe trong kho!!!");
		System.err.println();
		do {
			dsx.DanhSach_SL();
			System.err.println();
			System.err.format("\nMã sản phẩm mà bạn vừa nhập là " + masp + "\n");
			System.err.println();
			System.out.println("Nhập vào số lượng hàng mà khách hàng đã đặt: ");
			
			sl = sc.nextInt();
			if(!dsx.Search_SL(sl, masp)){
				System.err.println("\nSố lượng mua nhiều hơn số lượng hàng hiện có hoặc sai dữ liệu số lượng");
				System.err.println();
			}
				
		}while(!dsx.Search_SL(sl, masp));
		sc.nextLine();
		
		dongia = dsx.Search_DG(masp);
		System.err.println("\nLưu ý: nếu khách hàng là VIP thì sẽ được giảm 10%. Hãy nhấn enter để bỏ qua lưu ý\n");
		sc.nextLine();
		if(dskh.Search_LoaiKH(makh) != null) {
			giamgia = 10;
			thanhtien = 1.0 * (sl * dongia) - (sl * dongia * giamgia)/ 100;
		}
		else
			if(dskh.Search_LoaiKH(makh) == null) {
				giamgia = 0;
				thanhtien = sl * dongia;
			}
	}
	
	public void Xuat() {
		System.out.format(" %10s | %13s | %30s | %25s | %15s | %15s | %15s | %15s | %15s | %15s ||\n", mahd, makh, tenkh, nhanvien, ngaylaphoadon, masp, sl, dongia, giamgia, thanhtien);
	}
	//ĐỌC FILE GHI FILE
	public void GhiFile(String filename) throws IOException {
		DataOutputStream dos = new DataOutputStream(new FileOutputStream(filename, Boolean.TRUE));
		dos.writeUTF(mahd);
		dos.writeUTF(makh);
		dos.writeUTF(tenkh);
		dos.writeUTF(nhanvien);
		dos.writeUTF(ngaylaphoadon);
		dos.writeUTF(masp);
		dos.writeInt(sl);
		dos.writeFloat(dongia);
		dos.writeInt(giamgia);
		dos.writeDouble(thanhtien);
		dos.close();
	}
}
