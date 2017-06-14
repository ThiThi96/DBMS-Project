package Models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class User {
	private final SimpleStringProperty userID;
	private final SimpleStringProperty userName;
	private final SimpleStringProperty userYear;
	private final SimpleStringProperty userTel;
	private final SimpleStringProperty userAdd;
	private final SimpleStringProperty userEmail;
	private final SimpleIntegerProperty userType;
	
	public User(String id, String uName, String year, String tel, String add, String email, int type){
		this.userID = new SimpleStringProperty(id);
		this.userName = new SimpleStringProperty(uName);
		this.userYear = new SimpleStringProperty(year);
		this.userTel = new SimpleStringProperty(tel);
		this.userAdd = new SimpleStringProperty(add);
		this.userEmail = new SimpleStringProperty(email);
		this.userType = new SimpleIntegerProperty(type);
	}
	
    public String getUserID() {
        return userID.get();
    }
 
    public void setUserID(String id) {
        userID.set(id);
    }
    
    public String getUserName() {
        return userName.get();
     }
     
        
    public void setUserName(String uName) {
       userName.set(uName);
    }
    
    public String getUserYear() {
        return userYear.get();
    }
    
    public void setUserYear(String dob) {
    	userYear.set(dob);
    }
    
    public String getUserTel() {
        return userTel.get();
    }
    
    public void setUserAdd(String uAdd) {
        userAdd.set(uAdd);
    }

    public String getUserAdd() {
       return userAdd.get();
    }
    
    public void setUserEmail(String uEm) {
        userEmail.set(uEm);
    }

    public String getUserEmail() {
       return userEmail.get();
    }
    
    public void setUserType(int t) {
        userType.set(t);
    }

    public int getUserType() {
       return userType.get();
    }
}
