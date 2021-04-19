package PD;
import java.math.BigDecimal;
import java.time.LocalDate;
/**
 * This class represents an item's price
 */
public class Price implements Comparable<Price> {
	
	/**
	 * The item associated with the price
	 */
	private Item item;
	/**
	 * The amount of the price
	 */
	private BigDecimal price;
	/**
	 * The date the price becomes effective
	 */
	private LocalDate effectiveDate;

	public Item getItem() {
		return this.item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public LocalDate getEffectiveDate() {
		return this.effectiveDate;
	}
	
	public void setEffectiveDate(LocalDate effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	/**
	 * Default Constructor
	 */
	public Price() {
		// TODO - implement Price.Price
	}

	/**
	 * Constructor that takes price and effective date
	 * @param price Amount of the price
	 * @param effectiveDate Date the price becomes effective
	 */
	public Price(BigDecimal price, LocalDate effectiveDate) {
		this();
		this.price = price;
		this.effectiveDate = effectiveDate;
	}

	/**
	 * Returns true if the price is effective for the given date
	 * @param date The date that is checked to determine if the price is effective
	 * @return Returns true if the price is effective for the given date
	 */
	public Boolean isEffective(LocalDate date) {
		return this.effectiveDate.compareTo(date) <= 0;
	}

	/**
	 * Implementation of the Comparable method compareTo().
	 * @param price Price to compare the current price against
	 * @return Negative integer if current object is less than specified object
	 * Positive integer if current object is greater than specified object
	 * Zero if current object is equal to the specified object
	 */
	public int compareTo(Price price) { 
		return this.effectiveDate.compareTo(price.effectiveDate);
		
	}
	
	

	/**
	 * Gets the price for a given quantity
	 * @param quantity Quantity to multiply the price by
	 * @return The price for a given date and quantity
	 */
	public BigDecimal getPrice(int quantity) {
		return this.price.multiply(new BigDecimal(quantity));
	}

	/**
	 * Provides a string representation of Price
	 * @return A string representation of Price
	 */
	public String toString() {
		return "$" + this.getPrice().toString() + " [Start " + this.getEffectiveDate() + "]";
	}

}