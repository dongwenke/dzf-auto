package com.dzf.test.model;

import java.lang.reflect.Method;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

//import org.testng.Reporter;

import com.dzf.test.util.ILogUtil;
import com.dzf.test.util.MyException;

public class Handler extends WebDriverModel implements IHandler, ILogUtil {
	public Page page;

	@Override
	public void open() {
		logger.info("打开页面：【" + page.getName() + "】");
		driver.get(page.getUrl());
	}

	@Override
	public void quit() {
		logger.info("关闭浏览器！");
		driver.quit();
	}

	@Override
	public WebElement getWebElement(String elementName) throws MyException {
		try {

			// element =
			// wait.until(ExpectedConditions.presenceOfElementLocated(by(getElement(elementName))));

			WebElement webElement = getWebElement(by(getElement(elementName)));

			logger.info("【" + elementName + "】 已找到！");

			return webElement;

		} catch (WebDriverException e) {

			logger.error("【" + elementName + "】 未找到！");

			throw new MyException("元素未找到：" + elementName, e);
		}

	}

	@Override
	public WebElement getWebElement(By locator) throws MyException {
		try {

			// wait.until(ExpectedConditions.presenceOfElementLocated(locator));

			WebElement element = driver.findElement(locator);

			// highlightElementUtil.highlightElement(element);

			return element;
		} catch (WebDriverException e) {
			logger.error("元素未找到：" + locator);
			// Reporter.log("元素未找到：" + locator);
			throw new MyException("元素未找到：" + locator, e);
		}
	}

	@Override
	public WebElement getWebElement(WebElement webElement, By locator) throws MyException {
		WebElement element = null;

		try {

			element = webElement.findElement(locator);

		} catch (WebDriverException e) {
			logger.error("元素未找到：" + locator);
			throw new MyException("元素未找到：" + locator, e);
		}

		return element;
	}

	@Override
	public List<WebElement> getWebElements(WebElement webElement, By locator) throws MyException {
		List<WebElement> elementList = null;

		try {

			elementList = webElement.findElements(locator);

			// highlightElementUtil.highlightElement(element);

		} catch (WebDriverException e) {
			logger.error("元素未找到：" + locator);
			// Reporter.log("元素未找到：" + locator);
			throw new MyException("元素未找到：" + locator, e);
		}

		return elementList;
	}

	@Override
	public List<WebElement> getWebElements(String elementName) throws MyException {
		try {
			// wait.until(ExpectedConditions.presenceOfElementLocated(by(getElement(elementName))));

			List<WebElement> elementList = getWebElements(by(getElement(elementName)));

			logger.info("【" + elementName + "】 已找到！");

			return elementList;
		} catch (WebDriverException | NullPointerException e) {
			// Reporter.log("【" + elementName + "】 未找到！");
			logger.info("【" + elementName + "】 未找到！");
			throw new MyException("元素未找到：" + elementName, e);
		}
	}

	@Override
	public List<WebElement> getWebElements(By locator) throws MyException {

		try {
			// wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			List<WebElement> elementList = driver.findElements(locator);

			logger.info("【" + locator + "】 已找到！");

			return elementList;
		} catch (WebDriverException e) {
			logger.error("【" + locator + "】 未找到！");
			// Reporter.log("【" + locator + "】 未找到！");
			throw new MyException("元素未找到：" + locator, e);
		}

	}

	@Override
	public By by(Element element) {
		By by = null;
		if (element != null) {
			try {
				Class<By> classType = By.class;
				Method method = classType.getMethod(element.getMethod(), String.class);
				by = (By) method.invoke(classType, element.getValue());

			} catch (NoSuchMethodException e) {
				// Reporter.log("【" + element.getName() + "】：method【" +
				// element.getMethod() + "】有误!");
				e.printStackTrace();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return by;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dzf.test.model.IHandler#input(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public void input(String elementName, String string) throws MyException {
		try {
////			WebElement element = getWebElement(elementName);
			input(getWebElement(elementName),string);
			
//			element.clear();
//			element.sendKeys(string);
			logger.info("【" + elementName + "】 已输入：" + string);
		} catch (WebDriverException e) {
			logger.error("【" + elementName + "】 输入失败！");
			// Reporter.log("【" + elementName + "】 输入失败！");
			throw new MyException("【" + elementName + "】 输入失败！", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dzf.test.model.IHandler#input(java.lang.String,
	 * org.openqa.selenium.Keys)
	 */
	@Override
	public void input(String elementName, Keys key) throws MyException {
		try {
			WebElement element = getWebElement(elementName);
			element.sendKeys(key);
			// logger.info("【" + elementName + "】 已输入：" + );
		} catch (WebDriverException e) {
			logger.error("【" + elementName + "】 输入失败！");
			// Reporter.log("【" + elementName + "】 输入失败！");
			throw new MyException("【" + elementName + "】 输入失败！", e);
		}

	}

	@Override
	public void input(WebElement webElement, String inputText) throws MyException {
		try {
			webElement.clear();
			webElement.sendKeys(inputText);

			logger.info("已输入：" + inputText);
		} catch (WebDriverException e) {
			throw new MyException("【" + webElement + "】 输入失败！", e);
		}

	}

	@Override
	public void click(String elementName) throws MyException {
		try {
//			WebElement element = getWebElement(elementName);

//			wait.until(ExpectedConditions.elementToBeClickable(element));
//
//			// highlightElementUtil.highlightElement(element);
//
//			Thread.sleep(1000);
//
//			element.click();

			click(getWebElement(elementName));
			
			logger.info("点击【" + elementName + "】");
		} catch (WebDriverException e) {
			logger.error("【" + elementName + "】点击失败！");
			throw new MyException("【" + elementName + "】 点击失败！", e);

		} 
	}

	@Override
	public void click(WebElement webElement) throws MyException {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(webElement));

			// highlightElementUtil.highlightElement(webElement);

			Thread.sleep(1000);

			webElement.click();

			logger.info("点击【" + webElement.getText().replaceAll("\n", "") + "】");
		} catch (WebDriverException e) {

			logger.error("【" + webElement.toString() + "】点击失败！");
			// Reporter.log("【" + webElement.toString() + "】点击失败！");
			throw new MyException("【" + webElement.toString() + "】点击失败！", e);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	@Override
	public boolean waitToDisplayed(String elementName) throws MyException {
		boolean result = false;

		try {
			result = wait.until(ExpectedConditions.visibilityOfElementLocated(by(getElement(elementName))))
					.isDisplayed();
			logger.info("【" + elementName + "】 已显示！");
		} catch (WebDriverException e) {
			logger.error("【" + elementName + "】 没有显示！");

			throw new MyException(elementName + "没有显示！", e);
		}

		return result;
	}

	@Override
	public boolean waitToDisplayed(WebElement webElement) throws MyException {
		boolean result = false;

		try {
			result = wait.until(ExpectedConditions.visibilityOf(webElement)).isDisplayed();

			logger.info("【" + webElement + "】 已显示！");
		} catch (WebDriverException e) {
			logger.error("【" + webElement + "】 没有显示！");

			throw new MyException(webElement + "没有显示！", e);
		}

		return result;
	}

	@Override
	public boolean waitToDisplayed(By locator) throws MyException {
		boolean result = false;

		try {
			result = wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();

			logger.info("【" + locator + "】 已显示！");
		} catch (WebDriverException e) {
			logger.error("【" + locator + "】 没有显示！");

			throw new MyException(locator + "没有显示！", e);
		}

		return result;
	}

	@Override
	public boolean isDisplayed(String elementName) throws MyException {
		boolean result = false;

		try {
			result = getWebElement(elementName).isDisplayed();

		} catch (WebDriverException e) {
			// throw new MyException("元素未找到：" + elementName, e);
		}

		logger.info("【" + elementName + "】 已显示：" + result);

		return result;
	}

	@Override
	public boolean isDisplayed(WebElement webElement) {
		boolean result = false;

		result = webElement.isDisplayed();

		logger.info("【" + webElement + "】 已显示：" + result);

		return result;
	}

	@Override
	public boolean isDisplayed(By locator) throws MyException {
		boolean result = false;

		try {
			result  = getWebElement(locator).isDisplayed();
		} catch (WebDriverException e) {
			// throw new MyException("元素未找到：" + locator, e);
		}

		logger.info("【" + locator + "】 已显示：" + result);

		return result;
	}

	@Override
	public String getTitle() {
		return driver.getTitle();
	}

	@Override
	public String getText(String elementName) throws MyException {
		String result = null;
		try {
			result = getText(getWebElement(elementName));

			logger.info("已得到【" + elementName + "】的文本:" + result);

		} catch (WebDriverException e) {

			logger.info("获取【" + elementName + "】的文本失败");
			// Reporter.log("获取【" + elementName + "】的文本失败");

			throw new MyException("获取【" + elementName + "】的文本失败", e);
		}

		return result;
	}

	public String getText(WebElement webElement) {
		return webElement.getText();
	}

	@Override
	public void mouseMoveTo(String elementName) throws MyException {
		try {
			Actions action = new Actions(driver);
			action.moveToElement(getWebElement(elementName)).perform();

			logger.info("鼠标移动到：" + elementName);
		} catch (WebDriverException e) {
			logger.info("鼠标移动到：" + elementName + "失败！");
			// Reporter.log("鼠标移动到：" + elementName + "失败");
			throw new MyException("鼠标移动到：" + elementName + "失败！", e);
		}

	}

	@Override
	public void scrollTo(int height) {
		try {
			String setscroll = "document.documentElement.scrollTop=" + height;

			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript(setscroll);
		} catch (WebDriverException e) {
			logger.info("Fail to set the scroll.");

			throw e;
		}
	}

	@Override
	public void switchToDefaultContent() throws WebDriverException {
		driver.switchTo().defaultContent();
	}

	@Override
	public void switchToFrame(WebElement frameElement) throws MyException {
		try {
			driver.switchTo().frame(frameElement);
			logger.info("切换到frame：" + frameElement + "！");
		} catch (WebDriverException e) {
			logger.error("切换到frame：" + frameElement + "失败！");
			// Reporter.log("切换到frame：" + frameElement + "失败！");
			throw new MyException("切换到frame：" + frameElement + "失败！", e);
		}
	}

	@Override
	public void switchToFrame(String nameOrId) throws MyException {
		try {
			driver.switchTo().frame(nameOrId);
		} catch (WebDriverException e) {
			logger.error("切换到frame：" + nameOrId + "失败！");
			// Reporter.log("切换到frame：" + nameOrId + "失败！");
			throw new MyException("切换到frame：" + nameOrId + "失败！", e);
		}

	}

	@Override
	public void switchToFrame(int index) throws MyException {
		try {
			driver.switchTo().frame(index);
		} catch (WebDriverException e) {
			logger.error("切换到frame：" + index + "失败！");
			// Reporter.log("切换到frame：" + index + "失败！");
			throw new MyException("切换到frame：" + index + "失败！", e);
		}
	}

	@Override
	public boolean isElementExsit(By locator) {
		boolean flag = false;
		try {
			WebElement element = driver.findElement(locator);
			flag = null != element;
		} catch (NoSuchElementException e) {
			System.out.println("Element:" + locator.toString() + " is not exsit!");
		}
		return flag;
	}

	public Element getElement(String elementName) {
		Element element = page.getElement(elementName);

		if (element == null) {
			// Reporter.log(elementName + "不存在！");

			throw new NullPointerException(elementName + "不存在！");
		}

		return element;
	}

	private WebDriverException creatException(WebDriverException e, String elementName) {

		switch (e.getClass().getSimpleName()) {
		case "NoSuchElementException":
			return new WebDriverException("元素未找到：" + elementName);
		default:
			break;
		}
		return e;

	}
}
