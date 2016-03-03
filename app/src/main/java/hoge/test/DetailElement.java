package hoge.test;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
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
{
    private static final String KEY_NAME_ELEMENT = "name_element";
    private static final String KEY_IMAGE_ID = "image_id";

    protected android.widget.Button Button;
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

    static {
        ELEMENT_LIST.add(new Element(R.drawable.promoter, "Promoter A", "Promoter"));
        ELEMENT_LIST.add(new Element(R.drawable.arrow, "C", "CDS"));
        ELEMENT_LIST.add(new Element(R.drawable.terminater, "C", "Terminator"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_element);

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

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.detail_element, container, false);
        return rootView;
    }
}