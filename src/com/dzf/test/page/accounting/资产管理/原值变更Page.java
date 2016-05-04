package com.dzf.test.page.accounting.资产管理;

import com.dzf.test.model.Handler;
import com.dzf.test.model.Page;
import com.dzf.test.util.XMLUtil;

public class 原值变更Page  extends Handler {

	private final String xmlfile = "./config/page/accounting/资产管理/" + this.getClass().getSimpleName() + ".xml";

	public 原值变更Page() throws Exception {
		super();
		page = XMLUtil.convert(xmlfile, Page.class);
	}

}
