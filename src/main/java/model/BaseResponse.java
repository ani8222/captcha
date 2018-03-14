package model;



public class BaseResponse {
	
	protected Response response;

	public BaseResponse(Response response) {
		this.response = response;
	}

	public Response getResponse() {
		return response;
	}

	public void setResponse(Response response) {
		this.response = response;
	}	
}