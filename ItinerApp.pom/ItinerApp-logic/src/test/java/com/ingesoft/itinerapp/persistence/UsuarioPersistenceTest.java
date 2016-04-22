package com.ingesoft.itinerapp.persistence;

import com.ingesoft.itinerapp.entities.UsuarioEntity;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import javax.inject.Inject;
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
 * @author jc.martha10
 */

@RunWith(Arquillian.class)
public class UsuarioPersistenceTest {

    @Inject
    private UsuarioPersistence usuarioPersistence;

    @PersistenceContext
    private EntityManager em;
    private final PodamFactory factory = new PodamFactoryImpl();

    @Inject
    UserTransaction utx;

    public UsuarioPersistenceTest() {
    }

    @Deployment
    public static JavaArchive createDeployment()
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(UsuarioEntity.class.getPackage())
                .addPackage(UsuarioPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
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
    public void createTest() {
        UsuarioEntity usuario = factory.manufacturePojo(UsuarioEntity.class);

        UsuarioEntity usuarioResultado = usuarioPersistence.create(usuario);

        assertNotNull(usuarioResultado);
        UsuarioEntity entity = em.find(UsuarioEntity.class, usuarioResultado.getId());
        assertEquals(usuario.getNombre(), entity.getNombre());
        assertEquals(usuario.getApellido(), entity.getApellido());
        assertEquals(usuario.getUsername(), entity.getUsername());
        assertEquals(usuario.getEmail(), entity.getEmail());
        assertEquals(usuario.getCedula(), entity.getCedula());
    }

    @Test
    public void updateTest() {
         UsuarioEntity entity = data.get(0);
        UsuarioEntity usuarioResultado = factory.manufacturePojo(UsuarioEntity.class);
        usuarioResultado.setId(entity.getId());

        usuarioPersistence.update(usuarioResultado);

        UsuarioEntity resp = em.find(UsuarioEntity.class, entity.getId());

        assertEquals(usuarioResultado.getNombre(), resp.getNombre());
        assertEquals(usuarioResultado.getApellido(), resp.getApellido());
        assertEquals(usuarioResultado.getUsername(), resp.getUsername());
        assertEquals(usuarioResultado.getEmail(), resp.getEmail());
        assertEquals(usuarioResultado.getCedula(), resp.getCedula());
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
        Assert.assertEquals(entity.getNombre(), newEntity.getNombre());
        Assert.assertEquals(entity.getApellido(), newEntity.getApellido());
        Assert.assertEquals(entity.getUsername(), newEntity.getUsername());
        Assert.assertEquals(entity.getEmail(), newEntity.getEmail());
        Assert.assertEquals(entity.getCedula(), newEntity.getCedula());
    }

    @Test
    public void deleteTest() {
        UsuarioEntity entity = data.get(0);
        usuarioPersistence.delete(entity.getId());
        UsuarioEntity deleted = em.find(UsuarioEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    @Test
    public void findTest() {
         UsuarioEntity usuario = factory.manufacturePojo(UsuarioEntity.class);

        UsuarioEntity usuarioResultado = usuarioPersistence.create(usuario);

        assertNotNull(usuarioResultado);
        UsuarioEntity entity = em.find(UsuarioEntity.class, usuarioResultado.getId());
        assertEquals(usuario.getNombre(), entity.getNombre());
        assertEquals(usuario.getApellido(), entity.getApellido());
        assertEquals(usuario.getUsername(), entity.getUsername());
        assertEquals(usuario.getEmail(), entity.getEmail());
        assertEquals(usuario.getCedula(), entity.getCedula());
    }
}
