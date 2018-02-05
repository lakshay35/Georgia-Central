package directedstudy.georgiacentral.Objects;

public class Textbook {

    private int bookID;
    private String bookTitle;
    private String author;

    public Textbook(int bookID, String bookTitle, String author) {
        this.bookID     = bookID;
        this.bookTitle  = bookTitle;
        this.author     = author;
    }//Textbook

    public int getBookID() {
        return bookID;
    }//getBookID

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }//setBookID

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

}//class
