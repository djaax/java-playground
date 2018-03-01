package model;

import data.Database;
import view.UserInterface;

/**
 * This class describes a payment method and thus extends the Payment Class.
 * Added variables is (String) mobile phone
 */
public class MobilePay extends Payment {
	String mobilePhone;

	/**
	 * Database Constructor
	 * 
	 * @param newId Id of the payment
	 * @param newBookingRef Reference to a (unique) booking of the payment
	 * @param newAmount Amount of the payment
	 * @param newMobilePhone Mobile phone number of the payment / MP account
	 */
	public MobilePay(int newId, String newBookingRef, int newAmount, String newMobilePhone) {
		super(newId, newBookingRef, newAmount, "mobile pay");
		
		mobilePhone = newMobilePhone;
		super.setMethod("mobile pay");
	}
	
	/**
	 * In-App Constructor
	 * 
	 * @param newBookingRef Reference to a (unique) booking of the payment
	 * @param newAmount Amount of the payment
	 */
	public MobilePay(String newBookingRef, int newAmount) {
		super(newBookingRef, newAmount);
		
		mobilePhone = UserInterface.inputMobilePayNumber();
		super.setMethod("mobile pay");
	}
	
	/**
	 * Write a line to the database
	 */
	public void toDB() {
		Database.write("payments", super.getId()+";"+super.getBookingRef()+";"+super.getAmount()+";"+super.getMethod()+";"+this.mobilePhone+";; ");
	}
	
	// Get mobile phone
	public String getMobilePhone() {
		return this.mobilePhone;
	}
	
	// Start print methods
	public static String printTableHeader() {
		return String.format("\t  | %-18s | %-8s | %-15s | %-12s |\n",
				"Booking Reference", "Amount", "Payment Method", "MobilePhone");
	}
	
	public static String printLine(MobilePay mobilepay) {
		return String.format("\t  | %-18s | %-8s | %-15s | %-12s |\n",
				mobilepay.getBookingRef(), mobilepay.getAmount()/100.0, "Mobile Pay", mobilepay.getMobilePhone());
	}
	
	public String printLine() {
		return String.format("\t  | %-18s | %-8s | %-15s | %-12s | %-12s |\n",
				super.getBookingRef(), super.getAmount()/100.0, "Mobile Pay", "--", this.getMobilePhone());
	}
	
	public String toString() {
		String out = ("\n\n\tCREDIT CARD:\n");
		out += printTableHeader();
		out += printLine(this);
		return out;
	}
	// End print methods
}
