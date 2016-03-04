package hoge.test;

/**
 * Created by Chihiro Iijima on 2016/03/03.
 */

import com.loopj.android.http.*;
import cz.msebera.android.httpclient.*;
import java.util.regex.Pattern;

public class AsyncDB {

    String urlSave = "http://www.gene-design.kit.ac.jp/php/save_brick.php";
    String urlLoad = "http://www.gene-design.kit.ac.jp/php/load_brick.php";
    String urlSaveInsert =  "http://www.gene-design.kit.ac.jp/php/save_insert.php";
    final RequestParams params;
    String seq = "seq";
    String id = "load_id";
    String passByAsyncDB;
    String[] brickData;
    String passInsertByAsyncDB;


    //コンストラクタ生成
    public AsyncDB() {
        params = new RequestParams(); //リクエストパラメータ
    }


    //Brickに対する操作

    //DBにデータを追加しパスを得る
    public void setPassByAsyncDB(String brickTitle, String brickDNA, String brickKind, String brickMemo){

        params.put(seq,"1,"+ brickTitle+","+brickDNA+","+brickKind+","+brickMemo); //送るパラメータ

        AsyncHttpClient client = new AsyncHttpClient(); //通信準備

        client.post(urlSave, params, new AsyncHttpResponseHandler() {
            //パラメータを伴い通信開始
            //～通信後の処理～


            @Override
            public void onStart() {

            }

            @Override
            public void onFinish() {

            }

            //通信成功時の処理
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                passByAsyncDB = new String(responseBody);
                // passByAsyncDB = new String();
                // passByAsyncDB = response;
                //  System.out.println("Success   "+passByAsyncDB);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                //  System.out.println("Error");
            }


        });
    }

    public String getPassByAsyncDB(){
        return passByAsyncDB;
    }



    //DBにアクセスし、パスからブリックの情報を得る
    public void setBrickDataByAsyncDB(String pass){

        params.put(id, pass);   //送るパラメータ
        AsyncHttpClient client = new AsyncHttpClient(); //通信準備
        client.post(urlLoad, params, new AsyncHttpResponseHandler() {//パラメータを伴い通信開始

            //～通信後の処理～

            //通信成功時の処理
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                String response = new String(responseBody);
                brickData = new String[5];
                Pattern p = Pattern.compile("[,]");
                brickData = p.split(response);
                // System.out.println("Success   "+brickData[0]+brickData[1]+brickData[2]+brickData[3]);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                //   System.out.println("Error");
            }


            @Override
            public void onStart() {

            }

            @Override
            public void onFinish() {

            }

        });
    }


    //Insertに対する操作
    //
    public void setPassByAsyncDB(String insertData){

        params.put(seq, insertData); //送るパラメータ

        AsyncHttpClient client = new AsyncHttpClient(); //通信準備

        client.post(urlSaveInsert, params, new AsyncHttpResponseHandler() {
            //パラメータを伴い通信開始
            //～通信後の処理～


            @Override
            public void onStart() {

            }

            @Override
            public void onFinish() {

            }

            //通信成功時の処理
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                passInsertByAsyncDB = new String(responseBody);
                // passInsertByAsyncDB = new String();
                // passInsertByAsyncDB = response;
                //  System.out.println("Success   "+passByAsyncDB);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                //  System.out.println("Error");
            }


        });
    }

    public String getPassInsertByAsyncDB(){
        return passInsertByAsyncDB;
    }
/*
//DBにアクセスし、パスからInsertの情報を得る
    public void setInsertDataByAsyncDB(String pass){

        params.put(id, pass);   //送るパラメータ
        AsyncHttpClient client = new AsyncHttpClient(); //通信準備
        client.post(urlLoad, params, new AsyncHttpResponseHandler() {//パラメータを伴い通信開始

            //～通信後の処理～

            //通信成功時の処理
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                String response = new String(responseBody);
                brickData = new String[4];
                Pattern p = Pattern.compile("[,]");
                brickData = p.split(response);
               // System.out.println("Success   "+brickData[0]+brickData[1]+brickData[2]+brickData[3]);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

             //   System.out.println("Error");
            }


            @Override
            public void onStart() {

            }

            @Override
            public void onFinish() {

            }


        });
    }


     */

}
