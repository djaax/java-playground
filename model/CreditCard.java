package model;

import view.UserInterface;
import data.Database;

/**
 * This class describes a payment method and thus extends the Payment Class.
 * Added variables are (String) number, expiry date and security code.
 */
public class CreditCard extends Payment {
	private String number, expiryDate, securityCode;
	
	/**
	 * Database Constructor
	 * 
	 * @param newId ID of the payment instance
	 * @param newBookingRef Reference to a (unique) Booking
	 * @param newAmount Amount of the payment
	 * @param newNumber Credit card number
	 * @param newExpiryDate Credit card expiry date
	 * @param newSecurityCode Credit card security code
	 */
	public CreditCard(int newId, String newBookingRef, int newAmount, String newNumber, String newExpiryDate, String newSecurityCode) {
		super(newId, newBookingRef, newAmount, "credit card");
		
		number 			= newNumber;
		expiryDate 		= newExpiryDate;
		securityCode		= newSecurityCode;
	}
	
	/**
	 * In-App Constructor
	 * 
	 * @param newBookingRef Reference to a (unique) Booking
	 * @param newAmount Amount of the payment
	 */
	public CreditCard(String newBookingRef, int newAmount) {
		super(newBookingRef, newAmount);
		
		number 			= UserInterface.inputCreditCardNumber();
		expiryDate 		= UserInterface.inputCreditCardExpiryDate();
		securityCode		= UserInterface.inputCreditCardSecurityCode();
		super.setMethod("credit card");
	}
	
	/**
	 * Write a line to the database
	 */
	public void toDB() {
		Database.write("payments", super.getId()+";"+super.getBookingRef()+";"+super.getAmount()+";"+super.getMethod()+";"+this.number+";"+this.expiryDate+";"+this.securityCode+" ");
	}
	
	// Start getter
	public String getNumber() {
		return this.number;
	}

	public String getLastFour() {
		return this.number.substring(number.length()-4);
	}

	public String getExpiryDate() {
		return this.expiryDate;
	}

	public String getCvvCode() {
		return this.securityCode;
	}
	// End getter

	
	/**
	 * Implementation of the Luhn algorithm to validate credit card numbers.
	 * 
	 * Adapted from: https://github.com/eix128/gnuc-credit-card-checker/blob/master/CCCheckerPro/src/com/gnuc/java/ccc/Luhn.java
	 * 
	 * @param ccNumber Credit card number
	 * @return boolean check variable.
	 */
    public static boolean luhnCheck(String ccNumber) {
            int sum = 0;
            boolean alternate = false;
            for (int i = ccNumber.length() - 1; i >= 0; i--) {
                    int n = Integer.parseInt(ccNumber.substring(i, i + 1));
                    if (alternate) {
                            n *= 2;
                            if (n > 9) {
                                    n = (n % 10) + 1;
                            }
                    }
                    sum += n;
                    alternate = !alternate;
            }
            return (sum % 10 == 0);
    }
	
	// Start print methods
	public static String printTableHeader() {
		return String.format("\t  | %-18s | %-8s | %-15s | %-9s |\n",
				"Booking Reference", "Amount", "Payment Method", "Last Four");
	}
	
	public static String printLine(CreditCard creditcard) {
		return String.format("\t  | %-18s | %-8s | %-15s | %-9s |\n",
				creditcard.getBookingRef(), creditcard.getAmount()/100.0, "Credit Card", "****"+creditcard.getLastFour());
	}
	
	public String printLine() {
		return String.format("\t  | %-18s | %-8s | %-15s | %-9s | %-12s |\n",
				super.getBookingRef(), super.getAmount()/100.0, "Credit Card", "****"+this.getLastFour(), "--");
	}
	
	public String toString() {
		String out = String.format("\n\n\tCREDIT CARD:\n");
		out += printTableHeader();
		out += printLine(this);
		return out;
	}
	// End print methods
}
