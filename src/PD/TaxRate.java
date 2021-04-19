package PD;
import java.time.LocalDate;
import java.math.BigDecimal;
import java.math.RoundingMode;
/**
 * This class represents a Tax Rate
 */
public class TaxRate implements Comparable<TaxRate> {

	/**
	 * The tax rate
	 */
	private BigDecimal taxRate;
	/**
	 * The date the tax rate becomes effective.
	 */
	private LocalDate effectiveDate;

	public BigDecimal getTaxRate() {
		return this.taxRate;
	}

	public void setTaxRate(BigDecimal taxRate) {
		this.taxRate = taxRate;
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
	public TaxRate() {
		
	}
	
	/**
	 * Constructor that takes effective date and rate
	 * @param effectiveDate Date the tax rate becomes effective
	 * @param rate The tax rate
	 */
	public TaxRate(LocalDate effectiveDate, BigDecimal rate) {
		this();
		this.effectiveDate = effectiveDate;
		this.taxRate = rate;
	}

	/**
	 * Returns true if the tax rate is effective for the given date
	 * @param date Date to check whether the tax rate is effective on
	 * @return Returns true if the tax rate is effective for the given date
	 */
	public Boolean isEffective(LocalDate date) {
		// TODO - implement TaxRate.isEffective
		throw new UnsupportedOperationException();
	}

	/**
	 * Provides a string representation of TaxRate
	 * @return A string representation of TaxRate
	 */
	public String toString() {
		// TODO - implement TaxRate.toString
		return "Effective Date: " + this.effectiveDate.toString() + " " + this.taxRate.setScale(2, RoundingMode.HALF_UP);
	}
	
	/**
	 * Implements the Comparable method compareTo()
	 * @param taxRate TaxRate to compare against the current TaxRate
	 * @return Negative integer if current object is less than specified object
	 * Positive integer if current object is greater than specified object
	 * Zero if current object is equal to the specified object
	 */
	public int compareTo(TaxRate o) {
		return this.effectiveDate.compareTo(o.getEffectiveDate());
	}

}