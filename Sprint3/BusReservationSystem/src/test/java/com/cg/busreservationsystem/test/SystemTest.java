package com.cg.busreservationsystem.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.cg.busreservationsystem.dao.UserDao;
import com.cg.busreservationsystem.dao.UserDaoImpl;
import com.cg.busreservationsystem.dto.Bus;

class SystemTest {
	UserDao userDao;
	Bus bus1;
	Bus bus2;
	
	@BeforeEach
	void beforeEachTest() {
		userDao = new UserDaoImpl();
		
		bus1 = null;
		bus2 = new Bus();
		bus2.setBusClass("ac");
		bus2.setBusType("sleeper");
		bus2.setBusName("volvo");
		bus2.setSource("mumbai");
		bus2.setDestination("pune");
		//List<E> 
		//bus2.setDayOfJourney(dayOfJourney);
		
		
	}
	
	
	
	@Test
	void test() {
		//fail("Not yet implemented");
		
		
		
	}
	
	@AfterEach
	void afterEachTest() {
		userDao =null;
	}

}
