package statisticclassroomfeedback;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Sentiment {
    public static ArrayList doSentiment(String comment, String find){
        Connection conn = null;
        PreparedStatement pre_good = null;
        ResultSet rs_good = null;
        conn = MySqlConnect.ConnectDB();
        
        String good = "SELECT * From token_good";
        String bad = "SELECT * From token_bad";
        String dataSet = null;
        if(find.equals("good")){
            dataSet = good;
        }else if(find.equals("bad")){
            dataSet = bad;
        }
        
        Tokenization token = new Tokenization();
        ArrayList arrList = new ArrayList();
        ArrayList result = new ArrayList();
        arrList = token.input(comment);
        int count=0;
        
        try{
            pre_good = conn.prepareStatement(dataSet);
            rs_good =  pre_good.executeQuery();
            while(rs_good.next()){
                for(int i=0; i<arrList.size();i++){
                    String x = arrList.get(i).toString();
                    String y = rs_good.getString("text");
                    if(x.equals(y)){
                        count++;
                        result.add(y);
                    }
                }
            }     
        }catch(Exception e){
            System.out.println(e);
        }        
        return result;
    }
}
