package OOP;

import java.util.Scanner;
import java.util.Arrays;
import java.io.*;

public class DanhSachXe implements ThaoTac{
	Xe[] x;
	private int n, a = 0;
	Scanner sc = new Scanner(System.in);
	
	//CONSTRUCTOR
	public DanhSachXe() {
		
	}
	
	//NHẬP DANH SÁCH XUẤT DANH SÁCH
	public void NhapDanhSach() {
		System.out.print("Nhập vào số lượng sản phẩm: ");
		n = sc.nextInt();
		x = new Xe[n];
		int select = 0;
		for(int i = 0; i < n; i++) {
			System.out.println("\t\t\t======BẢNG LỰA CHỌN======");
			System.out.println("1.Sản phẩm là xe đạp điện");
			System.out.println("2.Sản phẩm là xe hơi");
			do {
				System.out.print("Lựa chọn của bạn là gì: ");
				select = sc.nextInt();
				if(select < 1 || select > 2) {
					System.err.println("Bạn đã nhập sai lựa chọn của mình, mù hay sao mà nhập sai vậy!!!");
				}
			}while(select < 1 || select > 2);
			if(select == 1) {
				x[i] = new XeDapDien();
				System.out.print("\n\t\t\t\t\tSẢN PHẨM THỨ " + (i + 1) + " (XE ĐẠP ĐIỆN)\n");
				x[i].Nhap();
				GhiFileJava("Xe.txt");
				sc.nextLine();
				if(i > 0) {
					ThayDoiMSP(i);
				}
			}
			else if(select == 2) {
				System.out.print("\n\t\t\t\t\tSẢN PHẨM THỨ " + (i + 1) + "(XE HƠI)\n");
				x[i] = new XeHoi();
				x[i].Nhap();
				GhiFileJava("Xe.txt");
				sc.nextLine();
				if(i > 0) {
					ThayDoiMSP(i);
				}
			}
		}
	}
	public void XuatDanhSach() {
		System.out.println("\t\t\t\t\t\t\t\t\t\t======DANH SÁCH SẢN PHẨM======");
		System.out.println("==============================================================================================================================================================================================================================");		
		System.out.format("|| %4s | %8s | %12s | %32s | %15s | %11s | %13s | %15s | %15s | %15s | %8s | %15s | %15s ||\n", "STT", "MÃ SP", "THƯƠNG HIỆU", "TÊN SẢN PHẨM", "MÀU SẮC", "PHÂN KHỐI", "ẮC QUY", "LOẠI BÌNH ĐIỆN", "DUNG TÍCH XĂNG", "LOẠI XE", "SỐ LƯỢNG", "ĐƠN GIÁ (ĐÔLA)", "THÀNH TIỀN (ĐÔLA)");
		try {
			for(int i = 0; i < n; i++) {
				System.out.format("|| %4s |", (i + 1));
				x[i].Xuat();
			}
		}catch(NullPointerException npe) {
			
		}
		System.out.println("==============================================================================================================================================================================================================================");
	}
	//KẾT THÚC NHẬP, XUẤT DANH SÁCH
	
	//CÁC HÀM LIÊN KẾT VÀ KIỂM TRA TÍNH ĐÚNG SAI
	public void DanhSach_SL() {
		System.out.println("\t\t=======DANH SÁCH SẢN PHẨM=======");
		System.out.println("======================================================================================================");
		System.out.format("|| %4s | %11s | %12s | %32s | %12s | %10s ||\n", "STT", "MÃ SP", "THƯƠNG HIỆU", "TÊN SẢN PHẨM", "LOẠI XE", "SỐ LƯỢNG");
		for(int i = 0; i < n; i++) {
			System.out.format("|| %4s | %11s | %12s | %32s | %12s | %10s ||\n", (i + 1), x[i].masp, x[i].thuonghieu, x[i].tensp, x[i].loaixe, x[i].sl);
		}
		System.out.println("======================================================================================================");
		
	}
	
	public void ThayDoiMSP(int i) {
		do {
			if(KiemTraMSP(x[i].masp, i)) {
				System.out.println();
				XuatDanhSach();
				System.err.println("\nSản phẩm thứ "+ (i+1) + " có mã " + x[i].masp + " bị trùng mã sản phẩm. Ấn enter để tiếp tục");
				sc.nextLine();
				System.err.print("Nhập lại mã sp cho sản phẩm: ");
				x[i].masp = sc.nextLine();
				GhiFileJava("Xe.txt");
			}
		}while(KiemTraMSP(x[i].masp, i));
	}
	
	public boolean KiemTraMSP(String masanpham, int k) {
		DocFileJava("Xe.txt");
		for(int i = a - 1; i >= 0; i--) {
			if(x[i].masp.indexOf(masanpham) != -1 && i != k) {
				return true;
			}
		}
		return false;
	}
	public boolean Search_MaSP(String masanpham) {
		for(int i = 0; i < n; i++) {
			if(x[i].masp.indexOf(masanpham) != -1) {
				return true;
			}
		}
		return false;
	}
	//KIỂM TRA SỐ LƯỢNG
	public boolean Search_SL(int soluong, String masanpham) {
		for(int i = 0; i < n; i++) {
			if(x[i].masp.indexOf(masanpham) != -1) {
				if(x[i].sl < soluong || soluong < 0)
					return false;
				else{
					return true;
				}
			}
		}
		return true;
	}
	public boolean Search_SLPNH(int sl, String thuonghieu, String tensp) {
		boolean flag = true;
		for(int i = 0; i < n; i++) {
			if(x[i].thuonghieu.indexOf(thuonghieu) != -1 && x[i].tensp.indexOf(tensp) != -1) {
				if(sl <= 0)
					return false;
				else {
					x[i].sl += sl;
					return true;
				}
			} 
				
		}
		if(flag) {
			if(sl <= 0)
				return false;
			return true;
		}
		return false;
	}
	public boolean Search_TT(String thuonghieu) {
		for(int i = 0; i < n; i++) {
			if(x[i].thuonghieu.equals(thuonghieu))
				return true;
		}
		return false;
	}
	//KẾT THÚC KIỂM TRA SỐ LƯỢNG
	public float Search_DG(String masanpham) {
		for(int i = 0; i < n; i++) {
			if(x[i].masp.indexOf(masanpham) != -1) {
				return x[i].gia;
			}
		}
		return 0;
	}
	
	//CÁC THAO TÁC XỬ LÝ TRÊN DANH SÁCH
	
	@Override
	public void Insert(int sl) {
		x = Arrays.copyOf(x, n + sl);
		int select, j = n + sl;
		j -= sl;
		n += sl;
		for(int i = j; i < n; i++) {
			System.out.println("\n\t\t\t\t\t======BẢNG LỰA CHỌN======");
			System.out.println("\t\t\t\t\t1.Thêm sản phẩm là xe đạp điện");
			System.out.println("\t\t\t\t\t2.Thêm Sản phẩm là xe hơi");
			System.out.println("\t\t\t\t\t3.Thoát khỏi việc thêm sản phẩm");
			do {
				System.out.print("Lựa chọn của bạn là: ");
				select = sc.nextInt();
				if(select < 1 || select > 2) {
					System.err.println("Bạn đã nhập sai lựa chọn của mình, xin mời nhập lại");
				}
			}while(select < 1 || select > 2);
			if(select == 1) {
				x[i] = new XeDapDien();
				System.out.print("\t\t\t\t\tSẢN PHÂM THỨ " + (i + 1) + " (XE ĐẠP ĐIỆN)\n");
				x[i].Nhap();
				GhiFileJava("Xe.txt");
				sc.nextLine();
				ThayDoiMSP(i);
			}
			else if(select == 2) {
				x[i] = new XeHoi();
				System.out.print("\t\t\t\t\tSẢN PHÂM THỨ " + (i + 1) + " (XE HƠI)\n");
				x[i].Nhap();
				GhiFileJava("Xe.txt");
				sc.nextLine();
				ThayDoiMSP(i);
			}
			else if(select == 3)
				return;
		}
	}
	@Override
	public void Delete(String masanpham) {
		boolean flag = false;
		int j = 0; 
		for(int i = 0; i < n; i++) {
			if(x[i].masp.indexOf(masanpham) != -1) {
				flag = true;
				j = i;
				break;
			}
		}
		for(int i = j; i < n - 1; i++) {
			x[i] = x[i + 1];
		}
		n--;
		GhiFileJava("Xe.txt");
		if(flag)
			System.out.println("\nĐã xoá thành công");
		else	
			System.err.println("\nKhông tìm thấy mã sản phẩm cần xoá!!!");
	}
	
	@Override
	public void Adjust(String msp) {
		int select = 0;
		boolean flag = false;
		for(int i = 0; i < n; i++) {
			if(x[i].masp.indexOf(msp) != -1) {
				do {
					System.out.println("\n\t\t\t\t\t=======BẢNG LỰA CHỌN=======");
					System.out.println("\t\t\t\t\t1.Ấn phím 1 để sửa thuộc tính sản phẩm thành thuộc tính xe đạp điện");
					System.out.println("\t\t\t\t\t2.Ấn phím 2 để sửa thuộc tính sản phẩm thành thuộc tính xe hơi");
					System.out.println("\t\t\t\t\t3.Ấn phím 3 để thoát");
					System.out.print("\nLựa chọn của bạn là: ");
					select = sc.nextInt();
					sc.nextLine();
					
					if(select < 1 || select > 3)
						System.err.println("Bạn đã nhập sai lựa chọn của mình. Xin mời nhập lại!!!");
				}while(select < 1 || select > 3);
				
				switch(select) {
					case 1:
						flag = true;
						System.out.println("\t\t\t\t\t\t\t\t\t\t=======SỬA THÔNG TIN=======");
						System.out.println("==============================================================================================================================================================================================================================");
						System.out.format("|| %4s | %8s | %12s | %32s | %15s | %11s | %13s | %15s | %15s | %15s | %8s | %15s | %15s ||\n", "STT", "MÃ SP", "THƯƠNG HIỆU", "TÊN SẢN PHẨM", "MÀU SẮC", "PHÂN KHỐI", "ẮC QUY", "LOẠI BÌNH ĐIỆN", "DUNG TÍCH XĂNG", "LOẠI XE", "SỐ LƯỢNG", "ĐƠN GIÁ (ĐÔLA)", "THÀNH TIỀN (ĐÔLA)");
						System.out.format("|| %4s |", i + 1);
						x[i].Xuat();
						System.out.println("==============================================================================================================================================================================================================================");	
						System.out.println("\nSửa thuộc tính sản phẩm thành xe đạp điện");
						x[i] = new XeDapDien();
						x[i].Nhap();
						GhiFileJava("Xe.txt");
						break;
					case 2: 
						flag = true;
						System.out.println("\t\t\t\t\t\t\t\t\t\t=======SỬA THÔNG TIN=======");
						System.out.println("==============================================================================================================================================================================================================================");							
						System.out.format("|| %4s | %8s | %12s | %32s | %15s | %11s | %13s | %15s | %15s | %15s | %8s | %15s | %15s ||\n", "STT", "MÃ SP", "THƯƠNG HIỆU", "TÊN SẢN PHẨM", "MÀU SẮC", "PHÂN KHỐI", "ẮC QUY", "LOẠI BÌNH ĐIỆN", "DUNG TÍCH XĂNG", "LOẠI XE", "SỐ LƯỢNG", "ĐƠN GIÁ (ĐÔLA)", "THÀNH TIỀN (ĐÔLA)");
						System.out.format("|| %4s |", i + 1);
						x[i].Xuat();
						System.out.println("==============================================================================================================================================================================================================================");	
						System.out.println("\nSửa thuộc tính sản phẩm thành xe hơi");
						x[i] = new XeHoi();
						x[i].Nhap();
						GhiFileJava("Xe.txt");
						break;
					case 3:
						flag = true;
						break;
				}
				ThayDoiMSP(i);
			}
		}
		if(flag == false) {
			System.err.println("\nKhông tìm thấy sản phẩm mà bạn cần sửa!!!");
			return;
		}
	}
	public void Search_MSP(String masp) {
		System.out.println("\t\t\t\t\t\t\t\t\t\t=======KẾT QUẢ=======");
		System.out.println("==============================================================================================================================================================================================================================");		
		System.out.format("|| %4s | %8s | %12s | %32s | %15s | %11s | %13s | %15s | %15s | %15s | %8s | %15s | %15s ||\n", "STT", "MÃ SP", "THƯƠNG HIỆU", "TÊN SẢN PHẨM", "MÀU SẮC", "PHÂN KHỐI", "ẮC QUY", "LOẠI BÌNH ĐIỆN", "DUNG TÍCH XĂNG", "LOẠI XE", "SỐ LƯỢNG", "ĐƠN GIÁ (ĐÔLA)", "THÀNH TIỀN (ĐÔLA)");
		for(int i = 0; i < n; i++) {
			if(x[i].masp.indexOf(masp) != -1) {
				System.out.format("|| %4s |", i + 1);
				x[i].Xuat();	
				break;
			}
		}
		System.out.println("==============================================================================================================================================================================================================================");	
	}
	public void Search_ThuongHieu(String th) {
		System.out.println("\t\t\t\t\t\t\t\t\t\t=======KẾT QUẢ=======");
		System.out.println("==============================================================================================================================================================================================================================");			
		System.out.format("|| %4s | %8s | %12s | %32s | %15s | %11s | %13s | %15s | %15s | %15s | %8s | %15s | %15s ||\n", "STT", "MÃ SP", "THƯƠNG HIỆU", "TÊN SẢN PHẨM", "MÀU SẮC", "PHÂN KHỐI", "ẮC QUY", "LOẠI BÌNH ĐIỆN", "DUNG TÍCH XĂNG", "LOẠI XE", "SỐ LƯỢNG", "ĐƠN GIÁ (ĐÔLA)", "THÀNH TIỀN (ĐÔLA)");
		for(int i = 0; i < n; i++) {
			if(x[i].thuonghieu.indexOf(th) != -1) {
				System.out.format("|| %4s |", i + 1);
				x[i].Xuat();
			}
		}
		System.out.println("==============================================================================================================================================================================================================================");	
	}
	public void Search_LoaiXe(String l) {
		System.out.println("\t\t\t\t\t\t\t\t\t\t=======KẾT QUẢ=======");
		System.out.println("==============================================================================================================================================================================================================================");			
		System.out.format("|| %4s | %8s | %12s | %32s | %15s | %11s | %13s | %15s | %15s | %15s | %8s | %15s | %15s ||\n", "STT", "MÃ SP", "THƯƠNG HIỆU", "XUẤT XỨ", "MÀU SẮC", "PHÂN KHỐI", "ẮC QUY", "LOẠI BÌNH ĐIỆN", "DUNG TÍCH XĂNG", "LOẠI XE", "SỐ LƯỢNG", "ĐƠN GIÁ (ĐÔLA)", "THÀNH TIỀN (ĐÔLA)");
		for(int i = 0; i < n; i++) {
			if(x[i].loaixe.indexOf(l) != -1) {
				System.out.format("|| %4s |", (i + 1));
				x[i].Xuat();
				break;
			}
		}
		System.out.println("==============================================================================================================================================================================================================================");	
	}
	public void Search() {
		int select = 0;
		loop:
		while(true) {
			System.out.println("\n\t\t\t\t\t======BẢNG LỰA CHỌN CHỨC NĂNG======");
			System.out.println("\t\t\t\t\t1.Ấn phím 1 để tìm sản phẩm theo mã sản phẩm");
			System.out.println("\t\t\t\t\t2.Ấn phím 2 để tìm sản phẩm tên thương hiệu");
			System.out.println("\t\t\t\t\t3.Ấn phím 3 để tìm sản phẩm theo loại xe");
			System.out.println("\t\t\t\t\t4.Ấn phím 4 để thoát");
			System.out.print("\nLựa chọn của bạn là: ");
			select = sc.nextInt();
			sc.nextLine();
			switch(select) {
				case 1: 
					System.out.println("Nhập vào mã sản phẩm cần tìm: ");
					String masp = sc.nextLine();
					Search_MSP(masp);
					break;
					
				case 2:
					System.out.print("Nhập vào thương hiệu mà bạn muốn tìm: ");
					String thuonghieu = sc.nextLine();
					thuonghieu.toUpperCase();
					Search_ThuongHieu(thuonghieu);
					break;
				
				case 3:
					System.out.println("\n\t\t\t\t\t======BẢNG LỰA CHỌN TÌM KIẾM======");
					System.out.println("\t\t\t\t\t1.Nếu bạn muốn tìm xe đạp điện thì hãy gõ đúng cú pháp 'xe dap dien'");
					System.out.println("\t\t\t\t\t2.Nếu bạn muốn tìm xe hơi số sàn thì gõ vào 'san'");
					System.out.println("\t\t\t\t\t3.Nếu bạn muốn tìm xe hơi số tự động thì gõ vào 'auto'");
					System.out.print("Lựa chọn của bạn là: ");
					String loaixe = sc.nextLine();
					loaixe.toLowerCase();
					Search_LoaiXe(loaixe);
					break;
				case 4: 
					break loop;
				default:
					System.err.println("Vì bạn nhập sai lựa chọn nên chương trình bị dừng");
					break;
			}
		}
	}
	public void ThaoTac() {
		int select = 0;
		String masp = "";
		loop:
			while(true) {
				System.out.println("\n\t\t\t\t\t======CÁC THAO TÁC======");
				System.out.println("\t\t\t\t\t1.Ấn phím 1 để thêm sản phẩm");
				System.out.println("\t\t\t\t\t2.Ấn phím 2 để xoá sản phẩm");
				System.out.println("\t\t\t\t\t3.Ấn phím 3 để sửa chi tiết sản phẩm");
				System.out.println("\t\t\t\t\t4.Ấn phím 4 để tìm kiếm các loại sản phẩm");
				System.out.println("\t\t\t\t\t5.Ấn phím 5 để xuất danh sách");
				System.out.println("\t\t\t\t\t5.Ấn phím 6 để dừng các thao tác");
				System.out.print("Lựa chọn của bạn là: ");
				select = sc.nextInt();
				sc.nextLine();
				switch(select) {
					case 1:
						System.out.print("Nhập vào số lượng xe cần thêm: ");
						int sl = sc.nextInt();
						Insert(sl);
						XuatDanhSach();
						break;
					case 2:
						System.out.print("Nhập vào mã sản phẩm cần xoá: ");
						masp = sc.nextLine();
						Delete(masp);
						XuatDanhSach();
						break;
					case 3:
						System.out.print("Nhập vào mã sản phẩm cần sửa: ");
						masp = sc.nextLine();
						Adjust(masp);
						XuatDanhSach();
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
						continue loop;
				}
			}
	}
	
	//ĐỌC FILE, GHI FILE
	@Override
	public void GhiFileJava(String filename) {
		try {
			DataOutputStream dos = new DataOutputStream(new FileOutputStream(filename));
			dos.writeInt(n);
			for(int i = 0; i < n; i++) {
				if(x[i] instanceof XeDapDien) {
					x[i] = (XeDapDien) x[i];
					x[i].GhiFile(filename);
				}
				if(x[i] instanceof XeHoi) {
					x[i] = (XeHoi) x[i];
					x[i].GhiFile(filename);
				}
			}
			dos.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void DocFileJava(String filename) {
		int i = 0;
		try {
			DataInputStream dis = new DataInputStream(new FileInputStream(filename));
			n = dis.readInt();
			x = new Xe[n];
			try {
				while(true) {
					int code = dis.readInt();
					String masp = dis.readUTF();
					String thuonghieu = dis.readUTF();
					String xuatxu = dis.readUTF();
					String mausac = dis.readUTF();
					String phankhoi = dis.readUTF();
					String acquy = dis.readUTF();
					String loaixe = dis.readUTF();
					int sl = dis.readInt();
					float gia = dis.readFloat();
					double thanhtien = dis.readDouble();
					if(code == 0) {
						String binhdien = dis.readUTF();
						x[i] = new XeDapDien(masp, thuonghieu, xuatxu, mausac, phankhoi, acquy, binhdien, loaixe, sl, gia, thanhtien);
					}
					else if(code == 1) {
						String dungtichxang = dis.readUTF();
						x[i] = new XeHoi(masp, thuonghieu, xuatxu, mausac, phankhoi, acquy, dungtichxang, loaixe, sl, gia, thanhtien);
					}
					i++;
				}
			}catch(EOFException e) {
			}
			finally {
				a = i;
				dis.close();
			}
		}catch(IOException ex) {
			ex.printStackTrace();
		}
	}
}
