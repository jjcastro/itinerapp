package com.ingesoft.itinerapp.persistence;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.ingesoft.itinerapp.entities.RecuerdoEntity;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;


@RunWith(Arquillian.class)
public class RecuerdoPersistenceTest {
    
    @Inject
    private RecuerdoPersistence recuerdoPersistence;
    @PersistenceContext
    private EntityManager em;
    private final PodamFactory factory = new PodamFactoryImpl();
    
   
    
    public RecuerdoPersistenceTest() {
    }

     public static final String DEPLOY = "Prueba";

    @Deployment
    public static JavaArchive createDeployment()
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(RecuerdoEntity.class.getPackage())
                .addPackage(RecuerdoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                //.addAsManifestResource("ItinerApp-logic\\src\\test\\META-INF\\persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml")
                //.addAsManifestResource("ItinerApp-logic\\src\\main\\resources\\META-INF\\beans.xml","beans.xml")
                ;
                
        
    }
     /*
      @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
                .addPackage(RecuerdoEntity.class.getPackage())
                .addPackage(RecuerdoEntity.class.getPackage())
                .addAsResource("ItinerApp-logic\\src\\test\\META-INF\\persistence.xml","persistence.xml")
                .addAsWebInfResource(ItinerApp-logic\\src\\main\\resources\\META-INF\\beans.xml","beans.xml");
    } */
    
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

    private void clearData() {
        em.createQuery("delete from RecuerdoEntity").executeUpdate();
    }

    private List<RecuerdoEntity> data = new ArrayList<>();

    private void insertData() {
        for (int i = 0; i < 3; i++) {
            RecuerdoEntity entity = factory.manufacturePojo(RecuerdoEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
    
    
    
    @Test
    public void createRecuerdoTest() 
    {
        RecuerdoEntity nuevaEntidad = factory.manufacturePojo(RecuerdoEntity.class);
        
        RecuerdoEntity resultado = recuerdoPersistence.create(nuevaEntidad);
        
        Assert.assertNotNull(resultado);
        RecuerdoEntity entity = em.find(RecuerdoEntity.class, resultado.getId());
        Assert.assertEquals(nuevaEntidad.getName(), entity.getName());
        Assert.assertEquals(nuevaEntidad.getDescripcion(), entity.getDescripcion());
        Assert.assertEquals(nuevaEntidad.getImagen(), entity.getImagen());
    }
    
    @Test
    public void getRecuerdosTest() {
        List<RecuerdoEntity> list = recuerdoPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (RecuerdoEntity ent : list) {
            boolean found = false;
            for (RecuerdoEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    @Test
    public void getRecuerdoTest() {
        RecuerdoEntity entity = data.get(0);
        RecuerdoEntity newEntity = recuerdoPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
        Assert.assertEquals(entity.getDescripcion(), newEntity.getDescripcion());
        Assert.assertEquals(entity.getImagen(), newEntity.getImagen());
    }
    
    @Test
    public void deleteRecuerdoTest() {
        RecuerdoEntity entity = data.get(0);
        recuerdoPersistence.delete(entity.getId());
        RecuerdoEntity deleted = em.find(RecuerdoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    @Test
    public void updateRecuerdoTest() {
        RecuerdoEntity entity = data.get(0);
        RecuerdoEntity newEntity = factory.manufacturePojo(RecuerdoEntity.class);
        newEntity.setId(entity.getId());

        recuerdoPersistence.update(newEntity);

        RecuerdoEntity resp = em.find(RecuerdoEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
        Assert.assertEquals(newEntity.getDescripcion(), entity.getDescripcion());
        Assert.assertEquals(newEntity.getImagen(), entity.getImagen());
    }
}
