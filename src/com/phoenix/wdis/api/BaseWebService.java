package com.phoenix.wdis.api;

import java.io.IOException;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.params.HttpConnectionParams;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.phoenix.wdis.R;
import com.phoenix.wdis.api.bean.response.BaseResponseBean;
import com.phoenix.wdis.api.listener.CTApiListener;
import com.phoenix.wdis.app.WDISApp;
import com.phoenix.wdis.util.CTApiConstants;
import com.phoenix.wdis.util.Utility;

/**
 * Class Name: CTBaseWebService Class Desc: The class for implementing
 * webservice related functionality
 * 
 * @author: QBurst
 */

public class BaseWebService extends AsyncTask<String, Void, String> {

	private String _postParams = null;
	private HttpClient _httpClient = null;
	private HttpResponse _httpResponse = null;
	private CTApiListener _apiListener;
	private int _httpStatus = 0;
	private String _userName = null, _password = null;
	private Class<?> _responseClass;

	public BaseWebService(CTApiListener apiListener) {
		this._apiListener = apiListener;
	}

	public BaseWebService(CTApiListener apiListener, String postParams) {
		this._apiListener = apiListener;
		this._postParams = postParams;
	}

	public BaseWebService(CTApiListener apiListener, String postParams,
			Class<?> responseClass) {
		this._apiListener = apiListener;
		this._postParams = postParams;
		this._responseClass = responseClass;
	}

	public void setAuthenticationParams(String username, String password) {
		this._userName = username;
		this._password = password;
		Authenticator.setDefault(new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(_userName, _password
						.toCharArray());
			}
		});
	}

	@Override
	protected String doInBackground(String... urls) {

		String serverResponse = null;

		if (Utility.isNetworkAvailable(WDISApp.getSharedApplication()
				.getApplicationContext())) {
			try {
				// POST
				_httpResponse = this.doJsonPostRequest(urls[0]);

				if (_httpResponse != null) {

					serverResponse = Utility
							.convertStreamToString(_httpResponse.getEntity()
									.getContent());
					_httpStatus = _httpResponse.getStatusLine().getStatusCode();

				} else {
					serverResponse = CTApiConstants.kApiError;
				}
			} catch (ClientProtocolException e) {
				serverResponse = CTApiConstants.kApiError;
				e.printStackTrace();
			} catch (IOException e) {
				serverResponse = CTApiConstants.kApiError;
				e.printStackTrace();
			}
		} else {
			serverResponse = CTApiConstants.kNetworkError;
		}
		Log.i("serverResponse", "" + serverResponse);
		return serverResponse;
	}

	@Override
	protected void onPostExecute(String result) {

		Map<String, Object> response = new HashMap<String, Object>();
		response.put(CTApiConstants.kHttpStatusKey, _httpStatus);

		if (result.equalsIgnoreCase(CTApiConstants.kNetworkError)) {
			response.put(CTApiConstants.kErrorMsgKey,
					CTApiConstants.kNetworkErrorExceptionKey);
			_apiListener.onFailedToGetResponse(response);

		} else if ((result != null)
				&& ((_httpStatus == HttpStatus.SC_OK) || (_httpStatus == HttpStatus.SC_CREATED))) {

			BaseResponseBean responseBean = getBeanFromResult(result);

			if (responseBean.getStatus().equalsIgnoreCase(
					CTApiConstants.kSuccessMsgKey)) {
				response.put(CTApiConstants.kSuccessMsgKey, responseBean);
			} else {
				response.put(CTApiConstants.kApiFailedMsgKey, responseBean);
			}

			_apiListener.onResponseReceived(response);

		} else if (_httpStatus == 401) {

			response.put(CTApiConstants.kErrorMsgKey,
					CTApiConstants.kAuthenticationErrorExceptionKey);
			_apiListener.onFailedToGetResponse(response);

		} else if (_httpStatus == 500) {
			response.put(CTApiConstants.kErrorMsgKey,
					CTApiConstants.kInternalServerErrorExceptionKey);
			_apiListener.onFailedToGetResponse(response);

		} else if (_httpStatus == 400) {
			response.put(CTApiConstants.kErrorMsgKey,
					CTApiConstants.kBadRequestErrorExceptionKey);
			_apiListener.onFailedToGetResponse(response);
		} else if (result.equalsIgnoreCase(CTApiConstants.kApiError)) {
			response.put(CTApiConstants.kErrorMsgKey,
					CTApiConstants.kTimeOutErrorExceptionKey);
			_apiListener.onFailedToGetResponse(response);
		} else {
			response.put(CTApiConstants.kApiFailedMsgKey,
					getBeanFromResult(result));
			_apiListener.onFailedToGetResponse(response);
		}

	}

	private BaseResponseBean getBeanFromResult(String result) {
		Gson gson = new Gson();
		try {
			return (BaseResponseBean) gson.fromJson(result, _responseClass);
		} catch (Exception ex) {

			BaseResponseBean bean = null;
			try {
				bean = (BaseResponseBean) _responseClass.newInstance();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			}

			bean.setStatus(CTApiConstants.kApiFailedMsgKey);

			BaseResponseBean errBean = new BaseResponseBean();
			errBean.setStatusCode(CTApiConstants.kApiFailedMsgKey);
			errBean.setErrorMessage(WDISApp.getSharedApplication()
					.getApplicationContext().getResources()
					.getString(R.string.generic_error_message));
			errBean.setStatus("failed");
			return bean;
		}
	}

	private HttpResponse doJsonPostRequest(String url)
			throws ClientProtocolException {

		HttpResponse httpResponse = null;

		try {
			HttpConnectionParams.setConnectionTimeout(_httpClient.getParams(),

			CTApiConstants.kRequestTimeOutInMills);
			HttpConnectionParams.setSoTimeout(_httpClient.getParams(),
					CTApiConstants.kRequestTimeOutInMills);

			HttpPost httpPost = new HttpPost(url);

			// Add post body.

			StringEntity se = new StringEntity(_postParams);
			httpPost.setHeader("Accept", "application/json");
			httpPost.setHeader("Content-type", "application/json");
			if (_userName != null) {
				httpPost.setHeader("Authorization", _userName + ":" + _password);
			}

			httpPost.setEntity(se);

			// Execute the request

			httpResponse = _httpClient.execute(httpPost);
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return null;
		}

		return httpResponse;
	}
}
