package statisticclassroomfeedback;
import java.sql.*;
import javax.swing.*;
public class Login extends javax.swing.JFrame {
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    public Login() {
        initComponents();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        usernameField = new javax.swing.JTextField();
        passwordField = new javax.swing.JPasswordField();
        btn_login = new javax.swing.JLabel();
        btn_signup = new javax.swing.JLabel();
        background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(315, 500));
        setMinimumSize(new java.awt.Dimension(315, 500));
        setPreferredSize(new java.awt.Dimension(315, 500));
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

        btn_login.setIcon(new javax.swing.ImageIcon(getClass().getResource("/statisticclassroomfeedback/imgs/btn_login1.png"))); // NOI18N
        btn_login.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_loginMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_loginMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_loginMouseExited(evt);
            }
        });
        getContentPane().add(btn_login);
        btn_login.setBounds(30, 340, 240, 40);

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
        btn_signup.setBounds(30, 390, 240, 40);

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/statisticclassroomfeedback/imgs/login-template.png"))); // NOI18N
        getContentPane().add(background);
        background.setBounds(0, 0, 300, 500);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_loginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_loginMouseClicked
        conn = MySqlConnect.ConnectDB();
        String qry = "SELECT * From user where username=? and password=?";
        try{
            pst=conn.prepareStatement(qry);
            pst.setString(1,usernameField.getText());
            pst.setString(2,passwordField.getText());
            rs =  pst.executeQuery();
            if(rs.next()){
                String type = rs.getString("type");
                String user = rs.getString("username");
                JOptionPane.showMessageDialog(null, user + ", sign in as "+ type);
                if("teacher".equals(type)){
                    new TeacherList().setVisible(true);
                    this.setVisible(false);
                }else if("student".equals(type)){
                    new Student(usernameField.getText()).setVisible(true);
                    this.setVisible(false);
                }
            }else{
                JOptionPane.showMessageDialog(null, "Invalid username or password","Access Denied",JOptionPane.ERROR_MESSAGE);
            }        
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_btn_loginMouseClicked

    private void btn_loginMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_loginMouseEntered
        btn_login.setIcon(new javax.swing.ImageIcon(getClass().getResource("/statisticclassroomfeedback/imgs/btn_login2.png")));
    }//GEN-LAST:event_btn_loginMouseEntered

    private void btn_loginMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_loginMouseExited
        btn_login.setIcon(new javax.swing.ImageIcon(getClass().getResource("/statisticclassroomfeedback/imgs/btn_login1.png")));
    }//GEN-LAST:event_btn_loginMouseExited

    private void usernameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameFieldActionPerformed
        
    }//GEN-LAST:event_usernameFieldActionPerformed

    private void btn_signupMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_signupMouseClicked
        SignUp signup = new SignUp();
        signup.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btn_signupMouseClicked

    private void btn_signupMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_signupMouseEntered
        btn_signup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/statisticclassroomfeedback/imgs/btn_signUp2.png")));
    }//GEN-LAST:event_btn_signupMouseEntered

    private void btn_signupMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_signupMouseExited
        btn_signup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/statisticclassroomfeedback/imgs/btn_signUp1.png")));
    }//GEN-LAST:event_btn_signupMouseExited

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            String user;
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel background;
    private javax.swing.JLabel btn_login;
    private javax.swing.JLabel btn_signup;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JTextField usernameField;
    // End of variables declaration//GEN-END:variables
}
