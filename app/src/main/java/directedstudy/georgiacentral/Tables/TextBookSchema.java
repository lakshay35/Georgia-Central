package directedstudy.georgiacentral.Tables;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import directedstudy.georgiacentral.Objects.Textbook;
import directedstudy.georgiacentral.Objects.User;

public class TextBookSchema extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION       = 1;
    private static final String DATABASE_NAME       = "BookDawg.db";
    public static final String TABLE_TEXTBOOK       = "Textbook";
    public static final String COLUMN_BOOKID        = "bookID";
    public static final String COLUMN_BOOKTITLE     = "bookTitle";
    public static final String COLUMN_AUTHOR        = "author";

    public TextBookSchema(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        SQLiteDatabase db = this.getReadableDatabase();
        onCreate(db);
    }//TextBookSchema

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE IF NOT EXISTS " + TABLE_TEXTBOOK + "(" +
                COLUMN_BOOKID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_BOOKTITLE + " TEXT NOT NULL, " +
                COLUMN_AUTHOR + " TEXT NOT NULL " +
                ");";
        db.execSQL(query);
    }//onCreate

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TEXTBOOK);

        onCreate(db);
    }//onUpgrade

    public boolean addTextBook(Textbook textBook){
        ContentValues values = new ContentValues();

        values.put(COLUMN_BOOKTITLE, textBook.getBookTitle());
        values.put(COLUMN_AUTHOR, textBook.getAuthor());

        SQLiteDatabase db   = getWritableDatabase();
        long result         = db.insert(TABLE_TEXTBOOK, null, values);

        db.close();

        if(result == -1)
            return false;
        else
            return true;
    }//addTextBook

    public Textbook retrieveTextBook(Textbook textbook){
        SQLiteDatabase db               = getReadableDatabase();

        onCreate(db);

        String query                    = "SELECT * FROM " + TABLE_TEXTBOOK + " WHERE " + COLUMN_BOOKTITLE + " = ? AND " + COLUMN_AUTHOR + " = ? ";
        Cursor cursor                   = db.rawQuery(query, new String [] {textbook.getBookTitle(), textbook.getAuthor()});

        if(cursor != null && cursor.moveToFirst()&& cursor.getCount()>0){
            Textbook retrieveTextBook = new Textbook(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2));

            return retrieveTextBook;
        }//if

        cursor.close();

        return null;
    }//retrieveTextBook

}//class
