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
@DatabaseTable(tableName = "data")
public class Data   {

    @DatabaseField(id = true, indexName = "primaryIndex", columnName = "id")
    private int id;

//    @DatabaseField(canBeNull = false, foreign = true)
//    private Node node;

//    @DatabaseField(canBeNull = false, foreign = true)
//    private Sensor sensor;

    @DatabaseField(columnName = "node_id")
    private Integer nodeId;

    @DatabaseField(columnName = "sensor_id")
    private Integer sensorId;

    @DatabaseField(columnName = "data_type")
    private String dataType;

    @DatabaseField()
    private String data;

    @DatabaseField()
    private Date time;

    public Data() {
    }

    public Data(Integer id) {
        this.id = id;
    }

    public Data(Integer id, String dataType, String data, Date time) {
        this.id = id;
        this.dataType = dataType;
        this.data = data;
        this.time = time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
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
        if (!(object instanceof Data)) {
            return false;
        }
        Data other = (Data) object;
        return this.id == other.id;
    }

    @Override
    public String toString() {
        return "md.vmacari.data.Data[ id=" + id + " ]";
    }

    public Integer getNodeId() {
        return nodeId;
    }

    public void setNodeId(Integer nodeId) {
        this.nodeId = nodeId;
    }

    public Integer getSensorId() {
        return sensorId;
    }

    public void setSensorId(Integer sensorId) {
        this.sensorId = sensorId;
    }

//    public Node getNode() {
//        return node;
//    }
//
//    public void setNode(Node node) {
//        this.node = node;
//    }
//
//    public Sensor getSensor() {
//        return sensor;
//    }
//
//    public void setSensor(Sensor sensor) {
//        this.sensor = sensor;
//    }
}
