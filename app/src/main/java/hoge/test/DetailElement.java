package hoge.test;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentManager;
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
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DetailElement extends Activity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks
{
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private CharSequence mTitle;

    private static final String KEY_NAME_ELEMENT = "name_element";
    private static final String KEY_IMAGE_ID = "image_id";

    protected Button Button;

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
        setContentView(R.layout.detail_element);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer2);
        mTitle = getTitle();

        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer2,
                (DrawerLayout) findViewById(R.id.drawer_layout2));

        final List<Element> elementList = new ArrayList<Element>(ELEMENT_LIST);

        List<Map<String, ?>> data = elementListToMapList(elementList);
        int layoutResourceId = R.layout.element_for_simple_adapter;

        SimpleAdapter simpleadapter;
        simpleadapter = new SimpleAdapter(this, data, layoutResourceId, FROM, TO);

        ListView listView1 = (ListView) findViewById(R.id.listView3);
        listView1.setAdapter(simpleadapter);

        listView1.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        listView1.setItemChecked(0, true);

        Button btnDrawPlasmid = (Button)findViewById(R.id.button5);

        //プラスミドマップを描くボタンを押した時の処理
        btnDrawPlasmid.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                AsyncDB addInsertDB = new AsyncDB();
                String insertData = null;
                String insertKey;
                Insert insert = new Insert();
                //Temporary.dbに検索をかけてinsertDataを取得する
                //forで回す？
                //Insertのkey(pass)を取得
                //addInsertDB.setPassByAsyncDB(insertData);
                //insertKey = addInsertDB.getPassByAsyncDB;
                //insert.drawPlasmidMap(insertKey, plasmid);

            }


        });
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

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container2, DrawerActivity.PlaceholderFragment.newInstance(position + 1))
                .commit();

        Intent intent2 = new Intent();
        System.out.println(position);

        switch (position)
        {
            case 0:
                intent2.setClassName("hoge.test", "hoge.test.MainActivity");
                startActivity(intent2);
                break;
            case 1:
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
            View rootView = inflater.inflate(R.layout.detail_element, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
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







    /**
     * mDragString を設定した後に notifyDataSetChanged() する事で Adapter の getView が呼ばれ、
     * mDragString とその行の String との比較によってハイライト状態が更新されます。
     */

/*    public void startDrag(String string) {
        mPosition = -1;
        mSortable = true;
        mDragString = string;
        mAdapter.notifyDataSetChanged();
    }

    public void stopDrag() {
        mPosition = -1;
        mSortable = false;
        mDragString = null;
        mAdapter.notifyDataSetChanged();
    }

    /**
     * ViewHolderパターン
     */
  /*  static class ViewHolder {
        TextView title;
        TextView handle;
    }


//

    public class MyArrayAdapter extends ArrayList<Element> {
        private ArrayList<String> mStrings = new ArrayList<String>();
        private LayoutInflater mInflater;
        private int mLayout;

        public MyArrayAdapter(Context context, int textViewResourceId) {
            super(context, textViewResourceId);
            this.mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            this.mLayout = textViewResourceId;
        }

        @Override
        public void add(String row) {
            super.add(row);
            mStrings.add(row);
        }

        @Override
        public void insert(String row, int position) {
            super.insert(row, position);
            mStrings.add(position, row);
        }

        @Override
        public void remove(String row) {
            super.remove(row);
            mStrings.remove(row);
        }

        @Override
        public void clear() {
            super.clear();
            mStrings.clear();
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder;

            View view = convertView;
            if (view == null) {
                view = mInflater.inflate(this.mLayout, null);
                assert view != null;
                holder = new ViewHolder();
                holder.title = (TextView) view.findViewById(R.id.title);
                holder.handle = (TextView) view.findViewById(R.id.handle);
                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }

            final String string = mStrings.get(position);

            holder.title.setText(string);

            /**
             * ドラッグハンドルのタップでソートを開始します
             * onClickでは指を離すまでイベントが発火しないのでドラッグ出来ません
             */
           /* holder.handle.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                        startDrag(string);
                        return true;
                    }
                    return false;
                }
            });

            /**
             * ドラッグ行のハイライトです、力技ですね。
             */
         /*   if (mDragString != null && mDragString.equals(string)) {
                view.setBackgroundColor(Color.parseColor("#9933b5e5"));
            } else {
                view.setBackgroundColor(Color.TRANSPARENT);
            }

            return view;
        }
    }
*/

}