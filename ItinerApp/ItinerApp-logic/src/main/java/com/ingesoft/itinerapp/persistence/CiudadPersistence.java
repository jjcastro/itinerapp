package com.ingesoft.itinerapp.persistence;


import com.ingesoft.itinerapp.entities.CiudadEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class CiudadPersistence {

    @PersistenceContext(unitName = "ItinerAppPU")
    protected EntityManager em;

    public CiudadEntity create(CiudadEntity entity) {
        em.persist(entity);
        return entity;
    }

    public CiudadEntity update(CiudadEntity entity) {
        return em.merge(entity);
    }

    public void delete(Long id) {
        CiudadEntity entity = em.find(CiudadEntity.class, id);
        em.remove(entity);
    }

    public CiudadEntity find(Long id) {
        return em.find(CiudadEntity.class, id);
    }

    public List<CiudadEntity> findAll() {
        Query q = em.createQuery("select u from CiudadEntity u");
        return q.getResultList();
    }
}
