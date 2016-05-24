package com.ingesoft.itinerapp.ejbs;

import com.ingesoft.itinerapp.api.IUsuarioLogic;
import com.ingesoft.itinerapp.entities.UsuarioEntity;
import com.ingesoft.itinerapp.persistence.UsuarioPersistence;
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
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@RunWith(Arquillian.class)
public class UsuarioLogicTest {

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private IUsuarioLogic usuarioLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<UsuarioEntity> data = new ArrayList<UsuarioEntity>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(UsuarioEntity.class.getPackage())
                .addPackage(UsuarioLogic.class.getPackage())
                .addPackage(IUsuarioLogic.class.getPackage())
                .addPackage(UsuarioPersistence.class.getPackage())
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
        em.createQuery("delete from UsuarioEntity").executeUpdate();
    }

    private void insertData() {

        for (int i = 0; i < 3; i++) {
            UsuarioEntity entity = factory.manufacturePojo(UsuarioEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }

    @Test
    public void createUsuarioTest() {
        UsuarioEntity expected = factory.manufacturePojo(UsuarioEntity.class);
        UsuarioEntity created = usuarioLogic.createUsuario(expected);

        UsuarioEntity result = em.find(UsuarioEntity.class, created.getId());

        Assert.assertNotNull(result);
        Assert.assertNotNull(result);
        Assert.assertEquals(expected.getId(), result.getId());
        Assert.assertEquals(expected.getLugarNacimiento(),result.getLugarNacimiento());
        Assert.assertEquals(expected.getNombre(),result.getNombre());
        Assert.assertEquals(expected.getCorreo(),result.getCorreo());
        Assert.assertEquals(expected.getPassword(),result.getPassword());
        Assert.assertEquals(expected.getFechaNacimiento(),result.getFechaNacimiento());
        Assert.assertEquals(expected.getFoto(),result.getFoto());
        Assert.assertEquals(expected.getAdministrador(),result.getAdministrador());
    }

    @Test
    public void getUsuariosTest() {
        List<UsuarioEntity> resultList = usuarioLogic.getUsuarios();
        List<UsuarioEntity> expectedList = em.createQuery("SELECT u from UsuarioEntity u").getResultList();
        Assert.assertEquals(expectedList.size(), resultList.size());
        for (UsuarioEntity expected : expectedList) {
            boolean found = false;
            for (UsuarioEntity result : resultList) {
                if (result.getId().equals(expected.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    @Test
    public void getUsuarioTest() {
        UsuarioEntity result = usuarioLogic.getUsuario(data.get(0).getId());

        UsuarioEntity expected = em.find(UsuarioEntity.class, data.get(0).getId());

        Assert.assertNotNull(expected);
        Assert.assertNotNull(result);
        Assert.assertEquals(expected.getId(), result.getId());
        Assert.assertEquals(expected.getLugarNacimiento(),result.getLugarNacimiento());
        Assert.assertEquals(expected.getNombre(),result.getNombre());
        Assert.assertEquals(expected.getCorreo(),result.getCorreo());
        Assert.assertEquals(expected.getPassword(),result.getPassword());
        Assert.assertEquals(expected.getFechaNacimiento(),result.getFechaNacimiento());
        Assert.assertEquals(expected.getFoto(),result.getFoto());
        Assert.assertEquals(expected.getAdministrador(),result.getAdministrador());
    }

    @Test
    public void deleteUsuarioTest() {
        UsuarioEntity entity = data.get(1);
        usuarioLogic.deleteUsuario(entity.getId());
        UsuarioEntity expected = em.find(UsuarioEntity.class, entity.getId());
        Assert.assertNull(expected);
    }

    @Test
    public void updateUsuarioTest() {
        UsuarioEntity entity = data.get(0);
        UsuarioEntity expected = factory.manufacturePojo(UsuarioEntity.class);

        expected.setId(entity.getId());

        usuarioLogic.updateUsuario(expected);

        UsuarioEntity result = em.find(UsuarioEntity.class, entity.getId());

        Assert.assertNotNull(expected);
        Assert.assertEquals(expected.getId(), result.getId());
        Assert.assertEquals(expected.getLugarNacimiento(),result.getLugarNacimiento());
        Assert.assertEquals(expected.getNombre(),result.getNombre());
        Assert.assertEquals(expected.getCorreo(),result.getCorreo());
        Assert.assertEquals(expected.getPassword(),result.getPassword());
        Assert.assertEquals(expected.getFechaNacimiento(),result.getFechaNacimiento());
        Assert.assertEquals(expected.getFoto(),result.getFoto());
        Assert.assertEquals(expected.getAdministrador(),result.getAdministrador());
    }

}