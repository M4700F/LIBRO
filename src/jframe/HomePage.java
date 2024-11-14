/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package jframe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.sql.Connection;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import jframe.DBConnection;
import jframe.LoginPage;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author Spooksy
 */
public class HomePage extends javax.swing.JFrame {

    /**
     * Creates new form HomePage
     */
    public HomePage() {
        initComponents();
        showPieChart();
        setDetailsToStudentTable();
        setDetailsToBookTable();
        setDataToCards();
    }

    Color mouseEnter = new Color(0, 0, 0);
    Color mouseExit = new Color(51, 51, 51);

    public void showPieChart() {

        //create dataset
        DefaultPieDataset barDataset = new DefaultPieDataset();
        try {
            Connection con = DBConnection.getConnection();
            String sql = "select bookname, count(*) as issuecount from issue_book_details group by bookid";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                barDataset.setValue(rs.getString("bookname"), new Double(rs.getDouble("issuecount")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //create chart
        JFreeChart piechart = ChartFactory.createPieChart("Issued Book Details", barDataset, true, true, false);//explain

        PiePlot piePlot = (PiePlot) piechart.getPlot();

        //changing pie chart blocks colors
        piePlot.setSectionPaint("Java: A Beginner’s Guide", new Color(255, 255, 102));
        piePlot.setSectionPaint("Head First Java", new Color(102, 255, 102));
        piePlot.setSectionPaint("Java for Dummies", new Color(255, 102, 153));
        piePlot.setSectionPaint("Effective Java", new Color(0, 204, 204));

        piePlot.setBackgroundPaint(Color.white);

        //create chartPanel to display chart(graph)
        ChartPanel barChartPanel = new ChartPanel(piechart);
        PanelPieChart.removeAll();
        PanelPieChart.add(barChartPanel, BorderLayout.CENTER);
        PanelPieChart.validate();
    }

    public void setDetailsToBookTable() {

        try {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet rst = st.executeQuery("select * from book_details");
            while (rst.next()) {
                int book_id = rst.getInt("bookid");
                String book_name = rst.getString("bookname");
                String author = rst.getString("author");
                int qty = rst.getInt("quantity");
                Object obj[] = {book_id, book_name, author, qty};
                DefaultTableModel model = (DefaultTableModel) tbl_bookdetails.getModel();
                model.addRow(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setDetailsToStudentTable() {
        try {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet rst = st.executeQuery("select * from student_details");
            while (rst.next()) {
                int student_id = rst.getInt("studentid");
                String student_name = rst.getString("studentname");
                String course = rst.getString("course");
                String branch = rst.getString("branch");
                Object obj[] = {student_id, student_name, course, branch};
                DefaultTableModel model = (DefaultTableModel) tbl_studentdetails.getModel();
                model.addRow(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setDataToCards() {
        Statement st = null;
        ResultSet rs = null;
        long due = System.currentTimeMillis();
        java.sql.Date today = new java.sql.Date(due);
        try {
            Connection con = DBConnection.getConnection();
            st = con.createStatement();
            rs = st.executeQuery("select * from book_details");
            rs.last();
            lbl_books.setText(Integer.toString(rs.getRow()));
            rs = st.executeQuery("select * from student_details");
            rs.last();
            lbl_students.setText(Integer.toString(rs.getRow()));
            rs = st.executeQuery("select * from issue_book_details where status='" + "pending" + "'");
            rs.last();
            lbl_issued.setText(Integer.toString(rs.getRow()));
            rs = st.executeQuery("select * from issue_book_details where duedate<'" + today + "'and status='" + "pending" + "'");
            rs.last();
            lbl_defaulters.setText(Integer.toString(rs.getRow()));
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel45 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        jPanel24 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jPanel25 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jPanel26 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jPanel27 = new javax.swing.JPanel();
        jPanel28 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jPanel29 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jPanel30 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jPanel31 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel32 = new javax.swing.JPanel();
        jPanel34 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        jPanel35 = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        jPanel36 = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        jPanel37 = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        jPanel38 = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        jPanel39 = new javax.swing.JPanel();
        jLabel40 = new javax.swing.JLabel();
        jPanel33 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jPanel22 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jPanel40 = new javax.swing.JPanel();
        jPanel41 = new javax.swing.JPanel();
        lbl_books = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jPanel42 = new javax.swing.JPanel();
        lbl_students = new javax.swing.JLabel();
        jPanel43 = new javax.swing.JPanel();
        lbl_issued = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jPanel44 = new javax.swing.JPanel();
        lbl_defaulters = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        PanelPieChart = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_studentdetails = new rojeru_san.complementos.RSTableMetro();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_bookdetails = new rojeru_san.complementos.RSTableMetro();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 255, 204));
        setMinimumSize(new java.awt.Dimension(1535, 828));
        setName("HomepageFrame"); // NOI18N
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(1535, 828));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(51, 102, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1900, 70));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_menu_48px_1.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, -1, 62));

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));
        jPanel2.setPreferredSize(new java.awt.Dimension(5, 50));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, 5, 45));

        jLabel5.setBackground(java.awt.Color.white);
        jLabel5.setFont(new java.awt.Font("Noto Sans CJK SC", 0, 34)); // NOI18N
        jLabel5.setForeground(java.awt.Color.white);
        jLabel5.setText("Libro");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 0, -1, 60));

        jPanel45.setBackground(new java.awt.Color(255, 51, 102));
        jPanel45.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel45MouseClicked(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_Exit_26px.png"))); // NOI18N
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel45Layout = new javax.swing.GroupLayout(jPanel45);
        jPanel45.setLayout(jPanel45Layout);
        jPanel45Layout.setHorizontalGroup(
            jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel45Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(8, Short.MAX_VALUE))
        );
        jPanel45Layout.setVerticalGroup(
            jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel1.add(jPanel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(1830, 20, 40, 40));

        jLabel12.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 28)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(204, 204, 204));
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/male_user_50px.png"))); // NOI18N
        jLabel12.setText("Admin");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 10, 260, 60));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1900, 70));

        jPanel3.setBackground(new java.awt.Color(69, 66, 66));
        jPanel3.setForeground(new java.awt.Color(102, 102, 255));
        jPanel3.setPreferredSize(new java.awt.Dimension(340, 110));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(java.awt.Color.red);
        jPanel4.setForeground(java.awt.Color.red);
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Segoe UI Semibold", 1, 17)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_Home_26px_2.png"))); // NOI18N
        jLabel3.setText("   Home");
        jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 190, -1));

        jPanel3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 340, 60));

        jPanel6.setBackground(new java.awt.Color(51, 51, 51));
        jPanel6.setForeground(java.awt.Color.red);
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel7.setBackground(new java.awt.Color(51, 51, 51));
        jPanel7.setForeground(java.awt.Color.red);
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_View_Details_26px.png"))); // NOI18N
        jLabel8.setText("   Dashboard");
        jPanel7.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 340, -1));

        jPanel6.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, -1, 60));

        jPanel8.setBackground(new java.awt.Color(51, 51, 51));
        jPanel8.setForeground(java.awt.Color.red);
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_View_Details_26px.png"))); // NOI18N
        jLabel9.setText("   Dashboard");
        jPanel8.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 340, -1));

        jPanel9.setBackground(new java.awt.Color(51, 51, 51));
        jPanel9.setForeground(java.awt.Color.red);
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_View_Details_26px.png"))); // NOI18N
        jLabel10.setText("   Dashboard");
        jPanel9.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 340, -1));

        jPanel8.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, -1, 60));

        jPanel6.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, -1, 60));

        jLabel11.setFont(new java.awt.Font("Segoe UI Semibold", 1, 17)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(204, 204, 204));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_Library_26px_1.png"))); // NOI18N
        jLabel11.setText("   Dashboard");
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel11MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel11MouseExited(evt);
            }
        });
        jPanel6.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 220, 50));

        jPanel11.setBackground(new java.awt.Color(51, 51, 51));
        jPanel11.setForeground(java.awt.Color.red);
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel12.setBackground(new java.awt.Color(51, 51, 51));
        jPanel12.setForeground(java.awt.Color.red);
        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel13.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_View_Details_26px.png"))); // NOI18N
        jLabel13.setText("   Dashboard");
        jPanel12.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 340, -1));

        jPanel11.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, -1, 60));

        jPanel13.setBackground(new java.awt.Color(51, 51, 51));
        jPanel13.setForeground(java.awt.Color.red);
        jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel14.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_View_Details_26px.png"))); // NOI18N
        jLabel14.setText("   Dashboard");
        jPanel13.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 340, -1));

        jPanel14.setBackground(new java.awt.Color(51, 51, 51));
        jPanel14.setForeground(java.awt.Color.red);
        jPanel14.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel15.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_View_Details_26px.png"))); // NOI18N
        jLabel15.setText("   Dashboard");
        jPanel14.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 340, -1));

        jPanel13.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, -1, 60));

        jPanel11.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, -1, 60));

        jLabel16.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_View_Details_26px.png"))); // NOI18N
        jLabel16.setText("   Dashboard");
        jPanel11.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 340, -1));

        jPanel6.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, -1, 60));

        jPanel3.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, -1, 60));

        jPanel5.setBackground(new java.awt.Color(51, 51, 51));
        jPanel5.setForeground(java.awt.Color.red);
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Segoe UI Semibold", 1, 17)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(204, 204, 204));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_Books_26px.png"))); // NOI18N
        jLabel6.setText("   Manage Books");
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel6MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel6MouseExited(evt);
            }
        });
        jPanel5.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 230, 60));

        jPanel23.setBackground(new java.awt.Color(51, 51, 51));
        jPanel23.setForeground(java.awt.Color.red);
        jPanel23.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel24.setBackground(new java.awt.Color(51, 51, 51));
        jPanel24.setForeground(java.awt.Color.red);
        jPanel24.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel25.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_View_Details_26px.png"))); // NOI18N
        jLabel25.setText("   Dashboard");
        jPanel24.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 340, -1));

        jPanel23.add(jPanel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, -1, 60));

        jPanel25.setBackground(new java.awt.Color(51, 51, 51));
        jPanel25.setForeground(java.awt.Color.red);
        jPanel25.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel26.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_View_Details_26px.png"))); // NOI18N
        jLabel26.setText("   Dashboard");
        jPanel25.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 340, -1));

        jPanel26.setBackground(new java.awt.Color(51, 51, 51));
        jPanel26.setForeground(java.awt.Color.red);
        jPanel26.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel27.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_View_Details_26px.png"))); // NOI18N
        jLabel27.setText("   Dashboard");
        jPanel26.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 340, -1));

        jPanel25.add(jPanel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, -1, 60));

        jPanel23.add(jPanel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, -1, 60));

        jLabel28.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_View_Details_26px.png"))); // NOI18N
        jLabel28.setText("   Dashboard");
        jPanel23.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 340, -1));

        jPanel27.setBackground(new java.awt.Color(51, 51, 51));
        jPanel27.setForeground(java.awt.Color.red);
        jPanel27.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel28.setBackground(new java.awt.Color(51, 51, 51));
        jPanel28.setForeground(java.awt.Color.red);
        jPanel28.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel29.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_View_Details_26px.png"))); // NOI18N
        jLabel29.setText("   Dashboard");
        jPanel28.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 340, -1));

        jPanel27.add(jPanel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, -1, 60));

        jPanel29.setBackground(new java.awt.Color(51, 51, 51));
        jPanel29.setForeground(java.awt.Color.red);
        jPanel29.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel30.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_View_Details_26px.png"))); // NOI18N
        jLabel30.setText("   Dashboard");
        jPanel29.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 340, -1));

        jPanel30.setBackground(new java.awt.Color(51, 51, 51));
        jPanel30.setForeground(java.awt.Color.red);
        jPanel30.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel31.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_View_Details_26px.png"))); // NOI18N
        jLabel31.setText("   Dashboard");
        jPanel30.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 340, -1));

        jPanel29.add(jPanel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, -1, 60));

        jPanel27.add(jPanel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, -1, 60));

        jLabel32.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_View_Details_26px.png"))); // NOI18N
        jLabel32.setText("   Dashboard");
        jPanel27.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 340, -1));

        jPanel23.add(jPanel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, -1, 60));

        jPanel5.add(jPanel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, -1, 60));

        jPanel3.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 340, 60));

        jPanel31.setBackground(new java.awt.Color(51, 51, 51));
        jPanel31.setForeground(java.awt.Color.red);
        jPanel31.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Segoe UI Semibold", 1, 17)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(204, 204, 204));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_Read_Online_26px.png"))); // NOI18N
        jLabel7.setText("  Manage Students");
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel7MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel7MouseExited(evt);
            }
        });
        jPanel31.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, 210, 60));

        jPanel3.add(jPanel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 340, 60));

        jPanel32.setBackground(java.awt.Color.red);
        jPanel32.setForeground(java.awt.Color.red);
        jPanel32.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel3.add(jPanel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, -1, 60));

        jPanel34.setBackground(new java.awt.Color(51, 51, 51));
        jPanel34.setForeground(java.awt.Color.red);
        jPanel34.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel34.setFont(new java.awt.Font("Segoe UI Semibold", 1, 17)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(204, 204, 204));
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_Return_Purchase_26px.png"))); // NOI18N
        jLabel34.setText("   Return Book");
        jLabel34.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel34MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel34MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel34MouseExited(evt);
            }
        });
        jPanel34.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 250, 60));

        jPanel35.setBackground(java.awt.Color.red);
        jPanel35.setForeground(java.awt.Color.red);
        jPanel35.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel35.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(255, 255, 255));
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_Home_26px_2.png"))); // NOI18N
        jLabel35.setText("   Home  Page");
        jPanel35.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 340, -1));

        jPanel34.add(jPanel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 360, -1, 60));

        jPanel3.add(jPanel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 360, -1, 60));

        jPanel36.setBackground(new java.awt.Color(51, 51, 51));
        jPanel36.setForeground(java.awt.Color.red);
        jPanel36.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel36.setFont(new java.awt.Font("Segoe UI Semibold", 1, 17)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(204, 204, 204));
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_View_Details_26px.png"))); // NOI18N
        jLabel36.setText("   View Records");
        jLabel36.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel36MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel36MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel36MouseExited(evt);
            }
        });
        jPanel36.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 260, 60));

        jPanel3.add(jPanel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 420, 340, 60));

        jPanel37.setBackground(new java.awt.Color(51, 51, 51));
        jPanel37.setForeground(java.awt.Color.red);
        jPanel37.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel37.setFont(new java.awt.Font("Segoe UI Semibold", 1, 17)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(204, 204, 204));
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_Book_26px.png"))); // NOI18N
        jLabel37.setText("   View Issued Books");
        jLabel37.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel37MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel37MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel37MouseExited(evt);
            }
        });
        jPanel37.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 260, 50));

        jPanel3.add(jPanel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 480, 340, 60));

        jPanel38.setBackground(new java.awt.Color(51, 51, 51));
        jPanel38.setForeground(java.awt.Color.red);
        jPanel38.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel38.setFont(new java.awt.Font("Segoe UI Semibold", 1, 17)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(204, 204, 204));
        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_Conference_26px.png"))); // NOI18N
        jLabel38.setText("   Defaulter List");
        jLabel38.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel38MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel38MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel38MouseExited(evt);
            }
        });
        jPanel38.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 280, 60));

        jPanel3.add(jPanel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 530, 340, 60));

        jPanel39.setBackground(new java.awt.Color(102, 102, 255));
        jPanel39.setForeground(new java.awt.Color(255, 0, 102));
        jPanel39.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel39MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel39MouseExited(evt);
            }
        });
        jPanel39.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel40.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(255, 255, 255));
        jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel40.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_Exit_26px_2.png"))); // NOI18N
        jLabel40.setText("   Logout  ");
        jLabel40.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel40MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel40MouseEntered(evt);
            }
        });
        jPanel39.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 240, 60));

        jPanel3.add(jPanel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 590, 340, 60));

        jPanel33.setBackground(new java.awt.Color(51, 51, 51));
        jPanel33.setForeground(java.awt.Color.red);
        jPanel33.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel20.setFont(new java.awt.Font("Segoe UI Semibold", 1, 17)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(204, 204, 204));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_Sell_26px.png"))); // NOI18N
        jLabel20.setText("   Issue Book");
        jLabel20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel20MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel20MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel20MouseExited(evt);
            }
        });
        jPanel33.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 200, 60));

        jPanel3.add(jPanel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, 340, 60));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 340, 940));

        jPanel15.setBackground(new java.awt.Color(51, 51, 51));
        jPanel15.setForeground(java.awt.Color.red);
        jPanel15.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel16.setBackground(new java.awt.Color(51, 51, 51));
        jPanel16.setForeground(java.awt.Color.red);
        jPanel16.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel17.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_View_Details_26px.png"))); // NOI18N
        jLabel17.setText("   Dashboard");
        jPanel16.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 340, -1));

        jPanel15.add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, -1, 60));

        jPanel17.setBackground(new java.awt.Color(51, 51, 51));
        jPanel17.setForeground(java.awt.Color.red);
        jPanel17.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel18.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_View_Details_26px.png"))); // NOI18N
        jLabel18.setText("   Dashboard");
        jPanel17.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 340, -1));

        jPanel18.setBackground(new java.awt.Color(51, 51, 51));
        jPanel18.setForeground(java.awt.Color.red);
        jPanel18.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel19.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_View_Details_26px.png"))); // NOI18N
        jLabel19.setText("   Dashboard");
        jPanel18.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 340, -1));

        jPanel17.add(jPanel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, -1, 60));

        jPanel15.add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, -1, 60));

        jPanel19.setBackground(new java.awt.Color(51, 51, 51));
        jPanel19.setForeground(java.awt.Color.red);
        jPanel19.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel20.setBackground(new java.awt.Color(51, 51, 51));
        jPanel20.setForeground(java.awt.Color.red);
        jPanel20.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel21.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_View_Details_26px.png"))); // NOI18N
        jLabel21.setText("   Dashboard");
        jPanel20.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 340, -1));

        jPanel19.add(jPanel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, -1, 60));

        jPanel21.setBackground(new java.awt.Color(51, 51, 51));
        jPanel21.setForeground(java.awt.Color.red);
        jPanel21.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel22.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_View_Details_26px.png"))); // NOI18N
        jLabel22.setText("   Dashboard");
        jPanel21.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 340, -1));

        jPanel22.setBackground(new java.awt.Color(51, 51, 51));
        jPanel22.setForeground(java.awt.Color.red);
        jPanel22.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel23.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_View_Details_26px.png"))); // NOI18N
        jLabel23.setText("   Dashboard");
        jPanel22.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 340, -1));

        jPanel21.add(jPanel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, -1, 60));

        jPanel19.add(jPanel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, -1, 60));

        jLabel24.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_View_Details_26px.png"))); // NOI18N
        jLabel24.setText("   Dashboard");
        jPanel19.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 340, -1));

        jPanel15.add(jPanel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, -1, 60));

        getContentPane().add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, -1, 60));

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 80, -1, -1));

        jPanel40.setBackground(new java.awt.Color(255, 255, 255));
        jPanel40.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel41.setBackground(new java.awt.Color(204, 204, 204));
        jPanel41.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 0, 0, 0, java.awt.Color.red));
        jPanel41.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_books.setFont(new java.awt.Font("Geometr415 Blk BT", 0, 50)); // NOI18N
        lbl_books.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_books.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_Book_Shelf_50px.png"))); // NOI18N
        lbl_books.setText(" 10");
        jPanel41.add(lbl_books, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 42, 260, -1));

        jPanel40.add(jPanel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 260, 140));

        jLabel33.setFont(new java.awt.Font("Geometr415 Blk BT", 1, 24)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(102, 102, 102));
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel33.setText("Book Details");
        jPanel40.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 570, 580, -1));

        jLabel43.setFont(new java.awt.Font("Geometr415 Blk BT", 1, 24)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(102, 102, 102));
        jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel43.setText("No. of Students");
        jPanel40.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 50, 260, -1));

        jPanel42.setBackground(new java.awt.Color(204, 204, 204));
        jPanel42.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 0, 0, 0, new java.awt.Color(102, 102, 255)));
        jPanel42.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_students.setFont(new java.awt.Font("Geometr415 Blk BT", 0, 50)); // NOI18N
        lbl_students.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_students.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_People_50px.png"))); // NOI18N
        lbl_students.setText(" 10");
        jPanel42.add(lbl_students, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 42, 260, -1));

        jPanel40.add(jPanel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 90, 260, 140));

        jPanel43.setBackground(new java.awt.Color(204, 204, 204));
        jPanel43.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 0, 0, 0, java.awt.Color.red));
        jPanel43.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_issued.setFont(new java.awt.Font("Geometr415 Blk BT", 0, 50)); // NOI18N
        lbl_issued.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_issued.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_Sell_50px.png"))); // NOI18N
        lbl_issued.setText(" 10");
        jPanel43.add(lbl_issued, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 42, 260, -1));

        jPanel40.add(jPanel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 90, 260, 140));

        jLabel45.setFont(new java.awt.Font("Geometr415 Blk BT", 1, 24)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(102, 102, 102));
        jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel45.setText("Issued Books");
        jPanel40.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 50, 260, -1));

        jLabel46.setFont(new java.awt.Font("Geometr415 Blk BT", 1, 24)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(102, 102, 102));
        jLabel46.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel46.setText("Defaulter List");
        jPanel40.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 50, 260, -1));

        jPanel44.setBackground(new java.awt.Color(204, 204, 204));
        jPanel44.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 0, 0, 0, new java.awt.Color(102, 102, 255)));
        jPanel44.setForeground(new java.awt.Color(102, 102, 255));
        jPanel44.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_defaulters.setFont(new java.awt.Font("Geometr415 Blk BT", 0, 50)); // NOI18N
        lbl_defaulters.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_defaulters.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_List_of_Thumbnails_50px.png"))); // NOI18N
        lbl_defaulters.setText(" 10");
        jPanel44.add(lbl_defaulters, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 42, 260, -1));

        jPanel40.add(jPanel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 90, 260, 140));

        jLabel48.setFont(new java.awt.Font("Geometr415 Blk BT", 1, 24)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(102, 102, 102));
        jLabel48.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel48.setText("No. of Books");
        jPanel40.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 260, -1));

        jLabel49.setFont(new java.awt.Font("Geometr415 Blk BT", 1, 24)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(102, 102, 102));
        jLabel49.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel49.setText("Student Details");
        jPanel40.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 290, 580, -1));

        PanelPieChart.setPreferredSize(new java.awt.Dimension(520, 430));
        PanelPieChart.setLayout(new java.awt.BorderLayout());
        jPanel40.add(PanelPieChart, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 340, 510, 450));

        tbl_studentdetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Student ID", "Name", "Course", "Branch"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
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
        jScrollPane3.setViewportView(tbl_studentdetails);
        if (tbl_studentdetails.getColumnModel().getColumnCount() > 0) {
            tbl_studentdetails.getColumnModel().getColumn(0).setPreferredWidth(40);
            tbl_studentdetails.getColumnModel().getColumn(2).setPreferredWidth(40);
            tbl_studentdetails.getColumnModel().getColumn(3).setPreferredWidth(40);
        }

        jPanel40.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 340, 810, 200));

        tbl_bookdetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Book ID", "Name", "Author", "Quantity"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tbl_bookdetails.setColorBackgoundHead(new java.awt.Color(102, 102, 255));
        tbl_bookdetails.setColorFilasBackgound2(new java.awt.Color(255, 255, 255));
        tbl_bookdetails.setColorFilasForeground1(new java.awt.Color(102, 102, 255));
        tbl_bookdetails.setColorFilasForeground2(new java.awt.Color(102, 102, 255));
        tbl_bookdetails.setColorSelBackgound(new java.awt.Color(255, 102, 102));
        tbl_bookdetails.setFont(new java.awt.Font("Yu Gothic Light", 0, 25)); // NOI18N
        tbl_bookdetails.setFuenteFilas(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        tbl_bookdetails.setRowHeight(35);
        tbl_bookdetails.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_bookdetailsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_bookdetails);
        if (tbl_bookdetails.getColumnModel().getColumnCount() > 0) {
            tbl_bookdetails.getColumnModel().getColumn(0).setPreferredWidth(40);
            tbl_bookdetails.getColumnModel().getColumn(2).setPreferredWidth(40);
            tbl_bookdetails.getColumnModel().getColumn(3).setPreferredWidth(40);
        }

        jPanel40.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 620, 810, 200));

        getContentPane().add(jPanel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 70, 1560, 950));

        setSize(new java.awt.Dimension(1905, 1023));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel39MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel39MouseClicked
        JOptionPane.showMessageDialog(this, "Logging Out");
        //TODOP: Page needs implementation
        //LoginPage logpage=new LoginPage();
        //    logpage.setVisible(true);
        //    dispose();
    }//GEN-LAST:event_jPanel39MouseClicked

    private void jLabel40MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel40MouseClicked
        int ch = JOptionPane.showConfirmDialog(this, "Confirm Log out", "LOGGING OUT", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (ch == JOptionPane.YES_OPTION) {
            LoginPage login = new LoginPage();
            login.setVisible(true);
            dispose();
        }

    }//GEN-LAST:event_jLabel40MouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked

         ManageBooks manage=new ManageBooks();
         manage.setVisible(true);
         this.dispose();
    }//GEN-LAST:event_jLabel6MouseClicked

    private void jLabel6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseEntered
        jPanel5.setBackground(mouseEnter);
    }//GEN-LAST:event_jLabel6MouseEntered

    private void jLabel6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseExited
        jPanel5.setBackground(mouseExit);
    }//GEN-LAST:event_jLabel6MouseExited

    private void jLabel7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseEntered
        jPanel31.setBackground(mouseEnter);
    }//GEN-LAST:event_jLabel7MouseEntered

    private void jLabel7MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseExited
        jPanel31.setBackground(mouseExit);
    }//GEN-LAST:event_jLabel7MouseExited

    private void jLabel20MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel20MouseEntered
        jPanel33.setBackground(mouseEnter);
    }//GEN-LAST:event_jLabel20MouseEntered

    private void jLabel20MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel20MouseExited
        jPanel33.setBackground(mouseExit);
    }//GEN-LAST:event_jLabel20MouseExited

    private void jLabel34MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel34MouseEntered
        jPanel34.setBackground(mouseEnter);
    }//GEN-LAST:event_jLabel34MouseEntered

    private void jLabel34MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel34MouseExited
        jPanel34.setBackground(mouseExit);
    }//GEN-LAST:event_jLabel34MouseExited

    private void jLabel36MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel36MouseEntered
        jPanel36.setBackground(mouseEnter);
    }//GEN-LAST:event_jLabel36MouseEntered

    private void jLabel36MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel36MouseExited
        jPanel36.setBackground(mouseExit);
    }//GEN-LAST:event_jLabel36MouseExited

    private void jLabel37MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel37MouseEntered
        jPanel37.setBackground(mouseEnter);
    }//GEN-LAST:event_jLabel37MouseEntered

    private void jLabel37MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel37MouseExited
        jPanel37.setBackground(mouseExit);
    }//GEN-LAST:event_jLabel37MouseExited

    private void jLabel38MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel38MouseEntered
        jPanel38.setBackground(mouseEnter);
    }//GEN-LAST:event_jLabel38MouseEntered

    private void jLabel38MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel38MouseExited
        jPanel38.setBackground(mouseExit);
    }//GEN-LAST:event_jLabel38MouseExited
    Color logoutentrycolor = new Color(255, 0, 102);
    Color logoutexitcolor = new Color(102, 102, 255);
    private void jLabel40MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel40MouseEntered
        jPanel39.setBackground(logoutentrycolor);
    }//GEN-LAST:event_jLabel40MouseEntered

    private void jPanel39MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel39MouseExited
        jPanel39.setBackground(logoutexitcolor);
    }//GEN-LAST:event_jPanel39MouseExited

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        
        //ManageStudents manage= new ManageStudents();
        //manage.setVisible(true);
        //dispose();
    }//GEN-LAST:event_jLabel7MouseClicked

    private void jLabel20MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel20MouseClicked
        //TODO: uncomment after page implementation pushed
        //IssueBook issue = new IssueBook();
        //issue.setVisible(true);
        //dispose();
    }//GEN-LAST:event_jLabel20MouseClicked

    private void jLabel34MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel34MouseClicked
        //TODO: uncomment after page implementation pushed
        //ReturnBook ret= new ReturnBook();
        //ret.setVisible(true);
        //dispose();
    }//GEN-LAST:event_jLabel34MouseClicked

    private void jLabel36MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel36MouseClicked
        //TODO: uncomment after page implementation pushed
        //ViewRecords view=new ViewRecords();
        //view.setVisible(true);
        //dispose();
    }//GEN-LAST:event_jLabel36MouseClicked

    private void jLabel37MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel37MouseClicked
        //TODO: uncomment after page implementation pushed
        //ViewIssuedBooks viewissued=new ViewIssuedBooks();
        //viewissued.setVisible(true);
        //dispose();
    }//GEN-LAST:event_jLabel37MouseClicked

    private void tbl_studentdetailsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_studentdetailsMouseClicked

    }//GEN-LAST:event_tbl_studentdetailsMouseClicked

    private void tbl_bookdetailsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_bookdetailsMouseClicked

    }//GEN-LAST:event_tbl_bookdetailsMouseClicked

    private void jLabel38MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel38MouseClicked
        //TODO: uncomment after page implementation pushed
        //ViewDefaulters def=new ViewDefaulters();
        //def.setVisible(true);
        //dispose();
    }//GEN-LAST:event_jLabel38MouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jPanel45MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel45MouseClicked

    }//GEN-LAST:event_jPanel45MouseClicked

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
        //TODO: uncomment after page implementation pushed
        //DashBoard dash=new DashBoard();
        //dash.setVisible(true);
        //dispose();

    }//GEN-LAST:event_jLabel11MouseClicked

    private void jLabel11MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseEntered
        jPanel6.setBackground(mouseEnter);
    }//GEN-LAST:event_jLabel11MouseEntered

    private void jLabel11MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseExited
        jPanel6.setBackground(mouseExit);
    }//GEN-LAST:event_jLabel11MouseExited

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
            java.util.logging.Logger.getLogger(HomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HomePage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelPieChart;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel40;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JPanel jPanel42;
    private javax.swing.JPanel jPanel43;
    private javax.swing.JPanel jPanel44;
    private javax.swing.JPanel jPanel45;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lbl_books;
    private javax.swing.JLabel lbl_defaulters;
    private javax.swing.JLabel lbl_issued;
    private javax.swing.JLabel lbl_students;
    private rojeru_san.complementos.RSTableMetro tbl_bookdetails;
    private rojeru_san.complementos.RSTableMetro tbl_studentdetails;
    // End of variables declaration//GEN-END:variables
}
