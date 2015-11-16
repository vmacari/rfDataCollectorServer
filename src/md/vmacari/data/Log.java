/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package md.vmacari.data;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**s
 *
 * @author vmacari
 */
@DatabaseTable( tableName = "log")
public class Log {

    @DatabaseField(id = true, columnName = "id")
    private int id;

    @DatabaseField(columnName = "message", canBeNull = false)
    private String message;

    @DatabaseField(columnName = "time", canBeNull = false)
    private Date time;

    public Log() {
    }

    public Log(Integer id) {
        this.id = id;
    }

    public Log(Integer id, String message, Date time) {
        this.id = id;
        this.message = message;
        this.time = time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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
        if (!(object instanceof Log)) {
            return false;
        }
        Log other = (Log) object;
        return this.id == other.id;
    }

    @Override
    public String toString() {
        return "md.vmacari.data.Log[ id=" + id + " ]";
    }
    
}
