package hoge.test;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Chihiro Iijima on 2016/03/04.
 */
public class LocalDbOpenHelper extends SQLiteOpenHelper{

    static final String DATABASE_NAME = "Local.db";
    static final int DATABASE_VERSION = 1;

    public LocalDbOpenHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database){

        database.beginTransaction();
        try {
            database.execSQL("CREATE TABLE "
                    + RecordItemDb.TABLE_NAME_ITEM + "("
                    + RecordItemDb.COLUMN_PASS + " TEXT, "
                    + RecordItemDb.COLUMN_TITLE + " TEXT NOT NULL, "
                    + RecordItemDb.COLUMN_DNA + " TEXT NOT NULL, "
                    + RecordItemDb.COLUMN_KIND + " TEXT NOT NULL, "
                    + RecordItemDb.COLUMN_MEMO + " TEXT " + ");");

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


