import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


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
	public ArrayList<Client> getClients() {
		ArrayList<Client> clients = new ArrayList<>();
		try {
			Statement stat = con.createStatement();
			ResultSet res = stat.executeQuery("select * from clients");
			while(res.next()) {
				int id = res.getInt(1);
				String name = res.getString(2);
				String email = res.getString(3);
				String num = res.getString(4);
				Client c = new Client();
				c.setEmail(email);
				c.setId(id);
				c.setName(name);
				c.setNum(num);
				clients.add(c);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return clients;
	}
	public ArrayList<DateTime> getFreeDateTime() {
		ArrayList<DateTime> dateTimes = new ArrayList<>();
		Statement stat;
		try {
			stat = con.createStatement();
			ResultSet res = stat.executeQuery("select * from basictime where isfree='yes'");
			while(res.next()) {
				DateTime d = new DateTime();
				d.setDate(res.getString(2));
				d.setTime(res.getString(3));
				dateTimes.add(d);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dateTimes;
	}
	public ArrayList<String> getFreeDate(){
		ArrayList<String> arr = new ArrayList<>();
		try {
			Statement stat;
			stat = con.createStatement();
			ResultSet res = stat.executeQuery("select DISTINCT btdate from basictime where isfree='yes'");
			while(res.next()) {
				arr.add(res.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr;
	}
	public ArrayList<String> getFreeTime(String date){
		ArrayList<String> arr = new ArrayList<>();
		try {
			Statement stat;
			stat = con.createStatement();
			ResultSet res = stat.executeQuery("select bttime from basictime where isfree='yes' and btdate='"+date+"'");
			while(res.next()) {
				arr.add(res.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr;
	}
	
}
