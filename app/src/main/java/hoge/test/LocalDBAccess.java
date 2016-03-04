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
public class LocalDBAccess {


    private LocalDbOpenHelper helper = null;

    public LocalDBAccess(Context context) {
        helper = new LocalDbOpenHelper(context);
    }

    /**
     * レコードの保存
     */
    public RecordItemDb save_item( RecordItemDb item){

        SQLiteDatabase db = helper.getWritableDatabase();
        RecordItemDb result = null;

        try {
            ContentValues values = new ContentValues();
            values.put( RecordItemDb.COLUMN_PASS, item.getPass());
            values.put( RecordItemDb.COLUMN_TITLE, item.getTitle());
            values.put( RecordItemDb.COLUMN_DNA, item.getDNA());
            values.put( RecordItemDb.COLUMN_KIND, item.getKind());
            values.put(RecordItemDb.COLUMN_MEMO, item.getMemo());

            db.insert(RecordItemDb.TABLE_NAME_ITEM, null, values);
            String rowPass = item.getPass();
            result = load_item( rowPass );

        } finally {
            db.close();
        }
        return result;
    }

    /**
     * レコードの削除
     */
    public void delete_item(RecordItemDb item) {
        SQLiteDatabase db = helper.getWritableDatabase();
        try {
            db.delete( RecordItemDb.TABLE_NAME_ITEM,
                    RecordItemDb.COLUMN_PASS + "=?",
                    new String[]{ String.valueOf( item.getPass())});
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
            String query = "delete from " + RecordItemDb.TABLE_NAME_ITEM + ";";
            db.execSQL(query);
        } finally {
            db.close();
        }
    }

    /**
     * passでRecordをロードする
     */
    public RecordItemDb load_item(String itemPass) {
        SQLiteDatabase db = helper.getReadableDatabase();

        RecordItemDb strPass = null;
        try {
            String query = "select * " +
                    " from " + RecordItemDb.TABLE_NAME_ITEM +
                    " where " + RecordItemDb.COLUMN_PASS + " = '" + itemPass + "';";
            Cursor cursor = db.rawQuery(query, null);
            cursor.moveToFirst();
            strPass = getItem( cursor );
        } finally {
            db.close();
        }
        return strPass;
    }

    /**
     * 検索
     */
    public List list_search_item(RecordItemDb record, boolean searchWord ) {
        SQLiteDatabase db = helper.getReadableDatabase();

        List itemList;
        String searchColumn = null;

        /* 検索する条件を設定 */
        String searchPass = RecordItemDb.getItemSearchPass();
        String searchTitle = RecordItemDb.getItemSearchTitle();
        String searchDNA = RecordItemDb.getItemSearchDNA();
        String searchKind = RecordItemDb.getItemSearchKind();
        String searchMemo = RecordItemDb.getItemSearchMemo();


        if( searchWord == true ){
            if( searchPass.length() > 0 ){

                searchColumn = (RecordItemDb.COLUMN_PASS + " = '" + searchPass + "' ");

            } else if( searchTitle.length() > 0 ){

                searchColumn = (RecordItemDb.COLUMN_TITLE + " = '" + searchTitle + "' ");

            }else if( searchDNA.length() > 0 ){

                searchColumn = (RecordItemDb.COLUMN_DNA + " = '" + searchDNA + "' ");

            } else if( searchKind.length() > 0 ){

                searchColumn = (RecordItemDb.COLUMN_KIND + " = '" + searchKind + "' ");

            } else{

                searchColumn = (RecordItemDb.COLUMN_MEMO + " = '" + searchMemo + "' ");

            }
        }
       /* else{
            if( searchHi == null ){
                searchCulmn = RecordItem.COLUMN_ITEMNEN + " = '" + searchNen + "' AND " +
                        RecordItem.COLUMN_ITEMTUKI + " = '" + searchTuki + "' ";
            }
            else {
                searchCulmn = RecordItem.COLUMN_ITEMNEN + " = '" + searchNen + "' AND " +
                        RecordItem.COLUMN_ITEMTUKI + " = '" + searchTuki + "' AND " +
                        RecordItem.COLUMN_ITEMHI + " = '" + searchHi + "' ";
            }
        }*/

        /* where句の条件を得たら検索 */
        try {
            String query = null;

            query = "select * " +
                    " from " + RecordItemDb.TABLE_NAME_ITEM +
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
    private RecordItemDb getItem( Cursor cursor ){
        RecordItemDb item = new RecordItemDb();

        item.setPass( cursor.getString(0) );
        item.setTitle(cursor.getString(1));
        item.setDNA(cursor.getString(2));
        item.setKind(cursor.getString(3));
        item.setMemo(cursor.getString(4));
        return item;
    }
}
