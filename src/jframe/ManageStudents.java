/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Jframe;

import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.*;
import jframe.DBConnection;
import jframe.HomePage;

/**
 *
 * @author Spooksy
 */
public class ManageStudents extends javax.swing.JFrame {

    /**
     * Creates new form ManageBooks
     */
    public ManageStudents() {
        initComponents();
        setDetailsToTable();
    }
    String student_name,course,branch;
    int student_id;
    DefaultTableModel model;
    //To set Book Detials into Table
    public void setDetailsToTable()
    {
        try
        {
            Connection con=DBConnection.getConnection();
            Statement st=con.createStatement();
            ResultSet rst=st.executeQuery("select * from student_details");
            while(rst.next())
            {
                student_id=rst.getInt("student_id");
                student_name=rst.getString("name");
                course=rst.getString("course");
                branch=rst.getString("branch");
                Object obj[]= {student_id,student_name,course,branch}; 
                model=(DefaultTableModel)tbl_studentdetails.getModel();
                model.addRow(obj);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
//    public boolean addStudent()
//    {
//        boolean added=false;
//        student_id=Integer.parseInt(txt_studentid.getText());
//        student_name=txt_studentname.getText();
//        course=txt_course.getSelectedItem().toString();
//        branch=txt_branch.getSelectedItem().toString();
//        try
//        {
//            Connection con=DBConnection.getConnection();
//            PreparedStatement pst=con.prepareStatement("insert into student_details values(?,?,?,?)");
//            pst.setInt(1, student_id);
//            pst.setString(2,student_name);
//            pst.setString(3, course);
//            pst.setString(4, branch);
//            int rowcount=pst.executeUpdate();
//            if(rowcount>0)
//                added=true;
//        }
//        catch(Exception e)
//        {
//            e.printStackTrace();
//        }
//        return added;
//    }
    
    public boolean addStudent()
{
    boolean added = false;
    student_id = Integer.parseInt(txt_studentid.getText());
    student_name = txt_studentname.getText();
    course = txt_course.getSelectedItem().toString();
    branch = txt_branch.getSelectedItem().toString();
    
    try
    {
        Connection con = DBConnection.getConnection();
        PreparedStatement pst = con.prepareStatement("INSERT INTO student_details VALUES(?, ?, ?, ?)");
        pst.setInt(1, student_id);
        pst.setString(2, student_name);
        pst.setString(3, course);
        pst.setString(4, branch);
        int rowcount = pst.executeUpdate();
        
        if (rowcount > 0) {
            added = true;
        }
    }
    catch (SQLException e)
    {
        if (e.getMessage().contains("Student ID already exists")) {
            JOptionPane.showMessageDialog(this, "Error: Student ID already exists.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            e.printStackTrace();
        }
    }
    return added;
}
    //Clear Table
    public void clearTable()
    {
        DefaultTableModel model=(DefaultTableModel)tbl_studentdetails.getModel();
        model.setRowCount(0);
    }
    //Update Student Table
    public boolean updateStudent()
    {
        boolean updated=false;
        student_id=Integer.parseInt(txt_studentid.getText());
        student_name=txt_studentname.getText();
        course=txt_course.getSelectedItem().toString();
        branch=txt_branch.getSelectedItem().toString();
        try
        {
            Connection con=DBConnection.getConnection();
            PreparedStatement pst=con.prepareStatement("Update student_details set name=?,course=?,branch=? where student_id=?");
            pst.setString(1, student_name);
            pst.setString(2, course);
            pst.setString(3, branch);
            pst.setInt(4,student_id);
            int rowcount=pst.executeUpdate();
            if(rowcount>0)
                updated=true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return updated;
    }
    //Delete Student
    public boolean deleteStudent()
    {
        boolean deleted=false;
        student_id=Integer.parseInt(txt_studentid.getText());
        try
        {
            Connection con=DBConnection.getConnection();
            PreparedStatement pst=con.prepareStatement("Delete from student_details where student_id=?");
            pst.setInt(1, student_id);
            int rowcount=pst.executeUpdate();
            if(rowcount>0)
                deleted=true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return deleted;
    }
    public boolean validateStudentDetails()
    {
        String student_id = txt_studentid.getText();
        student_name = txt_studentname.getText();
        course = txt_course.getSelectedItem().toString();
        branch = txt_branch.getSelectedItem().toString();
        if(student_id.equals(""))
        {
            JOptionPane.showMessageDialog(this, "Please Enter Student ID");
            return false;
        }
        if(student_name.equals(""))
        {
            JOptionPane.showMessageDialog(this, "Please Enter Student Name");
            return false;
        }
        if(course.equals(""))
        {
            JOptionPane.showMessageDialog(this, "Please Select Course");
            return false;
        }
        if(branch.equals(""))
        {
            JOptionPane.showMessageDialog(this, "Please Select Branch");
            return false;
        }
        return true;
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
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt_studentid = new app.bolivia.swing.JCTextField();
        txt_studentname = new app.bolivia.swing.JCTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        rSMaterialButtonCircle1 = new rojerusan.RSMaterialButtonCircle();
        rSMaterialButtonCircle2 = new rojerusan.RSMaterialButtonCircle();
        rSMaterialButtonCircle3 = new rojerusan.RSMaterialButtonCircle();
        txt_branch = new javax.swing.JComboBox<>();
        txt_course = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_studentdetails = new rojeru_san.complementos.RSTableMetro();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1535, 828));
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(102, 102, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(380, 828));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 51, 102));
        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel2MouseClicked(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/home_24px.png"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel1)
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 90, 40));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel9.setText("Student ID");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 110, 170, 30));

        jLabel4.setFont(new java.awt.Font("Nirmala UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Contact_26px.png"))); // NOI18N
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, 30, 40));

        txt_studentid.setBackground(new java.awt.Color(102, 102, 255));
        txt_studentid.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        txt_studentid.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        txt_studentid.setPhColor(new java.awt.Color(255, 255, 255));
        txt_studentid.setPlaceholder("Enter Student ID");
        txt_studentid.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_studentidFocusLost(evt);
            }
        });
        txt_studentid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_studentidActionPerformed(evt);
            }
        });
        jPanel1.add(txt_studentid, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 150, 250, -1));

        txt_studentname.setBackground(new java.awt.Color(102, 102, 255));
        txt_studentname.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        txt_studentname.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        txt_studentname.setPhColor(new java.awt.Color(255, 255, 255));
        txt_studentname.setPlaceholder("Enter Student Name");
        txt_studentname.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_studentnameFocusLost(evt);
            }
        });
        txt_studentname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_studentnameActionPerformed(evt);
            }
        });
        jPanel1.add(txt_studentname, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 250, 250, -1));

        jLabel5.setFont(new java.awt.Font("Nirmala UI", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Collaborator_Male_26px.png"))); // NOI18N
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 30, 40));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel10.setText("Name");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 210, 170, 30));

        jLabel6.setFont(new java.awt.Font("Nirmala UI", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Moleskine_26px.png"))); // NOI18N
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 340, 30, 40));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel11.setText("Course");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 310, 170, 30));

        jLabel7.setFont(new java.awt.Font("Nirmala UI", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Unit_26px.png"))); // NOI18N
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 440, 30, 40));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel12.setText("Branch");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 410, 170, 30));

        rSMaterialButtonCircle1.setBackground(new java.awt.Color(0, 102, 255));
        rSMaterialButtonCircle1.setText("Update");
        rSMaterialButtonCircle1.setFont(new java.awt.Font("Roboto Medium", 0, 18)); // NOI18N
        rSMaterialButtonCircle1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rSMaterialButtonCircle1MouseClicked(evt);
            }
        });
        rSMaterialButtonCircle1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonCircle1ActionPerformed(evt);
            }
        });
        jPanel1.add(rSMaterialButtonCircle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 530, 130, 60));

        rSMaterialButtonCircle2.setBackground(new java.awt.Color(255, 51, 51));
        rSMaterialButtonCircle2.setText("Delete");
        rSMaterialButtonCircle2.setFont(new java.awt.Font("Roboto Medium", 0, 18)); // NOI18N
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
        jPanel1.add(rSMaterialButtonCircle2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 530, 130, 60));

        rSMaterialButtonCircle3.setBackground(new java.awt.Color(255, 51, 51));
        rSMaterialButtonCircle3.setText("Add");
        rSMaterialButtonCircle3.setFont(new java.awt.Font("Roboto Medium", 0, 18)); // NOI18N
        rSMaterialButtonCircle3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rSMaterialButtonCircle3MouseClicked(evt);
            }
        });
        rSMaterialButtonCircle3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonCircle3ActionPerformed(evt);
            }
        });
        jPanel1.add(rSMaterialButtonCircle3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 530, 130, 60));

        txt_branch.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txt_branch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CSE", "EEE", "CEE", "MCE" }));
        txt_branch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_branchActionPerformed(evt);
            }
        });
        jPanel1.add(txt_branch, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 450, 250, -1));

        txt_course.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txt_course.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "BSc ", "MSc ", "PHD" }));
        txt_course.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_courseActionPerformed(evt);
            }
        });
        jPanel1.add(txt_course, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 350, 250, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 410, 828));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(255, 51, 102));
        jPanel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel4MouseClicked(evt);
            }
        });
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_Exit_26px.png"))); // NOI18N
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        jPanel4.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, -1, 40));

        jPanel3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 0, 70, 40));

        tbl_studentdetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Student ID", "Name", "Course", "Branch"
            }
        ));
        tbl_studentdetails.setColorBackgoundHead(new java.awt.Color(102, 102, 255));
        tbl_studentdetails.setColorFilasBackgound2(new java.awt.Color(255, 255, 255));
        tbl_studentdetails.setColorFilasForeground1(new java.awt.Color(102, 102, 255));
        tbl_studentdetails.setColorFilasForeground2(new java.awt.Color(102, 102, 255));
        tbl_studentdetails.setColorSelBackgound(new java.awt.Color(255, 102, 102));
        tbl_studentdetails.setFont(new java.awt.Font("Yu Gothic Light", 0, 25)); // NOI18N
        tbl_studentdetails.setFuenteFilas(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        tbl_studentdetails.setRowHeight(35);
        tbl_studentdetails.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_studentdetailsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_studentdetails);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 220, 790, 380));

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 32)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Student_Male_100px.png"))); // NOI18N
        jLabel3.setText("Manage Students");
        jLabel3.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 7, 0, new java.awt.Color(255, 51, 51)));
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 100, 370, 110));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 0, 1130, 830));

        setSize(new java.awt.Dimension(1535, 828));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        HomePage home= new HomePage();
        home.setVisible(true);
        dispose();       
    }//GEN-LAST:event_jLabel1MouseClicked

    private void txt_studentidFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_studentidFocusLost

    }//GEN-LAST:event_txt_studentidFocusLost

    private void txt_studentidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_studentidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_studentidActionPerformed

    private void txt_studentnameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_studentnameFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_studentnameFocusLost

    private void txt_studentnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_studentnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_studentnameActionPerformed

    private void jPanel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseClicked
         HomePage home= new HomePage();
        home.setVisible(true);
        dispose();
    }//GEN-LAST:event_jPanel2MouseClicked

    private void rSMaterialButtonCircle1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle1ActionPerformed
        
    }//GEN-LAST:event_rSMaterialButtonCircle1ActionPerformed

    private void rSMaterialButtonCircle2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rSMaterialButtonCircle2ActionPerformed

    private void rSMaterialButtonCircle3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rSMaterialButtonCircle3ActionPerformed

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jPanel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MouseClicked

    }//GEN-LAST:event_jPanel4MouseClicked

    private void tbl_studentdetailsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_studentdetailsMouseClicked
        int rowno= tbl_studentdetails.getSelectedRow();
        TableModel model=tbl_studentdetails.getModel();
        txt_studentid.setText(model.getValueAt(rowno, 0).toString());
        txt_studentname.setText(model.getValueAt(rowno, 1).toString());
        txt_course.setSelectedItem(model.getValueAt(rowno, 2).toString());
        txt_branch.setSelectedItem(model.getValueAt(rowno, 3).toString());
    }//GEN-LAST:event_tbl_studentdetailsMouseClicked

    private void rSMaterialButtonCircle3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle3MouseClicked
        if(validateStudentDetails()==true)
        {
            if(addStudent()==true)
            {
                JOptionPane.showMessageDialog(this, "Student Added");
                clearTable();
                setDetailsToTable();
            }
            else
                JOptionPane.showMessageDialog(this, "Student Addition Failed");
        }
    }//GEN-LAST:event_rSMaterialButtonCircle3MouseClicked

    private void rSMaterialButtonCircle1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle1MouseClicked
        if(updateStudent()==true)
        {
            JOptionPane.showMessageDialog(this, "Student Updated");
            clearTable();
            setDetailsToTable();
        }
        else
            JOptionPane.showMessageDialog(this, "Student Updation Failed");
    }//GEN-LAST:event_rSMaterialButtonCircle1MouseClicked

    private void rSMaterialButtonCircle2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle2MouseClicked
        if(deleteStudent()==true)
        {
            JOptionPane.showMessageDialog(this, "Student Deleted");
            clearTable();
            setDetailsToTable();
        }
        else
            JOptionPane.showMessageDialog(this, "Student Deletion Failed");
    }//GEN-LAST:event_rSMaterialButtonCircle2MouseClicked

    private void txt_branchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_branchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_branchActionPerformed

    private void txt_courseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_courseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_courseActionPerformed

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
            java.util.logging.Logger.getLogger(ManageStudents.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManageStudents.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManageStudents.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManageStudents.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManageStudents().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private rojerusan.RSMaterialButtonCircle rSMaterialButtonCircle1;
    private rojerusan.RSMaterialButtonCircle rSMaterialButtonCircle2;
    private rojerusan.RSMaterialButtonCircle rSMaterialButtonCircle3;
    private rojeru_san.complementos.RSTableMetro tbl_studentdetails;
    private javax.swing.JComboBox<String> txt_branch;
    private javax.swing.JComboBox<String> txt_course;
    private app.bolivia.swing.JCTextField txt_studentid;
    private app.bolivia.swing.JCTextField txt_studentname;
    // End of variables declaration//GEN-END:variables
}