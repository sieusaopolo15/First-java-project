package OOP;

import java.util.Arrays;
import java.util.Scanner;
import java.io.*;

public class DanhSachHoaDon implements ThaoTac {
	ChiTietHoaDon[] hd;
	private int n, a = 0;
	Scanner sc = new Scanner(System.in);
	
	//CONSTRUCTOR
	public DanhSachHoaDon() {
		
	}
	//CÁC HÀM NHẬP XUẤT DANH SÁCH
	public void NhapDanhSach() {
		System.out.print("Nhập vào số lượng hoá đơn: ");
		n = sc.nextInt();
		sc.nextLine();
		hd = new ChiTietHoaDon[n];
		for(int i = 0; i < n; i++) {
			System.out.print("\n\t\t\t\t\tHOÁ ĐƠN THỨ " + (i + 1) + "\n");
			System.err.println("\nKhi nhập mã hoá đơn, chỉ được phép nhập tối thiểu là 3 kí tự và tối đa là 4 kí tự	");
			hd[i] = new ChiTietHoaDon();
			hd[i].Nhap();
			GhiFileJava("HoaDon.txt");
			if(i > 0) {
				ThayDoiMHD(i);
			}
		}
	}
	public void XuatDanhSach(){
		System.out.println("\t\t\t\t\t\t\t\t=====DANH SÁCH HOÁ ĐƠN=====");
		System.out.println("\n================================================================================================================================================================================================================");
		System.out.format("|| %4s | %10s | %10s | %30s | %25s | %15s | %15s | %15s | %15s | %15s | %15s ||\n", "STT", "MÃ HOÁ ĐƠN", "MÃ KHÁCH HÀNG", "HỌ TÊN CỦA KHÁCH HÀNG", "NHÂN VIÊN PHỤ TRÁCH", "NGÀY LẬP HĐ", "MÃ SẢN PHẨM", "SỐ LƯỢNG", "ĐƠN GIÁ", "GIẢM GIÁ SP", "THÀNH TIỀN");
		try {
			for(int i = 0; i < n; i++) {
				System.out.format("|| %4s |", (i + 1));
				hd[i].Xuat();
			}
		}catch(NullPointerException npe) {
			
		}
		System.out.println("================================================================================================================================================================================================================");
		
	}
	
	//CÁC THAO TÁC TRÊN DANH SÁCH
	public void ThayDoiMHD(int i) {
		String hoadon, mhd;
		mhd = hd[i].getMahd();
		do {
			if(KiemTraMHD(mhd, i)) {
				XuatDanhSach();
				System.err.println();
				System.err.format("\nHoá đơn thứ " + (i + 1) + " có mã " + mhd + " bị trùng mã hoá đơn. Ấn phím enter để tiếp tục");
				sc.nextLine();
				System.err.println("Hãy nhập lại mã HĐ cho hoá đơn: ");
				hoadon = sc.nextLine();
				hd[i].setMahd(hoadon);
				GhiFileJava("HoaDon.txt");
				mhd = hd[i].getMahd();
			}
		}while(KiemTraMHD(mhd, i));
	}
	public boolean KiemTraMHD(String mahd, int k) {
		DocFileJava("HoaDon.txt");
		for(int i = a - 1; i >= 0; i--) {
			if(hd[i].getMahd().indexOf(mahd) != -1 && i != k) {
				return true;
			}
		}
		return false;
	}
	//=====THÊM MỘT HOÁ ĐƠN VÀO DANH SÁCH=====
	@Override
	public void Insert(int sl) {
		hd = Arrays.copyOf(hd, n + sl);
		int j = n + sl;
		j -= sl;
		n += sl;
		for(int i = j; i < n; i++) {
			hd[i] = new ChiTietHoaDon();
			System.out.print("\t\t\t\t\tHOÁ ĐƠN THỨ " + (i + 1) + "\n");
			hd[i].Nhap();
			GhiFileJava("HoaDon.txt");
			ThayDoiMHD(i);
		}
		XuatDanhSach();
	}
	//=====XOÁ MỘT HOÁ ĐƠN TRONG DANH SÁCH=====
	@Override
	public void Delete(String mahd) {
		boolean flag = false;
		int j = 0;
		for(int i = 0; i < n; i++) {
			if(hd[i].getMahd().indexOf(mahd) != -1) {
				flag = true;
				j = i;
				break;
			}
		}
		for(int i = j; i < n - 1; i++) {
			hd[i] = hd[i + 1];
		} 
		n--;
		GhiFileJava("HoaDon.txt");
		if(flag)
			System.out.println("\nĐã xoá thành công hoá đơn");
		else 
			System.err.println("\nKhông tìm thấy hoá đơn cần tìm");
	}
	//=====SỬA CHI TIẾT SẢN PHẨM=====
	@Override
	public void Adjust(String mahd) {
		int select = 0;
		do {
			System.out.println("\t\t\t\t\t======BẢNG LỰA CHỌN======");
			System.out.println("\t\t\t\t\t1.Sửa chi tiết hoá đơn");
			System.out.println("\t\t\t\t\t2.Thoát");
			System.out.print("Lựa chọn của bạn là: ");
			select = sc.nextInt();
			sc.nextLine();
			
			if(select < 1 || select > 2)
				System.err.println("\nBạn đã nhập sai lựa chọn của mình. Xin mời nhập lại");
		}while(select < 1 || select > 2);
		for(int i = 0; i < n; i++) {
			if(hd[i].getMahd().indexOf(mahd) != -1) {
				if(select == 1) {
					System.out.print("\nSửa thông tin của hoá đơn thứ " + (i + 1) + "\n");
					System.out.println("\t\t\t\t\t\t\t=====SỬA THÔNG TIN======");
					System.out.println("\n================================================================================================================================================================================================================");					
					System.out.format("|| %4s | %10s | %10s | %30s | %25s | %15s | %15s | %15s | %15s | %15s | %15s ||\n", "STT", "MÃ HOÁ ĐƠN", "MÃ KHÁCH HÀNG", "HỌ TÊN CỦA KHÁCH HÀNG", "NHÂN VIÊN PHỤ TRÁCH", "NGÀY LẬP HĐ", "MÃ SẢN PHẨM", "SỐ LƯỢNG", "ĐƠN GIÁ", "GIẢM GIÁ SP", "THÀNH TIỀN");
					System.out.format("|| %4s |", (i + 1));
					hd[i].Xuat();
					System.out.println("================================================================================================================================================================================================================");					
					hd[i] = new ChiTietHoaDon();
					hd[i].Nhap();
					GhiFileJava("HoaDon.txt");
					ThayDoiMHD(i);
					XuatDanhSach();
					return;
				}
				else
					return;
				
			}
		}
	}
	//=====TÌM KIẾM MÃ HOÁ ĐƠN=====
	public void Search_MaHD(String mahd) {
		System.out.println("\n================================================================================================================================================================================================================");		
		System.out.format("|| %4s | %10s | %10s | %30s | %25s | %15s | %15s | %15s | %15s | %15s | %15s ||\n", "STT", "MÃ HOÁ ĐƠN", "MÃ KHÁCH HÀNG", "HỌ TÊN CỦA KHÁCH HÀNG", "NHÂN VIÊN PHỤ TRÁCH", "NGÀY LẬP HĐ", "MÃ SẢN PHẨM", "SỐ LƯỢNG", "ĐƠN GIÁ", "GIẢM GIÁ SP", "THÀNH TIỀN");
		for(int i = 0; i < n; i++) {
			if(hd[i].getMahd().indexOf(mahd) != -1) {
				System.out.format("|| %4s |", i + 1);
				hd[i].Xuat();
				
				break;
			}
		}
		System.out.println("================================================================================================================================================================================================================");
	}
	public void Search_MKH(String makh) {
		System.out.println("\n================================================================================================================================================================================================================");		
		System.out.format("|| %4s | %10s | %10s | %30s | %25s | %15s | %15s | %15s | %15s | %15s | %15s ||\n", "STT", "MÃ HOÁ ĐƠN", "MÃ KHÁCH HÀNG", "HỌ TÊN CỦA KHÁCH HÀNG", "NHÂN VIÊN PHỤ TRÁCH", "NGÀY LẬP HĐ", "MÃ SẢN PHẨM", "SỐ LƯỢNG", "ĐƠN GIÁ", "GIẢM GIÁ SP", "THÀNH TIỀN");
		for(int i = 0; i < n; i++) {
			if(hd[i].getMakh().indexOf(makh) != -1) {
				System.out.format("|| %4s |", i + 1);
				hd[i].Xuat();
				break;
			}
		}
		System.out.println("================================================================================================================================================================================================================");
	}
	//=====TÌM KIẾM THEO HỌ VÀ TÊN LÓT CỦA NHÂN VIÊN PHỤ TRÁCH
	public void Search_Ho(String ho) {
		System.out.println("\t\t\t\t\t\t\t\t======KẾT QUẢ======");
		System.out.println("\n================================================================================================================================================================================================================");		
		System.out.format("|| %4s | %10s | %10s | %30s | %25s | %15s | %15s | %15s | %15s | %15s | %15s ||\n", "STT", "MÃ HOÁ ĐƠN", "MÃ KHÁCH HÀNG", "HỌ TÊN CỦA KHÁCH HÀNG", "NHÂN VIÊN PHỤ TRÁCH", "NGÀY LẬP HĐ", "MÃ SẢN PHẨM", "SỐ LƯỢNG", "ĐƠN GIÁ", "GIẢM GIÁ SP", "THÀNH TIỀN");
		for(int i = 0; i < n; i++) {
			if(hd[i].getNhanvien().indexOf(ho) != -1) {
				System.out.format("|| %4s |", i + 1);
				hd[i].Xuat();
			}
		}
		System.out.println("================================================================================================================================================================================================================");
	}
	//======TÌM KIẾM MÃ SẢN PHẨM TRONG HOÁ ĐƠN=====
	public void Search_MSP(String msp) {
		System.out.println("\t\t\t\t\t\t\t\t======KẾT QUẢ======");
		System.out.println("\n================================================================================================================================================================================================================");
		System.out.format("|| %4s | %10s | %10s | %30s | %25s | %15s | %15s | %15s | %15s | %15s | %15s ||\n", "STT", "MÃ HOÁ ĐƠN", "MÃ KHÁCH HÀNG", "HỌ TÊN CỦA KHÁCH HÀNG", "NHÂN VIÊN PHỤ TRÁCH", "NGÀY LẬP HĐ", "MÃ SẢN PHẨM", "SỐ LƯỢNG", "ĐƠN GIÁ", "GIẢM GIÁ SP", "THÀNH TIỀN");
		for(int i = 0; i < n; i++) {
			if(hd[i].getMasp().indexOf(msp) != -1) {
				System.out.format("|| %4s |", i + 1);
				hd[i].Xuat();
				break;
			}
		}
		System.out.println("================================================================================================================================================================================================================");
	}
	
	public void Search() {
		int select = 0;
		while(true) {
			System.out.println("\t\t\t\t\t======BẢNG LỰA CHỌN======");
			System.out.println("\t\t\t\t\t1.Ấn phím 1 để tìm kiếm Hoá đơn theo mã");
			System.out.println("\t\t\t\t\t2.Ấn phím 2 để tìm kiếm tên nhân viên phụ trách");
			System.out.println("\t\t\t\t\t3.Ấn phím 3 để tìm kiếm mã khách hàng của hoá đơn");
			System.out.println("\t\t\t\t\t4.Ấn phím 4 để thoát");
			System.out.println("\nLựa chọn của bạn là: ");
			select = sc.nextInt();
			sc.nextLine();
			switch(select) {
				case 1:
					System.out.print("Nhập vào mã hoá đơn cần tìm: ");
					String mahd = sc.nextLine();
					Search_MaHD(mahd);
					break;
				case 2:
					System.out.print("Nhập họ và tên lót hoặc tên của nhân viên phụ trách mà bạn muốn tìm: ");
					String ho = sc.nextLine();
					Search_Ho(ho);
					break;
				case 3:
					System.out.print("Nhập vào mã khách hàng mà bạn muốn tìm trong hoá đơn: ");
					String makh = sc.nextLine();
					Search_MKH(makh);
					break;
				case 4: 
					return;
				default:
					System.err.println("Bạn đã nhập sai lựa chọn của mình. Xin mời vào lại chức năng!!!");
					break;
			}
		}
		
	}
	public void ThaoTac() {
		int select = 0;
		String mahd = "";
		loop:
			while(true) {
				System.out.println("\t\t\t\t\t======BẢNG LỰA CHỌN======");
				System.out.println("\t\t\t\t\t1.Ấn phím 1 để thêm hoá đơn");
				System.out.println("\t\t\t\t\t2.Ấn phím 2 để xoá hoá đơn");
				System.out.println("\t\t\t\t\t3.Ấn phím 3 để sửa thông tin hoá đơn");
				System.out.println("\t\t\t\t\t4.Ấn phím 4 để tìm kiếm hoá đơn");
				System.out.println("\t\t\t\t\t5.Ấn phím 5 để xuất danh sách hoá đơn");
				System.out.println("\t\t\t\t\t6.Ấn phím 6 để dừng các thao tác");
				System.out.print("\nLựa chọn của bạn là: ");
				select = sc.nextInt();
				sc.nextLine();
				switch(select) {
					case 1:
						System.out.print("Nhập vào số lượng hoá đơn cần thêm: ");
						int sl = sc.nextInt();
						Insert(sl);
						break;
					case 2:
						System.out.print("Nhập vào mã hoá đơn cần xoá: ");
						mahd = sc.nextLine();
						Delete(mahd);
						XuatDanhSach();
						break;
					case 3:
						System.out.print("Nhập vào mã hoá đơn cần sửa: ");
						mahd = sc.nextLine();
						Adjust(mahd);
						break;
					case 4:
						Search();
						break;
					case 5: 
						XuatDanhSach();
						break;
					case 6:
						break loop;
					default:
						System.err.println("Bạn đã nhập sai lựa chọn");
						break;
				}
			}
	}
	public void ThongKe() {
		int dem = 0;
		DocFileJava("HoaDon.txt");
		String date;
		System.out.println("\t\t\t\t\t     =====BẢNG THỐNG KÊ=====");
		System.out.println("\t\t\t\t\t================================");
		System.out.format("\t\t\t\t\t|| %16s| %8s ||\n", "NGÀY LẬP HÓA ĐƠN", "BÁN ĐƯỢC");
		for(int i = 0; i < n; i++) {
			date = hd[i].getNgaylaphoadon();
			dem = Dem(date, i);
			if(dem != 0)
				System.out.format("\t\t\t\t\t|| %16s| %8s ||\n", date, dem);
			dem = 0;
		}
		System.out.println("\t\t\t\t\t================================\n");
	}
	
	public int Dem(String date, int k) {
		int dem = 1;
		for(int i = 0; i < n; i++) {
			if(KiemTraNLHD(date, k)){
				if(date.equals(hd[i].getNgaylaphoadon()) && i != k)
					dem++;
			}
			else
				dem = 0;
		}
		return dem;
	}
	
	public boolean KiemTraNLHD(String date, int k) {
		for(int i = k; i < n; i++) {
			if(date.equals(hd[i].getNgaylaphoadon()) && i != k)
				return false;
		}
		return true;
	}
	
	//ĐỌC FILE GHI FILE
	@Override
	public void GhiFileJava(String filename) {
		int j = n;
		try {
			DataOutputStream dos = new DataOutputStream(new FileOutputStream(filename));
			dos.writeInt(j);
			try {
				for(int i = 0; i < j; i++) {
					hd[i].GhiFile(filename);
				}
			}catch(NullPointerException npe) {
					
			}
			dos.close();
		}catch(IOException e) {}
	}
	@Override
	public void DocFileJava(String filename) {
		int i = 0;
		try {
			DataInputStream dis = new DataInputStream(new FileInputStream(filename));
			n = dis.readInt();
			hd = new ChiTietHoaDon[n];
			try {
				while(true) {
					String mahd = dis.readUTF();
					String makh = dis.readUTF();
					String tenkh = dis.readUTF();
					String nhanvien = dis.readUTF();
					String ngaylaphoadon = dis.readUTF();
					String masp = dis.readUTF();
					int sl = dis.readInt();
					float dongia = dis.readFloat();
					int giamgia = dis.readInt();
					double thanhtien = dis.readDouble();
					hd[i] = new ChiTietHoaDon(mahd, makh, tenkh, nhanvien, ngaylaphoadon, masp, sl, dongia, giamgia, thanhtien);
					i++;
				}
			}catch(EOFException e) {}
			finally {
				a = i;
				dis.close();
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
