package com.tianrui.service.mapper.system.base;

import com.tianrui.service.bean.system.base.SystemCode;

public interface SystemCodeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tr_system_code
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tr_system_code
     *
     * @mbggenerated
     */
    int insert(SystemCode record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tr_system_code
     *
     * @mbggenerated
     */
    int insertSelective(SystemCode record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tr_system_code
     *
     * @mbggenerated
     */
    SystemCode selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tr_system_code
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(SystemCode record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tr_system_code
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(SystemCode record);
}