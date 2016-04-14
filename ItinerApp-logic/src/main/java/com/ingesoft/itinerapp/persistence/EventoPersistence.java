/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ingesoft.itinerapp.persistence;

/**
 *
 * @author s.robayo222
 */
import com.ingesoft.itinerapp.entities.EventoEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class EventoPersistence
{
    @PersistenceContext(unitName = "ItinerAppPU")
    protected EntityManager em;

    public EventoEntity create(EventoEntity entity) {
        em.persist(entity);
        return entity;
    }

    public EventoEntity update(EventoEntity entity) {
        return em.merge(entity);
    }

    public void delete(Long id) {
        EventoEntity entity = em.find(EventoEntity.class, id);
        em.remove(entity);
    }

    public EventoEntity find(Long id) {
        return em.find(EventoEntity.class, id);
    }

    public List<EventoEntity> findAll() {
        Query q = em.createQuery("select u from EventoEntity u");
        return q.getResultList();
    }
}
