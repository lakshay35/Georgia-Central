package directedstudy.georgiacentral.Tables;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import directedstudy.georgiacentral.Objects.Condition;

public class ConditionSchema extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION       = 1;
    private static final String DATABASE_NAME       = "BookDawg.db";
    public static final String TABLE_CONDITION      = "Condition";
    public static final String COLUMN_CONDITIONID   = "conditionID";
    public static final String COLUMN_CONDITION     = "condition";

    public ConditionSchema(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }//ConditionSchema

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_CONDITION + "(" +
                COLUMN_CONDITIONID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_CONDITION + " TEXT NOT NULL " +
                ");";

        db.execSQL(query);

        query = "INSERT INTO " + TABLE_CONDITION + " VALUES(" +
                "New, " +
                "Used - Very Good, " +
                "Used - Good, " +
                "Used - Acceptable, " +
                "Used - Wear and Tear " +
                ");";

        db.execSQL(query);
    }//onCreate

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONDITION);

        onCreate(db);
    }//onUpgrade

    public List<Condition> getConditionList(){
        SQLiteDatabase db               = getReadableDatabase();
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

        return conditionList;
    }//getConditionList

}//class
