package giaovien;

public class LoaiDA {
	private String maLoai;
	private String tenLoai;
	
	public LoaiDA(String maLoai, String tenLoai) {
        this.maLoai = maLoai;
        this.tenLoai = tenLoai;
    }
	
	public String getMaLoai() {
		return maLoai;
	}
	public void setMaLoai(String maLoai) {
		this.maLoai = maLoai;
	}
	public String getTenLoai() {
		return tenLoai;
	}
	public void setTenLoai(String tenLoai) {
		this.tenLoai = tenLoai;
	}
	
	@Override
    public String toString()  {
        return this.tenLoai;
    }
}
