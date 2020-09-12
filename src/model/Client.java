package model;

public class Client {
	//Atributes
	private String id_type;
	private String id_number;
	private String name;
	private int phone;
	private String adress;
	//Methods
	/**
	 * 
	 * @param id_type
	 * @param id_number
	 * @param name
	 * @param phone
	 * @param adress
	 */
	public Client(String id_type, String id_number, String name, int phone, String adress) {
		this.id_type=id_type;
		this.id_number=id_number;
		this.name=name;
		this.phone=phone;
		this.adress=adress;
	}
	public String getId_type() {
		return this.id_type;
	}

	/**
	 * 
	 * @param id_type
	 */
	public void setId_type(String id_type) {
		this.id_type = id_type;
	}

	public String getId_number() {
		return this.id_number;
	}

	/**
	 * 
	 * @param id_number
	 */
	public void setId_number(String id_number) {
		this.id_number = id_number;
	}

	public String getName(){
		return this.name;
	}

	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	public int getPhone() {
		return this.phone;
	}

	/**
	 * 
	 * @param phone
	 */
	public void setPhone(int phone) {
		this.phone = phone;
	}

	public String getDress() {
		return this.adress;
	}

	/**
	 * 
	 * @param dress
	 */
	public void setDress(String adress) {
		this.adress = adress;
	}


}