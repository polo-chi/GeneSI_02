package hoge.test;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chihiro Iijima on 2016/03/04.
 */
public class TemporaryDBAccess {

    private TemporaryDbOpenHelper helper = null;

    public TemporaryDBAccess(Context context) {
        helper = new TemporaryDbOpenHelper(context);
    }


    //要素の保存
    public TemporaryRecordItemDb save_item(TemporaryRecordItemDb item){
        SQLiteDatabase db = helper.getWritableDatabase();
        TemporaryRecordItemDb result = null;

        try {
            ContentValues values = new ContentValues();
           // values.put( RecordItemDb.COLUMN_PASS, item.getPass());
            values.put( TemporaryRecordItemDb.COLUMN_TITLE, item.getTempTitle());
            values.put( TemporaryRecordItemDb.COLUMN_DNA, item.getTempDNA());
            values.put( TemporaryRecordItemDb.COLUMN_KIND, item.getTempKind());
            values.put( TemporaryRecordItemDb.COLUMN_MEMO, item.getTempMemo());
            values.put( TemporaryRecordItemDb.COLUMN_POSITION, item.getTempPosition());

            int rowId = item.getTempRowId();

            if( rowId == 0){
                db.insert(TemporaryRecordItemDb.TABLE_NAME_ITEM,
                        null,
                        values);
            }
            else{
                db.update( TemporaryRecordItemDb.TABLE_NAME_ITEM
                        , values
                        , TemporaryRecordItemDb.COLUMN_ROWID + "=?"
                        , new String[]{ String.valueOf( rowId )});
            }
            result = load_item( rowId );

        } finally {
            db.close();
        }
        return result;

    }


    /**
     * レコードの削除
     */
    public void delete_item(TemporaryRecordItemDb item) {
        SQLiteDatabase db = helper.getWritableDatabase();
        try {
            db.delete(TemporaryRecordItemDb.TABLE_NAME_ITEM,
                    TemporaryRecordItemDb.COLUMN_TITLE + "=?",
                    new String[]{String.valueOf(item.getTempTitle())});
        } finally {
            db.close();
        }
    }


    /**
     * レコードの全件削除
     */
    public void deleteall_item() {
        SQLiteDatabase db = helper.getWritableDatabase();
        try {
            String query = "delete from " + TemporaryRecordItemDb.TABLE_NAME_ITEM + ";";
            db.execSQL(query);
        } finally {
            db.close();
        }
    }




    /**
     * idでRecordをロードする
     */
    public TemporaryRecordItemDb load_item(int itemId) {
        SQLiteDatabase db = helper.getReadableDatabase();

        TemporaryRecordItemDb number = null;
        try {
            String query = "select * " +
                    " from " + TemporaryRecordItemDb.TABLE_NAME_ITEM +
                    " where " + TemporaryRecordItemDb.COLUMN_ROWID + " = '" + itemId + "';";
            Cursor cursor = db.rawQuery(query, null);
            cursor.moveToFirst();
            number = getItem(cursor);
        } finally {
            db.close();
        }
        return number;
    }

    /**
     * 検索
     */
    public List list_search_item(TemporaryRecordItemDb record, boolean searchWord ) {
        SQLiteDatabase db = helper.getReadableDatabase();

        List itemList;
        String searchColumn = null;

        /* 検索する条件を設定 */
        //int searchRowId = TemporaryRecordItemDb.getTempItemSearchRowId();
        String searchTitle = TemporaryRecordItemDb.getTempItemSearchTitle();
        String searchDNA = TemporaryRecordItemDb.getTempItemSearchDNA();
        String searchKind = TemporaryRecordItemDb.getTempItemSearchKind();
        String searchMemo = TemporaryRecordItemDb.getTempItemSearchMemo();
        int searchPosition = TemporaryRecordItemDb.getTempItemSearchPosition();

        if( searchWord == true ){
            if( searchTitle.length() > 0 ){

                searchColumn = (TemporaryRecordItemDb.COLUMN_TITLE + " = '" + searchTitle + "' ");

            }else if( searchDNA.length() > 0 ){

                searchColumn = (RecordItemDb.COLUMN_DNA + " = '" + searchDNA + "' ");

            } else if( searchKind.length() > 0 ){

                searchColumn = (RecordItemDb.COLUMN_KIND + " = '" + searchKind + "' ");

            } else if( searchMemo.length() > 0){

                searchColumn = (RecordItemDb.COLUMN_MEMO + " = '" + searchMemo + "' ");

            }else{

                searchColumn = (TemporaryRecordItemDb.COLUMN_POSITION + " = '" + searchPosition + "' ");
            }

        }
        /*else{
            if( searchHi == null ){
                searchCulmn =TemporaryRecordItemDb.COLUMN_ITEMNEN + " = '" + searchNen + "' AND " +
                        TemporaryRecordItemDb.COLUMN_ITEMTUKI + " = '" + searchTuki + "' ";
            }
            else {
                searchCulmn = TemporaryRecordItemDb.COLUMN_ITEMNEN + " = '" + searchNen + "' AND " +
                        TemporaryRecordItemDb.COLUMN_ITEMTUKI + " = '" + searchTuki + "' AND " +
                        TemporaryRecordItemDb.COLUMN_ITEMHI + " = '" + searchHi + "' ";
            }
        }

        /* where句の条件を得たら検索 */
        try {
            String query = null;

            query = "select * " +
                    " from " + TemporaryRecordItemDb.TABLE_NAME_ITEM +
                    " where " + searchColumn + ";";

            Cursor cursor = db.rawQuery(query, null);

            itemList = new ArrayList();
            cursor.moveToFirst();
            while( !cursor.isAfterLast()){
                itemList.add( getItem( cursor ) );
                cursor.moveToNext();
            }
        } finally {
            db.close();
        }
        return itemList;
    }

    /**
     * カーソルからオブジェクトへの変換
     */
    private TemporaryRecordItemDb getItem( Cursor cursor ){
        TemporaryRecordItemDb item = new TemporaryRecordItemDb();

        item.setTempRowId((int) cursor.getLong(0));
        item.setTempTitle(cursor.getString(1));
        item.setTempDNA(cursor.getString(2));
        item.setTempKind(cursor.getString(3));
        item.setTempMemo(cursor.getString(4));
        item.setTempPosition((int) cursor.getLong(5));
        return item;
    }

}
