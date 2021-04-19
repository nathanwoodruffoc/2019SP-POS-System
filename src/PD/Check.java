package PD;
import java.math.BigDecimal;

/**
 * This class represents a check payment method
 */
public class Check extends AuthorizedPayment {

	/**
	 * The routing number for the check
	 */
	private String routingNumber;
	/**
	 * The account number for the check
	 */
	private String accountNumber;
	/**
	 * The check number
	 */
	private String checkNumber;

	public String getRoutingNumber() {
		return this.routingNumber;
	}

	public void setRoutingNumber(String routingNumber) {
		this.routingNumber = routingNumber;
	}

	public String getAccountNumber() {
		return this.accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getCheckNumber() {
		return this.checkNumber;
	}

	public void setCheckNumber(String checkNumber) {
		this.checkNumber = checkNumber;
	}

	/**
	 * Default Constructor
	 */
	public Check() {
		// TODO - implement Check.Check
	}

	/**
	 * Constructor that takes amount, account number, and check number
	 * @param amount Check amount
	 * @param accountNumber Account number
	 * @param checkNumber Check number
	 */
	public Check(BigDecimal amount, BigDecimal amtTendered, String routingNumber, String accountNumber, String checkNumber) {
		this();
		this.setAmount(amount);
		this.setAmtTendered(amtTendered);
		this.setAccountNumber(accountNumber);
		this.setCheckNumber(checkNumber);
	}

	/**
	 * Returns true if the payment is authorized
	 * @return Returns true if the payment is authorized
	 */
	public Boolean isAuthorized() {
		// TODO - implement Check.isAuthorized
		throw new UnsupportedOperationException();
	}

	/**
	 * Calculates the change amount
	 * @return The change amount
	 */
	public BigDecimal calcChange() {
		// TODO - implement Check.calcChange
		throw new UnsupportedOperationException();
	}
	
	/**
	 * Returns true if the payment counts as cash
	 * @return Returns true if the payment counts as cash
	 */
	public Boolean countsAsCash() {
		return true;
	}
	
	/**
	 * Provides a string representation of Check
	 * @return Provides a string representation of Check
	 */
	public String toString() {
		// TODO - implement Check.toString
		throw new UnsupportedOperationException();
	}
}