/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidad;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author edixred
 */
@Entity
@Table(name = "maps", catalog = "geoalternar", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Maps.findAll", query = "SELECT m FROM Maps m"),
    //Select * from maps where latitude=? and longitude=? and tag_time=? and tag_type=?
    @NamedQuery(name = "Maps.findValuePoint", 
            query = "SELECT m FROM Maps m "
                    + "WHERE m.mapsPK.latitude = :latitude AND "
                    + "m.mapsPK.longitude = :longitude"),
    @NamedQuery(name = "Maps.findByLatitude", query = "SELECT m FROM Maps m WHERE m.mapsPK.latitude = :latitude"),
    @NamedQuery(name = "Maps.findByLongitude", query = "SELECT m FROM Maps m WHERE m.mapsPK.longitude = :longitude"),
    @NamedQuery(name = "Maps.findByTagTime", query = "SELECT m FROM Maps m WHERE m.mapsPK.tagTime = :tagTime"),
    @NamedQuery(name = "Maps.findByValue", query = "SELECT m FROM Maps m WHERE m.value = :value"),
    @NamedQuery(name = "Maps.findByTagType", query = "SELECT m FROM Maps m WHERE m.mapsPK.tagType = :tagType")})
public class Maps implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MapsPK mapsPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "value", precision = 17, scale = 17)
    private Double value;

    public Maps() {
    }

    public Maps(MapsPK mapsPK) {
        this.mapsPK = mapsPK;
    }

    public Maps(int latitude, int longitude, String tagTime, int tagType) {
        this.mapsPK = new MapsPK(latitude, longitude, tagTime, tagType);
    }

    public MapsPK getMapsPK() {
        return mapsPK;
    }

    public void setMapsPK(MapsPK mapsPK) {
        this.mapsPK = mapsPK;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mapsPK != null ? mapsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Maps)) {
            return false;
        }
        Maps other = (Maps) object;
        if ((this.mapsPK == null && other.mapsPK != null) || (this.mapsPK != null && !this.mapsPK.equals(other.mapsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidad.Maps[ mapsPK=" + mapsPK + " ]";
    }
    
}
