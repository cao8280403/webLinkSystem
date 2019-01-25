package com.webLinkSystem.houtai.bean;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "visit_history", schema = "weblinksystem", catalog = "")
public class VisitHistory {
    private String guid;
    private Timestamp createTime;
    private Integer productId;
    private String requestIp;
    private String requestLocation;
    private String requestUseragent;
    private String requestMode;
    private String requestUrl;
    private String requestId;

    @Id
    @Column(name = "guid")
    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
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
    @Column(name = "product_id")
    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    @Basic
    @Column(name = "request_ip")
    public String getRequestIp() {
        return requestIp;
    }

    public void setRequestIp(String requestIp) {
        this.requestIp = requestIp;
    }

    @Basic
    @Column(name = "request_location")
    public String getRequestLocation() {
        return requestLocation;
    }

    public void setRequestLocation(String requestLocation) {
        this.requestLocation = requestLocation;
    }

    @Basic
    @Column(name = "request_useragent")
    public String getRequestUseragent() {
        return requestUseragent;
    }

    public void setRequestUseragent(String requestUseragent) {
        this.requestUseragent = requestUseragent;
    }

    @Basic
    @Column(name = "request_mode")
    public String getRequestMode() {
        return requestMode;
    }

    public void setRequestMode(String requestMode) {
        this.requestMode = requestMode;
    }

    @Basic
    @Column(name = "request_url")
    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    @Basic
    @Column(name = "request_id")
    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VisitHistory that = (VisitHistory) o;

        if (guid != null ? !guid.equals(that.guid) : that.guid != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (productId != null ? !productId.equals(that.productId) : that.productId != null) return false;
        if (requestIp != null ? !requestIp.equals(that.requestIp) : that.requestIp != null) return false;
        if (requestLocation != null ? !requestLocation.equals(that.requestLocation) : that.requestLocation != null)
            return false;
        if (requestUseragent != null ? !requestUseragent.equals(that.requestUseragent) : that.requestUseragent != null)
            return false;
        if (requestMode != null ? !requestMode.equals(that.requestMode) : that.requestMode != null) return false;
        if (requestUrl != null ? !requestUrl.equals(that.requestUrl) : that.requestUrl != null) return false;
        if (requestId != null ? !requestId.equals(that.requestId) : that.requestId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = guid != null ? guid.hashCode() : 0;
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (productId != null ? productId.hashCode() : 0);
        result = 31 * result + (requestIp != null ? requestIp.hashCode() : 0);
        result = 31 * result + (requestLocation != null ? requestLocation.hashCode() : 0);
        result = 31 * result + (requestUseragent != null ? requestUseragent.hashCode() : 0);
        result = 31 * result + (requestMode != null ? requestMode.hashCode() : 0);
        result = 31 * result + (requestUrl != null ? requestUrl.hashCode() : 0);
        result = 31 * result + (requestId != null ? requestId.hashCode() : 0);
        return result;
    }
}
