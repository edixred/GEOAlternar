/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidad;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author giiee
 */
@Embeddable
public class MapsBiomassPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "grid450_id", nullable = false)
    private int grid450Id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "tag_time", nullable = false, length = 2147483647)
    private String tagTime;

    public MapsBiomassPK() {
    }

    public MapsBiomassPK(int grid450Id, String tagTime) {
        this.grid450Id = grid450Id;
        this.tagTime = tagTime;
    }

    public int getGrid450Id() {
        return grid450Id;
    }

    public void setGrid450Id(int grid450Id) {
        this.grid450Id = grid450Id;
    }

    public String getTagTime() {
        return tagTime;
    }

    public void setTagTime(String tagTime) {
        this.tagTime = tagTime;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) grid450Id;
        hash += (tagTime != null ? tagTime.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MapsBiomassPK)) {
            return false;
        }
        MapsBiomassPK other = (MapsBiomassPK) object;
        if (this.grid450Id != other.grid450Id) {
            return false;
        }
        if ((this.tagTime == null && other.tagTime != null) || (this.tagTime != null && !this.tagTime.equals(other.tagTime))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidad.MapsBiomassPK[ grid450Id=" + grid450Id + ", tagTime=" + tagTime + " ]";
    }
    
}
