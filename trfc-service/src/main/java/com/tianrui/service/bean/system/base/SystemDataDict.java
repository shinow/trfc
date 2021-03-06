package com.tianrui.service.bean.system.base;

import java.util.Date;

public class SystemDataDict {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tr_system_data_dict.id
     * 系统数据字典Id
     * @mbggenerated
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tr_system_data_dict.code
     * 系统数据字典编号
     * @mbggenerated
     */
    private String code;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tr_system_data_dict.name
     * 系统数据字典名称
     * @mbggenerated
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tr_system_data_dict.type
     * 系统数据字典分类
     * @mbggenerated
     */
    private String type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tr_system_data_dict.order_by
     * 系统数据字典排序
     * @mbggenerated
     */
    private Integer orderBy;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tr_system_data_dict.info
     * 系统数据字典说明
     * @mbggenerated
     */
    private String info;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tr_system_data_dict.creator
     * 系统数据字典创建人
     * @mbggenerated
     */
    private String creator;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tr_system_data_dict.createtime
     * 系统数据字典创建时间
     * @mbggenerated
     */
    private Long createtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tr_system_data_dict.modifier
     * 系统数据字典最后修改人
     * @mbggenerated
     */
    private String modifier;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tr_system_data_dict.modifytime
     * 系统数据字典最后修改时间
     * @mbggenerated
     */
    private Long modifytime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tr_system_data_dict.utc
     * 系统数据字典时间戳
     * @mbggenerated
     */
    private Date utc;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tr_system_data_dict.id
     *
     * @return the value of tr_system_data_dict.id
     *
     * @mbggenerated
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tr_system_data_dict.id
     *
     * @param id the value for tr_system_data_dict.id
     *
     * @mbggenerated
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tr_system_data_dict.code
     *
     * @return the value of tr_system_data_dict.code
     *
     * @mbggenerated
     */
    public String getCode() {
        return code;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tr_system_data_dict.code
     *
     * @param code the value for tr_system_data_dict.code
     *
     * @mbggenerated
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tr_system_data_dict.name
     *
     * @return the value of tr_system_data_dict.name
     *
     * @mbggenerated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tr_system_data_dict.name
     *
     * @param name the value for tr_system_data_dict.name
     *
     * @mbggenerated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tr_system_data_dict.type
     *
     * @return the value of tr_system_data_dict.type
     *
     * @mbggenerated
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tr_system_data_dict.type
     *
     * @param type the value for tr_system_data_dict.type
     *
     * @mbggenerated
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tr_system_data_dict.order_by
     *
     * @return the value of tr_system_data_dict.order_by
     *
     * @mbggenerated
     */
    public Integer getOrderBy() {
        return orderBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tr_system_data_dict.order_by
     *
     * @param orderBy the value for tr_system_data_dict.order_by
     *
     * @mbggenerated
     */
    public void setOrderBy(Integer orderBy) {
        this.orderBy = orderBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tr_system_data_dict.info
     *
     * @return the value of tr_system_data_dict.info
     *
     * @mbggenerated
     */
    public String getInfo() {
        return info;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tr_system_data_dict.info
     *
     * @param info the value for tr_system_data_dict.info
     *
     * @mbggenerated
     */
    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tr_system_data_dict.creator
     *
     * @return the value of tr_system_data_dict.creator
     *
     * @mbggenerated
     */
    public String getCreator() {
        return creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tr_system_data_dict.creator
     *
     * @param creator the value for tr_system_data_dict.creator
     *
     * @mbggenerated
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tr_system_data_dict.createtime
     *
     * @return the value of tr_system_data_dict.createtime
     *
     * @mbggenerated
     */
    public Long getCreatetime() {
        return createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tr_system_data_dict.createtime
     *
     * @param createtime the value for tr_system_data_dict.createtime
     *
     * @mbggenerated
     */
    public void setCreatetime(Long createtime) {
        this.createtime = createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tr_system_data_dict.modifier
     *
     * @return the value of tr_system_data_dict.modifier
     *
     * @mbggenerated
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tr_system_data_dict.modifier
     *
     * @param modifier the value for tr_system_data_dict.modifier
     *
     * @mbggenerated
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tr_system_data_dict.modifytime
     *
     * @return the value of tr_system_data_dict.modifytime
     *
     * @mbggenerated
     */
    public Long getModifytime() {
        return modifytime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tr_system_data_dict.modifytime
     *
     * @param modifytime the value for tr_system_data_dict.modifytime
     *
     * @mbggenerated
     */
    public void setModifytime(Long modifytime) {
        this.modifytime = modifytime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tr_system_data_dict.utc
     *
     * @return the value of tr_system_data_dict.utc
     *
     * @mbggenerated
     */
    public Date getUtc() {
        return utc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tr_system_data_dict.utc
     *
     * @param utc the value for tr_system_data_dict.utc
     *
     * @mbggenerated
     */
    public void setUtc(Date utc) {
        this.utc = utc;
    }
}