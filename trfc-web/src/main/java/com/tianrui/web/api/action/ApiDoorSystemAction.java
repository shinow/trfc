package com.tianrui.web.api.action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianrui.api.req.basicFile.measure.VehicleCheckApi;
import com.tianrui.api.req.businessManage.salesManage.ApiDoorQueueQuery;
import com.tianrui.api.req.businessManage.salesManage.ApiDoorSystemSave;
import com.tianrui.api.resp.businessManage.salesManage.ApiDoorQueueResp;
import com.tianrui.service.impl.access.AccessRecordService;
import com.tianrui.smartfactory.common.api.ApiParam;
import com.tianrui.smartfactory.common.api.ApiResult;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.Result;
import com.tianrui.web.smvc.ApiAuthValidation;
import com.tianrui.web.smvc.ApiParamRawType;


/**
 * 门禁相关
 * @author lixp 2017年1月7日 09:24:36
 *
 */
@Controller
@RequestMapping("api/doorSystem")
public class ApiDoorSystemAction {

	private Logger log = LoggerFactory.getLogger(ApiDoorSystemAction.class);
	
	@Autowired
	private AccessRecordService accessRecordService;
	/**
	 * 门禁记录
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/record",method=RequestMethod.POST)
	@ApiParamRawType(ApiDoorSystemSave.class)
	@ApiAuthValidation(callType="2")
	@ResponseBody
	public ApiResult record(ApiParam<ApiDoorSystemSave> req){
		ApiDoorSystemSave apiDoor = req.getBody();
		apiDoor.setCurrUid(req.getHead().getUserId());
		Result rs=Result.getErrorResult();
		try {
			rs = accessRecordService.add(apiDoor);
		} catch (Exception e) {
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
			log.error(e.getMessage(),e);
		}
		return ApiResult.valueOf(rs);
	}
	
	
	/**
	 * 出厂验证
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/leaveFactoryCheck",method=RequestMethod.POST)
	@ApiParamRawType(VehicleCheckApi.class)
	@ApiAuthValidation(callType="2")
	@ResponseBody
	public ApiResult leaveFactoryCheck(ApiParam<VehicleCheckApi> req){
		VehicleCheckApi checkApi = req.getBody();
		checkApi.setCurrUid(req.getHead().getUserId());
		Result rs=Result.getErrorResult();
		try {
			rs = accessRecordService.leaveFactoryCheckApi(checkApi);
		} catch (Exception e) {
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
			log.error(e.getMessage(),e);
		}
		return ApiResult.valueOf(rs);
	}
	
	/**
	 * 销售排队号
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/queueNumber",method=RequestMethod.POST)
	@ApiParamRawType(ApiDoorQueueQuery.class)
	@ApiAuthValidation(callType="2")
	@ResponseBody
	public ApiResult queueNumber(ApiParam<ApiDoorQueueQuery> req){
		Result rs=Result.getErrorResult();
		rs.setData(getData());
		return ApiResult.valueOf(rs);
	}
	
	private ApiDoorQueueResp getData(){ 
		ApiDoorQueueResp resp =new ApiDoorQueueResp();
		resp.setQueuenumber("1");
		resp.setSmallticket("0");
		return resp;
	}
	
}
