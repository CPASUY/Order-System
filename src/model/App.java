package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class App {
	//Relations
	private ArrayList<Restaurant> restaurants;
	private ArrayList<Order> orders;
	//Methods
	public App() {
		this.restaurants=new ArrayList<Restaurant>();
		this.orders=new ArrayList<Order>();
	}
	public ArrayList<Restaurant> getRestaurants(){
		return restaurants;
	}
	public ArrayList<Order> getOrders(){
		return orders;
	}
	public ArrayList<Product> chooseProducts(int[]order,String nit){
		ArrayList<Product> products;
		ArrayList<Product> p;
		products=new ArrayList<Product>();
		p=new ArrayList<Product>();
		Restaurant restaurant=searchRestaurant(nit);
		if(restaurant!=null) {
			p=restaurant.getProducts();
			for(int s=0;s<order.length;s++) {
				int num=order[s];
				products.add(p.get(num-1))	;
			}
		}
		return products;
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
	public void addOrder(String code, Date date, String code_client, String nit,ArrayList<Product> products) {
		Order order=new Order(code,date,code_client,nit,products);
		orders.add(order);
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
		int count=1;
		r2=new ArrayList<Restaurant>();
		r2=compare(restaurants);
		String message="Restaurants List: \n";
		for(Restaurant myRestaurants:r2) {
			message += count+". "+ myRestaurants.getName()+ "\n";
			count++;
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
	public void importDataRestaurant(String name) throws IOException {
		File f=new File(name);
		BufferedReader br =new BufferedReader(new FileReader(f));
		String line=br.readLine();
		while(line!=null) {
			String [] parts=line.split("|");
			addRestaurant(parts[0],parts[1],parts[2]);
			line=br.readLine();
		}
		br.close();
	}
	public void importDataClient(String name,String nit) throws IOException {
		File f=new File(name);
		Restaurant restaurant=searchRestaurant(nit);
		if(restaurant!=null) {
			restaurant.importDataClient(f);
		}
	}
	public void importDataProduct(String name,String nit) throws IOException {
		File f=new File(name);
		Restaurant restaurant=searchRestaurant(nit);
		if(restaurant!=null) {
			restaurant.importDataProduct(f);
		}
	}
	public void importDataOrder(String name) throws IOException {
		File f=new File(name);
		BufferedReader br =new BufferedReader(new FileReader(f));
		String line=br.readLine();
		while(line!=null) {
			String [] parts=line.split("|");
			line=br.readLine();
		}
		br.close();
	}
}
