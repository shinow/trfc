package com.tianrui.service.bean.system.auth;

import java.util.Date;
/**
 * @description 角色管理实体bean
 * @author YangZhenFu
 * @createtime 2017年3月31日 上午9:32:07
 */
public class SystemRole {
  
    private String id;
    //角色编码
    private String code;
    //角色名称
    private String name;
    //角色列表名称
    private String rolename;
    //角色分类
    private String roleType;
    //有效性  默认无效 0无效 1有效
    private Byte isvalid;
    //允许编辑 默认无效 0无效 1有效
    private Byte allowEdit;
    //允许编辑 默认无效 0无效 1有效
    private Byte allowDel;
    //说明
    private String info;
    //创建人
    private String creator;
    //创建时间
    private Long createtime;
    //最后修改人
    private String modifier;
    //最后修改时间
    private Long modifytime;
    //时间戳
    private Date utc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getName() {
        return name;
    }

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

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
    
    
}