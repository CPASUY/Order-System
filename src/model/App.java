package model;

import java.util.ArrayList;
import java.util.List;

public class App {
	//Relations
	private List<Restaurant> restaurants;
	//Methods
	public App() {
		this.restaurants=new ArrayList<Restaurant>();
	}
	public Restaurant searchRestaurant(String nit) {
		Restaurant search=null;
		boolean find=false;
		for (int s=0;s<restaurants.size() && find==false;s++){
			if(restaurants.get(s)!=null){
				if (restaurants.get(s).getNit().equals(nit)){
				search=restaurants.get(s);
				find=true;
				}
			}
		}
		return search;
	}
	public void addRestaurant(String name,String nit,String manager){
		Restaurant restaurant=new Restaurant(name,nit,manager);
		restaurants.add(restaurant);
	}
	public void addClient(String nit,String id_type,String id_number,String name,String phone, String adress){
		Client temp;
		Restaurant restaurant=searchRestaurant(nit);
		if(restaurant!=null) {
			for(int w=0;w<restaurants.size();w++){
			Client client= new Client(id_type,id_number,name,phone,adress);
			String [] parts=name.split(" ");
				if(restaurant.getClients().isEmpty()){
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
	public void updateRestaurant(String nit) {
		
	}
	public void updateClient(String document) {
		
	}
	public void updateProduct(String code) {
		
	}
	public void updateOrder(String code) {
		
	}
}
