package com.ddup4.autonav.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ddup4.autonav.db.DbManager;

@RestController
public class ControllerDemo {
 
	public ControllerDemo (){}

	@RequestMapping("/gpsinfo/list")
	public @ResponseBody Object getGpsInfoList() {
		return DbManager.getAllNavList();
	}


}
