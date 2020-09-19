package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

import exceptions.CeroCostException;
import exceptions.NegativeCostException;

public class Restaurant implements Comparable<Restaurant>,Serializable {
	//Constants
	private static final long serialVersionUID = 1L;
	public static final String CLIENTS_FILE_NAME="data/clients.bbd";
	public static final String PRODUCTS_FILE_NAME="data/products.bbd";
	//Atributes
	private String name;
	private String nit;
	private String manager;
	//Relations
	private ArrayList<Product> products;
	private ArrayList<Client> clients;
	//Methods
	//Methods
	public Restaurant(String name,String nit,String manager) throws IOException {
		this.name=name;
		this.nit=nit;
		this.manager=manager;
		this.products=new ArrayList<Product>();
		this.clients=new ArrayList<Client>();
		try {
			loadClients();
			loadProducts();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		saveClients();
		saveProducts();
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
	public Product searchProduct(String code) {
		Product search=null;
		boolean find=false;
		for (int s=0;s<clients.size() && find==false;s++){
			if(products.get(s)!=null){
				if (products.get(s).getCode().equals(code)){
				search=products.get(s);
				find=true;
				}
			}
		}
		return search;
	}
	public void addClient(String id_type,String id_number,String name,String phone, String adress) {
		Client client= new Client(id_type,id_number,name,phone,adress);
		clients.add(client);
	}
	public void addProduct(String code,String name,String description,double cost,String nit) throws NegativeCostException, CeroCostException {
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
	public void updateClients(String document,String newId_number,String newId_type,String newName,int newPhone,String newAdress) {
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
	public void updateProducts(String code,String newCode,String newNit,String newName,String newDescription,double newCost) {
		Product p=searchProduct(code);
		if(p!=null) {
			if(newNit!=null) {
				p.setNit(newNit);
			}
			if(newCode!=null) {
				p.setCode(newCode);
			}
			if(newName!=null) {
				p.setName(newName);
			}
			if(newDescription!=null) {
				p.setDescription(newDescription);
			}
			if(newCost!=0.0) {
				p.setCost(newCost);
			}
		}
	}
	public void removeClient(String eliminateCode) {
		Client c=searchClient(eliminateCode);
		if(c!=null) {
			for(int s=0;s<clients.size();s++) {
				if(clients.get(s).getId_number().equals(eliminateCode)) {
					clients.remove(s);
				}
			}
		}
	}
	public void removeProduct(String eliminateCode) {
		Product p=searchProduct(eliminateCode);
		if(p!=null) {
			for(int s=0;s<products.size();s++) {
				if(products.get(s).getCode().equals(eliminateCode)) {
					products.remove(s);
				}
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
	private void saveClients() throws IOException {
		ObjectOutputStream oos= new ObjectOutputStream(new FileOutputStream(CLIENTS_FILE_NAME));
		oos.writeObject(clients);
		oos.close();
	}
	private void loadClients() throws IOException, ClassNotFoundException{
		File f=new File(CLIENTS_FILE_NAME);
		boolean load=false;
		if(f.exists()) {
			load=true;
			ObjectInputStream ois= new ObjectInputStream(new FileInputStream(f));
			clients=(ArrayList<Client>) ois.readObject();
			ois.close();
		}
	}
	private void saveProducts() throws IOException {
		ObjectOutputStream oos= new ObjectOutputStream(new FileOutputStream(PRODUCTS_FILE_NAME));
		oos.writeObject(products);
		oos.close();
	}
	private void loadProducts() throws IOException, ClassNotFoundException{
		File f=new File(PRODUCTS_FILE_NAME);
		boolean load=false;
		if(f.exists()) {
			load=true;
			ObjectInputStream ois= new ObjectInputStream(new FileInputStream(f));
			products=(ArrayList<Product>) ois.readObject();
			ois.close();
		}
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