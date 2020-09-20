package ui;
import model.App;
import model.Product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

import exceptions.CeroCostException;
import exceptions.NegativeCostException;
public class Menu {
	private final static int EXIT_OPTION = 3;
	private Scanner sc;
	private App app;
	
	public Menu() {
		sc = new Scanner(System.in);
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
		menu += "12. Exit\n";
		menu += "Please enter the option: ";
		return menu;
	}
	
	private int readOption() {
		int op;
		op = Integer.parseInt(sc.nextLine());
		return op;
	}
	private void executeOperation(int option) throws IOException, NegativeCostException, CeroCostException {
		createApp();
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
			case 12: exitProgram(); break;
			default: break;
		}
	}
	
	private void exitProgram() {
		sc.close();
	}
	public void createApp() throws IOException{
		app=new App();
	}
	private void addRestaurant() {
		System.out.print("Please enter the restaurant name: ");
		String name= sc.nextLine();;
		System.out.print("Please enter the nit: ");
		String nit = sc.nextLine();
		System.out.print("Please enter the name of the restaurant manager: ");
		String manager= sc.nextLine();
		
		try {
			app.addRestaurant(name,nit,manager);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("The restaurant has been added succesfully");
		}	
	private void addClient() {
		System.out.println("Enter the nit of the restaurant who belongs the client");
		String nit=sc.nextLine();
		System.out.print("Please enter the full name: ");
		String name= sc.nextLine();;
		System.out.print("Please enter the identification type: ");
		String id_type = sc.nextLine();
		System.out.print("Please enter the identification number: ");
		String id_number= sc.nextLine();
		System.out.print("Please enter the phone number: ");
		String phone =sc.nextLine();
		System.out.print("Please enter the adress: ");
		String adress= sc.nextLine();
		
		app.addClient(nit,id_type,id_number,name,phone,adress);
		
		System.out.println("The client has been added succesfully");
	}
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
	private void addOrder() {
		String msg="";
		ArrayList<Product> products;
		products=new ArrayList<Product>();
		int[] order;
		Date date = new Date();
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
		System.out.print(app.toString());
		System.out.println("How many products do you want?");
		int pro=Integer.parseInt(sc.nextLine());
		order=new int[pro];
		System.out.println("Choose the products you want by writing the number in which the products are ordered");	
		int option=Integer.parseInt(sc.nextLine());
		for(int s=0;s<order.length && option!=0;s++) {
			order[s]=option;
		}
		products=app.chooseProducts(order,nit);
		System.out.print("Please enter the code of the client: ");
		String code_client= sc.nextLine();
		
		app.addOrder(code, date, code_client,nit,products);
		
		System.out.println("The product has been added succesfully");
	}
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
	private void showRestaurants() {
		app.sortByNameRest();
		System.out.println(app.getRestaurants());
	}
	private void showClients() {
		System.out.println("Enter the nit of the restaurant");
		String nit=sc.nextLine();
		app.toStringClients(nit);
		System.out.println(app.toStringClients(nit));	
	}
	private void orderStatus() {
		
	}
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
			System.out.println("Search time: "+ end);
		}
		else {
			System.out.println("Client founded");
			System.out.println("Search time: "+ end);
		}
	}
	private void importData() {
		String update="";
		System.out.println("What kind of information do you want to import? Choose an option");
		update  = "1. Restaurant \n";
		update += "2. Client \n";
		update += "3. Product \n";
		update += "4. Order \n";
		int option=Integer.parseInt(sc.nextLine());
		if(option==1) {
			System.out.println("Type the file name to import:");
			String name=sc.nextLine();
			try {
				app.importDataRestaurant(name);
				System.out.println("The file has been imported successfully");
			}
			catch(IOException e) {
				System.out.println("The data can not be imported");
			}
		}
		else if(option==2) {
			System.out.print("Please enter the nit of the restaurant who belongs the order : ");
			String nit = sc.nextLine();
			System.out.println("Type the file name to import:");
			String name=sc.nextLine();
			try {
					app.importDataClient(name,nit);
			System.out.println("The file has been imported successfully");
			}
			catch(IOException e) {
				System.out.println("The data can not be imported");
			}
		}
		else if(option==3) {
			System.out.print("Please enter the nit of the restaurant who belongs the order : ");
			String nit = sc.nextLine();
			System.out.println("Type the file name to import:");
			String name=sc.nextLine();
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
			String name=sc.nextLine();
			try {
				app.importDataOrder(name);
				System.out.println("The file has been imported successfully");
			}
			catch(IOException e) {
				System.out.println("The data can not be imported");
			}
		}
	}
}