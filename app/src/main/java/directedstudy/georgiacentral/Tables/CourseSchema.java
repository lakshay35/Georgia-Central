package directedstudy.georgiacentral.Tables;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import directedstudy.georgiacentral.Objects.Course;

public class CourseSchema extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION       = 1;
    private static final String DATABASE_NAME       = "BookDawg.db";
    public static final String TABLE_COURSE         = "Course";
    public static final String COLUMN_COURSEID      = "courseID";
    public static final String COLUMN_CourseNumber  = "courseNumber";

    public CourseSchema(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }//CourseSchema

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_COURSE + "(" +
                COLUMN_COURSEID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_CourseNumber + " TEXT NOT NULL " +
                ");";
        db.execSQL(query);
    }//onCreate

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COURSE);

        onCreate(db);
    }//onUpgrade

    public boolean addCourse(Course course){
        ContentValues values = new ContentValues();

        values.put(COLUMN_CourseNumber, course.getCourseNumber());

        SQLiteDatabase db   = getWritableDatabase();
        long result         = db.insert(TABLE_COURSE, null, values);

        db.close();

        if(result == -1)
            return false;
        else
            return true;
    }//addCourse
}//class