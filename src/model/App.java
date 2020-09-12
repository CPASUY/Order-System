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
	public void addClient(String nit,String id_type,String id_number,String name,int phone, String adress){
		Restaurant restaurant=searchRestaurant(nit);
		if(restaurant!=null) {
			restaurant.addClient(id_type,id_number,name,phone,adress);
		}	
	}
	public void addProduct(String code,String name,String description,double cost,String nit){
		Restaurant restaurant=searchRestaurant(nit);
		if(restaurant!=null) {
			restaurant.addProduct(code,name,description,cost,nit);
		}
	}
	public String toStringProducts(String nit) {
		String message="";
		Restaurant restaurant=searchRestaurant(nit);
		if(restaurant!=null) {
			message=restaurant.toStringProducts();
		}
		return message;
	}
	public String toStringClients(String nit) {
		String message="";
		Restaurant restaurant=searchRestaurant(nit);
		if(restaurant!=null) {
			message=restaurant.toStringClients();
		}
		return message;
	}
	public List<Restaurant> compare(List<Restaurant> restaurants){
		for (int s=0;s< restaurants.size();s++){
            for (int m= s+1;m< restaurants.size();m++) {
                if (restaurants.get(s).getName().compareTo(restaurants.get(m).getName())>0){
                    Restaurant temp = restaurants.get(s);
                    Restaurant now=restaurants.get(m);
                    restaurants.add(s,now);
                    restaurants.add(m,temp);
                }
            }
        }
		return restaurants;
	}
	public String toString() {
		List<Restaurant> r2;
		r2=new ArrayList<Restaurant>();
		r2=compare(restaurants);
		String message="Restaurants List: \n";
		for(Restaurant myRestaurants:r2) {
			message += myRestaurants.getName();
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
