package statisticclassroomfeedback;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class TeacherList extends javax.swing.JFrame {
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    DefaultListModel commentModel = new DefaultListModel();
    DefaultListModel c_indexModel = new DefaultListModel();
    
    public TeacherList() {
        initComponents();
        conn = MySqlConnect.ConnectDB();
        // query Commments
        String comments = "SELECT * From studentcomment";
        int rowcount = 1;
        try{
            pst=conn.prepareStatement(comments);
            rs =  pst.executeQuery();
            while(rs.next()){
               commentModel.addElement(rowcount+" :  "+ rs.getString("comment"));
               c_indexModel.addElement(rs.getString("comment_id"));

               rowcount++;
            }     
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        commentList.setModel(commentModel);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_signOut = new javax.swing.JLabel();
        btn_list = new javax.swing.JLabel();
        btn_graph = new javax.swing.JLabel();
        btn_sum = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        commentList = new javax.swing.JList<>();
        delete = new javax.swing.JLabel();
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

        btn_list.setIcon(new javax.swing.ImageIcon(getClass().getResource("/statisticclassroomfeedback/imgs/teacher/btn/list-on.png"))); // NOI18N
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

        commentList.setFont(new java.awt.Font("Cordia New", 0, 18)); // NOI18N
        commentList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(commentList);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(70, 180, 760, 330);

        delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/statisticclassroomfeedback/imgs/btn/del1.png"))); // NOI18N
        delete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deleteMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                deleteMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                deleteMouseExited(evt);
            }
        });
        getContentPane().add(delete);
        delete.setBounds(760, 540, 90, 30);

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/statisticclassroomfeedback/imgs/teacher/teach_listBg.png"))); // NOI18N
        getContentPane().add(background);
        background.setBounds(0, 0, 900, 600);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_listMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_listMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_listMouseClicked

    private void btn_listMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_listMouseEntered
        //btn_list.setIcon(new javax.swing.ImageIcon(getClass().getResource("/statisticclassroomfeedback/imgs/teacher/btn/list-on.png")));
    }//GEN-LAST:event_btn_listMouseEntered

    private void btn_listMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_listMouseExited
        //btn_list.setIcon(new javax.swing.ImageIcon(getClass().getResource("/statisticclassroomfeedback/imgs/teacher/btn/list-off.png")));
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
        new TeacherSum().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btn_sumMouseClicked

    private void btn_sumMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_sumMouseEntered
       btn_sum.setIcon(new javax.swing.ImageIcon(getClass().getResource("/statisticclassroomfeedback/imgs/teacher/btn/sum-on.png")));
    }//GEN-LAST:event_btn_sumMouseEntered

    private void btn_sumMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_sumMouseExited
       btn_sum.setIcon(new javax.swing.ImageIcon(getClass().getResource("/statisticclassroomfeedback/imgs/teacher/btn/sum-off.png")));
    }//GEN-LAST:event_btn_sumMouseExited

    private void deleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteMouseClicked
        int i = commentList.getSelectedIndex();
        int index = Integer.parseInt((c_indexModel.getElementAt(i).toString()));
        conn = MySqlConnect.ConnectDB();
        String x = String.valueOf(index);
 
        String delComment = "DELETE FROM studentcomment WHERE comment_id=?";
        try{
            pst=conn.prepareStatement(delComment);
            pst.setString(1,x);
            pst.execute();
            commentModel.removeElement(i); // ลบ
            c_indexModel.removeElement(i); // ลบ
            commentModel.setElementAt("", i);     // update List
            JOptionPane.showMessageDialog(null,"ลบสำเร็จ");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_deleteMouseClicked

    private void deleteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteMouseEntered
        delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/statisticclassroomfeedback/imgs/btn/del2.png")));
    }//GEN-LAST:event_deleteMouseEntered

    private void deleteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteMouseExited
        delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/statisticclassroomfeedback/imgs/btn/del1.png")));
    }//GEN-LAST:event_deleteMouseExited

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
            java.util.logging.Logger.getLogger(TeacherList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TeacherList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TeacherList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TeacherList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TeacherList().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel background;
    private javax.swing.JLabel btn_graph;
    private javax.swing.JLabel btn_list;
    private javax.swing.JLabel btn_signOut;
    private javax.swing.JLabel btn_sum;
    private javax.swing.JList<String> commentList;
    private javax.swing.JLabel delete;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
