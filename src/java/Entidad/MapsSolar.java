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
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author giiee
 */
@Entity
@Table(name = "maps_solar", catalog = "geoalternar", schema = "public")
@NamedQueries({
    @NamedQuery(name = "MapsSolar.findAll", query = "SELECT m FROM MapsSolar m"),
    @NamedQuery(name = "MapsSolar.findByGrid450Id", query = "SELECT m FROM MapsSolar m WHERE m.mapsSolarPK.grid450Id = :grid450Id"),
    @NamedQuery(name = "MapsSolar.findByTagTime", query = "SELECT m FROM MapsSolar m WHERE m.mapsSolarPK.tagTime = :tagTime"),
    @NamedQuery(name = "MapsSolar.findByValuePoint", query = "SELECT m FROM MapsSolar m WHERE m.valuePoint = :valuePoint")})
public class MapsSolar implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MapsSolarPK mapsSolarPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "value_point", precision = 17, scale = 17)
    private Double valuePoint;
    @JoinColumn(name = "grid450_id", referencedColumnName = "grid450_id", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Grid450 grid450;

    public MapsSolar() {
    }

    public MapsSolar(MapsSolarPK mapsSolarPK) {
        this.mapsSolarPK = mapsSolarPK;
    }

    public MapsSolar(int grid450Id, String tagTime) {
        this.mapsSolarPK = new MapsSolarPK(grid450Id, tagTime);
    }

    public MapsSolarPK getMapsSolarPK() {
        return mapsSolarPK;
    }

    public void setMapsSolarPK(MapsSolarPK mapsSolarPK) {
        this.mapsSolarPK = mapsSolarPK;
    }

    public Double getValuePoint() {
        return valuePoint;
    }

    public void setValuePoint(Double valuePoint) {
        this.valuePoint = valuePoint;
    }

    public Grid450 getGrid450() {
        return grid450;
    }

    public void setGrid450(Grid450 grid450) {
        this.grid450 = grid450;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mapsSolarPK != null ? mapsSolarPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MapsSolar)) {
            return false;
        }
        MapsSolar other = (MapsSolar) object;
        if ((this.mapsSolarPK == null && other.mapsSolarPK != null) || (this.mapsSolarPK != null && !this.mapsSolarPK.equals(other.mapsSolarPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidad.MapsSolar[ mapsSolarPK=" + mapsSolarPK + " ]";
    }
    
}
