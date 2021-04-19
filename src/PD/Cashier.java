package PD;
import java.util.*;

/**
 * This class represents a Cashier
 */
public class Cashier {

	/**
	 * The person the cashier opened the sale with
	 */
	private Person person;
	/**
	 * The cashier's identification number
	 */
	private String number;
	/**
	 * The session the cashier used to make the sale
	 */
	private ArrayList<Session> sessions;
	/**
	 * The cashier's password
	 */
	private String password;

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public String getNumber() {
		return this.number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Collection<Session> getSessions() {
		return this.sessions;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Default constructor
	 */
	public Cashier() {
		this.sessions = new ArrayList<Session>();
		this.person = new Person();
	}

	/**
	 * Constructor that takes Cashier number, person, and password
	 * @param number The number of the cashier
	 * @param person The person the cashier made the sale for
	 * @param password The cashier's password
	 */
	public Cashier(String number, Person person, String password) {
		this();
		this.number = number;
		this.person = person;
		this.password = password;
	}

	/**
	 * Adds a session to the cashier.  Indicates that the cashier has started a new session
	 * @param session Session to add
	 */
	public void addSession(Session session) {
		sessions.add(session);
	}

	/**
	 * Removes a session from the cashier
	 * @param session Session to remove
	 */
	public void removeSession(Session session) {
		sessions.remove(session);
	}

	/**
	 * Checks the password entered for the cashier
	 * @param password The cashier's password
	 * @return True if the password matches and the cashier has been authorized
	 */
	public Boolean isAuthorized(String password) {
		return (this.password.equals(password));
	}

	/**
	 * Provides a string representation of Cashier
	 * @return A string representation of Cashier
	 */
	public String toString() {
		return this.number + " - " + this.person.getName();
	}

}