/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package jframe;

// Import statements go here
import com.mysql.cj.protocol.Resultset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.ResultSet;

// Password Hashing
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Admin
 */
public class SignupPage extends javax.swing.JFrame {

    /**
     * Creates new form SignupPage
     */
    public SignupPage() {
        initComponents();
    }
    
    // method to hash a password using SHA-256
    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes()); // digest() method compute the hash of the input
            StringBuilder sb = new StringBuilder();
            for(byte b : hashedBytes){
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
            
        }catch(NoSuchAlgorithmException e) {
            JOptionPane.showMessageDialog(this, "Error occurred while hashing password");
            e.printStackTrace();
            return null;
        }
    }
    
    // method to insert values into users table
    public void insertSignupDetails() {
    String name = txt_username.getText();
    String pwd = txt_password.getText();
    String email = txt_email.getText();
    String contact = txt_contact.getText();
    
    // Hash the password
    String hashedpwd = hashPassword(pwd);

    String sql = "INSERT INTO users(name, password, email, contact) VALUES (?, ?, ?, ?)";
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement pst = conn.prepareStatement(sql)) {

        // Set parameters for the prepared statement
        pst.setString(1, name);
        pst.setString(2, hashedpwd);
        pst.setString(3, email);
        pst.setString(4, contact);

        // Execute the update
        int updatedRowCount = pst.executeUpdate();

        if (updatedRowCount > 0) {
            JOptionPane.showMessageDialog(this, "Record inserted successfully.");
            LoginPage login = new LoginPage();
            login.setVisible(true);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Record insertion failed!");
        }

    } catch (SQLException e) {
        String errorMessage = e.getMessage();
        if (errorMessage.contains("email")) {
            JOptionPane.showMessageDialog(this, "Email already exists. Please use a different one.");
        } else if (errorMessage.contains("contact")) {
            JOptionPane.showMessageDialog(this, "Contact number already exists. Please use a different one.");
        } else {
            JOptionPane.showMessageDialog(this, "Error occurred: " + errorMessage);
        }
        
    }
}

//    public void insertSignupDetails(){
//        String name = txt_username.getText();
//        String pwd = txt_password.getText();
//        String email = txt_email.getText();
//        String contact = txt_contact.getText();
//        
//        try{
//            Connection conn = DBConnection.getConnection();
//            
//            // why can't we just write name, pwd, email, contact in the ? placeholder. Because this will lead to SQL injection attack.
//            String sql = "insert into users(name, password, email, contact) values (?,?,?,?)";
//            PreparedStatement pst = conn.prepareStatement(sql);
//            
//            pst.setString(1, name);
//            pst.setString(2, pwd);
//            pst.setString(3, email);
//            pst.setString(4, contact);
//            
//            // as it is a non-select query (non-select queries are insert, update, delete) we use executeUpdate() method which also returns the number of affected rows which can be a valuable resource
//            // for select query like select * from users we use executeQuery() which will return a result set
//            int updatedRowCount = pst.executeUpdate();
//            
//            if(updatedRowCount > 0) {
//                JOptionPane.showMessageDialog(this, "Record inserted Successfully.");
//                LoginPage login = new LoginPage();
//                login.setVisible(true);
//                this.dispose();
//            }else{
//                JOptionPane.showMessageDialog(this, "Record insertion Failed!");
//            }
//        } catch(Exception e){
//            e.printStackTrace();
//        }
//    }
    
    // validation
    public boolean validateSignup() {
        String name = txt_username.getText();
        String pwd = txt_password.getText();
        String email = txt_email.getText();
        String contact = txt_contact.getText();
        
        if(name.equals("")){
            JOptionPane.showMessageDialog(this, "Please insert your name");
            return false;
        }
        if(pwd.equals("")){
            JOptionPane.showMessageDialog(this, "Please insert your password");
            return false;
        }
        // validate email correctly e.g. you can not enter abc@com
        if(email.equals("") || !email.matches("^.+@.+\\..+$")){
            JOptionPane.showMessageDialog(this, "Please insert your valid email");
            return false;
        }
        if(contact.equals("")){
            JOptionPane.showMessageDialog(this, "Please insert your phone No.");
            return false;
        }
        return true;
    }
    
    //check duplicate users w.r.t to username
    public boolean checkDuplicateUsers() {
        String name = txt_username.getText();
        
        boolean isExist = false;
        
        try{
            Connection conn = DBConnection.getConnection();
            PreparedStatement pst = conn.prepareStatement("Select * from users where name = ?");
            
            pst.setString(1, name);
            ResultSet rs = pst.executeQuery();
            
            if(rs.next()){
                isExist = true;
            }else {
                isExist = false;
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return isExist;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txt_contact = new app.bolivia.swing.JCTextField();
        rSMaterialButtonCircle1 = new rojerusan.RSMaterialButtonCircle();
        rSMaterialButtonCircle2 = new rojerusan.RSMaterialButtonCircle();
        txt_username = new app.bolivia.swing.JCTextField();
        txt_password = new app.bolivia.swing.JCTextField();
        txt_email = new app.bolivia.swing.JCTextField();
        jLabel14 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/libroo/Login 3.png"))); // NOI18N
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 990, 770));

        jLabel1.setFont(new java.awt.Font("C059", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(85, 101, 107));
        jLabel1.setText("LIBRO");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 20, -1, -1));

        jLabel3.setFont(new java.awt.Font("C059", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(171, 120, 120));
        jLabel3.setText("Welcome To");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 20, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 990, 830));

        jPanel2.setBackground(new java.awt.Color(236, 221, 174));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Sitka Display", 1, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(153, 153, 153));
        jLabel2.setText("Username");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 230, 180, 30));

        jLabel5.setFont(new java.awt.Font("Sitka Display", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(85, 101, 107));
        jLabel5.setText("X");
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 10, 20, -1));

        jLabel6.setFont(new java.awt.Font("Sitka Display", 1, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 51, 51));
        jLabel6.setText("Create New Account Here");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 130, 210, -1));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_Account_50px.png"))); // NOI18N
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 250, 50, -1));

        jLabel8.setFont(new java.awt.Font("Sitka Display", 1, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(153, 153, 153));
        jLabel8.setText("Password");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 330, 180, 30));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_Secure_50px.png"))); // NOI18N
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 350, 50, -1));

        jLabel10.setFont(new java.awt.Font("Sitka Display", 1, 16)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(153, 153, 153));
        jLabel10.setText("Email");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 420, 180, 30));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_Secured_Letter_50px.png"))); // NOI18N
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 450, 50, 30));

        jLabel12.setFont(new java.awt.Font("Sitka Display", 1, 16)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(153, 153, 153));
        jLabel12.setText("Contact");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 520, 180, 30));

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_Google_Mobile_50px.png"))); // NOI18N
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 530, 40, 50));

        txt_contact.setBackground(new java.awt.Color(171, 120, 120));
        txt_contact.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        txt_contact.setPlaceholder("Enter Phone No ....");
        txt_contact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_contactActionPerformed(evt);
            }
        });
        jPanel2.add(txt_contact, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 550, -1, -1));

        rSMaterialButtonCircle1.setBackground(new java.awt.Color(85, 101, 107));
        rSMaterialButtonCircle1.setText("Login");
        rSMaterialButtonCircle1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rSMaterialButtonCircle1MouseClicked(evt);
            }
        });
        jPanel2.add(rSMaterialButtonCircle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 700, 300, 70));

        rSMaterialButtonCircle2.setBackground(new java.awt.Color(85, 101, 107));
        rSMaterialButtonCircle2.setText("Sign Up");
        rSMaterialButtonCircle2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rSMaterialButtonCircle2MouseClicked(evt);
            }
        });
        rSMaterialButtonCircle2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonCircle2ActionPerformed(evt);
            }
        });
        jPanel2.add(rSMaterialButtonCircle2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 620, 300, 70));

        txt_username.setBackground(new java.awt.Color(171, 120, 120));
        txt_username.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        txt_username.setPlaceholder("Enter Username ....");
        txt_username.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_usernameFocusLost(evt);
            }
        });
        jPanel2.add(txt_username, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 260, -1, -1));

        txt_password.setBackground(new java.awt.Color(171, 120, 120));
        txt_password.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        txt_password.setPlaceholder("Enter Password ....\n");
        jPanel2.add(txt_password, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 360, -1, -1));

        txt_email.setBackground(new java.awt.Color(171, 120, 120));
        txt_email.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        txt_email.setPlaceholder("Enter Email ....");
        txt_email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_emailActionPerformed(evt);
            }
        });
        jPanel2.add(txt_email, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 450, -1, -1));

        jLabel14.setFont(new java.awt.Font("Sitka Display", 1, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(149, 91, 170));
        jLabel14.setText("Signup Page");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 90, 160, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 0, 540, 830));

        setSize(new java.awt.Dimension(1539, 836));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txt_usernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_usernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_usernameActionPerformed

    private void txt_passwordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_passwordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_passwordActionPerformed

    private void txt_emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_emailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_emailActionPerformed

    private void txt_contactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_contactActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_contactActionPerformed

    private void rSMaterialButtonCircle1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rSMaterialButtonCircle1ActionPerformed

    private void rSMaterialButtonCircle2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle2ActionPerformed
        if(validateSignup() == true){
            if(checkDuplicateUsers() == false){
                insertSignupDetails();
            }
            else{
                JOptionPane.showMessageDialog(this, "username already exists");
            }
        }
    }//GEN-LAST:event_rSMaterialButtonCircle2ActionPerformed
    // shutting down the window
    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel5MouseClicked

    private void txt_usernameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_usernameFocusLost
        if(checkDuplicateUsers() == true){
            JOptionPane.showMessageDialog(this, "username already exists");
        }
    }//GEN-LAST:event_txt_usernameFocusLost

    private void rSMaterialButtonCircle1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle1MouseClicked
        // TODO add your handling code here:
        LoginPage login = new LoginPage();
        login.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_rSMaterialButtonCircle1MouseClicked

    private void rSMaterialButtonCircle2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_rSMaterialButtonCircle2MouseClicked


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
            java.util.logging.Logger.getLogger(SignupPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SignupPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SignupPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SignupPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SignupPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private rojerusan.RSMaterialButtonCircle rSMaterialButtonCircle1;
    private rojerusan.RSMaterialButtonCircle rSMaterialButtonCircle2;
    private app.bolivia.swing.JCTextField txt_contact;
    private app.bolivia.swing.JCTextField txt_email;
    private app.bolivia.swing.JCTextField txt_password;
    private app.bolivia.swing.JCTextField txt_username;
    // End of variables declaration//GEN-END:variables
}
