package OOP;

import java.util.Scanner;

public class Menu {
	Scanner sc = new Scanner(System.in);
	NhapDanhSachXe ndsx = new NhapDanhSachXe();
	NhapDanhSachKhachHang ndskh = new NhapDanhSachKhachHang();
	NhapDanhSachNhanVien ndsnv = new NhapDanhSachNhanVien();
	NhapDanhSachHoaDon ndshd = new NhapDanhSachHoaDon();
	NhapDanhSachPhieuNhapHang ndspnh = new NhapDanhSachPhieuNhapHang();
	
	public void OperatingMenu() {
		int select = 0;
		while(true) {
			do {
				System.out.println("\t\t\t\t\t===========BẢNG QUẢN LÍ===========");
				System.out.println("\t\t\t\t\t1.Ấn phím 1 để quản lí sản phẩm");
				System.out.println("\t\t\t\t\t2.Ấn phím 2 để quản lí hoá đơn");
				System.out.println("\t\t\t\t\t5.Ấn phím 3 để quản lí phiếu nhập hàng");
				System.out.println("\t\t\t\t\t3.Ấn phím 4 để quản lí khách hàng");
				System.out.println("\t\t\t\t\t4.Ấn phím 5 để quản lý nhân viên");
				System.out.println("\t\t\t\t\t5.Ấn phím 6 để thống kê");
				System.out.println("\t\t\t\t\t7.Ấn phím 7 để thoát");
				System.out.print("Vậy lựa chọn của bạn là gì: ");
				select = sc.nextInt();
				sc.nextLine();
				if(select < 1 || select > 7)
					System.err.println("Bạn đã nhập sai lựa chọn của mình. Xin hãy nhập lại");
				loop:
				switch(select) {
					case 1:
						ndsx.NhapDanhSach();
						break;
					case 2:
						ndshd.NhapDanhSach();
						break;
					case 3:
						ndspnh.NhapDanhSach();
						break;
					case 4:
						ndskh.NhapDanhSach();
						break;
					case 5:
						ndsnv.NhapDanhSach();
						break;
					case 6:
						ndshd.ThongKe();
						break;
					case 7:
						System.err.println("\nBẠN ĐÃ LỰA CHỌN THOÁT KHỎI BẢNG QUẢN LÍ!!!");
						break loop;
					default:
						break loop;
				}
			}while(select < 1 || select > 7);
		}
	}
}
