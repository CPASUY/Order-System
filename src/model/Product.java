package model;
import exceptions.CeroCostException;
import exceptions.NegativeCostException;
import java.io.Serializable;

public class Product implements Comparable<Product>,Serializable {
	//Constants
	private static final long serialVersionUID = 1L;
	//Atributes
	private String code;
	private String name;
	private String description;
	private double cost;
	private String nit;
	//Methods
	/**
	 * 
	 * @param code!=null
	 * @param name!=null
	 * @param description!=null
	 * @param cost!=null
	 * @param nit!=null
	 */
	public Product(String code, String name, String description, double cost, String nit) throws NegativeCostException,CeroCostException  {
		this.code=code;
		this.name=name;
		this.description=description;
		this.cost=cost;
		if(cost<0) {
			throw new NegativeCostException();
		}
		else if(cost==0) {
			throw new CeroCostException();
		}
		this.nit=nit;	
	}
	/** getCode
     * Method to provide the code of the product
     * @return String code
     */
	public String getCode() {
		return this.code;
	}
	/** setCode
     * Method to changes the code of the product
     * @param code -code of the product!= null
     */
	public void setCode(String code) {
		this.code = code;
	}
	/** getName
     * Method to provide the name of the product
     * @return String name
     */
	public String getName() {
		return this.name;
	}
	/** setName
     * Method to changes the name of the product
     * @param name-name of the product!= null
     */
	public void setName(String name) {
		this.name = name;
	}
	/** getDescription
     * Method to provide the description of the product
     * @return String description
     */
	public String getDescription() {
		return this.description;
	}
	/** setDescription
     * Method to changes the description of the product
     * @param description-description of the product!= null
     */
	public void setDescription(String description) {
		this.description = description;
	}
	/** getCost
     * Method to provide the cost of the product
     * @return double cost
     */
	public double getCost() {
		return this.cost;
	}
	/** setCode
     * Method to changes the cost of the product
     * @param cost-cost of the product!= null
     */
	public void setCost(double cost) throws NegativeCostException, CeroCostException {
		if(cost<0) {
			throw new NegativeCostException();
		}
		else if(cost==0) {
			throw new CeroCostException();
		}
		this.cost = cost;
	}
	/** getNit
     * Method to provide the nit of the restaurant who belong the product
     * @return String nit
     */
	public String getNit() {
		return this.nit;
	}
	/** setNit
     * Method to changes the nit of the restaurant who belongs the product
     * @param nit-nit of restaurant who belongs the product!= null
     */
	public void setNit(String nit) {
		this.nit = nit;
	}
	/** compareTo
     * Method to compares the code and order
     * @param p != null
     */
	@Override
	public int compareTo(Product p) {
		return code.compareToIgnoreCase(p.getCode());

	}
}
