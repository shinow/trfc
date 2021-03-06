package com.tianrui.web.api.subsystem.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianrui.api.intf.basicFile.measure.IVehicleManageService;
import com.tianrui.api.req.basicFile.measure.VehicleManageApi;
import com.tianrui.smartfactory.common.api.ApiParam;
import com.tianrui.smartfactory.common.api.ApiResult;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.Result;
import com.tianrui.web.action.basicFile.measure.VehicleManageAction;
import com.tianrui.web.smvc.ApiAuthValidation;
import com.tianrui.web.smvc.ApiParamRawType;
/**
 * 车辆相关
 * @author Administrator
 *
 */
@Controller
@RequestMapping("api/vehicle")
public class ApiVehicleAction {

	private Logger log = LoggerFactory.getLogger(VehicleManageAction.class);
	
	@Autowired
	private IVehicleManageService vehicleManageService;
	
	//车辆 rfid绑定
	@RequestMapping(value="vehicleCard",method=RequestMethod.POST)
	@ApiParamRawType(VehicleManageApi.class)
	@ApiAuthValidation(callType="2")
	@ResponseBody
	public ApiResult vehicleCard(ApiParam<VehicleManageApi> req){
		Result rs=Result.getErrorResult();
		VehicleManageApi vehicleManageApi = req.getBody();
		vehicleManageApi.setCurrUid(req.getHead().getUserId());
		try {
			rs=vehicleManageService.addVehicleApi(vehicleManageApi);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return ApiResult.valueOf(rs);
	}
	
	//车辆验证 
	@RequestMapping("vehicleCheck")
	@ApiParamRawType(VehicleManageApi.class)
	@ApiAuthValidation(callType="1,3,4")
	@ResponseBody
	public ApiResult vehicleCheck(ApiParam<VehicleManageApi> req){
		//车辆是否绑定rfid 且已经注册
		//是否有通知单
		Result rs=Result.getErrorResult();
		VehicleManageApi vehicleManageApi = req.getBody();
		vehicleManageApi.setCurrUid(req.getHead().getUserId());
		try {
			rs=vehicleManageService.vehicleCheck(vehicleManageApi);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return ApiResult.valueOf(rs);
	}
	
	//车辆验证 
	@RequestMapping("vehicleDetail")
	@ApiParamRawType(VehicleManageApi.class)
	@ApiAuthValidation(callType="1")
	@ResponseBody
	public ApiResult vehicleDetail(ApiParam<VehicleManageApi> req){
		Result rs=Result.getErrorResult();
		VehicleManageApi vehicleManageApi = req.getBody();
		vehicleManageApi.setCurrUid(req.getHead().getUserId());
		try {
			rs=vehicleManageService.selectByVehicleNo(vehicleManageApi);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return ApiResult.valueOf(rs);
	}
	
	/**
	 * @annotation 车辆与IC卡绑定接口
	 * @param apiParam
	 * @return
	 */
	@RequestMapping("vehicleBindICard")
	@ApiParamRawType(VehicleManageApi.class)
	@ApiAuthValidation(callType="2")
	@ResponseBody
	public ApiResult vehicleBindICard(ApiParam<VehicleManageApi> apiParam){
		Result rs=Result.getErrorResult();
		VehicleManageApi req = apiParam.getBody();
		req.setCurrUid(apiParam.getHead().getUserId());
		try {
			rs=vehicleManageService.vehicleBindICard(req);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return ApiResult.valueOf(rs);
	}
	
	/**
	 * @annotation 查询车辆类别和IC卡信息
	 * @param apiParam
	 * @return
	 */
	@RequestMapping("getByVehicle")
	@ApiParamRawType(VehicleManageApi.class)
	@ApiAuthValidation(callType="1")
	@ResponseBody
	public ApiResult getByVehicle(ApiParam<VehicleManageApi> apiParam){
		Result rs=Result.getErrorResult();
		VehicleManageApi req = apiParam.getBody();
		req.setCurrUid(apiParam.getHead().getUserId());
		try {
			rs=vehicleManageService.getByVehicle(req);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return ApiResult.valueOf(rs);
	}
}