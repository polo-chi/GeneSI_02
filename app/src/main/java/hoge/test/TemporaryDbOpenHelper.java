package hoge.test;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Chihiro Iijima on 2016/03/04.
 */
public class TemporaryDbOpenHelper extends SQLiteOpenHelper{

    static final String DATABASE_NAME = "TemporaryInsert.db";
    static final int DATABASE_VERSION = 1;

    public TemporaryDbOpenHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database){

        database.beginTransaction();
        try {
            database.execSQL("CREATE TABLE "
                    + TemporaryRecordItemDb.TABLE_NAME_ITEM + "("
                    /*+ TemporaryRecordItemDb.COLUMN_PASS + " TEXT, "*/
                    + TemporaryRecordItemDb.COLUMN_TITLE + " TEXT NOT NULL, "
                    + TemporaryRecordItemDb.COLUMN_DNA + " TEXT NOT NULL, "
                    + TemporaryRecordItemDb.COLUMN_KIND + " TEXT NOT NULL, "
                    + TemporaryRecordItemDb.COLUMN_MEMO + " TEXT "
                    + TemporaryRecordItemDb.COLUMN_POSITION + " INTEGER "+ ");");

            database.setTransactionSuccessful();
        }finally {

            database.endTransaction();

        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

        // db.execSQL("DROP TABLE IF EXISTS " + RecordItemDb.TABLE_NAME_ITEM);
        // onCreate(db);
    }
}
