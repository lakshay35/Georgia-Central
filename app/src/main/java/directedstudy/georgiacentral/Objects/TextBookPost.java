package directedstudy.georgiacentral.Objects;

public class TextBookPost {
    private int postID;
    private String bookTitle;
    private String author;
    private String courseNumber;
    private String condition;
    private String name;
    private String email;
    private String expireDate;
    private String postDate;

    public TextBookPost(int postID, String bookTitle, String author, String courseNumber, String condition, String name, String email, String expireDate, String postDate) {
        this.postID         = postID;
        this.bookTitle      = bookTitle;
        this.author         = author;
        this.courseNumber   = courseNumber;
        this.condition      = condition;
        this.name           = name;
        this.email          = email;
        this.expireDate     = expireDate;
        this.postDate       = postDate;
    }//TextBookPost

    public int getPostID() {
        return postID;
    }//getPostID

    public void setPostID(int postID) {
        this.postID = postID;
    }//setPostID

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

    public String getExpireDate() {
        return expireDate;
    }//getExpireDate

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }//setExpireDate

    public String getPostDate() {
        return postDate;
    }//getPostDate

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }//setPostDate

}//class
