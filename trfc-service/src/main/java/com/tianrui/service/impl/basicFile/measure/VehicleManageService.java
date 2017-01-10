package com.tianrui.service.impl.basicFile.measure;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianrui.api.intf.basicFile.measure.IBlacklistManageService;
import com.tianrui.api.intf.basicFile.measure.IVehicleManageService;
import com.tianrui.api.req.basicFile.measure.BlacklistManageReq;
import com.tianrui.api.req.basicFile.measure.VehicleManageQuery;
import com.tianrui.api.req.basicFile.measure.VehicleManageSave;
import com.tianrui.api.req.basicFile.measure.VehicleSaveReq;
import com.tianrui.api.resp.basicFile.measure.VehicleManageResp;
import com.tianrui.service.bean.basicFile.measure.VehicleManage;
import com.tianrui.service.mapper.basicFile.measure.VehicleManageMapper;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.utils.UUIDUtil;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;

/**
 * 车辆管理Service
 * @author zhanggaohao
 * @createtime 2016年12月20日 下午4:23:52
 * @classname VehicleManageService.java
 */
@Service
public class VehicleManageService implements IVehicleManageService {
	
	@Autowired
	private VehicleManageMapper vehicleManageMapper;
	
	@Autowired
	private IBlacklistManageService blacklistManageService;
	
	@Override
	public PaginationVO<VehicleManageResp> page(VehicleManageQuery query) throws Exception{
		PaginationVO<VehicleManageResp> page = null;
		if(query != null){
			page = new PaginationVO<VehicleManageResp>();
			long count = vehicleManageMapper.findVehiclePageCount(query);
			if(count > 0){
				query.setStart((query.getPageNo()-1)*query.getPageSize());
				query.setLimit(query.getPageSize());
				List<VehicleManage> list = this.vehicleManageMapper.findVehiclePage(query);
				page.setList(copyBeanList2RespList(list));
			}
			page.setTotal(count);
			page.setPageNo(query.getPageNo());
			page.setPageSize(query.getPageSize());
		}
		return page;
	}
	
	@Transactional
	@Override
	public Result addVehicle(VehicleManageSave save) throws Exception {
		Result result =Result.getParamErrorResult();
		if(save != null){
			VehicleManage vehicle = new VehicleManage();
			vehicle.setVehicleno(save.getVehicleno());
			List<VehicleManage> list = this.vehicleManageMapper.selectSelective(vehicle);
			if(list != null && list.size() > 0){
				result.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
			}else{
				PropertyUtils.copyProperties(vehicle, save);
				vehicle.setId(UUIDUtil.getId());
				vehicle.setIsblacklist("0");
//				vehicle.setCreator("");
				vehicle.setCreatetime(System.currentTimeMillis());
//				vehicle.setModifier("");
				vehicle.setModifytime(System.currentTimeMillis());
				if(this.vehicleManageMapper.insert(vehicle) > 0){
					result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
				}else{
					result.setErrorCode(ErrorCode.OPERATE_ERROR);
				}
			}
		}
		return result;
	}
	
	@Transactional
	@Override
	public Result editVehicle(VehicleManageSave save) throws Exception {
		Result result = Result.getParamErrorResult();
		if(save != null){
			VehicleManage vehicle = new VehicleManage();
			PropertyUtils.copyProperties(vehicle, save);
//			vehicle.setModifier("");
			vehicle.setModifytime(System.currentTimeMillis());
			if(this.vehicleManageMapper.updateByPrimaryKeySelective(vehicle) > 0){
				result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			}else{
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return result;
	}
	
	@Override
	public Result deleteVehicle(VehicleManageQuery query){
		Result result = Result.getParamErrorResult();
		if(query != null && StringUtils.isNotBlank(query.getId())){
			VehicleManage vehicle = new VehicleManage();
			vehicle.setId(query.getId());
			vehicle.setState("0");
			vehicle.setModifier("");
			vehicle.setModifytime(System.currentTimeMillis());
			if(this.vehicleManageMapper.updateByPrimaryKeySelective(vehicle) > 0){
				result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			}else{
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return result;
	}

	@Override
	public Result delblacklist(VehicleManageQuery query) throws Exception {
		Result result = Result.getParamErrorResult();
		if(query != null && StringUtils.isNotBlank(query.getId())){
			VehicleManage vehicle = new VehicleManage();
			vehicle.setId(query.getId());
			vehicle.setIsblacklist("0");
			vehicle.setModifier("");
			vehicle.setModifytime(System.currentTimeMillis());
			if(this.vehicleManageMapper.updateByPrimaryKeySelective(vehicle) > 0){
				if(this.blacklistManageService.deleteBlacklist(query.getId()) > 0){
					result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
				}else{
					result.setErrorCode(ErrorCode.OPERATE_ERROR);
				}
			}else{
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return result;
	}

	@Transactional
	@Override
	public Result addblacklist(VehicleManageQuery query) throws Exception {
		Result result = Result.getParamErrorResult();
		if(query != null && StringUtils.isNotBlank(query.getId())){
			VehicleManage vehicle = new VehicleManage();
			vehicle.setId(query.getId());
			vehicle.setIsblacklist("1");
			vehicle.setModifier("");
			vehicle.setModifytime(System.currentTimeMillis());
			if(this.vehicleManageMapper.updateByPrimaryKeySelective(vehicle) > 0){
				BlacklistManageReq breq = new BlacklistManageReq();
				breq.setId(UUIDUtil.getId());
				breq.setVehicleid(query.getId());
				breq.setVehicleno(query.getBlackVno());
				breq.setRemarks(query.getBlackRemarks());
				breq.setCreator(query.getBlackCreator());
				breq.setCreatetime(System.currentTimeMillis());
				breq.setModifier(query.getBlackCreator());
				breq.setModifytime(System.currentTimeMillis());
				if(this.blacklistManageService.addBlacklist(breq) > 0){
					result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
				}else{
					result.setErrorCode(ErrorCode.OPERATE_ERROR);
				}
			}else{
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return result;
	}
	
	@Override
	public Result findListByParmas(VehicleManageQuery query) throws Exception {
		Result result = Result.getSuccessResult();
		VehicleManage vehicle = new VehicleManage();
		if(query != null){
			PropertyUtils.copyProperties(vehicle, query);
		}
		List<VehicleManage> list = vehicleManageMapper.selectSelective(vehicle);
		result.setData(copyBeanList2RespList(list));
		return result;
	}
	
	private List<VehicleManageResp> copyBeanList2RespList(List<VehicleManage> list) throws Exception {
		List<VehicleManageResp> listResp = null;
		if(list != null && list.size() > 0){
			listResp = new ArrayList<VehicleManageResp>();
			for(VehicleManage vehicle : list){
				listResp.add(copyBean2Resp(vehicle));
			}
		}
		return listResp;
	}
	
	private VehicleManageResp copyBean2Resp(VehicleManage bean) throws Exception {
		VehicleManageResp resp = null;
		if(bean != null){
			resp = new VehicleManageResp();
			PropertyUtils.copyProperties(resp, bean);
		}
		return resp;
	}

	@Override
	public void addVehicleApi(VehicleSaveReq vehicleSaveReq) {
		// TODO Auto-generated method stub
		
	}

}