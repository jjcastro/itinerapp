package com.ingesoft.itinerapp.persistence;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.ingesoft.itinerapp.entities.UsuarioEntity;
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
public class UsuarioPersistenceTest {

    @Inject
    private UsuarioPersistence usuarioPersistence;
    @PersistenceContext
    private EntityManager em;
    private final PodamFactory factory = new PodamFactoryImpl();

    public UsuarioPersistenceTest() {
    }

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(UsuarioEntity.class.getPackage())
                .addPackage(UsuarioPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                //.addAsManifestResource("ItinerApp-logic\\src\\test\\META-INF\\persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml") //.addAsManifestResource("ItinerApp-logic\\src\\main\\resources\\META-INF\\beans.xml","beans.xml")
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

    private void clearData() {
        em.createQuery("delete from UsuarioEntity").executeUpdate();
    }

    private List<UsuarioEntity> data = new ArrayList<>();

    private void insertData() {
        for (int i = 0; i < 3; i++) {
            UsuarioEntity entity = factory.manufacturePojo(UsuarioEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }

    @Test
    public void createUsuarioTest() {
        UsuarioEntity nuevaEntidad = factory.manufacturePojo(UsuarioEntity.class);

        UsuarioEntity resultado = usuarioPersistence.create(nuevaEntidad);

        Assert.assertNotNull(resultado);
        UsuarioEntity entity = em.find(UsuarioEntity.class, resultado.getId());
        Assert.assertEquals(nuevaEntidad.getLugarNacimiento(),entity.getLugarNacimiento());
        Assert.assertEquals(nuevaEntidad.getNombre(),entity.getNombre());
        Assert.assertEquals(nuevaEntidad.getCorreo(),entity.getCorreo());
        Assert.assertEquals(nuevaEntidad.getPassword(),entity.getPassword());
        Assert.assertEquals(nuevaEntidad.getFechaNacimiento(),entity.getFechaNacimiento());
        Assert.assertEquals(nuevaEntidad.getFoto(),entity.getFoto());
        Assert.assertEquals(nuevaEntidad.getAdministrador(),entity.getAdministrador());
    }

    @Test
    public void getUsuariosTest() {
        List<UsuarioEntity> list = usuarioPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (UsuarioEntity ent : list) {
            boolean found = false;
            for (UsuarioEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    @Test
    public void getUsuarioTest() {
        UsuarioEntity entity = data.get(0);
        UsuarioEntity newEntity = usuarioPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getLugarNacimiento(),newEntity.getLugarNacimiento());
        Assert.assertEquals(entity.getNombre(),newEntity.getNombre());
        Assert.assertEquals(entity.getCorreo(),newEntity.getCorreo());
        Assert.assertEquals(entity.getPassword(),newEntity.getPassword());
        Assert.assertEquals(entity.getFechaNacimiento(),newEntity.getFechaNacimiento());
        Assert.assertEquals(entity.getFoto(),newEntity.getFoto());
        Assert.assertEquals(entity.getAdministrador(),newEntity.getAdministrador());
    }

    @Test
    public void deleteUsuarioTest() {
        UsuarioEntity entity = data.get(0);
        usuarioPersistence.delete(entity.getId());
        UsuarioEntity deleted = em.find(UsuarioEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    @Test
    public void updateUsuarioTest() {
        UsuarioEntity entity = data.get(0);
        UsuarioEntity newEntity = factory.manufacturePojo(UsuarioEntity.class);
        newEntity.setId(entity.getId());

        usuarioPersistence.update(newEntity);

        UsuarioEntity resp = em.find(UsuarioEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getNombre(), resp.getNombre());
        Assert.assertEquals(newEntity.getLugarNacimiento(),resp.getLugarNacimiento());
        Assert.assertEquals(newEntity.getNombre(),resp.getNombre());
        Assert.assertEquals(newEntity.getCorreo(),resp.getCorreo());
        Assert.assertEquals(newEntity.getPassword(),resp.getPassword());
        Assert.assertEquals(newEntity.getFechaNacimiento(),resp.getFechaNacimiento());
        Assert.assertEquals(newEntity.getFoto(),resp.getFoto());
        Assert.assertEquals(newEntity.getAdministrador(),resp.getAdministrador());
    }
}
