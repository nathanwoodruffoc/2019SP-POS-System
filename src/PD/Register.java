package PD;

import java.util.ArrayList;

/**
 * This class represents a Cash Register
 */
public class Register {

	/**
	 * The register number
	 */
	private String number;
	/**
	 * The cash drawer for the register
	 */
	private CashDrawer cashDrawer;
	/**
	 * The sessions for the register
	 */
	private ArrayList<Session> sessions; 

	public String getNumber() {
		return this.number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public CashDrawer getCashDrawer() {
		return this.cashDrawer;
	}

	public void setCashDrawer(CashDrawer cashDrawer) {
		this.cashDrawer = cashDrawer;
	}

	public ArrayList getSessions() {
		return sessions;
	}
	

	
	/**
	 * Default Constructor
	 */
	public Register() {
		sessions = new ArrayList<Session>();
		cashDrawer = new CashDrawer();
	}

	/**
	 * Constructor that takes number
	 * @param number The register number
	 */
	public Register(String number) {
		this();
		this.number = number;
	}

	/**
	 * Determines if the register has any sessions
	 * @return A boolean value corresponding to the absolute fact that the register is either being used at the current moment or not being used.
	 */
	public Boolean isUsed() {
		return (sessions.size() > 0);
	}
	
	/**
	 * Adds a session
	 * @param session The session to add
	 */
	public void addSession(Session session) {
		this.sessions.add(session);
	}
	
	/**
	 * Removes a session
	 * @param session The session to be removed
	 */
	public void removeSession(Session session) {
		this.sessions.remove(session);
	}
	
	/**
	 * Provides a string representation of Register
	 * @return A string representation of Register
	 */
	public String toString() {
		// TODO - Change toString
		return number + "\n";
	}


}