package directedstudy.georgiacentral.Objects;

public class User {
    private int userID;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private boolean isActive;

    public User(String firstName, String lastName, String email, String password, String phoneNumber) {
        this.firstName      = firstName;
        this.lastName       = lastName;
        this.email          = email;
        this.password       = password;
        this.phoneNumber    = phoneNumber;
    }//User

    public User(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }//User

    public User(String email, String password) {

        this.email = email;
        this.password = password;
    }//User

    public int getUserID() {
        return userID;
    }//getUserID

    public void setUserID(int userID) {
        this.userID = userID;
    }//setUserID

    public String getFirstName() {
        return firstName;
    }//getFirstName

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }//setFirstName

    public String getLastName() {
        return lastName;
    }//getLastName

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }//setLastName

    public String getEmail() {
        return email;
    }//getEmail

    public void setEmail(String email) {
        this.email = email;
    }//setEmail

    public String getPhoneNumber() {
        return phoneNumber;
    }//getPhoneNumber

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }//setPhoneNumber

    public String getPassword() {
        return password;
    }//getPassword

    public void setPassword(String password) {
        this.password = password;
    }//setPassword

    public boolean isActive() {
        return isActive;
    }//isActive

    public void setActive(boolean active) {
        isActive = active;
    }//setActive

}//class
