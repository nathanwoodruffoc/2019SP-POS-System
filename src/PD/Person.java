package PD;
/**
 * This class represents a Person
 */
public class Person {

	/**
	 * The name of the person
	 */
	private String name;
	/**
	 * The address of the person
	 */
	private String address;
	/**
	 * The city of the person
	 */
	private String city;
	/**
	 * The state of the person
	 */
	private String state;
	/**
	 * The zip code of the person
	 */
	private String zip;
	/**
	 * The phone number of the person
	 */
	private String phone;
	/**
	 * The social security number of the person
	 */
	private String sSN;
	/**
	 * The cashier the person used to check out and pay for their items
	 */
	private Cashier cashier;

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	public String getZip() {
		return this.zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}
	
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSSN() {
		return this.sSN;
	}

	public void setSSN(String sSN) {
		this.sSN = sSN;
	}

	public Cashier getCashier() {
		return this.cashier;
	}

	public void setCashier(Cashier cashier) {
		this.cashier = cashier;
	}

	/**
	 * Default constructor
	 */
	public Person() {
		// TODO - implement Person.Person
	}

	/**
	 * Constructor that takes name, address, phone number, and SSN.
	 * @param name The name of the person
	 * @param address The address of the person
	 * @param phone The phone number of the person
	 * @param sSN The social security number of the person
	 */
	public Person(String name, String address, String city, String state, String zip, String phone, String sSN) {
		this();
		this.name = name;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.phone = phone;
		this.sSN = sSN;
	}

	/**
	 * Provides a string representation of Person
	 * @return A string representation of Person
	 */
	public String toString() {
		// TODO - implement Person.toString
		return name + "\t" + address + "\t" + phone + "\t" + sSN + "\n";
	}

}