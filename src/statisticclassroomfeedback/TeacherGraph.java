package statisticclassroomfeedback;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GradientPaint;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;

public class TeacherGraph extends javax.swing.JFrame {
    Connection conn = null;
    PreparedStatement pre_comment = null;
    PreparedStatement pre_good = null;
    PreparedStatement pre_bad = null;
    ResultSet rs = null;

    public TeacherGraph() {
        initComponents();
        DefaultListModel listModelPositive = new DefaultListModel();    // เก็บ List Token ของ Comment ทั้งหมด ที่เชิงบวก
        DefaultListModel listModelNegative = new DefaultListModel();    // เก็บ List Token ของ Comment ทั้งหมด ที่เชิงลบ

        Sentiment sentiment = new Sentiment();              // ประกาศ object ของ Sentiment
        conn = MySqlConnect.ConnectDB();                    // เชื่อมต่อกับฐานข้อมูล
        String comments = "SELECT * From studentcomment";   // คำสั่ง SQL ที่ใช้ในการ query comment ทั้งหมด 
        ArrayList x = new ArrayList();                      // เก็บ Token(+) แต่ละตัวที่ได้จาก การทำSentiment
        ArrayList y = new ArrayList();                      // เก็บ Token(-) แต่ละตัวที่ได้จาก การทำSentiment
        String[] wordPos = new String[50];                  // String[] สำหรับเก็บ Token(+) แต่ละคำ                  
        int[] wordPosCount = new int[50];                   // เพื่อนับว่า มี Token(+) ที่เหมือนกันซ้ำกันกี่ครั้ง
        String[] wordNeg = new String[50];                  // String[] สำหรับเก็บ Token(+) แต่ละคำ  
        int[] wordNegCount = new int[50];                   // เพื่อนับว่า มี Token(+) ที่เหมือนกันซ้ำกันกี่ครั้ง
        boolean isStringExists;                             // ค่า boolean เพื่อทบสอบว่า เจอ Token ที่ซ้ำหรือไม่
   
        try{
            pre_comment=conn.prepareStatement(comments);
            rs =  pre_comment.executeQuery();
            int countPos,countNeg;
            int i=0,j=0;
            String temp;
            wordPos[0] = "";
            while(rs.next()){
                // ส่ง ceomment ทั้งหมดไปทำ Sentiment โดยจะ ส่งค่า ArrayList กลับมาให้ตัวแปร x และ y
                x = sentiment.doSentiment(rs.getString("comment"),"good");  
                y = sentiment.doSentiment(rs.getString("comment"),"bad");
                countPos = 0;       // นับจำนวน Token ที่เป็น บวก
                if(!x.isEmpty()){
                    while(countPos!=x.size()){ 
                        temp = x.get(countPos).toString();                  // ใช้ตรวจสอบว่า 
                        isStringExists = listModelPositive.contains(temp);  // เจอ คำที่ซ้ำหรือไม่
                        int tempIndex = listModelPositive.indexOf(temp);     
                        if(isStringExists){             // ถ้าเจอคำที่ซ้ำแล้ว
                            wordPosCount[tempIndex]++;      // ให้นับคำๆนั้นเพิ่ม แต่ไม่ต้องเพิ่มเข้าไปใน wordPos
                        }else{                          // ถ้าไม่เจอคำซ้ำ
                            wordPos[i] = temp;              // ให้เพิ่มคำเข้าไปใน wordPos[]
                            wordPosCount[i]++;              // แล้วนับคำเพิ่มอีก 1
                        }
                        listModelPositive.addElement(x.get(countPos));  // เก็บคำ ที่เป็น (+) ทุกคำรวมทั้งคำที่ซ้ำด้วย
                        countPos++;
                        i++;
                    }
                }
                countNeg = 0;       // นับจำนวน Token ที่เป็น ลบ
                if(!y.isEmpty()){
                    while(countNeg!=y.size()){
                        temp = y.get(countNeg).toString();                  // ใช้ตรวจสอบว่า 
                        isStringExists = listModelNegative.contains(temp);  // เจอ คำที่ซ้ำหรือไม่
                        int tempIndex = listModelNegative.indexOf(temp);
                        if(isStringExists){            // ถ้าเจอคำที่ซ้ำแล้ว
                            wordNegCount[tempIndex]++;      // ให้นับคำๆนั้นเพิ่ม แต่ไม่ต้องเพิ่มเข้าไปใน wordNeg[]
                        }else{                          // ถ้าไม่เจอคำซ้ำ
                            wordNeg[j] = temp;              // ให้เพิ่มคำเข้าไปใน wordNeg[]
                            wordNegCount[j]++;              // แล้วนับคำเพิ่มอีก 1
                        }
                        listModelNegative.addElement(y.get(countNeg));  // เก็บคำ ที่เป็น (-) ทุกคำรวมทั้งคำที่ซ้ำด้วย
                        countNeg++;
                        j++;
                    }
                }
            }    
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        System.out.println("Positive Word : "+listModelPositive);
        System.out.println("Nagative Word : "+listModelNegative);
        
        
        ///////////////// ส่วนต่อไปนี้เป็นการนำ wordNeg[] และ wordPos[] ไปแสดงเป็นกราฟ //////////////////////
        DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
        for(int i=0; i<wordNeg.length; i++){
            if(wordNeg[i]!=null){
                dataSet.setValue(wordNegCount[i],"1",wordNeg[i]);
            }
        }
        for(int i=0; i<wordPos.length; i++){
            if(wordPos[i]!=null){
                dataSet.setValue(wordPosCount[i],"2",wordPos[i]);
            }
        }
        
        JFreeChart chart = ChartFactory.createBarChart("", "", "Times", dataSet, PlotOrientation.VERTICAL, false, false, false);
        CategoryPlot catPlot = chart.getCategoryPlot();
        catPlot.setRangeGridlinePaint(Color.BLACK);
        catPlot.getDomainAxis().setCategoryLabelPositions(CategoryLabelPositions.UP_45);
        catPlot.setBackgroundPaint(Color.WHITE);

        GradientPaint gp0 = new GradientPaint(
            0.0f, 0.0f, new Color(255, 127, 39), 
            0.0f, 0.0f, new Color(255, 201, 14)
        );
        GradientPaint gp1 = new GradientPaint(
            0.0f, 0.0f, Color.green, 
            0.0f, 0.0f, new Color(181, 230, 29)
        );

        BarRenderer renderer = (BarRenderer) catPlot.getRenderer();
        renderer.setDrawBarOutline(false);
        renderer.setSeriesPaint(0, gp0);
        renderer.setSeriesPaint(1, gp1);
        
        ChartPanel chartPanel = new ChartPanel(chart);
        panel.removeAll();
        panel.add(chartPanel, BorderLayout.CENTER);
        panel.validate();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_list = new javax.swing.JLabel();
        btn_signOut = new javax.swing.JLabel();
        btn_graph = new javax.swing.JLabel();
        btn_sum = new javax.swing.JLabel();
        panel = new javax.swing.JPanel();
        result = new javax.swing.JLabel();
        background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(915, 630));
        setMinimumSize(new java.awt.Dimension(915, 630));
        setPreferredSize(new java.awt.Dimension(915, 630));
        getContentPane().setLayout(null);

        btn_list.setIcon(new javax.swing.ImageIcon(getClass().getResource("/statisticclassroomfeedback/imgs/teacher/btn/list-off.png"))); // NOI18N
        btn_list.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_listMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_listMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_listMouseExited(evt);
            }
        });
        getContentPane().add(btn_list);
        btn_list.setBounds(60, 40, 160, 50);

        btn_signOut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/statisticclassroomfeedback/imgs/btn_signOut1.png"))); // NOI18N
        btn_signOut.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_signOutMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_signOutMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_signOutMouseExited(evt);
            }
        });
        getContentPane().add(btn_signOut);
        btn_signOut.setBounds(770, 0, 120, 30);

        btn_graph.setIcon(new javax.swing.ImageIcon(getClass().getResource("/statisticclassroomfeedback/imgs/teacher/btn/graph-on.png"))); // NOI18N
        btn_graph.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_graphMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_graphMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_graphMouseExited(evt);
            }
        });
        getContentPane().add(btn_graph);
        btn_graph.setBounds(230, 40, 170, 50);

        btn_sum.setIcon(new javax.swing.ImageIcon(getClass().getResource("/statisticclassroomfeedback/imgs/teacher/btn/sum-off.png"))); // NOI18N
        btn_sum.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_sumMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_sumMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_sumMouseExited(evt);
            }
        });
        getContentPane().add(btn_sum);
        btn_sum.setBounds(410, 40, 130, 50);

        panel.add(result);

        getContentPane().add(panel);
        panel.setBounds(0, 120, 890, 490);

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/statisticclassroomfeedback/imgs/teacher/teach_basicBackground.png"))); // NOI18N
        getContentPane().add(background);
        background.setBounds(0, 0, 900, 600);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_listMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_listMouseClicked
        new TeacherList().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btn_listMouseClicked

    private void btn_listMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_listMouseEntered
        btn_list.setIcon(new javax.swing.ImageIcon(getClass().getResource("/statisticclassroomfeedback/imgs/teacher/btn/list-on.png")));
    }//GEN-LAST:event_btn_listMouseEntered

    private void btn_listMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_listMouseExited
        btn_list.setIcon(new javax.swing.ImageIcon(getClass().getResource("/statisticclassroomfeedback/imgs/teacher/btn/list-off.png")));
    }//GEN-LAST:event_btn_listMouseExited

    private void btn_graphMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_graphMouseClicked
        //
    }//GEN-LAST:event_btn_graphMouseClicked

    private void btn_graphMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_graphMouseEntered
        //btn_graph.setIcon(new javax.swing.ImageIcon(getClass().getResource("/statisticclassroomfeedback/imgs/teacher/btn/graph-on.png")));
    }//GEN-LAST:event_btn_graphMouseEntered

    private void btn_graphMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_graphMouseExited
        //btn_graph.setIcon(new javax.swing.ImageIcon(getClass().getResource("/statisticclassroomfeedback/imgs/teacher/btn/graph-off.png")));
    }//GEN-LAST:event_btn_graphMouseExited

    private void btn_sumMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_sumMouseClicked
        new TeacherSum().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btn_sumMouseClicked

    private void btn_sumMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_sumMouseEntered
       btn_sum.setIcon(new javax.swing.ImageIcon(getClass().getResource("/statisticclassroomfeedback/imgs/teacher/btn/sum-on.png")));
    }//GEN-LAST:event_btn_sumMouseEntered

    private void btn_sumMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_sumMouseExited
       btn_sum.setIcon(new javax.swing.ImageIcon(getClass().getResource("/statisticclassroomfeedback/imgs/teacher/btn/sum-off.png")));
    }//GEN-LAST:event_btn_sumMouseExited

    private void btn_signOutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_signOutMouseClicked
        Login login = new Login();
        login.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btn_signOutMouseClicked

    private void btn_signOutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_signOutMouseEntered
        btn_signOut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/statisticclassroomfeedback/imgs/btn_signOut2.png")));
    }//GEN-LAST:event_btn_signOutMouseEntered

    private void btn_signOutMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_signOutMouseExited
        btn_signOut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/statisticclassroomfeedback/imgs/btn_signOut1.png")));
    }//GEN-LAST:event_btn_signOutMouseExited

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TeacherGraph.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TeacherGraph.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TeacherGraph.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TeacherGraph.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TeacherGraph().setVisible(true);
               
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel background;
    private javax.swing.JLabel btn_graph;
    private javax.swing.JLabel btn_list;
    private javax.swing.JLabel btn_signOut;
    private javax.swing.JLabel btn_sum;
    private javax.swing.JPanel panel;
    private javax.swing.JLabel result;
    // End of variables declaration//GEN-END:variables
}
