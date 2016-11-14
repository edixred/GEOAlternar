/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccessObject;

import Entidad.MapsBiomass;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author edixred
 */
@Stateless
public class MapsBiomassFacade extends AbstractFacade<MapsBiomass> {
    @PersistenceContext(unitName = "GeoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MapsBiomassFacade() {
        super(MapsBiomass.class);
    }
    
}
