package OOP;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class NhanVien extends Person {
	SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	private String manv, ngayvaolam, sdt, chucvu;
	private float luong;
	static int n;
	//CONSTRUCTOR
	public NhanVien() {
		super();
		manv = "";
		ngayvaolam = "";
		sdt = "";
		chucvu = "";
		luong = 0;
	}
	public NhanVien(String ho, String ten, String birthdate, String gioitinh, String mnv, String nvl, String sdt, String chucvu, float luong) {
		super(ho, ten, birthdate, gioitinh);
		manv = mnv;
		ngayvaolam = nvl;
		this.sdt = sdt;
		this.chucvu = chucvu;
		this.luong = luong;
	}
	public NhanVien(NhanVien a) {
		super((Person) a);
		manv = a.manv;
		ngayvaolam = a.ngayvaolam;
		sdt = a.sdt;
		chucvu = a.chucvu;
		luong = a.luong;
	}
	
	//SETTER
	public void setManv(String manv) {
		this.manv = manv;
	}
	public void setNgayvaolam(String ngayvaolam) {
		this.ngayvaolam = ngayvaolam;
	}
	public void setSdt(String sdt) {
		this.sdt = sdt;
	}
	public void setCV(String chucvu) {
		this.chucvu = chucvu;
	}
	public void setLuong(float luong) {
		this.luong = luong;
	}
	
	//GETTER
	public String getManv() {
		return manv;
	}
	public String getNgayvaolam() {
		return ngayvaolam;
	}
	public String getSdt() {
		return sdt;
	}
	public String getCV() {
		return chucvu;
	}
	public float getLuong() {
		return luong;
	}
		
	//CAC HAM KHAC
	public boolean CheckNVL(String date) {
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
		do {
			System.out.println("\nKhi nhập vào mã nhân viên, lưu ý chỉ cho phép tối thiếu 3 kí tự hoặc tối đa 5 kí tự");
			System.out.print("Nhập vào mã nhân viên: ");
			manv = sc.nextLine();
			if(manv.length() < 3 && manv.length() > 5)
				System.err.println("Bạn đã nhập thừa hoặc thiếu kí tự");
		}while(manv.length() < 3 && manv.length() > 5);
		
		
		System.out.print("Họ và tên lót của nhân viên: ");
		ho = sc.nextLine();
		
		System.out.print("Tên của nhân viên: ");
		ten = sc.nextLine();
		
		System.out.print("Giới tính: ");
		gioitinh = sc.nextLine();
		
		System.err.println("\nNgày tháng năm sinh của nhân viên phải hợp lệ theo cú pháp dd/MM/yyyy. Nếu không sẽ báo lỗi");
		do {
			System.out.print("Ngày tháng năm sinh: ");
			birthdate = sc.nextLine();
			if(!CheckNVL(birthdate)) {
				System.err.print("\nNgày tháng năm sinh không hợp lệ. Xin mời nhập lại!!!");
				System.err.println();
			}
		}while(!CheckNVL(birthdate));
		
		System.err.println("\nNgày vào làm của nhân viên phải hợp lệ theo cú pháp dd/MM/yyyy. Nếu không sẽ báo lỗi");
		do {
			System.out.print("Ngày vào làm: ");
			ngayvaolam = sc.nextLine();
			if(!CheckNVL(ngayvaolam)) {
				System.err.print("\nNgày vào làm không hợp lệ. Xin mời nhập lại!!!");
				System.err.println();
			}
		}while(!CheckNVL(ngayvaolam));
		
		do {
			System.err.println("\nKhi nhâp SĐT, lưu ý không được nhập quá 11 số hoặc ít hơn 10 số");
			System.out.print("Số điện thoại: ");
			sdt = sc.nextLine();
			if(sdt.length() < 10 && sdt.length() > 11)
				System.out.println("Bạn đã nhập thiếu hoặc thừa số trong số điện thoại");
		}while(sdt.length() < 10 && sdt.length() > 11);
		
		
		System.out.print("Chức vụ: ");
		chucvu = sc.nextLine();
		
		System.out.print("Lương: ");
		luong = sc.nextInt();
		sc.nextLine();
	}
	@Override
	public void Xuat() {
		System.out.format(" %8s | %28s | %10s | %5s | %15s | %15s | %13s | %20s | %15s ||\n", manv,  ho, ten, gioitinh, birthdate, ngayvaolam, sdt, chucvu, luong);
	}
	public void GhiFile(String filename) throws IOException {
		DataOutputStream dos = new DataOutputStream(new FileOutputStream(filename, Boolean.TRUE));
		dos.writeUTF(manv);
		dos.writeUTF(ho);
		dos.writeUTF(ten);
		dos.writeUTF(gioitinh);
		dos.writeUTF(birthdate);
		dos.writeUTF(ngayvaolam);
		dos.writeUTF(sdt);
		dos.writeUTF(chucvu);
		dos.writeFloat(luong);
		dos.close();
	}
}
