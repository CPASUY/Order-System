package model;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import exceptions.CeroCostException;
import exceptions.NegativeCostException;
public class App {
	//Constants
	public static final String RESTAURANTS_FILE_NAME="data/restaurants.bbd";
	public static final String ORDERS_FILE_NAME="data/orders.bbd";
	//Relations
	private ArrayList<Restaurant> restaurants;
	private ArrayList<Order> orders;
	//Methods
	public App() throws IOException {
		this.restaurants=new ArrayList<Restaurant>();
		this.orders=new ArrayList<Order>();
		try {
			loadRestaurants();
			loadOrders();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		saveRestaurants();
		saveOrders();
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
	/** searchRestaurant
     *Method used to search for a restaurant
     * post:Restaurant object created
     * @param nit -restaurant nit-!= null
     * @return Restaurant search null or not if find it.
     */
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
	/** searchOder
     *Method used to search for a order
     * post:Oder object created
     * @param code-order code-!= null
     * @return Order search null or not if find it.
     */
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
	/** addRestaurant
     * Method used to add restaurant
     * @param name!= null
     * @param nit!= null
     * @param manager!= null
     * @throws IOException if exist
     * @return boolean add
     */
	public boolean addRestaurant(String name,String nit,String manager) throws IOException{
		boolean add=false;
		Restaurant r=searchRestaurant(nit);
		if(r==null) {
			add=true;
		Restaurant restaurant=new Restaurant(name,nit,manager);
		restaurants.add(restaurant);
		}
		return add;
	}
	/** addClient
     * Method used to add client
     * @param nit!= null
     *@param id_type!= null
     *@param id_number!= null
     *@param name!= null
     *@param phone!= null
     *@param adress!= null
     *@param lname!= null
     * @return boolean add
     */
	public boolean addClient(String nit,String id_type,String id_number,String lname,String name,String phone, String adress){
		boolean add=false;
		Restaurant restaurant=searchRestaurant(nit);
		if(restaurant!=null) {
			add=restaurant.addClient(id_type,id_number,name,lname,phone,adress);
		}	
		return add;
	}
	public void exit() {
		orders.clear();
		restaurants.clear();
		orders.clear();
	}
	/** addProduct
     * Method used to add product
     * @param code!= null
     * @param name!= null
     * @param description!= null
     * @param nit!= null
     * @param cost= 0
     * @throws NegativeCostException .
     * @throws CeroCostException .
	 * @throws NumberFormatException .
     */
	public void addProduct(String code,String name,String description,double cost,String nit) throws NegativeCostException, CeroCostException{
		Restaurant restaurant=searchRestaurant(nit);
		if(restaurant!=null) {
			restaurant.addProduct(code,name,description,cost,nit);
		}
	}
	/** addOrder
     * Method used to add order
     * @param code!= null
     * @param date!= null
     * @param code_client!= null
     * @param nit!= null
     * @param products!= null
     */
	public void addOrder(String code, Date date, String code_client, String nit,ArrayList<Product> products) {
		Order order=new Order(code,date,code_client,nit,products);
		orders.add(order);
	}
	/** toStringRestaurants
     * Method to provide the restaurants information 
     * @return String restaurants information
     */
	public String toStringRestaurants() {
		String message="Restaurants List: \n";
			for(int s=0;s<restaurants.size();s++) {
				message += restaurants.get(s).getName() + "-"+ restaurants.get(s).getNit() + "-"+ restaurants.get(s).getManager()+ "\n";
			}
		return message;
	}
	/** toStringProducts
     * Method to verify if the restaurants who belongs the product exist
     * @param nit!= null
     * @return String products information
     */
	public String toStringProducts(String nit) {
		String message="";
		Restaurant restaurant=searchRestaurant(nit);
		if(restaurant!=null) {
			message=restaurant.toStringProducts();
		}
		return message;
	}
	/**saveRestaurants
     * Method to save data restaurants information 
     */
	private void saveRestaurants() throws IOException {
		ObjectOutputStream oos= new ObjectOutputStream(new FileOutputStream(RESTAURANTS_FILE_NAME));
		oos.writeObject(restaurants);
		oos.close();
	}
	/**loadRestaurants
     * Method to load data restaurants information
     */
	@SuppressWarnings("unchecked")
	private void loadRestaurants() throws IOException, ClassNotFoundException{
		File f=new File(RESTAURANTS_FILE_NAME);
		if(f.exists()) {
			ObjectInputStream ois= new ObjectInputStream(new FileInputStream(f));
			restaurants=(ArrayList<Restaurant>) ois.readObject();
			ois.close();
		}
	}
	/**saveOrders
     * Method to save data orders information 
     */
	private void saveOrders() throws IOException {
		ObjectOutputStream oos= new ObjectOutputStream(new FileOutputStream(ORDERS_FILE_NAME));
		oos.writeObject(orders);
		oos.close();
	}
	/**loadOrders
     * Method to load data orders information
     */
	@SuppressWarnings("unchecked")
	private void loadOrders() throws IOException, ClassNotFoundException{
		File f=new File(ORDERS_FILE_NAME);
		if(f.exists()) {
			ObjectInputStream ois= new ObjectInputStream(new FileInputStream(f));
			orders=(ArrayList<Order>) ois.readObject();
			ois.close();
		}
	}
	/**sortCost
     * Method to order the cost of the products
     * @param nit !=null
     * @return String message
     * */
	public String sortCost(String nit) {
		String message="";
		Restaurant restaurant=searchRestaurant(nit);
		if(restaurant!=null) {
			message=restaurant.sortCost();
		}
		return message;
	}
	/** toStringClients
     * Method to verify if the restaurants who belongs the clients exist
     * @param nit!= null
     * @return String clients information
     */
	public String toStringClients(String nit) {
		String message="";
		Restaurant restaurant=searchRestaurant(nit);
		if(restaurant!=null) {
			message=restaurant.toStringClients();
		}
		return message;
	}
	/** statusOrder
     * Method to change the status of the orders
     * @param status!= null
     * @param code!= null
     */
	public void statusOrder(String status,String code) {
		Order or=searchOrder(code);
		if(or!=null) {
			if(status.equals("REQUESTED")) {
			or.setStatus("PROCESS");
			}
			else if(status.equals("PROCESS")) {
				or.setStatus("SHIPPED");
			}
			else if(status.equals("SHIPPED")) {
				or.setStatus("DELIVERED");
			}
		}
	}
	/** updateRestaurant
     * Method to update the restaurants information 
     * @param nit!= null
     * @param newNit!= null
     * @param newName!= null
     * @param newManager!= null
     */
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
	/** updateClient
     * Method to update the clients information 
     * @param nit!=null
     * @param document!=null
     * @param newId_number!=null
     * @param newId_type!=null
     * @param newName!=null
     * @param newPhone!=null
     * @param newAdress!=null
     */
	public void updateClient(String nit,String document,String newId_number,String newId_type,String newName,String newPhone,String newAdress) {
		Restaurant restaurant=searchRestaurant(nit);
		if(restaurant!=null) {
			restaurant.updateClients(document,newId_number,newId_type,newName,newPhone,newAdress);
		}
	}
	/** updateProducts
     * Method to update the produdcts information 
     * @param code!=null
     * @param newCode!=null
     * @param nit!=null
     * @param newNit!=null
     * @param newName!=null
     * @param newDescription!=null
     * @param newCost!=null
     * @throws NegativeCostException .
     * @throws CeroCostException .
     */
	public void updateProducts(String code,String newCode,String nit,String newNit,String newName,String newDescription,double newCost) throws NegativeCostException, CeroCostException {
		Restaurant restaurant=searchRestaurant(nit);
		if(restaurant!=null) {
			restaurant.updateProducts(code,newCode,newNit,newName,newDescription,newCost);
		}
	}
	/** updateOder
     * Method to update the orders information 
     * @param order_Code!=null
     * @param newCode!=null
     * @param newNit!=null
     */
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
	/** removeRestaurant
     * Method to remove a restaurant of the list of restaurants
     * @param nit!= null
     */
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
	/** removeClient
     * Method to remove a client of the list of clients of the restaurant
     * @param eliminateCode!= null
     * @param nit!= null
     */
	public void removeClient(String nit,String eliminateCode) {
		Restaurant restaurant=searchRestaurant(nit);
		if(restaurant!=null) {
			restaurant.removeClient(eliminateCode);
		}
	}
	/** removeProducts
     * Method to remove a product of the list of products of the restaurant
     * @param eliminateCode!= null
     * @param nit!= null
     */
	public void removeProduct(String nit,String eliminateCode) {
		Restaurant restaurant=searchRestaurant(nit);
		if(restaurant!=null) {
			restaurant.removeProduct(eliminateCode);
		}
	}
	/** verifiedProducts
     * Method used to verified if the products are the same that the client choose
     * @param code!=null
     * @param nit!=null
     * @return boolean if verified
     */
	public boolean verifiedProducts(String code,String nit) {
		ArrayList<Product>p;
		p=new ArrayList<Product>();
		boolean verified=true;
		Order or=searchOrder(code);
		if(or!=null) {
			p=or.getOrderList();
			for(int s=0;s<p.size();s++) {
				if(!p.get(s).getNit().equals(nit)){
					verified=false;
				}
			}
		}
		return verified;
	}
	/** removeOrder
     * Method to remove a order of the list of orders of the restaurant
     * @param eliminateCode!= null
     */
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
	/** searchClientName
     *Method used to sort the restaurants for the name
     */
	public void sortByNameRest() {
		Collections.sort((List<Restaurant>) restaurants);
	}
	/** searchClientName
     *Method used to search a client for the name
     * @param name!= null
     * @param nit!= null
     * @return bolean search true if find it,false if not
     */
	public boolean searchClientName(String nit, String name) {
		Restaurant restaurant=searchRestaurant(nit);
		boolean search=false;
		if(restaurant!=null) {
			search=restaurant.searchClientName(name);
		}
		return search;
	}
	/**importDataRestaurant
     * Method to import data restauranst information 
     * @param name!= null
     * @throws IOException .
     */
	public void importDataRestaurant(String name) throws IOException {
		File f=new File(name);
		int cont=0;
		BufferedReader br =new BufferedReader(new FileReader(f));
		String line=br.readLine();
		while(line!=null) {
			if(cont>0){
			String [] parts=line.split(",");
			addRestaurant(parts[0],parts[1],parts[2]);
			}
			cont++;
			line=br.readLine();
		}
		br.close();
	}
	/**importDataClient
     * Method to import data clients information of the restaurant
     * @param nit!= null
     * @param name!= null
     * @throws IOException .
     */
	public void importDataClient(String name,String nit) throws IOException {
		File f=new File(name);
		Restaurant restaurant=searchRestaurant(nit);
		if(restaurant!=null) {
			restaurant.importDataClient(f);
		}
	}
	/**importDataProduct
     * Method to import data products information of the restaurant
     * @param nit!= null
     * @param name!= null
     * @throws IOException .
     * @throws CeroCostException .
	 * @throws NegativeCostException .
	 * @throws NumberFormatException .
     */
	public void importDataProduct(String name,String nit) throws IOException, NumberFormatException, NegativeCostException, CeroCostException {
		File f=new File(name);
		Restaurant restaurant=searchRestaurant(nit);
		if(restaurant!=null) {
			restaurant.importDataProduct(f);
		}
	}
	/**converteDate
     * Method to convert a string to date
     * @param date!= null
     * @throws ParseException .
     * @return Date date
     */
	public static Date convertDate (String date) throws ParseException {
		Calendar date1=Calendar.getInstance();
		int year=0;
		int month=0;
		int day=0;
		int hrs=0;
		int min=0;
		int sec=0;
		String []parts=date.split("-");
		year=Integer.parseInt(parts[0]);
		month=Integer.parseInt(parts[1]);
		String [] parts2=parts[2].split(" ");
		day=Integer.parseInt(parts2[0]);
		String []parts3=parts2[1].split(":");
		hrs=Integer.parseInt(parts3[0]);
		min=Integer.parseInt(parts3[1]);
		sec=Integer.parseInt(parts3[2]);
		date1.set(year,month,day,hrs,min,sec);
		Date dateFinal=date1.getTime();
		return dateFinal;
		
	}  
	/**importDataOrder
     * Method to import data order information 
     * @param name!= null
	 * @throws IOException .
	 * @throws ParseException .
	 * @throws CeroCostException .
	 * @throws NegativeCostException .
	 * @throws NumberFormatException .
	 * @throws FileNotFoundException .
     */
	public void importDataOrder(String name) throws IOException,FileNotFoundException, ParseException, NumberFormatException, NegativeCostException, CeroCostException  {
		File f=new File(name);
		ArrayList<Product> p;
		p=new ArrayList<Product>();
		int cont=0;
		BufferedReader br =new BufferedReader(new FileReader(f));
		String line=br.readLine();
		while(line!=null) {
			if(cont>0){
				String [] parts=line.split(",");
				Date date=convertDate(parts[1]);
				Product product=new Product(parts[4],parts[5],parts[6],Double.parseDouble(parts[7]),parts[8]);
				p.add(product);
				addOrder(parts[0],date,parts[2],parts[3],p);
			}
			line=br.readLine();
			cont++;
		}
		br.close();
	}
	/**exportOrders
     * Method to export data order information 
     * @param file!= null
     * @param s!= null
	 * @throws FileNotFoundException if exist
     */
	public void exportOrders(File file,String s) throws FileNotFoundException {
		ArrayList <Product>p;
		p=new ArrayList<Product>();
		PrintWriter pw =new PrintWriter(file);
		Collections.sort(orders);
		pw.write("Nit Restaurant"+s+" ID client"+s+ "Date"+s+" Code Product\n");
		for(int w=0;w<orders.size();w++) {
			p=orders.get(w).getOrderList();
			String code=p.get(0).getCode();
			pw.write(orders.get(w).getNit()+s+ orders.get(w).getCode_client()+s+orders.get(w).getDate()+s+code+"\n");
		}
		pw.close();	
	}
}
