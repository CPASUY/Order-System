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
	 * @param code
	 * @param date
	 * @param code_client
	 * @param nit
	 */
	public Order(String code, Date date, String code_client, String nit,ArrayList<Product> orderList) {
		this.code=code;
		this.date=date;
		this.code_client=code_client;
		this.nit=nit;
		this.orderList=orderList;
		this.status="REQUESTED";
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

	public Date getDate() {
		return this.date;
	}

	/**
	 * 
	 * @param date
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	public String getCode_client() {
		return this.code_client;
	}

	/**
	 * 
	 * @param code_client
	 */
	public void setCode_client(String code_client) {
		this.code_client = code_client;
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

	public ArrayList<Product> getOrderList() {
		return this.orderList;
	}

	/**
	 * 
	 * @param orderList
	 */
	public void setOrderList(ArrayList<Product>orderList) {
		this.orderList = orderList;
	}
	public String getStatus() {
		return this.status;
	}

	/**
	 * 
	 * @param nit
	 */
	public void setStatus(String status) {
		this.status = status;
	}
}
