/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ingesoft.itinerapp.ejbs;

import com.ingesoft.itinerapp.api.ICiudadLogic;
import com.ingesoft.itinerapp.entities.CiudadEntity;
import com.ingesoft.itinerapp.entities.UsuarioEntity;
import com.ingesoft.itinerapp.exceptions.BusinessLogicException;
import com.ingesoft.itinerapp.persistence.CiudadPersistence;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
 * @author Lenovo
 */
@RunWith(Arquillian.class)
public class CiudadLogicTest {

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private ICiudadLogic CiudadLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<CiudadEntity> data = new ArrayList<CiudadEntity>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CiudadEntity.class.getPackage())
                .addPackage(CiudadLogic.class.getPackage())
                .addPackage(ICiudadLogic.class.getPackage())
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

    private void insertData() {
        for (int i = 0; i < 3; i++) {
            CiudadEntity entity = factory.manufacturePojo(CiudadEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }

    @Test
    public void createCiudadTest() {
        try {
            CiudadEntity entity = factory.manufacturePojo(CiudadEntity.class);

            CiudadEntity created = CiudadLogic.createCiudad(entity);

            CiudadEntity result = em.find(CiudadEntity.class, created.getId());

            Assert.assertNotNull(result);
            Assert.assertEquals(entity.getNombre(), result.getNombre());
            Assert.assertEquals(entity.getPais(), result.getPais());
            Assert.assertEquals(entity.getDescripcion(), result.getDescripcion());
            Assert.assertEquals(entity.getFechaF(), result.getFechaF());
            Assert.assertEquals(entity.getFechaI(), result.getFechaI());
            Assert.assertEquals(entity.getFotoBig(), result.getFotoBig());
            Assert.assertEquals(entity.getFotoSmall(), result.getFotoSmall());
            Assert.assertEquals(entity.getSubtext(), result.getSubtext());
        } catch (Exception ex) {
            Assert.fail(ex.getLocalizedMessage());
        }
    }

    @Test
    public void getCiudadesTest() {
        List<CiudadEntity> resultList = CiudadLogic.getCiudades();
        List<CiudadEntity> expectedList = em.createQuery("SELECT u from CiudadEntity u").getResultList();
        Assert.assertEquals(expectedList.size(), resultList.size());
        for (CiudadEntity expected : expectedList) {
            boolean found = false;
            for (CiudadEntity result : resultList) {
                if (result.getId().equals(expected.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    @Test
    public void getCiudadTest() {
        CiudadEntity result = CiudadLogic.getCiudad(data.get(0).getId());
        CiudadEntity expected = em.find(CiudadEntity.class, data.get(0).getId());

        Assert.assertNotNull(expected);
        Assert.assertNotNull(result);
        Assert.assertEquals(expected.getNombre(), result.getNombre());
        Assert.assertEquals(expected.getPais(), result.getPais());
        Assert.assertEquals(expected.getDescripcion(), result.getDescripcion());
        Assert.assertEquals(expected.getFechaF(), result.getFechaF());
        Assert.assertEquals(expected.getFechaI(), result.getFechaI());
        Assert.assertEquals(expected.getFotoBig(), result.getFotoBig());
        Assert.assertEquals(expected.getFotoSmall(), result.getFotoSmall());
        Assert.assertEquals(expected.getSubtext(), result.getSubtext());
    }

    @Test
    public void deleteCiudadTest() {
        CiudadEntity entity = data.get(1);
        CiudadLogic.deleteCiudad(entity.getId());
        CiudadEntity deleted = em.find(CiudadEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    @Test
    public void updateCiudadTest() {
        try {
            CiudadEntity entity = data.get(0);
            CiudadEntity pojoEntity = factory.manufacturePojo(CiudadEntity.class);

            pojoEntity.setId(entity.getId());

            CiudadLogic.updateCiudad(pojoEntity);

            CiudadEntity resp = em.find(CiudadEntity.class, entity.getId());

            Assert.assertEquals(pojoEntity.getNombre(), resp.getNombre());
            Assert.assertEquals(pojoEntity.getPais(), resp.getPais());
            Assert.assertEquals(pojoEntity.getDescripcion(), resp.getDescripcion());
            Assert.assertEquals(pojoEntity.getFechaF(), resp.getFechaF());
            Assert.assertEquals(pojoEntity.getFechaI(), resp.getFechaI());
            Assert.assertEquals(pojoEntity.getFotoBig(), resp.getFotoBig());
            Assert.assertEquals(pojoEntity.getFotoSmall(), resp.getFotoSmall());
            Assert.assertEquals(pojoEntity.getSubtext(), resp.getSubtext());
        } catch (Exception ex) {
            Assert.fail(ex.getLocalizedMessage());
        }
    }

}