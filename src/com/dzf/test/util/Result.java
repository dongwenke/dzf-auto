package com.dzf.test.util;

public class Result {
	private boolean result;
	private String message;
	
	public Result(){
		this.result = false;
	}
	
	public Result(boolean result){
		this.result = result;
	}
	
	public Result(boolean result,String message){
		this(result);
		this.message = message;
	}
	
	public boolean isTrue() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
