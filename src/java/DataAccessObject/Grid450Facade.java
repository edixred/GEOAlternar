/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccessObject;

import Entidad.Grid450;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author edixred
 */
@Stateless
public class Grid450Facade extends AbstractFacade<Grid450> {
    @PersistenceContext(unitName = "GeoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public Grid450Facade() {
        super(Grid450.class);
    }
    
}
