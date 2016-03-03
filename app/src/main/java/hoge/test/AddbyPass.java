package hoge.test;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class AddbyPass extends Activity {

    protected Button Button;
    protected Button Button2;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_by_pass);

        Button = (Button) findViewById(R.id.button);
        Button2 = (Button) findViewById(R.id.button2);

        Button.setOnClickListener(new View.OnClickListener() // 追加ボタンを押した時の処理
        {
            @Override
            public void onClick(View v)
            {

                System.out.print("a");
            }
        });

        Button2.setOnClickListener(new View.OnClickListener() //　入れ替えボタンを押した時の処理
        {
            @Override
            public void onClick(View v)
            {
                System.out.print("b");
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
