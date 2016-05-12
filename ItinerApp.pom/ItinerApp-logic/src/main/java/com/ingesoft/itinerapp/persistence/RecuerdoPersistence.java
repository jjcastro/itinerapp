package com.ingesoft.itinerapp.persistence;


import com.ingesoft.itinerapp.entities.RecuerdoEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * @generated
 */
@Stateless
public class RecuerdoPersistence {

    @PersistenceContext(unitName = "ItinerAppPU")
    protected EntityManager em;

    public RecuerdoEntity create(RecuerdoEntity entity) {
        em.persist(entity);
        return entity;
    }

    public RecuerdoEntity update(RecuerdoEntity entity) {
        return em.merge(entity);
    }

    public void delete(Long id) {
        RecuerdoEntity entity = em.find(RecuerdoEntity.class, id);
        em.remove(entity);
    }

    public RecuerdoEntity find(Long id) {
        return em.find(RecuerdoEntity.class, id);
    }

    public List<RecuerdoEntity> findAll() {
        Query q = em.createQuery("select u from RecuerdoEntity u");
        return q.getResultList();
    }
     public List<RecuerdoEntity> findAllUsuario(Long idUsuario) {
        Query q = em.createQuery("select u from RecuerdoEntity u where USUARIO_ID = '"+idUsuario+"'");
        return q.getResultList();
    }
}
