package statisticclassroomfeedback;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Locale;
import java.util.*;
import java.lang.*;
import static jdk.nashorn.tools.ShellFunctions.input;

public class Tokenization {
    private static  BreakIterator b1 = BreakIterator.getWordInstance(new Locale("th"));
    
    private static ArrayList Split(String input,BreakIterator b1){
        ArrayList arrList = new ArrayList();
        int first=b1.first();
        String temp = null;
        ArrayList myArray = new ArrayList();
        myArray.add("ไม่");
        myArray.add("ข้อสอบ");
        myArray.add("น่า");
        myArray.add("ไม่ค่อย");
        myArray.add("งาน");
        myArray.add("เสียง");
        myArray.add("จด");
        myArray.add("ปล่อย");
        myArray.add("สอน");
        
        for (int last = b1.next(); last !=BreakIterator.DONE ; first=last,last=b1.next()) {
            String x = input.substring(first,last);
            boolean isStringExists = myArray.contains(x);
            if(isStringExists){
                temp = x;
                continue;
            }
            if(temp != null){
                arrList.add(temp+x);
                temp = null;
                continue;
            }
            if(!" ".equals(x)){
                arrList.add(x);
            }
        }
        return arrList;
    }
    
    public static ArrayList input(String input) {
       b1.setText(input);  
       return Split(input, b1);
    }
}
