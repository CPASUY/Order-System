package model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.Test;

import exceptions.CeroCostException;
import exceptions.NegativeCostException;

class OrderTest {
	private Order o;
	private String code;
	private Date date;
	private String code_client;
	private String nit;
	private Product p;
	private String codeP;
	private String name;
	private String description;
	private double cost;
	private String nitP;
	private ArrayList<Product> products;
	
	public void setupStage1() throws NegativeCostException, CeroCostException{
		codeP="A49F";
		name="Coca-cola";
		description="Soda";
		cost=1500;
		nitP="902,458,652-7";
		p=new Product(codeP,name,description,cost,nitP);
		products=new ArrayList<Product>();
	}
	public void setupStage2() throws NegativeCostException, CeroCostException{
		codeP="4349F";
		name="Chocorramo";
		description="Cake";
		cost=1000;
		nitP="202,358,652-7";
		p=new Product(codeP,name,description,cost,nitP);
		products=new ArrayList<Product>();
		products.add(p);
		code="A23F";
		date=new Date();
		code_client="1.323.323.429";
		nit="901,458,652-7";

		o=new Order(code,date,code_client,nit,products);
	}
	@Test
	void testOrder() throws NegativeCostException, CeroCostException {
		setupStage1();
		products.add(p);
		code="A49F";
		date=new Date();
		code_client="1.193.033.579";
		nit="901,458,652-7";

		o=new Order(code,date,code_client,nit,products);
		
		assertEquals(code,o.getCode(),"The order code is wrong");
		assertEquals(date,o.getDate(),"The order date is wrong");
		assertEquals(code_client,o.getCode_client(),"The order code client is wrong");
		assertEquals(nit,o.getNit(),"The order nit is wrong");
		assertEquals(products,o.getOrderList(),"The order products is wrong");
	}
	@Test
	void testSetCode_client() throws NegativeCostException, CeroCostException {
		setupStage2();
		
		String newCode_c="1.543.757.757";
		o.setCode_client(newCode_c);
		assertEquals(newCode_c,o.getCode_client(),"The code client is wrong");
	}

}
