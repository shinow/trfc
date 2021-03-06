package com.tianrui.api.resp.quality.sales;

import java.util.List;
import java.util.Map;
import com.tianrui.api.resp.BaseResp;

/**
 * 销售化验报告详情返回数据
  * <p>Title:AssayReportDetailResp </p>
  * <p>Description:TODO </p>
  * <p>Company: </p> 
  * @author   yangbobo
  * @date   2017年12月22日 下午4:21:57
 */
public class AssayReportDetailResp extends BaseResp {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 4626192825694528730L;
	//物料名称
	private String materName;
	//客户名称
	private String customer;
	//强度等级
	private String gradeintensity;
	//填表时间
	private String createTime;
	//出厂编码
	private String factoryCode;
	//出厂日期	
	private String factoryTime;
	//车牌号码
	private String vehicleNo;
	//提货量
	private String number;
	//3天抗压平均值
	private String avgSist3;
	//28天抗压平均值
	private String avgSist28;
	//3天抗折平均值
	private String avgFlexural3;
	//28天抗折平均值
	private String avgFlexural28;
	//检验项目集合
	private Map<String, Object> qualityRs;
	//3天抗压
	private List <String> sist3;
	//28天抗压
	private List <String> sist28;
	//3天抗折
	private List <String> flexural3;
	//28天抗折
	private List <String> flexural28;
	public String getMaterName() {
		return materName;
	}
	public void setMaterName(String materName) {
		this.materName = materName;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getFactoryCode() {
		return factoryCode;
	}
	public void setFactoryCode(String factoryCode) {
		this.factoryCode = factoryCode;
	}
	public String getFactoryTime() {
		return factoryTime;
	}
	public void setFactoryTime(String factoryTime) {
		this.factoryTime = factoryTime;
	}
	public String getVehicleNo() {
		return vehicleNo;
	}
	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getAvgSist3() {
		return avgSist3;
	}
	public void setAvgSist3(String avgSist3) {
		this.avgSist3 = avgSist3;
	}
	public String getAvgSist28() {
		return avgSist28;
	}
	public void setAvgSist28(String avgSist28) {
		this.avgSist28 = avgSist28;
	}
	public String getAvgFlexural3() {
		return avgFlexural3;
	}
	public void setAvgFlexural3(String avgFlexural3) {
		this.avgFlexural3 = avgFlexural3;
	}
	public String getAvgFlexural28() {
		return avgFlexural28;
	}
	public void setAvgFlexural28(String avgFlexural28) {
		this.avgFlexural28 = avgFlexural28;
	}
	public Map<String, Object> getQualityRs() {
		return qualityRs;
	}
	public void setQualityRs(Map<String, Object> qualityRs) {
		this.qualityRs = qualityRs;
	}
	
	public List<String> getSist3() {
		return sist3;
	}
	public void setSist3(List<String> sist3) {
		this.sist3 = sist3;
	}
	public List<String> getSist28() {
		return sist28;
	}
	public void setSist28(List<String> sist28) {
		this.sist28 = sist28;
	}
	public List<String> getFlexural3() {
		return flexural3;
	}
	public void setFlexural3(List<String> flexural3) {
		this.flexural3 = flexural3;
	}
	public List<String> getFlexural28() {
		return flexural28;
	}
	public void setFlexural28(List<String> flexural28) {
		this.flexural28 = flexural28;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public String getGradeintensity() {
		return gradeintensity;
	}
	public void setGradeintensity(String gradeintensity) {
		this.gradeintensity = gradeintensity;
	}
	
	
	
}
