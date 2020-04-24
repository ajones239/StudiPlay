package model;


/*
 * This class models accounts. Accounts include a user ID and password and can be instructor or student accounts. 
 */
public class Account 
{
	private String ID;
	private String password;
	private boolean isInstructor;
	
	/*
	 * Constructor for students
	 * @param ID new user's account ID
	 * @param password new user's account password
	 */
	public Account(String ID, String password) {
		this.ID = ID;
		this.password = password;
		isInstructor = false;
	}
	
	/*
	 * Constructor for students
	 * @param ID new user's account ID
	 * @param password new user's account password
	 * @param isInstructor true if this user is an instructor
	 */
	public Account(String ID, String password, boolean isInstructor) {
		this.ID = ID;
		this.password = password;
		this.isInstructor = isInstructor;
	}

	/*
	 * @return ID
	 */
	public String getID() {
		return ID;
	}
	
	/*
	 * @return password
	 */
	public String getPassword() {
		return password;
	}
	
	/*
	 * @return true if the account belongs to an instructor
	 */
	public boolean isInstructor() {
		return isInstructor;
	}
}