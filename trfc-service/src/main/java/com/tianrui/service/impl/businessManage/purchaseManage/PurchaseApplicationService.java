package com.tianrui.service.impl.businessManage.purchaseManage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tianrui.api.intf.basicFile.nc.ISupplierManageService;
import com.tianrui.api.intf.businessManage.purchaseManage.IPurchaseApplicationDetailService;
import com.tianrui.api.intf.businessManage.purchaseManage.IPurchaseApplicationService;
import com.tianrui.api.req.businessManage.app.AppOrderReq;
import com.tianrui.api.req.businessManage.purchaseManage.PurchaseApplicationQuery;
import com.tianrui.api.resp.businessManage.app.AppOrderDetailResp;
import com.tianrui.api.resp.businessManage.app.AppOrderResp;
import com.tianrui.api.resp.businessManage.purchaseManage.PurchaseApplicationJoinDetailResp;
import com.tianrui.api.resp.businessManage.purchaseManage.PurchaseApplicationResp;
import com.tianrui.service.bean.basicFile.nc.MaterielManage;
import com.tianrui.service.bean.businessManage.purchaseManage.PurchaseApplication;
import com.tianrui.service.bean.businessManage.purchaseManage.PurchaseApplicationDetail;
import com.tianrui.service.bean.common.BillType;
import com.tianrui.service.mapper.basicFile.nc.MaterielManageMapper;
import com.tianrui.service.mapper.businessManage.purchaseManage.PurchaseApplicationDetailMapper;
import com.tianrui.service.mapper.businessManage.purchaseManage.PurchaseApplicationMapper;

import com.tianrui.service.mapper.common.BillTypeMapper;
import com.tianrui.smartfactory.common.constants.Constant;

import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.utils.DateUtil;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;

@Service
public class PurchaseApplicationService implements IPurchaseApplicationService {
	
	@Autowired
	private PurchaseApplicationMapper purchaseApplicationMapper;
	
	@Autowired
	private IPurchaseApplicationDetailService purchaseApplicationDetailService;
	
	@Autowired
	private ISupplierManageService supplierManageService;
	
	@Autowired
	private PurchaseApplicationDetailMapper purchaseApplicationDetailMapper;
	
	@Autowired
	private BillTypeMapper billTypeMapper;
	
	@Autowired
	private MaterielManageMapper materielManageMapper;
	
	@Override
	public PaginationVO<PurchaseApplicationResp> page(PurchaseApplicationQuery query) throws Exception{
		PaginationVO<PurchaseApplicationResp> page = null;
		if(query != null){
			page = new PaginationVO<PurchaseApplicationResp>();
			query.setState("1");
			long count = purchaseApplicationMapper.findPurchaseApplicationPageCount(query);
			if(count > 0){
				query.setStart((query.getPageNo()-1)*query.getPageSize());
				query.setLimit(query.getPageSize());
				List<PurchaseApplication> list = purchaseApplicationMapper.findPurchaseApplicationPage(query);
				page.setList(copyBeanList2RespList(list, true));
			}
			page.setPageNo(query.getPageNo());
			page.setPageSize(query.getPageSize());
			page.setTotal(count);
		}
		return page;
	}
	
	@Override
	public PaginationVO<PurchaseApplicationJoinDetailResp> pageGroupMateriel(PurchaseApplicationQuery query) throws Exception{
		PaginationVO<PurchaseApplicationJoinDetailResp> page = null;
		if(query != null){
			page = new PaginationVO<PurchaseApplicationJoinDetailResp>();
			query.setState("1");
			long count = purchaseApplicationMapper.findPageGroupMaterielCount(query);
			if(count > 0){
				query.setStart((query.getPageNo()-1)*query.getPageSize());
				query.setLimit(query.getPageSize());
				List<PurchaseApplicationJoinDetailResp> list = purchaseApplicationMapper.findPageGroupMateriel(query);
				if(CollectionUtils.isNotEmpty(list)){
					for(PurchaseApplicationJoinDetailResp resp : list){
						resp.setSupplier(supplierManageService.findOne(resp.getSupplierid()));
						MaterielManage m = materielManageMapper.selectByPrimaryKey(resp.getMaterielid());
						if(m!=null){
							resp.setPackagetype(m.getPackagetype());
						}
					}
					page.setList(list);
				}
			}
			page.setPageNo(query.getPageNo());
			page.setPageSize(query.getPageSize());
			page.setTotal(count);
		}
		return page;
	}
	
	@Override
	public PurchaseApplicationResp findOne(String id) throws Exception {
		PurchaseApplicationResp resp = null;
		if(StringUtils.isNotBlank(id)){
			resp = copyBean2Resp(purchaseApplicationMapper.selectByPrimaryKey(id), true);
		}
		return resp;
	}
	
	@Override
	public List<PurchaseApplicationResp> selectByIds(List<String> ids, boolean setDetail) throws Exception{
		List<PurchaseApplicationResp> listResp = null;
		if(CollectionUtils.isNotEmpty(ids)){
			listResp = copyBeanList2RespList(purchaseApplicationMapper.selectByIds(ids), setDetail);
		}
		return listResp;
	}
	
	private List<PurchaseApplicationResp> copyBeanList2RespList(List<PurchaseApplication> list, boolean setDetail) throws Exception {
		List<PurchaseApplicationResp> listResp = null;
		if(list != null && list.size() > 0){
			listResp = new ArrayList<PurchaseApplicationResp>();
			for(PurchaseApplication purchase : list){
				listResp.add(copyBean2Resp(purchase, setDetail));
			}
		}
		return listResp;
	}
	
	private PurchaseApplicationResp copyBean2Resp(PurchaseApplication bean, boolean setDetail) throws Exception {
		PurchaseApplicationResp resp = null;
		if(bean != null){
			resp = new PurchaseApplicationResp();
			PropertyUtils.copyProperties(resp, bean);
			if(setDetail){
				resp.setListdetail(purchaseApplicationDetailService.selectByPurchaseId(bean.getId()));
			}
		}
		return resp;
	}

	@Override
	public Result findMaxUtc(PurchaseApplicationQuery req) throws Exception{
		Result result=Result.getErrorResult();
		if(req!=null){
			Long utc=purchaseApplicationMapper.findMaxUtc();
			result.setData(utc);
			result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
		}
		return result;
	}

	@Override
	@Transactional
	public Result updateDataWithDC(List<JSONObject> list) throws Exception{
		Result rs = Result.getErrorResult();
		if(CollectionUtils.isNotEmpty(list) ){
			Set<String> ids =getAllIds();
			List<PurchaseApplication> toUpdate =new ArrayList<PurchaseApplication>();
			List<PurchaseApplication> toSave =new ArrayList<PurchaseApplication>();
			List<PurchaseApplicationDetail> toSaveItem =new ArrayList<PurchaseApplicationDetail>();
			for( JSONObject jsonObject:list ){
				String id =jsonObject.getString("id");
				if( ids.contains(id) ){
					toUpdate.add(converJson2Bean(jsonObject));
				}else{
					toSave.add(converJson2Bean(jsonObject));
					toSaveItem.addAll(converJson2ItemList(jsonObject,id));
				}
			}
			if( CollectionUtils.isNotEmpty(toSave) ){
				purchaseApplicationMapper.insertBatch(toSave);
				purchaseApplicationDetailMapper.insertBatch(toSaveItem);
			}
			
			if( CollectionUtils.isNotEmpty(toUpdate) ){
				for( PurchaseApplication item :toUpdate){
					purchaseApplicationMapper.updateByPrimaryKeySelective(item);
				}
			}
			rs.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
		}
		return rs;
	}
	private Set<String> getAllIds(){
		Set<String> rs = new HashSet<String>();
		List<PurchaseApplication> list=purchaseApplicationMapper.selectSelective(null);
		for(PurchaseApplication item:list){
			rs.add(item.getId());
		}
		return rs;
	}
	private PurchaseApplication converJson2Bean(JSONObject jsonItem){
		PurchaseApplication item  =new PurchaseApplication();
		item.setId(jsonItem.getString("id"));
		//订单编号
		item.setCode(jsonItem.getString("code"));
		//审核状态 
		item.setAuditstatus("1");
		//状态   1正常  0作废  -1 到货关闭 -2 整单关闭 -3nc删除
		String status ="0";
		String closestatue=getItemStatus(jsonItem);
		//删除
		if( StringUtils.isNotBlank(jsonItem.getString("status")) && StringUtils.equals(jsonItem.getString("status"), "6")   ){
			status="-3";
		//正常	
		}else if( StringUtils.isNotBlank(closestatue) && StringUtils.equals(closestatue, "0")){
			status ="1";
		//到货关闭	
		}else if( StringUtils.isNotBlank(closestatue) && StringUtils.equals(closestatue, "1")){
			status ="-1";
		// 整单关闭 	
		}else if( StringUtils.isNotBlank(closestatue) && StringUtils.equals(closestatue, "2")){
			status ="-2";
		}
		
		//状态   1正常  0作废  -1 到货关闭 -2 整单关闭 -3nc删除
		item.setState(status);
		//来源
		item.setSource(jsonItem.getString("sourceType"));
		String billtypeid=jsonItem.getString("ctrantypeid");
		if(StringUtils.isNotBlank(billtypeid)){
			//订单类型id
			item.setBilltypeid(billtypeid);
			BillType type=billTypeMapper.selectByPrimaryKey(billtypeid);
			if(type!=null && StringUtils.isNotBlank(type.getName())){
				//订单类型名称
				item.setBilltypename(type.getName());
			}
		}
		//订单日期
		item.setBilltime(DateUtil.parse(jsonItem.getString("orderData"), "yyyy-MM-dd HH:mm:ss"));
		//总数量
		item.setSumnum(Double.valueOf(jsonItem.getString("ntotalastnum")));
		//组织id
		item.setOrgid(Constant.ORG_ID);
		//组织名称
		item.setOrgname(Constant.ORG_NAME);
		//供应商id
		item.setSupplierid(jsonItem.getString("supplierId"));
		//供应商名称
		item.setSuppliername(jsonItem.getString("suppliername"));
		//供应商备注
		item.setSupplierremark(jsonItem.getString("supplierremark"));
		//部门id
		item.setDepartmentid(jsonItem.getString("deptName"));
		//部门名称
		item.setDepartmentname(jsonItem.getString("departmentname"));
		//采购员id
		item.setBuyerid(jsonItem.getString("buyer"));
		//采购员姓名
		item.setBuyername(jsonItem.getString("buyername"));
		//审核人id
		item.setAuditid(jsonItem.getString("auditPerson"));
		//审核人姓名
		item.setAuditname(jsonItem.getString("auditname"));
		//审核日期
		item.setAudittime(DateUtil.parse(jsonItem.getString("auditTime"), "yyyy-MM-dd HH:mm:ss"));
		//制单人id
		item.setMakerid(jsonItem.getString("systemShanYuan"));
		//制单人名称
		item.setMakebillname(jsonItem.getString("makebillname"));
		//制单时间
		item.setMakebilltime(DateUtil.parse(jsonItem.getString("ssyData"), "yyyy-MM-dd HH:mm:ss"));
		//制单人	
		item.setCreator(jsonItem.getString("makebillname"));
		//制单时间
		item.setCreatetime(Long.valueOf(jsonItem.getString("createTime")));
		//备注
		item.setSupplierremark(jsonItem.getString("remark"));
		//NC同步时间戳
		item.setUtc(Long.valueOf(jsonItem.getString("ts")));
		//矿口id
		item.setMinemouthid(jsonItem.getString("kuangk"));
		//矿口名称
		item.setMinemouthname(jsonItem.getString("kuangkName"));
		return item;
	}
	
	//获取子表信息
	private String getItemStatus(JSONObject jsonItem){
		String rs ="0";
		if(jsonItem.getJSONArray("list")!=null){
			JSONArray arr =jsonItem.getJSONArray("list");
			if(arr !=null && arr.size()>0){
				JSONObject itemJon=JSONObject.parseObject(arr.get(0).toString());
				rs=itemJon.getString("closestatus");
			}
		}	
		return rs;
	}
	private List<PurchaseApplicationDetail> converJson2ItemList(JSONObject jsonItem,String id){
		List<PurchaseApplicationDetail> itemList = new ArrayList<PurchaseApplicationDetail>();
		if(jsonItem.getJSONArray("list")!=null){
			JSONArray arr =jsonItem.getJSONArray("list");
			if( arr.size()>0){
				for(int i=0;i<arr.size();i++){
					JSONObject itemJon=JSONObject.parseObject(arr.get(i).toString());
					PurchaseApplicationDetail purchaseItem = new PurchaseApplicationDetail();
					purchaseItem.setId(itemJon.getString("id"));
					purchaseItem.setPurchaseid(id);
					purchaseItem.setOrgid(Constant.ORG_ID);
					purchaseItem.setOrgname(Constant.ORG_NAME);
					purchaseItem.setMaterielid(itemJon.getString("materialId"));
					purchaseItem.setMaterielname(itemJon.getString("materialname"));
					purchaseItem.setMaterielspec(itemJon.getString("materialspec"));
					purchaseItem.setMaterieltype(itemJon.getString("materialtype"));
					if(StringUtils.isNotBlank(itemJon.getString("number"))){
						purchaseItem.setPurchasesum(Double.valueOf(itemJon.getString("number")));
					}
					purchaseItem.setMargin(purchaseItem.getPurchasesum());
					purchaseItem.setStoragequantity(0D);
					purchaseItem.setUnstoragequantity(0D);
					purchaseItem.setPretendingtake(0D);
					if(StringUtils.isNotBlank(itemJon.getString("price"))){
						purchaseItem.setPrice(Double.valueOf(itemJon.getString("price")));
					}
					purchaseItem.setUnit("吨");
					purchaseItem.setRemark(itemJon.getString("remark"));
					itemList.add(purchaseItem);
				}
			}
		}
		return itemList;
	}

	@Override
	public PaginationVO<AppOrderResp> appToPage(AppOrderReq req) {
		PaginationVO<AppOrderResp> page = null;
		if(req != null){
			page = new PaginationVO<AppOrderResp>();
			long count = purchaseApplicationMapper.findAppToPageGroupMaterielCount(req);
			if(count > 0){
				req.setStart((req.getPageNo() - 1) * req.getPageSize());
				req.setLimit(req.getPageSize());
				List<AppOrderResp> list = purchaseApplicationMapper.findAppToPageGroupMateriel(req);
				page.setList(list);
			}
			page.setPageNo(req.getPageNo());
			page.setPageSize(req.getPageSize());
			page.setTotal(count);
		}
		return page;
	}

	@Override
	public Result appToDetail(AppOrderReq req) {
		Result result = Result.getParamErrorResult();
		if(req != null 
				&& StringUtils.isNotBlank(req.getId())
				&& StringUtils.isNotBlank(req.getDetailid())){
			PurchaseApplication application = purchaseApplicationMapper.selectByPrimaryKey(req.getId());
			PurchaseApplicationDetail applicationDetail = purchaseApplicationDetailMapper.selectByPrimaryKey(req.getDetailid());
			if(application != null && applicationDetail != null){
				AppOrderDetailResp resp = new AppOrderDetailResp();
				resp.setId(application.getId());
				resp.setDetailid(applicationDetail.getId());
				resp.setCode(application.getCode());
				resp.setMaterialName(applicationDetail.getMaterielname());
				resp.setOrgName(application.getOrgname());
				resp.setCustomerName(application.getSuppliername());
				resp.setBillDateStr(DateUtil.parse(application.getBilltime(), "yyyy-MM-dd HH:mm:ss"));
				resp.setMargin(applicationDetail.getMargin());
				resp.setMinemouthname(application.getMinemouthname());
				result.setData(resp);
				result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			}else{
				result.setErrorCode(ErrorCode.APPLICATION_NOT_EXIST);
			}
		}
		return result;
	}
}