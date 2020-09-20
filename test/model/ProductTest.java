package model;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import exceptions.CeroCostException;
import exceptions.NegativeCostException;

class ProductTest {
	private Product p;
	private String code;
	private String name;
	private String description;
	private double cost;
	private String nit;
	public void setupStage1() throws NegativeCostException, CeroCostException{
		code="A49F";
		name="Coca-cola";
		description="Soda";
		cost=1500;
		nit="901,458,652-7";

		p=new Product(code,name,description,cost,nit);
	}
	public void setupStage2() throws NegativeCostException, CeroCostException{
		code="AG43F";
		name="Pizza";
		description="Napolitan pizza";
		cost=32000;
		nit="321,434,652-2";

		p=new Product(code,name,description,cost,nit);
	}
	@Test
	void testProduct() throws NegativeCostException, CeroCostException {
		setupStage1();
		
		assertEquals(code,p.getCode(),"The product code is wrong");
		assertEquals(name,p.getName(),"The product name is wrong");
		assertEquals(description,p.getDescription(),"The product description is wrong");
		assertEquals(cost,p.getCost(),"The product cost is wrong");
		assertEquals(nit,p.getNit(),"The product nit is wrong");
	}
	@Test
	void testSetCost(){
		double value=0;
		try {
			setupStage2();
			value=p.getCost();
			double newValue=-31000;
			p.setCost(newValue);
			fail("NegativeCostException expected");
		} catch (NegativeCostException e) {
			assertEquals(value,p.getCost());
		} catch (CeroCostException e) {
			fail("NegativeCostExceptione expected,not CeroCostException");
			
		}
		
		
	}

}
