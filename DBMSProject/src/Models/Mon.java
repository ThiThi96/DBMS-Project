package Models;

import java.util.Vector;

public class Mon {

    private String maLop;
    private String tenLop;

    public Mon(String maLop, String tenLop) {
        this.maLop = maLop;
        this.tenLop = tenLop;
    }
    
    public Mon(Vector<String> info){
    	maLop = info.get(0);
    	tenLop = info.get(1);
    }

    public String getMalop() {
        return maLop;
    }

    public void setMalop(String maLop) {
        this.maLop = maLop;
    }

    public String getTenlop() {
        return tenLop;
    }

    public void setTenlop(String tenLop) {
        this.tenLop = tenLop;
    }
    
    
}
