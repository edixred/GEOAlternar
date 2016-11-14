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
public class MapsSolarJpaController implements Serializable {

    public MapsSolarJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(MapsSolar mapsSolar) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (mapsSolar.getMapsSolarPK() == null) {
            mapsSolar.setMapsSolarPK(new MapsSolarPK());
        }
        mapsSolar.getMapsSolarPK().setGrid450Id(mapsSolar.getGrid450().getGrid450Id());
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Grid450 grid450 = mapsSolar.getGrid450();
            if (grid450 != null) {
                grid450 = em.getReference(grid450.getClass(), grid450.getGrid450Id());
                mapsSolar.setGrid450(grid450);
            }
            em.persist(mapsSolar);
            if (grid450 != null) {
                grid450.getMapsSolarList().add(mapsSolar);
                grid450 = em.merge(grid450);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findMapsSolar(mapsSolar.getMapsSolarPK()) != null) {
                throw new PreexistingEntityException("MapsSolar " + mapsSolar + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(MapsSolar mapsSolar) throws NonexistentEntityException, RollbackFailureException, Exception {
        mapsSolar.getMapsSolarPK().setGrid450Id(mapsSolar.getGrid450().getGrid450Id());
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            MapsSolar persistentMapsSolar = em.find(MapsSolar.class, mapsSolar.getMapsSolarPK());
            Grid450 grid450Old = persistentMapsSolar.getGrid450();
            Grid450 grid450New = mapsSolar.getGrid450();
            if (grid450New != null) {
                grid450New = em.getReference(grid450New.getClass(), grid450New.getGrid450Id());
                mapsSolar.setGrid450(grid450New);
            }
            mapsSolar = em.merge(mapsSolar);
            if (grid450Old != null && !grid450Old.equals(grid450New)) {
                grid450Old.getMapsSolarList().remove(mapsSolar);
                grid450Old = em.merge(grid450Old);
            }
            if (grid450New != null && !grid450New.equals(grid450Old)) {
                grid450New.getMapsSolarList().add(mapsSolar);
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
                MapsSolarPK id = mapsSolar.getMapsSolarPK();
                if (findMapsSolar(id) == null) {
                    throw new NonexistentEntityException("The mapsSolar with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(MapsSolarPK id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            MapsSolar mapsSolar;
            try {
                mapsSolar = em.getReference(MapsSolar.class, id);
                mapsSolar.getMapsSolarPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The mapsSolar with id " + id + " no longer exists.", enfe);
            }
            Grid450 grid450 = mapsSolar.getGrid450();
            if (grid450 != null) {
                grid450.getMapsSolarList().remove(mapsSolar);
                grid450 = em.merge(grid450);
            }
            em.remove(mapsSolar);
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

    public List<MapsSolar> findMapsSolarEntities() {
        return findMapsSolarEntities(true, -1, -1);
    }

    public List<MapsSolar> findMapsSolarEntities(int maxResults, int firstResult) {
        return findMapsSolarEntities(false, maxResults, firstResult);
    }

    private List<MapsSolar> findMapsSolarEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(MapsSolar.class));
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

    public MapsSolar findMapsSolar(MapsSolarPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(MapsSolar.class, id);
        } finally {
            em.close();
        }
    }

    public int getMapsSolarCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<MapsSolar> rt = cq.from(MapsSolar.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
