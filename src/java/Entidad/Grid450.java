/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidad;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author giiee
 */
@Entity
@Table(name = "grid_450", catalog = "geoalternar", schema = "public")
@NamedQueries({
    @NamedQuery(name = "Grid450.findAll", query = "SELECT g FROM Grid450 g"),
    @NamedQuery(name = "Grid450.findByGrid450Id", query = "SELECT g FROM Grid450 g WHERE g.grid450Id = :grid450Id"),
    @NamedQuery(name = "Grid450.findByLatitude3857", query = "SELECT g FROM Grid450 g WHERE g.latitude3857 = :latitude3857"),
    @NamedQuery(name = "Grid450.findByLongitude3857", query = "SELECT g FROM Grid450 g WHERE g.longitude3857 = :longitude3857"),
    @NamedQuery(name = "Grid450.findByLatitude4326", query = "SELECT g FROM Grid450 g WHERE g.latitude4326 = :latitude4326"),
    @NamedQuery(name = "Grid450.findByLongitude4326", query = "SELECT g FROM Grid450 g WHERE g.longitude4326 = :longitude4326")})
public class Grid450 implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "grid450_id", nullable = false)
    private Integer grid450Id;
    @Column(name = "latitude_3857")
    private Integer latitude3857;
    @Column(name = "longitude_3857")
    private Integer longitude3857;
    @Column(name = "latitude_4326")
    private BigInteger latitude4326;
    @Column(name = "longitude_4326")
    private BigInteger longitude4326;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "grid450", fetch = FetchType.LAZY)
    private List<MapsSolar> mapsSolarList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "grid450", fetch = FetchType.LAZY)
    private List<MapsBiomass> mapsBiomassList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "grid450", fetch = FetchType.LAZY)
    private List<MapsWind> mapsWindList;

    public Grid450() {
    }

    public Grid450(Integer grid450Id) {
        this.grid450Id = grid450Id;
    }

    public Integer getGrid450Id() {
        return grid450Id;
    }

    public void setGrid450Id(Integer grid450Id) {
        this.grid450Id = grid450Id;
    }

    public Integer getLatitude3857() {
        return latitude3857;
    }

    public void setLatitude3857(Integer latitude3857) {
        this.latitude3857 = latitude3857;
    }

    public Integer getLongitude3857() {
        return longitude3857;
    }

    public void setLongitude3857(Integer longitude3857) {
        this.longitude3857 = longitude3857;
    }

    public BigInteger getLatitude4326() {
        return latitude4326;
    }

    public void setLatitude4326(BigInteger latitude4326) {
        this.latitude4326 = latitude4326;
    }

    public BigInteger getLongitude4326() {
        return longitude4326;
    }

    public void setLongitude4326(BigInteger longitude4326) {
        this.longitude4326 = longitude4326;
    }

    public List<MapsSolar> getMapsSolarList() {
        return mapsSolarList;
    }

    public void setMapsSolarList(List<MapsSolar> mapsSolarList) {
        this.mapsSolarList = mapsSolarList;
    }

    public List<MapsBiomass> getMapsBiomassList() {
        return mapsBiomassList;
    }

    public void setMapsBiomassList(List<MapsBiomass> mapsBiomassList) {
        this.mapsBiomassList = mapsBiomassList;
    }

    public List<MapsWind> getMapsWindList() {
        return mapsWindList;
    }

    public void setMapsWindList(List<MapsWind> mapsWindList) {
        this.mapsWindList = mapsWindList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (grid450Id != null ? grid450Id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Grid450)) {
            return false;
        }
        Grid450 other = (Grid450) object;
        if ((this.grid450Id == null && other.grid450Id != null) || (this.grid450Id != null && !this.grid450Id.equals(other.grid450Id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidad.Grid450[ grid450Id=" + grid450Id + " ]";
    }
    
}
