/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package jframe;

import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;
import java.sql.SQLException;


/**
 *
 * @author thermal
 */
public class ManageBooks extends javax.swing.JFrame {
    
    String bookName, author;
    int bookId, quantity;
    DefaultTableModel model;
    
    private String selectedGenre;

    /**
     * Creates new form ManageBooks
     */
    public ManageBooks() {
        initComponents();
        loadGenresIntoCombo();
        setBookDetailsToTable();
    }
    
    private void loadGenresIntoCombo() {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(
               "SELECT genre_name FROM genre ORDER BY genre_name"
             );
             ResultSet rs = pst.executeQuery())
        {
            jComboBox1.removeAllItems();
            while (rs.next()) {
                jComboBox1.addItem(rs.getString("genre_name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
              "Could not load genres:\n" + e.getMessage(),
              "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // inserting books in book table
    public void setBookDetailsToTable() {
        model = (DefaultTableModel) tbl_bookDetails.getModel();
        model.setRowCount(0);
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(
               "SELECT book_id, book_name, author, quantity, genre FROM book_details"
             );
             ResultSet rs = pst.executeQuery())
        {
            while (rs.next()) {
                Object[] row = {
                  rs.getInt("book_id"),
                  rs.getString("book_name"),
                  rs.getString("author"),
                  rs.getInt("quantity"),
                  rs.getString("genre")
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //to add books to book details table
    public boolean addBook() {
        boolean isAdded = false;
        bookId = Integer.parseInt(txt_bookId.getText());
        bookName = txt_bookName.getText();
        author = txt_authorName.getText();
        quantity = Integer.parseInt(txt_quantity.getText());
        String genre    = jComboBox1.getSelectedItem().toString();
        
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(
               "INSERT INTO book_details(book_id, book_name, author, quantity, genre) VALUES (?,?,?,?,?)"
             ))
        {
            pst.setInt(1, bookId);
            pst.setString(2, bookName);
            pst.setString(3, author);
            pst.setInt(4, quantity);
            pst.setString(5, selectedGenre);
            isAdded = pst.executeUpdate() > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
              "Error adding book:\n" + e.getMessage(),
              "Error", JOptionPane.ERROR_MESSAGE);
        }
        return isAdded;
    }
//    public boolean addBook()
//    {
//        boolean isAdded=false;
//        bookId=Integer.parseInt(txt_bookId.getText());
//        bookName=txt_bookName.getText();
//        author=txt_authorName.getText();
//        quantity=Integer.parseInt(txt_quantity.getText());
//        try
//        {
//            Connection con=DBConnection.getConnection();
//            PreparedStatement pst=con.prepareStatement("insert into book_details values(?,?,?,?)");
//            pst.setInt(1, bookId);
//            pst.setString(2,bookName);
//            pst.setString(3, author);
//            pst.setInt(4, quantity);
//            int rowcount=pst.executeUpdate();
//            if(rowcount>0)
//                isAdded=true;
//        }
//        catch(Exception e)
//        {
//            e.printStackTrace();
//        }
//        return isAdded;
//    }
    
    //to update book details
    public boolean updateBook() {
        boolean isUpdated = false;
        bookId = Integer.parseInt(txt_bookId.getText());
        bookName = txt_bookName.getText();
        author = txt_authorName.getText();
        quantity = Integer.parseInt(txt_quantity.getText());
        String genre    = jComboBox1.getSelectedItem().toString();
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(
               "UPDATE book_details "
             + "SET book_name=?, author=?, quantity=?, genre=? "
             + "WHERE book_id=?"
             ))
        {
            pst.setString(1, bookName);
            pst.setString(2, author);
            pst.setInt(3, quantity);
            pst.setString(4, selectedGenre);
            pst.setInt(5, bookId);
            isUpdated = pst.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isUpdated;
    }

    
    //method to delete book
    public boolean deleteBook()
    {
        boolean isDeleted=false;
        bookId=Integer.parseInt(txt_bookId.getText());
        try
        {
            Connection con=DBConnection.getConnection();
            PreparedStatement pst=con.prepareStatement("Delete from book_details where book_id=?");
            pst.setInt(1, bookId);
            int rowcount=pst.executeUpdate();
            if(rowcount>0)
            {
                isDeleted=true;
            }else
            {
                isDeleted=false;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return isDeleted;
    }
    
    //method to clear table
    public void clearTable()
    {
        DefaultTableModel model=(DefaultTableModel)tbl_bookDetails.getModel();
        model.setRowCount(0);
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
        jLabel11 = new javax.swing.JLabel();
        txt_bookId = new app.bolivia.swing.JCTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txt_bookName = new app.bolivia.swing.JCTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_authorName = new app.bolivia.swing.JCTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt_quantity = new app.bolivia.swing.JCTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        rSMaterialButtonCircle1 = new rojerusan.RSMaterialButtonCircle();
        rSMaterialButtonCircle2 = new rojerusan.RSMaterialButtonCircle();
        rSMaterialButtonCircle3 = new rojerusan.RSMaterialButtonCircle();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        txt_bookId1 = new app.bolivia.swing.JCTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_bookName1 = new app.bolivia.swing.JCTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txt_authorName1 = new app.bolivia.swing.JCTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txt_quantity1 = new app.bolivia.swing.JCTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        rSMaterialButtonCircle4 = new rojerusan.RSMaterialButtonCircle();
        rSMaterialButtonCircle5 = new rojerusan.RSMaterialButtonCircle();
        rSMaterialButtonCircle6 = new rojerusan.RSMaterialButtonCircle();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_bookDetails = new rojeru_san.complementos.RSTableMetro();
        jLabel1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(236, 221, 174));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(85, 101, 107));

        jLabel11.setFont(new java.awt.Font("Te X Gyre Bonum", 0, 13)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Rewind_48px.png"))); // NOI18N
        jLabel11.setText("Back");
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel11)
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 2, Short.MAX_VALUE)
                .addComponent(jLabel11))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 120, 50));

        txt_bookId.setBackground(new java.awt.Color(171, 120, 120));
        txt_bookId.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        txt_bookId.setPlaceholder("Enter Book ID....");
        txt_bookId.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_bookIdFocusLost(evt);
            }
        });
        jPanel1.add(txt_bookId, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 150, 250, -1));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Contact_26px.png"))); // NOI18N
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 140, 30, 50));

        jLabel2.setFont(new java.awt.Font("Sitka Display", 1, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(94, 68, 71));
        jLabel2.setText("Book ID");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 120, 180, 30));

        txt_bookName.setBackground(new java.awt.Color(171, 120, 120));
        txt_bookName.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        txt_bookName.setPlaceholder("Enter Book Name....");
        txt_bookName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_bookNameFocusLost(evt);
            }
        });
        jPanel1.add(txt_bookName, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 250, 250, -1));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Moleskine_26px.png"))); // NOI18N
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 250, -1, -1));

        jLabel3.setFont(new java.awt.Font("Sitka Display", 1, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(94, 68, 71));
        jLabel3.setText("Book Name");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 220, 180, 30));

        txt_authorName.setBackground(new java.awt.Color(171, 120, 120));
        txt_authorName.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        txt_authorName.setPlaceholder("Enter author name....");
        txt_authorName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_authorNameFocusLost(evt);
            }
        });
        txt_authorName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_authorNameActionPerformed(evt);
            }
        });
        jPanel1.add(txt_authorName, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 350, 250, -1));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Collaborator_Male_26px.png"))); // NOI18N
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 350, -1, -1));

        jLabel4.setFont(new java.awt.Font("Sitka Display", 1, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(94, 68, 71));
        jLabel4.setText("Author Name");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 320, 180, 30));

        txt_quantity.setBackground(new java.awt.Color(171, 120, 120));
        txt_quantity.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        txt_quantity.setPlaceholder("Enter quantity....");
        txt_quantity.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_quantityFocusLost(evt);
            }
        });
        jPanel1.add(txt_quantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 530, 250, -1));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Unit_26px.png"))); // NOI18N
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 530, -1, -1));

        jLabel5.setFont(new java.awt.Font("Sitka Display", 1, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(94, 68, 71));
        jLabel5.setText("Quantity");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 500, 180, 30));

        rSMaterialButtonCircle1.setBackground(new java.awt.Color(85, 101, 107));
        rSMaterialButtonCircle1.setText("DELETE");
        rSMaterialButtonCircle1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonCircle1ActionPerformed(evt);
            }
        });
        jPanel1.add(rSMaterialButtonCircle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 670, 150, 80));

        rSMaterialButtonCircle2.setBackground(new java.awt.Color(85, 101, 107));
        rSMaterialButtonCircle2.setText("ADD");
        rSMaterialButtonCircle2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonCircle2ActionPerformed(evt);
            }
        });
        jPanel1.add(rSMaterialButtonCircle2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 670, 150, 80));

        rSMaterialButtonCircle3.setBackground(new java.awt.Color(85, 101, 107));
        rSMaterialButtonCircle3.setText("UPDATE");
        rSMaterialButtonCircle3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonCircle3ActionPerformed(evt);
            }
        });
        jPanel1.add(rSMaterialButtonCircle3, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 670, 150, 80));

        jPanel6.setBackground(new java.awt.Color(236, 221, 174));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel7.setBackground(new java.awt.Color(85, 101, 107));

        jLabel13.setFont(new java.awt.Font("Te X Gyre Bonum", 0, 13)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Rewind_48px.png"))); // NOI18N
        jLabel13.setText("Back");
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel13)
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(0, 2, Short.MAX_VALUE)
                .addComponent(jLabel13))
        );

        jPanel6.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 120, 50));

        txt_bookId1.setBackground(new java.awt.Color(171, 120, 120));
        txt_bookId1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        txt_bookId1.setPlaceholder("Enter Book ID....");
        txt_bookId1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_bookId1FocusLost(evt);
            }
        });
        jPanel6.add(txt_bookId1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 150, 250, -1));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Contact_26px.png"))); // NOI18N
        jPanel6.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 140, 30, 50));

        jLabel6.setFont(new java.awt.Font("Sitka Display", 1, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(94, 68, 71));
        jLabel6.setText("Book ID");
        jPanel6.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 120, 180, 30));

        txt_bookName1.setBackground(new java.awt.Color(171, 120, 120));
        txt_bookName1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        txt_bookName1.setPlaceholder("Enter Book Name....");
        txt_bookName1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_bookName1FocusLost(evt);
            }
        });
        jPanel6.add(txt_bookName1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 250, 250, -1));

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Moleskine_26px.png"))); // NOI18N
        jPanel6.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 250, -1, -1));

        jLabel16.setFont(new java.awt.Font("Sitka Display", 1, 16)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(94, 68, 71));
        jLabel16.setText("Book Name");
        jPanel6.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 220, 180, 30));

        txt_authorName1.setBackground(new java.awt.Color(171, 120, 120));
        txt_authorName1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        txt_authorName1.setPlaceholder("Enter author name....");
        txt_authorName1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_authorName1FocusLost(evt);
            }
        });
        txt_authorName1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_authorName1ActionPerformed(evt);
            }
        });
        jPanel6.add(txt_authorName1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 350, 250, -1));

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Collaborator_Male_26px.png"))); // NOI18N
        jPanel6.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 350, -1, -1));

        jLabel18.setFont(new java.awt.Font("Sitka Display", 1, 16)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(94, 68, 71));
        jLabel18.setText("Author Name");
        jPanel6.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 320, 180, 30));

        txt_quantity1.setBackground(new java.awt.Color(171, 120, 120));
        txt_quantity1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        txt_quantity1.setPlaceholder("Enter quantity....");
        txt_quantity1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_quantity1FocusLost(evt);
            }
        });
        jPanel6.add(txt_quantity1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 530, 250, -1));

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Unit_26px.png"))); // NOI18N
        jPanel6.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 530, -1, -1));

        jLabel20.setFont(new java.awt.Font("Sitka Display", 1, 16)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(94, 68, 71));
        jLabel20.setText("Quantity");
        jPanel6.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 500, 180, 30));

        rSMaterialButtonCircle4.setBackground(new java.awt.Color(85, 101, 107));
        rSMaterialButtonCircle4.setText("DELETE");
        rSMaterialButtonCircle4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonCircle4ActionPerformed(evt);
            }
        });
        jPanel6.add(rSMaterialButtonCircle4, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 670, 150, 80));

        rSMaterialButtonCircle5.setBackground(new java.awt.Color(85, 101, 107));
        rSMaterialButtonCircle5.setText("ADD");
        rSMaterialButtonCircle5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonCircle5ActionPerformed(evt);
            }
        });
        jPanel6.add(rSMaterialButtonCircle5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 670, 150, 80));

        rSMaterialButtonCircle6.setBackground(new java.awt.Color(85, 101, 107));
        rSMaterialButtonCircle6.setText("UPDATE");
        rSMaterialButtonCircle6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonCircle6ActionPerformed(evt);
            }
        });
        jPanel6.add(rSMaterialButtonCircle6, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 670, 150, 80));

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Collaborator_Male_26px.png"))); // NOI18N
        jPanel6.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 440, -1, -1));

        jLabel22.setFont(new java.awt.Font("Sitka Display", 1, 16)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(94, 68, 71));
        jLabel22.setText("Author Name");
        jPanel6.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 410, 180, 30));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        jPanel6.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 440, 250, 30));

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 580, 830));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 580, 830));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(85, 101, 107));

        jLabel12.setFont(new java.awt.Font("Te X Gyre Bonum", 0, 35)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("X");
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel12MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addComponent(jLabel12)
                .addGap(26, 26, 26))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel12)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 0, 80, 50));

        tbl_bookDetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Book ID", "Name", "Author", "Quantity", "Genre"
            }
        ));
        tbl_bookDetails.setColorBackgoundHead(new java.awt.Color(171, 120, 120));
        tbl_bookDetails.setColorFilasBackgound2(new java.awt.Color(255, 255, 255));
        tbl_bookDetails.setRowHeight(40);
        tbl_bookDetails.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_bookDetailsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_bookDetails);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 220, 870, 320));

        jLabel1.setFont(new java.awt.Font("Bitstream Charter", 0, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(171, 120, 120));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_Book_26px.png"))); // NOI18N
        jLabel1.setText("Manage Books");
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 60, 270, -1));

        jPanel5.setBackground(new java.awt.Color(171, 120, 120));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 540, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        jPanel3.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 120, 540, 5));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 0, 1140, 820));

        setSize(new java.awt.Dimension(1724, 824));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txt_bookIdFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_bookIdFocusLost
        
    }//GEN-LAST:event_txt_bookIdFocusLost

    private void txt_bookNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_bookNameFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_bookNameFocusLost

    private void txt_authorNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_authorNameFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_authorNameFocusLost

    private void txt_quantityFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_quantityFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_quantityFocusLost

    private void txt_authorNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_authorNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_authorNameActionPerformed

    private void rSMaterialButtonCircle2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle2ActionPerformed
        if(addBook()==true)
        {
            JOptionPane.showMessageDialog(this, "Book Added");
            clearTable();
            setBookDetailsToTable();
        }
        else{
            JOptionPane.showMessageDialog(this, "Book Addition Failed");
        }
    }//GEN-LAST:event_rSMaterialButtonCircle2ActionPerformed

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
        HomePage home = new HomePage();
        home.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel11MouseClicked

    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel12MouseClicked

    private void tbl_bookDetailsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_bookDetailsMouseClicked
        int rowNo = tbl_bookDetails.getSelectedRow();
        TableModel model = tbl_bookDetails.getModel();
        
        txt_bookId.setText(model.getValueAt(rowNo, 0).toString());
        txt_bookName.setText(model.getValueAt(rowNo, 1).toString());
        txt_authorName.setText(model.getValueAt(rowNo, 2).toString());
        txt_quantity.setText(model.getValueAt(rowNo, 3).toString());
        
    }//GEN-LAST:event_tbl_bookDetailsMouseClicked

    private void rSMaterialButtonCircle3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle3ActionPerformed
         if(updateBook()==true)
        {
            JOptionPane.showMessageDialog(this, "Book Updated");
            clearTable();
            setBookDetailsToTable();
        }
        else{
            JOptionPane.showMessageDialog(this, "Book Updation Failed");
        }
    }//GEN-LAST:event_rSMaterialButtonCircle3ActionPerformed

    private void rSMaterialButtonCircle1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle1ActionPerformed
         if(deleteBook()==true)
        {
            JOptionPane.showMessageDialog(this, "Book Deleted");
            clearTable();
            setBookDetailsToTable();
        }
        else{
            JOptionPane.showMessageDialog(this, "Book Deletion Failed");
        }
    }//GEN-LAST:event_rSMaterialButtonCircle1ActionPerformed

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel13MouseClicked

    private void txt_bookId1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_bookId1FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_bookId1FocusLost

    private void txt_bookName1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_bookName1FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_bookName1FocusLost

    private void txt_authorName1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_authorName1FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_authorName1FocusLost

    private void txt_authorName1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_authorName1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_authorName1ActionPerformed

    private void txt_quantity1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_quantity1FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_quantity1FocusLost

    private void rSMaterialButtonCircle4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rSMaterialButtonCircle4ActionPerformed

    private void rSMaterialButtonCircle5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rSMaterialButtonCircle5ActionPerformed

    private void rSMaterialButtonCircle6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rSMaterialButtonCircle6ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        Object sel = jComboBox1.getSelectedItem();
        if (sel != null) {
            selectedGenre = sel.toString();
        }
    }//GEN-LAST:event_jComboBox1ActionPerformed

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
            java.util.logging.Logger.getLogger(ManageBooks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManageBooks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManageBooks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManageBooks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManageBooks().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> jComboBox1;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private rojerusan.RSMaterialButtonCircle rSMaterialButtonCircle1;
    private rojerusan.RSMaterialButtonCircle rSMaterialButtonCircle2;
    private rojerusan.RSMaterialButtonCircle rSMaterialButtonCircle3;
    private rojerusan.RSMaterialButtonCircle rSMaterialButtonCircle4;
    private rojerusan.RSMaterialButtonCircle rSMaterialButtonCircle5;
    private rojerusan.RSMaterialButtonCircle rSMaterialButtonCircle6;
    private rojeru_san.complementos.RSTableMetro tbl_bookDetails;
    private app.bolivia.swing.JCTextField txt_authorName;
    private app.bolivia.swing.JCTextField txt_authorName1;
    private app.bolivia.swing.JCTextField txt_bookId;
    private app.bolivia.swing.JCTextField txt_bookId1;
    private app.bolivia.swing.JCTextField txt_bookName;
    private app.bolivia.swing.JCTextField txt_bookName1;
    private app.bolivia.swing.JCTextField txt_quantity;
    private app.bolivia.swing.JCTextField txt_quantity1;
    // End of variables declaration//GEN-END:variables
}
