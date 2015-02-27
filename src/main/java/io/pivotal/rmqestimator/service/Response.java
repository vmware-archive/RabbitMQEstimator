package io.pivotal.rmqestimator.service;

public class Response implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 540652869053982980L;
	String httpResponse;
	String interpretedResponse;
	/**
	 * @return the httpResponse
	 */
	public String getHttpResponse() {
		return httpResponse;
	}
	/**
	 * @param httpResponse the httpResponse to set
	 */
	public void setHttpResponse(String httpResponse) {
		this.httpResponse = httpResponse;
	}
	/**
	 * @return the interpretedResponse
	 */
	public String getInterpretedResponse() {
		return interpretedResponse;
	}
	/**
	 * @param interpretedResponse the interpretedResponse to set
	 */
	public void setInterpretedResponse(String interpretedResponse) {
		this.interpretedResponse = interpretedResponse;
	}
}
