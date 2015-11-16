/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package md.vmacari.data;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import md.vmacari.messages.MessagePresentationSubtypes;

/**
 *
 * @author vmacari
 */
@DatabaseTable(tableName = "sensor")
public class Sensor {

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @DatabaseField(id = true, indexName = "primaryIndex")
    private int id;

    //private static final long serialVersionUID = 1L;
    @DatabaseField()
    private String name;

    @DatabaseField(dataType = DataType.ENUM_INTEGER)
    private MessagePresentationSubtypes type;

    @DatabaseField(columnName = "node_id")
    private int nodeId;



//    public Node getNode() {
//        return node;
//    }
//
//    public void setNode(Node node) {
//        this.node = node;
//    }

    //@DatabaseField(indexName = "primaryIndex")
    //private Integer nodeId;
//    @DatabaseField(canBeNull = false, foreign = true)
//    private Node node;


    public Sensor() {
    }
//    public Sensor(int id, Node node) {
//        this.id = id;
//        this.node= node;
//    }

    public Sensor(int id, Integer nodeId) {
        this.id = id;
        this.nodeId= nodeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MessagePresentationSubtypes getType() {
        return type;
    }

    public void setType(MessagePresentationSubtypes type) {
        this.type = type;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        //hash += node.id + id;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sensor)) {
            return false;
        }

        Sensor other = (Sensor) object;
        return this.id == other.id;
    }

    @Override
    public String toString() {
        return "md.vmacari.comm.Sensor[ id = " + id + ",  nodeId = " + nodeId + "]";

    }

    public int getNodeId() {
        return nodeId;
    }

    public void setNodeId(int nodeId) {
        this.nodeId = nodeId;
    }
}
