package com.webLinkSystem.houtai.bean;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "web_link", schema = "weblinksystem", catalog = "")
public class WebLink {
    private int guid;
    private Timestamp createTime;
    private Timestamp updateTime;
    private String sourceWebLink;
    private String transferedWebLink;
    private String productName;
    private Integer state;
    private String pv;
    private String uv;

    @Id
    @Column(name = "guid")
    public int getGuid() {
        return guid;
    }

    public void setGuid(int guid) {
        this.guid = guid;
    }

    @Basic
    @Column(name = "create_time")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "update_time")
    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    @Basic
    @Column(name = "source_web_link")
    public String getSourceWebLink() {
        return sourceWebLink;
    }

    public void setSourceWebLink(String sourceWebLink) {
        this.sourceWebLink = sourceWebLink;
    }

    @Basic
    @Column(name = "transfered_web_link")
    public String getTransferedWebLink() {
        return transferedWebLink;
    }

    public void setTransferedWebLink(String transferedWebLink) {
        this.transferedWebLink = transferedWebLink;
    }

    @Basic
    @Column(name = "product_name")
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Basic
    @Column(name = "state")
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Basic
    @Column(name = "pv")
    public String getPv() {
        return pv;
    }

    public void setPv(String pv) {
        this.pv = pv;
    }

    @Basic
    @Column(name = "uv")
    public String getUv() {
        return uv;
    }

    public void setUv(String uv) {
        this.uv = uv;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WebLink webLink = (WebLink) o;

        if (guid != webLink.guid) return false;
        if (createTime != null ? !createTime.equals(webLink.createTime) : webLink.createTime != null) return false;
        if (updateTime != null ? !updateTime.equals(webLink.updateTime) : webLink.updateTime != null) return false;
        if (sourceWebLink != null ? !sourceWebLink.equals(webLink.sourceWebLink) : webLink.sourceWebLink != null)
            return false;
        if (transferedWebLink != null ? !transferedWebLink.equals(webLink.transferedWebLink) : webLink.transferedWebLink != null)
            return false;
        if (productName != null ? !productName.equals(webLink.productName) : webLink.productName != null) return false;
        if (state != null ? !state.equals(webLink.state) : webLink.state != null) return false;
        if (pv != null ? !pv.equals(webLink.pv) : webLink.pv != null) return false;
        if (uv != null ? !uv.equals(webLink.uv) : webLink.uv != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = guid;
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        result = 31 * result + (sourceWebLink != null ? sourceWebLink.hashCode() : 0);
        result = 31 * result + (transferedWebLink != null ? transferedWebLink.hashCode() : 0);
        result = 31 * result + (productName != null ? productName.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (pv != null ? pv.hashCode() : 0);
        result = 31 * result + (uv != null ? uv.hashCode() : 0);
        return result;
    }
}
