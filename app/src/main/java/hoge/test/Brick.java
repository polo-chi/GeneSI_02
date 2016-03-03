package hoge.test;

/**
 * Created by Chihiro Iijima on 2016/03/03.
 */

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Brick {
    private static String defaultText;
    private static String planeText;
    private static StringBuilder upperPlaneText;
    private static String tidyUpText;
    private static String complementText;

    public void setPlaneText(String defText){
        //defaultText = new String();
        defaultText = defText;
        Pattern deleteCharPattern = Pattern.compile("[^acgt]");
        Matcher matcher = deleteCharPattern.matcher(defaultText);
        planeText = matcher.replaceAll("");

    }

    public String getPlaneText(){
        return planeText;
    }

    public void setTidyUpText(String plnText){
        upperPlaneText = new StringBuilder((planeText.toUpperCase()));
        for(int i = 5; i < upperPlaneText.length(); i += 6){
            upperPlaneText = upperPlaneText.insert(i," ");
        }
        tidyUpText = upperPlaneText.toString();
    }

    public String getTidyUpText(){
        return tidyUpText;
    }

    public void setComplementText(String tuText){
        StringBuilder sbTidyUpText = new StringBuilder(tuText);
        String reverseTUText = sbTidyUpText.reverse().toString();

        String replaceReverseTUText = reverseTUText.replace("A", "t");
        replaceReverseTUText =  replaceReverseTUText.replace("T", "a");
        replaceReverseTUText =  replaceReverseTUText.replace("C", "g");
        replaceReverseTUText =  replaceReverseTUText.replace("G", "c");

        complementText =  replaceReverseTUText.toUpperCase();
    }

    public static String getComplementText(){
        return complementText;
    }

}
