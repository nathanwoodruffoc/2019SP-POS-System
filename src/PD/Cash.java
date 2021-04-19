package PD;
import java.math.BigDecimal;
/**
 * This class represents a cash payment method
 */
public class Cash extends Payment {

	/**
	 * Default Constructor
	 */
	public Cash() {
		// TODO - implement Cash.Cash
	}

	/**
	 * Constructors that takes amount and amount tendered
	 * @param amount Amount of the payment
	 * @param amtTendered Amount Tendered
	 */
	public Cash(BigDecimal amount, BigDecimal amtTendered) {
		this();
		setAmount(amount);
		setAmtTendered(amtTendered);
	}

	/**
	 * Calculate change
	 * @return Returns the change amount
	 */
	public BigDecimal calcChange() {
		// TODO - implement Cash.calcChange
		throw new UnsupportedOperationException();
		//amount tendered - sale total? 
	}

	/**
	 * Always returns true because cash always counts as cash
	 * @return Always returns true because cash always counts as cash
	 */
	public Boolean countsAsCash() {
		return true;
	}

	/**
	 * Provides a string representation of Cash
	 * @return Provides a string representation of Cash
	 */
	public String toString() {
		// TODO - implement Cash.toString
		throw new UnsupportedOperationException();
	}

}