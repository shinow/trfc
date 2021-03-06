package com.tianrui.api.req;

import java.io.Serializable;

import com.tianrui.smartfactory.common.constants.Constant;
import com.tianrui.smartfactory.common.utils.UUIDUtil;



public class BaseReq implements Serializable {

	private static final long serialVersionUID = 1167143490657252869L;
	
	//调用来源
	private String callSource;

	//调用时间
	private String callTime=String.valueOf(System.currentTimeMillis());
	
	//调用id
	private String callId =UUIDUtil.getId();
	
	
	/**
	 *  页数
	 */
	protected Integer pageNo=1;
	/**
	 * 每页显示数据总数
	 */
	protected Integer pageSize=10;

	public String getCallSource() {
		return callSource;
	}

	public void setCallSource(String callSource) {
		this.callSource = callSource;
	}

	public String getCallTime() {
		return callTime;
	}

	public void setCallTime(String callTime) {
		this.callTime = callTime;
	}

	public String getCallId() {
		return callId;
	}

	public void setCallId(String callId) {
		this.callId = callId;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		if( this.pageSize > Constant.MAX_PAGESIZE ){
			pageSize =Constant.MAX_PAGESIZE;
		}
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}


}
