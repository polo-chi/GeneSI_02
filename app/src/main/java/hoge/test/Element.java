package hoge.test;

/**
 * MainActivityとかでListViewを入れ子にするときに使う
 */

import android.widget.Checkable;
import android.widget.RadioButton;

public class Element implements Checkable{

    private final String nameComponent;
    private final String nameElement;
    private final int imageID;

    private RadioButton mRadioButton;

   public Element (int image, String component, String element)
    {
        this.nameComponent = component;
        this.nameElement = element;
        this.imageID = image;
    }

    public String getNameComponent()
    {
        return  nameComponent;
    }

    public String getNameElement()
    {
        return nameElement;
    }

    public int getImageID()
    {
        return imageID;
    }

    @Override
    public boolean isChecked()
    {
        return mRadioButton.isChecked();
    }

    @Override
    public void setChecked(boolean checked)
    {
        if (isChecked() == true) {
            mRadioButton.setChecked(false);
        }
        else
        {
            mRadioButton.setChecked(true);
        }
    }

    @Override
    public void toggle()
    {

    }
}
