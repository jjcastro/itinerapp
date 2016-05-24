/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ingesoft.itinerapp.ejbs;

import com.ingesoft.itinerapp.api.IRecuerdoLogic;
import com.ingesoft.itinerapp.entities.RecuerdoEntity;
import com.ingesoft.itinerapp.entities.UsuarioEntity;
import com.ingesoft.itinerapp.exceptions.BusinessLogicException;
import com.ingesoft.itinerapp.persistence.RecuerdoPersistence;
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
public class RecuerdoLogicTest {

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private IRecuerdoLogic recuerdoLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<RecuerdoEntity> data = new ArrayList<RecuerdoEntity>();

    private List<UsuarioEntity> usuarioData = new ArrayList<>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(RecuerdoEntity.class.getPackage())
                .addPackage(RecuerdoLogic.class.getPackage())
                .addPackage(IRecuerdoLogic.class.getPackage())
                .addPackage(RecuerdoPersistence.class.getPackage())
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
        em.createQuery("delete from RecuerdoEntity").executeUpdate();
        em.createQuery("delete from UsuarioEntity").executeUpdate();
    }

    private void insertData() {
        
        for (int i = 0; i < 3; i++) {
            UsuarioEntity usuario = factory.manufacturePojo(UsuarioEntity.class);
            em.persist(usuario);
            usuarioData.add(usuario);
        }

        for (int i = 0; i < 3; i++) {
            RecuerdoEntity entity = factory.manufacturePojo(RecuerdoEntity.class);
            entity.setUsuario(usuarioData.get(0));
            
            em.persist(entity);
            data.add(entity);
        }
    }

    @Test
    public void createRecuerdoTest() {
        try {
            RecuerdoEntity entity = factory.manufacturePojo(RecuerdoEntity.class);
            
            RecuerdoEntity created = recuerdoLogic.createRecuerdo(entity);
            
            RecuerdoEntity result = em.find(RecuerdoEntity.class, created.getId());

            Assert.assertNotNull(result);
            Assert.assertEquals(entity.getId(), result.getId());
            Assert.assertEquals(entity.getName(), result.getName());
            Assert.assertEquals(entity.getDescripcion(), result.getDescripcion());
            Assert.assertEquals(entity.getImagen(), result.getImagen());
        } catch (Exception ex) {
            Assert.fail(ex.getLocalizedMessage());
        }
    }

    @Test
    public void getRecuerdosTest() {
        List<RecuerdoEntity> resultList = recuerdoLogic.getRecuerdos();
        List<RecuerdoEntity> expectedList = em.createQuery("SELECT u from RecuerdoEntity u").getResultList();
        Assert.assertEquals(expectedList.size(), resultList.size());
        for (RecuerdoEntity expected : expectedList) {
            boolean found = false;
            for (RecuerdoEntity result : resultList) {
                if (result.getId().equals(expected.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    @Test
    public void getRecuerdoTest() {
        RecuerdoEntity result = recuerdoLogic.getRecuerdo(data.get(0).getId());
        RecuerdoEntity expected = em.find(RecuerdoEntity.class, data.get(0).getId());

        Assert.assertNotNull(expected);
        Assert.assertNotNull(result);
        Assert.assertEquals(expected.getId(), result.getId());
        Assert.assertEquals(expected.getName(), result.getName());
        Assert.assertEquals(expected.getDescripcion(), result.getDescripcion());
        Assert.assertEquals(expected.getImagen(), result.getImagen());
    }

    @Test
    public void deleteRecuerdoTest() {
        RecuerdoEntity entity = data.get(1);
        recuerdoLogic.deleteRecuerdo(entity.getId());
        RecuerdoEntity deleted = em.find(RecuerdoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    @Test
    public void updateRecuerdoTest() {
        try {
            RecuerdoEntity entity = data.get(0);
            RecuerdoEntity pojoEntity = factory.manufacturePojo(RecuerdoEntity.class);

            pojoEntity.setId(entity.getId());

            recuerdoLogic.updateRecuerdo(pojoEntity);

            RecuerdoEntity resp = em.find(RecuerdoEntity.class, entity.getId());

            Assert.assertEquals(pojoEntity.getId(), resp.getId());
            Assert.assertEquals(pojoEntity.getName(), resp.getName());
            Assert.assertEquals(pojoEntity.getDescripcion(), resp.getDescripcion());
            Assert.assertEquals(pojoEntity.getImagen(), resp.getImagen());
        } catch (Exception ex) {
            Assert.fail(ex.getLocalizedMessage());
        }
    }
    
}