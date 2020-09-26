package model;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;


class RestaurantTest {
	private Restaurant r;
	private String name;
	private String nit;
	private String manager;
	private String idToSearch;
	private Client search;
	private String nameC;
	private String lname;
	private String id_number;
	private String id_type;
	private String phone;
	private String adress;
	public void setupStage1() throws IOException{
		name="Cheers";
		nit="901,458,652-7";
		manager="Justin Ross";
		
		r=new Restaurant(name,nit,manager);
	}
		public void setupStage2() throws IOException{
			name="Butacos";
			nit="903,458,652-7";
			manager="Ross Melia";
			
			r=new Restaurant(name,nit,manager);
			
			nameC="Michael";
			lname="Jasckson";
			id_number="1.1193.033.576";
			id_type="Cedula";
			phone="3043808681";
			adress="Carrera 92#54-42";

			r.addClient(id_type,id_number,nameC,lname,phone,adress);
			
		}

	@Test
	void testRestaurant () throws IOException {
		setupStage1();
		
		assertEquals(name,r.getName(),"The restaurant name is wrong");
		assertEquals(nit,r.getNit(),"The restaurant nit is wrong");
		assertEquals(manager,r.getManager(),"The restaurant manager is wrong");
	}
	@Test
	void testSearchClient() throws IOException {
		setupStage2();
		idToSearch="1.1193.033.576";
		search=r.searchClient(idToSearch);
		assertNotNull(search,"The client does not exist");
	}
}
