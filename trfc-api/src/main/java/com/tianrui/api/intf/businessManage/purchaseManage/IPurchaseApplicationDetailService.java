package com.tianrui.api.intf.businessManage.purchaseManage;

import java.util.List;

import com.tianrui.api.resp.businessManage.purchaseManage.PurchaseApplicationDetailResp;

public interface IPurchaseApplicationDetailService {
	
	List<PurchaseApplicationDetailResp> selectByPurchaseId(String purchaseId) throws Exception;

	PurchaseApplicationDetailResp findOne(String id) throws Exception;

	List<PurchaseApplicationDetailResp> selectByIds(List<String> ids) throws Exception;

}
