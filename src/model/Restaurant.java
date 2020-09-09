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
	private List<Client> clients;
	//Methods
	public Restaurant(String name,String nit,String manager) {
		this.name=name;
		this.nit=nit;
		this.manager=manager;
		this.products=new ArrayList<Product>();
		this.clients=new ArrayList<Client>();
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
	public List<Client> getClients(){
		return clients;
	}
	public List<Product> getProducts(){
		return products;
	}
}