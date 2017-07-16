package com.ddup4.autonav.db;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;

public class DbHelper {

	private static Properties appConfig = new Properties();
	static {
		InputStream is = null;
		try {
			is = new ClassPathResource("app_config.properties").getInputStream();
			appConfig.load(is);
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
	}
	private static final String url = appConfig.getProperty("mysql_url");
	private static final String name = appConfig.getProperty("mysql_driver");
	private static final String user = appConfig.getProperty("mysql_user");
	private static final String password = appConfig.getProperty("mysql_password");

	public Connection conn = null;
	public PreparedStatement prepare = null;

	public DbHelper(String sql) {
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
			if (prepare != null) {
				prepare.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
