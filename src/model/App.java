package model;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
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
	public Order searchOrder(String code) {
		Order search=null;
		boolean find=false;
		for (int s=0;s<orders.size() && find==false;s++){
			if(orders.get(s)!=null){
				if (orders.get(s).getCode().equals(code)){
				search=orders.get(s);
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
	public String toStringRestaurants() {
		String message="Restaurants List: \n";
		for(Restaurant myR:restaurants) {
			message += myR.getName() + "-"+ myR.getNit() + "-"+ myR.getManager()+ "\n";
		}
		return message;
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
	public void updateRestaurant(String nit,String newNit,String newName,String newManager) {
		Restaurant restaurant=searchRestaurant(nit);
		if(restaurant!=null) {
			if(newNit!=null) {
				restaurant.setNit(newNit);
			}
			if(newName!=null) {
				restaurant.setName(newName);
			}
			if(newManager!=null) {
				restaurant.setManager(newManager);
			}
		}
	}
	public void updateClient(String nit,String document,String newId_number,String newId_type,String newName,int newPhone,String newAdress) {
		Restaurant restaurant=searchRestaurant(nit);
		if(restaurant!=null) {
			restaurant.updateClients(document,newId_number,newId_type,newName,newPhone,newAdress);
		}
	}
	public void updateProducts(String code,String newCode,String nit,String newNit,String newName,String newDescription,double newCost) {
		Restaurant restaurant=searchRestaurant(nit);
		if(restaurant!=null) {
			restaurant.updateProducts(code,newCode,newNit,newName,newDescription,newCost);
		}
	}
	public void updateOrder(String order_Code,String newCode,String newNit) {
		Order or=searchOrder(order_Code);
		if(or!=null) {
			if(newCode!=null) {
				or.setCode_client(newCode);
			}
			if(newNit!=null) {
				or.setNit(newNit);
			}
		}
	}
	public void removeRestaurant(String nit) {
		Restaurant restaurant=searchRestaurant(nit);
		if(restaurant!=null) {
			for(int s=0;s<restaurants.size();s++) {
				if(restaurants.get(s).getNit().equals(nit)) {
					restaurants.remove(s);
				}
			}
		}
	}
	public void removeClient(String nit,String eliminateCode) {
		Restaurant restaurant=searchRestaurant(nit);
		if(restaurant!=null) {
			restaurant.removeClient(eliminateCode);
		}
	}
	public void removeProduct(String nit,String eliminateCode) {
		Restaurant restaurant=searchRestaurant(nit);
		if(restaurant!=null) {
			restaurant.removeProduct(eliminateCode);
		}
	}
	public void removeOrder(String eliminateCode) {
		Order or=searchOrder(eliminateCode);
		if(or!=null) {
			for(int s=0;s<orders.size();s++) {
				if(orders.get(s).getNit().equals(eliminateCode)) {
					orders.remove(s);
				}
			}
		}
	}
	public void sortByNameRest() {
		Collections.sort((List<Restaurant>) restaurants);
	}
	public void sortByClient(String nit) {
		Restaurant restaurant=searchRestaurant(nit);
		if(restaurant!=null) {
			restaurant.sortByNameClient();
		}
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
