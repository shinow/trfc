package com.tianrui.api.intf.businessManage.purchaseManage;

import com.tianrui.api.req.businessManage.purchaseManage.PurchaseApplicationReq;
import com.tianrui.api.resp.businessManage.purchaseManage.PurchaseApplicationResp;
import com.tianrui.smartfactory.common.vo.PaginationVO;

public interface IPurchaseApplicationService {

	PaginationVO<PurchaseApplicationResp> page(PurchaseApplicationReq req) throws Exception;
	
}