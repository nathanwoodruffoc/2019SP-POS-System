package PD;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * This class represents an item's price that only applies for a certain period of time
 */
public class PromoPrice extends Price {

	/**
	 * Date that the price stops being effective
	 */
	private LocalDate endDate;

	public LocalDate getEndDate() {
		return this.endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	/**
	 * Default Constructor
	 */
	public PromoPrice() {
		// TODO - implement PromoPrice.PromoPrice
		
	}

	/**
	 * Constructor that takes price, effectiveDate, and endDate
	 * @param price amount of the price
	 * @param effectiveDate Date the promo price becomes effective
	 * @param endDate Date the promo price stops being effective
	 */
	public PromoPrice(BigDecimal price, LocalDate effectiveDate, LocalDate endDate) {
		this();
		setPrice(price);
		setEffectiveDate(effectiveDate);
		this.endDate = endDate;
	}

	/**
	 * Returns true if the PromoPrice is effective for the given date
	 * @param date Date to check if the promo price is effective on
	 * @return Returns true if the PromoPrice is effective for the given date
	 */
	public Boolean isEffective(LocalDate date) {
		return getEffectiveDate().compareTo(date) <= 0 && getEndDate().compareTo(date) >= 0;
	}

	/**
	 * Implements the Comparable method compareTo()
	 * @param price Price to compare against the current price
	 * @return Negative integer if current object is less than specified object
	 * Positive integer if current object is greater than specified object
	 * Zero if current object is equal to the specified object
	 */

	/**
	 * Provides a string representation of PromoPrice
	 * @return A string representation of PromoPrice
	 */
	public String toString() {
		return "$" + this.getPrice() + " [Start " + this.getEffectiveDate().toString() + "] [End " + this.getEndDate().toString() + "]";
	}

}