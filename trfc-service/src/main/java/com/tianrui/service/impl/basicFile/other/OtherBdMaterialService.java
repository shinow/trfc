package com.tianrui.service.impl.basicFile.other;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianrui.api.intf.basicFile.other.IOtherBdMaterialService;
import com.tianrui.api.req.basicFile.other.OtherBdMaterialReq;
import com.tianrui.api.resp.basicFile.other.OtherBdMaterialResp;
import com.tianrui.service.bean.basicFile.other.OtherBdMaterial;
import com.tianrui.service.mapper.basicFile.other.OtherBdMaterialMapper;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.utils.UUIDUtil;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;

/**
 * 其他物料的Service接口的实现类
 * @author 王沙沙
 *
 */
@Service
public class OtherBdMaterialService implements IOtherBdMaterialService{
	@Autowired
	private OtherBdMaterialMapper otherBdMaterialMapper;
	
	
	@Override
	public Result page(OtherBdMaterialReq req) throws Exception {
		Result result=Result.getSuccessResult();
		// 实现分页方法
		PaginationVO<OtherBdMaterialResp> page=null;
		if(req!=null){
			page=new PaginationVO<OtherBdMaterialResp>();
			req.setStart((req.getPageNo()-1)*req.getPageSize());
			req.setLimit(req.getPageSize());			
			long count=otherBdMaterialMapper.findMaterialPageCount(req);					
            if(count>0){
            	List<OtherBdMaterial> list=otherBdMaterialMapper.findMaterialPage(req);
            	page.setList(copyBeanList2RespList(list));
            	page.setTotal(count);
				page.setPageNo(req.getPageNo());
				page.setPageSize(req.getPageSize());
				result.setData(page);
            }else{
            	page.setTotal(count);
                page.setPageNo(req.getPageNo());
                page.setPageSize(req.getPageSize());
                result.setData(page);
            }
		}
		return result;
	}
	
	private List<OtherBdMaterialResp> copyBeanList2RespList(List<OtherBdMaterial> list)throws Exception{
		List<OtherBdMaterialResp> listResp=null;
		if(list != null && list.size()>0){
			listResp=new ArrayList<OtherBdMaterialResp>();
			for(OtherBdMaterial mater : list){
				listResp.add(copyBean2Resp(mater));
			}
		}		
		return listResp;	
	}
	
	private OtherBdMaterialResp copyBean2Resp(OtherBdMaterial bean) throws Exception{
		OtherBdMaterialResp resp=null;
		if(bean !=null){
			resp=new OtherBdMaterialResp();
			PropertyUtils.copyProperties(resp, bean);
		}		
		return resp;
		
	} 
	@Override
	public String getMaterialCode() {
		return "CD"+(int)(Math.random()*10000);
		//return null;
	}
	@Override
	public String getMaterialInnercode() {
		return "ICD"+(int)(Math.random()*10000);
		//return null;
	}
	public String getMaterialId(){
		return UUIDUtil.getId();
	}
	
	@Transactional
	@Override
	public Result addOtherBdMaterial(OtherBdMaterialReq req) throws Exception {
		Result result=Result.getSuccessResult();
		//增加
		int n=0;
		if(req!=null){
			OtherBdMaterial mater=new OtherBdMaterial();
			PropertyUtils.copyProperties(mater, req);
			mater.setCreator("Qmater");
			mater.setId(UUIDUtil.getId());
			mater.setCreatetime(System.currentTimeMillis());
			mater.setModifytime(System.currentTimeMillis());
			n=otherBdMaterialMapper.insertSelective(mater);
			if(n > 0){
				result.setData(n);
			}else if(n == -1){
				result.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
			}else{
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return result;
	}
	
	
	@Override
	public Result updateOtherBdMaterial(OtherBdMaterialReq req) throws Exception{
		Result result=Result.getSuccessResult();
		//更新
		int n=0;
		if(req!=null){
			OtherBdMaterial mater=new OtherBdMaterial();
			PropertyUtils.copyProperties(mater, req);
			mater.setId(UUIDUtil.getId());
			mater.setModifytime(System.currentTimeMillis());
			n=this.otherBdMaterialMapper.updateByPrimaryKeySelective(req);
			if(n > 0){
				result.setData(n);
			}else{
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return result;
		
	}
	@Override
	public OtherBdMaterialReq findByPrimaryKey(String id) throws Exception {
		OtherBdMaterialReq req = new OtherBdMaterialReq();
		if(id==null||id==""){
			throw new RuntimeException("查询数据时,参数不能为空");
		}
		OtherBdMaterial mater=otherBdMaterialMapper.selectByPrimaryKey(id);
		if(mater==null){
			throw new RuntimeException("查询数据的ID无效");
		}
		PropertyUtils.copyProperties(req, mater);

		return req;
	}
		
	@Transactional
	@Override
	public Result deleteByPrimaryKey(String id) throws Exception {
		// 删除
		Result result=Result.getSuccessResult();
		if(id!=null && !id.trim().isEmpty()){
			int n=this.otherBdMaterialMapper.deleteByPrimaryKey(id);
			if(n > 0){
				result.setData(n);
			}else{
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return result;
	}
	
	
}
