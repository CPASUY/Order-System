package model;

public class Product {
	//Atributes
	private String code;
	private String name;
	private String description;
	private double cost;
	private String nit;
	//Methods
	/**
	 * 
	 * @param code
	 * @param name
	 * @param description
	 * @param cost
	 * @param nit
	 */
	public Product(String code, String name, String description, double cost, String nit) {


	}
	public String getCode() {
		return this.code;
	}

	/**
	 * 
	 * @param code
	 */
	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return this.name;
	}

	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	/**
	 * 
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	public double getCost() {
		return this.cost;
	}

	/**
	 * 
	 * @param cost
	 */
	public void setCost(double cost) {
		this.cost = cost;
	}

	public String getNit() {
		return this.nit;
	}

	/**
	 * 
	 * @param nit
	 */
	public void setNit(String nit) {
		this.nit = nit;
	}



}
