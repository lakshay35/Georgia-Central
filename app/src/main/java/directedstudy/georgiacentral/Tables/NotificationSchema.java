package directedstudy.georgiacentral.Tables;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;


/**
 * Created by lakshaysharma on 2/27/18.
 */

public class NotificationSchema extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION       = 1;
    private static final String DATABASE_NAME       = "BookDawg.db";
    private static final String TABLE_NOTIFICATION  = "Notification";
    private static final String COLUMN_ID           = "id";
    private static final String COLUMN_TITLE         = "title";
    private static final String COLUMN_USERID       = "userID";


    public NotificationSchema(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        SQLiteDatabase db = this.getReadableDatabase();
        onCreate(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE IF NOT EXISTS " + TABLE_NOTIFICATION + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITLE + " TEXT NOT NULL, " +
                COLUMN_USERID + " INTEGER NOT NULL" +
                ");";

        db.execSQL(query);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTIFICATION);

        onCreate(db);
    }

    /**
     * Adds a notification
     * @param book
     * @param userID
     * @return boolean
     */
    public boolean addNotification(String book, int userID) {
        if(notificationExists(userID, book)) {
            return false;
        }
        ContentValues values = new ContentValues();

        values.put(COLUMN_TITLE, book);
        values.put(COLUMN_USERID, userID);

        SQLiteDatabase db = getWritableDatabase();

        long result = db.insert(TABLE_NOTIFICATION, null, values);

        db.close();

        return true;
    }

    /**
     * Checks if a notification exists
     * @param userID
     * @param bookTitle
     * @return boolean
     */
    private boolean notificationExists(int userID, String bookTitle) {
        SQLiteDatabase db               = getReadableDatabase();

        onCreate(db);

        String query                    = "SELECT * FROM " + TABLE_NOTIFICATION + " WHERE " + COLUMN_USERID + " = ? AND " + COLUMN_TITLE + " = ?";
        Cursor cursor = db.rawQuery(query, new String[] {Integer.toString(userID), bookTitle});

        if(cursor != null && cursor.moveToFirst() && cursor.getCount() > 0) {
            return true;
        }
        return false;
    }

    /**
     * Retrieves all the notifications for a certain user
     * @param userID
     * @return
     */
    public ArrayList<String> retrieveNotifications(int userID) {
        ArrayList<String> notifications = new ArrayList<String>();
        SQLiteDatabase db               = getReadableDatabase();

        onCreate(db);

        String query                    = "SELECT * FROM " + TABLE_NOTIFICATION + " WHERE " + COLUMN_USERID + " = ?";
        Cursor cursor                   = db.rawQuery(query, new String [] {Integer.toString(userID)});

        while(cursor.moveToNext()){
            notifications.add(cursor.getString(1));
            removeNotification(userID, cursor.getString(1));
        }//if

        cursor.close();

        return notifications;
    }

    /**
     * Removes a notification
     * @param userID
     * @param bookTitle
     */
    private void removeNotification(int userID, String bookTitle) {
        SQLiteDatabase db               = getReadableDatabase();

        onCreate(db);

        String query                    = "DELETE FROM " + TABLE_NOTIFICATION + " WHERE " + COLUMN_USERID + " = '" + userID + "' AND " + COLUMN_TITLE + " = '" + bookTitle + "'";
        try {
            db.execSQL(query);
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
    }
}
