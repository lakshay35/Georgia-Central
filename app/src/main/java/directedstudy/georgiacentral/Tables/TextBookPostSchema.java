package directedstudy.georgiacentral.Tables;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;
import java.util.ArrayList;

import directedstudy.georgiacentral.Objects.TextBookPost;
import directedstudy.georgiacentral.Objects.Textbook;
import directedstudy.georgiacentral.Objects.User;

public class TextBookPostSchema extends SQLiteOpenHelper{
        private static final int DATABASE_VERSION       = 1;
        private static final String DATABASE_NAME       = "BookDawg.db";
        public static final String TABLE_TEXTBOOKPOST   = "TextBook-Post";
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
        String query = "CREATE TABLE " + TABLE_TEXTBOOKPOST + "(" +
                COLUMN_POSTID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_USERID + " INTEGER NOT NULL, " +
                COLUMN_BOOKID + " INTEGER NOT NULL, " +
                COLUMN_COURSEID + " INTEGER NOT NULL UNIQUE, " +
                COLUMN_CONDITIONID + " INTEGER NOT NULL, " +
                COLUMN_PRICE + " NUMERIC NOT NULL, " +
                COLUMN_EXPIREDATE + " REAL NOT NULL, " +
                COLUMN_POSTDATE + " REAL NOT NULL, " +
                " FOREIGN KEY (" + COLUMN_USERID + ") REFERENCES User(userId));";
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
        long result         = db.insert(TABLE_TEXTBOOKPOST, null, values);

        db.close();

        if(result == -1)
            return false;
        else
            return true;
    }//addTextBookPost

}//class

