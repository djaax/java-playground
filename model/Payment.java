package model;

import data.Database;

import java.util.List;

/**
 * This class is the parent class for the three payment methods, namely CreditCard,
 * Cash and MobilePay.
 * 
 */
public class Payment {
	private String bookingRef, method;
	private int amount, id;
	private static int counter;

	/**
	 * Database Constructor
	 * 
	 * @param newId ID of the payment
	 * @param newBookingRef Reference to a (unique) Booking
	 * @param newAmount Amount of the payment
	 * @param newMethod Method of the payment
	 */
	public Payment(int newId, String newBookingRef, int newAmount, String newMethod) {		
		id 				= newId;
		bookingRef 		= newBookingRef;
		amount 			= newAmount;
		method			= newMethod;
		counter++;
	}
	
	/**
	 * In-App Constructor
	 * 
	 * @param newBookingRef Reference to a (unique) Booking
	 * @param newAmount Amount of the payment
	 */
	public Payment(String newBookingRef, int newAmount) {		
		id 				= ++counter;
		bookingRef 		= newBookingRef;
		amount 			= newAmount;
		method 			= null;
	}
	
	/**
	 * Write a line to the database
	 */
	public void toDB() {
		Database.write("payments", this.id+";"+this.bookingRef+";"+this.amount+";"+this.method+";;; ");
	}
	
	// Start getter
	public int getId() {
		return this.id;
	}
	
	public String getBookingRef() {
		return this.bookingRef;
	}
	
	public int getAmount() {
		return this.amount;
	}

	public String getMethod() {
		return this.method;
	}
	// End getter
	
	/**
	 * Set payment method method
	 * @param paymentMethod Payment Method as String (credit card, cash, or mobile pay)
	 */
	public void setMethod(String paymentMethod) {
		this.method = paymentMethod;
	}
	
	// Start print methods
	public static String printTableHeader() {
		return String.format("\t  | %-18s | %-8s | %-15s | %-9s | %-10s |\n",
				"Booking Reference", "Amount", "Payment Method", "Last Four", "Mobile Phone");
	}
	
	public static String printLine(Payment payment) {
		return String.format("\t  | %-18s | %-8s |\n",
			payment.getBookingRef(), payment.getAmount()/100.0);
	}
	
	public String printLine() {
		return String.format("\t  | %-18s | %-8s |\n",
			this.getBookingRef(), this.getAmount()/100.0);
	}
	
	public static String toString(List<Payment> payments) {
		String out = String.format("\n\n\tPAYMENTS:\n");
		out += printTableHeader();
		for ( Payment payment : payments ) {
			out += payment.printLine();
		}
		return out;
	}
	
	public String toString() {
		String out = String.format("\n\n\tPAYMENT:\n");
		out += printTableHeader();
		out += printLine(this);
		return out;
	}
	// End print methods
	
	/**
	 * Report methods printing all payments in the database.
	 */
	public static String report() {
		String out = String.format("\n\n\tPAYMENTS:\n");
		out += printTableHeader();
		for ( Payment payment : Database.getPayments() ) {
			out += payment.printLine();
		}
		return out;
	}
}