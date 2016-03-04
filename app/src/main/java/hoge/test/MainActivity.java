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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
    protected ArrayAdapter<String> adapter3;
    protected int selectedIndex2 = 0;
    protected int selectedIndex3 = 0;

    SortableListView listView1;
    SimpleAdapter simpleadapter;
    List<Element> elementList;
    List<Map<String, ?>> data;

    int mDraggingPosition = -1;

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
        ELEMENT_LIST.add(new Element("Promoter", "promoter", "", 1));
        ELEMENT_LIST.add(new Element("CDS", "cds", "", 2));
        ELEMENT_LIST.add(new Element("Terminator", "terminator", "", 3));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);

        Log.d("test debug", "onCreate");

        /*setTitle("Promoter");

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
        spinner.setSelection(0);*/


        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice);
        adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice);

        elementList = new ArrayList<Element>(ELEMENT_LIST);

        data = elementListToMapList(elementList);
        int layoutResourceId = R.layout.element_for_simple_adapter;

        simpleadapter = new SimpleAdapter(this, data, layoutResourceId, FROM, TO);

        listView1 = (SortableListView) findViewById(R.id.listView);
        listView1.setAdapter(simpleadapter);

        listView1.setDragListener(new DragListener());
        listView1.setSortable(false);

        listView1.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        listView1.setItemChecked(0, true);

        int i = 0, end = ELEMENT_LIST.size();
        Element add;
        for(i =0; i < end; i++)
        {
            add = ELEMENT_LIST.get(i);
            adapter.add(add.getNameElement());
            adapter3.add(add.getNameElement());
        }


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

        Button2.setOnClickListener(new View.OnClickListener() //　編集ボタンを押した時の処理
        {
            @Override
            public void onClick(View v)
            {
                AlertDialog.Builder builder2 = new AlertDialog.Builder(MainActivity.this);
                builder2.setTitle("編集するブリック");
                builder2.setSingleChoiceItems(adapter, selectedIndex2, onDialogClickListener2);
                alertDialog2 = builder2.create();
                alertDialog2.show();
            }
        });

        Button3.setOnClickListener(new View.OnClickListener() //　削除ボタンを押した時の処理
        {
            @Override
            public void onClick(View v)
            {
                AlertDialog.Builder builder3 = new AlertDialog.Builder(MainActivity.this);
                builder3.setTitle("削除するブリック");
                builder3.setSingleChoiceItems(adapter, selectedIndex3, onDialogClickListener3);
                alertDialog3 = builder3.create();
                alertDialog3.show();
            }
        });

        Button4.setOnClickListener(new View.OnClickListener() //入れ替えボタンを押したときの処理
        {
            @Override
            public void onClick(View v) {
                if(listView1.getSortable() == true)
                {
                    listView1.setSortable(false);
                }
                else
                {
                    listView1.setSortable(true);
                }
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

    private DialogInterface.OnClickListener onDialogClickListener2 = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            // AlertDialogで選択された内容を保持
            selectedIndex2 = which;
            Button2.setText(adapter.getItem(which));
            alertDialog2.dismiss();
        }
    };

    private DialogInterface.OnClickListener onDialogClickListener3 = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            // AlertDialogで選択された内容を保持
            selectedIndex3 = which;

            AlertDialog.Builder builder_check = new AlertDialog.Builder(MainActivity.this);
            builder_check.setTitle("確認");
            builder_check.setMessage(adapter.getItem(which) + "を削除しますか？");
            builder_check.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    List<Element> clone = ELEMENT_LIST;
                    int end = clone.size(), line = which + 1;

                    ELEMENT_LIST.clear();
                    int i;

                    if (line == 1) {
                        for (i = 2; i < end; i++) {
                            ELEMENT_LIST.add(clone.get(i));
                        }
                    } else if (line == end) {

                    } else {
                        for (i = 0; i < line - 1; i++) {
                            ELEMENT_LIST.add(clone.get(i));
                        }
                        for (i = line + 1; i < end; i++) {
                            ELEMENT_LIST.add(clone.get(i));
                        }
                    }
                }
            });

            builder_check.setNegativeButton("Cancel", null);
            builder_check.show();
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

        int pos = position;
        switch (pos)
        {
            case 0:
                System.out.println(position);
                break;
            case 1:
                intent2.setClassName("hoge.test", "hoge.test.DetailElement");
                startActivity(intent2);
                position = 1;
                break;
            case 2:
                intent2.setClassName("hoge.test", "hoge.test.ProducePass");
                startActivity(intent2);
                position = 2;
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

    class DragListener extends SortableListView.SimpleDragListener {
        @Override
        public int onStartDrag(int position) {
            mDraggingPosition = position;
            listView1.invalidateViews();
            return position;
        }

        @Override
        public int onDuringDrag(int positionFrom, int positionTo) {
            if (positionFrom < 0 || positionTo < 0
                    || positionFrom == positionTo) {
                return positionFrom;
            }
            int i;
            if (positionFrom < positionTo) {
                final int min = positionFrom;
                final int max = positionTo;
                final Element tmpElement =  elementList.get(min);
                final Map tmpData = data.get(min);
                i = min;
                while (i < max) {
                    Log.d("TEST_D", Integer.toString(i));
                    elementList.set(i, elementList.get(i + 1));
                    data.set(i, data.get(i + 1));
                    i++;
                }
                elementList.set(max, tmpElement);
                data.set(max, tmpData);
            } else if (positionFrom > positionTo) {
                final int min = positionTo;
                final int max = positionFrom;
                final Element tmpElement = elementList.get(max);
                final Map tmpData = data.get(max);
                i = max;
                while (i > min) {
                    Log.d("TEST_D", Integer.toString(i));
                    elementList.set(i, elementList.get(i - 1));
                    data.set(i, data.get(i - 1));
                    i--;
                }
                elementList.set(min, tmpElement);
                data.set(min, tmpData);
            }
            mDraggingPosition = positionTo;

            //int layoutResourceId = R.layout.element_for_simple_adapter;

            simpleadapter.notifyDataSetChanged();
            //listView1.invalidateViews();
            return positionTo;
        }

        @Override
        public boolean onStopDrag(int positionFrom, int positionTo) {
            mDraggingPosition = -1;
            simpleadapter.notifyDataSetChanged();
            return super.onStopDrag(positionFrom, positionTo);
        }
    }
}
