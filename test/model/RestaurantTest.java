package model;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;


class RestaurantTest {
	private Restaurant r;
	private String name;
	private String nit;
	private String manager;
	public void setupStage1(){
		
	}
	public void setupStage2() throws IOException{
	
		
	}
	@Test
	void testRestaurant () throws IOException {
		setupStage1();
		name="Cheers";
		nit="901,458,652-7";
		manager="Identification";

		r=new Restaurant(name,nit,manager);
		
		assertEquals(name,r.getName(),"The restaurant name is wrong");
		assertEquals(nit,r.getNit(),"The restaurant nit is wrong");
		assertEquals(manager,r.getManager(),"The restaurant manager is wrong");
	}
}
