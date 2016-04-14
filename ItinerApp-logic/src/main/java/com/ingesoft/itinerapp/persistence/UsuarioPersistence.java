package com.ingesoft.itinerapp.persistence;


import com.ingesoft.itinerapp.entities.UsuarioEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * @author jc.martha10
 */
@Stateless
public class UsuarioPersistence {

    @PersistenceContext(unitName = "ItinerAppPU")
    protected EntityManager em;

    public UsuarioEntity create(UsuarioEntity entity) {
        em.persist(entity);
        return entity;
    }

    public UsuarioEntity update(UsuarioEntity entity) {
        return em.merge(entity);
    }

    public void delete(Long id) {
        UsuarioEntity entity = em.find(UsuarioEntity.class, id);
        em.remove(entity);
    }

    public UsuarioEntity find(Long id) {
        return em.find(UsuarioEntity.class, id);
    }

    public List<UsuarioEntity> findAll() {
        Query q = em.createQuery("select u from UsuarioEntity u");
        return q.getResultList();
    }
}
