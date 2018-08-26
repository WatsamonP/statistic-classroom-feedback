package statisticclassroomfeedback;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.*;

public class Student extends javax.swing.JFrame {
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    String user;
    public Student(String user) {
        initComponents();
        this.user = user;
        System.out.println(user);
        username.setText(user);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_signOut = new javax.swing.JLabel();
        username = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        studentComment = new javax.swing.JTextArea();
        btn_clear = new javax.swing.JLabel();
        btn_post = new javax.swing.JLabel();
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

        username.setFont(new java.awt.Font("Cordia New", 1, 26)); // NOI18N
        username.setForeground(new java.awt.Color(255, 255, 255));
        username.setText("username");
        getContentPane().add(username);
        username.setBounds(190, 150, 130, 40);

        studentComment.setColumns(20);
        studentComment.setRows(5);
        jScrollPane1.setViewportView(studentComment);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(150, 200, 600, 190);

        btn_clear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/statisticclassroomfeedback/imgs/btn/clear1.png"))); // NOI18N
        btn_clear.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_clearMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_clearMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_clearMouseExited(evt);
            }
        });
        getContentPane().add(btn_clear);
        btn_clear.setBounds(560, 430, 90, 30);

        btn_post.setIcon(new javax.swing.ImageIcon(getClass().getResource("/statisticclassroomfeedback/imgs/btn/post1.png"))); // NOI18N
        btn_post.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_postMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_postMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_postMouseExited(evt);
            }
        });
        getContentPane().add(btn_post);
        btn_post.setBounds(670, 430, 90, 30);

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/statisticclassroomfeedback/imgs/student/background.png"))); // NOI18N
        getContentPane().add(background);
        background.setBounds(0, 0, 900, 600);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_postMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_postMouseClicked
        conn = MySqlConnect.ConnectDB();
        // insert Comment
        String sql = "INSERT INTO studentcomment(std_id, comment) VALUES (?, ?)";
        String std_id = this.user;
        String comment = studentComment.getText();
        try{
            pst=conn.prepareStatement(sql);
            pst.setString(1, std_id);
            pst.setString(2, comment);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null,"บันทึก Comment");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_btn_postMouseClicked

    private void btn_postMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_postMouseEntered
        btn_post.setIcon(new javax.swing.ImageIcon(getClass().getResource("/statisticclassroomfeedback/imgs/btn/post2.png")));
    }//GEN-LAST:event_btn_postMouseEntered

    private void btn_postMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_postMouseExited
        btn_post.setIcon(new javax.swing.ImageIcon(getClass().getResource("/statisticclassroomfeedback/imgs/btn/post1.png")));
    }//GEN-LAST:event_btn_postMouseExited

    private void btn_clearMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_clearMouseClicked
        studentComment.setText("");
    }//GEN-LAST:event_btn_clearMouseClicked

    private void btn_clearMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_clearMouseEntered
        btn_clear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/statisticclassroomfeedback/imgs/btn/clear2.png")));
    }//GEN-LAST:event_btn_clearMouseEntered

    private void btn_clearMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_clearMouseExited
        btn_clear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/statisticclassroomfeedback/imgs/btn/clear1.png")));
    }//GEN-LAST:event_btn_clearMouseExited

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
        java.awt.EventQueue.invokeLater(new Runnable() {
            String user;
            public void run() {
                new Student(user).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel background;
    private javax.swing.JLabel btn_clear;
    private javax.swing.JLabel btn_post;
    private javax.swing.JLabel btn_signOut;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea studentComment;
    private javax.swing.JLabel username;
    // End of variables declaration//GEN-END:variables
}
