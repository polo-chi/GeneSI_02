/*
 メイン画面
*/

package hoge.test;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends Activity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks
{
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private CharSequence mTitle;

    private static final String KEY_NAME_ELEMENT = "name_element";
    private static final String KEY_IMAGE_ID = "image_id";

    protected Button Button;
    protected Button Button2;
    protected Button Button3;
    protected Button Button4;
    protected AlertDialog alertDialog2;
    protected AlertDialog alertDialog3;
    protected ArrayAdapter<String> adapter;
    protected ArrayAdapter<String> adapter2;
    protected ArrayAdapter<String> adapter3;
    protected ArrayAdapter<String> adapter4;
    protected int selectedIndex = 0;
    protected int selectedIndex2 = 0;
    protected int selectedIndex3 = 0;
    protected int selectedIndex4 = 0;

    private static final String[] FROM = new String[]
            {
                    KEY_IMAGE_ID, KEY_NAME_ELEMENT
            };

    private static final int[] TO = new int[]
            {
                    R.id.image_view, R.id.name_element_text_view
            };

    private static final List<Element> ELEMENT_LIST = new ArrayList<Element>();

    static {
        ELEMENT_LIST.add(new Element(R.drawable.promoter, "Promoter A", "Promoter"));
        ELEMENT_LIST.add(new Element(R.drawable.arrow, "C", "CDS"));
        ELEMENT_LIST.add(new Element(R.drawable.terminater, "C", "Terminator"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);

        /*setTitle("Promoter");

        TextView t1 = (TextView)findViewById(R.id.textView9);
        TextView t2 = (TextView)findViewById(R.id.textView10);
        TextView t3 = (TextView)findViewById(R.id.textView13);

        t1.setText("Promoter");
        t2.setText("CCCCAGGCTC CCCAGCAGGC AGAAGTATGC AAAGCATGCA TCTCAATTAG TCAGCAACCA GGTGTGGAAA AGTCCCCAGG CTCCCCAGCA GGCAGAAGTA TGCAAAGCAT GCATCTCAAT TAGTCAGCAA CCATAGTCCC GCCCCTAACT CCGCCCATCC CGCCCCTAAC TCCGCCCAGT TCCGCCCATT CTCCGCCCCA TGGCTGACTA ATTTTTTTTA TTTATGCAGA GGCCGAGGCC GCCTCGGCCT CTGAGCTA");
        t3.setText("SV40 promoter");*/

        // アダプタの生成(選択済のアイテムを表示するレイアウトを指定)
        /*ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item);
        // ドロップダウンリストのアイテム表示レイアウトを指定
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // リスト内の要素を配列指定
        String[] items = new String[] { "整形", "相補鎖", "逆鎖"};
        // アダプタに要素を追加
        for (String item : items) {
            adapter.add(item);
        }

        Spinner spinner = (Spinner)findViewById(R.id.spinner);
        // SpinnerにAdapterをセット
        spinner.setAdapter(adapter);
        // 選択する要素位置の指定
        spinner.setSelection(0);

// アダプタの生成(選択済のアイテムを表示するレイアウトを指定)
        ArrayAdapter<String> adapte2r = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item);
        // ドロップダウンリストのアイテム表示レイアウトを指定
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // リスト内の要素を配列指定
        String[] items2 = new String[] { "pUASP", "pBSSK-", "pBSSK+", "pCMV2"};
        // アダプタに要素を追加
        for (String item : items) {
            adapter.add(item);
        }
        // Spinnerオブジェクト生成
       Spinner spinner2 = (Spinner)findViewById(R.id.spinner2);
        // SpinnerにAdapterをセット
        spinner.setAdapter(adapter);
        // 選択する要素位置の指定
        spinner.setSelection(0);*/


        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

        adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice);
        adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice);

        final List<Element> elementList = new ArrayList<Element>(ELEMENT_LIST);

        List<Map<String, ?>> data = elementListToMapList(elementList);
        int layoutResourceId = R.layout.element_for_simple_adapter;

        SimpleAdapter simpleadapter;
        simpleadapter = new SimpleAdapter(this, data, layoutResourceId, FROM, TO);

        ListView listView1 = (ListView) findViewById(R.id.listView);
        listView1.setAdapter(simpleadapter);

        listView1.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        listView1.setItemChecked(0, true);


        /*adapter3.add("要素A");
        adapter3.add("要素B");
        adapter3.add("要素C");
        adapter3.add("要素D");*/

        Button = (Button) findViewById(R.id.button);
        Button2 = (Button) findViewById(R.id.button2);
        Button3 = (Button) findViewById(R.id.button3);
        Button4 = (Button) findViewById(R.id.button4);

        Button.setOnClickListener(new View.OnClickListener() // 追加ボタンを押した時の処理
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent();
                intent.setClassName("hoge.test", "hoge.test.AddElement");
                startActivity(intent);
            }
        });

        Button2.setOnClickListener(new View.OnClickListener() //　入れ替えボタンを押した時の処理
        {
            @Override
            public void onClick(View v)
            {

            }
        });

        Button3.setOnClickListener(new View.OnClickListener() //　削除ボタンを押した時の処理
        {
            @Override
            public void onClick(View v)
            {

            }
        });

        Button4.setOnClickListener(new View.OnClickListener() //
        {
            @Override
            public void onClick(View v)
            {

            }
        });

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() // リスト要素選択時の処理
        {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

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

    private List<Map<String, ?>> elementListToMapList(List<Element> colorItemList) {
        List<Map<String, ?>> data = new ArrayList<Map<String, ?>>();
        for (Element element : colorItemList) {
            data.add(elementListToMap(element));
        }
        return data;
    }

    private Map<String, ?> elementListToMap(Element colorItem) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(KEY_IMAGE_ID, Integer.valueOf(colorItem.getImageID()));
        map.put(KEY_NAME_ELEMENT, colorItem.getNameElement());

        return map;
    }


    private View.OnClickListener onClickListener2 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            AlertDialog.Builder builder2 = new AlertDialog.Builder(MainActivity.this);
            builder2.setTitle("");
            builder2.setSingleChoiceItems(adapter2, selectedIndex2, onDialogClickListener2);
            alertDialog2 = builder2.create();
            alertDialog2.show();
        }
    };

    private View.OnClickListener onClickListener3 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            AlertDialog.Builder builder3 = new AlertDialog.Builder(MainActivity.this);
            builder3.setTitle("どれを削除しますか");
            builder3.setSingleChoiceItems(adapter3, selectedIndex3, onDialogClickListener3);
            alertDialog3 = builder3.create();
            alertDialog3.show();
        }
    };

    private DialogInterface.OnClickListener onDialogClickListener2 = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            // AlertDialogで選択された内容を保持
            selectedIndex2 = which;
            Button2.setText(adapter2.getItem(which));
            alertDialog2.dismiss();
        }
    };

    private DialogInterface.OnClickListener onDialogClickListener3 = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            // AlertDialogで選択された内容を保持
            selectedIndex3 = which;
            Button3.setText(adapter3.getItem(which));
            alertDialog3.dismiss();
        }
    };

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

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, DrawerActivity.PlaceholderFragment.newInstance(position + 1))
                .commit();

        Intent intent2 = new Intent();
        System.out.println(position);

        switch (position)
        {
            case 0:
                System.out.println(position);
                break;
            case 1:
                intent2.setClassName("hoge.test", "hoge.test.DetailElement");
                startActivity(intent2);
                break;
            case 2:
                intent2.setClassName("hoge.test", "hoge.test.ProducePass");
                startActivity(intent2);
                break;
        }
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = "①インサートの選択";
                break;
            case 2:
                mTitle = "②プラスミドの選択";
                break;
            case 3:
                mTitle = "③保存・読み込み";
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.activity_main, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }
}
