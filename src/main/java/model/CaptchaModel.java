package model;

import java.util.Date;

public class CaptchaModel {

	private String resultCode;
	private String status;
	private String code;
	private String expireDate;
	private byte[] image;


	public CaptchaModel() {
	}


	public synchronized String getResultCode() {
		return resultCode;
	}


	public synchronized void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}


	public synchronized String getStatus() {
		return status;
	}


	public synchronized void setStatus(String status) {
		this.status = status;
	}


	public synchronized String getCode() {
		return code;
	}


	public synchronized void setCode(String code) {
		this.code = code;
	}


	public synchronized String getExpireDate() {
		return expireDate;
	}


	public synchronized void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}

	

	public synchronized byte[] getImage() {
		return image;
	}


	public synchronized void setImage(byte[] image) {
		this.image = image;
	}


	@Override
	public String toString() {
		return "CaptchaModel [resultCode=" + resultCode + ", status=" + status + ", code=" + code + ", expireDate="
				+ expireDate + "]";
	}

 



}