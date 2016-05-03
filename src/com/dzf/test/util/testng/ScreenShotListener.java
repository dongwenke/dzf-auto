package com.dzf.test.util.testng;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

import com.dzf.test.model.WebDriverModel;
import com.dzf.test.util.ScreenShotUtil;

/**
 * 
 * @author 董文科
 *
 */
public class ScreenShotListener extends TestListenerAdapter {

	@Override
	public void onTestFailure(ITestResult tr) {
		super.onTestFailure(tr);
		takeScreenShot(tr);
	}

	@Override
	public void onConfigurationFailure(ITestResult tr) {
		super.onConfigurationFailure(tr);
		takeScreenShot(tr);
	}
	
	@Override
	public void onTestSuccess(ITestResult tr){
		super.onConfigurationFailure(tr);
		takeScreenShot(tr);
	}

	private void takeScreenShot(ITestResult tr) {
		WebDriver currentDirver = WebDriverModel.getDriver();

		File dir = new File("test-output/snapshot");
		if (!dir.exists()) {
			dir.mkdirs();
		}

		String filename = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + "_" + tr.getName() + ".png";
		String filePath = dir.getAbsolutePath() + "\\" + filename;

		new ScreenShotUtil(currentDirver).takeScreenshot(filePath);

		Reporter.setCurrentTestResult(tr);
//		Reporter.log(tr.getName());

		System.setProperty("org.uncommons.reportng.escape-output", "false");
		// 这里实现把图片链接直接输出到结果文件中，通过邮件发送结果则可以直接显示图片
		Reporter.log("<a href='file:\\" + filePath + "'><img src='file:\\" + filePath + "' width='250' height='150'/></a>");

	}

	public static void main(String[] args) {

		String fileName2 = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss").format(new Date()) + "_";
		System.out.println(fileName2);
	}
}