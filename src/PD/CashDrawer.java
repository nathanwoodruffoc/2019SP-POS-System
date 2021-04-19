package PD;
import java.math.BigDecimal;
/**
 * This class represents a cash drawer in a cash register
 */
public class CashDrawer {

	public CashDrawer() {
		cashAmount = new BigDecimal(0);
		position = 0;
	}

	/**
	 * Amount of cash in the drawer
	 */
	private BigDecimal cashAmount;
	/**
	 * Position of the cash drawer
	 * 0 - Open
	 * 1 - Closed
	 */
	private int position;

	public BigDecimal getCashAmount() {
		return this.cashAmount;
	}

	public void setCashAmount(BigDecimal cashAmount) {
		this.cashAmount = cashAmount;
	}

	public int getPosition() {
		return this.position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	/**
	 * Adds an amount of cash to the cash drawer
	 * @param cash amount of cash to add to the cash drawer
	 */
	public void addCash(BigDecimal cash) {
		cashAmount = cashAmount.add(cash);
	}

	/**
	 * Removes an amount of cash from the cash drawer
	 * @param cash Amount of cash to remove from the cash drawer
	 */
	public void removeCash(BigDecimal cash) {
		cashAmount = cashAmount.subtract(cash);
	}

	public String toString() {
		// TODO - implement CashDrawer.toString
		throw new UnsupportedOperationException();
	}

}