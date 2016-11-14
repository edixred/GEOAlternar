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
public class MapsWindJpaController implements Serializable {

    public MapsWindJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(MapsWind mapsWind) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (mapsWind.getMapsWindPK() == null) {
            mapsWind.setMapsWindPK(new MapsWindPK());
        }
        mapsWind.getMapsWindPK().setGrid450Id(mapsWind.getGrid450().getGrid450Id());
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Grid450 grid450 = mapsWind.getGrid450();
            if (grid450 != null) {
                grid450 = em.getReference(grid450.getClass(), grid450.getGrid450Id());
                mapsWind.setGrid450(grid450);
            }
            em.persist(mapsWind);
            if (grid450 != null) {
                grid450.getMapsWindList().add(mapsWind);
                grid450 = em.merge(grid450);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findMapsWind(mapsWind.getMapsWindPK()) != null) {
                throw new PreexistingEntityException("MapsWind " + mapsWind + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(MapsWind mapsWind) throws NonexistentEntityException, RollbackFailureException, Exception {
        mapsWind.getMapsWindPK().setGrid450Id(mapsWind.getGrid450().getGrid450Id());
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            MapsWind persistentMapsWind = em.find(MapsWind.class, mapsWind.getMapsWindPK());
            Grid450 grid450Old = persistentMapsWind.getGrid450();
            Grid450 grid450New = mapsWind.getGrid450();
            if (grid450New != null) {
                grid450New = em.getReference(grid450New.getClass(), grid450New.getGrid450Id());
                mapsWind.setGrid450(grid450New);
            }
            mapsWind = em.merge(mapsWind);
            if (grid450Old != null && !grid450Old.equals(grid450New)) {
                grid450Old.getMapsWindList().remove(mapsWind);
                grid450Old = em.merge(grid450Old);
            }
            if (grid450New != null && !grid450New.equals(grid450Old)) {
                grid450New.getMapsWindList().add(mapsWind);
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
                MapsWindPK id = mapsWind.getMapsWindPK();
                if (findMapsWind(id) == null) {
                    throw new NonexistentEntityException("The mapsWind with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(MapsWindPK id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            MapsWind mapsWind;
            try {
                mapsWind = em.getReference(MapsWind.class, id);
                mapsWind.getMapsWindPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The mapsWind with id " + id + " no longer exists.", enfe);
            }
            Grid450 grid450 = mapsWind.getGrid450();
            if (grid450 != null) {
                grid450.getMapsWindList().remove(mapsWind);
                grid450 = em.merge(grid450);
            }
            em.remove(mapsWind);
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

    public List<MapsWind> findMapsWindEntities() {
        return findMapsWindEntities(true, -1, -1);
    }

    public List<MapsWind> findMapsWindEntities(int maxResults, int firstResult) {
        return findMapsWindEntities(false, maxResults, firstResult);
    }

    private List<MapsWind> findMapsWindEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(MapsWind.class));
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

    public MapsWind findMapsWind(MapsWindPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(MapsWind.class, id);
        } finally {
            em.close();
        }
    }

    public int getMapsWindCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<MapsWind> rt = cq.from(MapsWind.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
