/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidad;

import Entidad.exceptions.NonexistentEntityException;
import Entidad.exceptions.PreexistingEntityException;
import Entidad.exceptions.RollbackFailureException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.UserTransaction;

/**
 *
 * @author edixred
 */
public class MapsBiomassJpaController implements Serializable {

    public MapsBiomassJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(MapsBiomass mapsBiomass) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (mapsBiomass.getMapsBiomassPK() == null) {
            mapsBiomass.setMapsBiomassPK(new MapsBiomassPK());
        }
        mapsBiomass.getMapsBiomassPK().setGrid450Id(mapsBiomass.getGrid450().getGrid450Id());
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Grid450 grid450 = mapsBiomass.getGrid450();
            if (grid450 != null) {
                grid450 = em.getReference(grid450.getClass(), grid450.getGrid450Id());
                mapsBiomass.setGrid450(grid450);
            }
            em.persist(mapsBiomass);
            if (grid450 != null) {
                grid450.getMapsBiomassList().add(mapsBiomass);
                grid450 = em.merge(grid450);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findMapsBiomass(mapsBiomass.getMapsBiomassPK()) != null) {
                throw new PreexistingEntityException("MapsBiomass " + mapsBiomass + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(MapsBiomass mapsBiomass) throws NonexistentEntityException, RollbackFailureException, Exception {
        mapsBiomass.getMapsBiomassPK().setGrid450Id(mapsBiomass.getGrid450().getGrid450Id());
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            MapsBiomass persistentMapsBiomass = em.find(MapsBiomass.class, mapsBiomass.getMapsBiomassPK());
            Grid450 grid450Old = persistentMapsBiomass.getGrid450();
            Grid450 grid450New = mapsBiomass.getGrid450();
            if (grid450New != null) {
                grid450New = em.getReference(grid450New.getClass(), grid450New.getGrid450Id());
                mapsBiomass.setGrid450(grid450New);
            }
            mapsBiomass = em.merge(mapsBiomass);
            if (grid450Old != null && !grid450Old.equals(grid450New)) {
                grid450Old.getMapsBiomassList().remove(mapsBiomass);
                grid450Old = em.merge(grid450Old);
            }
            if (grid450New != null && !grid450New.equals(grid450Old)) {
                grid450New.getMapsBiomassList().add(mapsBiomass);
                grid450New = em.merge(grid450New);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                MapsBiomassPK id = mapsBiomass.getMapsBiomassPK();
                if (findMapsBiomass(id) == null) {
                    throw new NonexistentEntityException("The mapsBiomass with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(MapsBiomassPK id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            MapsBiomass mapsBiomass;
            try {
                mapsBiomass = em.getReference(MapsBiomass.class, id);
                mapsBiomass.getMapsBiomassPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The mapsBiomass with id " + id + " no longer exists.", enfe);
            }
            Grid450 grid450 = mapsBiomass.getGrid450();
            if (grid450 != null) {
                grid450.getMapsBiomassList().remove(mapsBiomass);
                grid450 = em.merge(grid450);
            }
            em.remove(mapsBiomass);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<MapsBiomass> findMapsBiomassEntities() {
        return findMapsBiomassEntities(true, -1, -1);
    }

    public List<MapsBiomass> findMapsBiomassEntities(int maxResults, int firstResult) {
        return findMapsBiomassEntities(false, maxResults, firstResult);
    }

    private List<MapsBiomass> findMapsBiomassEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(MapsBiomass.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public MapsBiomass findMapsBiomass(MapsBiomassPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(MapsBiomass.class, id);
        } finally {
            em.close();
        }
    }

    public int getMapsBiomassCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<MapsBiomass> rt = cq.from(MapsBiomass.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
