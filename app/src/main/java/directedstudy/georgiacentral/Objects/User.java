package directedstudy.georgiacentral.Objects;

/**
 * Represents a user object
 */
public class User {
    private int userID;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private boolean isActive;

    /**
     * Constructor
     * @param firstName
     * @param lastName
     * @param email
     * @param password
     * @param phoneNumber
     */
    public User(String firstName, String lastName, String email, String password, String phoneNumber) {
        this.firstName      = firstName;
        this.lastName       = lastName;
        this.email          = email;
        this.password       = password;
        this.phoneNumber    = phoneNumber;
    }//User

    public User(int userID, String firstName, String lastName, String email, String password, String phoneNumber) {
        this.userID         = userID;
        this.firstName      = firstName;
        this.lastName       = lastName;
        this.email          = email;
        this.password       = password;
        this.phoneNumber    = phoneNumber;
    }//User

    /**
     * Constructor
     * @param firstName
     * @param lastName
     * @param email
     */
    public User(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }//User

    /**
     * Constructor
     * @param email
     * @param password
     */
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }//User

    /**
     * Constructor
     * @param email
     */
    public User(String email) {
        this.email = email;
    }

    /**
     * @return userID
     */
    public int getUserID() {
        return userID;
    }//getUserID

    /**
     * Sets userID
     * @param userID
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }//setUserID

    /**
     * @return firstName
     */
    public String getFirstName() {
        return firstName;
    }//getFirstName

    /**
     * Sets firstName
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }//setFirstName

    /**
     * @return lastName
     */
    public String getLastName() {
        return lastName;
    }//getLastName

    /**
     * Sets lastName
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }//setLastName

    /**
     * @return email
     */
    public String getEmail() {
        return email;
    }//getEmail

    /**
     * Sets email
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }//setEmail

    /**
     * @return phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }//getPhoneNumber

    /**
     * Sets phoneNumber
     * @param phoneNumber
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }//setPhoneNumber

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }//getPassword

    /**
     * Sets password
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }//setPassword

    /**
     * @return isActive
     */
    public boolean isActive() {
        return isActive;
    }//isActive

    /**
     * Sets isActive
     * @param active
     */
    public void setActive(boolean active) {
        isActive = active;
    }//setActive

}//class
