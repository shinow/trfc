package com.tianrui.api.req.businessManage.financeManage;

import com.tianrui.api.req.BaseReq;

public class CustomerRemainderQuery extends BaseReq {
     
	private static final long serialVersionUID = 800367509390000676L;
	
    private String id;
    //   组织名称
    private String orgName;
    //   组织id
    private String orgId;
    //  客户名称
    private String customerName;
    //  客户id
    private String customerId;
    //  信用额度
    private Double nlimitmny;
    //  信用占用
    private Double nengrossmny;
    //  信用余额
    private Double nbalancemny;
    //  币种
    private String corigcurrencyid;
    //  创建人
    private String creator;
    //  创建日期
    private Long createtime;
    //  修改人
    private String modifier;
    //   修改时间
    private Long modifytime;
    //  备注
    private String remark;
    //  NC同步时间
    private Long utc;
    //  币种名称
    private String corigcurrencyName;
    //  客户编码
    private String customerCode;    
    //  状态
    private String status;   
    //开始时间
    private Long starttime;
    //结束时间
    private Long endtime;
    //起始页
    private Integer start;
    //条数
    private Integer limit;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public Double getNlimitmny() {
		return nlimitmny;
	}
	public void setNlimitmny(Double nlimitmny) {
		this.nlimitmny = nlimitmny;
	}
	public Double getNengrossmny() {
		return nengrossmny;
	}
	public void setNengrossmny(Double nengrossmny) {
		this.nengrossmny = nengrossmny;
	}
	public Double getNbalancemny() {
		return nbalancemny;
	}
	public void setNbalancemny(Double nbalancemny) {
		this.nbalancemny = nbalancemny;
	}
	public String getCorigcurrencyid() {
		return corigcurrencyid;
	}
	public void setCorigcurrencyid(String corigcurrencyid) {
		this.corigcurrencyid = corigcurrencyid;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public Long getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Long createtime) {
		this.createtime = createtime;
	}
	public String getModifier() {
		return modifier;
	}
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	public Long getModifytime() {
		return modifytime;
	}
	public void setModifytime(Long modifytime) {
		this.modifytime = modifytime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Long getUtc() {
		return utc;
	}
	public void setUtc(Long utc) {
		this.utc = utc;
	}
	public String getCorigcurrencyName() {
		return corigcurrencyName;
	}
	public void setCorigcurrencyName(String corigcurrencyName) {
		this.corigcurrencyName = corigcurrencyName;
	}
	public String getCustomerCode() {
		return customerCode;
	}
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getStarttime() {
		return starttime;
	}
	public void setStarttime(Long starttime) {
		this.starttime = starttime;
	}
	public Long getEndtime() {
		return endtime;
	}
	public void setEndtime(Long endtime) {
		this.endtime = endtime;
	}
	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
  
}