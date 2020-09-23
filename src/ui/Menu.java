package ui;
import model.App;
import model.Product;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;
import exceptions.CeroCostException;
import exceptions.NegativeCostException;
enum Status{
	REQUESTED,PROCESS, SHIPPED ,DELIVERED;
}
public class Menu {
	private final static int EXIT_OPTION = 3;
	private Scanner sc;
	private App app;
	
	public Menu() throws IOException {
		sc = new Scanner(System.in);
		createApp();
	}
	
	public void startMenu() throws IOException, NegativeCostException, CeroCostException {
		String menu = getMenuText();
		int option;
		do {
			System.out.print(menu);
			option = readOption();
			executeOperation(option);
		}while(option!=EXIT_OPTION);
	}
	
	private String getMenuText() {
		String menu;
		menu  = "==============================\n";
		menu += "      ORDER SYSTEM\n";
		menu += "==============================\n";
		menu += "1. Add restaurant\n";
		menu += "2. Add client \n";
		menu += "3. Add product\n";
		menu += "4. Add order \n";
		menu += "5. Show restaurants \n";
		menu += "6. Show clients\n";
		menu += "7. Order status\n";
		menu += "8. Search client \n";
		menu += "9. Data update \n";
		menu += "10. Remove information\n";
		menu += "11. Import data \n";
		menu += "12. Emport data orders \n";
		menu += "13. Exit\n";
		menu += "Please enter the option: ";
		return menu;
	}
	
	private int readOption() {
		int op;
		op = Integer.parseInt(sc.nextLine());
		return op;
	}
	private void executeOperation(int option) throws IOException, NegativeCostException, CeroCostException {
		switch(option) {
			case 1: addRestaurant();   break;
			case 2: addClient(); break;
			case 3: addProduct();   break;
			case 4: addOrder(); break;
			case 5: showRestaurants();break;
			case 6: showClients();break;
			case 7: orderStatus(); break;
			case 8: searchClient();break;
			case 9: updateData();break;
			case 10: remove();break;
			case 11: importData();break;
			case 12: exportOrders();break;
			case 13: exitProgram(); break;
			default: break;
		}
	}
	
	private void exitProgram() {
		sc.close();
		
	}
	 /** createApp
     *Method used create a new app
     */
	public void createApp() throws IOException{
		app=new App();
	}
	/** addRestaurant
     * Method used to enter the information of a restaurant and register it
     */
	private void addRestaurant() {
		boolean add;
		System.out.print("Please enter the restaurant name: ");
		String name= sc.nextLine();;
		System.out.print("Please enter the nit: ");
		String nit = sc.nextLine();
		System.out.print("Please enter the name of the restaurant manager: ");
		String manager= sc.nextLine();
		try {
			add=app.addRestaurant(name,nit,manager);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(add=true) {
		System.out.println("The restaurant has been added succesfully");
		}
		else {
		System.out.println("The restaurant has  notbeen added succesfully");	
		}
	}	
	/** addClient
     * Method used to enter the information of a client and register it
     */
	private void addClient() {
		System.out.println("Enter the nit of the restaurant who belongs the client");
		String nit=sc.nextLine();
		System.out.print("Please enter the name: ");
		String name= sc.nextLine();
		System.out.print("Please enter the last name: ");
		String last_name= sc.nextLine();
		System.out.print("Please enter the identification type: ");
		String id_type = sc.nextLine();
		System.out.print("Please enter the identification number: ");
		String id_number= sc.nextLine();
		System.out.print("Please enter the phone number: ");
		String phone =sc.nextLine();
		System.out.print("Please enter the adress: ");
		String adress= sc.nextLine();
		
		boolean add=app.addClient(nit,id_type,id_number,name,last_name,phone,adress);
		if (add==true) {
		System.out.println("The client has been added succesfully");
		}
		else {
			System.out.println("The client has not been added succesfully");
		}
	}
	/** addProduct
     * Method used to enter the information of a product and register it
     */
	private void addProduct() throws NegativeCostException, CeroCostException {
		System.out.print("Please enter the product name: ");
		String name= sc.nextLine();;
		System.out.print("Please enter the description of the product: ");
		String description = sc.nextLine();
		System.out.print("Please enter the code: ");
		String code= sc.nextLine();
		System.out.print("Please enter the cost of the product: ");
		double cost = Double.parseDouble(sc.nextLine());
		System.out.print("Please enter the nit of restaurant to belongs: ");
		String nit= sc.nextLine();
		
		app.addProduct(code,name,description,cost,nit);
		
		System.out.println("The product has been added succesfully");
	}
	/** addOrder
     * Method used to enter the information of a order and register it
     */
	private void addOrder() {
		ArrayList<Product> products;
		products=new ArrayList<Product>();
		int[] order;
		Date date = new Date();
		System.out.println(date);
		 Random aleatorio = new Random();
         String alfa = "ABCDEFGHIJKLMNOPQRSTVWXYZ";
         String code = "";  
         int num=0;
         int form=0;
         form=(int)(aleatorio.nextDouble() * alfa.length()-1+0);
         num=(int)(aleatorio.nextDouble() * 99+100);
        code=code+alfa.charAt(form)+num;
        System.out.println(code);
		System.out.print("Please enter the nit of the restaurant who belongs the order : ");
		String nit = sc.nextLine();
		System.out.println("Products of the restaurant: ");
		System.out.print(app.toStringProducts(nit));
		System.out.println("How many products do you want?");
		int pro=Integer.parseInt(sc.nextLine());
		order=new int[pro];
		System.out.println("Choose the products you want by writing the number in which the products are ordered");	
		for(int s=0;s<order.length;s++) {
			int option=Integer.parseInt(sc.nextLine());
			order[s]=option;
		}
		products=app.chooseProducts(order,nit);
		System.out.println("Please enter the code of the client: ");
		String code_client= sc.nextLine();
		
		app.addOrder(code, date, code_client,nit,products);
		
		System.out.println("The product has been added succesfully");
	}
	/** updateData
     * Method used to enter the information of a restaurant,client,product or order and update it
     */
	private void updateData() {
		String newId_number=null;
		String newId_type=null;
		String newAdress=null;
		String newNit=null;
		String newManager=null;
		String newName=null;
		String newDescription=null;
		Double newCost=0.0;
		String newPhone=null;
		String newCode=null;
		System.out.println("What update do you want to do? Choose an option");
		System.out.print("1. Restaurant \n");
		System.out.print("2. Client \n");
		System.out.print("3. Product \n");;
		System.out.print("4. Order \n");;
		int option=Integer.parseInt(sc.nextLine());
		if(option==1) {
			System.out.println("Information to update");
			System.out.println("Enter the nit of the restaurant");
			String nit=sc.nextLine();
			System.out.println("Do you want to update the nit of the restaurant? Enter 1 for yes 2 for no");
			int op=Integer.parseInt(sc.nextLine());
			if(op==1) {
				System.out.println("Enter the new nit of the restaurant");
				newNit=sc.nextLine();
			}
			System.out.println("Do you want to update the name of the restaurant? Enter 1 for yes 2 for no");
			int op1=Integer.parseInt(sc.nextLine());
			if(op1==1) {
				System.out.println("Enter the new name of the restaurant");
				newName=sc.nextLine();
			}
			System.out.println("Do you want to update the manager of the restaurant? Enter 1 for yes 2 for no");
			int op2=Integer.parseInt(sc.nextLine());
			if(op2==1) {
				System.out.println("Enter the new manager of the restaurant");
				newManager=sc.nextLine();
			}
			app.updateRestaurant(nit,newNit,newName,newManager);
			System.out.println("Restaurant update");
		}
		else if(option==2) {
			System.out.println("Information to update");
			System.out.println("Enter the nit of the restaurant who belongs the client");
			String nit=sc.nextLine();
			System.out.println("Enter the id number of the client");
			String document=sc.nextLine();
			System.out.println("Do you want to update the id number of the client? Enter 1 for yes 2 for no");
			int op2=Integer.parseInt(sc.nextLine());
			if(op2==1) {
				System.out.println("Enter the new id number");
				newId_number=sc.nextLine();
			}
			System.out.println("Do you want to update the id type of the client? Enter 1 for yes 2 for no");
			int op4=Integer.parseInt(sc.nextLine());
			if(op4==1) {
				System.out.println("Enter the new id type");
				newId_type=sc.nextLine();
			}
			System.out.println("Do you want to update the name of the client? Enter 1 for yes 2 for no");
			int op5=Integer.parseInt(sc.nextLine());
			if(op5==1) {
				System.out.println("Enter the new name");
				newName=sc.nextLine();
			}
			System.out.println("Do you want to update the phone of the client? Enter 1 for yes 2 for no");
			int op6=Integer.parseInt(sc.nextLine());
			if(op6==1) {
				System.out.println("Enter the new phone");
				newPhone=sc.nextLine();
			}
			System.out.println("Do you want to update the adress of the client? Enter 1 for yes 2 for no");
			int op7=Integer.parseInt(sc.nextLine());
			if(op7==1) {
				System.out.println("Enter the new adress");
				newAdress=sc.nextLine();
			}
			app.updateClient(nit,document,newId_number,newId_type,newName,newPhone,newAdress);
		}
		else if(option==3) {
			System.out.println("Information to update");
			System.out.println("Enter the nit of the restaurant who belongs the product");
			String nit=sc.nextLine();
			System.out.println("Do you want to update the nit of the restaurant who belongs? Enter 1 for yes 2 for no");
			int op7=Integer.parseInt(sc.nextLine());
			if(op7==1) {
				System.out.println("Enter the new nit");
				newNit=sc.nextLine();
			}
			System.out.println("Enter the code of the product");
			String code=sc.nextLine();
			System.out.println("Do you want to update code of the product? Enter 1 for yes 2 for no");
			int op4=Integer.parseInt(sc.nextLine());
			if(op4==1) {
				System.out.println("Enter the new code");
				newCode=sc.nextLine();
			}
			System.out.println("Do you want to update the name of the product? Enter 1 for yes 2 for no");
			int op5=Integer.parseInt(sc.nextLine());
			if(op5==1) {
				System.out.println("Enter the new name");
				newName=sc.nextLine();
			}
			System.out.println("Do you want to update the description of the product? Enter 1 for yes 2 for no");
			int op6=Integer.parseInt(sc.nextLine());
			if(op6==1) {
				System.out.println("Enter the new description");
				newDescription=sc.nextLine();
			}
			System.out.println("Do you want to update the cost of the product? Enter 1 for yes 2 for no");
			int op3=Integer.parseInt(sc.nextLine());
			if(op3==1) {
				System.out.println("Enter the new cost");
				newCost=Double.parseDouble(sc.nextLine());
			}
			try {
				app.updateProducts(code,newCode,nit,newNit,newName,newDescription,newCost);
			} catch (NegativeCostException e) {
				System.out.println(e.getMessage());
			} catch (CeroCostException e) {
				System.out.println(e.getMessage());
			}
		}
		else {
			System.out.println("Enter the code of the order");
			String order_Code=sc.nextLine();
			System.out.println("Do you want to update the client code of the order? Enter 1 for yes 2 for no");
			int op3=Integer.parseInt(sc.nextLine());
			if(op3==1) {
				System.out.println("Enter the new code");
				newCode=sc.nextLine();
			}
			System.out.println("Do you want to update the nit of the restaurant who belongs the order? Enter 1 for yes 2 for no");
			int op4=Integer.parseInt(sc.nextLine());
			if(op4==1) {
				System.out.println("Enter the new nit");
				newNit=sc.nextLine();
			}
			app.updateOrder(order_Code,newCode,newNit);
		}
	}
	/** remove
     * Method used to remove the information of a restaurant,product,client or order
     */
	private void remove() {
		String eliminateCode=null;
		String nit=null;
		String update="";
		System.out.println("What kind of information do you want to remove? Choose an option");
		update  = "1. Restaurant \n";
		update += "2. Client \n";
		update += "3. Product \n";
		update += "4. Order \n";
		int option=Integer.parseInt(sc.nextLine());
		if(option==1) {
			System.out.println("Enter the nit of the restaurant ");
			nit=sc.nextLine();
			app.removeRestaurant(nit);
		}
		if(option==2) {
			System.out.println("Enter the nit of the restaurant who belongs the client");
			nit=sc.nextLine();
			System.out.println("Enter the id number of the client");
			eliminateCode=sc.nextLine();
			app.removeClient(nit,eliminateCode);
		}
		if(option==3) {
			System.out.println("Enter the nit of the restaurant who belongs the product");
			nit=sc.nextLine();
			System.out.println("Enter the code of the product");
			eliminateCode=sc.nextLine();
			app.removeProduct(nit,eliminateCode);
		}
		else {
			System.out.println("Enter the code of the order");
			eliminateCode=sc.nextLine();
			app.removeOrder(eliminateCode);
		}

	}
	/** showRestaurants
     * Method used to show all the restaurants of the app 
     */
	private void showRestaurants() {
		String msg="";
		app.sortByNameRest();
		msg=app.toStringRestaurants();
		System.out.println(msg);
		
	}
	/** showClients
     * Method used to show all the clients of a restaurant
     */
	private void showClients() {
		String msg="";
		System.out.println("Enter the nit of the restaurant");
		String nit=sc.nextLine();
		msg=app.toStringClients(nit);
		System.out.println(msg);	
	}
	/** orderStatus
     * Method used to change the status of a order
     */
	private void orderStatus() {
		Status status = null;
		String order=" ";
		System.out.println("Enter the code of the order");
		String code=sc.nextLine();
		System.out.println("What is the status of the order ");
		System.out.println("1.REQUESTED");
		System.out.println("2.PROCESS");
		System.out.println("3.SHIPPED");
		System.out.println("4.DELIVERED");
		int op=Integer.parseInt(sc.nextLine());
		if(op==1) {
			System.out.println("Do you want to changed to PROCCES?Enter 1 for yes,2 for no");
			int op1=Integer.parseInt(sc.nextLine());
			if(op1==1){
				status=Status.REQUESTED;
			}
		}
		else if(op==2) {
			System.out.println("Do you want to changed to SHIPPED?Enter 1 for yes,2 for no");
			int op1=Integer.parseInt(sc.nextLine());
			if(op1==1){
				status=Status.PROCESS;
			}
		}
		else if(op==3) {
			System.out.println("Do you want to changed to DELIVERED?Enter 1 for yes,2 for no");
			int op1=Integer.parseInt(sc.nextLine());
			if(op1==1){
				status=Status.SHIPPED;
			}
		}
		if(status==Status.REQUESTED) {
			order="REQUESTED";
		}
		else if(status==Status.PROCESS) {
			order="PROCESS";
		}
		else if(status==Status.SHIPPED) {
			order="SHIPPED";
		}
		app.statusOrder(code,order);
		
	}
	/** searchClient
     * Method used to search a client for the name
     */
	private void searchClient() {
		System.out.println("Enter the nit of the restaurant who belongs the client you want to search");
		String nit=sc.nextLine();
		System.out.println("Enter the name of the client you want to search");
		String name=sc.nextLine();
		long start = System.currentTimeMillis();
		boolean found=app.searchClientName(nit,name);
		long end = System.currentTimeMillis();
		if(found==false) {
			System.out.println("The client does not exist");
			System.out.println("Search time:"+(end-start));
		}
		else {
			System.out.println("Client founded");
			System.out.println("Search time:"+(end-start));
		}
	}
	/** importData
     * Method used to import information like restaurants,products,clients, or order
     */
	private void importData() {
		System.out.println("What kind of information do you want to import? Choose an option");
		System.out.println("1. Restaurants");
		System.out.println("2. Clients");
		System.out.println("3. Products");
		System.out.println("4. Orders");
		int option=Integer.parseInt(sc.nextLine());
		if(option==1) {
			System.out.println("Type the file name to import:");
			String n=sc.nextLine();
			String name="data/"+n+".csv";
			
			try {
				app.importDataRestaurant(name);
				System.out.println("The file has been imported successfully");
			}
			catch(IOException e) {
				System.out.println("The data can not be imported");
			}
		}
		else if(option==2) {
			System.out.print("Please enter the nit of the restaurant who belongs the clients : ");
			String nit = sc.nextLine();
			System.out.println("Type the file name to import:");
			String n=sc.nextLine();
			String name="data/"+n+".csv";
			try {
					app.importDataClient(name,nit);
			System.out.println("The file has been imported successfully");
			}
			catch(IOException e) {
				System.out.println("The data can not be imported");
			}
		}
		else if(option==3) {
			System.out.print("Please enter the nit of the restaurant who belongs the products : ");
			String nit = sc.nextLine();
			System.out.println("Type the file name to import:");
			String n=sc.nextLine();
			String name="data/"+n+".csv";
			try {
				try {
					app.importDataProduct(name,nit);
					System.out.println("The file has been imported successfully");
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (NegativeCostException e) {
					System.out.println(e.getMessage());
				} catch (CeroCostException e) {
					System.out.println(e.getMessage());
				}
			}
			catch(IOException e) {
				System.out.println("The data can not be imported");
			}
		}
		else {
			System.out.println("Type the file name to import:");
			String n=sc.nextLine();
			String name="data/"+n+".csv";
			try {
				app.importDataOrder(name);
				System.out.println("The file has been imported successfully");
			} catch (CeroCostException e) {
				System.out.println(e.getMessage());
			}catch(NegativeCostException e) {
				System.out.println(e.getMessage());
			}catch(NumberFormatException e) {
				e.printStackTrace();
			}catch(ParseException e) {
				e.printStackTrace();
			}catch(FileNotFoundException e) {
				e.printStackTrace();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	public void exportOrders() {
		System.out.println("Type the file name to export:");
		String name=sc.nextLine();
		String file_name="data/"+name+".csv";
		System.out.println("Which separator do you want to use");
		String separator=sc.nextLine();
		File export=new File (file_name);
		try{
		app.exportOrders(export,separator);
		System.out.println("The file has been exported successfully");
		}
		catch(FileNotFoundException e) {
			System.out.println("The data can not be exported");
		}
	}
}