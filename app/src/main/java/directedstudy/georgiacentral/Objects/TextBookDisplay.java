package directedstudy.georgiacentral.Objects;

public class TextBookDisplay {

    private int postid;
    private String name;
    private String email;
    private String phoneNumber;
    private String bookTitle;
    private String author;
    private String courseNumber;
    private String condition;
    private float price;
    private String postDate;

    public TextBookDisplay(int postid, String name, String email, String phoneNumber, String bookTitle, String author, String courseNumber, String condition, float price, String postDate) {
        this.postid         = postid;
        this.name           = name;
        this.email          = email;
        this.phoneNumber    = phoneNumber;
        this.bookTitle      = bookTitle;
        this.author         = author;
        this.courseNumber   = courseNumber;
        this.condition      = condition;
        this.price          = price;
        this.postDate       = postDate;
    }//TextBookDisplay

    public TextBookDisplay(String name, String email, String phoneNumber, String bookTitle, String author, String courseNumber, String condition, float price, String postDate) {
        this.name           = name;
        this.email          = email;
        this.phoneNumber    = phoneNumber;
        this.bookTitle      = bookTitle;
        this.author         = author;
        this.courseNumber   = courseNumber;
        this.condition      = condition;
        this.price          = price;
        this.postDate       = postDate;
    }//TextBookDisplay

    public int getPostid() {
        return postid;
    }//getPostid

    public void setPostid(int postid) {
        this.postid = postid;
    }//setPostid

    public String getName() {
        return name;
    }//getName

    public void setName(String name) {
        this.name = name;
    }//setName

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

    public String getBookTitle() {
        return bookTitle;
    }//getBookTitle

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }//setBookTitle

    public String getAuthor() {
        return author;
    }//getAuthor

    public void setAuthor(String author) {
        this.author = author;
    }//setAuthor

    public String getCourseNumber() {
        return courseNumber;
    }//getCourseNumber

    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
    }//setCourseNumber

    public String getCondition() {
        return condition;
    }//getCondition

    public void setCondition(String condition) {
        this.condition = condition;
    }//setCondition

    public float getPrice() {
        return price;
    }//getPrice

    public void setPrice(float price) {
        this.price = price;
    }//setPrice

    public String getPostDate() {
        return postDate;
    }//getPostDate

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }//setPostDate

}//class
