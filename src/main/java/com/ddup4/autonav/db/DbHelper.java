package com.ddup4.autonav.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class DbHelper {
	private static final String url = "jdbc:mysql://localhost:3306/db_autonav";
	private static final String name = "com.mysql.jdbc.Driver";
	private  static final String user = "root";
	private static final String password = "root";
	
	
	public Connection conn = null;
	public PreparedStatement prepare = null;
	
	public DbHelper (String sql) {
		try {
			Class.forName(name);
			conn = DriverManager.getConnection(url, user, password);
			prepare = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}
	
	public void close() {
		try {
			if (conn != null) {
				conn.close();
			}
			if(prepare != null) {
				prepare.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
