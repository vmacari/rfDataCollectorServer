/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package md.vmacari.data;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 *
 * @author vmacari
 */

@DatabaseTable(tableName = "node")
public class Node  {

    @DatabaseField()
    private String name;
    @DatabaseField()
    private String version;
    @DatabaseField(columnName = "last_update_time")
    private Date lastUpdateTime;
    @DatabaseField(columnName = "fw_version")
    private String fwVersion;
    @DatabaseField(columnName = "battery_level")
    private Short batteryLevel;
    @DatabaseField()
    private String configuration;
    @DatabaseField(columnName = "is_rebooting")
    private Boolean isRebooting;
    @DatabaseField()
    private String protocol;

    @DatabaseField(id = true, indexName = "primaryIndex")
    private int id;

    public Node() {
    }

    public Node(Integer id) {
        this.id = id;
    }

    public Node(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getFwVersion() {
        return fwVersion;
    }

    public void setFwVersion(String fwVersion) {
        this.fwVersion = fwVersion;
    }

    public Short getBatteryLevel() {
        return batteryLevel;
    }

    public void setBatteryLevel(Short batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    public String getConfiguration() {
        return configuration;
    }

    public void setConfiguration(String configuration) {
        this.configuration = configuration;
    }

    public Boolean getIsRebooting() {
        return isRebooting;
    }

    public void setIsRebooting(Boolean isRebooting) {
        this.isRebooting = isRebooting;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += id;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Node)) {
            return false;
        }
        Node other = (Node) object;
        return this.id  == other.id;
    }

    @Override
    public String toString() {
        return "md.vmacari.comm.Node[ id=" + id + " ]";
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }
}
