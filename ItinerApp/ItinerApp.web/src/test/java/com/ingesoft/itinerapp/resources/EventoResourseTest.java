/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*package com.ingesoft.itinerapp.resources;

import com.ingesoft.itinerapp.converter.EventoConverter;
import com.ingesoft.itinerapp.dtos.EventoDTO;
import com.ingesoft.itinerapp.dtos.CiudadDTO;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import org.jboss.arquillian.container.test.api.Deployment;
import org.junit.runner.RunWith;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.BeforeClass;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author s.robayo222
 */
/*
@RunWith(Arquillian.class)
public class EventoResourseTest
{
    private final int OK = Response.Status.OK.getStatusCode();
    private final int CREATED = Response.Status.CREATED.getStatusCode();
    private final int NO_CONTENT = Response.Status.NO_CONTENT.getStatusCode();

    private final String eventoPath = "eventos";

    private final static ArrayList<EventoDTO> oraculo = new ArrayList<>();
    private WebTarget target;
    private final String apiPath = "api";
    private static PodamFactory factory = new PodamFactoryImpl();

    @ArquillianResource
    private URL deploymentURL;

    @Deployment(testable = false)
    public static WebArchive createDeployment()
    {
        return ShrinkWrap.create(WebArchive.class)
                .addAsLibraries(Maven.resolver()
                        .resolve("com.ingesoft:ItinerApp-logic:1.0-SNAPSHOT")
                        .withTransitivity().asFile())
                .addPackage(eventoResource.class.getPackage())
                .addPackage(EventoDTO.class.getPackage())
                .addPackage(EventoConverter.class.getPackage())
                .addPackage(EJBExceptionMapper.class.getPackage())
                .addPackage(DateAdapter.class.getPackage())
                .addPackage(CreatedFilter.class.getPackage())
                .addAsResource("Meta-INF/persistence.xml","Meta-INF/persistence.xml")
                .addAsWebInfResource(new File("src/main/webapp/WEB-INF/beans.xml"))
                .setWebXML(new File("src/main/webapp/WEB-INF/web.xml"));
    }

    public static void insertData()
    {
        for (int i =0; i<5; i++)
        {
            EventoDTO evento = factory.manufacturePojo(EventoDTO.class);
            evento.setName("e"+i);
            evento.setId(i+1L);

            CiudadDTO ciudad = factory.manufacturePojo(CiudadDTO.class);
            ciudad.setId(i+1L);
            ciudad.setNombre("lol");

            evento.setCiudad(ciudad.getNombre());
        }
    }

    @BeforeClass
    public static void setUp()
    {
        insertData();
    }
}
*/
