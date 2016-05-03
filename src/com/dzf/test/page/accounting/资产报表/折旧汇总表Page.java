package com.dzf.test.page.accounting.资产报表;

import com.dzf.test.model.Handler;
import com.dzf.test.model.Page;
import com.dzf.test.util.XMLUtil;

public class 折旧汇总表Page  extends Handler {

	public 折旧汇总表Page() throws Exception {
		super();
		page = XMLUtil.convert("./config/page/accounting/资产报表/" + this.getClass().getSimpleName() + ".xml",
				Page.class.getName());
	}
}
