package Models;

import java.util.Date;

public class DeMon1 {
	protected String de;
	protected String mon;
	protected String loaiDA;
	protected boolean loaiDe;
	protected String moTa;
	private int slDangKyTD;
	private int slDangKy;
	private int slSVNhom;
	private String ngayBDDangKy;
	private String deadline;
	

	public DeMon1(String de, String mon, String loaiDA, boolean loaiDe, String moTa, int slDangKyTD, int slDangKy, int slSVNhom, String ngayBDDangKy, String deadline) {
		super();
		this.de = de;
		this.mon = mon;
		this.loaiDA = loaiDA;
		this.loaiDe = loaiDe;
		this.moTa = moTa;
		this.slDangKyTD = slDangKyTD;
		this.slDangKy = slDangKy;
		this.slSVNhom = slSVNhom;
		this.ngayBDDangKy = ngayBDDangKy;
		this.deadline = deadline;
	}

	public String getDe() {
		return de;
	}
	public void setDe(String de) {
		this.de = de;
	}
	public String getMon() {
		return mon;
	}
	public void setMon(String mon) {
		this.mon = mon;
	}
	public String getLoaiDA() {
		return loaiDA;
	}
	public void setLoaiDA(String loaiDA) {
		this.loaiDA = loaiDA;
	}
	public boolean isLoaiDe() {
		return loaiDe;
	}
	public void setLoaiDe(boolean loaiDe) {
		this.loaiDe = loaiDe;
	}
	public String getMoTa() {
		return moTa;
	}
	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}
	public int getSlDangKyTD() {
		return slDangKyTD;
	}
	public void setSlDangKyTD(int slDangKyTD) {
		this.slDangKyTD = slDangKyTD;
	}
	public int getSlSVNhom() {
		return slSVNhom;
	}
	public void setSlSVNhom(int slSVNhom) {
		this.slSVNhom = slSVNhom;
	}
	public String getNgayBDDangKy() {
		return ngayBDDangKy;
	}
	public void setNgayBDDangKy(String ngayBDDangKy) {
		this.ngayBDDangKy = ngayBDDangKy;
	}

	public int getSlDangKy() {
		return slDangKy;
	}

	public void setSlDangKy(int slDangKy) {
		this.slDangKy = slDangKy;
	}

	public String getDeadline() {
		return deadline;
	}

	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}
}
