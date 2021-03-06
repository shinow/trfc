package com.tianrui.service.mapper.system.base;

import java.util.List;

import com.tianrui.api.req.system.base.SystemDataDictReq;
import com.tianrui.service.bean.system.base.SystemDataDict;

public interface SystemDataDictMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tr_system_data_dict
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tr_system_data_dict
     *
     * @mbggenerated
     */
    int insert(SystemDataDict record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tr_system_data_dict
     *
     * @mbggenerated
     */
    int insertSelective(SystemDataDict record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tr_system_data_dict
     *
     * @mbggenerated
     */
    SystemDataDict selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tr_system_data_dict
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(SystemDataDict record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tr_system_data_dict
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(SystemDataDict record);
    /**
     * 查找所有的数据字典类别
     * @param req
     * @return
     */
    List<SystemDataDict> findSystemDataDicts(SystemDataDictReq req);
    
}