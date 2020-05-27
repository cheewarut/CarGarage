package Garage;
import java.sql.*;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

public class Use_List_Spare extends javax.swing.JFrame {
    private Connection con = null;
    private ResultSet rs = null;
    private PreparedStatement pst = null;
    
    public Use_List_Spare() {
        con = Connect.connectDB();
        initComponents();
        showUse_List_Spare();
        showListRepair();
        showSpares();
        getCombox();
    }
    
    private void getCombox() {
        try {
            String sql1 = "SELECT * FROM listrepair";
            String sql2 = "SELECT * FROM spares";
            PreparedStatement pst1 = con.prepareStatement(sql1);
            ResultSet rs1 = pst1.executeQuery();
            PreparedStatement pst2 = con.prepareStatement(sql2);
            ResultSet rs2 = pst2.executeQuery();
            while (rs1.next()) {
                String id1 = rs1.getString("Lis_id");
                Combolis.addItem(id1);
            }
            while (rs2.next()) {
                String id2 = rs2.getString("Spa_id");
                Combospa.addItem(id2);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error!", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public void showUse_List_Spare() {
        String sql = "SELECT * FROM use_list_spares";
        try {
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            tbuse.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void showListRepair() {
        String sql = "SELECT Lis_id,Lis_name,Lis_price FROM listrepair";
        try {
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            tblist.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void showSpares() {
        String sql = "SELECT * FROM spares";
        try {
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            tbspa.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void clearTextBox(){
        Combolis.setSelectedIndex(0);
        Combospa.setSelectedIndex(0);
        txt1.setText("");
        txt2.setText("");
    }
    
    private void clickTable() {
        try {
            int row = tbuse.getSelectedRow();
            String selectId1 = tbuse.getValueAt(row, 0).toString();
            String selectId2 = tbuse.getValueAt(row, 1).toString();
            String sql = "select * from use_list_spares where Lis_id = '" + selectId1 + "' "
                    + "AND Spa_id = '" + selectId2 + "' ";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                String add1 = rs.getString("Lis_id");
                Combolis.setSelectedItem(add1);
                String add2 = rs.getString("Spa_id");
                Combospa.setSelectedItem(add2);
                String add3 = rs.getString("Date");
                txt1.setText(add3);
                String add4 = rs.getString("Amount");
                txt2.setText(add4);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    private void insertData() {
        try {
            String sql = "insert into use_list_spares(Lis_id,Spa_id,Date,Amount) "
                    + "values(?,?,?,?)";
            pst = con.prepareStatement(sql);
            pst.setString(1, Combolis.getSelectedItem().toString());
            pst.setString(2, Combospa.getSelectedItem().toString());
            pst.setString(3, txt1.getText());
            pst.setString(4, txt2.getText());
            pst.execute();
            JOptionPane.showMessageDialog(this, "บันทึกเรียบร้อย" ,"แจ้งเตือน", JOptionPane.INFORMATION_MESSAGE);
            clearTextBox();
            showUse_List_Spare();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void updateData() {
        try {
            String sql = "UPDATE use_list_spares SET Lis_id = ? ,Spa_id = ? ,"
                    + "Date = ? ,Amount = ? "
                    + "WHERE Lis_id "
                    + "= '" + Combolis.getSelectedItem().toString() + "' "
                    + "AND Spa_id = '" + Combospa.getSelectedItem().toString() + "'";
            pst = con.prepareStatement(sql);
            pst.setString(1, Combolis.getSelectedItem().toString());
            pst.setString(2, Combospa.getSelectedItem().toString());
            pst.setString(3, txt1.getText());
            pst.setString(4, txt2.getText());
            pst.execute();
            JOptionPane.showMessageDialog(this, "แก้ไข " + txt1.getText(), "แจ้งเตือน", JOptionPane.INFORMATION_MESSAGE);
            clearTextBox();
            showUse_List_Spare();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void deleteData() {
        try {
            String id_delete1 = Combolis.getSelectedItem().toString();
            String id_delete2 = Combospa.getSelectedItem().toString();
            String sql = "DELETE FROM use_list_spares WHERE Lis_id = '" + id_delete1 + "' "
                    + "AND Spa_id = '" + Combospa.getSelectedItem().toString() + "'";
            pst = con.prepareStatement(sql);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "ลบ " + txt1.getText(), "แจ้งเตือน", JOptionPane.INFORMATION_MESSAGE);
            clearTextBox();
            showUse_List_Spare();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt1 = new javax.swing.JTextField();
        txt2 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbuse = new javax.swing.JTable();
        Combospa = new javax.swing.JComboBox<>();
        update = new javax.swing.JButton();
        Insert = new javax.swing.JButton();
        clear = new javax.swing.JButton();
        delete = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbspa = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblist = new javax.swing.JTable();
        manu = new javax.swing.JButton();
        Combolis = new javax.swing.JComboBox<>();
        back = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("รายละเอียด");
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel1.setText("รหัสรายการซ่อม ");

        jLabel2.setText("รหัสอะไหล่ ");

        jLabel3.setText("วันที่เบิกอะไหล่ ");

        jLabel4.setText("จำนวนที่เบิก ");

        txt1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt1FocusLost(evt);
            }
        });

        txt2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt2FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt2FocusLost(evt);
            }
        });
        txt2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt2ActionPerformed(evt);
            }
        });

        tbuse.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "รหัสรายการซ่อม", "รหัสอะไหล่", "วันที่เบิกอะไหล่", "จำนวนที่เบิก"
            }
        ));
        tbuse.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbuseMouseClicked(evt);
            }
        });
        tbuse.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbuseKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tbuse);

        Combospa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                CombospaKeyReleased(evt);
            }
        });

        update.setText("แก้ไข");
        update.setEnabled(false);
        update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionPerformed(evt);
            }
        });

        Insert.setText("บันทึก");
        Insert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InsertActionPerformed(evt);
            }
        });

        clear.setText("เคลียร์");
        clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearActionPerformed(evt);
            }
        });

        delete.setText("ลบ");
        delete.setEnabled(false);
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });

        tbspa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "รหัสอะไหล่", "ชื่ออะไหล่", "ราคาอะไหล่", "จำนวนอะไหล่"
            }
        ));
        jScrollPane2.setViewportView(tbspa);

        tblist.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "รหัสรายการซ่อม", "ชื่อรายการซ่อม", "ราคารายการซ่อม"
            }
        ));
        tblist.setEnabled(false);
        jScrollPane3.setViewportView(tblist);

        manu.setText("เมนู");
        manu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manuActionPerformed(evt);
            }
        });

        Combolis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                CombolisKeyReleased(evt);
            }
        });

        back.setText("ย้อนกลับ");
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addGap(63, 63, 63)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(Insert)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(update, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(delete, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(clear))))
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(70, 70, 70)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(Combospa, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txt1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txt2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(Combolis, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(98, 98, 98)
                                        .addComponent(jLabel4)))
                                .addGap(46, 46, 46))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(manu)
                                        .addGap(92, 92, 92))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(91, 91, 91))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addGap(76, 76, 76))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(81, 81, 81)))))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 680, Short.MAX_VALUE)
                            .addComponent(jScrollPane2)
                            .addComponent(jScrollPane3))
                        .addGap(0, 35, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(back)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(Combolis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(Combospa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(txt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(txt2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Insert)
                            .addComponent(update)))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(clear)
                    .addComponent(delete))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 25, Short.MAX_VALUE)
                        .addComponent(manu)
                        .addGap(183, 183, 183))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(back)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbuseKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbuseKeyReleased
        int select=tbuse.getSelectedRow();
        Combolis.setSelectedItem(tbuse.getValueAt(select, 0).toString());
        Combospa.setSelectedItem(tbuse.getValueAt(select, 1).toString());
        txt1.setText(tbuse.getValueAt(select, 2).toString());
        txt2.setText(tbuse.getValueAt(select, 3).toString());
        
        Combolis.setEnabled(false);
        Combospa.setEnabled(false);
        
        update.setEnabled(true);
    }//GEN-LAST:event_tbuseKeyReleased

    private void tbuseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbuseMouseClicked
        clickTable();
        int select=tbuse.getSelectedRow();
        Combolis.setSelectedItem(tbuse.getValueAt(select, 0).toString());
        Combospa.setSelectedItem(tbuse.getValueAt(select, 1).toString());
        txt1.setText(tbuse.getValueAt(select, 2).toString());
        txt2.setText(tbuse.getValueAt(select, 3).toString());
        
        Combolis.setEnabled(false);
        Combospa.setEnabled(false);
        
        update.setEnabled(true);
        delete.setEnabled(true);
    }//GEN-LAST:event_tbuseMouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        Calendar c= Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        txt1.setText(df.format(c.getTime()));
    }//GEN-LAST:event_formWindowOpened

    private void CombolisKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CombolisKeyReleased
        try{
            String sql ="select * from use_list_spares";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){
                String id = rs.getString("Lis_id");
                if(id.equalsIgnoreCase(Combolis.getSelectedItem().toString())){
                    JOptionPane.showMessageDialog(this,"รหัสซ้ำ","แจ้งเตือน",JOptionPane.WARNING_MESSAGE);
                    clearTextBox();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_CombolisKeyReleased

    private void CombospaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CombospaKeyReleased
        try{
            String sql ="select * from use_list_spares";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){
                String id = rs.getString("Spa_id");
                if(id.equalsIgnoreCase(Combospa.getSelectedItem().toString())){
                    JOptionPane.showMessageDialog(this,"รหัสซ้ำ","แจ้งเตือน",JOptionPane.WARNING_MESSAGE);
                    clearTextBox();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_CombospaKeyReleased

    private void InsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InsertActionPerformed
        boolean checkData=Combolis.getSelectedItem().toString().isEmpty()||
                          Combospa.getSelectedItem().toString().isEmpty()||
                          txt1.getText().isEmpty()||
                          txt2.getText().isEmpty();
                          
        if(checkData){
            JOptionPane.showMessageDialog(this,"ใส่ข้อมูลไม่ครบ","คำเตือน",JOptionPane.WARNING_MESSAGE);
        }else{
            insertData();
        }        
        
        clearTextBox();
    }//GEN-LAST:event_InsertActionPerformed

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed
        updateData();
    }//GEN-LAST:event_updateActionPerformed

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
        deleteData();
    }//GEN-LAST:event_deleteActionPerformed

    private void clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearActionPerformed
        clearTextBox();
    }//GEN-LAST:event_clearActionPerformed

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        Combolis.setEnabled(true);
        Combospa.setEnabled(true);
    }//GEN-LAST:event_formMouseClicked

    private void txt1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt1FocusGained
        txt1.setBackground(Color.yellow);
    }//GEN-LAST:event_txt1FocusGained

    private void txt2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt2FocusGained
        txt2.setBackground(Color.yellow);
    }//GEN-LAST:event_txt2FocusGained

    private void txt1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt1FocusLost
        txt1.setBackground(Color.WHITE);
    }//GEN-LAST:event_txt1FocusLost

    private void txt2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt2FocusLost
        txt2.setBackground(Color.WHITE);
    }//GEN-LAST:event_txt2FocusLost

    private void manuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manuActionPerformed
        Main main = new Main();
        main.setVisible(true);
        
        setVisible(false);
    }//GEN-LAST:event_manuActionPerformed

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        Spares spa = new Spares();
        spa.setVisible(true);
        
        setVisible(false);
    }//GEN-LAST:event_backActionPerformed

    private void txt2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt2ActionPerformed
        /* select * from spares where Spa_amount = ? */
    }//GEN-LAST:event_txt2ActionPerformed

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
            java.util.logging.Logger.getLogger(Use_List_Spare.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Use_List_Spare.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Use_List_Spare.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Use_List_Spare.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Use_List_Spare().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> Combolis;
    private javax.swing.JComboBox<String> Combospa;
    private javax.swing.JButton Insert;
    private javax.swing.JButton back;
    private javax.swing.JButton clear;
    private javax.swing.JButton delete;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton manu;
    private javax.swing.JTable tblist;
    private javax.swing.JTable tbspa;
    private javax.swing.JTable tbuse;
    private javax.swing.JTextField txt1;
    private javax.swing.JTextField txt2;
    private javax.swing.JButton update;
    // End of variables declaration//GEN-END:variables
}
