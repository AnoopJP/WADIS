/**
 * 
 */
package com.phoenix.wdis.api.bean.response;

import com.phoenix.wdis.api.bean.BaseBean;

public class BaseResponseBean extends BaseBean {

	private String StatusCode;
	private String Status;
	private String errorMessage = "";

	public String getStatusCode() {
		return StatusCode;
	}

	public void setStatusCode(String statusCode) {
		StatusCode = statusCode;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
