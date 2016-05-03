package com.dzf.test.page.accounting.结账办理;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.dzf.test.model.Handler;
import com.dzf.test.model.Page;
import com.dzf.test.util.MyException;
import com.dzf.test.util.XMLUtil;

public class 期末处理Page extends Handler {

	public 期末处理Page() {
		super();
		try {
			page = XMLUtil.convert("./config/page/accounting/结账办理/" + this.getClass().getSimpleName() + ".xml",
					Page.class.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * 期末处理查询
	 */
	public void search(String company, String beginDate, String endDate, boolean 仅显示未结转, boolean 仅显示已结转)
			throws MyException {
		// 切换到期末处理iframe
		switchToDefaultContent();
		switchToFrame(getWebElement("期末处理iframe"));

		mouseMoveTo("查询按钮");

		// 判断公司 警告：场景一暂未实现
		if (company != null && company != "") {
			// click("查询面板-选择公司按钮");
		}

		click("开始年份选择按钮");

		// 年份显示是2016,2015
		List<WebElement> elementList = null;
		elementList = getWebElement("开始年份选择悬浮层").findElements(By.tagName("div"));
		for (WebElement webElement : elementList) {
			if (webElement.getText().equals(beginDate.split("-")[0])) {
				webElement.click();
				break;
			}
		}

		click("开始月份选择按钮");

		// 年份显示是2016,2015
		elementList = getWebElement("开始月份选择悬浮层").findElements(By.tagName("div"));
		for (WebElement webElement : elementList) {
			if (webElement.getText().equals(beginDate.split("-")[1])) {
				webElement.click();
				break;
			}
		}

		click("结束年份选择按钮");

		// 年份显示是2016,2015
		elementList = getWebElement("结束年份选择悬浮层").findElements(By.tagName("div"));
		for (WebElement webElement : elementList) {
			if (webElement.getText().equals(endDate.split("-")[0])) {
				webElement.click();
				break;
			}
		}

		click("结束月份选择按钮");

		// 年份显示是2016,2015
		elementList = getWebElement("结束月份选择悬浮层").findElements(By.tagName("div"));
		for (WebElement webElement : elementList) {
			if (webElement.getText().equals(endDate.split("-")[1])) {
				webElement.click();
				break;
			}
		}

		if (仅显示未结转 && 仅显示已结转) {
			logger.warn("仅显示未结转和仅显示已结转不能同时为真，此处以默认不选择任何处理！");
		} else {
			if (仅显示未结转) {
				click("仅显示未结转复选框");
			}
			if (仅显示已结转) {
				click("仅显示已结转复选框");
			}
		}

		click("查询面板-确定按钮");
	}

	/*
	 * 成本结转
	 */
	public boolean 成本结转() throws InterruptedException, MyException {
		switchToDefaultContent();
		switchToFrame(getWebElement("期末处理iframe"));

		click("成本结转按钮");

		Thread.sleep(5000);
		return false;
	}

	/*
	 * 反成本结转
	 */
	public boolean 反成本结转() throws InterruptedException, MyException {
		switchToDefaultContent();
		switchToFrame(getWebElement("期末处理iframe"));

		click("反成本结转按钮");
		Thread.sleep(5000);
		return false;
	}

	/*
	 * 计提折旧
	 */
	public boolean 计提折旧() throws InterruptedException, MyException {
		switchToDefaultContent();
		switchToFrame(getWebElement("期末处理iframe"));

		click("计提折旧按钮");
		Thread.sleep(5000);
		return false;
	}

	/*
	 * 反计提折旧
	 */
	public boolean 反计提折旧() throws InterruptedException, MyException {
		switchToDefaultContent();
		switchToFrame(getWebElement("期末处理iframe"));

		click("反计提折旧按钮");
		Thread.sleep(5000);
		return false;
	}

	public void 期间损益结转() throws InterruptedException, MyException {
		switchToDefaultContent();
		switchToFrame(getWebElement("期末处理iframe"));

		Thread.sleep(1000);

		click("期间损益结转按钮");

		if (isDisplayed("提示框")) {
			click("提示确定按钮");
		}

		Thread.sleep(8000);
	}

	/*
	 * 反期间损益结转
	 */
	public void 反期间损益结转() throws InterruptedException, MyException {
		switchToDefaultContent();
		switchToFrame(getWebElement("期末处理iframe"));

		Thread.sleep(1000);

		click("反期间损益结转按钮");

		Thread.sleep(8000);
	}

	/*
	 * 汇兑损益调整
	 * 
	 */
	public boolean 汇兑损益调整() throws InterruptedException, MyException {
		switchToDefaultContent();
		switchToFrame(getWebElement("期末处理iframe"));

		click("汇兑损益调整按钮");

		Thread.sleep(1000);

		return isDisplayed("汇兑调整面板-取消按钮");
	}

	public boolean 取消汇兑调整() throws InterruptedException, MyException {
		switchToDefaultContent();
		switchToFrame(getWebElement("期末处理iframe"));

		if (!isDisplayed("汇兑调整面板-取消按钮")) {
			return false;
		}

		click("汇兑调整面板-取消按钮");

		Thread.sleep(500);

		return !isDisplayed("汇兑调整面板-取消按钮");
	}

	/*
	 * 刷新
	 */
	public boolean refresh() throws MyException {
		switchToDefaultContent();
		switchToFrame(getWebElement("期末处理iframe"));

		click("刷新按钮");

		return false;
	}

	public boolean selectAll() throws MyException {
		switchToDefaultContent();
		switchToFrame(getWebElement("期末处理iframe"));

		click("全选复选框");

		return false;
	}
}
