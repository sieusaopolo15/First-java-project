package OOP;

import java.util.Scanner;

public abstract class Person {
	protected String ho, ten, birthdate, gioitinh;
	Scanner sc = new Scanner(System.in);
	//CONSTRUCTOR
	public Person() {
		ho = "";
		ten = "";
		birthdate = "";
		gioitinh = "";
	}
	public Person(String h, String t, String bd, String gender) {
		ho = h;
		ten = t;
		birthdate = bd;
		gioitinh = gender;
	}
	public Person(Person a) {
		ho = a.ho;
		ten = a.ten;
		birthdate = a.birthdate;
		gioitinh = a.gioitinh;
	}
	//SETTER
	public void setHo(String ho) {
		this.ho = ho;
	}
	public void setTen(String ten) {
		this.ten = ten;
	}
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}
	public void setGioitinh(String gioitinh) {
		this.gioitinh = gioitinh;
	}
	//GETTER
	public String getHo() {
		return ho;
	}
	public String getTen() {
		return ten;
	}
	public String getBirthdate() {
		return birthdate;
	}
	public String getGioitinh() {
		return gioitinh;
	}
	public abstract void Nhap();
	public abstract void Xuat();
}
