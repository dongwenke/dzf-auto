package com.dzf.test.page.accounting.基础设置_总账;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;

import com.dzf.test.model.Handler;
import com.dzf.test.model.Page;
import com.dzf.test.util.MyException;
import com.dzf.test.util.WebTableUtil;
import com.dzf.test.util.XMLUtil;

public class 会计科目Page extends Handler {
	
	private final String xmlfile = "./config/page/accounting/基础设置_总账/" + this.getClass().getSimpleName() + ".xml";

	public 会计科目Page() throws Exception {
		super();
		page = XMLUtil.convert(xmlfile, Page.class);
	}

	public boolean modify() throws InterruptedException, MyException {

		switchToDefaultContent();

		switchToFrame(getWebElement("会计科目iframe"));

		if (getText(getWebElement("资产-其他货币资金-外汇核算币种")).contains("美元")) {
			return true;
		}

		click("其它货币资金");

		click("修改按钮");

		Thread.sleep(500);

		click("是否外汇核算选择按钮");

		click("是否外汇核算选项是");

		new Actions(driver).sendKeys(getWebElement(By.xpath(".//*[@id='accform']/table/tbody/tr[3]/th")), Keys.DOWN)
				.perform();

		click("外汇核算币种选择按钮");

		click("外汇核算币种选项-美元复选框");

		click("外汇核算币种选择面板-确定按钮");

		click("科目修改面板-保存按钮");

		Thread.sleep(1000);

		click("刷新按钮");

		Thread.sleep(500);

		if (getWebElement("资产-其他货币资金-外汇核算币种").getText().contains("美元")) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * 参数：会计科目、币别
	 */
	public boolean modify(String subject, String currency) throws InterruptedException, MyException {

		switchToDefaultContent();

		switchToFrame(getWebElement("会计科目iframe"));

		// 判断科目的外汇核算币种是否有值
		// 从左table得到科目所在的行号，然后去右table查找外汇核算币种的值
		// String lineNo =
		// getText(getWebElement(getSubjectTr(subject),By.xpath("/td[@class='datagrid-td-rownumber']/div")));
		//
		// WebTableUtil table2= new WebTableUtil(getWebElement("右table"));
		//
		// if (getText(getWebElement("资产-其他货币资金-外汇核算币种")).contains(currency)) {
		// return true;
		// }

		Reporter.log("点击科目所在行");
		click(getSubjectTr(subject));

		Reporter.log("点击修改按钮");
		click("修改按钮");

		Thread.sleep(500);

		if (getWebElement("外汇核算币种输入框").getAttribute("value").contains(currency)) {
			Reporter.log("科目：" + subject + "已启用:" + currency);

			click("科目修改面板-取消按钮");
			return true;
		}

		Reporter.log("点击是否外汇核算按钮");
		click("是否外汇核算选择按钮");

		Reporter.log("点击是");
		click("是否外汇核算选项是");

		new Actions(driver).sendKeys(getWebElement(By.xpath(".//*[@id='accform']/table/tbody/tr[3]/th")), Keys.DOWN)
				.perform();

		Reporter.log("点击外汇核算币种选择按钮");
		click("外汇核算币种选择按钮");

		Thread.sleep(500);
		Reporter.log("点击" + currency);
		click(new WebTableUtil(getWebElement("外汇核算币种面板table")).getTr(currency));

		Thread.sleep(500);
		
		Reporter.log("点击确定");
		click("外汇核算币种选择面板-确定按钮");
		
		Thread.sleep(500);
		Reporter.log("点击保存");
		click("科目修改面板-保存按钮");

		Thread.sleep(1000);

		Reporter.log("点击刷新");
		click("刷新按钮");

		Thread.sleep(500);

		Reporter.log("点击科目所在行");
		click(getSubjectTr(subject));

		Reporter.log("点击修改按钮");
		click("修改按钮");

		Thread.sleep(500);

		if (getWebElement("外汇核算币种输入框").getAttribute("value").contains(currency)) {
			Reporter.log("科目：" + subject + "已启用:" + currency);
			click("科目修改面板-取消按钮");
			return true;
		} else {
			Reporter.log("科目：" + subject + "启用:" + currency+"失败！");
			click("科目修改面板-取消按钮");
			return false;
		}

	}

	public WebElement getSubjectTr(String subject) throws InterruptedException, MyException {

		String tableList[] = { "资产左table", "负债左table", "共同左table", "所有者权益左table", "成本左table", "损益左table" };
		for (String table : tableList) {

			switch (table) {
			case "资产左table":
				Reporter.log("点击资产");
				click("资产类别");
				break;
			case "负债左table":
				Reporter.log("点击负债");
				click("负债类别");
				break;
			case "共同左table":
				Reporter.log("点击共同");
				click("共同类别");
				break;
			case "所有者权益左table":
				Reporter.log("点击所有者权益");
				click("所有者权益类别");
				break;
			case "成本左table":
				Reporter.log("点击成本");
				click("成本类别");
				break;
			case "损益左table":
				Reporter.log("点击损益");
				click("损益类别");
				break;
			}

			Thread.sleep(800);

			return new WebTableUtil(getWebElement(table)).getTr(subject);
		}

		return null;
	}

}
