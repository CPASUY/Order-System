package model;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

class ClientTest {
	private Client c;
	private String name;
	private String lname;
	private String id_number;
	private String id_type;
	private String phone;
	private String adress;
	public void setupStage1(){
		name="Michael Jasckson";
		id_number="1.1193.033.576";
		id_type="Cedula";
		phone="3043808681";
		adress="Carrera 92#54-42";

		c=new Client(id_type,id_number,name,lname,phone,adress);
	}
	public void setupStage2(){
		name="Jack";
		lname="Sparrow";
		id_number="1.123.653.321";
		id_type="Cedula";
		phone="3531308681";
		adress="Carrera 21#55-42";

		c=new Client(id_type,id_number,name,lname,phone,adress);
	}
	@Test
	void testClient () throws IOException {
		setupStage1();
		
		assertEquals(name,c.getName(),"The client name is wrong");
		assertEquals(lname,c.getLast_name(),"The client last name is wrong");
		assertEquals(id_number,c.getId_number(),"The client id number is wrong");
		assertEquals(id_type,c.getId_type(),"The client id type is wrong");
		assertEquals(phone,c.getPhone(),"The client phone is wrong");
		assertEquals(adress,c.getAdress(),"The client adress is wrong");
	}
	@Test
	void testSetId_type () {
		setupStage2();
		
		String newId_type="Passport";
		c.setId_type(newId_type);
		assertEquals(newId_type,c.getId_type(),"The id number is wrong");
	}

}
