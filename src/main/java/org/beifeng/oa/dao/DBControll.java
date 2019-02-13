package org.beifeng.oa.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBControll {

	private String url="jdbc:mysql://localhost:3306/oa?useUnicode=true&amp;characterEncoding=utf8";
	private String driver="com.mysql.jdbc.Driver";
	private String username="root";
	private String password="43046721";
	private ResultSet rs=null;;
	private Connection conn=null;
	public ResultSet getData(String sql) {
		try{
			Class.forName(driver);
			conn=DriverManager.getConnection(url, username, password);
			Statement stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		return rs;
	}
	
	public void close(){
		try {
			if(rs!=null && !rs.isClosed()){
				rs.close();
			}
			if(conn!=null && !conn.isClosed()){
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
