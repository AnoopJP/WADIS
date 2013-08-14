package com.phoenix.wdis.api.bean;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class BaseBean {

	public BaseBean() {

	}

	public String toJsonString() {
		Gson gson = new GsonBuilder().create();
		return gson.toJson(this);
	}

}
