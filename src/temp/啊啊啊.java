package temp;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.dzf.test.page.accounting.AccountingLoginPage;
import com.dzf.test.page.accounting.AccountingMainPage;
import com.dzf.test.page.accounting.资产报表.*;
import com.dzf.test.page.accounting.资产管理.卡片管理Page;
import com.dzf.test.util.DatePickerUtil;
import com.dzf.test.util.MyException;

public class 啊啊啊 {

	@BeforeTest
	public void setup() throws Exception {

		Assert.assertTrue(new AccountingLoginPage().login("ali001", "ali001!!", "桃子传媒"));

	}

	/*
	 * 设置汇率档案
	 */
	@Test
	public void testOpenFrame() throws Exception {
		AccountingMainPage mainPage = new AccountingMainPage();
//		卡片管理Page 卡片管理 = new 卡片管理Page();
//		mainPage.openFrame("卡片管理");
		// 卡片管理.add("2016-05-04", "160504001", "办公桌", "0103", "2016-010-01",
		// "平均年限法", "12", "160103_器具、工具、家具等",
		// "1001_库存现金", "160203_器具、工具、家具等", "56010701_固定资产折旧", "2000", false,
		// "无备注");
//		卡片管理.search("2010-01-01", "2020-12-31", null, null, "0103", null, null, null);
		mainPage.openFrame("折旧汇总表");
		new 折旧汇总表Page().search("2016-01", null, null, null, null);
		
		mainPage.openFrame("资产明细账");
		new 资产明细账Page().search("2016-01-01", "2018-01-01", null, null, null);
		
		mainPage.openFrame("资产与总账对账表");
		new 资产与总账对账表Page().search("2016-01-01");
		
		mainPage.openFrame("资产折旧明细");
		new 资产折旧明细Page().search("2016-01", "2016-12", null, null, null);
		
		mainPage.openFrame("资产总账");
		new 资产总账Page().search("2016-01-01", "2018-01-01", null);
		
		
		
	}
	
	public static void main(String[] args) {

//		System.setProperty("webdriver.firefox.bin", "D:/Program Files (x86)/Mozilla Firefox/firefox.exe");
//
//		WebDriver driver = new FirefoxDriver();
//		
//		driver.get("http://172.16.2.142:8090/dzfadmin/");
//		
//		System.out.println(driver.findElement(By.xpath("(//*[@id='login']/div/div[1]/div[1]/p)[2]")).getText());
		
//		System.out.println(new SimpleDateFormat("yyMMddHHmmss-SSS").format(Calendar.getInstance().getTime()));
		
		String date = "2015-02";
		System.out.println(date.substring(0, 4));
		System.out.println(date.substring(5));

	}

}
