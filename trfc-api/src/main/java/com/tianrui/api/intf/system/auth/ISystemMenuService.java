package com.tianrui.api.intf.system.auth;

import com.tianrui.api.req.system.auth.SystemMenuQueryReq;
import com.tianrui.api.req.system.auth.SystemMenuSaveReq;
import com.tianrui.smartfactory.common.vo.Result;

/**
 * 菜单管理
 * @author lixp 2017年2月26日 14:20:37
 *
 */
public interface ISystemMenuService {

	/**
	 * 菜单分页查询
	 * @param req
	 * @return
	 * @throws Exception
	 */
	Result page(SystemMenuQueryReq req) throws Exception;
	
	
	/**
	 * 菜单详情查询
	 * @param req
	 * @return
	 * @throws Exception
	 */
	Result detail(SystemMenuQueryReq req) throws Exception;
	
	
	/**
	 * 添加菜单
	 * @param req
	 * @return
	 * @throws Exception
	 */
	Result addMenu(SystemMenuSaveReq  req) throws Exception;
	
	/**
	 * 编辑菜单
	 * @param req
	 * @return
	 * @throws Exception
	 */	
	Result editMenu(SystemMenuSaveReq  req) throws Exception;
	/**
	 * 删除菜单
	 * @param req
	 * @return
	 * @throws Exception
	 */
	Result delMenu(SystemMenuQueryReq  req) throws Exception;
	/**
	 * 获取下拉树数据
	 */
	Result getTreeData() throws Exception;
	
}
