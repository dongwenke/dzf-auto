package temp;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriverException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.dzf.test.page.accounting.AccountingMainPage;
import com.dzf.test.page.accounting.凭证管理.凭证管理Page;
import com.dzf.test.page.accounting.凭证管理.填制凭证Page;
import com.dzf.test.page.accounting.基础设置_总账.科目期初余额Page;
import com.dzf.test.page.accounting.科目账表.发生额及余额表Page;
import com.dzf.test.page.accounting.科目账表.序时账Page;
import com.dzf.test.page.accounting.科目账表.数量金额总账Page;
import com.dzf.test.page.accounting.科目账表.数量金额明细账Page;
import com.dzf.test.page.accounting.科目账表.现金银行日记账Page;
import com.dzf.test.page.accounting.科目账表.科目总账Page;
import com.dzf.test.page.accounting.科目账表.科目明细账Page;
import com.dzf.test.page.accounting.科目账表.科目汇总表Page;
import com.dzf.test.page.accounting.结账办理.期末处理Page;
import com.dzf.test.page.accounting.财务账表.业务活动表Page;
import com.dzf.test.page.accounting.财务账表.利润表Page;
import com.dzf.test.page.accounting.财务账表.利润表季报page;
import com.dzf.test.page.accounting.财务账表.增值税和营业税月度申报对比表Page;
import com.dzf.test.page.accounting.财务账表.收入支出表Page;
import com.dzf.test.page.accounting.财务账表.现金流量表Page;
import com.dzf.test.page.accounting.财务账表.资产负债表Page;
import com.dzf.test.testcase.accounting.LoginPageTest;
import com.dzf.test.util.ILogUtil;
import com.dzf.test.util.MyException;

public class Test1 implements ILogUtil {
	private AccountingMainPage mainPage;

	private 利润表季报page 利润表季报;
	private 利润表Page 利润表;
	private 收入支出表Page 收入支出表;
	private 现金流量表Page 现金流量表;
	private 业务活动表Page 业务活动表;
	private 增值税和营业税月度申报对比表Page 增值税和营业税月度申报对比表;
	private 资产负债表Page 资产负债表;

	private 发生额及余额表Page 发生额及余额表;
	private 科目汇总表Page 科目汇总表;
	private 科目明细账Page 科目明细帐;
	private 科目总账Page 科目总账;
	private 数量金额明细账Page 数量金额明细帐;
	private 数量金额总账Page 数量金额总账;
	private 现金银行日记账Page 现金银行日记账;
	private 序时账Page 序时账;

	private 科目期初余额Page 科目期初余额;
	private 填制凭证Page 填制凭证;
	private 凭证管理Page 凭证管理;
	private 期末处理Page 期末处理;

	@BeforeClass
	public void init() throws Exception {
		mainPage = new AccountingMainPage();

		利润表季报 = new 利润表季报page();
		利润表 = new 利润表Page();
		收入支出表 = new 收入支出表Page();
		现金流量表 = new 现金流量表Page();
		业务活动表 = new 业务活动表Page();
		增值税和营业税月度申报对比表 = new 增值税和营业税月度申报对比表Page();
		资产负债表 = new 资产负债表Page();

		科目总账 = new 科目总账Page();
		科目明细帐 = new 科目明细账Page();
		现金银行日记账 = new 现金银行日记账Page();
		发生额及余额表 = new 发生额及余额表Page();
		序时账 = new 序时账Page();
		科目汇总表 = new 科目汇总表Page();
		数量金额明细帐 = new 数量金额明细账Page();
		数量金额总账 = new 数量金额总账Page();

		科目期初余额 = new 科目期初余额Page();
		填制凭证 = new 填制凭证Page();
		凭证管理 = new 凭证管理Page();
		期末处理 = new 期末处理Page();

	}

	@BeforeTest
	public void setup() throws Exception {
		logger.info("登录成功：" + new LoginPageTest().testLogin("ali002", "ali002!!", ""));
	}

	@Test
	public void test期初试算平衡() throws InterruptedException, MyException {
		mainPage.open科目期初余额();
		科目期初余额.modify("1001_库存现金", "10000", null, null);
		科目期初余额.modify("2001_短期借款", "10000", null, null);

		科目期初余额.fixedAssetsSync();

		科目期初余额.refresh();

		科目期初余额.trialBalancing();

		科目期初余额.getBalanceInfo();

		科目期初余额.print();

	}

	@Test
	public void test填制凭证() throws InterruptedException, MyException {
		mainPage.open填制凭证();
		填制凭证.saveVoucher("测试摘要", "库存现金", null, null, "100", "银行存款");

		mainPage.open填制凭证();
		填制凭证.billImage();

		填制凭证.copyByMonthOnFill();

		mainPage.open填制凭证();
		填制凭证.commonTemplet("", "100");

		填制凭证.copy();

		填制凭证.audit();

		填制凭证.copyByMonthOnSaved();

		String now = new SimpleDateFormat("yyMMdd-hhms").format(new Date());
		填制凭证.saveAsCommonTemplet(now, "自动模版" + now);

		填制凭证.print();

		// 填制凭证.delete();

	}

	@Test
	public void test凭证管理() throws InterruptedException, MyException {
		mainPage.open凭证管理();

		凭证管理.refresh();

		凭证管理.searchVoucher("期间", "2016-04", "2016-04", null, null, null, null, null, null, null, null);

		凭证管理.selectAll();

		凭证管理.auditVoucher();

		凭证管理.accountVoucher();

		凭证管理.unAccountVoucher();

		凭证管理.unAuditVoucher();

		凭证管理.print();

		凭证管理.deSelectAll();

	}

	@Test
	public void test期末处理() throws InterruptedException, MyException {
		mainPage.open期末处理();
		期末处理.search(null, "2016-04", "2016-12", false, false);

		期末处理.selectAll();
		期末处理.成本结转();
		期末处理.反成本结转();
		期末处理.计提折旧();
		期末处理.反计提折旧();
		期末处理.期间损益结转();
		期末处理.反期间损益结转();

	}

	@Test
	public void test科目总账() throws InterruptedException, MyException {
		mainPage.open科目总账();
		科目总账.search(null, null, null, null, null, null, null, null, false, false, null);

	}

	@Test
	public void test科目明细帐() throws InterruptedException, MyException {
		mainPage.open科目明细账();
		科目明细帐.search(null, null, null, null, null, null, null, null, true, true, null);

	}

	@Test
	public void test现金银行日记账() throws InterruptedException, MyException {
		mainPage.open现金银行日记账();
		现金银行日记账.search(null, null, null, null, null, null, null, null, true, true, null);

	}

	@Test
	public void test发生额及余额表() throws InterruptedException, MyException {
		mainPage.open发生额及余额表();
		发生额及余额表.search(null, null, null, null, null, null, null, null, true, false, null);

	}

	@Test
	public void test序时账() throws InterruptedException, MyException {
		mainPage.open序时账();
		序时账.search(null, null, null, null, null, null, null, null, false, false, null);

	}

	@Test
	public void test科目汇总表() throws InterruptedException, MyException {
		mainPage.open科目汇总表();
		科目汇总表.search(null, null, null, null, null, null, null, null, false, false, null);

	}

	@Test
	public void test数量金额明细帐() throws InterruptedException, MyException {
		mainPage.open数量金额明细账();
		数量金额明细帐.search(null, null, null, null, null, null, null, null, false, false, null);

	}

	@Test
	public void test数量金额总账() throws InterruptedException, MyException {
		mainPage.open数量金额总账();
		数量金额总账.search(null, null, null, null, null, null, null, null, false, false, null);

	}

	@Test
	public void test利润表季报() throws InterruptedException, MyException {
		mainPage.open利润表季报();
		利润表季报.search(null, null, null, true);

	}

	@Test
	public void test利润表() throws InterruptedException, MyException {
		mainPage.open利润表();
		利润表.search(null, null, false);

	}

	@Test
	public void test收入支出表() throws InterruptedException, MyException {
		mainPage.open收入支出表();
		收入支出表.search(null, null, false);

	}

	@Test
	public void test现金流量表() throws InterruptedException, MyException {
		mainPage.open现金流量表();
		现金流量表.search(null, null);

	}

	@Test
	public void test业务活动表() throws InterruptedException, MyException {
		mainPage.open业务活动表();
		业务活动表.search(null, null, false);

	}

	@Test
	public void test增值税和营业税月度申报对比表() throws InterruptedException, MyException {
		mainPage.open增值税和营业税月度申报对比表();
		增值税和营业税月度申报对比表.search(null, null);

	}

	@Test
	public void test资产负债表() throws InterruptedException, MyException {

		mainPage.open资产负债表();
		资产负债表.search(null, null, false, false);
	}

}
