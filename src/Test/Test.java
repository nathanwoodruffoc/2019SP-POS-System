package Test;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import HI.StringOperations;
import PD.*;


public class Test {
	public static void main(String[] args) {
		test2();
	}
	public static void test1() {
		// create a store with 3 items (with taxcategory, UPC, and Price)
		Store store = new Store("Store 1", "8080");
		
		
		//Item 1
		Item item1 = new Item("8080", "Thing1"); //Number, Name
			//Item 1 Price
			Price price1 = new Price(new BigDecimal("12.56"), StringOperations.toLocalDate("03/15/2018")); //Price, effectivedate
			item1.addPrice(price1);
			//Item 1 UPC
			UPC upc1 = new UPC("4758385737", item1); //UPC code
			item1.addUPC(upc1);
			store.addUPC(upc1);
			//Item 1 TaxCategory
			TaxCategory taxCategory1 = new TaxCategory("Categoryname"); //Tax category name
				//Tax Category Tax Rate
				TaxRate taxRate1 = new TaxRate(StringOperations.toLocalDate("03/15/2018"), new BigDecimal(0.08)); //taxrate date, taxrate rate
			taxCategory1.addTaxRate(taxRate1);
			store.addTaxCategory(taxCategory1);
			item1.setTaxCategory(taxCategory1);
		//Item 1 Add
		store.addItem(item1);
		
		
		//Item 2
		Item item2 = new Item("8443", "Thing2"); //Number, Name
			//Item 2 Price
			Price price2 = new Price(new BigDecimal("62.56"), StringOperations.toLocalDate("03/15/2018")); //Price, effectivedate
			item2.addPrice(price2);
			//Item 2 UPC
			UPC upc2 = new UPC("4758204737", item2); //UPC code
			item2.addUPC(upc2);
			store.addUPC(upc2);
			//Item 2 TaxCategory
			TaxCategory taxCategory2 = new TaxCategory("Categoryname"); //Tax category name
				//Tax Category Tax Rate
				TaxRate taxRate2 = new TaxRate(StringOperations.toLocalDate("03/15/2018"), new BigDecimal(0.08)); //taxrate date, taxrate rate
				taxCategory2.addTaxRate(taxRate2);
			store.addTaxCategory(taxCategory2);
			item2.setTaxCategory(taxCategory2);
		//Item 2 Add
		store.addItem(item2);
		
				
		//Item 3
		Item item3 = new Item("22", "Thing3"); //Number, Name
			//Item 3 Price
			Price price3 = new Price(new BigDecimal("9.56"), StringOperations.toLocalDate("03/15/2018")); //Price, effectivedate
			item3.addPrice(price3);
			//Item 3 UPC
			UPC upc3 = new UPC("4754565737", item3); //UPC code
			item3.addUPC(upc3);
			store.addUPC(upc3);
			//Item 3 TaxCategory
			TaxCategory taxCategory3 = new TaxCategory("Categoryname"); //Tax category name
				//Tax Category Tax Rate
				TaxRate taxRate3 = new TaxRate(StringOperations.toLocalDate("03/15/2018"), new BigDecimal(0.08)); //taxrate date, taxrate rate
			taxCategory3.addTaxRate(taxRate3);
			store.addTaxCategory(taxCategory3);
			item3.setTaxCategory(taxCategory3);
		//Item 3 Add
		store.addItem(item3);
		
		
		//display Store, Items, Taxcategory, UPC, and Prices

		
		

		//create a store with 3 cashiers with person
		//Store store = new Store("Store 2", "1192");
		store.addCashier(new Cashier("1", new Person("Name1", "address", "city", "state", "zip", "phone", "ssn"), "password"));
		store.addCashier(new Cashier("2", new Person("Name2", "address", "city", "state", "zip", "phone", "ssn"), "passwordd"));
		store.addCashier(new Cashier("3", new Person("Name3", "address", "city", "state", "zip", "phone", "ssn"), "passworddd"));
		
		//display data about store and cashiers

		
		
		
		//create a store with 2 registers
		//Store store = new Store("Store 3", "443");
		store.addRegister(new Register("1"));
		store.addRegister(new Register("2"));
		store.addRegister(new Register("3"));
		


		
		//create a session with a sale with 2 salelineitems with quantity and item with prices, tax categories, and taxrates
		Session session = new Session(new Cashier("1", new Person("Name1", "address", "city", "state", "zip", "phone", "ssn"), "password"), new Register("1"));
		
		Sale sale = new Sale();
		sale.addSaleLineItem(new SaleLineItem(sale, item1, 3));
		sale.addSaleLineItem(new SaleLineItem(sale, item2, 2));
		sale.setDateTime(LocalDateTime.now());
		session.addSale(sale);

		
		store.addSession(session);
		//display data about the sale datetime and salelineitems and the subtotal and tax and total 
		
		
		System.out.println(store.toString());
	}
	
	public static void test2() {
		Store myStore = new Store();
		myStore.loadData(); //loads all data into store class
		System.out.println(myStore.toString());
	}
}