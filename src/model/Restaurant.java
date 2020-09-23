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
	/**
	 * 
	 * @param name!=null
	 * @param nit!=null
	 * @param manager!=null
	 */
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
	/** getName
     * Method to provide the name of the restaurant
     * @return String name
     */
	public String getName() {
		return this.name;
	}
	/** setName
     * Method to changes the name of the restaurant
     * @param name-name of the restaurant!= null
     */
	public void setName(String name) {
		this.name = name;
	}
	/** getNit
     * Method to provide the nit of the restaurant
     * @return String nit
     */
	public String getNit() {
		return this.nit;
	}
	/** setNit
     * Method to changes the nit of the restaurant
     * @param nit-nit of the restaurant!= null
     */
	public void setNit(String nit) {
		this.nit = nit;
	}
	/** getManager
     * Method to provide the manager of the restaurant
     * @return String manager
     */
	public String getManager() {
		return this.manager;
	}
	/** setManager
     * Method to changes the manager of the restaurant
     * @param manager-manager of the restaurant!= null
     */
	public void setManager(String manager) {
		this.manager = manager;
	}
	/** getClients
     * Method to provide the clients list of the restaurant
     * @return ArrayList<Client> clients
     */
	public ArrayList<Client> getClients(){
		return clients;
	}
	/** getProducts
     * Method to provide the products list of the restaurant
     * @return ArrayList<Product> products
     */
	public ArrayList<Product> getProducts(){
		return products;
	}
	/** searchClient
     *Method used to search for a client.
     * post:Client object created
     * @param id_number -client id number-!= null
     * @return Client search null or not if find it.
     */
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
	/** searchProduct
     *Method used to search for a product.
     * post:Product object created
     * @param icode -product code-!= null
     * @return Product search null or not if find it.
     */
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
	/** addClient
     * Method used to add client
     * @param id_type-id_number-name-phone-adress-!= null
     * @return void
     */
	public boolean addClient(String id_type,String id_number,String name,String lname,String phone, String adress) {
		boolean add=false;
		Client client= new Client(id_type,id_number,name,lname,phone,adress);
		if(clients.isEmpty()) {
			clients.add(client);
			add=true;
		}
		else {
			int s=0;
			while(s<clients.size() && client.getLast_name().compareTo(clients.get(s).getLast_name())<0 ){
				s++;
			}
			clients.add(s,client);
		}
		return add;
	}
	/** addProduct
     * Method used to add client
     * @param code-name-description-cost-nit-!= null
     * @return void
     */
	public void addProduct(String code,String name,String description,double cost,String nit) throws NegativeCostException, CeroCostException {
		Product product=new Product(code,name,description,cost,nit);
		products.add(product);
	}
	/** toStringProducts
     * Method to provide the products information of the restaurant
     * @return String products information of the restaurant
     */
	public String toStringProducts() {
		String message="Product List: \n";
		for(Product myProducts:products) {
			message += myProducts.getName() + "|"+ myProducts.getDescription() + "|"+ myProducts.getCost()+"\n";
		}
		return message;
	}
	/** bubble
     * Method to organize a list of clients with bubble sort
     * @return ArrayList<Client> clients 
     */
	public ArrayList<Client> bubble(ArrayList<Client> clients){
		for (int s= 0; s< clients.size()-1; s++){
	        for(int m=0; m< clients.size()-1; m++) {
	             String a= clients.get(s).getPhone();
	             String b= clients.get(m+1).getPhone();
	             Client c = clients.get(s);
	             Client d = clients.get(m+1);
	             if ( a.compareTo(b)>0)  {
	               Client temp = d;  
	                 clients.set(m+1, c);
	                 clients.set(s, temp);

	             } 
	        }
	}
	return clients;
	  }
	/** updateClients
     * Method to update the clients information of the restaurant
     * @return void
     */
	public void updateClients(String document,String newId_number,String newId_type,String newName,String newPhone,String newAdress) {
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
			if(newPhone!=null) {
				c.setPhone(newPhone);
			}
			if(newAdress!=null) {
				c.setAdress(newAdress);
			}
		}
	}
	/** updateProducts
     * Method to update the products information of the restaurant
     * @return void
     */
	public void updateProducts(String code,String newCode,String newNit,String newName,String newDescription,double newCost) throws NegativeCostException, CeroCostException {
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
	public void orderByname() {
			for (int i =1; i < clients.size(); i++) {
				Client ci = clients.get(i);
				int j = i;
				Client cj= clients.get(j-1);
				while(j>0 && ci.compareByName(cj)<0) {
					clients.set(j,cj);
					j--;
					if(j>0) cj = clients.get(j-1);
				}
				clients.set(j,cj);
			}
		}
	/** searchClientName
     *Method used to search for a client name
     * post:Client object created
     * @param name -client name-!= null
     * @return Client search null or not if find it.
     */
	public boolean searchClientName(String name) {
		orderByname();
		boolean exist=false;
		int inicio = 0;
		int fin = clients.size()-1;
		while( inicio <= fin && exist==false){
			int medio = ( inicio + fin ) / 2;
			if( clients.get(medio).getName().equals(name)){
				exist = true;
			}
			else if(clients.get(medio).getName().compareTo(name)>0){
				fin = medio - 1;
			}
			else{
				inicio = medio + 1;
			}
		}
		return exist;
	}
	/** removeClient
     * Method to remove a client of the list of clients of the restaurant
     * @param eliminatedCode-!= null
     * @return void
     */
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
	/** removeProduct
     * Method to remove a product of the list of products of the restaurant
     * @param eliminatedCode-!= null
     * @return void
     */
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
	/** toStringClients
     * Method to provide the clients information of the restaurant
     * @return String clients information of the restaurant
     */
	public String toStringClients() {
		ArrayList<Client> clients2;
		clients2=new ArrayList<Client>();
		clients2=bubble(clients);
		String message="Clients List: \n";
		for(Client myClients:clients2) {
			message += myClients.getName() + "|"+ myClients.getId_type() + "|"+ myClients.getId_number()+ "|"+ myClients.getPhone()+"\n";
		}
		return message;
	}
	public String toStringClients2() {
		String message="Clients List: \n";
		for(Client myClients:clients) {
			message += myClients.getName() + "|"+myClients.getLast_name() + "|"+ myClients.getId_type() + "|"+ myClients.getId_number()+ "|"+ myClients.getPhone()+"\n";
		}
		return message;
	}
	/**importDataClient
     * Method to import data clients information of the restaurant
     * @param File-!= null
     * @return void
     */
	public void importDataClient(File file) throws IOException {
		BufferedReader br =new BufferedReader(new FileReader(file));
		int count=0;
		String line=br.readLine();
		while(line!=null) {
			 if(count>0){
				 String [] parts=line.split(",");
				 addClient(parts[0],parts[1],parts[2],parts[3],parts[4],parts[5]);
			 }
			 count++;
			 line=br.readLine();
		}
		br.close();
		String msg=toStringClients2();
		System.out.println(msg);
	}
	/**importDataClient
     * Method to import data products information of the restaurant
     * @param File-!= null
     * @return void
     */
	public void importDataProduct(File file) throws IOException, NumberFormatException, NegativeCostException, CeroCostException {
		BufferedReader br =new BufferedReader(new FileReader(file));
		int count=0;
		String line=br.readLine();
		while(line!=null) {
			 if(count>0){
				 String [] parts=line.split(",");
				 if(parts[3]!=null) {
					double cost=Double.parseDouble(parts[3]);
					addProduct(parts[0],parts[1],parts[2],cost,parts[4]);
			     }
			 }
			 count++;
			 line=br.readLine();
		}
		br.close();
	}
	/**saveClient
     * Method to save data clients information of the restaurant
     * @return void
     */
	private void saveClients() throws IOException {
		ObjectOutputStream oos= new ObjectOutputStream(new FileOutputStream(CLIENTS_FILE_NAME));
		oos.writeObject(clients);
		oos.close();
	}
	/**loadClient
     * Method to load data clients information of the restaurant
     * @return void
     */
	@SuppressWarnings("unchecked")
	private void loadClients() throws IOException, ClassNotFoundException{
		File f=new File(CLIENTS_FILE_NAME);
		if(f.exists()) {
			ObjectInputStream ois= new ObjectInputStream(new FileInputStream(f));
			clients=(ArrayList<Client>) ois.readObject();
			ois.close();
		}
	}
	/**saveProducts
     * Method to save data products information of the restaurant
     * @return void
     */
	private void saveProducts() throws IOException {
		ObjectOutputStream oos= new ObjectOutputStream(new FileOutputStream(PRODUCTS_FILE_NAME));
		oos.writeObject(products);
		oos.close();
	}
	/**loadClient
     * Method to load data products information of the restaurant
     * @return void
     */
	@SuppressWarnings("unchecked")
	private void loadProducts() throws IOException, ClassNotFoundException{
		File f=new File(PRODUCTS_FILE_NAME);
		if(f.exists()) {
			ObjectInputStream ois= new ObjectInputStream(new FileInputStream(f));
			products=(ArrayList<Product>) ois.readObject();
			ois.close();
		}
	}
	/**compareTo
     * Method to compares the restaurants names and sort
     */
	@Override
	public int compareTo(Restaurant r) {
		return name.compareToIgnoreCase(r.getName());
	}
}