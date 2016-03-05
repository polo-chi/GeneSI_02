package hoge.test;

/**
 * Created by Chihiro Iijima on 2016/03/03.
 */

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

import static android.support.v4.app.ActivityCompat.startActivity;

//import static android.support.v4.app.ActivityCompat.startActivity;

//insertに対する操作
public class Insert {

    String InsertKey = null;
    //Activity dactivity = new Activity();

    Insert(){
        //dactivity = activity;
    }

        //Insertのプラスミドマップを標準ブラウザから描く
        //明示的に起動させたいアクティビティ(標準ブラウザ)を指定

    public Intent drawPlasmidMap(String key, String plasmid){

        System.out.println("draw");
        String uriString = "http://www.gene-design.kit.ac.jp/plasmid_android.html?insert="+key+"&plasmid="+plasmid;
        Uri uri = Uri.parse(uriString);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(uri);
       // intent.setClassName("com.android.browser", "com.android.browser.BrowserActivity");
        //startActivity(intent);
        return intent;

    }


   }


   /* public void setInsertKey(){

        String insertData = null;

    }

    public String getInsertKey(){

        return InsertKey;

    }*/

