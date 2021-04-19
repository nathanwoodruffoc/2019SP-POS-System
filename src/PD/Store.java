package PD;
import java.util.*;

import DM.StoreDM;

/**
 * This class represents a Store
 */
public class Store {

	/**
	 * The store number
	 */
	private String number;
	/**
	 * The store name
	 */
	private String name;
	/**
	 * Collection of all tax categories
	 */
	private TreeMap<String, TaxCategory> taxCategories;
	/**
	 * Collection of all cashiers
	 */
	private TreeMap<String, Cashier> cashiers;
	/**
	 * Collection of all sessions
	 */
	private ArrayList<Session> sessions;
	/**
	 * Collection of all UPCs
	 */
	private TreeMap<String, UPC> uPCs;
	/**
	 * Collection of all items
	 */
	private TreeMap<String, Item> items;
	/**
	 * Collection of all registers
	 */
	private TreeMap<String, Register> registers;

	public String getNumber() {
		return this.number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TreeMap<String, TaxCategory> getTaxCategories() {
		return this.taxCategories;
	}

	public TreeMap<String, Cashier> getCashiers() {
		return this.cashiers;
	}

	public ArrayList<Session> getSessions() {
		return this.sessions;
	}

	public TreeMap<String, UPC> getUPCs() {
		return this.uPCs;
	}

	public TreeMap<String, Item> getItems() {
		return this.items;
	}

	public TreeMap<String, Register> getRegisters() {
		return this.registers;
	}

	/**
	 * Default Constructor
	 */
	public Store() {
		taxCategories = new TreeMap<String, TaxCategory>();
		cashiers = new TreeMap<String, Cashier>();
		sessions = new ArrayList<Session>();
		uPCs = new TreeMap<String, UPC>();
		items = new TreeMap<String, Item>();
		registers = new TreeMap<String, Register>();
	}

	/**
	 * Constructor that takes name and number
	 * @param name The store name
	 * @param number The store number
	 */
	public Store(String name, String number) {
		this();
		this.name = name;
		this.number = number;
	}

	/**
	 * Adds an item
	 * @param item Item to add
	 */
	public void addItem(Item item) {
		// TODO - confirm item.toString() is proper key value
		items.put(item.getNumber(), item);
	}

	/**
	 * Removes an item
	 * @param item Item to remove
	 */
	public void removeItem(Item item) {
		items.remove(item.getNumber(), item);
	}

	/**
	 * Adds a UPC
	 * @param upc UPC to add
	 */
	public void addUPC(UPC upc) {
		uPCs.put(upc.getUPC(), upc);
	}

	/**
	 * Removes a UPC
	 * @param upc UPC to remove
	 */
	public void removeUPC(UPC upc) {
		uPCs.remove(upc.getUPC(), upc);
	}

	/**
	 * Adds a register
	 * @param register Register to add
	 */
	public void addRegister(Register register) {
		this.registers.put(register.getNumber(), register);
	}

	/**
	 * Removes a register
	 * @param register Register to remove
	 */
	public void removeRegister(Register register) {
		this.registers.remove(register.getNumber(), register);
	}

	/**
	 * Adds a cashier
	 * @param cashier Cashier to add
	 */
	public void addCashier(Cashier cashier) {
		cashiers.put(cashier.getNumber(), cashier);
	}

	/**
	 * Removes a cashier
	 * @param cashier Cashier to remove
	 */
	public void removeCashier(Cashier cashier) {
		cashiers.remove(cashier.getNumber(), cashier);
	}

	/**
	 * Adds a session
	 * @param session Session to add
	 */
	public void addSession(Session session) {
		sessions.add(session);
	}

	/**
	 * Removes a session
	 * @param session Session to remove
	 */
	public void removeSession(Session session) {
		sessions.remove(session);
	}

	/**
	 * Provides a string representation of Store
	 * @return Provides a string representation of Store
	 */
	public String toString() {
		String result = new String();
		//Store Data
		result += "Store Name:\t" + this.name + "\n" +
				  "Store Number:\t" + this.number + "\n";
		
		//Cashiers
		if (this.getCashiers().size() > 0) {
			result += "\n\n" + "Cashiers: --------------------------------------------------------------------------------\n";
			for (Cashier cashier : this.getCashiers().values()) {
				result += cashier.toString();
			}
		}
		
		//Registers
		if (this.getRegisters().size() > 0) {
			result += "\n\n" + "Registers: -------------------------------------------------------------------------------\n";
			for (Register register : this.getRegisters().values()) {
				result += register.toString();
			}
		}
		
		//Items
		if (this.getItems().size() > 0) {
			result += "\n\n" + "Items: -----------------------------------------------------------------------------------\n";
			for (Item item : this.getItems().values()) {
				result += item.toString();
			}
		}
		
		//Sessions
		if (this.getRegisters().size() > 0) {
			result += "\n\n" + "Sessions: --------------------------------------------------------------------------------\n";
			for (Session session : this.getSessions()) {
				result += session.toString();
			}
		}
		
		return result;
	}

	/**
	 * Finds an item associated with the given UPC code
	 * @param upc UPC to search the items with
	 * @return An item associated with the given UPC code
	 */
	public Item findItemForUPC(String uPC) {
		Item item = null;
		if (uPCs.get(uPC) != null) item = uPCs.get(uPC).getItem(); 
		return item; 
	}

	/**
	 * Finds an item associated with the given number
	 * @param number Number to search the items with
	 * @return The item associated with the given number
	 */
	public Item findItemForNumber(String number) {
		return items.get(number);
	}

	/**
	 * Finds a cashier associated with a given cashier number
	 * @param number Number used to search the cashiers with
	 * @return The cashier associated with a given cashier number
	 */
	public Cashier findCashierForNumber(String number) {
		return cashiers.get(number);
	}

	/**
	 * Finds a register associated with a given register number
	 * @param number Number used to search the registers with
	 * @return The register associated with a given register number
	 */
	public Register findRegisterForNumber(String number) {
		return this.registers.get(number);
	}
	
	
	/**
	 * Adds a tax category
	 * @param taxCategory Tax category to add
	 */
	public void addTaxCategory(TaxCategory taxCategory) {
		this.taxCategories.put(taxCategory.getCategory(), taxCategory);
	}

	/**
	 * Removes a tax category
	 * @param taxCategory Tax category to remove
	 */
	public void removeTaxCategory(TaxCategory taxCategory) {
		this.taxCategories.remove(taxCategory.getCategory(), taxCategory);
	}

	public void loadData() {
		StoreDM.loadData(this);
	}

}