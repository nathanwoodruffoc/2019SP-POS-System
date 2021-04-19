package PD;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.lang.Boolean;

/**
 * This class represents a credit card payment method
 */
public class Credit extends AuthorizedPayment {

	/**
	 * The card type
	 */
	private String cardType;
	/**
	 * The account number
	 */
	private String cardNumber;
	/**
	 * Expiration Date
	 */
	private LocalDate expirationDate;

	public String getCardType() {
		return this.cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getCardNumber() {
		return this.cardNumber;
	}

	public void setCardNumber(String accountNumber) {
		this.cardNumber = accountNumber;
	}

	public LocalDate getExpirationDate() {
		return this.expirationDate;
	}

	public void setExpirationDate(LocalDate expirationDate) {
		this.expirationDate = expirationDate;
	}

	/**
	 * Default Constructor
	 */
	public Credit() {
		// TODO - implement Credit.Credit
	}

	/**
	 * Constructor that takes card type, account number, and expiration date
	 * @param cardType Card Type
	 * @param accountNumber Account number
	 * @param expirationDate Expiration Date
	 */
	public Credit(BigDecimal amount, BigDecimal amountTendered, String cardType, String cardNumber, LocalDate expirationDate) {
		this();
		this.cardType = cardType;
		this.cardNumber = cardNumber;
		this.expirationDate = expirationDate;
		this.setAmount(amount);
		this.setAmtTendered(amountTendered);
	}

	/**
	 * Returns true if the payment is authorized
	 * @return Returns true if the payment is authorized
	 */
	public Boolean isAuthorized() {
		// TODO - implement Credit.isAuthorized
		throw new UnsupportedOperationException();
	}

	/**
	 * Calculates the change amount
	 * @return The change amount
	 */
	public BigDecimal calcChange() {
		// TODO - implement Credit.calcChange
		throw new UnsupportedOperationException();
	}

	@Override
	public Boolean countsAsCash() {
		// TODO - May be incorrect
		return false;
	}
	
	/**
	 * Provides a string representation of Credit
	 * @return Provides a string representation of Credit
	 */
	public String toString() {
		return "Credit " + this.getAmount() + "\t" + this.getAmtTendered() + "\t" + this.getCardType() + "\t" + this.getCardNumber() + "\t" + this.getExpirationDate().toString();
	}
}