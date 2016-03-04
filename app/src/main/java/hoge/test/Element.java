package hoge.test;

/**
 * MainActivityとかでListViewを入れ子にするときに使う
 */

public class Element {

    private final String nameElement;
    private int imageID;
    private final int position;
    private final String property;
    private final String memo;

    public Element (String element, String pro, String note, int pos)
    {
        this.nameElement = element;
        this.property = pro;
        this.memo = note;
        this.position = pos;
    }

    public String getNameElement()
    {
        return nameElement;
    }

    public int getImageID()
    {
        if(getProperty() == "promoter")
        {
            imageID = R.drawable.promoter;
        }
        else if(getProperty() == "cds")
        {
            imageID = R.drawable.arrow;
        }
        if(getProperty() == "terminator")
        {
            imageID = R.drawable.terminater;
        }

        return imageID;
    }
    public String getProperty()
    {
        return property;
    }

    public  String getMemo()
    {
        return memo;
    }

    public  int getPosition()
    {
        return position;
    }

}
