package OOP;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class KhachHang extends Person {
	SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	private String makh, sdt, diachi, loaikh;
	//CONSTRUCTOR
	public KhachHang() {
		super();
		makh = "";
		sdt = "";
		diachi = "";
		loaikh = "";
	}
	public KhachHang(KhachHang a) {
		super((Person) a);
		makh = a.makh;
		sdt = a.sdt;
		diachi = a.diachi;
		loaikh = a.loaikh;
	}
	
	public KhachHang(String ho, String ten, String birthdate, String gioitinh, String makh, String sdt, String loaikh, String diachi) {
		super(ho, ten, birthdate, gioitinh);
		this.makh = makh;
		this.sdt = sdt;
		this.diachi = diachi;
		this.loaikh = loaikh;
	}
	//SETTER	
	public void setMakh(String makh) {
		this.makh = makh;
	}
	public void setSdt(String sdt) {
		this.sdt = sdt;
	}
	public void setDiachi(String diachi) {
		this.diachi = diachi;
	}
	public void setLoaikh(String loaikh) {
		this.loaikh = loaikh;
	}
	
	//GETTER
	public String getMakh() {
		return makh;
	}
	public String getSdt() {
		return sdt;
	}
	public String getDiachi() {
		return diachi;
	}
	public String getLoaikh() {
		return loaikh;
	}
	
	//CAC HAM KHAC
	public boolean CheckBD(String date) {
		df.setLenient(false);
		try {
			df.parse(date);
		}catch(ParseException e) {
			return false;
		}
		return true;
	}
	@Override
	public void Nhap() {
		
			System.out.print("Nhập vào mã khách hàng: ");
			makh = sc.nextLine();
		
		System.out.print("Họ và tên lót: ");
		ho = sc.nextLine();
		
		System.out.print("Tên: ");
		ten = sc.nextLine();
		
		do {
			System.out.print("Nếu là nam thì nhập 1, còn nữ thì nhập 0: ");
			gioitinh = sc.nextLine();
			if(!"0".equals(gioitinh) && !"1".equals(gioitinh)) {
				System.out.println("\nBạn đã nhập sai dữ liệu. Xin mời nhập lại");
			}
		}while(!"0".equals(gioitinh) && !"1".equals(gioitinh));
		
		System.err.println("\nNgày tháng năm sinh của KH phải hợp lệ theo cú pháp dd/MM/yyyy. Nếu không sẽ báo lỗi");
		do {
			System.out.print("Ngày tháng năm sinh của khách hàng: ");
			birthdate = sc.nextLine();
			if(!CheckBD(birthdate)) {
				System.err.print("\nNgày tháng năm sinh không hợp lệ. Xin mời nhập lại!!!");
				System.err.println();
			}
		}while(!CheckBD(birthdate));
		
		do {
			System.out.print("Số điện thoại (nhập đúng 11 số): ");
			sdt = sc.nextLine();
			if(sdt.length() != 11) {
				System.err.println("Bạn đã nhập thiếu hoặc thừa số");
			}
		}while(sdt.length() != 11);
		
		System.out.print("Địa chỉ: ");
		diachi = sc.nextLine();
		
		do {
			System.out.print("Nếu là khách hàng VIP thì nhập 1, còn ngược lại thì nhập 0: ");
			loaikh = sc.nextLine();
			if(!"1".equals(loaikh) && !"0".equals(loaikh)) {
				System.err.println("\nBạn đã nhập sai dữ liệu của loại khách hàng");
			}
		}while(!"1".equals(loaikh) && !"0".equals(loaikh));
	}
	
	@Override
	public void Xuat() {
		System.out.format(" %8s | %28s | %10s | %15s | %6s | %13s | %7s | %30s ||\n", makh, ho, ten, birthdate, gioitinh, sdt, loaikh, diachi);
	}
	public void GhiFile(String filename) throws IOException {
		DataOutputStream dos = new DataOutputStream(new FileOutputStream(filename, Boolean.TRUE));
		dos.writeUTF(makh);
		dos.writeUTF(ho);
		dos.writeUTF(ten);
		dos.writeUTF(birthdate);
		dos.writeUTF(gioitinh);
		dos.writeUTF(sdt);
		dos.writeUTF(loaikh);
		dos.writeUTF(diachi);
		dos.close();
	}
}
