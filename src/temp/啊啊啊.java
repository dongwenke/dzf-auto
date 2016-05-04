package temp;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.dzf.test.page.accounting.AccountingLoginPage;
import com.dzf.test.page.accounting.AccountingMainPage;
import com.dzf.test.util.MyException;

public class 啊啊啊 {

	@BeforeTest
	public void setup() throws Exception {

		Assert.assertTrue(new AccountingLoginPage().login("ali001", "ali001!!", "桃子传媒"));

	}

	// @AfterTest
	// public void teardown() throws InterruptedException, MyException {
	// try {
	// mainPage.logout();
	//
	// } catch (MyException e) {
	// logger.error("退出失败！", e);
	// Reporter.log(e.getMessage());
	// Reporter.log("退出失败！");
	// throw e;
	// }
	// }

	/*
	 * 设置汇率档案
	 */
	@Test()
	public void testOpenFrame() throws InterruptedException, MyException {
		AccountingMainPage mainPage = new AccountingMainPage();
		mainPage.openFrame("卡片管理");
		mainPage.openFrame("原值变更");
		mainPage.openFrame("资产清理");
		mainPage.openFrame("工作量管理");

		mainPage.openFrame("折旧汇总表");
		mainPage.openFrame("资产明细账");
		mainPage.openFrame("资产总账");
		mainPage.openFrame("资产与总账对账表");
		mainPage.openFrame("资产折旧明细");

	}

}
