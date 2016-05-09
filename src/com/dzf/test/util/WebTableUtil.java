package com.dzf.test.util;

import java.util.*;

import static org.testng.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebTableUtil {
	private WebElement webTable;

	public WebTableUtil(WebElement webElement) {

		this.webTable = webElement;
	}

	// 得到表格的行数
	public int getRowCount() throws MyException {
		try {
			List<WebElement> rowCounts = webTable.findElements(By.tagName("tr"));
			return rowCounts.size();
		} catch (WebDriverException e) {
			throw new MyException("获取行数失败！", e);
		}
	}// 增加一个得到指定行癿列数，传入一个指定行数为参数，从 0 开始。交流方式 ryannj @126. com

	// 得到表格的行
	public WebElement getRow(int rowIdx) throws MyException {
		try {
			List<WebElement> rowCounts = webTable.findElements(By.tagName("tr"));
			// 得到对应的行
			return rowCounts.get(rowIdx);
		} catch (WebDriverException e) {
			throw new MyException("获取第" + rowIdx + "行失败！", e);
		}

	}

	// 得到指定行的列数
	public int getColCount(int rowIdx) throws MyException {
		try {
			List<WebElement> rowCounts = webTable.findElements(By.tagName("tr"));
			// 取得当前的tr
			WebElement rowNum = rowCounts.get(rowIdx);
			// 计算当前的td数
			List<WebElement> colCounts = rowNum.findElements(By.tagName("td"));

			return colCounts.size();
		} catch (WebDriverException e) {
			throw new MyException("获取第行" + rowIdx + "的列数失败！", e);
		}
	}// 增加一个得到指定单元格内容的方法，传入指定的行数，和列数作为参数。

	// 得到指定单元格的内容
	public WebElement getCell(int rowIdx, int colIdx) throws MyException {
		try {

			WebElement currentRow = getRow(rowIdx);

			List<WebElement> td = currentRow.findElements(By.tagName("td"));
			// 取得对应的单元格

			return td.get(colIdx);
		} catch (WebDriverException e) {
			throw new MyException("获取单元格失败！行：" + rowIdx + "列：" + colIdx, e);
		}
	}

	/*
	 * 获取 包含keyword的tr行 返回的tr不再包含tr 如果需要的tr中包含table则不适用此方法
	 */
	public WebElement getTr(String keyword) throws MyException {
		try {
//			List<WebElement> webElementList = webTable.findElements(By.xpath("//tr[*='" + keyword + "']"));
//
//			// Reporter.log("获取" + elementName + "所在的行");
//			// 遍历
//			for (WebElement webElement : webElementList) {
//				if (webElement.findElements(By.tagName("tr")).size() == 0) {
//					// Reporter.log("获取成功！");
//					return webElement;
//				}
//			}

			return webTable.findElement(By.xpath("//tr[*='" + keyword + "']"));
		} catch (WebDriverException e) {
			throw new MyException("没有找到包含：" + keyword + "的行", e);
		}
	}

	public static void main(String[] args) throws MyException {
		System.setProperty("webdriver.firefox.bin", "D:/Program Files (x86)/Mozilla Firefox/firefox.exe");
		WebDriver driver = new FirefoxDriver();
		driver.get("http://www.w3school.com.cn/tags/tag_table.asp");
		// 得到table元系
		WebTableUtil webTable = new WebTableUtil(driver.findElements(By.tagName("table")).get(1));
		// 验证总行数
		System.out.println(webTable.getRowCount());
		// assertEquals(10, webTable.getRowCount());
		// 验证第2行的例数
		System.out.println(webTable.getColCount(1));
		// assertEquals(4, webTable.getColCount(1));
		// 验证第4行，第3列的单元格内容
		assertEquals("规定表格边框的宽度。", webTable.getCell(3, 2).getText());
		driver.close();
	}

}// 增加一个得到表格中行数癿方法
