package com.dzf.test.page.accounting.基础设置_总账;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
//import org.testng.Reporter;
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

	public boolean add(String parentSubjectCode, /* 科目编码 */String subjectCode, /* 科目名称 */ String subjectName,
			String 是否数量核算, String 计量单位, String 是否外汇核算, /* 币种名称 */String currency)
			throws InterruptedException, MyException {
		try {
			switchToDefaultContent();
			switchToFrame("会计科目");

			// 选择父资产所在的行 并点击
			click(getSubjectTr(parentSubjectCode));
			// 点击增加按钮
			click("增加按钮");

			editSubject(subjectCode, subjectName, 是否数量核算, 计量单位, 是否外汇核算, currency);

		} catch (MyException e) {
			Reporter.log("增加会计科目失败！");
			throw e;
		}
		return false;
	}

	@Deprecated
	public boolean modify() throws InterruptedException, MyException {

		switchToDefaultContent();

		switchToFrame("会计科目");

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
	public boolean modify(/* 科目编码 */String subjectCode, String currency) throws InterruptedException, MyException {

		switchToDefaultContent();

		switchToFrame("会计科目iframe");

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

		// Reporter.log("点击科目所在行");
		click(getSubjectTr(subjectCode));

		// Reporter.log("点击修改按钮");
		click("修改按钮");

		Thread.sleep(500);

		if (getWebElement("外汇核算币种输入框").getAttribute("value").contains(currency)) {
			// Reporter.log("科目：" + subject + "已启用:" + currency);

			click("科目修改面板-取消按钮");
			return true;
		}

		// Reporter.log("点击是否外汇核算按钮");
		click("是否外汇核算选择按钮");

		// Reporter.log("点击是");
		click("是否外汇核算选项是");

		new Actions(driver).sendKeys(getWebElement(By.xpath(".//*[@id='accform']/table/tbody/tr[3]/th")), Keys.DOWN)
				.perform();

		// Reporter.log("点击外汇核算币种选择按钮");
		click("外汇核算币种选择按钮");

		Thread.sleep(500);
		// Reporter.log("点击" + currency);
		click(new WebTableUtil(getWebElement("外汇核算币种面板table")).getTr(currency));

		Thread.sleep(500);

		// Reporter.log("点击确定");
		click("外汇核算币种选择面板-确定按钮");

		Thread.sleep(500);
		// Reporter.log("点击保存");
		click("科目修改面板-保存按钮");

		Thread.sleep(1000);

		// Reporter.log("点击刷新");
		click("刷新按钮");

		Thread.sleep(500);

		// Reporter.log("点击科目所在行");
		click(getSubjectTr(subjectCode));

		// Reporter.log("点击修改按钮");
		click("修改按钮");

		Thread.sleep(500);

		if (getWebElement("外汇核算币种输入框").getAttribute("value").contains(currency)) {
			// Reporter.log("科目：" + subject + "已启用:" + currency);
			click("科目修改面板-取消按钮");
			return true;
		} else {
			// Reporter.log("科目：" + subject + "启用:" + currency+"失败！");
			click("科目修改面板-取消按钮");
			return false;
		}

	}

	public WebElement getSubjectTr(String subjectCode) throws InterruptedException, MyException {

		String tableList[] = { "资产左table", "负债左table", "共同左table", "所有者权益左table", "成本左table", "损益左table" };
		for (String table : tableList) {

			switch (table) {
			case "资产左table":
				// Reporter.log("点击资产");
				click("资产类别");
				break;
			case "负债左table":
				// Reporter.log("点击负债");
				click("负债类别");
				break;
			case "共同左table":
				// Reporter.log("点击共同");
				click("共同类别");
				break;
			case "所有者权益左table":
				// Reporter.log("点击所有者权益");
				click("所有者权益类别");
				break;
			case "成本左table":
				// Reporter.log("点击成本");
				click("成本类别");
				break;
			case "损益左table":
				// Reporter.log("点击损益");
				click("损益类别");
				break;
			}

			Thread.sleep(800);

			return new WebTableUtil(getWebElement(table)).getTr(subjectCode);
		}

		return null;
	}

	/*
	 * 
	 */
	public boolean editSubject(/* 科目编码 */String subjectCode, /* 科目名称 */ String subjectName, String 是否数量核算, String 计量单位,
			String 是否外汇核算, /* 币种名称 */String currency) throws InterruptedException, MyException {
		try {
			switchToDefaultContent();
			switchToFrame("会计科目");

			if (subjectCode != null && !subjectCode.equals("")) {
				input("修改科目面板-科目编码输入框", subjectCode);
			}

			if (subjectName != null && !subjectName.equals("")) {
				input("修改科目面板-科目名称输入框", subjectName);
			}

			if (是否数量核算 != null && !是否数量核算.equals("")) {

				if (是否数量核算.equals("否")) {
					click("修改科目面板-是否数量核算选择按钮");

					click("是否数量核算选项-否");

				} else if (是否数量核算.equals("是")) {

					if (计量单位 != null && !计量单位.equals("")) {
						// *[@id='sfsz']/following-sibling::span/span/a
						click("修改科目面板-是否数量核算选择按钮");

						// (//div[text()='是' and
						// starts-with(@id,'_easyui_combobox_')
						// and
						// contains(@class,'combobox-item')])[1]
						click("是否数量核算选项-是");

						input("修改科目面板-计量单位输入框", 计量单位);

					} else {
						throw new MyException("请设置具体的计量单位！");
					}
				}
			}

			if (是否外汇核算 != null && !是否外汇核算.equals("")) {

				if (是否外汇核算.equals("否")) {

					new Actions(driver).sendKeys(getWebElement("是修改科目面板-否数量核算标签"), Keys.DOWN).perform();

					click("修改科目面板-是否外汇核算选择按钮");

					click("修改科目面板-是否外汇核算选项-否");

				} else if (是否外汇核算.equals("是")) {

					if (currency != null && !currency.equals("")) {

						if (!getWebElement("修改科目面板-外汇核算币种输入框").getAttribute("value").contains(currency)) {

							new Actions(driver).sendKeys(getWebElement("是修改科目面板-否数量核算标签"), Keys.DOWN).perform();

							click("修改科目面板-是否外汇核算选择按钮");

							click("修改科目面板-是否外汇核算选项-是");

							click("修改科目面板-外汇核算币种选择按钮");

							click(new WebTableUtil(getWebElement("外汇核算币种面板table")).getTr(currency));

							click("外汇核算币种选择面板-确定按钮");

						}
					}
				}
			}

			click("修改科目面板-保存按钮");

			return !isDisplayed("修改科目面板-保存按钮");

		} catch (MyException e) {
			Reporter.log(e.getMessage());
			Reporter.log("修改会计科目失败！");
			throw e;
		}

	}

}
