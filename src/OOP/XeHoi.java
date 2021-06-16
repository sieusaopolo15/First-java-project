package OOP;

import java.io.*;

public class XeHoi extends Xe {
	private String dungtichxang;
	DanhSachXe dsx = new DanhSachXe();
	//CONSTRUCTOR
	public XeHoi() {
		dungtichxang = "";
	}
	public XeHoi(String msp, String thuonghieu, String tensp, String mausac, String phankhoi, String acquy, String dtx, String loaixe, int sl, float gia, double thanhtien) {	
		super(msp, thuonghieu, tensp, mausac, phankhoi, acquy, loaixe, sl, gia, thanhtien);
		dungtichxang = dtx;
	}
	public XeHoi(XeHoi a) {
		super((Xe) a);
		dungtichxang = a.dungtichxang;
	}
	//SETTER
	public void setDungtichxang(String dungtichxang) {
		this.dungtichxang = dungtichxang;
	}
	//GETTER
	public String getDungtichxang() {
		return dungtichxang;
	}
	//CAC HAM KHAC
	@Override
	public void Nhap() {
		do {
			System.out.println("Khi nhập mã sản phẩm, tối thiểu chỉ có 3 kí tự và tối đa là 5 kí tự:");
			System.out.print("Mời bạn nhập mã sản phẩm: ");
			masp = sc.nextLine();
			if(masp.length() < 3 || masp.length() > 5) 
				System.err.println("Bạn đã nhập mã sản phẩm thiếu hoặc thừa kí tự. Xin mời nhập lại!!!");
		}while(masp.length() < 3 || masp.length() > 5);
		
		System.out.print("Thương hiệu của xe: ");
		thuonghieu = sc.nextLine();
		
		System.err.println("\nTên của sản phẩm phải có thương hiệu trong đó. Nếu không thì tên sẽ không hợp lệ");
		do {
			System.out.println("Thương hiệu mà bạn vừa nhập là: " + thuonghieu);
			System.out.print("Nhập vào tên của sản phầm: ");
			tensp = sc.nextLine();
			if(tensp.indexOf(thuonghieu) == -1)
				System.err.println("\nTên sản phẩm không hợp lệ!!!");
		}while(tensp.indexOf(thuonghieu) == -1);
		
		System.out.print("Màu sắc chủ đạo của xe: ");
		mausac = sc.nextLine();
		
		System.out.print("Phân khối của xe hơi: ");
		phankhoi = sc.nextLine();
		
		System.out.print("Thông số của ắc quy: ");
		acquy = sc.nextLine();
		
		System.out.print("Dung tích của bình chứa xăng: ");
		dungtichxang = sc.nextLine();
		
		do {
			System.out.println("\tXe hơi có hai loại là:");
			System.out.println("\t1.Nếu là xe số sàn thì nhập vào (san||SAN)");
			System.out.println("\t2.Nếu là xe số tự động thì nhập vào (auto||AUTO)");
			System.out.print("Loại xe: ");
			loaixe = sc.nextLine();
			if((!"san".equals(loaixe) && !"SAN".equals(loaixe)) && (!"auto".equals(loaixe) && !"AUTO".equals(loaixe))) {
				System.err.println("Dữ liệu mà bạn nhập vào không hợp lệ!!!");
			}
		}while((!"san".equals(loaixe) && !"SAN".equals(loaixe)) && (!"auto".equals(loaixe) && !"AUTO".equals(loaixe)));
		
		loaixe.toLowerCase();
		
		System.out.print("Số lượng xe hơi: ");
		sl = sc.nextInt();
		
		
		System.out.print("Giá tiền của mỗi chiếc (tính bằng đô la): ");
		gia = sc.nextFloat();
		
		thuonghieu.toUpperCase();
	}
	@Override
	public void Xuat() {
		thanhtien = gia * sl;
		System.out.format(" %8s | %12s | %32s | %15s | %11s | %13s | %15s | %15s | %15s | %8s | %15s | %17s ||\n", masp, thuonghieu, tensp, mausac, phankhoi, acquy, null, dungtichxang, loaixe, sl, gia, thanhtien);
	}
	
	@Override
	public void GhiFile(String filename) throws IOException {
		DataOutputStream dos = new DataOutputStream(new FileOutputStream(filename, Boolean.TRUE));
		int code = 1;
		dos.writeInt(code);
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
		dos.writeUTF(dungtichxang);
		dos.close();
	}
	
}
