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
		mainPage.openFrame("填制凭证");
		mainPage.openFrame("凭证管理");
		mainPage.openFrame("利润表季报");
		mainPage.openFrame("利润表");
//		mainPage.openFrame("收入支出表");
		mainPage.openFrame("现金流量表");
//		mainPage.openFrame("业务活动表");
		mainPage.openFrame("增值税和营业税月度申报对比表");
		mainPage.openFrame("资产负债表");
		mainPage.openFrame("常用凭证模版");
		mainPage.openFrame("汇率档案");
		mainPage.openFrame("会计科目");
		mainPage.openFrame("科目期初余额");
		mainPage.openFrame("期末处理");
		mainPage.openFrame("总账期末结账");
		mainPage.openFrame("发生额及余额表");
		mainPage.openFrame("科目汇总表");
		mainPage.openFrame("科目明细账");
		mainPage.openFrame("科目总账");
		mainPage.openFrame("数量金额明细账");
		mainPage.openFrame("数量金额总账");
		mainPage.openFrame("现金银行日记账");
		mainPage.openFrame("序时账");

	}

}
