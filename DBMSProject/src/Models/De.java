package Models;

public class De{
	private String maDe;
	private String moTa;
	
	public String getMaDe() {
		return maDe;
	}
	public void setMaDe(String maDe) {
		this.maDe = maDe;
	}
	public String getMoTa() {
		return moTa;
	}
	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}
	
	public De(){
		
	}
	
	public De(String maDe, String moTa) {
        this.maDe = maDe;
        this.moTa = moTa;
    }
	
	
	@Override
    public String toString()  {
        return this.maDe;
    }
}
