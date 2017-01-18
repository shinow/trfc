package com.tianrui.api.intf.businessManage.salesManage;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.tianrui.api.req.businessManage.salesManage.SalesApplicationQuery;
import com.tianrui.api.req.businessManage.salesManage.SalesApplicationSave;
import com.tianrui.api.resp.businessManage.salesManage.SalesApplicationResp;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;

public interface ISalesApplicationService {

	PaginationVO<SalesApplicationResp> page(SalesApplicationQuery query) throws Exception;

	Result add(SalesApplicationSave save) throws Exception;

	Result update(SalesApplicationSave save) throws Exception;

	Result audit(String id);

	Result unaudit(String id);

	Result delete(String id);

	SalesApplicationResp findOne(SalesApplicationQuery query) throws Exception;
	
	//查询最大时间戳
	Result findMaxUtc(SalesApplicationQuery req) throws Exception;
	//同步修改订单数据
	Result updateDataWithDC(List<JSONObject> list) throws Exception;

}