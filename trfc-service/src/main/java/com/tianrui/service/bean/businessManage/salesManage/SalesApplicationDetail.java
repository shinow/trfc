package com.tianrui.service.bean.businessManage.salesManage;

public class SalesApplicationDetail {
    private String id;

    private String salesid;

    private String materielid;

    private String materielname;

    private String warehouseid;

    private String warehousename;

    private Double salessum;

    private Double taxprice;

    private Double untaxprice;

    private Double taxrate;

    private String remarks;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getSalesid() {
        return salesid;
    }

    public void setSalesid(String salesid) {
        this.salesid = salesid == null ? null : salesid.trim();
    }

    public String getMaterielid() {
        return materielid;
    }

    public void setMaterielid(String materielid) {
        this.materielid = materielid == null ? null : materielid.trim();
    }

    public String getMaterielname() {
        return materielname;
    }

    public void setMaterielname(String materielname) {
        this.materielname = materielname == null ? null : materielname.trim();
    }

    public String getWarehouseid() {
        return warehouseid;
    }

    public void setWarehouseid(String warehouseid) {
        this.warehouseid = warehouseid == null ? null : warehouseid.trim();
    }

    public String getWarehousename() {
        return warehousename;
    }

    public void setWarehousename(String warehousename) {
        this.warehousename = warehousename == null ? null : warehousename.trim();
    }

    public Double getSalessum() {
        return salessum;
    }

    public void setSalessum(Double salessum) {
        this.salessum = salessum;
    }

    public Double getTaxprice() {
        return taxprice;
    }

    public void setTaxprice(Double taxprice) {
        this.taxprice = taxprice;
    }

    public Double getUntaxprice() {
        return untaxprice;
    }

    public void setUntaxprice(Double untaxprice) {
        this.untaxprice = untaxprice;
    }

    public Double getTaxrate() {
        return taxrate;
    }

    public void setTaxrate(Double taxrate) {
        this.taxrate = taxrate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }
}