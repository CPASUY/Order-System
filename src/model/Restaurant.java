package model;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
	//Atributes
	private String name;
	private String nit;
	private String manager;
	//Relations
	private List<Product> products;
	//Methods
	public Restaurant(String name,String nit,String manager) {
		this.name=name;
		this.nit=nit;
		this.manager=manager;
		this.products=new ArrayList<Product>();
	}
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNit() {
		return this.nit;
	}
	public void setNit(String nit) {
		this.nit = nit;
	}
	public String getManager() {
		return this.manager;
	}
	public void setManager(String manager) {
		this.manager = manager;
	}
	public void addRestaurant(String name,String nit,String manager){
		
	}
	public void addClient(String id_type,String id_number,String name,String phone, String adress){
		
	}
	public void addProduct(String code,String name,String description,String cost,String nit){
	
	}
	public String toString() {
		String message="Product List: \n";
		for(Product myProducts:products) {
			message += myProducts.getName() + "-"+ myProducts.getDescription() + "-"+ myProducts.getCost();
		}
		return message;
	}
}