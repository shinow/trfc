package com.tianrui.service.bean.system.auth;

import java.util.Date;

public class SystemUser {
	
    private String id;
    //用户编码
    private String code;
    //用户名称
    private String name;
    //登录账户
    private String account;
    //密码
    private String password;
    //所属组织
    private String orgid;
    //来源  	1 联网  0脱机  
    private String source;
    //是否有效 0 无效  1有效
    private Byte isvalid;
    //登录次数
    private Integer logincount;
    //最后登录时间
    private Long lastLogintime;
    //创建人
    private String creator;

    private Long createtime;

    private String modifier;

    private Long modifytime;
    //时间戳
    private Date utc;

    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tr_system_user.id
     *
     * @param id the value for tr_system_user.id
     *
     * @mbggenerated
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tr_system_user.code
     *
     * @return the value of tr_system_user.code
     *
     * @mbggenerated
     */
    public String getCode() {
        return code;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tr_system_user.code
     *
     * @param code the value for tr_system_user.code
     *
     * @mbggenerated
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tr_system_user.name
     *
     * @return the value of tr_system_user.name
     *
     * @mbggenerated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tr_system_user.name
     *
     * @param name the value for tr_system_user.name
     *
     * @mbggenerated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tr_system_user.account
     *
     * @return the value of tr_system_user.account
     *
     * @mbggenerated
     */
    public String getAccount() {
        return account;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tr_system_user.account
     *
     * @param account the value for tr_system_user.account
     *
     * @mbggenerated
     */
    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tr_system_user.password
     *
     * @return the value of tr_system_user.password
     *
     * @mbggenerated
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tr_system_user.password
     *
     * @param password the value for tr_system_user.password
     *
     * @mbggenerated
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tr_system_user.orgid
     *
     * @return the value of tr_system_user.orgid
     *
     * @mbggenerated
     */
    public String getOrgid() {
        return orgid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tr_system_user.orgid
     *
     * @param orgid the value for tr_system_user.orgid
     *
     * @mbggenerated
     */
    public void setOrgid(String orgid) {
        this.orgid = orgid == null ? null : orgid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tr_system_user.source
     *
     * @return the value of tr_system_user.source
     *
     * @mbggenerated
     */
    public String getSource() {
        return source;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tr_system_user.source
     *
     * @param source the value for tr_system_user.source
     *
     * @mbggenerated
     */
    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tr_system_user.isvalid
     *
     * @return the value of tr_system_user.isvalid
     *
     * @mbggenerated
     */
    public Byte getIsvalid() {
        return isvalid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tr_system_user.isvalid
     *
     * @param isvalid the value for tr_system_user.isvalid
     *
     * @mbggenerated
     */
    public void setIsvalid(Byte isvalid) {
        this.isvalid = isvalid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tr_system_user.logincount
     *
     * @return the value of tr_system_user.logincount
     *
     * @mbggenerated
     */
    public Integer getLogincount() {
        return logincount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tr_system_user.logincount
     *
     * @param logincount the value for tr_system_user.logincount
     *
     * @mbggenerated
     */
    public void setLogincount(Integer logincount) {
        this.logincount = logincount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tr_system_user.last_logintime
     *
     * @return the value of tr_system_user.last_logintime
     *
     * @mbggenerated
     */
    public Long getLastLogintime() {
        return lastLogintime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tr_system_user.last_logintime
     *
     * @param lastLogintime the value for tr_system_user.last_logintime
     *
     * @mbggenerated
     */
    public void setLastLogintime(Long lastLogintime) {
        this.lastLogintime = lastLogintime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tr_system_user.creator
     *
     * @return the value of tr_system_user.creator
     *
     * @mbggenerated
     */
    public String getCreator() {
        return creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tr_system_user.creator
     *
     * @param creator the value for tr_system_user.creator
     *
     * @mbggenerated
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tr_system_user.createtime
     *
     * @return the value of tr_system_user.createtime
     *
     * @mbggenerated
     */
    public Long getCreatetime() {
        return createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tr_system_user.createtime
     *
     * @param createtime the value for tr_system_user.createtime
     *
     * @mbggenerated
     */
    public void setCreatetime(Long createtime) {
        this.createtime = createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tr_system_user.modifier
     *
     * @return the value of tr_system_user.modifier
     *
     * @mbggenerated
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tr_system_user.modifier
     *
     * @param modifier the value for tr_system_user.modifier
     *
     * @mbggenerated
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tr_system_user.modifytime
     *
     * @return the value of tr_system_user.modifytime
     *
     * @mbggenerated
     */
    public Long getModifytime() {
        return modifytime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tr_system_user.modifytime
     *
     * @param modifytime the value for tr_system_user.modifytime
     *
     * @mbggenerated
     */
    public void setModifytime(Long modifytime) {
        this.modifytime = modifytime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tr_system_user.utc
     *
     * @return the value of tr_system_user.utc
     *
     * @mbggenerated
     */
    public Date getUtc() {
        return utc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tr_system_user.utc
     *
     * @param utc the value for tr_system_user.utc
     *
     * @mbggenerated
     */
    public void setUtc(Date utc) {
        this.utc = utc;
    }
}