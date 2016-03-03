package hoge.test;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class ProducePass extends Activity
{
    private static final String KEY_NAME_COMPONENT = "name_component";
    private static final String KEY_NAME_ELEMENT = "name_element";
    private static final String KEY_IMAGE_ID = "image_id";

    protected Button Button;
    protected Button Button2;

    private static final String[] FROM = new String[]
    {
            KEY_IMAGE_ID, KEY_NAME_ELEMENT
    };

    private static final int[] TO = new int[]
    {
            R.id.image_view, R.id.name_element_text_view
    };

    private static final List<Element> ELEMENT_LIST = new ArrayList<Element>();

    static
    {
        ELEMENT_LIST.add(new Element(R.drawable.arrow, "A", "AB"));
        ELEMENT_LIST.add(new Element(R.drawable.terminater, "A", "CD"));
        ELEMENT_LIST.add(new Element(R.drawable.terminater, "B", "EF"));
        ELEMENT_LIST.add(new Element(R.drawable.promoter, "C", "GH"));
        ELEMENT_LIST.add(new Element(R.drawable.arrow, "C", "IJ"));
        ELEMENT_LIST.add(new Element(R.drawable.terminater, "C", "KL"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*final List<Element> elementList = new ArrayList<Element>(ELEMENT_LIST);

        List<Map<String, ?>> data = elementListToMapList(elementList);
        int layoutResourceId = R.layout.element_for_produce_pass_adapter;

        SimpleAdapter simpleadapter;
        simpleadapter = new SimpleAdapter(this, data, layoutResourceId, FROM, TO);

        ListView listView1 = (ListView)findViewById(R.id.listView2);
        listView1.setAdapter(simpleadapter);

        Button = (Button) findViewById(R.id.button);
        Button2 = (Button) findViewById(R.id.button2);

        Button.setText("追加");
        Button2.setText("入れ替え");

        Button.setOnClickListener(new View.OnClickListener() // 追加ボタンを押した時の処理
        {
            @Override
            public void onClick(View v)
            {

            }
        });

        Button2.setOnClickListener(new View.OnClickListener() //　入れ替えボタンを押した時の処理
        {
            @Override
            public void onClick(View v)
            {

            }
        });

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() // リスト要素選択時の処理
        {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {

            }
        });

        listView1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) // リスト要素長押し時の処理
            {
                // ここに処理を記述
                return false;
            }
        });
    }

    private List<Map<String, ?>> elementListToMapList(List<Element> colorItemList)
    {
        List<Map<String, ?>> data = new ArrayList<Map<String, ?>>();
        for (Element element : colorItemList) {
            data.add(elementListToMap(element));
        }
        return data;
    }

    private Map<String, ?> elementListToMap(Element colorItem)
    {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(KEY_IMAGE_ID, Integer.valueOf(colorItem.getImageID()));
        map.put(KEY_NAME_COMPONENT, colorItem.getNameComponent());
        map.put(KEY_NAME_ELEMENT, colorItem.getNameElement());

        return map;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

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

}*/
    }}