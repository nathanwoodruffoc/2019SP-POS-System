package PD;
import java.math.BigDecimal;

/**
 * This abstract class represents a Payment method
 */
public abstract class Payment {

	/**
	 * Amount of the payment
	 */
	private BigDecimal amount;
	/**
	 * Amount tendered
	 */
	private BigDecimal amtTendered;

	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getAmtTendered() {
		return this.amtTendered;
	}

	public void setAmtTendered(BigDecimal amtTendered) {
		this.amtTendered = amtTendered;
	}

	/**
	 * Calculates the change
	 * @return Returns the change amount
	 */
	abstract public BigDecimal calcChange();

	/**
	 * Returns true if the payment counts as cash
	 * @return Returns true if the payment counts as cash
	 */
	abstract public Boolean countsAsCash();
	
	abstract public String toString();

}