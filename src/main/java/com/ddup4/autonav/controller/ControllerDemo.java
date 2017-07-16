package com.ddup4.autonav.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ddup4.autonav.bean.NavBean;
import com.ddup4.autonav.db.DbManager;

@RestController
public class ControllerDemo {
 
	public ControllerDemo (){}

	@RequestMapping("/gpsinfo/list")
	public @ResponseBody List<NavBean> getGpsInfoList() {
		System.out.println("-------------getGpsInfoList begin ----------");
		List<NavBean> listAll = DbManager.getAllNavList();
		for(NavBean iBean:listAll) {
			System.out.println("list : " + iBean.toString());
		}
		System.out.println("-------------getGpsInfoList end  ----------");
		return listAll;
	}
	@RequestMapping("/gpsinfo/getById")
	public Object getGpsInfoListById(@RequestParam("id") int id) {
		return DbManager.getAllNavListById(id);
	}
	
	@RequestMapping("/gpsinfo/insert")
	public @ResponseBody Object insertGpsDataByBean(@ModelAttribute NavBean navBean){
		System.out.println("insertGpsDataByBean " + navBean);
		return DbManager.inserBean(navBean);
	}
	
	@RequestMapping("/gpsinfo/delById")
	public Object delGpsDataById(@RequestParam("id") int id) {
		return DbManager.delBeanById(id);
	}

	@RequestMapping("/gpsinfo/update")
	public Object updateGpsData(@RequestParam("navBean") NavBean bean) {
		return DbManager.updateBean(bean);
	}


}
