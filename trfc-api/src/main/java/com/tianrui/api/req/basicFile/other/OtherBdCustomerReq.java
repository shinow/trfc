package com.tianrui.api.req.basicFile.other;

import com.tianrui.api.req.BaseReq;

public class OtherBdCustomerReq extends BaseReq{
    /**
	 * 
	 */
	private static final long serialVersionUID = 7372462579491521052L;

	/**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tr_other_bd_customer.id
     *	客户ID
     * @mbggenerated
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tr_other_bd_customer.code
     *	客户编码
     * @mbggenerated
     */
    private String code;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tr_other_bd_customer.innercode
     *	客户内码
     * @mbggenerated
     */
    private String innercode;
    /**
     * (模糊查询)内码
     */
    private String innercodelike;
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tr_other_bd_customer.name
     *	客户名称
     * @mbggenerated
     */
    private String name;
    /**
     * (模糊查询)名称
     */
    private String namelike;
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tr_other_bd_customer.info
     *	客户信息
     * @mbggenerated
     */
    private String info;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tr_other_bd_customer.isvalid
     *	有效性
     * @mbggenerated
     */
    private Byte isvalid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tr_other_bd_customer.orgid
     *	组织ID
     * @mbggenerated
     */
    private String orgid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tr_other_bd_customer.orgname
     *	组织名称
     * @mbggenerated
     */
    private String orgname;
    /**
     * (模糊查询)组织名称
     */
    private String orgnamelike;
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tr_other_bd_customer.remark
     *	备注
     * @mbggenerated
     */
    private String remark;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tr_other_bd_customer.creator
     *	创建者
     * @mbggenerated
     */
    private String creator;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tr_other_bd_customer.modifier
     *	修改者
     * @mbggenerated
     */
    private String modifier;

   /**
    * 分页查询开始位置
    */
    private int start;
    /**
     * 分页查询数据量
     */
    private int limit;
    /**
     * 排序条件
     */
    private String ordering;
    /**
     * 排序顺序
     */
    private String sorting;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getInnercode() {
		return innercode;
	}
	public void setInnercode(String innercode) {
		this.innercode = innercode;
	}
	public String getInnercodelike() {
		return innercodelike;
	}
	public void setInnercodelike(String innercodelike) {
		this.innercodelike = innercodelike;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNamelike() {
		return namelike;
	}
	public void setNamelike(String namelike) {
		this.namelike = namelike;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public Byte getIsvalid() {
		return isvalid;
	}
	public void setIsvalid(Byte isvalid) {
		this.isvalid = isvalid;
	}
	public String getOrgid() {
		return orgid;
	}
	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}
	public String getOrgname() {
		return orgname;
	}
	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}
	public String getOrgnamelike() {
		return orgnamelike;
	}
	public void setOrgnamelike(String orgnamelike) {
		this.orgnamelike = orgnamelike;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getModifier() {
		return modifier;
	}
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public String getOrdering() {
		return ordering;
	}
	public void setOrdering(String ordering) {
		this.ordering = ordering;
	}
	public String getSorting() {
		return sorting;
	}
	public void setSorting(String sorting) {
		this.sorting = sorting;
	}
    
    
}