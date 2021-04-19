package DM;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import HI.StringOperations;
import PD.Cash;
import PD.Cashier;
import PD.Check;
import PD.Credit;
import PD.Item;
import PD.Payment;
import PD.Person;
import PD.Price;
import PD.PromoPrice;
import PD.Register;
import PD.Sale;
import PD.SaleLineItem;
import PD.Session;
import PD.Store;
import PD.TaxCategory;
import PD.TaxRate;
import PD.UPC;

public class StoreDM {
	public static void loadData(Store store) {
		String filename = "data.csv";
		try {
			Sale sale = null;
			Session session = null;
			Register register = null;
			Cashier cashier = null;
			
			String line = null;
			FileReader fileReader = new FileReader(filename);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			while ((line = bufferedReader.readLine()) != null) {
				String[] result = line.split(",");
				switch (result[0]) {
					case "Store":
						store.setName(result[1]);
						break;					
					
					case "TaxCategory": //1-category, 2-amount, 3-effectivedate
						TaxCategory taxCategory = new TaxCategory(result[1]);
						
						TaxRate taxRate = new TaxRate(StringOperations.toLocalDate(result[3]), new BigDecimal(result[2]));
						taxCategory.addTaxRate(taxRate);
						
						store.addTaxCategory(taxCategory);
						break;
					
					case "Cashier": //1-number, 2-name, 3-ssn, 4-address, 5-city, 6-state, 7-zip, 8-phone, 9-password
						Person person = new Person(result[2], result[4], result[5], result[6], result[7], result[8], result[3]);
						cashier = new Cashier(result[1], person, result[9]);
						store.addCashier(cashier);
						break;
					
					case "Item": //1-number, 2-upc, 3-description, 4-taxcategoryname 5-priceamount, 6-priceEffectiveDate, 7-promoPriceAmount, 8-promoPriceEffectiveDate, 9-promoPriceEndDate
						//New item
						Item item = new Item(result[1], result[3]);
						
						//add upc to item and store
						UPC upc = new UPC(result[2], item);
						item.addUPC(upc);
						store.addUPC(upc);
						
						//add tax category to item
						item.setTaxCategory(store.getTaxCategories().get(result[4]));
						
						//add price to item
						Price price = new Price(new BigDecimal(result[5]), StringOperations.toLocalDate(result[6]));
						item.addPrice(price);
						
						//add promo price to item
						if (result.length > 7) {
							PromoPrice promoPrice = new PromoPrice(new BigDecimal(result[7]), StringOperations.toLocalDate(result[8]), StringOperations.toLocalDate(result[9]));
							item.addPrice(promoPrice);
						}
						//add item to store
						store.addItem(item);
						break;
					
					case "Register": //1-number
						register = new Register(result[1]);
						store.addRegister(register);
						break;
					
					case "Session": //1-cashiernum, 2-registernum
						//new session with cashier and register
						session = new Session(store.findCashierForNumber(result[1]), store.findRegisterForNumber(result[2]));
						session.setEndDateTime(LocalDateTime.now());
						//add session to latest added cashier and register
						register.addSession(session);
						cashier.addSession(session);
						
						//add session to store
						store.addSession(session);
						break;
					
					case "Sale": //1-istaxfree
						sale = new Sale(result[1].equals("Y"));
						sale.setDateTime(LocalDateTime.now());
						session.addSale(sale);
						break;
					
					case "SaleLineItem": //1-itemnum, 2-quantity
						//new salelineitem
						SaleLineItem sli = new SaleLineItem(sale, store.findItemForNumber(result[1]), Integer.parseInt(result[2]));
						
						//add salelineitem to latest added sale
						sale.addSaleLineItem(sli);
						
						break;
					
					case "Payment": // 1-cash, 2-amount, 3-amountTendered
									// 1-credit, 2-amountAsked, 3-amountTendered, 4-cardtype, 5-cardNum, 6-date
									// 1-check, 2-amountAsked, 3-amountTendered, 4-routingNum, 5-acctNum, 6-checkNum
									
						//new payment
						Payment payment = null;
						switch (result[1]) {
							case "Cash": payment = new Cash(new BigDecimal(result[2]), new BigDecimal(result[3])); break;
							case "Credit": payment = new Credit(new BigDecimal(result[2]), new BigDecimal(result[3]), result[4], result[5], StringOperations.toLocalDate(result[6])); break;
							case "Check": payment = new Check(new BigDecimal(result[2]), new BigDecimal(result[3]), result[4], result[5], result[6]); break;
							default: break;
						}
						
						//add payment to latest added sale
						sale.addPayment(payment); 
						
						break;
					default: break;
				}	
			}
			bufferedReader.close();
		}
		catch (FileNotFoundException ex) {
			System.out.println("Unable to open \"" + filename +"\".");
		}
		catch (IOException ex) {
			System.out.println("Error reading \"" + filename + "\".");
		}
		
	}
}
