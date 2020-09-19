package model;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

class ProductTest {
	private Product p;
	private String code;
	private String name;
	private String description;
	private double cost;
	private String nit;
	public void setupStage1(){
		
	}
	@Test
	void testProduct() throws IOException {
		setupStage1();
		code="A49F";
		name="Coca-cola";
		description="Soda";
		cost=1500;
		nit="901,458,652-7";

		p=new Product(code,name,description,cost,nit);
		
		assertEquals(code,p.getCode(),"The product code is wrong");
		assertEquals(name,p.getName(),"The product name is wrong");
		assertEquals(description,p.getDescription(),"The product description is wrong");
		assertEquals(cost,p.getCost(),"The product cost is wrong");
		assertEquals(nit,p.getNit(),"The product nit is wrong");
	}

}
