package controller;

import data.Database;
import view.UserInterface;

/**
 * 
 * @author amir.bohnenkamp@googlemail.com, dustin.jaacks@gmail.com
 * 
 * This class is the entry point of the ShareRUs Application and as such
 * contains the main method. This class class constructors and methods from
 * other classes when needed.
 * 
 * Notes:
 * <ul>
 * <li>We did not implement the CarDatabase. Instead we decided to make a global
 * database class that reads and writes to all external files.</li>
 * <li>For the users we decided to not have child classes because the only thing
 * that changes is the role. Making separate classes for every role would not 
 * be good practice, we figured. Check the Payment child classes for a demonstration
 * of the concept of inheritance.</li>
 * <li>The sudo password is "supersecret".</li>
 * <li>We decided for a static Database design. This is because we not only read but
 * also manipulate/edit/write to the database. Changes made in one instance of a
 * database would ultimately lead to different versions of databases which would
 * compromise the functionality of this application.</li>
 * <li>We developed our own approach to initialize instances read from the database.
 * Please check Booking first to read an explanation.</li>
 * </ul>
 *
 */
public class Application {
	public static void main(String[] args) {
		// Initialize the database
		Database.read("users");
		Database.read("cars");
		Database.read("bookings");
		Database.read("payments");
		
		/*
		 * Debug: Auto-login
		 * 
		 * 1: administrator, 2: customer, 3: car owner
		 */
		//Auth.autoLogin(3);
		
		// Display main navigation
		UserInterface.hello();
	}
}
