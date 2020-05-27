package Garage;
import java.sql.*;
import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Spares extends javax.swing.JFrame {
    private Connection con = null;
    private ResultSet rs = null;
    private PreparedStatement pst = null;
    DefaultTableModel model=new DefaultTableModel();
    Object[]data=new Object[0];
    
    public Spares() {
        initComponents();
        con = Connect.connectDB();
        model=(DefaultTableModel)tbspa.getModel();
        showSpares();    
    }
    
    public void showSpares(){
        clearRow();
        String sql = "Select * from spares";
        try{
            Connection c=Connect.connectDB();//เชื่อมต่อกับฐานข้อมูล
            ResultSet rs=c.createStatement().executeQuery(sql);
            int row=0;//แถว
            while(rs.next()){
                model.addRow(data);//เพิ่มแถว
                model.setValueAt(rs.getString(1), row,0);//รหัสอะไหล่
                model.setValueAt(rs.getString(2), row,1);//ชื่ออะไหล่
                model.setValueAt(rs.getString(3), row,2);//ราคาอะไหล่
                model.setValueAt(rs.getString(4), row,3);//จำนวนที่เหลือ
                row++;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
     }
    
    public void clearTextBox(){
        txt1.setText("");
        txt2.setText("");
        txt3.setText("");
        txt4.setText("");        
    }
    
    public void clearRow(){//การลบแถวทั้งหมด
         int row=model.getRowCount()-1;
         while (row>-1){
             model.removeRow(row);
             row--;
         }
    }
    
    private void clickTable() {
        try {
            int row = tbspa.getSelectedRow();
            String selectId = tbspa.getValueAt(row, 0).toString();
            String sql = "select * from spares where Spa_id = '" + selectId + "' ";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                String add1 = rs.getString("Spa_id");
                txt1.setText(add1);
                String add2 = rs.getString("Spa_name");
                txt2.setText(add2);
                String add3 = rs.getString("Spa_price");
                txt3.setText(add3);
                String add4 = rs.getString("Spa_amount");
                txt4.setText(add4);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void insertData(){//การบันทึกข้อมูล
        clearRow();
        try{
            String sql="insert into spares(Spa_id,Spa_name,Spa_price,Spa_amount)value(?,?,?,?)";
            pst=con.prepareStatement(sql);
            pst.setString(1,txt1.getText());
            pst.setString(2,txt2.getText());
            pst.setString(3,txt3.getText());
            pst.setString(4,txt4.getText());
            pst.execute();
            JOptionPane.showMessageDialog(this, "บันทึกเรียบร้อย" ,"แจ้งเตือน", JOptionPane.INFORMATION_MESSAGE);
            clearTextBox();
            showSpares();
            
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    private void updateData() {
        try {
            String sql = "update spares set Spa_id= ? ,"
                + "Spa_name= ? ,Spa_price= ? ,"
                + "Spa_amount= ? "
                + "where Spa_id"
                + "= '" + txt1.getText() + "'";
            pst = con.prepareStatement(sql);
            pst.setString(1, txt1.getText());
            pst.setString(2, txt2.getText());
            pst.setString(3, txt3.getText());
            pst.setString(4, txt4.getText());
            pst.execute();
            JOptionPane.showMessageDialog(this, "แก้ไข " + txt1.getText(), "แจ้งเตือน", JOptionPane.INFORMATION_MESSAGE);
            clearTextBox();
            showSpares();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
     
     private void deleteData() {
        try {
            String id_delete = txt1.getText();
            String sql = "DELETE FROM spares WHERE Spa_id = '" + id_delete + "'";
            pst = con.prepareStatement(sql);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "ลบ " + txt1.getText(), "แจ้งเตือน", JOptionPane.INFORMATION_MESSAGE);
            clearTextBox();
            showSpares();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "ลบข้อมูลไม่สำเร็จ", JOptionPane.WARNING_MESSAGE);
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
        txt3 = new javax.swing.JTextField();
        update = new javax.swing.JButton();
        Insert = new javax.swing.JButton();
        clear = new javax.swing.JButton();
        delete = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbspa = new javax.swing.JTable();
        txt4 = new javax.swing.JTextField();
        manu = new javax.swing.JButton();
        next = new javax.swing.JButton();
        back = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("รายการอะไหล่");
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        jLabel1.setText("รหัสอะไหล่ :");

        jLabel2.setText("ชื่ออะไหล่ :");

        jLabel3.setText("ราคาอะไหล่ :");

        jLabel4.setText("จำนวนอะไหล่ :");

        txt1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt1FocusLost(evt);
            }
        });
        txt1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt1KeyReleased(evt);
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

        txt3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt3FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt3FocusLost(evt);
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
        tbspa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbspaMouseClicked(evt);
            }
        });
        tbspa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbspaKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tbspa);

        txt4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt4FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt4FocusLost(evt);
            }
        });

        manu.setText("เมนู");
        manu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manuActionPerformed(evt);
            }
        });

        next.setText("ถัดไป");
        next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextActionPerformed(evt);
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(back)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(next, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txt1)
                                    .addComponent(txt2, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                                    .addComponent(txt3, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                                    .addComponent(txt4)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(delete, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Insert))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(update, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(clear, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(26, 26, 26)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(134, 134, 134)
                        .addComponent(manu)))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txt2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txt3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txt4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(update)
                    .addComponent(Insert))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(clear)
                    .addComponent(delete))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(manu)
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(back)
                    .addComponent(next))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed
        updateData();
    }//GEN-LAST:event_updateActionPerformed

    private void txt1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt1KeyReleased
        try{
            String sql ="select * from spares";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){
                String id = rs.getString("Spa_id");
                if(id.equalsIgnoreCase(txt1.getText())){
                    JOptionPane.showMessageDialog(this,"รหัสซ้ำ","แจ้งเตือน",JOptionPane.WARNING_MESSAGE);
                    clearTextBox();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_txt1KeyReleased

    private void InsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InsertActionPerformed
        boolean checkData=txt1.getText().isEmpty()||
                          txt2.getText().isEmpty()||
                          txt3.getText().isEmpty()||
                          txt4.getText().isEmpty();
        if(checkData){
            JOptionPane.showMessageDialog(this,"ใส่ข้อมูลไม่ครบ","คำเตือน",JOptionPane.WARNING_MESSAGE);
        }else{
            insertData();
        }
    }//GEN-LAST:event_InsertActionPerformed

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
        deleteData();
    }//GEN-LAST:event_deleteActionPerformed

    private void clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearActionPerformed
        clearTextBox();
    }//GEN-LAST:event_clearActionPerformed

    private void tbspaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbspaMouseClicked
        int select=tbspa.getSelectedRow();
        txt1.setText(tbspa.getValueAt(select, 0).toString());
        txt2.setText(tbspa.getValueAt(select, 1).toString());
        txt3.setText(tbspa.getValueAt(select, 2).toString());
        txt4.setText(tbspa.getValueAt(select, 3).toString());
        
        txt1.setEnabled(false);
        
        update.setEnabled(true);
        delete.setEnabled(true);
    }//GEN-LAST:event_tbspaMouseClicked

    private void tbspaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbspaKeyReleased
        int select=tbspa.getSelectedRow();
        txt1.setText(tbspa.getValueAt(select, 0).toString());
        txt2.setText(tbspa.getValueAt(select, 1).toString());
        txt3.setText(tbspa.getValueAt(select, 2).toString());
        txt4.setText(tbspa.getValueAt(select, 3).toString());
        
        txt1.setEnabled(false);
        
        update.setEnabled(true);
    }//GEN-LAST:event_tbspaKeyReleased

    private void txt1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt1FocusGained
        txt1.setBackground(Color.yellow);
    }//GEN-LAST:event_txt1FocusGained

    private void txt2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt2FocusGained
        txt2.setBackground(Color.yellow);
    }//GEN-LAST:event_txt2FocusGained

    private void txt3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt3FocusGained
        txt3.setBackground(Color.yellow);
    }//GEN-LAST:event_txt3FocusGained

    private void txt4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt4FocusGained
        txt4.setBackground(Color.yellow);
    }//GEN-LAST:event_txt4FocusGained

    private void txt1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt1FocusLost
        txt1.setBackground(Color.WHITE);
    }//GEN-LAST:event_txt1FocusLost

    private void txt2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt2FocusLost
        txt2.setBackground(Color.WHITE);
    }//GEN-LAST:event_txt2FocusLost

    private void txt3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt3FocusLost
        txt3.setBackground(Color.WHITE);
    }//GEN-LAST:event_txt3FocusLost

    private void txt4FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt4FocusLost
        txt4.setBackground(Color.WHITE);
    }//GEN-LAST:event_txt4FocusLost

    private void manuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manuActionPerformed
        Main main = new Main();
        main.setVisible(true);
        
        setVisible(false);
    }//GEN-LAST:event_manuActionPerformed

    private void nextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextActionPerformed
        Use_List_Spare use = new Use_List_Spare();
        use.setVisible(true);
        
        setVisible(false);
    }//GEN-LAST:event_nextActionPerformed

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        txt1.setEnabled(true);
    }//GEN-LAST:event_formMouseClicked

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        ListRepair lis = new ListRepair();
        lis.setVisible(true);
        
        setVisible(false);
    }//GEN-LAST:event_backActionPerformed

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
            java.util.logging.Logger.getLogger(Spares.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Spares.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Spares.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Spares.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Spares().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Insert;
    private javax.swing.JButton back;
    private javax.swing.JButton clear;
    private javax.swing.JButton delete;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton manu;
    private javax.swing.JButton next;
    private javax.swing.JTable tbspa;
    private javax.swing.JTextField txt1;
    private javax.swing.JTextField txt2;
    private javax.swing.JTextField txt3;
    private javax.swing.JTextField txt4;
    private javax.swing.JButton update;
    // End of variables declaration//GEN-END:variables
}
