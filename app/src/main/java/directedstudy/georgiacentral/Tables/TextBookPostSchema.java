package directedstudy.georgiacentral.Tables;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;
import java.util.ArrayList;

import directedstudy.georgiacentral.Objects.TextBookDisplay;
import directedstudy.georgiacentral.Objects.TextBookPost;
import directedstudy.georgiacentral.Objects.Textbook;
import directedstudy.georgiacentral.Objects.User;

public class TextBookPostSchema extends SQLiteOpenHelper{
        private static final int DATABASE_VERSION       = 1;
        private static final String DATABASE_NAME       = "BookDawg.db";
        public static final String TABLE_TEXTBOOKPOST   = "TextBookPost";
        public static final String COLUMN_POSTID        = "postID";
        public static final String COLUMN_USERID        = "userID";
        public static final String COLUMN_BOOKID        = "bookID";
        public static final String COLUMN_COURSEID      = "courseID";
        public static final String COLUMN_CONDITIONID   = "conditionID";
        public static final String COLUMN_PRICE         = "price";
        public static final String COLUMN_EXPIREDATE    = "expireDate";
        public static final String COLUMN_POSTDATE      = "postDate";

    public TextBookPostSchema(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }//TextBookPostSchema

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE IF NOT EXISTS " + TABLE_TEXTBOOKPOST + "(" +
                COLUMN_POSTID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_USERID + " INTEGER NOT NULL, " +
                COLUMN_BOOKID + " INTEGER NOT NULL, " +
                COLUMN_COURSEID + " INTEGER, " +
                COLUMN_CONDITIONID + " INTEGER NOT NULL, " +
                COLUMN_PRICE + " NUMERIC NOT NULL, " +
                COLUMN_EXPIREDATE + " TEXT NOT NULL, " +
                COLUMN_POSTDATE + " TEXT NOT NULL, " +
                " FOREIGN KEY (" + COLUMN_USERID + ") REFERENCES User(userId)," +
                " FOREIGN KEY (" + COLUMN_BOOKID + ") REFERENCES Textbook(bookID)," +
                " FOREIGN KEY (" + COLUMN_COURSEID + ") REFERENCES Course(courseID)," +
                " FOREIGN KEY (" + COLUMN_CONDITIONID + ") REFERENCES Condition(conditionID)" +
                ");";

        db.execSQL(query);
    }//onCreate

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TEXTBOOKPOST);

        onCreate(db);
    }//onUpgrade

    public boolean addTextBookPost(TextBookPost textBookPost){
        ContentValues values = new ContentValues();

        values.put(COLUMN_USERID, textBookPost.getUserID());
        values.put(COLUMN_BOOKID, textBookPost.getBookID());
        values.put(COLUMN_COURSEID, textBookPost.getCourseID());
        values.put(COLUMN_CONDITIONID, textBookPost.getConditionID());
        values.put(COLUMN_PRICE, textBookPost.getPrice());
        values.put(COLUMN_EXPIREDATE, textBookPost.getExpireDate());
        values.put(COLUMN_POSTDATE, textBookPost.getPostDate());

        SQLiteDatabase db   = getWritableDatabase();

        onCreate(db);

        long result         = db.insert(TABLE_TEXTBOOKPOST, null, values);

        db.close();

        if(result == -1)
            return false;
        else
            return true;
    }//addTextBookPost

    public boolean deleteTextBookPost(int postID){
        SQLiteDatabase db = getWritableDatabase();

        String query = "DELETE FROM " + TABLE_TEXTBOOKPOST +
                " WHERE " + COLUMN_POSTID + " = " + postID;
        try {
            db.execSQL(query);
            db.close();

            return true;
        }catch(Exception e){
            db.close();

            return false;
        }//try catch
    }//deleteTextBookPost

    public boolean updateTextBookPost(TextBookPost textBookPost){
        SQLiteDatabase db = getWritableDatabase();

        String query = "UPDATE " + TABLE_TEXTBOOKPOST + " SET " +
                COLUMN_BOOKID + " = '" + textBookPost.getBookID() + "', " +
                COLUMN_COURSEID + " = '" + textBookPost.getCourseID() + "', " +
                COLUMN_CONDITIONID + " = '" + textBookPost.getConditionID() + "', " +
                COLUMN_PRICE + " = '" + textBookPost.getPrice() + "', " +
                COLUMN_POSTDATE + " = '" + textBookPost.getPostDate()  +
                "' WHERE " + COLUMN_POSTID + " = '" + textBookPost.getPostID() + "'";
        try {
            db.execSQL(query);
            db.close();

            return true;
        }catch(Exception e){
            db.close();

            return false;
        }//try catch
    }//updateTextBookPost

    public ArrayList<TextBookDisplay> retrieveTextBookDisplayList(){ //String bookTitle, String courseNumber){
        ArrayList<TextBookDisplay> textBookDisplayList  = new ArrayList<TextBookDisplay>();
        SQLiteDatabase db                               = getReadableDatabase();
        String query                                    = "SELECT " +
                "   p.postID, " +
                "   u.firstName, " +
                "   u.lastName, " +
                "   u.email, " +
                "   u.phoneNumber, " +
                "   b.bookTitle, " +
                "   b.author, "  +
                "   c.courseNumber, " +
                "   co.condition, " +
                "   p.price, " +
                "   p.postDate " +
                "   FROM TextBookPost p " +
                "       INNER JOIN User u ON p.userID = u.userID " +
                "       INNER JOIN Textbook b ON p.bookID = b.bookID " +
                "       INNER JOIN Course c ON p.courseID = c.courseID " +
                "       INNER JOIN Condition co ON p.conditionID = co.conditionID ";
                //"   WHERE " +
                //"       b.bookTitle = ? OR " +
                //"       c.courseNumber = ? ";

        Cursor c                                        = db.rawQuery(query, null); //, new String [] {bookTitle, courseNumber});

        while (c.moveToNext()) {
            TextBookDisplay textBookDisplay = new TextBookDisplay(c.getInt(0), c.getString(1) + " " + c.getString(2), c.getString(3), c.getString(4), c.getString(5), c.getString(6), c.getString(7), c.getString(8), c.getFloat(9), c.getString(10));

            textBookDisplayList.add(textBookDisplay);
        }//while

        c.close();

        return textBookDisplayList;
    }//retrieveTextBookDisplayList

    public ArrayList<TextBookDisplay> retrieveTextBookDisplayList(String email){ //String bookTitle, String courseNumber){
        ArrayList<TextBookDisplay> textBookDisplayList  = new ArrayList<TextBookDisplay>();
        SQLiteDatabase db                               = getReadableDatabase();
        String query                                    = "SELECT " +
                "   p.postID, " +
                "   u.firstName, " +
                "   u.lastName, " +
                "   u.email, " +
                "   u.phoneNumber, " +
                "   b.bookTitle, " +
                "   b.author, "  +
                "   c.courseNumber, " +
                "   co.condition, " +
                "   p.price, " +
                "   p.postDate " +
                "   FROM TextBookPost p " +
                "       INNER JOIN User u ON p.userID = u.userID " +
                "       INNER JOIN Textbook b ON p.bookID = b.bookID " +
                "       INNER JOIN Course c ON p.courseID = c.courseID " +
                "       INNER JOIN Condition co ON p.conditionID = co.conditionID " +
                "       WHERE email = ?";

        Cursor c                                        = db.rawQuery(query, new String [] {email});

        while (c.moveToNext()) {
            TextBookDisplay textBookDisplay = new TextBookDisplay(c.getInt(0), c.getString(1) + " " + c.getString(2), c.getString(3), c.getString(4), c.getString(5), c.getString(6), c.getString(7), c.getString(8), c.getFloat(9), c.getString(10));

            textBookDisplayList.add(textBookDisplay);
        }//while

        c.close();

        return textBookDisplayList;
    }//retrieveTextBookDisplayList

}//class

