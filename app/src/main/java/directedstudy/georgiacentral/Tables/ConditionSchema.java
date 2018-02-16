package directedstudy.georgiacentral.Tables;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import directedstudy.georgiacentral.Objects.Condition;
import directedstudy.georgiacentral.Objects.Textbook;

public class ConditionSchema extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION       = 1;
    private static final String DATABASE_NAME       = "BookDawg.db";
    public static final String TABLE_CONDITION      = "Condition";
    public static final String COLUMN_CONDITIONID   = "conditionID";
    public static final String COLUMN_CONDITION     = "condition";

    public ConditionSchema(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        SQLiteDatabase db = this.getReadableDatabase();
        onCreate(db);
    }//ConditionSchema

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE IF NOT EXISTS " + TABLE_CONDITION + "(" +
                COLUMN_CONDITIONID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_CONDITION + " TEXT NOT NULL " +
                ");";

        db.execSQL(query);
    }//onCreate

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONDITION);

        onCreate(db);
    }//onUpgrade

    public List<Condition> getConditionList(){
        SQLiteDatabase db               = getReadableDatabase();
        onCreate(db);
        List<Condition> conditionList   = new ArrayList<>();
        String query                    = "SELECT  * FROM " + TABLE_CONDITION;
        Cursor cursor                   = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                conditionList.add(new Condition(Integer.parseInt(cursor.getString(0)),cursor.getString(1)));
            } while (cursor.moveToNext());
        }//if

        cursor.close();
        db.close();

        if(conditionList.size() <= 0){
            addConditions();
            return getConditionList();
        }else
            return conditionList;

    }//getConditionList

    public Condition retrieveCondition(String condition){
        SQLiteDatabase db               = getReadableDatabase();
        String query                    = "SELECT * FROM " + TABLE_CONDITION + " WHERE " + COLUMN_CONDITION + " = ?";
        Cursor cursor                   = db.rawQuery(query, new String [] {condition});

        if(cursor != null && cursor.moveToFirst()&& cursor.getCount()>0){
            Condition retrieveCondition = new Condition(Integer.parseInt(cursor.getString(0)), cursor.getString(1));

            return retrieveCondition;
        }//if

        cursor.close();

        return null;
    }//retrieveCondition

    public void addConditions(){
        SQLiteDatabase db               = getWritableDatabase();

        String query = "INSERT INTO " + TABLE_CONDITION + " (condition) VALUES" +
                "('New'), " +
                "('Used - Very Good'), " +
                "('Used - Good'), " +
                "('Used - Acceptable'), " +
                "('Used - Wear and Tear') " +
                ";";

        db.execSQL(query);
    }//addConditions

}//class
