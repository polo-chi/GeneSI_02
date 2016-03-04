package hoge.test;

import java.io.Serializable;

/**
 * Created by Chihiro Iijima on 2016/03/04.
 */
public class RecordItemDb implements Serializable {

    /* テーブル名 */
    public static final String TABLE_NAME_ITEM = "LocalBrickData";

    /* カラム名 */
    /* 実際のDataBaseに記録されるカラム名 */
    public static final String COLUMN_PASS = "pass";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_DNA = "DNA";
    public static final String COLUMN_KIND = "kind";
    public static final String COLUMN_MEMO = "memo";


    private String pass = null;
    private String title = null;
    private String DNA = null;
    private String kind = null;
    private String memo = null;
   // private int position = 0;
    private static String itemsearchPass = null;
    private static String itemsearchTitle = null;
    private static String itemsearchDNA = null;
    private static String itemsearchKind = null;
    private static String itemsearchMemo = null;
   // private static int itemsearchposition = 0;



    /* プロパティを操作するメソッド達です */
    public String getPass() { return pass; }
    public void setPass(String pass) { this.pass = pass; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDNA() { return DNA; }
    public void setDNA(String DNA) { this.DNA = DNA; }

    public String getKind() { return kind; }
    public void setKind(String kind) { this.kind = kind; }

    public String getMemo() { return memo; }
    public void setMemo(String memo) { this.memo = memo; }

    public static String getItemSearchPass() { return itemsearchPass; }
    public void setItemSearchPass(String paramPass) { RecordItemDb.itemsearchPass = paramPass; }

    public static String getItemSearchTitle() { return itemsearchTitle; }
    public void setItemSearchTitle(String paramTitle) { RecordItemDb.itemsearchTitle = paramTitle; }

    public static String getItemSearchDNA() { return itemsearchDNA; }
    public void setItemSearchDNA(String paramDNA) { RecordItemDb.itemsearchDNA = paramDNA; }

    public static String getItemSearchKind() { return itemsearchKind; }
    public void setItemSearchKind(String paramKind) { RecordItemDb.itemsearchKind = paramKind; }

    public static String getItemSearchMemo() { return itemsearchMemo; }
    public void setItemSearchMemo(String paramMemo) { RecordItemDb.itemsearchMemo = paramMemo; }


}
