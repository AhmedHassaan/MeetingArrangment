import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBConnect {
	private Connection con;
	private String conName = "student";
	private String conPass = "student";
	private String className = "com.mysql.cj.jdbc.Driver";
	private String urlConnection = "jdbc:mysql://localhost:3306/secertarysystem";
	
	public DBConnect(){
		try {
			Class.forName(className);
			con = DriverManager.getConnection(urlConnection,conName,conPass);
			System.out.println("Connected");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error : " + e);
		}
	}
	
	public void addClient(String name,String email,String num) {
		
		try {
			String sql = "insert into clients (cname,cemail,cnum) values(?,?,?)";
			PreparedStatement stat = con.prepareStatement(sql);
			stat.setString(1, name);
			stat.setString(2, email);
			stat.setString(3, num);
			stat.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
