package directedstudy.georgiacentral.Tables;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;
import java.util.ArrayList;
import directedstudy.georgiacentral.Objects.User;

public class UserSchema extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION       = 1;
    private static final String DATABASE_NAME       = "BookDawg.db";
    public static final String TABLE_USER           = "User";
    public static final String COLUMN_USERID        = "userID";
    public static final String COLUMN_FIRSTNAME     = "firstName";
    public static final String COLUMN_LASTNAME      = "lastName";
    public static final String COLUMN_EMAIL         = "email";
    public static final String COLUMN_PASSWORD      = "password";
    public static final String COLUMN_PHONENUMBER   = "phoneNumber";
    public static final String COLUMN_ISACTIVE      = "isActive";

    /**
     * Constructor
     * @param context
     */
    public UserSchema(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Creates a database
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_USER + "(" +
                COLUMN_USERID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_FIRSTNAME + " TEXT NOT NULL, " +
                COLUMN_LASTNAME + " TEXT NOT NULL, " +
                COLUMN_EMAIL + " TEXT NOT NULL UNIQUE, " +
                COLUMN_PASSWORD + " TEXT NOT NULL, " +
                COLUMN_PHONENUMBER + " TEXT UNIQUE, " +
                COLUMN_ISACTIVE + " BOOLEAN DEFAULT 0 " +
                ");";
        db.execSQL(query);
    }//onCreate

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);

        onCreate(db);
    }//onUpgrade

    /**
     * Logs user in
     * @param user
     * @return User object or null
     */
    public User logIn(User user) {
        SQLiteDatabase db = this.getReadableDatabase();

        String query    = "SELECT * FROM " + TABLE_USER + " WHERE " + COLUMN_EMAIL + " = ?";

        Cursor cursor   = db.rawQuery(query, new String [] {user.getEmail()});

        if(cursor != null && cursor.moveToFirst()&& cursor.getCount()>0){
            User logUser = new User(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5));

            if(logUser.getPassword().equals(user.getPassword())) {
                return logUser;
            }//if
        }//if

        return null;
    }//logIn

    /**
     * Adds a user to the database
     * @param user
     * @return boolean representing success of failure
     */
    public boolean addUser(User user){
        ContentValues values = new ContentValues();

        values.put(COLUMN_FIRSTNAME, user.getFirstName());
        values.put(COLUMN_LASTNAME, user.getLastName());
        values.put(COLUMN_EMAIL, user.getEmail());
        values.put(COLUMN_PASSWORD, user.getPassword());
        values.put(COLUMN_PHONENUMBER, user.getPhoneNumber());
        values.put(COLUMN_ISACTIVE, user.isActive());

        SQLiteDatabase db = getWritableDatabase();

        long result = db.insert(TABLE_USER, null, values);

        db.close();

        if(result == -1)
            return false;
        else
            return true;
    }//addUser

    //Tested - Works Great

    /**
     * Updates User data in the database
     * @param user
     */
    public void updateUser(User user){
        SQLiteDatabase db = getWritableDatabase();

        String query = "UPDATE " + TABLE_USER + " SET " +
                        COLUMN_FIRSTNAME + " = '" + user.getFirstName() + "', " +
                        COLUMN_LASTNAME + " = '" + user.getLastName() + "', " +
                        COLUMN_PASSWORD + " = '" + user.getPassword() + "', " +
                        COLUMN_PHONENUMBER + " = '" + user.getPhoneNumber()  +
                        "' WHERE " + COLUMN_EMAIL + " = '" + user.getEmail() + "'";

        db.execSQL(query);

        db.close();
    }//updateUser

    //Tested - Works Great

    /**
     * Retrieves user data from the database
     * @param user
     * @return User object
     */
    public User retrieveUser(User user){
        SQLiteDatabase db               = getReadableDatabase();
        String query                    = "SELECT * FROM " + TABLE_USER + " WHERE " + COLUMN_EMAIL + " = ?";
        Cursor cursor                   = db.rawQuery(query, new String [] {user.getEmail()});

        if(cursor != null && cursor.moveToFirst()&& cursor.getCount()>0){
            User retrieveUser = new User(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5));

            return retrieveUser;
        }//if

        cursor.close();

        return null;
    }//retrieveUser

    //untested function

    /**
     * Retrieves a list of all the users
     * @return ArrayList<User> Object
     */
    public ArrayList<User> retrieveUsers(){
        ArrayList<User> userList         = new ArrayList<User>();
        SQLiteDatabase db                   = getReadableDatabase();
        String query                        = "SELECT * FROM " + TABLE_USER;
        Cursor cursor                       = db.rawQuery(query,null);

        cursor.moveToFirst();
        while (cursor.moveToNext()) {
            User user = new User(cursor.getString(1), cursor.getString(2), cursor.getString(3));

            userList.add(user);
        }//while

        cursor.close();
        return userList;
    }//retrieveUser

}//class
