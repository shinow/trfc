package com.tianrui.api.intf.system.merchants;

import com.tianrui.api.req.system.merchants.AppUserGroupReq;
import com.tianrui.api.req.system.merchants.SupplierGroupQuery;
import com.tianrui.api.req.system.merchants.SupplierGroupSave;
import com.tianrui.api.resp.system.merchants.SupplierGroupResp;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;

/**
 * @Description 供应商用户接口
 * @author zhanggaohao
 * @version 2017年5月9日 上午9:19:16
 */
public interface ISupplierGroupService {
	/**
	 * @Description 查询供应商用户分页接口 
	 * @author zhanggaohao
	 * @version 2017年5月9日 下午1:45:22
	 * @param query
	 * @return
	 */
	PaginationVO<SupplierGroupResp> page(SupplierGroupQuery query);
	/**
	 * @Description 新增组
	 * @author zhanggaohao
	 * @version 2017年5月10日 上午9:27:32
	 * @param save
	 * @return
	 */
	Result addSupplierGroup(SupplierGroupSave save);
	/**
	 * @Description 新增组成员
	 * @author zhanggaohao
	 * @version 2017年5月11日 上午10:24:00
	 * @param groupid
	 * @param childrenList
	 * @param userid
	 * @return
	 */
	Result addSupplierToGroup(String groupid, String childrenList, String userid);
	/**
	 * @Description 查询组成员
	 * @author zhanggaohao
	 * @version 2017年5月10日 下午1:38:25
	 * @param supplierid
	 * @return
	 */
	Result supplierGroupCutover(String supplierid);
	/**
	 * @Description 切换用户
	 * @author zhanggaohao
	 * @version 2017年5月10日 下午3:01:37
	 * @param req
	 * @return
	 * @throws Exception 
	 */
	Result userCutover(AppUserGroupReq req) throws Exception;
	/**
	 * @Description 查询供应商组成员
	 * @author zhanggaohao
	 * @version 2017年5月11日 上午10:55:25
	 * @param query
	 * @return
	 */
	Result supplierGroupDetail(SupplierGroupQuery query);

}
