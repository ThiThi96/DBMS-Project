package Models;

import Models.De;
import Models.LoaiDA;
import java.util.Date;

public class DeMon extends De{
	
	protected Mon mon;
	protected LoaiDA loaiDA;
	protected boolean loaiDe;
	
	private byte slDangKyTD;
	private byte slSVNhom;
	private Date ngayBDDangKy;
	
	public DeMon() {
		super();
	}
	
	public DeMon(Mon mon, LoaiDA loaiDA, boolean loaiDe, byte slDangKyTD, byte slSVNhom, Date ngayBDDangKy) {
		super();
		this.mon = mon;
		this.loaiDA = loaiDA;
		this.loaiDe = loaiDe;
		this.slDangKyTD = slDangKyTD;
		this.slSVNhom = slSVNhom;
		this.ngayBDDangKy = ngayBDDangKy;
	}

	public Mon getMon() {
		return mon;
	}
	public void setMon(Mon mon) {
		this.mon = mon;
	}
	public LoaiDA getLoaiDA() {
		return loaiDA;
	}
	public void setLoaiDA(LoaiDA loaiDA) {
		this.loaiDA = loaiDA;
	}
	public boolean isLoaiDe() {
		return loaiDe;
	}
	public void setLoaiDe(boolean loaiDe) {
		this.loaiDe = loaiDe;
	}
	public byte getSlDangKyTD() {
		return slDangKyTD;
	}
	public void setSlDangKyTD(byte slDangKyTD) {
		this.slDangKyTD = slDangKyTD;
	}
	public byte getSlSVNhom() {
		return slSVNhom;
	}
	public void setSlSVNhom(byte slSVNhom) {
		this.slSVNhom = slSVNhom;
	}
	public Date getNgayBDDangKy() {
		return ngayBDDangKy;
	}
	public void setNgayBDDangKy(Date ngayBDDangKy) {
		this.ngayBDDangKy = ngayBDDangKy;
	}
	
}
