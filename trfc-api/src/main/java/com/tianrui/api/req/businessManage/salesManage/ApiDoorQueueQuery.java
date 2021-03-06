package com.tianrui.api.req.businessManage.salesManage;

import com.tianrui.api.req.BaseReq;
/**
 * 小票号
 * @author zhanggaohao
 * @createtime 2017年1月9日 下午4:24:40
 */
public class ApiDoorQueueQuery extends BaseReq {

	private static final long serialVersionUID = 2459911624693685605L;
	//物料id
	private String materiel;
	//物料包装类型（1：袋装，2：散装）
	private String packagetype;
	//车牌号
	private String vehicleno;
	//RFID
	private String rfid;
	//通知单号
	private String noticecode;
	
	private String currUid;

	public String getMateriel() {
		return materiel;
	}

	public void setMateriel(String materiel) {
		this.materiel = materiel;
	}

	public String getPackagetype() {
		return packagetype;
	}

	public void setPackagetype(String packagetype) {
		this.packagetype = packagetype;
	}

	public String getVehicleno() {
		return vehicleno;
	}

	public void setVehicleno(String vehicleno) {
		this.vehicleno = vehicleno;
	}

	public String getRfid() {
		return rfid;
	}

	public void setRfid(String rfid) {
		this.rfid = rfid;
	}

	public String getNoticecode() {
		return noticecode;
	}

	public void setNoticecode(String noticecode) {
		this.noticecode = noticecode;
	}

	public String getCurrUid() {
		return currUid;
	}

	public void setCurrUid(String currUid) {
		this.currUid = currUid;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
