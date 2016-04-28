/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ingesoft.itinerapp.ejbs;

import com.ingesoft.itinerapp.api.IItinerarioLogic;
import com.ingesoft.itinerapp.entities.CiudadEntity;
import com.ingesoft.itinerapp.entities.ItinerarioEntity;
import com.ingesoft.itinerapp.exceptions.BusinessLogicException;
import com.ingesoft.itinerapp.persistence.ItinerarioPersistence;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class ItinerarioLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private IItinerarioLogic itinerarioLogic;
    
    @PersistenceContext
    private EntityManager em;
    
 
    @Inject
    private UserTransaction utx;
    
    private List<ItinerarioEntity> data = new ArrayList<ItinerarioEntity>();
    
    private List<CiudadEntity> ciudadData = new ArrayList<>();
    
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ItinerarioEntity.class.getPackage())
                .addPackage(ItinerarioLogic.class.getPackage())
                .addPackage(IItinerarioLogic.class.getPackage())
                .addPackage(ItinerarioPersistence.class.getPackage())
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
    
    
    public ItinerarioLogicTest() {
    }

  

     private void clearData() {
         
         for(ItinerarioEntity it : data){
             em.remove(it);
         }
         for(CiudadEntity c: ciudadData){
             em.remove(c);
         }

        em.createQuery("delete from ItinerarioEntity").executeUpdate();                 
        em.createQuery("delete from CiudadEntity").executeUpdate();
    }
     
    @Test
    public void createItinerarioTest() {
        ItinerarioEntity expected = factory.manufacturePojo(ItinerarioEntity.class);
           System.out.println("w" + expected.getName() + expected.getFechaEntrada() + expected.getFechaSalida());
        ItinerarioEntity created;
        try {
            created = itinerarioLogic.createItinerario(expected);
            System.out.println("com.ingesoft.itinerapp.ejbs.ItinerarioLogicTest.createItinerarioTest()" + created.getImagen());
            ItinerarioEntity result = em.find(ItinerarioEntity.class, created.getId());
            Assert.assertNotNull(result);
            Assert.assertNotNull(result);
            Assert.assertEquals(expected.getId(), result.getId());
            Assert.assertEquals(expected.getName(), result.getName());
            Assert.assertEquals(expected.getImagen(), result.getImagen());
        
            SimpleDateFormat sdf = new SimpleDateFormat("dd MM yyyy");
            Assert.assertEquals(sdf.format(expected.getFechaEntrada()), sdf.format(result.getFechaEntrada()));
            Assert.assertEquals(sdf.format(expected.getFechaSalida()), sdf.format(result.getFechaSalida()));

        } catch (BusinessLogicException ex) {
            Logger.getLogger(ItinerarioLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("Lo que sea" + expected.getId());


        

    }
     
     
     
     
     
     

    private void insertData() {
        //Crear ciudades y agregarlos al itinerarios
        for (int i = 0; i < 3; i++) {
            CiudadEntity ciudades = factory.manufacturePojo(CiudadEntity.class);
            System.out.println(ciudades.getFechaI());
            System.out.println(ciudades.getFechaF());
            em.persist(ciudades);
            ciudadData.add(ciudades);
            System.out.println(ciudadData.size());
           
        }
        
                
        List<CiudadEntity> ciudades = em.createQuery("select c from CiudadEntity c", CiudadEntity.class).getResultList();
        for (CiudadEntity ciudad : ciudades ) {
            System.out.println("c = " +  ciudad.getId());
        }

        for (int i = 0; i < 3; i++) {
            ItinerarioEntity entity = factory.manufacturePojo(ItinerarioEntity.class);
            
            SimpleDateFormat sdf = new SimpleDateFormat("dd MM yyyy");
            Date y = new Date(2016, 4, 25);
            Date yy = new Date(2016, 4, 29);

            entity.setFechaEntrada(y);
            entity.setFechaSalida(yy);

            CiudadEntity ciudad = ciudadData.get(0);
            entity.getCiudades().add(ciudad);
            
    //        entity.getCiudades().add(ciudadData.get(0));
            em.persist(entity);
            data.add(entity);
            System.out.println(data.size());
        }
       
    }
    
    
     @Test
    public void getItinerariosTest() {
        List<ItinerarioEntity> resultList = itinerarioLogic.getItinerarios();
        List<ItinerarioEntity> expectedList = em.createQuery("SELECT u from ItinerarioEntity u").getResultList();
        Assert.assertEquals(expectedList.size(), resultList.size());
        for (ItinerarioEntity expected : expectedList) {
            boolean found = false;
            for (ItinerarioEntity result : resultList) {
                if (result.getId().equals(expected.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    @Test
    public void getItinerarioTest() {
        ItinerarioEntity result = itinerarioLogic.getItinerario(data.get(0).getId());

        ItinerarioEntity expected = em.find(ItinerarioEntity.class, data.get(0).getId());
        System.out.println("Lo que sea" + expected.getId());

        Assert.assertNotNull(expected);
        Assert.assertNotNull(result);
        Assert.assertEquals(expected.getId(), result.getId());
        Assert.assertEquals(expected.getName(), result.getName());
        Assert.assertEquals(expected.getImagen(), result.getImagen());

        

        SimpleDateFormat sdf = new SimpleDateFormat("dd MM yyyy");
        Assert.assertEquals(sdf.format(expected.getFechaEntrada()), sdf.format(result.getFechaEntrada()));
        Assert.assertEquals(sdf.format(expected.getFechaSalida()), sdf.format(result.getFechaSalida()));

    }

    @Test
    public void deleteItinerarioTest() {
        System.out.println(data.size());
        ItinerarioEntity entity = data.get(1);
        itinerarioLogic.deleteItinerario(entity.getId());
        ItinerarioEntity expected = em.find(ItinerarioEntity.class, entity.getId());
        Assert.assertNull(expected);
    }

    @Test
    public void updateItinerarioTest() {
        ItinerarioEntity entity = data.get(0);
        ItinerarioEntity expected = factory.manufacturePojo(ItinerarioEntity.class);

        expected.setId(entity.getId());

        itinerarioLogic.updateItinerario(expected);

        ItinerarioEntity resp = em.find(ItinerarioEntity.class, entity.getId());
        System.out.println("Lo que sea" + resp.getId());

        Assert.assertNotNull(expected);
        Assert.assertEquals(expected.getId(), resp.getId());
        Assert.assertEquals(expected.getName(), resp.getName());
        Assert.assertEquals(expected.getImagen(), resp.getImagen());

        
        SimpleDateFormat sdf = new SimpleDateFormat("dd MM yyyy");
        Assert.assertEquals(sdf.format(expected.getFechaEntrada()), sdf.format(resp.getFechaEntrada()));
        Assert.assertEquals(sdf.format(expected.getFechaSalida()), sdf.format(resp.getFechaSalida()));
        
        
    }
    
    
    


    
     private Date getMaxDate() {
        Random r = new Random();
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, 9999);
        c.set(Calendar.MONTH, c.getActualMaximum(Calendar.MONTH));
        c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));
                
        return c.getTime();
    }
     
    private Date getMinDate() {
        Random r = new Random();
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, 2016);
        c.set(Calendar.MONTH, c.getActualMaximum(Calendar.MONTH));
        c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));
        return c.getTime();
    }
    
    
}
