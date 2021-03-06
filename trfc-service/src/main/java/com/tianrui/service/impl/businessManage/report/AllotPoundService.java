package com.tianrui.service.impl.businessManage.report;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.businessManage.report.IAllotPoundService;
import com.tianrui.api.req.businessManage.report.InOutDaoPoundQuery;
import com.tianrui.api.resp.businessManage.report.InOutDaoPoundMaterResp;
import com.tianrui.api.resp.businessManage.report.InOutDaoPoundResp;
import com.tianrui.service.bean.businessManage.report.InOutDaoPound;
import com.tianrui.service.mapper.businessManage.report.InOutDaoPoundMapper;
import com.tianrui.smartfactory.common.vo.PaginationVO;

/**
 * 调拨逐车明细Service
 * @author lenovo
 *
 */
@Service
public class AllotPoundService implements IAllotPoundService{
	@Resource
	private InOutDaoPoundMapper inOutDaoPoundMapper;
	
	/**
	 * 分页
	 */
	@Override
	public PaginationVO<InOutDaoPoundResp> page(InOutDaoPoundQuery inOutDaoPoundQuery) throws Exception {
		// TODO Auto-generated method stub
		PaginationVO<InOutDaoPoundResp> page = null;
		if (inOutDaoPoundQuery != null) {
			InOutDaoPound query = queryParam(inOutDaoPoundQuery, true);
			if (query !=null) {
				page = new PaginationVO<InOutDaoPoundResp>();
			}
			//4表示厂内倒运
			query.setBilltype("4");
			//查询总条数
			Long count = inOutDaoPoundMapper.countByCondition(query);
			if (count>0) {
				page.setList(copyBeanList2RespList(inOutDaoPoundMapper.selectByCondition(query)));
			}
			//返回结果参数补全
			page.setPageNo(inOutDaoPoundQuery.getPageNo());
			page.setPageSize(inOutDaoPoundQuery.getPageSize());
			page.setTotal(count);
		}
		
		
		return page;
	}

	@Override
	public List<InOutDaoPoundResp> list(InOutDaoPoundQuery inOutDaoPoundQuery) throws Exception {
		// TODO Auto-generated method stub
		
		List<InOutDaoPoundResp> rs = null;
		if (inOutDaoPoundQuery != null) {
			InOutDaoPound query = queryParam(inOutDaoPoundQuery, false);
			//4 表示厂内倒运
			query.setBilltype("4");
			if (query != null) {
				//参数转换
				rs = copyBeanList2RespList(inOutDaoPoundMapper.selectByCondition(query));
			}
		}
		
		/*if( CollectionUtils.isNotEmpty(rs) ){
			rs =new ArrayList<InOutDaoPoundResp>();
		}*/
		return rs;
	}
	/**
	 * 查询参数拼接
	 * @param query
	 * @param bb 当bb=true时调用分页，bb=false时，不调用分页
	 * @return
	 */
	private InOutDaoPound queryParam(InOutDaoPoundQuery query,boolean bb){
		InOutDaoPound bean =null;
		if(query!=null){
			bean =new InOutDaoPound();
			//开始时间
			if( StringUtils.isNotBlank(query.getBeginTime()) ){
				bean.setBeginTimeLong(Long.valueOf(query.getBeginTime()));
			}
			//结束时间
			if( StringUtils.isNotBlank(query.getEndTime()) ){
				bean.setEndTimeLong(Long.valueOf(query.getEndTime()));
			}
			//通知单号
			if( StringUtils.isNotBlank(query.getNoticecode()) ){
				bean.setNoticecodeLike(query.getNoticecode());
			}	
			//过磅单号
			if (StringUtils.isNotBlank(query.getCode())) {
				bean.setCodeLike(query.getCode());
			}
			//调入堆场
			if( StringUtils.isNotBlank(query.getEnteryardname()) ){
				bean.setEnteryardnameLike(query.getEnteryardname());
			}	
			//调出堆场
			if( StringUtils.isNotBlank(query.getLeaveyardname()) ){
				bean.setLeaveyardnameLike(query.getLeaveyardname());
			}	
			//单据状态
			if( StringUtils.isNotBlank(query.getStatus()) ){
				bean.setStatus(query.getStatus());;
			}

			//供应商
			if( StringUtils.isNotBlank(query.getSuppliername()) ){
				bean.setSupplierNameLike(query.getSuppliername());
			}
			//物料
			if( StringUtils.isNotBlank(query.getCargo()) ){
				bean.setCargoNameLike(query.getCargo());
			}
			//司机
			if( StringUtils.isNotBlank(query.getDrivername()) ){
				bean.setDriverNameLike(query.getDrivername());
			}
			//车牌
			if( StringUtils.isNotBlank(query.getVehicleno()) ){
				bean.setVehicleNoLike(query.getVehicleno());
			}
			//客户
			if( StringUtils.isNotBlank(query.getCustomername()) ){
				bean.setCustomerNameLike(query.getCustomername());
			}		
			if (bb==true) {
				//分页参数
				bean.setStart((query.getPageNo()-1)*query.getPageSize());
				bean.setLimit(query.getPageSize());
			}
			
		}
		return bean;
		
	}

	//返回对象拼装
	private List<InOutDaoPoundResp> copyBeanList2RespList(List<InOutDaoPound> list){
		List<InOutDaoPoundResp> rs =null;
		if( CollectionUtils.isNotEmpty(list) ){
			rs=new ArrayList<InOutDaoPoundResp>();
			for(InOutDaoPound item:list){
				if( item !=null ){
					InOutDaoPoundResp itemResp = new InOutDaoPoundResp();
					try {
						PropertyUtils.copyProperties(itemResp, item);
						rs.add(itemResp);
					}  catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		return rs;
	}

	@Override
	public PaginationVO<InOutDaoPoundMaterResp> materPage(InOutDaoPoundQuery inOutDaoPoundQuery) throws Exception {
		// TODO Auto-generated method stub
		PaginationVO<InOutDaoPoundMaterResp> page = null;
		if (inOutDaoPoundQuery != null) {
			InOutDaoPound query = queryParam(inOutDaoPoundQuery, true);
			if (query !=null) {
				page = new PaginationVO<InOutDaoPoundMaterResp>();
			}
			//4表示厂内倒运
			query.setBilltype("4");
			//查询总条数
			Long count = inOutDaoPoundMapper.countByConditionForMater(query);
			if (count>0) {
				page.setList(inOutDaoPoundMapper.selectByConditionForMater(query));
			}
			//返回结果参数补全
			page.setPageNo(inOutDaoPoundQuery.getPageNo());
			page.setPageSize(inOutDaoPoundQuery.getPageSize());
			page.setTotal(count);
		}
		
		
		return page;
	}

	@Override
	public List<InOutDaoPoundMaterResp> materList(InOutDaoPoundQuery inOutDaoPoundQuery) throws Exception {
		// TODO Auto-generated method stub
		List<InOutDaoPoundMaterResp> rs = null;
		if (inOutDaoPoundQuery != null) {
			InOutDaoPound query = queryParam(inOutDaoPoundQuery, true);
			//4表示厂内倒运
			query.setBilltype("4");
			if (query !=null) {
				rs = inOutDaoPoundMapper.selectByConditionForMater(query);
			}
		}
		
		return rs;
	}

	@Override
	public PaginationVO<InOutDaoPoundMaterResp> materDCPage(InOutDaoPoundQuery inOutDaoPoundQuery) throws Exception {
		PaginationVO<InOutDaoPoundMaterResp> page = null;
		if (inOutDaoPoundQuery != null) {
			InOutDaoPound query = queryParam(inOutDaoPoundQuery, true);
			if (query !=null) {
				page = new PaginationVO<InOutDaoPoundMaterResp>();
			}
			//4表示厂内倒运
			query.setBilltype("4");
			//查询总条数
			Long count = inOutDaoPoundMapper.countByConditionForMaterDC(query);
			if (count>0) {
				page.setList(inOutDaoPoundMapper.selectByConditionForMaterDC(query));
			}
			//返回结果参数补全
			page.setPageNo(inOutDaoPoundQuery.getPageNo());
			page.setPageSize(inOutDaoPoundQuery.getPageSize());
			page.setTotal(count);
		}
		
		
		return page;
	}

	@Override
	public List<InOutDaoPoundMaterResp> materDCList(InOutDaoPoundQuery inOutDaoPoundQuery) throws Exception {
		List<InOutDaoPoundMaterResp> rs = null;
		if (inOutDaoPoundQuery != null) {
			InOutDaoPound query = queryParam(inOutDaoPoundQuery, true);
			//4表示厂内倒运
			query.setBilltype("4");
			if (query !=null) {
				rs = inOutDaoPoundMapper.selectByConditionForMaterDC(query);
			}
		}
		
		return rs;
	}

	@Override
	public PaginationVO<InOutDaoPoundMaterResp> materVehiclenoPage(InOutDaoPoundQuery inOutDaoPoundQuery) throws Exception {
		PaginationVO<InOutDaoPoundMaterResp> page = null;
		if (inOutDaoPoundQuery != null) {
			InOutDaoPound query = queryParam(inOutDaoPoundQuery, true);
			if (query !=null) {
				page = new PaginationVO<InOutDaoPoundMaterResp>();
			}
			//4表示厂内倒运
			query.setBilltype("4");
			//查询总条数
			Long count = inOutDaoPoundMapper.countByConditionForMaterVehicleno(query);
			if (count>0) {
				page.setList(inOutDaoPoundMapper.selectByConditionForMaterVehicleno(query));
			}
			//返回结果参数补全
			page.setPageNo(inOutDaoPoundQuery.getPageNo());
			page.setPageSize(inOutDaoPoundQuery.getPageSize());
			page.setTotal(count);
		}
		
		
		return page;
	}

	@Override
	public List<InOutDaoPoundMaterResp> materVehiclenoList(InOutDaoPoundQuery inOutDaoPoundQuery) throws Exception {
		List<InOutDaoPoundMaterResp> rs = null;
		if (inOutDaoPoundQuery != null) {
			InOutDaoPound query = queryParam(inOutDaoPoundQuery, true);
			//4表示厂内倒运
			query.setBilltype("4");
			if (query !=null) {
				rs = inOutDaoPoundMapper.selectByConditionForMaterVehicleno(query);
			}
		}
		
		return rs;
	}

	@Override
	public PaginationVO<InOutDaoPoundMaterResp> materDrDcPage(InOutDaoPoundQuery inOutDaoPoundQuery) throws Exception {
		PaginationVO<InOutDaoPoundMaterResp> page = null;
		if (inOutDaoPoundQuery != null) {
			InOutDaoPound query = queryParam(inOutDaoPoundQuery, true);
			if (query !=null) {
				page = new PaginationVO<InOutDaoPoundMaterResp>();
			}
			//4表示厂内倒运
			query.setBilltype("4");
			//查询总条数
			Long count = inOutDaoPoundMapper.countByConditionForMaterDrDc(query);
			if (count>0) {
				page.setList(inOutDaoPoundMapper.selectByConditionForMaterDrDc(query));
			}
			//返回结果参数补全
			page.setPageNo(inOutDaoPoundQuery.getPageNo());
			page.setPageSize(inOutDaoPoundQuery.getPageSize());
			page.setTotal(count);
		}
		
		
		return page;
	}

	@Override
	public List<InOutDaoPoundMaterResp> materDrDcList(InOutDaoPoundQuery inOutDaoPoundQuery) throws Exception {
		List<InOutDaoPoundMaterResp> rs = null;
		if (inOutDaoPoundQuery != null) {
			InOutDaoPound query = queryParam(inOutDaoPoundQuery, true);
			//4表示厂内倒运
			query.setBilltype("4");
			if (query !=null) {
				rs = inOutDaoPoundMapper.selectByConditionForMaterDrDc(query);
			}
		}
		
		return rs;	
	}

}
