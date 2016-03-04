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
        ELEMENT_LIST.add(new Element(R.drawable.promoter, "Promoter A", "Promoter"));
        ELEMENT_LIST.add(new Element(R.drawable.arrow, "C", "CDS"));
        ELEMENT_LIST.add(new Element(R.drawable.terminater, "C", "Terminator"));
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

}