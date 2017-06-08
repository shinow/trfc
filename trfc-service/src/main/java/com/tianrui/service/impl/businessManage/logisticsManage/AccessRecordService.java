package com.tianrui.service.impl.businessManage.logisticsManage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianrui.api.intf.businessManage.logisticsManage.IAccessRecordService;
import com.tianrui.api.intf.businessManage.purchaseManage.IPurchaseArriveService;
import com.tianrui.api.intf.businessManage.salesManage.ISalesArriveService;
import com.tianrui.api.intf.system.base.ISystemCodeService;
import com.tianrui.api.req.basicFile.measure.VehicleCheckApi;
import com.tianrui.api.req.businessManage.logisticsManage.AccessRecordQuery;
import com.tianrui.api.req.businessManage.salesManage.ApiDoorSystemSave;
import com.tianrui.api.req.system.base.GetCodeReq;
import com.tianrui.api.resp.businessManage.logisticsManage.AccessRecordResp;
import com.tianrui.api.resp.businessManage.otherManage.OtherArriveResp;
import com.tianrui.api.resp.businessManage.purchaseManage.PurchaseArriveResp;
import com.tianrui.api.resp.businessManage.salesManage.ApiSalesArriveResp;
import com.tianrui.api.resp.businessManage.salesManage.SalesArriveResp;
import com.tianrui.service.bean.basicFile.measure.VehicleManage;
import com.tianrui.service.bean.businessManage.cardManage.Card;
import com.tianrui.service.bean.businessManage.logisticsManage.AccessRecord;
import com.tianrui.service.bean.businessManage.otherManage.OtherArrive;
import com.tianrui.service.bean.businessManage.purchaseManage.PurchaseApplicationDetail;
import com.tianrui.service.bean.businessManage.purchaseManage.PurchaseArrive;
import com.tianrui.service.bean.businessManage.salesManage.SalesApplicationDetail;
import com.tianrui.service.bean.businessManage.salesManage.SalesApplicationJoinNatice;
import com.tianrui.service.bean.businessManage.salesManage.SalesArrive;
import com.tianrui.service.bean.common.RFID;
import com.tianrui.service.impl.businessManage.otherManage.OtherArriveService;
import com.tianrui.service.mapper.basicFile.measure.VehicleManageMapper;
import com.tianrui.service.mapper.businessManage.cardManage.CardMapper;
import com.tianrui.service.mapper.businessManage.logisticsManage.AccessRecordMapper;
import com.tianrui.service.mapper.businessManage.otherManage.OtherArriveMapper;
import com.tianrui.service.mapper.businessManage.purchaseManage.PurchaseApplicationDetailMapper;
import com.tianrui.service.mapper.businessManage.purchaseManage.PurchaseArriveMapper;
import com.tianrui.service.mapper.businessManage.salesManage.SalesApplicationDetailMapper;
import com.tianrui.service.mapper.businessManage.salesManage.SalesApplicationJoinNaticeMapper;
import com.tianrui.service.mapper.businessManage.salesManage.SalesArriveMapper;
import com.tianrui.service.mapper.common.RFIDMapper;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.utils.DateUtil;
import com.tianrui.smartfactory.common.utils.UUIDUtil;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;

@Service
public class AccessRecordService implements IAccessRecordService {

	@Autowired
	private AccessRecordMapper accessRecordMapper;
	@Autowired
	private IPurchaseArriveService purchaseArriveService;
	@Autowired
	private PurchaseArriveMapper purchaseArriveMapper;
	@Autowired
	private ISalesArriveService salesArriveService;
	@Autowired
	private SalesArriveMapper salesArriveMapper;
	@Autowired
	private CardMapper cardMapper;
	@Autowired
	private ISystemCodeService systemCodeService;
	@Autowired
	private PurchaseApplicationDetailMapper purchaseApplicationDetailMapper;
	@Autowired
	private SalesApplicationDetailMapper salesApplicationDetailMapper;
	@Autowired
	private SalesApplicationJoinNaticeMapper salesApplicationJoinNaticeMapper;
	@Autowired
	private RFIDMapper rfidMapper;
	@Autowired
	private VehicleManageMapper vehicleManageMapper;
	@Autowired
	private OtherArriveMapper otherArriveMapper;
	@Autowired
	private OtherArriveService otherArriveService;
	
	@Override
	public PaginationVO<AccessRecordResp> page(AccessRecordQuery query) throws Exception{
		PaginationVO<AccessRecordResp> page = null;
		if (query != null) {
			page = new PaginationVO<AccessRecordResp>();
			query.setState("1");
			long count = accessRecordMapper.findAccessRecordPageCount(query);
			if (count > 0) {
				query.setStart((query.getPageNo() - 1) * query.getPageSize());
				query.setLimit(query.getPageSize());
				List<AccessRecord> list = accessRecordMapper.findAccessRecordPage(query);
				page.setList(copyBeanList2RespList(list, true));
			}
			page.setTotal(count); 
			page.setPageNo(query.getPageNo());
			page.setPageSize(query.getPageSize());
		}
		return page;
	}
	
	private void SetPurchaseViewData(List<AccessRecordResp> list, String type) throws Exception{
		List<String> ids = new ArrayList<>();
		for (AccessRecordResp resp : list) {
			if (StringUtils.equals(resp.getBusinesstype(), type)) {
				ids.add(resp.getNoticeid());
			}
		}
		List<PurchaseArriveResp> listArrive = purchaseArriveService.selectByIds(ids);
		if (CollectionUtils.isNotEmpty(listArrive)) {
			for (AccessRecordResp resp : list) {
				for (PurchaseArriveResp arriveResp : listArrive) {
					if (StringUtils.equals(resp.getNoticeid(), arriveResp.getId())) {
						resp.setVehicleno(arriveResp.getVehicleno());
						resp.setMaterielname(arriveResp.getPurchaseApplicationDetailResp().getMaterielname());
						resp.setRfid(arriveResp.getVehiclerfid());
						resp.setOtherparty(arriveResp.getPurchaseApplicationResp().getSuppliername());
						if (StringUtils.isNotBlank(arriveResp.getIcardid())) {
							Card card = cardMapper.selectByPrimaryKey(arriveResp.getIcardid());
							if (card != null) {
								resp.setIcardno(card.getCardno());
								resp.setIcardcode(card.getCardcode());
							}
						}
					}
				}
			}
		}
	}
	
	private void SetSalesViewData(List<AccessRecordResp> list, String type) throws Exception{
		List<String> ids = new ArrayList<>();
		for (AccessRecordResp resp : list) {
			if (StringUtils.equals(resp.getBusinesstype(), type)) {
				ids.add(resp.getNoticeid());
			}
		}
		List<SalesArriveResp> salesList = salesArriveService.selectByIds(ids);
		if (CollectionUtils.isNotEmpty(salesList)) {
			for (AccessRecordResp resp : list) {
				for (SalesArriveResp salesResp : salesList) {
					if (StringUtils.equals(resp.getNoticeid(), salesResp.getId())) {
						resp.setVehicleno(salesResp.getVehicleno());
						resp.setMaterielname(salesResp.getMainApplicationDetail().getMaterielname());
						resp.setRfid(salesResp.getVehiclerfid());
						resp.setOtherparty(salesResp.getMainApplication().getCustomername());
						if (StringUtils.isNotBlank(salesResp.getIcardid())) {
							Card card = cardMapper.selectByPrimaryKey(salesResp.getIcardid());
							if (card != null) {
								resp.setIcardno(card.getCardno());
								resp.setIcardcode(card.getCardcode());
							}
						}
					}
				}
			}
		}
	}
	
	//其他业务  lxy
	private void SetOtherViewData(List<AccessRecordResp> list) throws Exception{
		// 5:其他入库 7:其他出库 4:厂内倒运
		String[] types = {"5","7","4","9"};
		for (AccessRecordResp resp : list) {
			if (Arrays.asList(types).contains(resp.getBusinesstype())) {
				Result result = otherArriveService.findOne(resp.getNoticeid());
				OtherArriveResp ar = (OtherArriveResp)result.getData();
				resp.setVehicleno(ar.getVehicleno());
				resp.setMaterielname(ar.getMaterielname());
				resp.setRfid(ar.getRfid());
				if(StringUtils.isNotBlank(ar.getSuppliername())){
					resp.setOtherparty(ar.getSuppliername());
				}else if(StringUtils.isNotBlank(ar.getCustomername())){
					resp.setOtherparty(ar.getCustomername());
				}else{
					resp.setOtherparty(ar.getReceivedepartmentname());
				}
				if (StringUtils.isNotBlank(ar.getIcardid())) {
					Card card = cardMapper.selectByPrimaryKey(ar.getIcardid());
					if (card != null) {
						resp.setIcardno(card.getCardno());
						resp.setIcardcode(card.getCardcode());
					}
				}
			}
		}
	}
	
	
	private List<AccessRecordResp> copyBeanList2RespList(List<AccessRecord> list, boolean setNotice) throws Exception {
		List<AccessRecordResp> listResp = null;
		if (CollectionUtils.isNotEmpty(list)) {
			listResp = new ArrayList<AccessRecordResp>();
			for (AccessRecord sa : list) {
				listResp.add(copyBean2Resp(sa, setNotice));
			}
			if (setNotice) {
				//set采购信息
				SetPurchaseViewData(listResp, "1");
				//set销售信息
				SetSalesViewData(listResp, "2");
				//set其他业务信息
				SetOtherViewData(listResp);
			}
		}
		return listResp;
	}
	
	private AccessRecordResp copyBean2Resp(AccessRecord bean, boolean setNotice) throws Exception {
		AccessRecordResp resp = null;
		if (bean != null) {
			resp = new AccessRecordResp();
			PropertyUtils.copyProperties(resp, bean);
//			if (setNotice) {
//				groupSetViewData(resp);
//			}
		}
		return resp;
	}

	@Override
	public Result findOne(AccessRecordQuery query) throws Exception {
		Result rs = Result.getParamErrorResult();
		if(query!=null){
			AccessRecord record = accessRecordMapper.selectByPrimaryKey(query.getId());
//			if(record!=null){
//				AccessRecordResp resp = new AccessRecordResp();
//				PropertyUtils.copyProperties(resp, record);
//				if(StringUtils.isNotBlank(record.getNoticeid())){
//					SalesArriveResp sar = salesArriveService.findOne(record.getNoticeid());
//					if(sar!=null){
//						resp.setRfid(sar.getVehiclerfid());
//						resp.setVehicleno(sar.getVehicleno());
//						resp.setOtherparty(sar.getUnit());
//						resp.setIcardno(sar.getIcardno());
//						resp.setSpraycode(sar.getSpraycode());
//					}
//				}
				rs = Result.getSuccessResult();
				rs.setData(record);
//			}
		}
		return rs;
	}
	
//	private AccessRecordResp groupSetViewData(AccessRecordResp resp) throws Exception{
//		if (resp != null) {
//			switch (resp.getBusinesstype()) {
//			case "1":
//				//采购
//				setPurchaseViewData(resp);
//				break;
//			case "2":
//				setSalesViewData(resp);
//				break;
//			default:
//				break;
//			}
//		}
//		return resp;
//	}
//
//	//添加采购信息
//	private void setPurchaseViewData(AccessRecordResp resp) throws Exception {
//		PurchaseArriveResp arriveResp = purchaseArriveService.findOne(resp.getNoticeid());
//		resp.setVehicleno(arriveResp.getVehicleno());
//		resp.setMaterielname(arriveResp.getPurchaseApplicationDetailResp().getMaterielname());
//		resp.setRfid(arriveResp.getVehiclerfid());
//		resp.setOtherparty(arriveResp.getPurchaseApplicationResp().getSuppliername());
//		if (StringUtils.isNotBlank(arriveResp.getIcardid())) {
//			CardResp card = cardService.findOne(arriveResp.getIcardid());
//			if (card != null) {
//				resp.setIcardno(card.getCardno());
//				resp.setIcardcode(card.getCardcode());
//			}
//		}
//	}
//	
//	//添加销售信息
//	private void setSalesViewData(AccessRecordResp resp) throws Exception {
//		SalesArriveResp salesResp = salesArriveService.findOne(resp.getNoticeid());
//		resp.setVehicleno(salesResp.getVehicleno());
//		resp.setMaterielname(salesResp.getSalesApplication().getDetailResp().getMaterielname());
//		resp.setRfid(salesResp.getVehiclerfid());
//		resp.setOtherparty(salesResp.getSalesApplication().getCustomername());
//		if (StringUtils.isNotBlank(salesResp.getIcardid())) {
//			CardResp card = cardService.findOne(salesResp.getIcardid());
//			if (card != null) {
//				resp.setIcardno(card.getCardno());
//				resp.setIcardcode(card.getCardcode());
//			}
//		}
//	}
	
	@Transactional
	@Override
	public Result addAccessRecord(ApiDoorSystemSave apiParam) throws Exception {
		Result result = Result.getParamErrorResult();
		if (apiParam!=null && StringUtils.isNotBlank(apiParam.getNotionformcode()) 
				&& StringUtils.isNotBlank(apiParam.getIcardno())
				&& StringUtils.isNotBlank(apiParam.getServicetype())
				&& StringUtils.isNotBlank(apiParam.getType()) 
				&& StringUtils.isNotBlank(apiParam.getTime())
				&& StringUtils.isNotBlank(apiParam.getCurrUid())) {
			Card card = cardMapper.selectByCardno(apiParam.getIcardno());
			if (card != null) {
				ErrorCode ec = ErrorCode.OPERATE_ERROR;
				switch (apiParam.getServicetype()) {
				//采购到货通知单
				case "0":
					ec = setICardToPurchaseArrive(card, apiParam);
					break;
				//采购退货通知单
				case "1":
					ec = setICardToPurchaseReturnArrive(card, apiParam);
					break;
				//销售提货通知单
				case "2":
					ec = setICardToSalesArrive(card, apiParam);
					break;
					//销售提货通知单
				case "5":
					ec = setICardToOtherArrive(card, apiParam);
					break;
				case "7":
					ec = setICardToOtherArrive(card, apiParam);
					break;
				case "9":
					ec = setICardToOtherArrive(card, apiParam);
					break;
				case "4":
					ec = setICardToOtherArrive(card, apiParam);
					break;
				default:
					break;
				}
				result.setErrorCode(ec);
			}else{
				result.setErrorCode(ErrorCode.CARD_NOT_EXIST);
			}
		}
		return result;
	}

	private ErrorCode setICardToPurchaseArrive(Card card, ApiDoorSystemSave apiParam) throws Exception {
		ErrorCode ec = ErrorCode.OPERATE_ERROR;
		PurchaseArrive purchase = purchaseArriveMapper.selectByCode(apiParam.getNotionformcode());
		if(StringUtils.equals(purchase.getAuditstatus(), "1")){
			if(!StringUtils.equals(purchase.getStatus(), "3")){
				//入厂
				if(StringUtils.equals(apiParam.getType(), "1")){
					if(StringUtils.equals(purchase.getStatus(), "0")){
						PurchaseArrive pa = purchaseArriveMapper.checkICUse(card.getId());
						if(pa == null){
							//修改通知单状态并绑定IC卡
							pa = new PurchaseArrive();
							pa.setId(purchase.getId());
							pa.setStatus("6");
							pa.setIcardid(card.getId());
							//回写订单的未入库占用量和预提占用
							PurchaseApplicationDetail applicationDetail = purchaseApplicationDetailMapper.selectByPrimaryKey(purchase.getBilldetailid());
							PurchaseApplicationDetail save = new PurchaseApplicationDetail();
							save.setId(applicationDetail.getId());
							save.setUnstoragequantity(applicationDetail.getUnstoragequantity() + purchase.getArrivalamount());
							save.setPretendingtake(applicationDetail.getPretendingtake() - purchase.getArrivalamount());
							if(purchaseArriveMapper.updateByPrimaryKeySelective(pa) > 0
									&& purchaseApplicationDetailMapper.updateByPrimaryKeySelective(save) > 0){
								ec = addInfoAccessRecordApi(apiParam, purchase.getId(), purchase.getCode(), "1");
							}else{
								ec = ErrorCode.OPERATE_ERROR;
							}
						}else{
							ec = ErrorCode.CARD_IN_USE;
						}
					}else{
						ec = ErrorCode.VEHICLE_NOTICE_ALREADY_ENTER;
					}
				//出厂
				}else{
					if(StringUtils.equals(purchase.getStatus(), "2")){
						//修改通知单状态
						PurchaseArrive pa = new PurchaseArrive();
						pa.setId(purchase.getId());
						pa.setStatus("5");
						if(purchaseArriveMapper.updateByPrimaryKeySelective(pa) > 0){
							AccessRecord access = accessRecordMapper.selectByNoticeId(purchase.getId());
							if(StringUtils.equals(access.getAccesstype(), "1")){
								ec = addOutAccessRecordApi(apiParam, access.getId());
							}else{
								ec = ErrorCode.NOTICE_OUT_FACTORY;
							}
						}else{
							ec = ErrorCode.OPERATE_ERROR;
						}
					}else{
						ec = ErrorCode.VEHICLE_NOTICE_NOT_TWO_WEIGHT;
					}
				}
			}else{
				ec = ErrorCode.NOTICE_ON_INVALID;
			}
		}else{
			ec = ErrorCode.NOTICE_NOT_AUDIT;
		}
		return ec;
	}
	
	private ErrorCode setICardToPurchaseReturnArrive(Card card, ApiDoorSystemSave apiParam) throws Exception {
		ErrorCode ec = ErrorCode.OPERATE_ERROR;
		PurchaseArrive purchase = purchaseArriveMapper.selectByCode(apiParam.getNotionformcode());
		if(StringUtils.equals(purchase.getAuditstatus(), "1")){
			if(!StringUtils.equals(purchase.getStatus(), "3")){
				//入厂
				if(StringUtils.equals(apiParam.getType(), "1")){
					if(StringUtils.equals(purchase.getStatus(), "0")){
						PurchaseArrive pa = purchaseArriveMapper.checkICUse(card.getId());
						if(pa == null){
							//修改通知单状态并绑定IC卡
							pa = new PurchaseArrive();
							pa.setId(purchase.getId());
							pa.setStatus("6");
							pa.setIcardid(card.getId());
							//回写订单的未入库占用量和预提占用
							PurchaseApplicationDetail applicationDetail = purchaseApplicationDetailMapper.selectByPrimaryKey(purchase.getBilldetailid());
							PurchaseApplicationDetail save = new PurchaseApplicationDetail();
							save.setId(applicationDetail.getId());
							save.setPretendingtake(applicationDetail.getPretendingtake() - purchase.getArrivalamount());
							if(purchaseArriveMapper.updateByPrimaryKeySelective(pa) > 0
									&& purchaseApplicationDetailMapper.updateByPrimaryKeySelective(save) > 0){
								ec = addInfoAccessRecordApi(apiParam, purchase.getId(), purchase.getCode(), "1");
							}else{
								ec = ErrorCode.OPERATE_ERROR;
							}
						}else{
							ec = ErrorCode.CARD_IN_USE;
						}
					}else{
						ec = ErrorCode.VEHICLE_NOTICE_ALREADY_ENTER;
					}
				//出厂
				}else{
					if(StringUtils.equals(purchase.getStatus(), "2")){
						//修改通知单状态
						PurchaseArrive pa = new PurchaseArrive();
						pa.setId(purchase.getId());
						pa.setStatus("5");
						if(purchaseArriveMapper.updateByPrimaryKeySelective(pa) > 0){
							AccessRecord access = accessRecordMapper.selectByNoticeId(purchase.getId());
							if(StringUtils.equals(access.getAccesstype(), "1")){
								ec = addOutAccessRecordApi(apiParam, access.getId());
							}else{
								ec = ErrorCode.NOTICE_OUT_FACTORY;
							}
						}else{
							ec = ErrorCode.OPERATE_ERROR;
						}
					}else{
						ec = ErrorCode.VEHICLE_NOTICE_NOT_TWO_WEIGHT;
					}
				}
			}else{
				ec = ErrorCode.NOTICE_ON_INVALID;
			}
		}else{
			ec = ErrorCode.NOTICE_NOT_AUDIT;
		}
		return ec;
	}

	private ErrorCode setICardToSalesArrive(Card card, ApiDoorSystemSave apiParam) throws Exception {
		ErrorCode ec = ErrorCode.OPERATE_ERROR;
		SalesArrive sales = salesArriveMapper.selectByCode(apiParam.getNotionformcode());
		if(StringUtils.equals(sales.getAuditstatus(), "1")){
			if(!StringUtils.equals(sales.getStatus(), "3")){
				//入厂
				if(StringUtils.equals(apiParam.getType(), "1")){
					if(StringUtils.equals(apiParam.getType(), "1")){
						SalesArrive sa = salesArriveMapper.checkICUse(card.getId());
						if(sa == null){
							//修改通知单状态并绑定IC卡
							sa = new SalesArrive();
							sa.setId(sales.getId());
							sa.setStatus("6");
							sa.setIcardid(card.getId());
							sa.setIcardno(card.getCardno());
							if(salesArriveMapper.updateByPrimaryKeySelective(sa) > 0){
								//回写订单的未入库占用量和预提占用
								List<SalesApplicationJoinNatice> list = salesApplicationJoinNaticeMapper.selectByNaticeId(sales.getId());
								if(CollectionUtils.isNotEmpty(list)){
									boolean flag = false;
									Double takeamount = sales.getTakeamount();
									for(SalesApplicationJoinNatice join : list){
										SalesApplicationDetail applicationDetail = salesApplicationDetailMapper.selectByPrimaryKey(join.getBilldetailid());
										if(applicationDetail != null && takeamount > 0){
											if(takeamount > join.getMargin()){
												SalesApplicationDetail sd = new SalesApplicationDetail();
												sd.setId(applicationDetail.getId());
												sd.setUnstoragequantity(applicationDetail.getUnstoragequantity() + join.getMargin());
												sd.setPretendingtake(applicationDetail.getPretendingtake() - join.getMargin());
												if(salesApplicationDetailMapper.updateByPrimaryKeySelective(sd) > 0){
													flag = true;
												}else{
													flag = false;
													break;
												}
												takeamount -= join.getMargin();
											}else{
												SalesApplicationDetail sd = new SalesApplicationDetail();
												sd.setId(applicationDetail.getId());
												sd.setUnstoragequantity(applicationDetail.getUnstoragequantity() + takeamount);
												sd.setPretendingtake(applicationDetail.getPretendingtake() - takeamount);
												if(salesApplicationDetailMapper.updateByPrimaryKeySelective(sd) > 0){
													flag = true;
												}else{
													flag = false;
													break;
												}
												takeamount = 0D;
											}
										}
									}
									if(flag){
										ec = addInfoAccessRecordApi(apiParam, sales.getId(), sales.getCode(), "2");
									}else{
										ec = ErrorCode.OPERATE_ERROR;
									}
								}	
							}else{
								ec = ErrorCode.OPERATE_ERROR;
							}
						}else{
							ec = ErrorCode.CARD_IN_USE;
						}
					}else{
						ec = ErrorCode.VEHICLE_NOTICE_ALREADY_ENTER;
					}
				//出厂
				}else{
					if(StringUtils.equals(sales.getStatus(), "2")){
						//修改通知单状态并绑定IC卡
						SalesArrive sa = new SalesArrive();
						sa.setId(sales.getId());
						sa.setStatus("5");
						if(salesArriveMapper.updateByPrimaryKeySelective(sa) > 0){
							AccessRecord access = accessRecordMapper.selectByNoticeId(sales.getId());
							if(access != null){
								ec = addOutAccessRecordApi(apiParam, access.getId());
							}else{
								ec = ErrorCode.VEHICLE_NOTICE_NOT_ENTER;
							}
						}else{
							ec = ErrorCode.OPERATE_ERROR;
						}
					}else{
						ec = ErrorCode.VEHICLE_NOTICE_NOT_TWO_WEIGHT;
					}
				}
			}else{
				ec = ErrorCode.NOTICE_ON_INVALID;
			}
		}else{
			ec = ErrorCode.NOTICE_NOT_AUDIT;
		}
		return ec;
	}
	
	//其他业务通知单绑定ic卡
	private ErrorCode setICardToOtherArrive(Card card, ApiDoorSystemSave apiParam) throws Exception {
		ErrorCode ec = ErrorCode.OPERATE_ERROR;
		OtherArrive other = otherArriveMapper.selectByCode(apiParam.getNotionformcode());
		if(StringUtils.equals(other.getAuditstatus(), "1")){
			if(!StringUtils.equals(other.getStatus(), "3")){
				//入厂
				if(StringUtils.equals(apiParam.getType(), "1")){
					OtherArrive  oa = new OtherArrive();
					oa.setIcardid(card.getId());
					List<OtherArrive> list = otherArriveMapper.checkDriverAndVehicleAndIcardIsUse(oa);
					if(list == null || list.size() == 0){
						//修改通知单状态并绑定IC卡
						oa.setId(other.getId());
						oa.setStatus("6");
						if(otherArriveMapper.updateByPrimaryKeySelective(oa) > 0){
							ec = addInfoAccessRecordApi(apiParam, other.getId(), other.getCode(), apiParam.getServicetype());
						}else{
							ec = ErrorCode.OPERATE_ERROR;
						}
					}else{
						ec = ErrorCode.CARD_IN_USE;
					}
				//出厂
				}else{
					//修改通知单状态并绑定IC卡
					OtherArrive oa = new OtherArrive();
					oa.setId(other.getId());
					oa.setStatus("5");
					if(otherArriveMapper.updateByPrimaryKeySelective(oa) > 0){
						AccessRecord access = accessRecordMapper.selectByNoticeId(other.getId());
						if(access != null){
							ec = addOutAccessRecordApi(apiParam, access.getId());
						}else{
							ec = ErrorCode.VEHICLE_NOTICE_NOT_ENTER;
						}
					}else{
						ec = ErrorCode.OPERATE_ERROR;
					}
				}
			}else{
				ec = ErrorCode.NOTICE_ON_INVALID;
			}
		}else{
			ec = ErrorCode.NOTICE_NOT_AUDIT;
		}
		return ec;
	}
	

	//添加入厂门禁记录
	private ErrorCode addInfoAccessRecordApi(ApiDoorSystemSave apiParam, String noticeid, String noticecode, String businesstype) throws Exception {
		ErrorCode ec;
		AccessRecord access = new AccessRecord();
		access.setId(UUIDUtil.getId());
		GetCodeReq codeReq = new GetCodeReq();
		codeReq.setCode("ZW");
		codeReq.setCodeType(true);
		codeReq.setUserid(apiParam.getCurrUid());
		access.setCode(systemCodeService.getCode(codeReq).getData().toString());
		access.setBusinesstype(businesstype);
		access.setAccesstype("1");
		access.setNoticeid(noticeid);
		access.setNoticecode(noticecode);
		access.setEntersource("");
		access.setEntertime(DateUtil.parse(apiParam.getTime(), "yyyy-MM-dd HH:mm:ss"));
		access.setState("1");
		access.setCreator(apiParam.getCurrUid());
		access.setCreatetime(System.currentTimeMillis());
		access.setModifier(apiParam.getCurrUid());
		access.setModifytime(System.currentTimeMillis());
		if(accessRecordMapper.insertSelective(access) > 0
				&& StringUtils.equals(systemCodeService.updateCodeItem(codeReq).getCode(), ErrorCode.SYSTEM_SUCCESS.getCode())){
			ec = ErrorCode.SYSTEM_SUCCESS;
		}else{
			ec = ErrorCode.OPERATE_ERROR;
		}
		return ec;
	}
	
	//添加出厂门禁记录
	private ErrorCode addOutAccessRecordApi(ApiDoorSystemSave apiParam, String accessId) {
		ErrorCode ec;
		AccessRecord ar = new AccessRecord();
		ar.setId(accessId);
		ar.setAccesstype("2");
		ar.setOutsource("");
		ar.setOuttime(DateUtil.parse(apiParam.getTime(), "yyyy-MM-dd HH:mm:ss"));
		ar.setModifier(apiParam.getCurrUid());
		ar.setModifytime(System.currentTimeMillis());
		if(accessRecordMapper.updateByPrimaryKeySelective(ar) > 0){
			ec = ErrorCode.SYSTEM_SUCCESS;
		}else{
			ec = ErrorCode.OPERATE_ERROR;
		}
		return ec;
	}
	
	@Override
	public Result leaveFactoryCheckApi(VehicleCheckApi checkApi) {
		Result result = Result.getParamErrorResult();
		if (checkApi != null 
				&& StringUtils.isNotBlank(checkApi.getVehicleNo()) 
				&& StringUtils.isNotBlank(checkApi.getRfid())) {
			if (validateRFID(checkApi.getRfid())) {
				if (validateVehicle(checkApi.getVehicleNo(), checkApi.getRfid())) {
					ApiSalesArriveResp resp = validateHasBill(checkApi.getVehicleNo());
					if (resp != null) {
						if (!StringUtils.equals(resp.getStatus(), "3")) {
							if (StringUtils.equals(resp.getAuditstatus(), "1")) {
								if (StringUtils.equals(resp.getStatus(), "2")) {
									//result.setData(map);
									result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
									//如果业务类型为 工程车辆 且是入厂状态,可以直接出厂
								} else if(StringUtils.equals(resp.getServicetype(), "9")
										&& StringUtils.equals(resp.getStatus(), "6")){
									result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
								}else {
									result.setErrorCode(ErrorCode.VEHICLE_NOTICE_NOT_TWO_WEIGHT);
								}
							} else {
								result.setErrorCode(ErrorCode.NOTICE_NOT_AUDIT);
							}
						} else {
							result.setErrorCode(ErrorCode.NOTICE_ON_INVALID);
						}
					} else {
						result.setErrorCode(ErrorCode.VEHICLE_NOT_NOTICE);
					}
				} else {
					result.setErrorCode(ErrorCode.RFID_VEHICLE_NOT_EXIST);
				}
			} else {
				result.setErrorCode(ErrorCode.RFID_NOT_EXIST);
			}
		}
		return result;
	}
	/**
	 * @Description 入厂验证
	 * @author zhanggaohao
	 * @version 2017年3月2日 下午3:49:33
	 * @param checkApi
	 * @return
	 */
	@Override
	public Result enterFactoryCheckApi(VehicleCheckApi checkApi) {
		Result result = Result.getParamErrorResult();
		if (checkApi != null 
				&& StringUtils.isNotBlank(checkApi.getVehicleNo()) 
				&& StringUtils.isNotBlank(checkApi.getRfid())) {
			if (validateRFID(checkApi.getRfid())) {
				if (validateVehicle(checkApi.getVehicleNo(), checkApi.getRfid())) {
					ApiSalesArriveResp resp = validateHasBill(checkApi.getVehicleNo());
					if (resp != null) {
						if (!StringUtils.equals(resp.getStatus(), "3")) {
							if (StringUtils.equals(resp.getAuditstatus(), "1")) {
								if (StringUtils.equals(resp.getStatus(), "0")) {
									result.setData(resp.getServicetype());
									result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
								} else {
									result.setErrorCode(ErrorCode.VEHICLE_NOTICE_ALREADY_ENTER);
								}
							} else {
								result.setErrorCode(ErrorCode.NOTICE_NOT_AUDIT);
							}
						} else {
							result.setErrorCode(ErrorCode.NOTICE_ON_INVALID);
						}
					} else {
						result.setErrorCode(ErrorCode.VEHICLE_NOT_NOTICE);
					}
				} else {
					result.setErrorCode(ErrorCode.RFID_VEHICLE_NOT_EXIST);
				}
			} else {
				result.setErrorCode(ErrorCode.RFID_NOT_EXIST);
			}
		}
		return result;
	}
	/**
	 * @Description 验证RFID是否已注册
	 * @author zhanggaohao
	 * @version 2017年3月2日 上午9:36:57
	 * @param rfid
	 * @return
	 */
	private boolean validateRFID(String rfid) {
		boolean flag = false;
		if (StringUtils.isNotBlank(rfid)) {
			RFID bean = rfidMapper.validateRFID(rfid);
			if (bean != null) {
				flag = true;
			}
		}
		return flag;
	}
	/**
	 * @Description 验证车牌号是否与RFID已绑定
	 * @author zhanggaohao
	 * @version 2017年3月2日 上午9:48:26
	 * @param vehicleno
	 * @param rfid
	 * @return
	 */
	private boolean validateVehicle(String vehicleno, String rfid) {
		boolean flag = false;
		if (StringUtils.isNotBlank(vehicleno) && StringUtils.isNotBlank(rfid)) {
			VehicleManage bean = vehicleManageMapper.validateVehicle(vehicleno, rfid);
			if (bean != null) {
				flag = true;
			}
		}
		return flag;
	}
	/**
	 * @Description 验证是否有通知单
	 * @author zhanggaohao
	 * @version 2017年3月2日 上午9:52:35
	 * @param vehicleNo
	 * @return
	 */
	private ApiSalesArriveResp validateHasBill(String vehicleno) {
		ApiSalesArriveResp resp = new ApiSalesArriveResp();
		if (StringUtils.isNotBlank(vehicleno)) {
			PurchaseArrive purchaseArrive = hasPurchaseArrive(vehicleno);
			//判断是否有采购到货通知单
			if (purchaseArrive == null) {
				SalesArrive salesArrive = hasSalesArrive(vehicleno);
				//判断是否有销售通知单
				if (salesArrive == null) {
					//判断是否有其他业务通知单 
					OtherArrive otherArrive = hasOtherArrive(vehicleno);
					if(otherArrive == null){
						//
						resp = null;
					}else{
						resp.setStatus(otherArrive.getStatus());
						resp.setAuditstatus(otherArrive.getAuditstatus());
						resp.setServicetype(otherArrive.getBusinesstype());
					}
				} else {
					resp.setStatus(salesArrive.getStatus());
					resp.setAuditstatus(salesArrive.getAuditstatus());
					resp.setServicetype("2");
				}
			} else {
				resp.setStatus(purchaseArrive.getStatus());
				resp.setAuditstatus(purchaseArrive.getAuditstatus());
				resp.setServicetype(purchaseArrive.getType());
			}
		}
		return resp;
	}
	/**
	 * @Description 验证是否有采购到货通知单
	 * @author zhanggaohao
	 * @version 2017年3月2日 上午9:57:02
	 * @param vehicleno
	 * @return
	 */
	private PurchaseArrive hasPurchaseArrive(String vehicleno) {
		PurchaseArrive bean = null;
		if (StringUtils.isNotBlank(vehicleno)) {
			bean = purchaseArriveMapper.hasPurchaseArrive(vehicleno);
		}
		return bean;
	}
	/**
	 * @Description 验证是否有销售提货通知单
	 * @author zhanggaohao
	 * @version 2017年3月2日 上午10:31:40
	 * @param vehicleno
	 * @return
	 */
	private SalesArrive hasSalesArrive(String vehicleno) {
		SalesArrive bean = null;
		if (StringUtils.isNotBlank(vehicleno)) {
			bean = salesArriveMapper.hasPurchaseArrive(vehicleno);
		}
		return bean;
	}
	/**
	 * @Description 验证是否有其他业务通知单
	 * @author lixiaoyong
	 * @version 2017年5月6日 上午10:42:40
	 * @param vehicleno
	 * @return
	 */
	private OtherArrive hasOtherArrive(String vehicleno) {
		OtherArrive bean = null;
		if (StringUtils.isNotBlank(vehicleno)) {
			VehicleManage vehicle = vehicleManageMapper.selectByVehicleno(vehicleno);
			if(vehicle != null){
				bean = otherArriveMapper.hasOtherArrive(vehicle.getId());
			}
		}
		return bean;
	}
}