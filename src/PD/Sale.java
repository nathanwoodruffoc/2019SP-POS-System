package PD;
import java.util.*;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * This class represents a sale done by a Cashier on a Register
 */
public class Sale {

	/**
	 * All payments made in the sale
	 */
	private ArrayList<Payment> payments;
	/**
	 * All sale line items in the sale
	 */
	private ArrayList<SaleLineItem> saleLineItems;
	/**
	 * Time that sale was made
	 */
	private LocalDateTime dateTime;
	/**
	 * Indicates that the sale is tax free
	 */
	private Boolean taxFree = false;

	public ArrayList<Payment> getPayments() {
		return this.payments;
	}

	public ArrayList<SaleLineItem> getSaleLineItems() {
		return this.saleLineItems;
	}
	
	public LocalDateTime getDateTime() {
		return this.dateTime;
	}

	public void setTaxFree(Boolean taxFree) {
		this.taxFree = taxFree;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public Boolean getTaxFree() {
		return this.taxFree;
	}

	
	
	/**
	 * Default Constructor
	 */
	public Sale() {
		payments = new ArrayList<Payment>();
		saleLineItems = new ArrayList<SaleLineItem>();
		dateTime = LocalDateTime.now();
		
	}

	/**
	 * Constructor that takes taxFree as a parameter
	 * @param taxFree Indicates the sale is tax-free
	 */
	public Sale(Boolean taxFree) {
		this();
		this.taxFree = taxFree;
		
	}
	
	public Sale(Sale other) {
		this();
		this.dateTime = LocalDateTime.parse(other.dateTime.toString());
		for (Payment p : other.getPayments()) this.addPayment(p);
		for (SaleLineItem sli : other.getSaleLineItems()) this.addSaleLineItem(sli);
		this.taxFree = other.taxFree;
	}

	/**
	 * Adds a payment to the sale
	 * @param payment Payment to add
	 */
	public void addPayment(Payment payment) {
		payments.add(payment);
	}

	/**
	 * Removes a payment from the sale
	 * @param payment Payment to remove
	 */
	public void removePayment(Payment payment) {
		payments.remove(payment);
	}

	/**
	 * Adds a sale line item to the sale
	 * @param sli Sale line item to add
	 */
	public void addSaleLineItem(SaleLineItem sli) {
		saleLineItems.add(sli);
	}

	/**
	 * Removes a sale line item from the sale
	 * @param sli Sale Line Item to remove
	 */
	public void removeSaleLineItem(SaleLineItem sli) {
		saleLineItems.remove(sli);
	}

	/**
	 * Calculates and returns the total price of the sale including tax
	 * @return The total price of the sale including tax
	 */
	public BigDecimal calcTotal() {
		return calcSubTotal().add(calcTax());
	}

	/**
	 * Calculates and returns the total value of the sale before tax
	 * @return The total value of the sale before tax
	 */
	public BigDecimal calcSubTotal() {
		BigDecimal total = new BigDecimal(0);
		for (SaleLineItem i : saleLineItems)
			total = total.add(i.calcSubTotal());
		return total.setScale(2, RoundingMode.HALF_UP);
	}

	/**
	 * Calculates and returns the total amount of tax for the sale
	 * @return The total amount of tax for the sale
	 */
	public BigDecimal calcTax() {
		BigDecimal total = new BigDecimal(0);
		if (!this.taxFree) {
			for (SaleLineItem i : saleLineItems)
				total = total.add(i.calcTax());
		}
		return total.setScale(2, RoundingMode.HALF_UP);
	}

	/**
	 * Returns the total amount paid
	 * @return The total amount paid
	 */
	public BigDecimal calcTotalPayments() {
		// TODO - implement Sale.getTotalPayments
		BigDecimal total = new BigDecimal(0);
		for (Payment payment : this.getPayments()) {
			total = total.add(payment.getAmtTendered());
		}
		return total.setScale(2, RoundingMode.HALF_UP);
	}

	/**
	 * Returns true if the total payment value returned by getTotalPayments() is enough to cover the price of the sale
	 * @return True if the total payment value returned by getTotalPayments() is enough to cover the price of the sale
	 */
	public Boolean isPaymentEnough() {
		return (calcTotalPayments().compareTo(calcTotal()) >= 0); // payments >= total
		
	}

	/**
	 * How much to charge the customer
	 * @param amtTendered Amount tendered
	 */
	public BigDecimal calcAmount(BigDecimal amtTendered) { // change to calcAmountPaid
		BigDecimal result = calcTotal().subtract(calcTotalPayments());
		if(result.compareTo(amtTendered) > 0) result = amtTendered;
		
		return result.setScale(2, RoundingMode.HALF_UP);
	}

	/**
	 * Calculates and returns the change amount; the difference between the amount tendered and the total price of the sale
	 * @return The difference between the amount tendered and the total price of the sale
	 */
	public BigDecimal calcChange() {
		BigDecimal result = this.calcTotalPayments().subtract(this.calcTotal()).setScale(2, RoundingMode.HALF_UP);
		if (result.compareTo(new BigDecimal(0)) < 0) result = new BigDecimal(0);
		//System.out.println("Sale.calcChange(): " + this.calcTotalPayments().toString() + " - " + this.calcTotal() + " = " + result.toString());
		return result;
	}

	/**
	 * Calculates the amount tendered
	 * @return The amount tendered
	 */
	public BigDecimal calcAmtTendered() {		
		BigDecimal total = new BigDecimal(0);
		for (Payment payment : this.getPayments()) {
			total = total.add(payment.getAmtTendered());
		}
		return total.setScale(2, RoundingMode.HALF_UP);
		
	}

	/**
	 * Provides a string representation of Sale
	 * @return A string representation of Sale
	 */
	public String toString() {
		String result = new String();
		
		result += "Date and Time: " + this.dateTime.toString() + "\n";
		for (SaleLineItem sli : this.getSaleLineItems()) {
			result += " " + sli.toString();
		}
		result += "Tax-free:\t" + this.taxFree.toString() + "\nSubTotal: \t$" + this.calcSubTotal() + "\nTax: \t\t$" + this.calcTax() + "\nTotal: \t\t$" + this.calcTotal() + 
				"\nAmount Paid: \t$" + this.calcTotalPayments() + "\nChange: \t$" + this.calcChange() + "\n\n";
		
		return result;
	}

}