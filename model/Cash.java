package model;

import data.Database;

/**
 * This class describes a payment method and thus extends the Payment Class.
 * Added variables is (String) mobilePhone.
 */
public class Cash extends Payment {
	String mobilePhone;

	/**
	 * Database constructor
	 * 
	 * @param newId ID of the payment instance
	 * @param newBookingRef Reference to a (unique) Booking
	 * @param newAmount Amount of the payment
	 */
	public Cash(int newId, String newBookingRef, int newAmount) {
		super(newId, newBookingRef, newAmount, "cash");
	}
	
	/**
	 * In-App Constructor.
	 * 
	 * @param newBookingRef Reference to a (unique) Booking
	 * @param newAmount Amount of the payment
	 */
	public Cash(String newBookingRef, int newAmount) {
		super(newBookingRef, newAmount);
		super.setMethod("cash");
	}
	
	/**
	 * Write a line to the database
	 */
	public void toDB() {
		Database.write("payments", super.getId()+";"+super.getBookingRef()+";"+super.getAmount()+";"+super.getMethod()+";;; ");
	}
	
	// Start print methods
	public static String printTableHeader() {
		return String.format("\t  | %-18s | %-8s | %-15s |\n",
				"Booking Reference", "Amount", "Payment Method");
	}
	
	public static String printLine(Cash cash) {
		return String.format("\t  | %-18s | %-8s | %-15s |\n",
				cash.getBookingRef(), cash.getAmount()/100.0, "Cash");
	}
	
	public String printLine() {
		return String.format("\t  | %-18s | %-8s | %-15s | %-9s | %-12s |\n",
				super.getBookingRef(), super.getAmount()/100.0, "Cash", "--", "--");
	}
	
	public String toString() {
		String out = String.format("\n\n\tCREDIT CARD:\n");
		out += printTableHeader();
		out += printLine(this);
		return out;
	}
	// End print methods
}
