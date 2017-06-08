package sinhvien;

public class User {

	private String MaNguoiDung;
	private String HoTen;
	private String Email;
	private String SDT;
	private String DiaChi;
	private byte Khoa;
	private byte LoaiNguoiDung;
	private boolean trangThai;
	
	public User() {}
	
	public User(String maNguoiDung, String hoTen, String email, String sDT, String diaChi, byte khoa,
			byte loaiNguoiDung, boolean trangThai) {
		MaNguoiDung = maNguoiDung;
		HoTen = hoTen;
		Email = email;
		SDT = sDT;
		DiaChi = diaChi;
		Khoa = khoa;
		LoaiNguoiDung = loaiNguoiDung;
		this.trangThai = trangThai;
	}



	public String getMaNguoiDung() {
		return MaNguoiDung;
	}
	public void setMaNguoiDung(String maNguoiDung) {
		MaNguoiDung = maNguoiDung;
	}
	public String getHoTen() {
		return HoTen;
	}
	public void setHoTen(String hoTen) {
		HoTen = hoTen;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public byte getKhoa() {
		return Khoa;
	}
	public void setKhoa(byte khoa) {
		Khoa = khoa;
	}
	public byte getLoaiNguoiDung() {
		return LoaiNguoiDung;
	}
	public void setLoaiNguoiDung(byte loaiNguoiDung) {
		LoaiNguoiDung = loaiNguoiDung;
	}
	public boolean isTrangThai() {
		return trangThai;
	}
	public void setTrangThai(boolean trangThai) {
		this.trangThai = trangThai;
	}
	
	
}
