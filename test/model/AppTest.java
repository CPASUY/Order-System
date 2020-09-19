package model;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

class AppTest {
	private App app;
	private String name;
	private String nit;
	private String manager;
	private String searchNit;
	private Restaurant search;
	public void setupStage1() throws IOException{
		app=new App();
	}
	public void setupStage2() throws IOException{
		app=new App();
		name="Cheers";
		nit="901,458,652-7";
		manager="Justin Ross";
		app.addRestaurant(name, nit, manager);
		
	}
	@Test
	void testApp() throws IOException {
		setupStage1();
		
		assertEquals(0,app.getRestaurants().size(),"The restaurants list is not empty");
		assertEquals(0,app.getOrders().size(),"The order list is not empty");
	}
	@Test
	void testSearchRestaurant() throws IOException {
		setupStage2();
		nit="901,458,652-7";
		search=app.searchRestaurant(nit);
		assertNotNull(search,"The restaurant does not exist");
	}
}
