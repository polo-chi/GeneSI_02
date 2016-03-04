package hoge.test;

/**
 * Created by Chihiro Iijima on 2016/03/03.
 */

import android.content.Intent;
import android.net.Uri;

//insertに対する操作
public class Insert {

    String InsertKey = null;

    Insert(){

    }

        //Insertのプラスミドマップを標準ブラウザから描く
        //明示的に起動させたいアクティビティ(標準ブラウザ)を指定

    public void drawPlasmidMap(String key, String plasmid){

        String uriString = "http://www.gene-design.kit.ac.jp/prasmid_android.html?insert="+key+"&plasmid="+plasmid;
        Uri uri = Uri.parse(uriString);
        Intent i = new Intent(Intent.ACTION_VIEW,uri);
        i.setClassName("com.android.browser", "com.android.browser.BrowserActivity");

    }


    public void setInsertKey(){

        String insertData = null;

    }

    public String getInsertKey(){

        return InsertKey;

    }

    }
