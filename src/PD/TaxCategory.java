package PD;
import java.util.*;
import java.time.LocalDate;
import java.math.BigDecimal;

/**
 * This class represents a Tax Category
 */
public class TaxCategory {

	/**
	 * String representation of the tax category
	 */
	private String category;
	/**
	 * All the tax rates that are a part of the tax category
	 */
	private TreeSet<TaxRate> taxRates;

	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public TreeSet<TaxRate> getTaxRates() {
		return this.taxRates;
	}

	/**
	 * Default Constructor
	 */
	public TaxCategory() {
		taxRates = new TreeSet<TaxRate>();
	}

	/**
	 * Constructor that takes category
	 * @param category String representation of the tax category
	 */
	public TaxCategory(String category) {
		this();
		this.category = category;
	}

	/**
	 * Returns the tax rate for a given date
	 * @param date Date to check the tax rate on
	 * @return Returns the tax rate for a given date
	 */
	public BigDecimal getTaxRateForDate(LocalDate date) {
		// TODO - implement TaxCategory.getTaxRateForDate
		//rate with the latest effective date before current date
		// go in reverse order?
		for (TaxRate taxRate : taxRates)
			if (taxRate.getEffectiveDate().compareTo(date) <= 0)
				return taxRate.getTaxRate();
		return new BigDecimal(0);
	}

	/**
	 * Adds a tax rate
	 * @param taxRate Tax rate to add
	 */
	public void addTaxRate(TaxRate taxRate) {
		taxRates.add(taxRate);
	}

	/**
	 * Provides a string representation of TaxCategory
	 * @return Provides a string representation of TaxCategory
	 */
	public String toString() {
		// TODO - implement TaxCategory.toString
		//throw new UnsupportedOperationException();
		String taxRateInfo = new String();
		for (TaxRate taxRate : this.taxRates)
			taxRateInfo = taxRateInfo + taxRate.toString() + "\n";
		;
		return this.category + "  (" + this.getTaxRateForDate(LocalDate.now()).toString() + "%)";
	}
	
	public String toString2() {
		// TODO - implement TaxCategory.toString
		//throw new UnsupportedOperationException();
		String taxRateInfo = new String();
		for (TaxRate taxRate : this.taxRates)
			taxRateInfo = taxRateInfo + taxRate.toString() + "\n";
		return this.category + "   " + taxRateInfo;
	}

	/**
	 * Removes a given tax rate
	 * @param taxRate Tax rate to remove
	 */
	public void removeTaxRate(TaxRate taxRate) {
		this.taxRates.remove(taxRate);
	}

}