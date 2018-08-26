package statisticclassroomfeedback;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class TeacherSum extends javax.swing.JFrame {
    Connection conn = null;
    PreparedStatement pre_comment = null;
    PreparedStatement pre_good = null;
    PreparedStatement pre_bad = null;
    ResultSet rs = null;
    public TeacherSum() {
        initComponents();
        
        DefaultListModel listModelPositive = new DefaultListModel();
        DefaultListModel listModelNegative = new DefaultListModel();

        Sentiment good = new Sentiment();
        Sentiment bad = new Sentiment();
        conn = MySqlConnect.ConnectDB();
        String comments = "SELECT * From studentcomment";
        ArrayList x = new ArrayList();
        ArrayList y = new ArrayList();
        String[] wordPos = new String[50];
        int[] wordPosCount = new int[50];
        String[] wordNeg = new String[50];
        int[] wordNegCount = new int[50];
        boolean isStringExists;
   
        try{
            pre_comment=conn.prepareStatement(comments);
            rs =  pre_comment.executeQuery();
            int countPos,countNeg;
            int i=0,j=0;
            String temp;
            wordPos[0] = "";
            while(rs.next()){
                x = good.doSentiment(rs.getString("comment"),"good");
                y = bad.doSentiment(rs.getString("comment"),"bad");
                countPos = 0;
                if(!x.isEmpty()){
                    while(countPos!=x.size()){
                        temp = x.get(countPos).toString();
                        isStringExists = listModelPositive.contains(temp);
                        int tempIndex = listModelPositive.indexOf(temp);
                        if(isStringExists){
                            wordPosCount[tempIndex]++;
                        }else{
                            wordPos[i] = temp;
                            wordPosCount[i]++;
                        }
                        listModelPositive.addElement(x.get(countPos));
                        countPos++;
                        i++;
                    }
                }
                countNeg = 0;
                if(!y.isEmpty()){
                    while(countNeg!=y.size()){
                        temp = y.get(countNeg).toString();
                        isStringExists = listModelNegative.contains(temp);
                        int tempIndex = listModelNegative.indexOf(temp);
                        if(isStringExists){
                            wordNegCount[tempIndex]++;
                        }else{
                            wordNeg[j] = temp;
                            wordNegCount[j]++;
                        }
                        listModelNegative.addElement(y.get(countNeg));
                        countNeg++;
                        j++;
                    }
                }
            }    
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        
        int countAllword = 0;
        int countPosword = 0;
        int countNegword = 0;
    for(int i=0;i<wordPosCount.length;i++){
            countAllword = countAllword + wordPosCount[i];
            countPosword = countPosword + wordPosCount[i];
        }
        for(int i=0;i<wordNegCount.length;i++){
            countAllword = countAllword + wordNegCount[i];
            countNegword = countNegword + wordNegCount[i];
        }
        System.out.println("จำนวนคำทั้งหมด "+countAllword);
        System.out.println("จำนวนคำ + "+countPosword);
        System.out.println("จำนวนคำ - "+countNegword);

        float PosPercentage = (float)countPosword/countAllword * 100;
        float NegPercentage = (float)countNegword/countAllword * 100;
        System.out.println("Positive% = " + PosPercentage);
        System.out.println("Negative% = " + NegPercentage);
        
        posPercentage.setText(String.format("%.02f", PosPercentage)+" %");
        negPercentage.setText(String.format("%.02f", NegPercentage)+" %");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_signOut = new javax.swing.JLabel();
        btn_list = new javax.swing.JLabel();
        btn_graph = new javax.swing.JLabel();
        btn_sum = new javax.swing.JLabel();
        posPercentage = new javax.swing.JLabel();
        negPercentage = new javax.swing.JLabel();
        img_smile = new javax.swing.JLabel();
        img_sad = new javax.swing.JLabel();
        background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(915, 630));
        setMinimumSize(new java.awt.Dimension(915, 630));
        setPreferredSize(new java.awt.Dimension(915, 630));
        getContentPane().setLayout(null);

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

        btn_graph.setIcon(new javax.swing.ImageIcon(getClass().getResource("/statisticclassroomfeedback/imgs/teacher/btn/graph-off.png"))); // NOI18N
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

        btn_sum.setIcon(new javax.swing.ImageIcon(getClass().getResource("/statisticclassroomfeedback/imgs/teacher/btn/sum-on.png"))); // NOI18N
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

        posPercentage.setFont(new java.awt.Font("Cordia New", 1, 48)); // NOI18N
        posPercentage.setForeground(new java.awt.Color(0, 153, 0));
        posPercentage.setText("XX.X %");
        getContentPane().add(posPercentage);
        posPercentage.setBounds(200, 380, 160, 60);

        negPercentage.setFont(new java.awt.Font("Cordia New", 1, 48)); // NOI18N
        negPercentage.setForeground(new java.awt.Color(204, 0, 0));
        negPercentage.setText("XX.X %");
        getContentPane().add(negPercentage);
        negPercentage.setBounds(560, 360, 170, 90);

        img_smile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/statisticclassroomfeedback/imgs/teacher/good.png"))); // NOI18N
        getContentPane().add(img_smile);
        img_smile.setBounds(170, 160, 180, 180);

        img_sad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/statisticclassroomfeedback/imgs/teacher/bad.png"))); // NOI18N
        getContentPane().add(img_sad);
        img_sad.setBounds(530, 160, 180, 180);

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
        new TeacherGraph().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btn_graphMouseClicked

    private void btn_graphMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_graphMouseEntered
        btn_graph.setIcon(new javax.swing.ImageIcon(getClass().getResource("/statisticclassroomfeedback/imgs/teacher/btn/graph-on.png")));
    }//GEN-LAST:event_btn_graphMouseEntered

    private void btn_graphMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_graphMouseExited
        btn_graph.setIcon(new javax.swing.ImageIcon(getClass().getResource("/statisticclassroomfeedback/imgs/teacher/btn/graph-off.png")));
    }//GEN-LAST:event_btn_graphMouseExited

    private void btn_sumMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_sumMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_sumMouseClicked

    private void btn_sumMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_sumMouseEntered
       //btn_sum.setIcon(new javax.swing.ImageIcon(getClass().getResource("/statisticclassroomfeedback/imgs/teacher/btn/sum-on.png")));
    }//GEN-LAST:event_btn_sumMouseEntered

    private void btn_sumMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_sumMouseExited
       //btn_sum.setIcon(new javax.swing.ImageIcon(getClass().getResource("/statisticclassroomfeedback/imgs/teacher/btn/sum-off.png")));
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
            java.util.logging.Logger.getLogger(TeacherSum.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TeacherSum.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TeacherSum.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TeacherSum.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TeacherSum().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel background;
    private javax.swing.JLabel btn_graph;
    private javax.swing.JLabel btn_list;
    private javax.swing.JLabel btn_signOut;
    private javax.swing.JLabel btn_sum;
    private javax.swing.JLabel img_sad;
    private javax.swing.JLabel img_smile;
    private javax.swing.JLabel negPercentage;
    private javax.swing.JLabel posPercentage;
    // End of variables declaration//GEN-END:variables
}
