package com.dzf.test.model;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import com.dzf.test.util.MyException;

public interface IHandler {

	public static final String templateXml = "./config/page/template.xml";

	public void open();

	public void quit();

	public String getTitle();

	public String getText(String elementName) throws MyException;

	public String getText(WebElement webElement) throws MyException;

	public WebElement getWebElement(String name) throws MyException;

	public WebElement getWebElement(By locator) throws MyException;

	public List<WebElement> getWebElements(String name) throws MyException;

	public List<WebElement> getWebElements(By locator) throws MyException;

	public By by(Element element) throws Exception;

	public void input(String elementName, String string) throws MyException;

	public void input(WebElement webElement, String inputText) throws MyException;

	public void input(String elementName, Keys key) throws MyException;

	public void click(String elementName) throws MyException;

	public void click(WebElement webElement) throws MyException;

	public boolean isDisplayed(String elementName) throws MyException;

	public boolean isDisplayed(WebElement webElement) throws MyException;

	public boolean isDisplayed(By locator) throws MyException;

	public void mouseMoveTo(String elementName) throws MyException;

	public void scrollTo(int height);

	public void switchToDefaultContent();

	public void switchToFrame(WebElement frameElement) throws MyException;

	public void switchToFrame(String nameOrId) throws MyException;

	public void switchToFrame(int index) throws MyException;

	public boolean isElementExsit(By locator);

	public boolean waitToDisplayed(WebElement webElement) throws MyException;
	
	public boolean waitToDisplayed(String elementName) throws MyException;
	
	public boolean waitToDisplayed(By locator) throws MyException;

	WebElement getWebElement(WebElement webElement, By locator) throws MyException;

	List<WebElement> getWebElements(WebElement webElement, By locator) throws MyException;

}
