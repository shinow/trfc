package com.tianrui.api.intf.basicFile.nc;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.tianrui.api.req.basicFile.nc.MaterielManageQuery;
import com.tianrui.api.req.basicFile.nc.MaterielManageSave;
import com.tianrui.api.req.businessManage.app.AppQueryReq;
import com.tianrui.api.req.businessManage.handset.HandSetRequestParam;
import com.tianrui.api.resp.basicFile.nc.MaterielManageResp;
import com.tianrui.api.resp.businessManage.app.AppMaterialResp;
import com.tianrui.api.resp.businessManage.handset.HandSetReturnResp;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;

/**
 * 物料管理Service接口
 * @author zhanggaohao
 * @createtime 2016年12月7日 上午9:30:26
 * @classname IMaterielManageService.java
 */
public interface IMaterielManageService {
	
	/**
	 * 查询分页
	 * @param req
	 * @return
	 * @throws Exception
	 */
	public PaginationVO<MaterielManageResp> page(MaterielManageQuery query) throws Exception;
	/**
	 * 更新物料管理
	 * @param req
	 * @return
	 * @throws Exception
	 */
	public Result updateMateriel(MaterielManageSave save) throws Exception;
	/**
	 * 根据条件查询物料
	 * @param query
	 * @return
	 * @throws Exception
	 */
	public Result findListByParmas(MaterielManageQuery query) throws Exception;
	/**
	 * 根据id查询物料
	 * @param query
	 * @return
	 * @throws Exception 
	 */
	public MaterielManageResp findOne(MaterielManageQuery query) throws Exception;
	
	Result findMaxUtc(MaterielManageQuery query) throws Exception;
	
	Result updateDataWithDC(List<JSONObject> list )throws Exception;
//	/**
//	 * 获取物料数据(selector)
//	 */
//	Result materialData() throws Exception;
	/**
	 * @Description 根据名称模糊查询
	 * @author zhanggaohao
	 * @version 2017年2月25日 上午10:28:01
	 * @param likeName
	 * @return
	 * @throws Exception
	 */
	List<MaterielManageResp> autoCompleteSearch(String likeName,String type) throws Exception;
	/**
	 * @Description 根据名称模糊查询(物料名字带规格)
	 * @author zhanggaohao
	 * @version 2017年2月25日 上午10:28:01
	 * @param likeName
	 * @return
	 * @throws Exception
	 */
	List<MaterielManageResp> autoCompleteSearch1(String likeName,String type) throws Exception;
	/**
	 * @Description app物料列表
	 * @author zhanggaohao
	 * @version 2017年4月25日 上午9:39:33
	 * @param req
	 * @return
	 */
	public PaginationVO<AppMaterialResp> materialList(AppQueryReq req);
	/**
	 * @Description 手持机物料接口
	 * @author zhanggaohao
	 * @version 2017年5月15日 下午2:34:16
	 * @param body
	 * @return
	 */
	public List<HandSetReturnResp> handSetQueryAll(HandSetRequestParam body);
}