/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidad;

import Entidad.exceptions.IllegalOrphanException;
import Entidad.exceptions.NonexistentEntityException;
import Entidad.exceptions.PreexistingEntityException;
import Entidad.exceptions.RollbackFailureException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author edixred
 */
public class Grid450JpaController implements Serializable {

    public Grid450JpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Grid450 grid450) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (grid450.getMapsSolarList() == null) {
            grid450.setMapsSolarList(new ArrayList<MapsSolar>());
        }
        if (grid450.getMapsBiomassList() == null) {
            grid450.setMapsBiomassList(new ArrayList<MapsBiomass>());
        }
        if (grid450.getMapsWindList() == null) {
            grid450.setMapsWindList(new ArrayList<MapsWind>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            List<MapsSolar> attachedMapsSolarList = new ArrayList<MapsSolar>();
            for (MapsSolar mapsSolarListMapsSolarToAttach : grid450.getMapsSolarList()) {
                mapsSolarListMapsSolarToAttach = em.getReference(mapsSolarListMapsSolarToAttach.getClass(), mapsSolarListMapsSolarToAttach.getMapsSolarPK());
                attachedMapsSolarList.add(mapsSolarListMapsSolarToAttach);
            }
            grid450.setMapsSolarList(attachedMapsSolarList);
            List<MapsBiomass> attachedMapsBiomassList = new ArrayList<MapsBiomass>();
            for (MapsBiomass mapsBiomassListMapsBiomassToAttach : grid450.getMapsBiomassList()) {
                mapsBiomassListMapsBiomassToAttach = em.getReference(mapsBiomassListMapsBiomassToAttach.getClass(), mapsBiomassListMapsBiomassToAttach.getMapsBiomassPK());
                attachedMapsBiomassList.add(mapsBiomassListMapsBiomassToAttach);
            }
            grid450.setMapsBiomassList(attachedMapsBiomassList);
            List<MapsWind> attachedMapsWindList = new ArrayList<MapsWind>();
            for (MapsWind mapsWindListMapsWindToAttach : grid450.getMapsWindList()) {
                mapsWindListMapsWindToAttach = em.getReference(mapsWindListMapsWindToAttach.getClass(), mapsWindListMapsWindToAttach.getMapsWindPK());
                attachedMapsWindList.add(mapsWindListMapsWindToAttach);
            }
            grid450.setMapsWindList(attachedMapsWindList);
            em.persist(grid450);
            for (MapsSolar mapsSolarListMapsSolar : grid450.getMapsSolarList()) {
                Grid450 oldGrid450OfMapsSolarListMapsSolar = mapsSolarListMapsSolar.getGrid450();
                mapsSolarListMapsSolar.setGrid450(grid450);
                mapsSolarListMapsSolar = em.merge(mapsSolarListMapsSolar);
                if (oldGrid450OfMapsSolarListMapsSolar != null) {
                    oldGrid450OfMapsSolarListMapsSolar.getMapsSolarList().remove(mapsSolarListMapsSolar);
                    oldGrid450OfMapsSolarListMapsSolar = em.merge(oldGrid450OfMapsSolarListMapsSolar);
                }
            }
            for (MapsBiomass mapsBiomassListMapsBiomass : grid450.getMapsBiomassList()) {
                Grid450 oldGrid450OfMapsBiomassListMapsBiomass = mapsBiomassListMapsBiomass.getGrid450();
                mapsBiomassListMapsBiomass.setGrid450(grid450);
                mapsBiomassListMapsBiomass = em.merge(mapsBiomassListMapsBiomass);
                if (oldGrid450OfMapsBiomassListMapsBiomass != null) {
                    oldGrid450OfMapsBiomassListMapsBiomass.getMapsBiomassList().remove(mapsBiomassListMapsBiomass);
                    oldGrid450OfMapsBiomassListMapsBiomass = em.merge(oldGrid450OfMapsBiomassListMapsBiomass);
                }
            }
            for (MapsWind mapsWindListMapsWind : grid450.getMapsWindList()) {
                Grid450 oldGrid450OfMapsWindListMapsWind = mapsWindListMapsWind.getGrid450();
                mapsWindListMapsWind.setGrid450(grid450);
                mapsWindListMapsWind = em.merge(mapsWindListMapsWind);
                if (oldGrid450OfMapsWindListMapsWind != null) {
                    oldGrid450OfMapsWindListMapsWind.getMapsWindList().remove(mapsWindListMapsWind);
                    oldGrid450OfMapsWindListMapsWind = em.merge(oldGrid450OfMapsWindListMapsWind);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findGrid450(grid450.getGrid450Id()) != null) {
                throw new PreexistingEntityException("Grid450 " + grid450 + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Grid450 grid450) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Grid450 persistentGrid450 = em.find(Grid450.class, grid450.getGrid450Id());
            List<MapsSolar> mapsSolarListOld = persistentGrid450.getMapsSolarList();
            List<MapsSolar> mapsSolarListNew = grid450.getMapsSolarList();
            List<MapsBiomass> mapsBiomassListOld = persistentGrid450.getMapsBiomassList();
            List<MapsBiomass> mapsBiomassListNew = grid450.getMapsBiomassList();
            List<MapsWind> mapsWindListOld = persistentGrid450.getMapsWindList();
            List<MapsWind> mapsWindListNew = grid450.getMapsWindList();
            List<String> illegalOrphanMessages = null;
            for (MapsSolar mapsSolarListOldMapsSolar : mapsSolarListOld) {
                if (!mapsSolarListNew.contains(mapsSolarListOldMapsSolar)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain MapsSolar " + mapsSolarListOldMapsSolar + " since its grid450 field is not nullable.");
                }
            }
            for (MapsBiomass mapsBiomassListOldMapsBiomass : mapsBiomassListOld) {
                if (!mapsBiomassListNew.contains(mapsBiomassListOldMapsBiomass)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain MapsBiomass " + mapsBiomassListOldMapsBiomass + " since its grid450 field is not nullable.");
                }
            }
            for (MapsWind mapsWindListOldMapsWind : mapsWindListOld) {
                if (!mapsWindListNew.contains(mapsWindListOldMapsWind)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain MapsWind " + mapsWindListOldMapsWind + " since its grid450 field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<MapsSolar> attachedMapsSolarListNew = new ArrayList<MapsSolar>();
            for (MapsSolar mapsSolarListNewMapsSolarToAttach : mapsSolarListNew) {
                mapsSolarListNewMapsSolarToAttach = em.getReference(mapsSolarListNewMapsSolarToAttach.getClass(), mapsSolarListNewMapsSolarToAttach.getMapsSolarPK());
                attachedMapsSolarListNew.add(mapsSolarListNewMapsSolarToAttach);
            }
            mapsSolarListNew = attachedMapsSolarListNew;
            grid450.setMapsSolarList(mapsSolarListNew);
            List<MapsBiomass> attachedMapsBiomassListNew = new ArrayList<MapsBiomass>();
            for (MapsBiomass mapsBiomassListNewMapsBiomassToAttach : mapsBiomassListNew) {
                mapsBiomassListNewMapsBiomassToAttach = em.getReference(mapsBiomassListNewMapsBiomassToAttach.getClass(), mapsBiomassListNewMapsBiomassToAttach.getMapsBiomassPK());
                attachedMapsBiomassListNew.add(mapsBiomassListNewMapsBiomassToAttach);
            }
            mapsBiomassListNew = attachedMapsBiomassListNew;
            grid450.setMapsBiomassList(mapsBiomassListNew);
            List<MapsWind> attachedMapsWindListNew = new ArrayList<MapsWind>();
            for (MapsWind mapsWindListNewMapsWindToAttach : mapsWindListNew) {
                mapsWindListNewMapsWindToAttach = em.getReference(mapsWindListNewMapsWindToAttach.getClass(), mapsWindListNewMapsWindToAttach.getMapsWindPK());
                attachedMapsWindListNew.add(mapsWindListNewMapsWindToAttach);
            }
            mapsWindListNew = attachedMapsWindListNew;
            grid450.setMapsWindList(mapsWindListNew);
            grid450 = em.merge(grid450);
            for (MapsSolar mapsSolarListNewMapsSolar : mapsSolarListNew) {
                if (!mapsSolarListOld.contains(mapsSolarListNewMapsSolar)) {
                    Grid450 oldGrid450OfMapsSolarListNewMapsSolar = mapsSolarListNewMapsSolar.getGrid450();
                    mapsSolarListNewMapsSolar.setGrid450(grid450);
                    mapsSolarListNewMapsSolar = em.merge(mapsSolarListNewMapsSolar);
                    if (oldGrid450OfMapsSolarListNewMapsSolar != null && !oldGrid450OfMapsSolarListNewMapsSolar.equals(grid450)) {
                        oldGrid450OfMapsSolarListNewMapsSolar.getMapsSolarList().remove(mapsSolarListNewMapsSolar);
                        oldGrid450OfMapsSolarListNewMapsSolar = em.merge(oldGrid450OfMapsSolarListNewMapsSolar);
                    }
                }
            }
            for (MapsBiomass mapsBiomassListNewMapsBiomass : mapsBiomassListNew) {
                if (!mapsBiomassListOld.contains(mapsBiomassListNewMapsBiomass)) {
                    Grid450 oldGrid450OfMapsBiomassListNewMapsBiomass = mapsBiomassListNewMapsBiomass.getGrid450();
                    mapsBiomassListNewMapsBiomass.setGrid450(grid450);
                    mapsBiomassListNewMapsBiomass = em.merge(mapsBiomassListNewMapsBiomass);
                    if (oldGrid450OfMapsBiomassListNewMapsBiomass != null && !oldGrid450OfMapsBiomassListNewMapsBiomass.equals(grid450)) {
                        oldGrid450OfMapsBiomassListNewMapsBiomass.getMapsBiomassList().remove(mapsBiomassListNewMapsBiomass);
                        oldGrid450OfMapsBiomassListNewMapsBiomass = em.merge(oldGrid450OfMapsBiomassListNewMapsBiomass);
                    }
                }
            }
            for (MapsWind mapsWindListNewMapsWind : mapsWindListNew) {
                if (!mapsWindListOld.contains(mapsWindListNewMapsWind)) {
                    Grid450 oldGrid450OfMapsWindListNewMapsWind = mapsWindListNewMapsWind.getGrid450();
                    mapsWindListNewMapsWind.setGrid450(grid450);
                    mapsWindListNewMapsWind = em.merge(mapsWindListNewMapsWind);
                    if (oldGrid450OfMapsWindListNewMapsWind != null && !oldGrid450OfMapsWindListNewMapsWind.equals(grid450)) {
                        oldGrid450OfMapsWindListNewMapsWind.getMapsWindList().remove(mapsWindListNewMapsWind);
                        oldGrid450OfMapsWindListNewMapsWind = em.merge(oldGrid450OfMapsWindListNewMapsWind);
                    }
                }
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
                Integer id = grid450.getGrid450Id();
                if (findGrid450(id) == null) {
                    throw new NonexistentEntityException("The grid450 with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Grid450 grid450;
            try {
                grid450 = em.getReference(Grid450.class, id);
                grid450.getGrid450Id();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The grid450 with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<MapsSolar> mapsSolarListOrphanCheck = grid450.getMapsSolarList();
            for (MapsSolar mapsSolarListOrphanCheckMapsSolar : mapsSolarListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Grid450 (" + grid450 + ") cannot be destroyed since the MapsSolar " + mapsSolarListOrphanCheckMapsSolar + " in its mapsSolarList field has a non-nullable grid450 field.");
            }
            List<MapsBiomass> mapsBiomassListOrphanCheck = grid450.getMapsBiomassList();
            for (MapsBiomass mapsBiomassListOrphanCheckMapsBiomass : mapsBiomassListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Grid450 (" + grid450 + ") cannot be destroyed since the MapsBiomass " + mapsBiomassListOrphanCheckMapsBiomass + " in its mapsBiomassList field has a non-nullable grid450 field.");
            }
            List<MapsWind> mapsWindListOrphanCheck = grid450.getMapsWindList();
            for (MapsWind mapsWindListOrphanCheckMapsWind : mapsWindListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Grid450 (" + grid450 + ") cannot be destroyed since the MapsWind " + mapsWindListOrphanCheckMapsWind + " in its mapsWindList field has a non-nullable grid450 field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(grid450);
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

    public List<Grid450> findGrid450Entities() {
        return findGrid450Entities(true, -1, -1);
    }

    public List<Grid450> findGrid450Entities(int maxResults, int firstResult) {
        return findGrid450Entities(false, maxResults, firstResult);
    }

    private List<Grid450> findGrid450Entities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Grid450.class));
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

    public Grid450 findGrid450(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Grid450.class, id);
        } finally {
            em.close();
        }
    }

    public int getGrid450Count() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Grid450> rt = cq.from(Grid450.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
