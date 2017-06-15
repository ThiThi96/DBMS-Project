package Models;

import java.util.Vector;

import javafx.beans.property.SimpleStringProperty;

public class User {
	private final SimpleStringProperty userID;
	private final SimpleStringProperty userName;
	private final SimpleStringProperty userYear;
	private final SimpleStringProperty userTel;
	private final SimpleStringProperty userAdd;
	private final SimpleStringProperty userEmail;
	private final SimpleStringProperty userType;
	
	
	public User(String id, String uName, String year, String tel, String add, String email){
		
		this.userID = new SimpleStringProperty(id);
		this.userName = new SimpleStringProperty(uName);
		this.userYear = new SimpleStringProperty(year);
		this.userTel = new SimpleStringProperty(tel);
		this.userAdd = new SimpleStringProperty(add);
		this.userEmail = new SimpleStringProperty(email);
		this.userType = new SimpleStringProperty("1");
	}
	
	public User(Vector<String> info)
	{
		this.userID = new SimpleStringProperty(info.get(0));
		this.userName = new SimpleStringProperty(info.get(1));
		this.userYear = new SimpleStringProperty(info.get(5));
		this.userTel = new SimpleStringProperty(info.get(3));
		this.userAdd = new SimpleStringProperty(info.get(4));
		this.userEmail = new SimpleStringProperty(info.get(2));
		this.userType = new SimpleStringProperty(info.get(6));
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
    
    public void setUserType(String type) {
        userType.set(type);
    }

    public String getUserType() {
       return userType.get();
    }
}
