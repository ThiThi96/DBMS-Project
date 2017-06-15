package ModelConnection;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import java.util.concurrent.Callable;

import Models.User;
import application.FxDialogs;

public class UserConnection extends AbstractConnection{

	public UserConnection(String connectionString) {
		super(connectionString);
		// TODO Auto-generated constructor stub
	}

	//kt thông tin ng dùng
	public User checkEmInfo(String userName, String pass)
	{
		User info = null;
		try {
			CallableStatement statement = con.prepareCall("{call DangNhap (?, ?)}");
			statement.setString(1, userName);
			statement.setString(2, pass);
			boolean res = statement.execute();
		
		
			int i=1;
			if (!res)
			{
				return null;
			}
			ResultSet rs = statement.getResultSet();
			ResultSetMetaData rsmd = rs.getMetaData();

			int columnsNumber = rsmd.getColumnCount();
			Vector<String> Info = new Vector<String>();

			if (rs.next())
			{
				while (i!= columnsNumber + 1)
				{
					String s = rs.getString(i);
					Info.addElement(s);
					if (Info.get(i-1) == null)
						Info.set(i-1, "");
					i++;
					System.out.print(s+" ");
				}

			}
			info = new User(Info);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			FxDialogs.showError("Lỗi", e.getMessage());
		}
		return info;
	}

	//tìm ng dùng (màn hình admin)
	public Vector<User> findUser(String keyword)
	{
		Vector<User> info = new Vector<User>();
		try {
			CallableStatement statement = con.prepareCall("{call XemDSNguoiDung (?)}");
			statement.setString(1, keyword);
			//statement.registerOutParameter(4, java.sql.Types.VARCHAR);
			//trả về 1 nếu có resultset ngc lại trả về 0
			boolean res = statement.execute();
		
		
			int i=1;
			if (!res)
			{
				return null;
			}
			ResultSet rs = statement.getResultSet();
			ResultSetMetaData rsmd = rs.getMetaData();

			int columnsNumber = rsmd.getColumnCount();
			

			while (rs.next())
			{
				Vector<String> Info = new Vector<String>();
				while (i!= columnsNumber + 1)
				{
					String s = rs.getString(i);
					Info.addElement(s);
					if (Info.get(i-1) == null)
						Info.set(i-1, "");
					i++;
					System.out.print(s+" ");
				}
				i= 1;
				info.addElement(new User(Info));
			}
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			FxDialogs.showError("Lỗi", e.getMessage());
		}
		return info;
	}

	//khóa tài khoản
	public int lockUser(String userID){
		int res = 0;
		try {
			CallableStatement statement = con.prepareCall("{call KhoaTaiKhoan (?, ?)}");
			statement.setString(1, userID);
			statement.registerOutParameter(2, java.sql.Types.INTEGER);
			statement.setInt(2, res);
			
			statement.execute();
			//statement.registerOutParameter(4, java.sql.Types.VARCHAR);
			//trả về 1 nếu có resultset ngc lại trả về 0
			
			res = statement.getInt(2);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	//thêm người dùng (admin)
	public String addUser(String uName, String email, String tel, String uAdd, int uYear, char uType, String Pass, boolean delay)
	{
		String uID = new String();
		try {
			CallableStatement statement = con.prepareCall("{call  TaoTaiKhoan_Delay(?, ?, ?, ?, ?, ?, ?, ?)}");
			statement.setString(1, Pass);
			statement.setString(2, uName);
			statement.setString(3, email);
			statement.setString(4, tel);
			statement.setString(5, uAdd);
			if (uYear == -1)
				statement.setObject(6, null);
			else
				statement.setInt(6, uYear);
			statement.setString(7, String.valueOf(uType));
			statement.registerOutParameter(8, java.sql.Types.BIT);
			statement.setBoolean(8, delay);
			
			statement.execute();
			
			ResultSet rs = statement.getResultSet();
			uID = rs.getString(1);
			
			delay = statement.getBoolean(8);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			FxDialogs.showError("Lỗi", e.getMessage());
		}
		return uID;
	}

	//thay đổi thông tin ng dùng
	public int updateUserDelay(String uID, String uPass, String uName, String email, String uTel, String uAdd, int delay){
		int res = 0;
		try {
			CallableStatement statement = con.prepareCall("{call SuaThongTinCaNhan (?, ?, ?, ?, ?, ?, ?)}");
			statement.setString(1, uID);
			statement.setString(2, uPass);
			statement.setString(3, uName);
			statement.setString(4, email);
			statement.setString(5, uTel);
			statement.setString(6, uAdd);
			statement.registerOutParameter(7, java.sql.Types.INTEGER);
			statement.setInt(7, delay);
			
			
			statement.execute();
			
			res = statement.getInt(7);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			FxDialogs.showError("Lỗi", e.getMessage());
		}
		return res;
	}

	//đổi mật khẩu
	public int changPasswordDelay(String uID, String oldPass, String newPass, int delay)
	{
		int res = 0;
		try {
			CallableStatement statement = con.prepareCall("{call DoiMatKhau (?, ?, ?, ?)}");
			statement.setString(1, uID);
			statement.setString(2, oldPass);
			statement.setString(3, newPass);
			statement.registerOutParameter(4, java.sql.Types.INTEGER);
			statement.setInt(4, delay);
			
			statement.execute();
			
			res = statement.getInt(4);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			FxDialogs.showError("Lỗi", e.getMessage());
		}
		return res;
	}

	//tìm danh sách sinh viên được quản lý bởi giáo viên nào đó
	public Vector<User> getUserUnderControllOf(){
		Vector<User> uList = new Vector<User>();
		
		return uList;
	}

	
}
