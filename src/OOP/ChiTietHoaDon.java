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
	//H??M NH???P XU???T
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
			System.out.print("\nNh???p v??o m?? ho?? ????n: ");
			mahd = sc.nextLine();
			if(mahd.length() < 3 || mahd.length() > 4)
				System.err.println("M?? ho?? ????n b??? thi???u ho???c th???a k?? t???");;
		}while(mahd.length() < 3 || mahd.length() > 4);
		
		
		do {
			dskh.DocFileJava("KhachHang.txt");
			dskh.XuatDanhSach();
			System.out.print("Nh???p v??o m?? kh??ch h??ng: ");
			makh = sc.nextLine();
			if(dskh.TruyenDuLieu_KH(makh) == null)
				System.err.println("\nM?? kh??ch h??ng m?? b???n v???a nh???p kh??ng h???p l??? ho???c kh??ng c?? trong danh s??ch!!!");
		}while(dskh.TruyenDuLieu_KH(makh) == null);
		tenkh = dskh.TruyenDuLieu_KH(makh);
		
		do {
			dsx.DocFileJava("Xe.txt");
			dsx.XuatDanhSach();
			System.out.print("Nh???p v??o m?? s???n ph???m: ");
			masp = sc.nextLine();
			if(!dsx.Search_MaSP(masp)) {
				System.err.println("\nKh??ng t??m th???y m?? s???n ph???m m?? b???n v???a nh???p!!!");
			}
		}while(!dsx.Search_MaSP(masp));
		
		System.err.println("\nNg??y nh???p ho?? ????n ph???i h???p l??? theo c?? ph??p dd/MM/yyyy. N???u kh??ng s??? b??o l???i");
		System.err.println();
		do {
			System.out.print("\nNg??y l???p ho?? ????n: ");
			ngaylaphoadon = sc.nextLine();
			
			if(!CheckDate(ngaylaphoadon)) {
				System.err.println("Ng??y th??ng n??m kh??ng h???p l??. Xin m???i nh???p l???i!!!");
				System.err.println();
			}
				
		}while(!CheckDate(ngaylaphoadon));
		
		
		do {
			dsnv.DocFileJava("NhanVien.txt");
			dsnv.XuatDanhSachNV();
			System.out.print("Nh???p m?? nh??n vi??n ph??? tr??ch: ");
			nhanvien = sc.nextLine();
			if(dsnv.Search_MNV(nhanvien) == null) {
				System.err.println("\nM?? nh??n vi??n kh??ng c?? trong danh s??ch!!!");
			}
		}while(dsnv.Search_MNV(nhanvien) == null);
		nhanvien = dsnv.Search_MNV(nhanvien);
		
		System.err.println();
		System.err.format("S??? l?????ng xe c???a kh??ch h??ng ???? ?????t kh??ng ???????c qu?? s??? l?????ng xe trong kho!!!");
		System.err.println();
		do {
			dsx.DanhSach_SL();
			System.err.println();
			System.err.format("\nM?? s???n ph???m m?? b???n v???a nh???p l?? " + masp + "\n");
			System.err.println();
			System.out.println("Nh???p v??o s??? l?????ng h??ng m?? kh??ch h??ng ???? ?????t: ");
			
			sl = sc.nextInt();
			if(!dsx.Search_SL(sl, masp)){
				System.err.println("\nS??? l?????ng mua nhi???u h??n s??? l?????ng h??ng hi???n c?? ho???c sai d??? li???u s??? l?????ng");
				System.err.println();
			}
				
		}while(!dsx.Search_SL(sl, masp));
		sc.nextLine();
		
		dongia = dsx.Search_DG(masp);
		System.err.println("\nL??u ??: n???u kh??ch h??ng l?? VIP th?? s??? ???????c gi???m 10%. H??y nh???n enter ????? b??? qua l??u ??\n");
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
	//?????C FILE GHI FILE
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
