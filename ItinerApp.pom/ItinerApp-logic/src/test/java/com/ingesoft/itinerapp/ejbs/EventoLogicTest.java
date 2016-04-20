/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ingesoft.itinerapp.ejbs;

import com.ingesoft.itinerapp.api.IEventoLogic;
import com.ingesoft.itinerapp.entities.EventoEntity;
import com.ingesoft.itinerapp.entities.CiudadEntity;
import com.ingesoft.itinerapp.exceptions.BusinessLogicException;
import com.ingesoft.itinerapp.persistence.EventoPersistence;
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
public class EventoLogicTest {

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private IEventoLogic eventoLogic;

    @PersistenceContext
    private EntityManager enm;

    @Inject
    private UserTransaction utx;

    private List<EventoEntity> data = new ArrayList<EventoEntity>();

    private List<CiudadEntity> ciudadData = new ArrayList<>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(EventoEntity.class.getPackage())
                .addPackage(EventoLogic.class.getPackage())
                .addPackage(IEventoLogic.class.getPackage())
                .addPackage(EventoPersistence.class.getPackage())
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
        enm.createQuery("delete from EventoEntity").executeUpdate();
        enm.createQuery("delete from CiudadEntity").executeUpdate();
    }

    private void insertData() {

        for (int i = 0; i < 3; i++) {
            CiudadEntity ciudad = factory.manufacturePojo(CiudadEntity.class);
            enm.persist(ciudad);
            ciudadData.add(ciudad);
        }

        for (int i = 0; i < 3; i++) {
            EventoEntity entity = factory.manufacturePojo(EventoEntity.class);
            entity.setCiudad(ciudadData.get(0));

            enm.persist(entity);
            data.add(entity);
        }
    }

    @Test
    public void createEventoTest() {
        try {
            EventoEntity entity = factory.manufacturePojo(EventoEntity.class);

            EventoEntity created = eventoLogic.createEvento(entity);

            EventoEntity result = enm.find(EventoEntity.class, created.getId());

            Assert.assertNotNull(result);
            Assert.assertEquals(entity.getId(), result.getId());
            Assert.assertEquals(entity.getName(), result.getName());
            Assert.assertEquals(entity.getDescripcion(), result.getDescripcion());
        } catch (Exception ex) {
            Assert.fail(ex.getLocalizedMessage());
        }
    }

    @Test
    public void getEventosTest() {
        List<EventoEntity> resultList = eventoLogic.getEventos();
        List<EventoEntity> expectedList = enm.createQuery("SELECT u from EventoEntity u").getResultList();
        Assert.assertEquals(expectedList.size(), resultList.size());
        for (EventoEntity expected : expectedList) {
            boolean found = false;
            for (EventoEntity result : resultList) {
                if (result.getId().equals(expected.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    @Test
    public void getEventoTest() {
        EventoEntity result = eventoLogic.getEvento(data.get(0).getId());
        EventoEntity expected = enm.find(EventoEntity.class, data.get(0).getId());

        Assert.assertNotNull(expected);
        Assert.assertNotNull(result);
        Assert.assertEquals(expected.getId(), result.getId());
        Assert.assertEquals(expected.getName(), result.getName());
        Assert.assertEquals(expected.getDescripcion(), result.getDescripcion());
    }

    @Test
    public void deleteEventoTest() {
        EventoEntity entity = data.get(1);
        eventoLogic.deleteEvento(entity.getId());
        EventoEntity deleted = enm.find(EventoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    @Test
    public void updateEventoTest() {
        try {
            EventoEntity entity = data.get(0);
            EventoEntity pojoEntity = factory.manufacturePojo(EventoEntity.class);

            pojoEntity.setId(entity.getId());

            eventoLogic.updateEvento(pojoEntity);

            EventoEntity resp = enm.find(EventoEntity.class, entity.getId());

            Assert.assertEquals(pojoEntity.getId(), resp.getId());
            Assert.assertEquals(pojoEntity.getName(), resp.getName());
            Assert.assertEquals(pojoEntity.getDescripcion(), resp.getDescripcion());
        } catch (Exception ex) {
            Assert.fail(ex.getLocalizedMessage());
        }
    }

}