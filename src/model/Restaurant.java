package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Restaurant implements Comparable<Restaurant> {
	//Atributes
	private String name;
	private String nit;
	private String manager;
	//Relations
	private ArrayList<Product> products;
	private ArrayList<Client> clients;
	//Methods
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
	public ArrayList<Client> getClients(){
		return clients;
	}
	public ArrayList<Product> getProducts(){
		return products;
	}
	public Client searchClient(String id_number) {
		Client search=null;
		boolean find=false;
		for (int s=0;s<clients.size() && find==false;s++){
			if(clients.get(s)!=null){
				if (clients.get(s).getId_number().equals(id_number)){
				search=clients.get(s);
				find=true;
				}
			}
		}
		return search;
	}
	public void addClient(String id_type,String id_number,String name,int phone, String adress) {
		Client client= new Client(id_type,id_number,name,phone,adress);
		String [] parts=name.split(" ");
		Client temp;
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
	public void addProduct(String code,String name,String description,double cost,String nit) {
		Product product=new Product(code,name,description,cost,nit);
		products.add(product);
	}
	public String toStringProducts() {
		String message="Product List: \n";
		for(Product myProducts:products) {
			message += myProducts.getName() + "-"+ myProducts.getDescription() + "-"+ myProducts.getCost();
		}
		return message;
	}
	public ArrayList<Client> bubble(ArrayList<Client> clients){
		for(int s=0; s<clients.size(); s++){
		    for(int m=0; m<clients.size()-1; m++){
		        if(clients.get(m).getPhone()>clients.get(m+2).getPhone()){
		        	Client temp=clients.get(m+1);
		        	Client now=clients.get(m);
		        	clients.add(m+1,now);
		        	clients.add(m,temp);
		        }
		    }
		}
		return clients;
	  }
	public void updateClient(String document,String newId_number,String newId_type,String newName,int newPhone,String newAdress) {
		Client c=searchClient(document);
		if(c!=null) {
			if(newId_number!=null) {
				c.setId_number(newId_number);
			}
			if(newId_type!=null) {
				c.setId_type(newId_type);
			}
			if(newName!=null) {
				c.setName(newName);
			}
			if(newPhone!=0) {
				c.setPhone(newPhone);
			}
			if(newAdress!=null) {
				c.setAdress(newAdress);
			}
		}
	}
	public String toStringClients() {
		ArrayList<Client> clients2;
		clients2=new ArrayList<Client>();
		clients2=bubble(clients);
		String message="Clients List: \n";
		for(Client myClients:clients2) {
			message += myClients.getName() + "-"+ myClients.getId_type() + "-"+ myClients.getId_number()+ "-"+ myClients.getPhone();
		}
		return message;
	}
	public void importDataClient(File file) throws IOException {
		BufferedReader br =new BufferedReader(new FileReader(file));
		String line=br.readLine();
		while(line!=null) {
			String [] parts=line.split("|");
			addClient(parts[0],parts[1],parts[2],Integer.parseInt(parts[3]),parts[4]);
			line=br.readLine();
		}
		br.close();
	}
	public void importDataProduct(File file) throws IOException {
		BufferedReader br =new BufferedReader(new FileReader(file));
		String line=br.readLine();
		while(line!=null) {
			String [] parts=line.split("|");
			addProduct(parts[0],parts[1],parts[2],Double.parseDouble(parts[3]),parts[4]);
			line=br.readLine();
		}
		br.close();
	}
	@Override
	public int compareTo(Restaurant r) {
		return name.compareToIgnoreCase(r.getName());
	}
	public void sortByNameClient() {
			NameComparator nc = new NameComparator();
			Collections.sort(clients,nc);
			
	}
	public void sortByPhone() {
		PhoneComparator nc = new PhoneComparator();
		Collections.sort(clients,nc);
	}
}