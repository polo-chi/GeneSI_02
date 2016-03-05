package hoge.test;

import java.io.Serializable;

/**
 * Created by Chihiro Iijima on 2016/03/04.
 */
public class TemporaryRecordItemDb implements Serializable{

        /* テーブル名 */
        public static final String TABLE_NAME_ITEM = "TemporaryBrickData";

        /* カラム名 */
    /* 実際のDataBaseに記録されるカラム名 */
       // public static final String COLUMN_PASS = "pass";
    public static final String COLUMN_ROWID = "row_id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_DNA = "DNA";
    public static final String COLUMN_KIND = "kind";
    public static final String COLUMN_MEMO = "memo";
    public static final String COLUMN_POSITION = "position";


    //private String pass = null;
    private int row_id = 0;
    private String title = null;
    private String DNA = null;
    private String kind = null;
    private String memo = null;
    private int position = 0;
   // private static String itemsearchPass = null;
    private static int itemSearchRowId = 0;
    private static String itemSearchTitle = null;
    private static String itemSearchDNA = null;
    private static String itemSearchKind = null;
    private static String itemSearchMemo = null;
    private static int itemSearchPosition = 0;



    /* プロパティを操作するメソッド達です */
   // public String getTempPass() { return pass; }
    //public void setTempPass(String pass) { this.pass = pass; }

    public int getTempRowId() { return row_id; }
    public void setTempRowId(int rowId) { this.row_id = rowId; }

    public String getTempTitle() { return title; }
    public void setTempTitle(String title) { this.title = title; }

    public String getTempDNA() { return DNA; }
    public void setTempDNA(String DNA) { this.DNA = DNA; }

    public String getTempKind() { return kind; }
    public void setTempKind(String kind) { this.kind = kind; }

    public String getTempMemo() { return memo; }
    public void setTempMemo(String memo) { this.memo = memo; }

    public int getTempPosition() { return position; }
    public void setTempPosition(int position) {
        this.position = position;
    }

   // public static String getTempItemSearchPass() { return itemsearchPass; }
    //public void setTempItemSearchPass(String paramPass) { TemporaryRecordItemDb.itemsearchPass = paramPass; }

    public static int getTempItemSearchRowId() { return itemSearchRowId; }
    public void setTempItemSearchRowId(int paramRowId) { TemporaryRecordItemDb.itemSearchRowId = paramRowId; }

    public static String getTempItemSearchTitle() { return itemSearchTitle; }
    public void setTempItemSearchTitle(String paramTitle) { TemporaryRecordItemDb.itemSearchTitle = paramTitle; }

    public static String getTempItemSearchDNA() { return itemSearchDNA; }
    public void setTempItemSearchDNA(String paramDNA) { TemporaryRecordItemDb.itemSearchDNA = paramDNA; }

    public static String getTempItemSearchKind() { return itemSearchKind; }
    public void setTempItemSearchKind(String paramKind) { TemporaryRecordItemDb.itemSearchKind = paramKind; }

    public static String getTempItemSearchMemo() { return itemSearchMemo; }
    public void setTempItemSearchMemo(String paramMemo) { TemporaryRecordItemDb.itemSearchMemo = paramMemo; }

    public static int getTempItemSearchPosition() { return itemSearchPosition; }
    public void setTempItemSearchPosition(int paramPosition) { TemporaryRecordItemDb.itemSearchPosition = paramPosition; }


}


