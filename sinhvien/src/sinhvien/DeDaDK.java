package sinhvien;

import java.util.Date;

public class DeDaDK extends DeMon{

	private User gvPhuTrach;
	private Date deadline;
	private Nhom nhom;
	
	public DeDaDK(User gvPhuTrach, Date deadline, Nhom nhom) {
		super();
		this.gvPhuTrach = gvPhuTrach;
		this.deadline = deadline;
		this.nhom = nhom;
	}

	public DeDaDK() {
		super();
	}

	public User getGvPhuTrach() {
		return gvPhuTrach;
	}

	public void setGvPhuTrach(User gvPhuTrach) {
		this.gvPhuTrach = gvPhuTrach;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public Nhom getNhom() {
		return nhom;
	}

	public void setNhom(Nhom nhom) {
		this.nhom = nhom;
	}
	
	
}
