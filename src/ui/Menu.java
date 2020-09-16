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
		String update;
		System.out.println("What update do you want to do? Choose an option");
		update  = "1. Restaurant \n";
		update += "2. Client \n";
		
		update += "3. Product \n";
		update += "4. Order \n";
		int option=Integer.parseInt(sc.nextLine());
		if(option==1) {
			System.out.println("Enter the nit of the restaurant");
			String nit=sc.nextLine();
			app.updateRestaurant(nit);
		}
		else if(option==2) {
			System.out.println("Enter the document number of the client");
			String document=sc.nextLine();
			app.updateClient(document);
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
		
	}
	private void showClients() {
		
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