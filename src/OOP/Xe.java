package OOP;

import java.util.Scanner;
import java.io.*;

public abstract class Xe implements Serializable {
	protected String masp, thuonghieu, tensp, mausac, phankhoi, acquy, loaixe;
	protected int sl;
	protected float gia;
	protected double thanhtien;
	public transient Scanner sc = new Scanner(System.in);
	//CONSTRUCTOR
	public Xe() {
		masp = "";
		thuonghieu = "";
		tensp = "";
		mausac = "";
		phankhoi = "";
		acquy = "";
		loaixe = "";
		sl = 0;
		gia = 0;	
	}
	public Xe(String msp, String th, String xx, String color, String pk, String acquy, String l, int soluong, float dg, double tt) {
		masp = msp;
		thuonghieu = th;
		tensp = xx;
		mausac = color;
		phankhoi = pk;
		this.acquy = acquy;
		loaixe = l;
		sl = soluong;
		gia = dg;
		thanhtien = tt;
	}
	public Xe(Xe a) {
		masp = a.masp;
		thuonghieu = a.thuonghieu;
		tensp = a.tensp;
		mausac = a.mausac;
		phankhoi = a.phankhoi;
		acquy = a.acquy;
		loaixe = a.loaixe;
		sl = a.sl;
		gia = a.gia;
	}
	
	//SETTER
	public void setMasp(String masp) {
		this.masp = masp;
	}
	public void setThuonghieu(String thuonghieu) {
		this.thuonghieu = thuonghieu;
	}
	public void setXuatxu(String xuatxu) {
		this.tensp = xuatxu;
	}
	public void setMausac(String mausac) {
		this.mausac = mausac;
	}
	public void setPhankhoi(String phankhoi) {
		this.phankhoi = phankhoi;
	}
	public void setLoaiXe(String loaixe) {
		this.loaixe = loaixe;
	}
	public void setGia(float gia) {
		this.gia = gia;
	}
	public void setSl(int sl) {
		this.sl = sl;
	}
	
	//GETTER
	public String getMasp() {
		return masp;
	}
	public String getThuonghieu() {
		return thuonghieu;
	}
	public String getXuatxu() {
		return tensp;
	}
	public String getMausac() {
		return mausac;
	}
	public String getPhankhoi() {
		return phankhoi;
	}
	public String getAcquy() {
		return acquy;
	}
	public String getLoaiXe() {
		return loaixe;
	}
	public float getGia() {
		return gia;
	}
	public int getSl() {
		return sl;
	}
	//CAC HAM KHAC
	public abstract void Nhap();
	public abstract void Xuat();
	//GHI FILE, ĐỌC FILE
	public void GhiFile(String filename) throws IOException {
		DataOutputStream dos = new DataOutputStream(new FileOutputStream(filename, Boolean.TRUE));
		dos.writeUTF(masp);
		dos.writeUTF(thuonghieu);
		dos.writeUTF(tensp);
		dos.writeUTF(mausac);
		dos.writeUTF(phankhoi);
		dos.writeUTF(acquy);
		dos.writeUTF(loaixe);
		dos.writeInt(sl);
		dos.writeFloat(gia);
		dos.writeDouble(thanhtien);
		dos.close();
	}
}
