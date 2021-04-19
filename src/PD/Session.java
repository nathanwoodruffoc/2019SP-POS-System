package PD;
import java.util.*;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * This class represents a cash register session
 */
public class Session {

	/**
	 * The cash register used for the session
	 */
	private Register register;
	/**
	 * The cashier who began the session
	 */
	private Cashier cashier;
	/**
	 * Time the session was started
	 */
	private LocalDateTime startDateTime;
	/**
	 * Time the session was closed
	 */
	private LocalDateTime endDateTime;
	/**
	 * All the sales that were made during the session
	 */
	private ArrayList<Sale> sales;

	public Register getRegister() {
		return this.register;
	}

	public void setRegister(Register register) {
		this.register = register;
	}

	public Cashier getCashier() {
		return this.cashier;
	}

	public void setCashier(Cashier cashier) {
		this.cashier = cashier;
	}

	public LocalDateTime getStartDateTime() {
		return this.startDateTime;
	}

	public void setStartDateTime(LocalDateTime startDateTime) {
		this.startDateTime = startDateTime;
	}

	public LocalDateTime getEndDateTime() {
		return this.endDateTime;
	}

	public void setEndDateTime(LocalDateTime endDateTime) {
		this.endDateTime = endDateTime;
	}

	public ArrayList<Sale> getSales() {
		return this.sales;
	}
	/**
	 * Default constructor
	 */
	public Session() {
		sales = new ArrayList<Sale>();
		this.startDateTime = LocalDateTime.now();
	}
	
	/**
	 * Constructor that takes cashier and register
	 * @param cashier Cashier used in the session
	 * @param register Register used in the session
	 */
	public Session(Cashier cashier, Register register) {
		this();
		this.cashier = cashier;
		this.register = register;
	}

	/**
	 * Adds a sale to the session
	 * @param sale Sale to add
	 */
	public void addSale(Sale sale) {
		sales.add(sale);
	}

	/**
	 * Removes a sale from the session
	 * @param sale Sale to add
	 */
	public void removeSale(Sale sale) {
		sales.remove(sale);
	}

	/**
	 * Calculates the difference between what should be and what actually is in the cash drawer after the session.
	 * @param cash Amount of cash that is actually in the drawer
	 * @return The difference between what should be and what actually is in the cash drawer after the session.
	 */
	public BigDecimal calcCashCountDiff(BigDecimal cash) {
		// TODO - implement Session.calcCashCountDiff
		//return (should be) - (what is)
		//return (initial cashdrawer amount + all sale payments) - cashdrawer amount
		
		return cash.subtract(register.getCashDrawer().getCashAmount());
	}

	/**
	 * Get total
	 */
	public BigDecimal getTotal() {
		BigDecimal total = new BigDecimal(0);
		
		for (Sale sale : this.getSales()) {
			total = total.add(sale.calcTotal());
		}
		return total;
	}
	/**
	 * Provides a string representation of Session
	 * @return A string representation of Session
	 */
	public String toString() {
		String result = new String();
		result += "Session:\tCashier: " + this.getCashier().getPerson().getName()
				+ "\t\tRegister " + this.getRegister().getNumber() 
				+ "\tTotal: $" + this.getTotal().setScale(2, RoundingMode.HALF_UP) 
				+ "\tDate:" + this.startDateTime.toString() + "\n\n";
		
		for (Sale sale : this.getSales()) {
			result += "Sale:\n" + sale.toString();
		}
		
		result += "\n\n";
		return result;
	}

}