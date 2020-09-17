package ui;
import model.App;
import model.Product;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
public class Menu {
	private final static int EXIT_OPTION = 3;
	private Scanner sc;
	private App app;
	
	public Menu() {
		sc = new Scanner(System.in);
	}
	
	public void startMenu() {
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
		menu += "5. Data update \n";
		menu += "6. Exit\n";
		menu += "Please enter the option: ";
		return menu;
	}
	
	private int readOption() {
		int op;
		op = Integer.parseInt(sc.nextLine());
		return op;
	}
	
	private void executeOperation(int option) {
		switch(option) {
			case 1: addRestaurant();   break;
			case 2: addClient(); break;
			case 3: addProduct();   break;
			case 4: addOrder(); break;
			case 5: updateData();break;
			case 6: showRestaurants();break;
			case 7: showClients();break;
			case 8: orderStatus(); break;
			case 9: searchClient();break;
			case 10: importData();break;
			case 11: exitProgram(); break;
			default: break;
		}
	}
	
	private void exitProgram() {
		sc.close();
	}
	private void addRestaurant() {
		System.out.print("Please enter the restaurant name: ");
		String name= sc.nextLine();;
		System.out.print("Please enter the nit: ");
		String nit = sc.nextLine();
		System.out.print("Please enter the name of the restaurant manager: ");
		String manager= sc.nextLine();
		
		app.addRestaurant(name,nit,manager);
		
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
		int phone = Integer.parseInt(sc.nextLine());
		System.out.print("Please enter the adress: ");
		String adress= sc.nextLine();
		
		app.addClient(nit,id_type,id_number,name,phone,adress);
		
		System.out.println("The client has been added succesfully");
	}
	private void addProduct() {
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
		int newPhone=0;
		System.out.println("What update do you want to do? Choose an option");
		System.out.println("1. Restaurant \n");
		System.out.println("2. Client \n");
		System.out.println("3. Product \n");;
		System.out.println("4. Order \n");;
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
				newPhone=Integer.parseInt(sc.nextLine());
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
			System.out.println("Enter the code of the product");
			String code=sc.nextLine();
			app.updateProduct(code);
		}
		else {
			System.out.println("Enter the code of the order");
			String order_Code=sc.nextLine();
			app.updateOrder(order_Code);
		}
	}
	private void showRestaurants() {
		app.sortByNameRest();
		System.out.println(app.getRestaurants());
	}
	private void showClients() {
		System.out.println("Enter the nit of the restaurant");
		String nit=sc.nextLine();
		app.sortByClient(nit);
		System.out.println(app.toStringClients(nit));	
	}
	private void orderStatus() {
		
	}
	private void searchClient() {
		
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
				app.importDataProduct(name,nit);
				System.out.println("The file has been imported successfully");
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