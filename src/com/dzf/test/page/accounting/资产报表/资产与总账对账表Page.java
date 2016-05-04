package com.dzf.test.page.accounting.资产报表;

import org.testng.Reporter;

import com.dzf.test.model.Handler;
import com.dzf.test.model.Page;
import com.dzf.test.util.MyException;
import com.dzf.test.util.XMLUtil;

public class 资产与总账对账表Page extends Handler {
	private final String xmlfile = "./config/page/accounting/资产报表/" + this.getClass().getSimpleName() + ".xml";

	public 资产与总账对账表Page() throws Exception {
		super();
		page = XMLUtil.convert(xmlfile, Page.class);
	}
	
	public boolean search() throws MyException{
		try{
			switchToDefaultContent();
			switchToFrame("");
			
			return false;
		}catch(MyException e){
			Reporter.log("");
			throw e;
		}
		
	}
}
