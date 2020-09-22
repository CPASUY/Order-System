package model;

import java.io.Serializable;

public class Client implements Comparable<Client>,Serializable{
	//Constants
	private static final long serialVersionUID = 1L;
	//Atributes
	private String id_type;
	private String id_number;
	private String name;
	private String phone;
	private String adress;
	//Methods
	/**
	 * 
	 * @param id_type!=null
	 * @param id_number!=null
	 * @param name!=null
	 * @param phone!=null
	 * @param adress!=null
	 */
	public Client(String id_type, String id_number, String name, String phone, String adress) {
		this.id_type=id_type;
		this.id_number=id_number;
		this.name=name;
		this.phone=phone;
		this.adress=adress;
	}
	/** getId_type
     * Method to provide the identification type of the client
     * @return String id type
     */
	public String getId_type() {
		return this.id_type;
	}
	/** setId_type
     * Method to changes the identification type of the client
     * @param id_typr-id_type of the client!= null
     */
	public void setId_type(String id_type) {
		this.id_type = id_type;
	}
	/** getId_number
     * Method to provide the identification number of the client
     * @return String id number
     */
	public String getId_number() {
		return this.id_number;
	}
	/** setId_number
     * Method to changes the identification number of the client
     * @param id_number-id_number of the client!= null
     */
	public void setId_number(String id_number) {
		this.id_number = id_number;
	}
	/** getName
     * Method to provide the name of the client
     * @return String name
     */
	public String getName(){
		return this.name;
	}
	/** setName
     * Method to changes the name of the client
     * @param name-name of the client!= null
     */
	public void setName(String name) {
		this.name = name;
	}
	/** getPhone
     * Method to provide the phone of the client
     * @return String phone
     */
	public String getPhone() {
		return this.phone;
	}
	/** setPhone
     * Method to changes the phone of the client
     * @param phone-phone of the client!= null
     */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/** getAdress
     * Method to provide the adress of the client
     * @return String adress
     */
	public String getAdress() {
		return this.adress;
	}
	/** setAdress
     * Method to changes the adress of the client
     * @param adress-adress of the client!= null
     */
	public void setAdress(String adress) {
		this.adress = adress;
	}
	/** compareTo
     * Method to compares the name and order
     * @param other != null
     */
	@Override
	public int compareTo(Client other) {
		String [] name1=name.split(" ");
		String [] name2=other.getName().split(" ");
		String full1=name1[1]+name1[0];
		String full2=name2[1]+name2[0];
		return full1.compareTo(full2);
	}
}