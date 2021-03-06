package com.tianrui.service.mapper.basicFile.measure;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tianrui.api.req.android.SearchKeyParam;
import com.tianrui.api.req.basicFile.measure.DriverManageQuery;
import com.tianrui.api.req.businessManage.app.AppQueryReq;
import com.tianrui.api.resp.android.SearchListVo;
import com.tianrui.api.resp.businessManage.app.AppDriverResp;
import com.tianrui.service.bean.basicFile.measure.DriverManage;

public interface DriverManageMapper {
    int deleteByPrimaryKey(String id);

    int insert(DriverManage record);

    int insertSelective(DriverManage record);

    DriverManage selectByPrimaryKey(String id);
    
    List<DriverManage> selectSelective(DriverManage record);

    int updateByPrimaryKeySelective(DriverManage record);

    int updateByPrimaryKey(DriverManage record);

	long findDriverPageCount(DriverManageQuery query);

	List<DriverManage> findDriverPage(DriverManageQuery query);

    List<DriverManage> autoCompleteSearch(@Param("likeName")String likeName);

	long appQueryDriverPageCount(AppQueryReq req);

	List<AppDriverResp> appQueryDriverPage(AppQueryReq req);

	List<SearchListVo> appAutoCompleteSearch(SearchKeyParam param);
}