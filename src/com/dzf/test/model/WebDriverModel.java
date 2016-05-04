package com.dzf.test.model;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.dzf.test.util.WebDriverFactory;

public class WebDriverModel {
	
	public static WebDriver driver;
	public static WebDriverWait wait;
	
	public WebDriverModel() {
		if(driver == null){
			setDriver(WebDriverFactory.create());
		}
	}
	
	public void setDriver(WebDriver driver) {
		this.driver = driver;
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);//识别元素时的超时时间
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);//页面加载时的超时时间
		driver.manage().timeouts().setScriptTimeout(6, TimeUnit.SECONDS);//异步脚本的超时时间
		driver.manage().window().maximize();
		
		if (this.wait == null){
			this.wait = new WebDriverWait(this.driver, 5);
		}
	}
	
	public static WebDriver getDriver(){
		return driver;
	}
	
}
