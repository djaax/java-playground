package model;

import data.Database;

import java.util.Scanner;


/**
 * 
 * This class‘ purpose is to allow authentication for users.
 * The constructor's purpose is to set the active user to null which
 * then will be modified by the login method (see UserInterace.login).
 * Since only one user can be logged-in at once, the static User variable
 * is set globally and accessible via the public getter getActiveUser(); below. 
 *
 */
public class Auth {
	static Scanner input = new Scanner(System.in);
	private static User activeUser;
	
	
	// To Do: needed?
	public Auth() {
		activeUser = null;
	}
	
	/**
	 * Set the activeUser.
	 */
	public static void setActiveUser(User newActiveUser) {
		activeUser = newActiveUser;
	}
	
	/**
	 * Retrieve the activeUser.
	 * 
	 * @return activeUser
	 */
	public static User getActiveUser() {
		return activeUser;
	}
	
	/**
	 * DEBUG FUNCTION
	 * 
	 * Logs in a user with a specific id automatically to test the application
	 * more rapidly.
	 * Sets the activeUser variable.
	 * 
	 * @param id The id of the user you want to log in as.
	 */
	public static void autoLogin(int id) {
		activeUser = null;
		for ( User user : Database.getUsers() ) {
			if ( user.getId() == id ) {
				activeUser = user;
			}
		}
	}
}