/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package jframe;

import java.util.Date;
import java.sql.*;
import javax.swing.JOptionPane;
import jframe.DBConnection;
import jframe.HomePage;

/**
 *
 * @author Spooksy
 */
public class IssueBook extends javax.swing.JFrame {

    /**
     * Creates new form IssueBook
     */
    public IssueBook() {
        initComponents();
    }

    //Get Book Details from DB
    public void getBookDetails() {
        int bookId = Integer.parseInt(txt_bookid.getText());
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement pst = con.prepareStatement("select * from book_details where book_id=?");
            pst.setInt(1, bookId);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                lbl_bookid.setText(rs.getString("book_id"));
                lbl_bookname.setText(rs.getString("book_name"));
                lbl_author.setText(rs.getString("author"));
                lbl_qty.setText(rs.getString("quantity"));
            } else {
                lbl_invalidbookid.setText("Invalid Book ID");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //Getting Student Details from DB
    public void getStudentDetails() {
        int studentId = Integer.parseInt(txt_studentid.getText());
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement pst = con.prepareStatement("select * from student_details where student_id=?");
            pst.setInt(1, studentId);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                lbl_studentid.setText(rs.getString("student_id"));
                lbl_studentname.setText(rs.getString("name"));
                lbl_course.setText(rs.getString("course"));
                lbl_branch.setText(rs.getString("branch"));
            } else {
                lbl_invalidstudentid.setText("Invalid Student ID");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //Insert Issue Details into DB
    public boolean issueBook() {
        boolean issued = false;
        int bookId = Integer.parseInt(txt_bookid.getText());
        int studentId = Integer.parseInt(txt_studentid.getText());
        String bookName = lbl_bookname.getText();
        String studentName = lbl_studentname.getText();
        Date uissueDate = txt_issuedate.getDate();
        Date udueDate = txt_duedate.getDate();

        java.sql.Date sissueDate = new java.sql.Date(uissueDate.getTime());
        java.sql.Date sdueDate = new java.sql.Date(udueDate.getTime());

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO issue_book_details (book_id, book_name, student_id, student_name, issue_date, due_date, status) VALUES (?, ?, ?, ?, ?, ?, ?)"
            );
            ps.setInt(1, bookId);
            ps.setString(2, bookName);
            ps.setInt(3, studentId);
            ps.setString(4, studentName);
            ps.setDate(5, sissueDate);
            ps.setDate(6, sdueDate);
            ps.setString(7, "Pending");

            int rowCount = ps.executeUpdate();
            if (rowCount > 0) {
                issued = true;
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return issued;
    }

//    public boolean issueBook()
//    {
//        boolean issued=false;
//        int bookId=Integer.parseInt(txt_bookid.getText());
//        int studentId=Integer.parseInt(txt_studentid.getText());
//        String bookName=lbl_bookname.getText();
//        String studentName=lbl_studentname.getText();
//        Date  uissueDate=txt_issuedate.getDate();
//        Date  udueDate=txt_duedate.getDate();
//        Long lissue=uissueDate.getTime();
//        Long ldue=udueDate.getTime();
//        java.sql.Date sissueDate= new java.sql.Date(lissue);
//        java.sql.Date sdueDate= new java.sql.Date(ldue);
//        try
//        {
//            Connection con=DBConnection.getConnection();
//            PreparedStatement ps=con.prepareStatement("insert into issue_book_details(book_id,book_name,student_id,student_name,issue_date,due_date,status) values (?,?,?,?,?,?,?)");
//            ps.setInt(1,bookId);
//            ps.setString(2,bookName);
//            ps.setInt(3,studentId);
//            ps.setString(4,studentName);
//            ps.setDate(5, sissueDate);
//            ps.setDate(6,sdueDate);
//            ps.setString(7, "Pending");
//            int rowcount=ps.executeUpdate();
//            if(rowcount>0)
//            {
//                issued=true;
//            }
//        }
//        catch(Exception e)
//        {
//            e.printStackTrace();
//        }
//        return issued;
//    }
    //Update Book Count after ever Issue
    public void updateBookCount() {
        int bookId = Integer.parseInt(txt_bookid.getText());
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("update book_details set quantity=quantity-1 where book_id=?");
            ps.setInt(1, bookId);
            int rowcount = ps.executeUpdate();
            if (rowcount > 0) {
                JOptionPane.showMessageDialog(this, "Book Count Updated");
                int updation = Integer.parseInt(lbl_qty.getText());
                lbl_qty.setText(Integer.toString(updation - 1));
            } else {
                JOptionPane.showMessageDialog(this, "Problem Updating Book Count");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Check Whether book already alloted or not
    public boolean alreadyIssued() {
        boolean issued = false;
        int bookId = Integer.parseInt(txt_bookid.getText());
        int studentId = Integer.parseInt(txt_studentid.getText());
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("select * from issue_book_details where book_id=? and student_id=? and status=?");
            ps.setInt(1, bookId);
            ps.setInt(2, studentId);
            ps.setString(3, "pending");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                issued = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return issued;
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
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        lbl_qty = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        lbl_bookname = new javax.swing.JLabel();
        lbl_author = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        lbl_bookid = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        lbl_invalidbookid = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        lbl_course = new javax.swing.JLabel();
        lbl_studentname = new javax.swing.JLabel();
        lbl_studentid = new javax.swing.JLabel();
        lbl_branch = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        lbl_invalidstudentid = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txt_bookid = new app.bolivia.swing.JCTextField();
        jLabel21 = new javax.swing.JLabel();
        txt_studentid = new app.bolivia.swing.JCTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        rSMaterialButtonCircle1 = new rojerusan.RSMaterialButtonCircle();
        txt_duedate = new com.toedter.calendar.JDateChooser();
        txt_issuedate = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(85, 101, 107));
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

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1470, 0, 70, 40));

        jPanel3.setBackground(new java.awt.Color(236, 221, 174));
        jPanel3.setPreferredSize(new java.awt.Dimension(380, 828));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(85, 101, 107));
        jPanel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel5MouseClicked(evt);
            }
        });

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/home_24px.png"))); // NOI18N
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel3)
                .addContainerGap(37, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        jPanel3.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 90, 40));

        lbl_qty.setFont(new java.awt.Font("Nirmala UI", 1, 18)); // NOI18N
        lbl_qty.setForeground(new java.awt.Color(85, 101, 107));
        lbl_qty.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbl_qty.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        jPanel3.add(lbl_qty, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 580, 230, 30));

        jLabel4.setFont(new java.awt.Font("Nirmala UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Contact_26px.png"))); // NOI18N
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 290, 50, 20));

        jLabel5.setFont(new java.awt.Font("Nirmala UI", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Moleskine_26px.png"))); // NOI18N
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 380, 50, 30));

        jLabel10.setFont(new java.awt.Font("Nirmala UI", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(85, 101, 107));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel10.setText("Book Name");
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 330, 190, 30));

        jLabel6.setFont(new java.awt.Font("Nirmala UI", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Collaborator_Male_26px.png"))); // NOI18N
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 480, 50, 30));

        jLabel11.setFont(new java.awt.Font("Nirmala UI", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(85, 101, 107));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel11.setText("Author's Name");
        jPanel3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 440, 190, 30));

        jLabel7.setFont(new java.awt.Font("Nirmala UI", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Unit_26px.png"))); // NOI18N
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 580, 50, 30));

        jLabel12.setFont(new java.awt.Font("Nirmala UI", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(85, 101, 107));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel12.setText("Quantity");
        jPanel3.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 530, 190, 30));

        lbl_bookname.setFont(new java.awt.Font("Nirmala UI", 1, 18)); // NOI18N
        lbl_bookname.setForeground(new java.awt.Color(85, 101, 107));
        lbl_bookname.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbl_bookname.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        jPanel3.add(lbl_bookname, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 380, 230, 30));

        lbl_author.setFont(new java.awt.Font("Nirmala UI", 1, 18)); // NOI18N
        lbl_author.setForeground(new java.awt.Color(85, 101, 107));
        lbl_author.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbl_author.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        jPanel3.add(lbl_author, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 480, 230, 30));

        jLabel29.setFont(new java.awt.Font("Nirmala UI", 1, 18)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(85, 101, 107));
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel29.setText("Book ID");
        jPanel3.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 240, 190, 30));

        lbl_bookid.setFont(new java.awt.Font("Nirmala UI", 1, 18)); // NOI18N
        lbl_bookid.setForeground(new java.awt.Color(85, 101, 107));
        lbl_bookid.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbl_bookid.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        jPanel3.add(lbl_bookid, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 280, 230, 30));

        jLabel31.setFont(new java.awt.Font("Nirmala UI", 1, 18)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(85, 101, 107));
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Literature_100px_1.png"))); // NOI18N
        jLabel31.setText("  Book Details");
        jLabel31.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        jPanel3.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 410, 130));

        lbl_invalidbookid.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbl_invalidbookid.setForeground(new java.awt.Color(255, 204, 0));
        lbl_invalidbookid.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel3.add(lbl_invalidbookid, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 670, 280, 40));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 410, 828));

        jPanel6.setBackground(new java.awt.Color(197, 171, 157));
        jPanel6.setPreferredSize(new java.awt.Dimension(380, 828));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel13.setFont(new java.awt.Font("Nirmala UI", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(85, 101, 107));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel13.setText("Student ID");
        jPanel6.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 240, 210, 30));

        jLabel14.setFont(new java.awt.Font("Nirmala UI", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Contact_26px.png"))); // NOI18N
        jPanel6.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 280, 70, 30));

        jLabel15.setFont(new java.awt.Font("Nirmala UI", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Moleskine_26px.png"))); // NOI18N
        jPanel6.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 470, 70, 30));

        jLabel16.setFont(new java.awt.Font("Nirmala UI", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(85, 101, 107));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel16.setText("Student Name");
        jPanel6.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 330, 210, 30));

        jLabel17.setFont(new java.awt.Font("Nirmala UI", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Collaborator_Male_26px.png"))); // NOI18N
        jPanel6.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 380, 70, 30));

        jLabel18.setFont(new java.awt.Font("Nirmala UI", 1, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(85, 101, 107));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel18.setText("Course");
        jPanel6.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 440, 210, 30));

        jLabel19.setFont(new java.awt.Font("Nirmala UI", 1, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Unit_26px.png"))); // NOI18N
        jPanel6.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 580, 70, 30));

        jLabel20.setFont(new java.awt.Font("Nirmala UI", 1, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(85, 101, 107));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel20.setText("Branch");
        jPanel6.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 530, 210, 30));

        lbl_course.setFont(new java.awt.Font("Nirmala UI", 1, 18)); // NOI18N
        lbl_course.setForeground(new java.awt.Color(85, 101, 107));
        lbl_course.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbl_course.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        jPanel6.add(lbl_course, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 470, 250, 30));

        lbl_studentname.setFont(new java.awt.Font("Nirmala UI", 1, 18)); // NOI18N
        lbl_studentname.setForeground(new java.awt.Color(85, 101, 107));
        lbl_studentname.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbl_studentname.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        jPanel6.add(lbl_studentname, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 380, 250, 30));

        lbl_studentid.setFont(new java.awt.Font("Nirmala UI", 1, 18)); // NOI18N
        lbl_studentid.setForeground(new java.awt.Color(85, 101, 107));
        lbl_studentid.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbl_studentid.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        jPanel6.add(lbl_studentid, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 280, 250, 30));

        lbl_branch.setFont(new java.awt.Font("Nirmala UI", 1, 18)); // NOI18N
        lbl_branch.setForeground(new java.awt.Color(85, 101, 107));
        lbl_branch.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbl_branch.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        jPanel6.add(lbl_branch, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 580, 250, 30));

        jLabel32.setFont(new java.awt.Font("Nirmala UI", 1, 18)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(85, 101, 107));
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Student_Registration_100px_2.png"))); // NOI18N
        jLabel32.setText("  Student Details");
        jLabel32.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        jPanel6.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 410, 130));

        lbl_invalidstudentid.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbl_invalidstudentid.setForeground(new java.awt.Color(0, 51, 204));
        lbl_invalidstudentid.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel6.add(lbl_invalidstudentid, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 670, 280, 40));

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 0, 410, -1));

        jLabel22.setBackground(new java.awt.Color(171, 120, 120));
        jLabel22.setFont(new java.awt.Font("Nirmala UI", 1, 24)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(171, 120, 120));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Books_52px_1.png"))); // NOI18N
        jLabel22.setText("    ISSUE BOOKS ");
        jLabel22.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 4, 0, new java.awt.Color(171, 120, 120)));
        jPanel1.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 50, 720, 130));

        jLabel9.setFont(new java.awt.Font("Nirmala UI", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(171, 120, 120));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel9.setText("Book ID:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 270, 120, 30));

        txt_bookid.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(171, 120, 120)));
        txt_bookid.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        txt_bookid.setPhColor(new java.awt.Color(102, 102, 102));
        txt_bookid.setPlaceholder("Enter Book ID");
        txt_bookid.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_bookidFocusLost(evt);
            }
        });
        txt_bookid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_bookidActionPerformed(evt);
            }
        });
        jPanel1.add(txt_bookid, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 270, 310, -1));

        jLabel21.setFont(new java.awt.Font("Nirmala UI", 1, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(171, 120, 120));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel21.setText("Issue Date:");
        jPanel1.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 460, 120, 30));

        txt_studentid.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(171, 120, 120)));
        txt_studentid.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        txt_studentid.setPhColor(new java.awt.Color(102, 102, 102));
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
        jPanel1.add(txt_studentid, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 370, 310, -1));

        jLabel23.setFont(new java.awt.Font("Nirmala UI", 1, 18)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(171, 120, 120));
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel23.setText("Student ID:");
        jPanel1.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 370, 120, 30));

        jLabel24.setFont(new java.awt.Font("Nirmala UI", 1, 18)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(171, 120, 120));
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel24.setText("Due Date:");
        jPanel1.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 560, 120, 30));

        rSMaterialButtonCircle1.setBackground(new java.awt.Color(85, 101, 107));
        rSMaterialButtonCircle1.setText("Issue Book");
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
        jPanel1.add(rSMaterialButtonCircle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 680, 300, 70));

        txt_duedate.setBackground(new java.awt.Color(171, 120, 120));
        txt_duedate.setForeground(new java.awt.Color(171, 120, 120));
        txt_duedate.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jPanel1.add(txt_duedate, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 560, 320, 40));

        txt_issuedate.setBackground(new java.awt.Color(171, 120, 120));
        txt_issuedate.setForeground(new java.awt.Color(171, 120, 120));
        txt_issuedate.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jPanel1.add(txt_issuedate, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 450, 320, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1540, 830));

        setSize(new java.awt.Dimension(1535, 828));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jPanel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MouseClicked

    }//GEN-LAST:event_jPanel4MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        HomePage home = new HomePage();
        home.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jPanel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel5MouseClicked
        HomePage home = new HomePage();
        home.setVisible(true);
        dispose();
    }//GEN-LAST:event_jPanel5MouseClicked

    private void txt_bookidFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_bookidFocusLost
        if (!txt_bookid.getText().equals("")) {
            getBookDetails();
        }
    }//GEN-LAST:event_txt_bookidFocusLost

    private void txt_bookidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_bookidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_bookidActionPerformed

    private void txt_studentidFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_studentidFocusLost
        if (!txt_studentid.getText().equals("")) {
            getStudentDetails();
        }
    }//GEN-LAST:event_txt_studentidFocusLost

    private void txt_studentidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_studentidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_studentidActionPerformed

    private void rSMaterialButtonCircle1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rSMaterialButtonCircle1ActionPerformed

    private void rSMaterialButtonCircle1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle1MouseClicked

        if (issueBook()) {
            JOptionPane.showMessageDialog(this, "Book Issued Successfully");
        } else {
            JOptionPane.showMessageDialog(this, "Book Issue Failed");
        }
    }//GEN-LAST:event_rSMaterialButtonCircle1MouseClicked

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
            java.util.logging.Logger.getLogger(ReturnBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReturnBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReturnBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReturnBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new IssueBook().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JLabel lbl_author;
    private javax.swing.JLabel lbl_bookid;
    private javax.swing.JLabel lbl_bookname;
    private javax.swing.JLabel lbl_branch;
    private javax.swing.JLabel lbl_course;
    private javax.swing.JLabel lbl_invalidbookid;
    private javax.swing.JLabel lbl_invalidstudentid;
    private javax.swing.JLabel lbl_qty;
    private javax.swing.JLabel lbl_studentid;
    private javax.swing.JLabel lbl_studentname;
    private rojerusan.RSMaterialButtonCircle rSMaterialButtonCircle1;
    private app.bolivia.swing.JCTextField txt_bookid;
    private com.toedter.calendar.JDateChooser txt_duedate;
    private com.toedter.calendar.JDateChooser txt_issuedate;
    private app.bolivia.swing.JCTextField txt_studentid;
    // End of variables declaration//GEN-END:variables
}
