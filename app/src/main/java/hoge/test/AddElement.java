package hoge.test;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

/**
 * Created by 功 on 2016/03/01.
 */
public class AddElement extends Activity {

    int cntAdd = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_element);

       // TemporaryDbOpenHelper dbOpenHelper = new TemporaryDbOpenHelper(this);
        //SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        final TemporaryDBAccess dbAccess = new TemporaryDBAccess(this);

        Button btnAdd = (Button) findViewById(R.id.button);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText etxtBrickTitle = (EditText) findViewById(R.id.editText);
                EditText etxtBrickDNA = (EditText) findViewById(R.id.editText2);
                EditText etxtBrickMemo = (EditText) findViewById(R.id.editText3);
                RadioButton rbtn1 = (RadioButton) findViewById(R.id.radioButton);
                RadioButton rbtn2 = (RadioButton) findViewById(R.id.radioButton);
                RadioButton rbtn3 = (RadioButton) findViewById(R.id.radioButton);


                String strBrickTitle = etxtBrickTitle.getText().toString();
                String strBrickDNA = etxtBrickDNA.getText().toString();
                String strBrickMemo = etxtBrickMemo.getText().toString();

                Brick brk = new Brick();
                brk.setPlaneText(strBrickDNA);
                strBrickDNA = brk.getPlaneText();

                String kind = new String();

                if (rbtn1.isChecked() == true) {
                    kind = "0";
                } else if (rbtn2.isChecked() == true) {
                    kind = "1";
                } else {
                    kind = "2";
                }

                cntAdd++;

                TemporaryRecordItemDb newBrick = new TemporaryRecordItemDb();
                newBrick.setTempTitle(strBrickTitle);
                newBrick.setTempDNA(strBrickDNA);
                newBrick.setTempKind(kind);
                newBrick.setTempMemo(strBrickMemo);
                newBrick.setTempPosition(cntAdd);
                dbAccess.save_item(newBrick);

                etxtBrickTitle.setText("");
                etxtBrickDNA.setText("");
                etxtBrickMemo.setText("");



                Intent intent = new Intent();
                intent.setClassName("hoge.test", "hoge.test.MainActivity");
                startActivity(intent);


                //AsyncDB addDB = new AsyncDB();
                //addDB.setPassByAsyncDB(strBrickTitle, strBrickDNA, kind, strBrickMemo);


            }
        });


    }
}