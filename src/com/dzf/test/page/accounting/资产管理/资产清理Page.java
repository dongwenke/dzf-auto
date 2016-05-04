package com.dzf.test.page.accounting.资产管理;

import com.dzf.test.model.Handler;
import com.dzf.test.model.Page;
import com.dzf.test.util.XMLUtil;

public class 资产清理Page extends Handler {

	private final String xmlfile = "./config/page/accounting/资产管理/" + this.getClass().getSimpleName() + ".xml";

	public 资产清理Page() throws Exception {
		super();
		page = XMLUtil.convert(xmlfile, Page.class);
	}

}
