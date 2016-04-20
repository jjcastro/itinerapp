/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ingesoft.itinerapp.ejbs;

import com.ingesoft.itinerapp.api.IItinerarioLogic;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import static org.junit.Assert.*;
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
    
    
    

    
    public ItinerarioLogicTest() {
    }

    @Test
    public void testSomeMethod() {
    }
    
}
