package com.ingesoft.itinerapp.persistence;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.ingesoft.itinerapp.entities.EventoEntity;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;


@RunWith(Arquillian.class)
public class EventoPersistenceTest
{

    @Inject
    private EventoPersistence eventoPersistence;
    @PersistenceContext
    private EntityManager enm;
    private final PodamFactory factory = new PodamFactoryImpl();



    public EventoPersistenceTest()
    {
    }

    public static final String DEPLOY = "Prueba";

    @Deployment
    public static JavaArchive createDeployment()
    {
       return ShrinkWrap.create(JavaArchive.class)
               .addPackage(EventoEntity.class.getPackage())
               .addPackage(EventoPersistence.class.getPackage())
               .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
               .addAsManifestResource("META-INF/beans.xml","beans.xml")
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
        }
        catch (Exception e)
        {
            e.printStackTrace();
            try
            {
                utx.rollback();
            }
            catch (Exception e1)
            {
               e1.printStackTrace();
          }
        }
    }

    private void clearData()
    {
        enm.createQuery("delete from EventoEntity").executeUpdate();
    }

    private List<EventoEntity> data = new ArrayList<>();

    private void insertData()
    {
        for (int i = 0; i < 3; i++)
        {
            EventoEntity entity = factory.manufacturePojo(EventoEntity.class);
            enm.persist(entity);
            data.add(entity);
        }
    }



    @Test
    public void createEventoTest()
    {
        EventoEntity nuevaEntidad = factory.manufacturePojo(EventoEntity.class);
        EventoEntity resultado = eventoPersistence.create(nuevaEntidad);
        Assert.assertNotNull(resultado);
        EventoEntity entity = enm.find(EventoEntity.class, resultado.getId());
        Assert.assertEquals(nuevaEntidad.getName(), entity.getName());
        Assert.assertEquals(nuevaEntidad.getDescripcion(), entity.getDescripcion());
    }

    @Test
   public void getEventosTest()
   {
       List<EventoEntity> list = eventoPersistence.findAll();
       Assert.assertEquals(data.size(), list.size());
       for (EventoEntity ent : list)
       {
           boolean found = false;
           for (EventoEntity entity : data)
           {
               if (ent.getId().equals(entity.getId()))
               {
                   found = true;
               }
           }
           Assert.assertTrue(found);
       }
   }

    @Test
    public void getRecuerdosTest() {
        List<EventoEntity> list = eventoPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (EventoEntity ent : list) {
            boolean found = false;
            for (EventoEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

   @Test
    public void getEventoTest()
    {
        EventoEntity entity = data.get(0);
        EventoEntity newEntity = eventoPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
        Assert.assertEquals(entity.getDescripcion(), newEntity.getDescripcion());
    }


    @Test
    public void deleteEventoTest()
    {
        EventoEntity entity = data.get(0);
        eventoPersistence.delete(entity.getId());
        EventoEntity deleted = enm.find(EventoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    @Test
    public void updateEventoTest()
    {
        EventoEntity entity = data.get(0);
        EventoEntity nuevaEntidad = factory.manufacturePojo(EventoEntity.class);
        nuevaEntidad.setId(entity.getId());
        eventoPersistence.update(nuevaEntidad);
        EventoEntity rta = enm.find(EventoEntity.class, entity.getId());
        Assert.assertEquals(nuevaEntidad.getName(), rta.getName());
        Assert.assertEquals(nuevaEntidad.getDescripcion(), rta.getDescripcion());
    }
}

