/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ingesoft.itinerapp.persistence;

import com.ingesoft.itinerapp.entities.ItinerarioEntity;
import java.text.SimpleDateFormat;
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
 * @author johnycsc
 */
@RunWith(Arquillian.class)
public class ItinerarioPersistenceTest {
    
    @Inject
    private ItinerarioPersistence itinerarioPersistence;
    
    @PersistenceContext
    private EntityManager em;
    
    private final PodamFactory factory = new PodamFactoryImpl();
    
    public ItinerarioPersistenceTest() {
    }

    public static final String DEPLOY = "Prueba";
    
    
    @Deployment
    public static JavaArchive createDeployment()
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ItinerarioEntity.class.getPackage())
                .addPackage(RecuerdoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                //.addAsManifestResource("ItinerApp-logic\\src\\test\\META-INF\\persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml")
                //.addAsManifestResource("ItinerApp-logic\\src\\main\\resources\\META-INF\\beans.xml","beans.xml")
                ;
                
        
    }
    
    @Inject
    UserTransaction utx;
    
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
    
    private List<ItinerarioEntity> data = new ArrayList<>();
    
     private void insertData() {
        for (int i = 0; i < 3; i++) {
            ItinerarioEntity entity = factory.manufacturePojo(ItinerarioEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
    
    private void clearData() {
        em.createQuery("delete from ItinerarioEntity").executeUpdate();
    }
    
    
    
    
    @Test
    public void createItinerarioTest() 
    {
        ItinerarioEntity nuevaEntidad = factory.manufacturePojo(ItinerarioEntity.class);
        try {
                ItinerarioEntity resultado = itinerarioPersistence.create(nuevaEntidad);
                Assert.assertNotNull(resultado);
        ItinerarioEntity entity = em.find(ItinerarioEntity.class, resultado.getId());
        Assert.assertEquals(nuevaEntidad.getName(), entity.getName());
        
         SimpleDateFormat sdf = new SimpleDateFormat("dd MM yyyy");
        
        Assert.assertEquals(sdf.format(nuevaEntidad.getFechaEntrada()), sdf.format(entity.getFechaEntrada()));
        Assert.assertEquals(sdf.format(nuevaEntidad.getFechaSalida()), sdf.format(entity.getFechaSalida()));
        Assert.assertEquals(nuevaEntidad.getImagen(), entity.getImagen());

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
    }
    
    @Test
    public void getItinerariosTest() {
        List<ItinerarioEntity> list = itinerarioPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (ItinerarioEntity ent : list) {
            boolean found = false;
            for (ItinerarioEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
     @Test
    public void getItinerarioTest() {
        ItinerarioEntity entity = data.get(0);
        ItinerarioEntity newEntity = itinerarioPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
        Assert.assertEquals(entity.getImagen(), newEntity.getImagen());
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd MM yyyy");

        
        Assert.assertEquals(sdf.format(entity.getFechaEntrada()), sdf.format(newEntity.getFechaEntrada()));
        Assert.assertEquals(sdf.format(entity.getFechaSalida()), sdf.format(newEntity.getFechaSalida()));
    }
    
       @Test
    public void deleteItinerarioTest() {
        ItinerarioEntity entity = data.get(0);
        itinerarioPersistence.delete(entity.getId());
        ItinerarioEntity deleted = em.find(ItinerarioEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
    
     @Test
    public void updateItinerarioTest() {
        
        ItinerarioEntity entity = data.get(0);
        
        ItinerarioEntity newEntity = factory.manufacturePojo(ItinerarioEntity.class);
        newEntity.setId(entity.getId());
             
        try {
            itinerarioPersistence.update(newEntity);
            data.set(0, newEntity);
        
            ItinerarioEntity resp = em.find(ItinerarioEntity.class, entity.getId());

            SimpleDateFormat sdf = new SimpleDateFormat("dd MM yyyy");
            Assert.assertEquals(sdf.format(newEntity.getFechaEntrada()), sdf.format(resp.getFechaEntrada()));
            Assert.assertEquals(sdf.format(newEntity.getFechaSalida()), sdf.format(resp.getFechaSalida()));

            Assert.assertEquals(newEntity.getName(), resp.getName());
            
            Assert.assertEquals(newEntity.getImagen(), resp.getImagen());
        
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}
