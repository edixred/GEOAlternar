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
 * @author edixred
 */
@Embeddable
public class MapsPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "latitude", nullable = false)
    private int latitude;
    @Basic(optional = false)
    @NotNull
    @Column(name = "longitude", nullable = false)
    private int longitude;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "tag_time", nullable = false, length = 2147483647)
    private String tagTime;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tag_type", nullable = false)
    private int tagType;

    public MapsPK() {
    }

    public MapsPK(int latitude, int longitude, String tagTime, int tagType) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.tagTime = tagTime;
        this.tagType = tagType;
    }

    public int getLatitude() {
        return latitude;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    public int getLongitude() {
        return longitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    public String getTagTime() {
        return tagTime;
    }

    public void setTagTime(String tagTime) {
        this.tagTime = tagTime;
    }

    public int getTagType() {
        return tagType;
    }

    public void setTagType(int tagType) {
        this.tagType = tagType;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) latitude;
        hash += (int) longitude;
        hash += (tagTime != null ? tagTime.hashCode() : 0);
        hash += (int) tagType;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MapsPK)) {
            return false;
        }
        MapsPK other = (MapsPK) object;
        if (this.latitude != other.latitude) {
            return false;
        }
        if (this.longitude != other.longitude) {
            return false;
        }
        if ((this.tagTime == null && other.tagTime != null) || (this.tagTime != null && !this.tagTime.equals(other.tagTime))) {
            return false;
        }
        if (this.tagType != other.tagType) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidad.MapsPK[ latitude=" + latitude + ", longitude=" + longitude + ", tagTime=" + tagTime + ", tagType=" + tagType + " ]";
    }
    
}
