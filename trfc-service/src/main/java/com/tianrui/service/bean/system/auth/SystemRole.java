package com.tianrui.service.bean.system.auth;

import java.util.Date;

public class SystemRole {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tr_system_role.id
     *
     * @mbggenerated
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tr_system_role.code
     *
     * @mbggenerated
     */
    private String code;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tr_system_role.name
     *
     * @mbggenerated
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tr_system_role.role_type
     *
     * @mbggenerated
     */
    private String roleType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tr_system_role.isvalid
     *
     * @mbggenerated
     */
    private Byte isvalid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tr_system_role.allow_edit
     *
     * @mbggenerated
     */
    private Byte allowEdit;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tr_system_role.allow_del
     *
     * @mbggenerated
     */
    private Byte allowDel;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tr_system_role.info
     *
     * @mbggenerated
     */
    private String info;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tr_system_role.creator
     *
     * @mbggenerated
     */
    private String creator;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tr_system_role.createtime
     *
     * @mbggenerated
     */
    private Long createtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tr_system_role.modifier
     *
     * @mbggenerated
     */
    private String modifier;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tr_system_role.modifytime
     *
     * @mbggenerated
     */
    private Long modifytime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tr_system_role.utc
     *
     * @mbggenerated
     */
    private Date utc;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tr_system_role.id
     *
     * @return the value of tr_system_role.id
     *
     * @mbggenerated
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tr_system_role.id
     *
     * @param id the value for tr_system_role.id
     *
     * @mbggenerated
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tr_system_role.code
     *
     * @return the value of tr_system_role.code
     *
     * @mbggenerated
     */
    public String getCode() {
        return code;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tr_system_role.code
     *
     * @param code the value for tr_system_role.code
     *
     * @mbggenerated
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tr_system_role.name
     *
     * @return the value of tr_system_role.name
     *
     * @mbggenerated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tr_system_role.name
     *
     * @param name the value for tr_system_role.name
     *
     * @mbggenerated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tr_system_role.role_type
     *
     * @return the value of tr_system_role.role_type
     *
     * @mbggenerated
     */
    public String getRoleType() {
        return roleType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tr_system_role.role_type
     *
     * @param roleType the value for tr_system_role.role_type
     *
     * @mbggenerated
     */
    public void setRoleType(String roleType) {
        this.roleType = roleType == null ? null : roleType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tr_system_role.isvalid
     *
     * @return the value of tr_system_role.isvalid
     *
     * @mbggenerated
     */
    public Byte getIsvalid() {
        return isvalid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tr_system_role.isvalid
     *
     * @param isvalid the value for tr_system_role.isvalid
     *
     * @mbggenerated
     */
    public void setIsvalid(Byte isvalid) {
        this.isvalid = isvalid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tr_system_role.allow_edit
     *
     * @return the value of tr_system_role.allow_edit
     *
     * @mbggenerated
     */
    public Byte getAllowEdit() {
        return allowEdit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tr_system_role.allow_edit
     *
     * @param allowEdit the value for tr_system_role.allow_edit
     *
     * @mbggenerated
     */
    public void setAllowEdit(Byte allowEdit) {
        this.allowEdit = allowEdit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tr_system_role.allow_del
     *
     * @return the value of tr_system_role.allow_del
     *
     * @mbggenerated
     */
    public Byte getAllowDel() {
        return allowDel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tr_system_role.allow_del
     *
     * @param allowDel the value for tr_system_role.allow_del
     *
     * @mbggenerated
     */
    public void setAllowDel(Byte allowDel) {
        this.allowDel = allowDel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tr_system_role.info
     *
     * @return the value of tr_system_role.info
     *
     * @mbggenerated
     */
    public String getInfo() {
        return info;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tr_system_role.info
     *
     * @param info the value for tr_system_role.info
     *
     * @mbggenerated
     */
    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tr_system_role.creator
     *
     * @return the value of tr_system_role.creator
     *
     * @mbggenerated
     */
    public String getCreator() {
        return creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tr_system_role.creator
     *
     * @param creator the value for tr_system_role.creator
     *
     * @mbggenerated
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tr_system_role.createtime
     *
     * @return the value of tr_system_role.createtime
     *
     * @mbggenerated
     */
    public Long getCreatetime() {
        return createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tr_system_role.createtime
     *
     * @param createtime the value for tr_system_role.createtime
     *
     * @mbggenerated
     */
    public void setCreatetime(Long createtime) {
        this.createtime = createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tr_system_role.modifier
     *
     * @return the value of tr_system_role.modifier
     *
     * @mbggenerated
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tr_system_role.modifier
     *
     * @param modifier the value for tr_system_role.modifier
     *
     * @mbggenerated
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tr_system_role.modifytime
     *
     * @return the value of tr_system_role.modifytime
     *
     * @mbggenerated
     */
    public Long getModifytime() {
        return modifytime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tr_system_role.modifytime
     *
     * @param modifytime the value for tr_system_role.modifytime
     *
     * @mbggenerated
     */
    public void setModifytime(Long modifytime) {
        this.modifytime = modifytime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tr_system_role.utc
     *
     * @return the value of tr_system_role.utc
     *
     * @mbggenerated
     */
    public Date getUtc() {
        return utc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tr_system_role.utc
     *
     * @param utc the value for tr_system_role.utc
     *
     * @mbggenerated
     */
    public void setUtc(Date utc) {
        this.utc = utc;
    }
}