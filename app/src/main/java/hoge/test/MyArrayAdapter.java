package hoge.test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Chihiro Iijima on 2016/03/04.
 */
public class MyArrayAdapter extends ArrayList<Element> {
   /* private ArrayList<String> mStrings = new ArrayList<String>();
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
        /*holder.handle.setOnTouchListener(new View.OnTouchListener() {
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
      /*  if (mDragString != null && mDragString.equals(string)) {
            view.setBackgroundColor(Color.parseColor("#9933b5e5"));
        } else {
            view.setBackgroundColor(Color.TRANSPARENT);
        }

        return view;
    }*/
}
