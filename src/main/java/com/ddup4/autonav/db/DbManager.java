package com.ddup4.autonav.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ddup4.autonav.bean.NavBean;

public class DbManager {
	private static final String tbl_name = "t_gps_info";

	public static List<NavBean> getAllNavListById(int id) {
		List<NavBean> listBean = new ArrayList<>();
		if (id != 0) {
			String sql = "select * from " + tbl_name + "where id = " + id + " order by id descß";
			DbHelper db = new DbHelper(sql);
			try {
				ResultSet rs = db.prepare.executeQuery();
				if (rs != null) {
					while (rs.next()) {
						NavBean iBean = new NavBean();
						iBean.setId(rs.getInt("id"));
						iBean.setPhone(rs.getString("phone"));
						iBean.setLatitude(rs.getString("gps_lat"));
						iBean.setLongtitude(rs.getString("gps_lng"));
						listBean.add(iBean);
					}
				}
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				db.close();
			}
		}
		return listBean;
	}

	public static List<NavBean> getAllNavListByPhone(String phone) {
		List<NavBean> listBean = new ArrayList<>();
		if (phone != null && !phone.equals("")) {
			String sql = "select * from " + tbl_name + " where phone = '" + phone + "' order by id desc";
			DbHelper db = new DbHelper(sql);
			try {
				ResultSet rs = db.prepare.executeQuery();
				if (rs != null) {
					while (rs.next()) {
						NavBean iBean = new NavBean();
						iBean.setId(rs.getInt("id"));
						iBean.setPhone(rs.getString("phone"));
						iBean.setLatitude(rs.getString("gps_lat"));
						iBean.setLongtitude(rs.getString("gps_lng"));
						listBean.add(iBean);
					}
				}
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				db.close();
			}
		}
		return listBean;
	}

	public static List<NavBean> getAllNavList() {
		List<NavBean> beanList = new ArrayList<>();
		String sql = "select * from " + tbl_name + " order by id desc";
		DbHelper db = new DbHelper(sql);
		if (db.prepare != null) {
			try {
				ResultSet rs = db.prepare.executeQuery(sql);
				if (rs != null) {
					while (rs.next()) {
						NavBean iBean = new NavBean();
						iBean.setId(rs.getInt("id"));
						iBean.setPhone(rs.getString("phone"));
						iBean.setLatitude(rs.getString("gps_lat"));
						iBean.setLongtitude(rs.getString("gps_lng"));
						beanList.add(iBean);
					}
				}
				rs.close();

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				db.close();
			}
		}
		return beanList;
	}

	public static boolean delBeanById(int id) {
		if (id > 0) {
			String sql = "delete from " + tbl_name + " where id = " + id;
			DbHelper db = new DbHelper(sql);
			if (db.prepare != null) {
				try {
					db.prepare.execute(sql);
					return true;
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					db.close();
				}
			}
			return true;
		}
		return false;
	}

	public static boolean inserBean(NavBean navBean) {

		if (navBean != null && navBean.getId() == 0) {
			String sql = "insert into " + tbl_name + " (phone,gps_lat,gps_lng) values ('" + navBean.getPhone() + "'"
					+ ",'" + navBean.getLatitude() + "'," + "'" + navBean.getLongtitude() + "'" + ")";
			DbHelper db = new DbHelper(sql);
			try {
				boolean flag = db.prepare.execute();
				System.out.println("insertBean -------------> " + flag);
				ResultSet idInsert = db.prepare.getGeneratedKeys();
				if (idInsert.next()) {
					navBean.setId(idInsert.getInt(1));
				}
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				db.close();
			}
		}
		return false;
	}

	public static boolean updateBean(NavBean bean) {
		if (bean != null && bean.getId() > 0) {
			String sql = "update " + tbl_name + " set phone = '" + bean.getPhone() + "',gps_lat = '"
					+ bean.getLatitude() + "',gps_lng = '" + bean.getLongtitude() + "' where id=" + bean.getId();
			DbHelper db = new DbHelper(sql);
			if (db.prepare != null) {
				try {
					if (!db.prepare.execute(sql)) {
						return true;
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					db.close();
				}
			}
		}
		return false;
	}

	public static void main(String[] args) {
		NavBean bean = new NavBean();
		// bean.setPhone("15201580861");
		// bean.setLongtitude("56");
		// bean.setLatitude("78");
		// inserBean(bean);
//		int id = 3;
//		System.out.println("delete method ====>   " + delBeanById(id));
		String phone = "15201580861";
		List<NavBean> listbyphone = getAllNavListByPhone(phone);
		System.out.println("byPhone ------------->" + listbyphone.size());
		System.out.println("byPhone -------------> " + listbyphone.toString());
	}
}
