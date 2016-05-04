package com.dzf.test.page.accounting.资产管理;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import com.dzf.test.model.Handler;
import com.dzf.test.model.Page;
import com.dzf.test.page.accounting.凭证管理.填制凭证Page;
import com.dzf.test.util.DatePickerUtil;
import com.dzf.test.util.MyException;
import com.dzf.test.util.WebTableUtil;
import com.dzf.test.util.XMLUtil;

public class 卡片管理Page extends Handler {

	private final String xmlfile = "./config/page/accounting/资产管理/" + this.getClass().getSimpleName() + ".xml";

	public 卡片管理Page() throws Exception {
		super();
		page = XMLUtil.convert(xmlfile, Page.class);
	}

	/*
	 * 查询
	 */
	public boolean search(/* 开始日期 */String beginDate, String endDate, String code, String name, String classesCode,
			String isbeginofperiod, String isclean, String convertedLedger) throws MyException {
		try {
			switchToDefaultContent();
			switchToFrame("卡片管理");

			click("查询按钮");

			click("查询面板-起始使用日期选择按钮");
			new DatePickerUtil(getWebElement("查询面板-起始使用日期时间选择器")).choseDate(beginDate);

			click("查询面板-结束使用日期选择按钮");
			new DatePickerUtil(getWebElement("查询面板-结束使用日期时间选择器")).choseDate(endDate);

			input("查询面板-卡片编码输入框", code);

			input("查询面板-资产名称输入框", name);

			if (classesCode != null && !classesCode.equals("")) {
				click("查询面板-资产类别选择按钮");
				new choseAssetClass().doChose(classesCode);
			}

			// 是否期初
			input("查询面板-是否期初输入框", isbeginofperiod);

			// 是否清理
			input("查询面板-是否清理输入框", isclean);

			// 是否已转总账
			input("查询面板-是否已转总账输入框", convertedLedger);

			// 点击查询按钮
			click("查询面板-确定按钮");

			return isDisplayed("查询面板-确定按钮");
		} catch (MyException e) {
			Reporter.log("查询失败！");
			throw e;
		}
	}

	/*
	 * 
	 * date=录入期间,code=资产编码,name=资产名称,classes=资产类别,useDate=开始使用日期,
	 * depreciationMethod=折旧方式,life=预计使用年限,assetSubject=资产科目,
	 * settlementSubject=结算科目，
	 * depreciationSubject=折旧(摊销)科目，depreciationCostSubject=折旧(摊销)费用科目,
	 * originalValue=原值
	 */
	public boolean add(/* 录入期间 */String date, /* 资产编码 */String code, String name, String classesCode, String useDate,
			String depreciationMethod, String life, String assetSubject, String settlementSubject,
			String depreciationSubject, String depreciationCostSubject, String originalValue, boolean 是否期初,
			String 期初信息备注) throws MyException, InterruptedException {
		try {

			argsNotNull(name, classesCode, depreciationMethod, life, assetSubject, settlementSubject,
					depreciationSubject, depreciationCostSubject, originalValue);

			switchToDefaultContent();
			switchToFrame("卡片管理");

			click("列表显示-增加按钮");
			// 输入录入期间：默认当天
			click("录入期间选择按钮");
			Thread.sleep(500);
			new DatePickerUtil(getWebElement("录入期间时间选择器")).choseDate(date);

			// String a =
			// driver.findElement(By.xpath("html/body/div[14]//div[@class='calendar-header']/div[@class='calendar-title']/span")).getText();
			// String b =
			// driver.findElement(By.xpath("html/body/div[14]")).findElement(By.xpath(".//div[@class='calendar-header']/div[@class='calendar-title']/span")).getText();
			// System.out.println(a+";"+b);
			// 输入资产编码
			if (code == null || code.equals("")) {
				code = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(Calendar.getInstance().getTime());
			}
			input("资产编码输入框", code);

			// 输入资产名称
			input("资产名称输入框", name);

			// 输入资产类别
			click("资产类别选择按钮");
			new choseAssetClass().doChose(classesCode);

			// 输入开始使用日期
			click("开始使用日期选择按钮");
			// Thread.sleep(500);
			new DatePickerUtil(getWebElement("开始使用日期时间选择器")).choseDate(useDate);

			// 输入折旧方式
			// 输入预计使用年限
			click("折旧方式下拉按钮");

			switch (depreciationMethod) {
			case "平均年限法":
				click("折旧方式-平均年限法");
				input("预计使用年限输入框", life);
				break;
			case "工作量法":
				click("折旧方式-工作量法");
				input("工作总量输入框", "");
				input("工作量单位输入框", "");
				break;
			case "双倍余额递减法":
				click("折旧方式-双倍余额递减法");
				input("预计使用年限输入框", life);
				break;
			}

			// 输入固定（无形）资产科目
			click("固定(无形)资产科目选择按钮");
			new choseSubject(getWebElement("选择科目面板")).doChose(assetSubject);
			// 输入结算科目
			click("结算科目选择按钮");
			new choseSubject(getWebElement("选择科目面板")).doChose(settlementSubject);
			// 输入折旧（摊销）科目
			click("折旧(摊销)科目选择按钮");
			new choseSubject(getWebElement("选择科目面板")).doChose(depreciationSubject);
			// 输入折旧(摊销)费用科目
			click("折旧(摊销)费用科目选择按钮");
			new choseSubject(getWebElement("选择科目面板")).doChose(depreciationCostSubject);
			// 输入原值
			input("原值输入框", originalValue);
			// 原值输入enter
			input("原值输入框", Keys.RETURN);
			// 输入是否期初
			click("是否期初选择按钮");

			click(是否期初?"是否期初选项-是":"是否期初选项-否");
			

			// 期初信息输入备注
			input("期初信息备注输入框", 期初信息备注);

			click("保存按钮");

			return isDisplayed("卡片显示-增加按钮");
		} catch (MyException e) {
			Reporter.log("添加失败！");
			throw e;
		}
	}

	private void argsNotNull(String name, String classesCode, String depreciationMethod, String life,
			String assetSubject, String settlementSubject, String depreciationSubject, String depreciationCostSubject,
			String originalValue) throws MyException {
		if (name == null) {
			throw new MyException("资产名称不能为空！");
		}
		if (classesCode == null) {
			throw new MyException("资产类别不能为空！");
		}
		if (depreciationMethod == null) {
			throw new MyException("折旧方式不能为空！");
		}
		if (life == null) {
			throw new MyException("使用年限不能为空！");
		}
		if (assetSubject == null) {
			throw new MyException("固定(无形)资产科目不能为空！");
		}
		if (settlementSubject == null) {
			throw new MyException("结算科目不能为空！");
		}
		if (depreciationSubject == null) {
			throw new MyException("折旧(摊销)科目不能为空！");
		}
		if (depreciationCostSubject == null) {
			throw new MyException("折旧(摊销)费用科目不能为空！");
		}
		if (originalValue == null) {
			throw new MyException("原值不能为空！");
		}

	}

	public boolean existAssetCode(String assetCode) throws MyException {
		try {
			switchToDefaultContent();
			switchToFrame("卡片管理");

			return getWebElements(By.xpath("//td[@field='zcbm']/div[text()='" + assetCode + "']")).size() > 0;

		} catch (MyException e) {
			Reporter.log("查询固定资产编码是否存在失败！");
			throw e;
		}

	}

	public boolean modify() {

		return false;
	}

	public boolean delete() {

		return false;
	}

	public boolean refresh() {

		return false;
	}

	public boolean copy() {

		return false;
	}

	public boolean trim() {

		return false;
	}

	public boolean assetClean() {

		return false;
	}

	public boolean cardShow() {

		return false;
	}

	public boolean convertLedgerOnCardShow() throws MyException, InterruptedException {
		try {
			switchToDefaultContent();
			switchToFrame("卡片管理");

			//获取使用日期
			String useDate = getWebElement("开始使用日期输入框").getAttribute("value");
			
			click("卡片显示-转总账按钮");
			
			Thread.sleep(3000);
			
			填制凭证Page 填制凭证 = new 填制凭证Page();
			
			填制凭证.转总账(useDate);
			
			switchToFrame("卡片管理");
			
			return getWebElement("状态信息-转总账输入框").getAttribute("value").equals("是");
			
		} catch (MyException e) {
			Reporter.log("转总账失败！");
			throw e;
		}

	}
	
	public boolean convertLedgerOnListShow(String cardCode) throws MyException {
		try {
			switchToDefaultContent();
			switchToFrame("卡片管理");

			return false;
		} catch (MyException e) {
			Reporter.log("资产编码：" + cardCode + "转总账失败！");
			throw e;
		}

	}

	/*
	 * 联查凭证
	 */
	public boolean openVoucher() {

		return false;
	}

	public boolean 联查折旧明细() {

		return false;
	}

	public boolean exportExcel() {

		return false;
	}

	public boolean importExcel() {

		return false;
	}

	public boolean selectAll() {

		return false;
	}

	class choseSubject {
		WebElement container;

		choseSubject(WebElement container) {
			this.container = container;
		}

		void doChose(String subject) {
			try {
				String tableList[] = { "资产", "负债", "共同", "权益", "成本", "损益" };
				WebElement target = null;
				for (String table : tableList) {
					// 点击标题分类
					click(container.findElement(By.xpath(
							"//div[@class='tabs-header']//ul/li//span[@class='tabs-title'][text()='" + table + "']")));
					Thread.sleep(800);
					// 获得该分类对应的table
					target = new WebTableUtil(container.findElement(
							By.xpath("//*[@id='km_tabs']/div[@class='tabs-panels']/div/div[contains(@data-options,'"
									+ table + "')]/div/div/div/div[2]/div[2]/table"))).getTr(subject);
					if (target != null) {
						break;
					}
				}

				// 点击目标行
				click(target);

				// 点击确定按钮
				click(container.findElement(By.xpath("//*[@id='km-buttons']/a[contains(.,'确认')]")));

			} catch (Exception e) {

			}
		}
	}

	class choseAssetClass {

		void doChose(String classesCode) throws MyException {
			input("资产类别面板-输入框", classesCode);
			input("资产类别面板-输入框", Keys.RETURN);
			click("资产类别面板-确定按钮");
		}
	}

	/*
	 * 获取卡片编号，仅在添加卡片完成时使用！
	 */
	public String getCardCode() throws MyException {
		try {
			switchToDefaultContent();
			switchToFrame("卡片管理");

			return getWebElement("卡片编号").getAttribute("value");
		} catch (MyException e) {
			Reporter.log("获取卡片编号失败！");
			throw e;
		}

	}
}
