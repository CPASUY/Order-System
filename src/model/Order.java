package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Order implements Serializable {
	//Constants
	private static final long serialVersionUID = 1L;
	//Atributes
	private String code;
	private Date date;
	private String code_client;
	private String nit;
	private String status;
	//Relations
	private ArrayList<Product> orderList;
	//Methods
	/**
	 * 
	 * @param code!=null
	 * @param date!=null
	 * @param code_client!=null
	 * @param nit!=null
	 */
	public Order(String code, Date date, String code_client, String nit,ArrayList<Product> orderList) {
		this.code=code;
		this.date=date;
		this.code_client=code_client;
		this.nit=nit;
		this.orderList=orderList;
		this.status="REQUESTED";
	}
	/** getCode
     * Method to provide the code of the order
     * @return String code
     */
	public String getCode() {
		return this.code;
	}
	/** setCode
     * Method to changes the code of the order
     * @param code-code of the order!= null
     */
	public void setCode(String code) {
		this.code = code;
	}
	/** getDate
     * Method to provide the date of the order
     * @return String date
     */
	public Date getDate() {
		return this.date;
	}
	/** setDate
     * Method to changes the date  of the order
     * @param date-date of the order!= null
     */
	public void setDate(Date date) {
		this.date = date;
	}
	/** getCode_client
     * Method to provide the code client of the order
     * @return String code_client
     */
	public String getCode_client() {
		return this.code_client;
	}
	/** setCode_client
     * Method to changes the code client of the order
     * @param code_client-code_client of the order!= null
     */
	public void setCode_client(String code_client) {
		this.code_client = code_client;
	}
	/** getNit
     * Method to provide the restaurant nit of the order
     * @return String nit
     */
	public String getNit() {
		return this.nit;
	}
	/** setNit
     * Method to changes the  restaurant nit of the order
     * @param nit-nit of the order!= null
     */
	public void setNit(String nit) {
		this.nit = nit;
	}
	/** getOrderList
     * Method to provide the list of the order
     * @return ArrayList<Product> orderList
     */
	public ArrayList<Product> getOrderList() {
		return this.orderList;
	}
	/** setOrderList
     * Method to changes the list of the order
     * @param orderList-orderList of the order!= null
     */
	public void setOrderList(ArrayList<Product>orderList) {
		this.orderList = orderList;
	}
	/** getStatus
     * Method to provide the status of the order
     * @return String status
     */
	public String getStatus() {
		return this.status;
	}
	/** setStatus
     * Method to changes the status of the order
     * @param status-status of the order!= null
     */
	public void setStatus(String status) {
		this.status = status;
	}
}
