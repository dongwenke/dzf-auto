package com.dzf.test.page.accounting.凭证管理;

import java.util.Iterator;
import org.openqa.selenium.By;
import org.testng.Reporter;

import com.dzf.test.model.Handler;
import com.dzf.test.model.Page;
import com.dzf.test.util.MyException;
import com.dzf.test.util.XMLUtil;

public class 凭证管理Page extends Handler {

	public 凭证管理Page() throws Exception {
		super();
		page = XMLUtil.convert("./config/page/accounting/凭证管理/" + this.getClass().getSimpleName() + ".xml",
				Page.class.getName());

	}

	/*
	 * 审核
	 */
	public void auditVoucher() throws InterruptedException, MyException {
		try {
			// 切换到凭证管理页面
			switchToDefaultContent();
			switchToFrame(getWebElement("凭证管理iframe"));

			Thread.sleep(1000);

			// 点击审核按钮
//			Reporter.log("点击审核按钮");
			click("审核按钮");

			Thread.sleep(10000);
		} catch (MyException e) {
			Reporter.log(e.getMessage());
			Reporter.log("审核凭证失败");
			throw e;
		}
	}

	/*
	 * 反审核
	 */
	public void unAuditVoucher() throws InterruptedException, MyException {
		try {
			// 切换到凭证管理页面
			switchToDefaultContent();
			switchToFrame(getWebElement("凭证管理iframe"));

			Thread.sleep(1000);

			// 点击审核按钮
//			Reporter.log("点击反审核按钮");
			click("反审核按钮");

			Thread.sleep(10000);
		} catch (MyException e) {
			Reporter.log(e.getMessage());
			Reporter.log("反审核失败");
			throw e;
		}
	}

	/*
	 * 记账
	 */
	public void accountVoucher() throws InterruptedException, MyException {

		try {
			// 切换到凭证管理页面
			switchToDefaultContent();
			switchToFrame(getWebElement("凭证管理iframe"));

			Thread.sleep(1000);

			// 点击记账按钮
//			Reporter.log("点击记账按钮");
			click("记账按钮");

			Thread.sleep(10000);
		} catch (MyException e) {
			Reporter.log(e.getMessage());
			Reporter.log("记账失败");
			throw e;
		}
	}

	/*
	 * 取消记账
	 */
	public void unAccountVoucher() throws InterruptedException, MyException {
		try {
			// 切换到凭证管理页面
			switchToDefaultContent();
			switchToFrame(getWebElement("凭证管理iframe"));

			Thread.sleep(1000);

			// 点击记账按钮
			click("取消记账按钮");

			Thread.sleep(10000);
		} catch (MyException e) {
			Reporter.log(e.getMessage());
			Reporter.log("反记账失败");
			throw e;
		}
	}

	/*
	 * 凭证查询 参数说明：查询方式；开始日期；结束日期；开始凭证号；结束凭证号；状态；科目；摘要；最小金额；最大金额；公司名称
	 */
	public boolean searchVoucher(String byDateOrPeriod, String beginDate, String endDate, String beginCode,
			String endCode, String status, String subject, String digest, String minMoney, String maxMoney,
			String company) throws MyException {
		try {
			switchToDefaultContent();
			switchToFrame(getWebElement("凭证管理iframe"));

			if (byDateOrPeriod != null && byDateOrPeriod != "") {
				switch (byDateOrPeriod) {
				case "日期":
					searchVoucherByDate(beginDate, endDate);
					break;
				case "期间":
					searchVoucherByPeriod(beginDate, endDate);
					break;
				default:
					return false;
				}
			}

			input("查询面板-开始凭证号输入框", beginCode);

			input("查询面板-结束凭证号输入框", endCode);

			if (status != null && status != "") {
				switch (status) {
				case "未审核":
					click("查询面板-状态选择按钮");
					click("查询面板-状态选项未审核");
					break;
				case "已审核":
					click("查询面板-状态选择按钮");
					click("查询面板-状态选项已审核");
					break;
				case "全部":
				default:
					break;
				}
			}

			input("查询面板-科目输入框", subject);

			input("查询面板-摘要输入框", digest);

			input("查询面板-最小金额输入框", minMoney);

			input("查询面板-最大金额输入框", maxMoney);

			if (company != null) {
				// 暂不实现
				// click("查询面板-公司选择按钮");
			}

			click("查询面板-确定按钮");

			return false;
		} catch (MyException e) {
			Reporter.log(e.getMessage());
			Reporter.log("查找失败");
			throw e;
		}
	}

	public boolean searchVoucherByPeriod(String beginDate, String endDate) throws MyException {
		switchToDefaultContent();
		switchToFrame(getWebElement("凭证管理iframe"));

		click("查询按钮");

		click("凭证查询-期间");

		click("开始年份选择按钮");

		click(getWebElement("开始年份选择悬浮层").findElement(By.xpath("./div[text()='" + beginDate.split("-")[0] + "']")));

		// // 年份显示是2016,2015,2014
//		List<WebElement> elementList = null;
		// elementList =
		// getWebElement("开始年份选择悬浮层").findElements(By.xpath("./div[text()='"+beginDate.split("-")[0]+"']"));
		// for (WebElement webElement : elementList) {
		// if (getText(webElement).equals(beginDate.split("-")[0])) {
		// click(webElement);
		// break;
		// }
		// }

		click("开始月份选择按钮");

		click(getWebElement("开始月份选择悬浮层").findElement(By.xpath("./div[text()='" + beginDate.split("-")[1] + "']")));

		// // 年份显示是2016,2015
		// elementList =
		// getWebElement("开始月份选择悬浮层").findElements(By.tagName("div"));
		// for (WebElement webElement : elementList) {
		// if (getText(webElement).equals(beginDate.split("-")[1])) {
		// click(webElement);
		// break;
		// }
		// }

		click("结束年份选择按钮");

		click(getWebElement("结束年份选择悬浮层").findElement(By.xpath("./div[text()='" + endDate.split("-")[0] + "']")));

		// // 年份显示是2016,2015
		// elementList =
		// getWebElement("结束年份选择悬浮层").findElements(By.tagName("div"));
		// for (WebElement webElement : elementList) {
		// if (getText(webElement).equals(endDate.split("-")[0])) {
		// click(webElement);
		// break;
		// }
		// }

		click("结束月份选择按钮");

		click(getWebElement("结束月份选择悬浮层").findElement(By.xpath("./div[text()='" + endDate.split("-")[1] + "']")));

		// // 年份显示是2016,2015
		// elementList =
		// getWebElement("结束月份选择悬浮层").findElements(By.tagName("div"));
		// for (WebElement webElement : elementList) {
		// if (getText(webElement).equals(endDate.split("-")[1])) {
		// click(webElement);
		// break;
		// }
		// }

		return false;
	}

	public boolean searchVoucherByDate(String beginDate, String endDate) throws MyException {
		switchToDefaultContent();
		switchToFrame(getWebElement("凭证管理iframe"));

		return false;
	}

	/*
	 * 全选
	 */
	public void selectAll() throws MyException {
		switchToDefaultContent();
		switchToFrame(getWebElement("凭证管理iframe"));

		click("全选按钮");
	}

	/*
	 * 全消
	 */
	public void deSelectAll() throws MyException {
		switchToDefaultContent();
		switchToFrame(getWebElement("凭证管理iframe"));

		click("全消按钮");
	}

	/*
	 * 刷新
	 */
	public void refresh() throws MyException {
		try {
			switchToDefaultContent();
			switchToFrame(getWebElement("凭证管理iframe"));

//			Reporter.log("点击刷新按钮");
			click("刷新按钮");
		} catch (MyException e) {
			Reporter.log(e.getMessage());
			Reporter.log("刷新失败");
			throw e;
		}
	}

	/*
	 * 打印
	 */
	public boolean print() throws InterruptedException, MyException {
		try {
			boolean result = false;

			switchToDefaultContent();
			switchToFrame(getWebElement("凭证管理iframe"));

			click("打印按钮");
			// 选择模版
			// 设置边距
			// 选择制单人
			click("打印面板-打印按钮");

			Thread.sleep(5000);

			String prePage = driver.getWindowHandle();

			// 切换选项卡
			Iterator<String> it = driver.getWindowHandles().iterator();// 获得所有窗口句柄报错
			while (it.hasNext()) {
				driver.switchTo().window(it.next());
				if (driver.getTitle().contains("print")) {
					result = true;
					break;
				}
			}

			logger.info(driver.getCurrentUrl());

			// 关闭当前打印窗口
			if (!driver.getWindowHandle().equals(prePage)) {
				driver.close();
				logger.info("当前打印窗口已关闭!");
			}

			// 切换回先前的页面
			driver.switchTo().window(prePage);
			logger.info("driver已切换到先前的页面！");

			return result;
		} catch (MyException e) {
			Reporter.log(e.getMessage());
			Reporter.log("打印失败！");
			throw e;
		}
	}

	/*
	 * 导出 警告：场景一暂不实现 暂无法实现
	 */
	public void export() throws MyException {
		try {
			switchToDefaultContent();
			switchToFrame(getWebElement("凭证管理iframe"));

			click("导出按钮");
		} catch (MyException e) {
			Reporter.log(e.getMessage());
			Reporter.log("导出失败！");
			throw e;
		}
	}

	/*
	 * 通过凭证号选择凭证 警告：场景一暂不实现
	 */
	public void selectVoucherByCode(String VoucherCode) throws MyException {
		try {
			switchToDefaultContent();
			switchToFrame("凭证管理iframe");

		} catch (MyException e) {
			Reporter.log(e.getMessage());
			Reporter.log("选择凭证" + VoucherCode + "失败！");
			throw e;
		}
	}

	/*
	 * 凭证整理 场景一暂不实现
	 */
	public boolean voucherTrim() throws MyException {
		switchToDefaultContent();
		switchToFrame(getWebElement("凭证管理iframe"));

		return false;
	}
}
