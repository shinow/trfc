package com.tianrui.api.intf.common;

import com.tianrui.api.req.common.RFIDReq;
import com.tianrui.smartfactory.common.vo.Result;

public interface IRFIDService {

	Result save(RFIDReq req) throws Exception;

	
}