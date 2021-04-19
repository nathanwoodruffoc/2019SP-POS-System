package PD;

/**
 * This class represents a payment method that requires authorization
 * 
 * @author Nathan Woodruff
 * @version 1.1
 */
public abstract class AuthorizedPayment extends Payment {

	/**
	 * The authorization code for the payment
	 */
	private String authorizationCode;

	public String getAuthorizationCode() {
		return this.authorizationCode;
	}

	public void setAuthorizationCode(String authorizationCode) {
		this.authorizationCode = authorizationCode;
	}

	/**
	 * Returns true if the payment has been authorized
	 * @return Returns true if the payment has been authorized
	 */
	abstract public Boolean isAuthorized();

	/**
	 * Returns true if the payment counts as cash
	 * @return Returns true if the payment counts as cash
	 */
	abstract public Boolean countsAsCash();
}