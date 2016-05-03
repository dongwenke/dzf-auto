package com.dzf.test.page.accounting;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import org.testng.Reporter;

import com.dzf.test.model.Handler;
import com.dzf.test.model.Page;
import com.dzf.test.util.MyException;
import com.dzf.test.util.WebTableUtil;
import com.dzf.test.util.XMLUtil;

public class AccountingLoginPage extends Handler {
	private final String xmlfile = "./config/page/accounting/" + this.getClass().getSimpleName() + ".xml";

	public AccountingLoginPage() throws Exception {
		super();
		page = XMLUtil.convert(xmlfile, Page.class);
	}

	public boolean login(String name, String password, String company) throws InterruptedException, MyException {
		try {
			// 打开登录页面
			Reporter.log("打开登录页面");
			this.open();

			// 输入用户名
			Reporter.log("用户名输入：" + name);
			input("用户名输入框", name);

			// 输入密码
			Reporter.log("密码输入：" + name);
			input("密码输入框", password);

			// 点击登录按钮
			Reporter.log("点击登录按钮");
			click("登录按钮");

			// 判断该用户是否已登录
			Reporter.log("判断该用户是否已登录");
			if (isDisplayed("已登录提示")) {

				Reporter.log("该用户已登录！");

				// 如果显示则点击确定
				Reporter.log("点击确定强制退出按钮！");
				click("确认强制退出");

			} else {
				Reporter.log("该用户未登录！");
			}

			Thread.sleep(1000);

			Reporter.log("选择公司面板-搜索框输入：" + company);
			input("选择公司面板-搜索输入框", company);

			Reporter.log("选择公司面板-搜索框输入：回车");
			input("选择公司面板-搜索输入框", Keys.RETURN);

			WebTableUtil table = new WebTableUtil(getWebElement("选择公司面板-公司列表"));
			boolean hasConpany = false;
			for (int i = 0; i < table.getRowCount(); i++) {
				String companytmp = table.getCell(i, 1).findElement(By.tagName("div")).getText();
				if (companytmp.equals(company)) {
					hasConpany = true;

					Reporter.log("点击公司：" + company);

					click(table.getRow(i));
				}
			}

			if (!hasConpany) {
				logger.info("没有找到公司：" + company);
				Reporter.log("没有找到公司：" + company);
				return false;
			}

			// 点击确定
			Reporter.log("点击选择公司面板-确定");
			click("确定所选公司");

			return isDisplayed("用户信息");
		} catch (MyException e) {
			logger.error("登录失败！", e);
			Reporter.log(e.getMessage());
			Reporter.log("登录失败！");
			throw e;
		}
	}
}
