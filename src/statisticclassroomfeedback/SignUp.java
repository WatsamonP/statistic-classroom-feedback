package statisticclassroomfeedback;
import java.sql.*;
import javax.swing.*;
public class SignUp extends javax.swing.JFrame {
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    String radio = "";
    public SignUp() {
        initComponents();
        ButtonGroup bgroup = new ButtonGroup();
        bgroup.add(RadioButton_std);
        bgroup.add(RadioButton_teach);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        usernameField = new javax.swing.JTextField();
        passwordField = new javax.swing.JPasswordField();
        RadioButton_teach = new javax.swing.JRadioButton();
        RadioButton_std = new javax.swing.JRadioButton();
        btn_signup = new javax.swing.JLabel();
        background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(315, 500));
        getContentPane().setLayout(null);

        usernameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameFieldActionPerformed(evt);
            }
        });
        getContentPane().add(usernameField);
        usernameField.setBounds(20, 180, 260, 30);
        getContentPane().add(passwordField);
        passwordField.setBounds(20, 280, 260, 30);

        RadioButton_teach.setText("TEACHER");
        RadioButton_teach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RadioButton_teachMouseClicked(evt);
            }
        });
        getContentPane().add(RadioButton_teach);
        RadioButton_teach.setBounds(40, 340, 100, 23);

        RadioButton_std.setText("STUDENT");
        RadioButton_std.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RadioButton_stdMouseClicked(evt);
            }
        });
        getContentPane().add(RadioButton_std);
        RadioButton_std.setBounds(170, 340, 93, 23);

        btn_signup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/statisticclassroomfeedback/imgs/btn_signUp1.png"))); // NOI18N
        btn_signup.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_signupMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_signupMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_signupMouseExited(evt);
            }
        });
        getContentPane().add(btn_signup);
        btn_signup.setBounds(30, 380, 240, 40);

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/statisticclassroomfeedback/imgs/signup-template.png"))); // NOI18N
        getContentPane().add(background);
        background.setBounds(0, 0, 300, 500);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_signupMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_signupMouseClicked
        conn = MySqlConnect.ConnectDB();
        String sql = "INSERT INTO user(username, password, type ) VALUES (?, ?, ?)";
        String username = usernameField.getText();
        String password = passwordField.getText();
        try{
            pst=conn.prepareStatement(sql);
            pst.setString(1, username);
            pst.setString(2, password);
            pst.setString(3, this.radio);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null,"สมัครสมาชิกสำเร็จ");
            Login login = new Login();
            login.setVisible(true);
            this.setVisible(false);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_btn_signupMouseClicked

    private void btn_signupMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_signupMouseEntered
        btn_signup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/statisticclassroomfeedback/imgs/btn_signUp2.png")));
    }//GEN-LAST:event_btn_signupMouseEntered

    private void btn_signupMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_signupMouseExited
        btn_signup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/statisticclassroomfeedback/imgs/btn_signUp1.png")));
    }//GEN-LAST:event_btn_signupMouseExited

    private void usernameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameFieldActionPerformed
        
    }//GEN-LAST:event_usernameFieldActionPerformed

    private void RadioButton_teachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RadioButton_teachMouseClicked
        this.radio = "teacher";
    }//GEN-LAST:event_RadioButton_teachMouseClicked

    private void RadioButton_stdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RadioButton_stdMouseClicked
        this.radio = "student";
    }//GEN-LAST:event_RadioButton_stdMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SignUp().setVisible(true);
                String radio=null;
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton RadioButton_std;
    private javax.swing.JRadioButton RadioButton_teach;
    private javax.swing.JLabel background;
    private javax.swing.JLabel btn_signup;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JTextField usernameField;
    // End of variables declaration//GEN-END:variables
}
