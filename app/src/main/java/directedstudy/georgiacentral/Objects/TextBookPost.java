package directedstudy.georgiacentral.Objects;

public class TextBookPost {
    private int postID;
    private int userID;
    private int bookID;
    private int courseID;
    private int conditionID;
    private String price;
    private String expireDate;
    private String postDate;

    public TextBookPost(int postID, int userID, int bookID, int courseID, int conditionID, String price, String expireDate, String postDate) {
        this.postID         = postID;
        this.userID         = userID;
        this.bookID         = bookID;
        this.courseID       = courseID;
        this.conditionID    = conditionID;
        this.price          = price;
        this.expireDate     = expireDate;
        this.postDate       = postDate;
    }//TextBookPost

    public TextBookPost(int userID, int bookID, int courseID, int conditionID, String price, String expireDate, String postDate) {
        this.userID         = userID;
        this.bookID         = bookID;
        this.courseID       = courseID;
        this.conditionID    = conditionID;
        this.price          = price;
        this.expireDate     = expireDate;
        this.postDate       = postDate;
    }//TextBookPost

    public int getPostID() {
        return postID;
    }//getPostID

    public void setPostID(int postID) {
        this.postID = postID;
    }//setPostID

    public int getUserID() {
        return userID;
    }//getUserID

    public void setUserID(int userID) {
        this.userID = userID;
    }//setUserID

    public int getBookID() {
        return bookID;
    }//getBookID

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }//setBookID

    public int getCourseID() {
        return courseID;
    }//getCourseID

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }//setCourseID

    public int getConditionID() {
        return conditionID;
    }//getConditionID

    public void setConditionID(int conditionID) {
        this.conditionID = conditionID;
    }//setConditionID

    public String getPrice() {
        return price;
    }//getPrice

    public void setPrice(String price) {
        this.price = price;
    }//setPrice

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
