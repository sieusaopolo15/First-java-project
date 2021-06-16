package OOP;

import java.io.*;

public class XeDapDien extends Xe {
	DanhSachXe dsx = new DanhSachXe();
	private String binhdien;
	//CONSTRUCTOR
	public XeDapDien() {
	}
	public XeDapDien(String msp, String thuonghieu, String tensp, String mausac, String phankhoi, String acquy, String bd, String loaixe, int sl, float gia, double thanhtien) {	
		super(msp, thuonghieu, tensp, mausac, phankhoi, acquy, loaixe, sl, gia, thanhtien);
		binhdien = bd;
	}
	public XeDapDien(XeDapDien a) {
		super((Xe) a);
		binhdien = a.binhdien;
	}
	//SETTER
	public void setAQ(String aq) {
		binhdien = aq;
	}
	
	//GETTER
	public String getAQ() {
		return binhdien;
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
			System.out.print("Tên của sản phầm là: ");
			tensp = sc.nextLine();
			if(tensp.indexOf(thuonghieu) == -1)
				System.err.println("\nTên sản phẩm không hợp lệ!!!");
		}while(tensp.indexOf(thuonghieu) == -1);
		
		
		System.out.print("Màu sắc chủ đạo của xe: ");
		mausac = sc.nextLine();
		
		System.out.print("Phân khối của xe đạp điên: ");
		phankhoi = sc.nextLine();
		
		System.out.print("Thông số ắc quy của xe đạp điện: ");
		acquy = sc.nextLine();
		
		do {
			System.out.println("\tBình điện của xe đạp điện có hai loại là:");
			System.out.println("\t1.Nếu là ắc quy thì nhập vào (acquy||AcQuy||ACQUY)");
			System.out.println("\t2.Nếu là pin thì nhập vào (pin||Pin||PIN)");
			System.out.print("Loại bình điện: ");
			binhdien = sc.nextLine();
			if((!"pin".equals(binhdien) && !"Pin".equals(binhdien) && !"PIN".equals(binhdien)) && (!"acquy".equals(binhdien) && !"Acquy".equals(binhdien) && !"ACQUY".equals(binhdien))) {
				System.err.println("Dữ liệu mà bạn nhập vào không hợp lệ!!!");
			}
		}while((!"pin".equals(binhdien) && !"Pin".equals(binhdien) && !"PIN".equals(binhdien)) && (!"acquy".equals(binhdien) && !"Acquy".equals(binhdien) && !"ACQUY".equals(binhdien)));
		
		binhdien.toLowerCase();
		
		System.out.print("Số lượng xe đạp điện: ");
		sl = sc.nextInt();
		sc.nextLine();
		
		System.out.print("Giá tiền của mỗi chiếc (tính bằng đôla): ");
		gia = sc.nextFloat();
		
		loaixe = "Xe Dap dien".toLowerCase();
		thuonghieu.toUpperCase();
}
	@Override
	public void Xuat() {
		thanhtien = gia * sl;
		System.out.format(" %8s | %12s | %32s | %15s | %11s | %13s | %15s | %15s | %15s | %8s | %15s | %17s ||\n", masp, thuonghieu, tensp, mausac, phankhoi, acquy, binhdien, null, loaixe, sl, gia, thanhtien);
	}
	@Override
	public void GhiFile(String filename) throws IOException {
		DataOutputStream dos = new DataOutputStream(new FileOutputStream(filename, Boolean.TRUE));
		int code = 0;
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
		dos.writeUTF(binhdien);
		dos.close();
	}
}