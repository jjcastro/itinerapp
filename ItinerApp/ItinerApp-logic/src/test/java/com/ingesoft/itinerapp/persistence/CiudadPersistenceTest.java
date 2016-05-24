/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ingesoft.itinerapp.persistence;

import com.ingesoft.itinerapp.entities.CiudadEntity;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author jj.castro10
 */
@RunWith(Arquillian.class)
public class CiudadPersistenceTest {

    @Inject
    private CiudadPersistence ciudadPersistence;
    @PersistenceContext
    private EntityManager em;
    private final PodamFactory factory = new PodamFactoryImpl();
    @Inject
    UserTransaction utx;

    public CiudadPersistenceTest() {
    }

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CiudadEntity.class.getPackage())
                .addPackage(CiudadPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    @Before
    public void configTest() {
        try {
            utx.begin();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    private void clearData() {
        em.createQuery("delete from CiudadEntity").executeUpdate();
    }

    private List<CiudadEntity> data = new ArrayList<>();

    private void insertData() {
        for (int i = 0; i < 3; i++) {
            CiudadEntity entity = factory.manufacturePojo(CiudadEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }

    @Test
    public void createCiudadTest() {
        CiudadEntity newEntity = factory.manufacturePojo(CiudadEntity.class);
        CiudadEntity result = ciudadPersistence.create(newEntity);

        Assert.assertNotNull(result);
        CiudadEntity entity = em.find(CiudadEntity.class, result.getId());

        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
        Assert.assertEquals(newEntity.getPais(), entity.getPais());
        Assert.assertEquals(newEntity.getDescripcion(), entity.getDescripcion());
        Assert.assertEquals(newEntity.getFechaF(), entity.getFechaF());
        Assert.assertEquals(newEntity.getFechaI(), entity.getFechaI());
        Assert.assertEquals(newEntity.getFotoBig(), entity.getFotoBig());
        Assert.assertEquals(newEntity.getFotoSmall(), entity.getFotoSmall());
        Assert.assertEquals(newEntity.getSubtext(), entity.getSubtext());
    }

    @Test
    public void getCiudadesTest() {
        List<CiudadEntity> list = ciudadPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (CiudadEntity ent : list) {
            boolean found = false;
            for (CiudadEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    @Test
    public void getCiudadTest()
    {
        CiudadEntity entity = data.get(0);
        CiudadEntity newEntity = ciudadPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
        Assert.assertEquals(newEntity.getPais(), entity.getPais());
        Assert.assertEquals(newEntity.getDescripcion(), entity.getDescripcion());
        Assert.assertEquals(newEntity.getFechaF(), entity.getFechaF());
        Assert.assertEquals(newEntity.getFechaI(), entity.getFechaI());
        Assert.assertEquals(newEntity.getFotoBig(), entity.getFotoBig());
        Assert.assertEquals(newEntity.getFotoSmall(), entity.getFotoSmall());
        Assert.assertEquals(newEntity.getSubtext(), entity.getSubtext());
    }


    @Test
    public void deleteCiudadTest()
    {
        CiudadEntity entity = data.get(0);
        ciudadPersistence.delete(entity.getId());
        CiudadEntity deleted = em.find(CiudadEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    @Test
    public void updateEventoTest()
    {
        CiudadEntity entity = data.get(0);
        CiudadEntity newEntity = factory.manufacturePojo(CiudadEntity.class);
        newEntity.setId(entity.getId());
        ciudadPersistence.update(newEntity);
        CiudadEntity rta = em.find(CiudadEntity.class, entity.getId());
        Assert.assertEquals(newEntity.getNombre(), rta.getNombre());
        Assert.assertEquals(newEntity.getPais(), rta.getPais());
        Assert.assertEquals(newEntity.getDescripcion(), rta.getDescripcion());
        Assert.assertEquals(newEntity.getFechaF(), rta.getFechaF());
        Assert.assertEquals(newEntity.getFechaI(), rta.getFechaI());
        Assert.assertEquals(newEntity.getFotoBig(), rta.getFotoBig());
        Assert.assertEquals(newEntity.getFotoSmall(), rta.getFotoSmall());
        Assert.assertEquals(newEntity.getSubtext(), rta.getSubtext());
    }

}
