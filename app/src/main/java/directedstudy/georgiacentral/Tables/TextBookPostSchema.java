package directedstudy.georgiacentral.Tables;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;
import java.util.ArrayList;
import directedstudy.georgiacentral.Objects.User;

public class TextBookPostSchema extends SQLiteOpenHelper{
        private static final int DATABASE_VERSION       = 1;
        private static final String DATABASE_NAME       = "BookDawg.db";
        public static final String TABLE_USER           = "TextBook-Post";
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
        String query = "CREATE TABLE " + TABLE_USER + "(" +
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
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);

        onCreate(db);
    }//onUpgrade

}//class

