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
	public void addRestaurant(String name,String nit,String manager){
		Restaurant restaurant=new Restaurant(name,nit,manager);
	}
	public void addClient(String id_type,String id_number,String name,String phone, String adress){
		Client temp;
		Client client=new Client(id_type,id_number,name,phone,adress);
		String [] parts=name.split(" ");
		if(clients.isEmpty()){
			clients.add(client);
		}
		else {
		for (int s= 0; s<clients.size() ;s++){
			String [] parts2=clients.get(s).getName().split(" ");
                if (parts[1].compareTo(parts2[1])<0) {
                    temp=clients.get(s);
                    clients.add(s,temp);
                    clients.add(s+1,client);
                	}
            	}
			}	
		}
	public void addProduct(String code,String name,String description,double cost,String nit){
		Product product=new Product(code,name,description,cost,nit);
	}
	public String toString() {
		String message="Product List: \n";
		for(Product myProducts:products) {
			message += myProducts.getName() + "-"+ myProducts.getDescription() + "-"+ myProducts.getCost();
		}
		return message;
	}
}