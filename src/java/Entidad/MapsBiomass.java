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
@Table(name = "maps_biomass", catalog = "geoalternar", schema = "public")
@NamedQueries({
    @NamedQuery(name = "MapsBiomass.findAll", query = "SELECT m FROM MapsBiomass m"),
    @NamedQuery(name = "MapsBiomass.findByGrid450Id", query = "SELECT m FROM MapsBiomass m WHERE m.mapsBiomassPK.grid450Id = :grid450Id"),
    @NamedQuery(name = "MapsBiomass.findByTagTime", query = "SELECT m FROM MapsBiomass m WHERE m.mapsBiomassPK.tagTime = :tagTime"),
    @NamedQuery(name = "MapsBiomass.findByValuePoint", query = "SELECT m FROM MapsBiomass m WHERE m.valuePoint = :valuePoint")})
public class MapsBiomass implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MapsBiomassPK mapsBiomassPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "value_point", precision = 17, scale = 17)
    private Double valuePoint;
    @JoinColumn(name = "grid450_id", referencedColumnName = "grid450_id", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Grid450 grid450;

    public MapsBiomass() {
    }

    public MapsBiomass(MapsBiomassPK mapsBiomassPK) {
        this.mapsBiomassPK = mapsBiomassPK;
    }

    public MapsBiomass(int grid450Id, String tagTime) {
        this.mapsBiomassPK = new MapsBiomassPK(grid450Id, tagTime);
    }

    public MapsBiomassPK getMapsBiomassPK() {
        return mapsBiomassPK;
    }

    public void setMapsBiomassPK(MapsBiomassPK mapsBiomassPK) {
        this.mapsBiomassPK = mapsBiomassPK;
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
        hash += (mapsBiomassPK != null ? mapsBiomassPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MapsBiomass)) {
            return false;
        }
        MapsBiomass other = (MapsBiomass) object;
        if ((this.mapsBiomassPK == null && other.mapsBiomassPK != null) || (this.mapsBiomassPK != null && !this.mapsBiomassPK.equals(other.mapsBiomassPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidad.MapsBiomass[ mapsBiomassPK=" + mapsBiomassPK + " ]";
    }
    
}
