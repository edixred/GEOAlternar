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
@Table(name = "maps_wind", catalog = "geoalternar", schema = "public")
@NamedQueries({
    @NamedQuery(name = "MapsWind.findAll", query = "SELECT m FROM MapsWind m"),
    @NamedQuery(name = "MapsWind.findByGrid450Id", query = "SELECT m FROM MapsWind m WHERE m.mapsWindPK.grid450Id = :grid450Id"),
    @NamedQuery(name = "MapsWind.findByTagTime", query = "SELECT m FROM MapsWind m WHERE m.mapsWindPK.tagTime = :tagTime"),
    @NamedQuery(name = "MapsWind.findByValuePoint", query = "SELECT m FROM MapsWind m WHERE m.valuePoint = :valuePoint")})
public class MapsWind implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MapsWindPK mapsWindPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "value_point", precision = 17, scale = 17)
    private Double valuePoint;
    @JoinColumn(name = "grid450_id", referencedColumnName = "grid450_id", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Grid450 grid450;

    public MapsWind() {
    }

    public MapsWind(MapsWindPK mapsWindPK) {
        this.mapsWindPK = mapsWindPK;
    }

    public MapsWind(int grid450Id, String tagTime) {
        this.mapsWindPK = new MapsWindPK(grid450Id, tagTime);
    }

    public MapsWindPK getMapsWindPK() {
        return mapsWindPK;
    }

    public void setMapsWindPK(MapsWindPK mapsWindPK) {
        this.mapsWindPK = mapsWindPK;
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
        hash += (mapsWindPK != null ? mapsWindPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MapsWind)) {
            return false;
        }
        MapsWind other = (MapsWind) object;
        if ((this.mapsWindPK == null && other.mapsWindPK != null) || (this.mapsWindPK != null && !this.mapsWindPK.equals(other.mapsWindPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidad.MapsWind[ mapsWindPK=" + mapsWindPK + " ]";
    }
    
}
