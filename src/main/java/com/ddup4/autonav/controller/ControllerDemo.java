package com.ddup4.autonav.controller;

import java.util.List;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ddup4.autonav.bean.Constant;
import com.ddup4.autonav.bean.NavBean;
import com.ddup4.autonav.bean.ResponseBean;
import com.ddup4.autonav.db.DbManager;

@RestController
public class ControllerDemo {
 
	public ControllerDemo (){}

	@RequestMapping("/gpsinfo/list")
	public @ResponseBody ResponseBean<List<NavBean>> getGpsInfoList() {
		System.out.println("-------------getGpsInfoList begin ----------");
		List<NavBean> listAll = DbManager.getAllNavList();
		for(NavBean iBean:listAll) {
			System.out.println("list : " + iBean.toString());
		}
		System.out.println("-------------getGpsInfoList end  ----------");

		ResponseBean<List<NavBean>> response = new ResponseBean<>();
		response.setStatus(Constant.STATUS_OK);
		response.setMsg(Constant.MSG_OK);
		response.setData(listAll);
		return response;
	}

	@RequestMapping("/gpsinfo/getById")
	public @ResponseBody ResponseBean<List<NavBean>> getGpsInfoListById(@RequestParam("id") int id) {
		ResponseBean<List<NavBean>> response = new ResponseBean<>();
		List<NavBean> listAll = DbManager.getAllNavListById(id);
		response.setStatus(Constant.STATUS_OK);
		response.setMsg(Constant.MSG_OK);
		response.setData(listAll);
		return response;
	}
	
	@RequestMapping("/gpsinfo/getByPhone")
	public @ResponseBody ResponseBean<NavBean> getGpsInfoListByPhone(@RequestParam("phone") String phone) {
		ResponseBean<NavBean> response = new ResponseBean<>();
		List<NavBean> listAll = DbManager.getAllNavListByPhone(phone);
		System.out.println("getByPhone -----------> "+ listAll.size());
		response.setStatus(Constant.STATUS_OK);
		response.setMsg(Constant.MSG_OK);
		response.setData(listAll.get(0));
		System.out.println("getByPhone -----------> "+ listAll.get(0).toString());
		return response;
	}
	
	@RequestMapping("/gpsinfo/insert")
	public @ResponseBody ResponseBean<NavBean> insertGpsDataByBean(@ModelAttribute NavBean navBean){
		System.out.println("insertGpsDataByBean  begin ====>" + navBean);
		ResponseBean<NavBean> response = new ResponseBean<>();
		boolean insertFlag = DbManager.inserBean(navBean);
		System.out.println("insertGpsDataByBean  End ====>" + navBean);
		if(insertFlag && navBean.getId() > 0 ) {
			response.setStatus(Constant.STATUS_OK);
			response.setMsg(Constant.MSG_OK);
			response.setData(navBean);
		} else {
			response.setStatus(Constant.STATUS_ERROR);
			response.setMsg(Constant.MSG_ERROR);
			response.setData(null);
		}
		
		return response;
	}
	
	@RequestMapping("/gpsinfo/delById")
	public ResponseBean<Boolean> delGpsDataById(@RequestParam("id") int id) {
		ResponseBean<Boolean> response = new ResponseBean<>();
		boolean delFlag = DbManager.delBeanById(id);
		if(delFlag) {
			response.setStatus(Constant.STATUS_OK);
			response.setMsg(Constant.MSG_OK);
			response.setData(delFlag);
		} else {
			response.setStatus(Constant.STATUS_ERROR);
			response.setMsg(Constant.MSG_ERROR);
			response.setData(delFlag);
		}
		return response;
	}

	@RequestMapping("/gpsinfo/update")
	public ResponseBean<NavBean> updateGpsData(@RequestParam("navBean") NavBean bean) {
		ResponseBean<NavBean> response = new ResponseBean<>();
		boolean updateFlag = DbManager.updateBean(bean);
		if(updateFlag ) {
			response.setStatus(Constant.STATUS_OK);
			response.setMsg(Constant.MSG_OK);
			response.setData(bean);
		} else {
			response.setStatus(Constant.STATUS_ERROR);
			response.setMsg(Constant.MSG_ERROR);
			response.setData(null);
		}
		return response;
	}


}
