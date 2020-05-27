package Garage;
import java.sql.*;
import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;

public class Car extends javax.swing.JFrame {
    private Connection con = null;
    private ResultSet rs = null;
    private PreparedStatement pst = null;
    DefaultTableModel model=new DefaultTableModel();
    Object[]data=new Object[0];
    
    public Car() {
        initComponents();
        con = Connect.connectDB();
        model=(DefaultTableModel)tbcar.getModel();
        showCar(); 
        getCombox();     
    }
    
    private void getCombox() {
        try {
            String sql1 = "SELECT * FROM customer";
            PreparedStatement pst1 = con.prepareStatement(sql1);
            ResultSet rs1 = pst1.executeQuery();
            while (rs1.next()) {
                String id1 = rs1.getString("Cus_id");
                Combocus.addItem(id1);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error!", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public void showCar(){
        clearRow();
        String sql = "Select * from car";
        try{
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            tbcar.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void insertData(){//การบันทึกข้อมูล
        clearRow();
        try{
            String sql="insert into car(Car_registration,Cus_id,Car_brand,Car_model,Car_color,Car_gear)value(?,?,?,?,?,?)";
            pst=con.prepareStatement(sql);
            pst.setString(1,txt1.getText());
            pst.setString(2,Combocus.getSelectedItem().toString());
            pst.setString(3,txt3.getText());
            pst.setString(4,txt4.getText());
            pst.setString(5,txt5.getText());
            pst.setString(6,txt6.getText());
            pst.execute();
            JOptionPane.showMessageDialog(this, "บันทึกเรียบร้อย" ,"แจ้งเตือน", JOptionPane.INFORMATION_MESSAGE);
            clearTextBox();
            showCar();
            
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
     public void clearTextBox(){
        txt1.setText("");
        Combocus.setSelectedIndex(0);
        txt3.setText("");
        txt4.setText("");
        txt5.setText("");
        txt6.setText("");
        search.setText("");
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
            int row = tbcar.getSelectedRow();
            String selectId = tbcar.getValueAt(row, 0).toString();
            String sql = "select * from car where Car_registration = '" + selectId + "' ";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                String add1 = rs.getString("Car_registration");
                txt1.setText(add1);
                String add2 = rs.getString("Cus_id");
                Combocus.setSelectedItem(add2);
                String add3 = rs.getString("Car_brand");
                txt3.setText(add3);
                String add4 = rs.getString("Car_model");
                txt4.setText(add4);
                String add5 = rs.getString("Car_color");
                txt5.setText(add5);
                String add6 = rs.getString("Car_gear");
                txt6.setText(add6);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    private void updateData() {
        try {
            String sql = "update car set Car_registration= ? ,"
                + "Cus_id= ? ,Car_brand= ? ,"
                + "Car_model= ? ,Car_color= ? , Car_gear= ? "
                + "where Car_registration"
                + "= '" + txt1.getText() + "'";
            pst = con.prepareStatement(sql);
            pst.setString(1, txt1.getText());
            pst.setString(2, Combocus.getSelectedItem().toString());
            pst.setString(3, txt3.getText());
            pst.setString(4, txt4.getText());
            pst.setString(5, txt5.getText());
            pst.setString(6, txt6.getText());
            pst.execute();
            JOptionPane.showMessageDialog(this, "แก้ไข " + txt1.getText(), "แจ้งเตือน", JOptionPane.INFORMATION_MESSAGE);
            clearTextBox();
            showCar();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
     
     private void deleteData() {
        try {
            String id_delete = txt1.getText();
            String sql = "DELETE FROM car WHERE Car_registration = '" + id_delete + "'";
            pst = con.prepareStatement(sql);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "ลบ : " + txt1.getText(), "แจ้งเตือน", JOptionPane.INFORMATION_MESSAGE);
            clearTextBox();
            showCar();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "ลบข้อมูลไม่สำเร็จ", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tbcar = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt1 = new javax.swing.JTextField();
        txt3 = new javax.swing.JTextField();
        Combocus = new javax.swing.JComboBox<>();
        txt4 = new javax.swing.JTextField();
        txt5 = new javax.swing.JTextField();
        txt6 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        search = new javax.swing.JTextField();
        Insert = new javax.swing.JButton();
        update = new javax.swing.JButton();
        delete = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        next = new javax.swing.JButton();
        back = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ข้อมูลรถยนต์ลูกค้า");
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        tbcar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ทะเบียนรถ", "รหัสลูกค้า", "รุ่น", "ยี่ห้อ", "สี", "ระบบเกียร์"
            }
        ));
        tbcar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbcarMouseClicked(evt);
            }
        });
        tbcar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbcarKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tbcar);

        jLabel1.setText("ทะเบียนรถ :");

        jLabel2.setText(" รหัสลูกค้า :");

        jLabel3.setText("รุ่น :");

        jLabel4.setText("ยี่ห้อ :");

        jLabel5.setText("สี :");

        jLabel6.setText("ระบบเกียร์ :");

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

        txt3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt3FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt3FocusLost(evt);
            }
        });

        txt4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt4FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt4FocusLost(evt);
            }
        });

        txt5.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt5FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt5FocusLost(evt);
            }
        });

        txt6.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt6FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt6FocusLost(evt);
            }
        });

        jLabel7.setText("ค้นหา :");

        search.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                searchFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                searchFocusLost(evt);
            }
        });
        search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchKeyReleased(evt);
            }
        });

        Insert.setText("บันทึก");
        Insert.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                InsertMouseClicked(evt);
            }
        });
        Insert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InsertActionPerformed(evt);
            }
        });

        update.setText("แก้ไข");
        update.setEnabled(false);
        update.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                updateMouseClicked(evt);
            }
        });
        update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionPerformed(evt);
            }
        });

        delete.setText("ลบ");
        delete.setEnabled(false);
        delete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deleteMouseClicked(evt);
            }
        });
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });

        jButton4.setText("เคลียร์");
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton4MouseClicked(evt);
            }
        });
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("เมนู");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
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
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addComponent(jLabel5)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt1)
                            .addComponent(txt3)
                            .addComponent(Combocus, 0, 120, Short.MAX_VALUE)
                            .addComponent(txt4)
                            .addComponent(txt5)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(txt6))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 55, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Insert, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(delete, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(update, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(196, 196, 196)))
                        .addContainerGap(21, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton5)
                        .addGap(269, 269, 269))))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(back)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(next, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(Combocus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(txt3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txt4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(7, 7, 7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txt5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txt6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(update)
                        .addComponent(Insert)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton4)
                        .addComponent(delete))
                    .addComponent(jButton5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(back)
                    .addComponent(next))
                .addGap(19, 19, 19))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void InsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InsertActionPerformed
        boolean checkData=txt1.getText().isEmpty()||
                Combocus.getSelectedItem().toString().isEmpty()||
                txt3.getText().isEmpty()||
                txt4.getText().isEmpty()||
                txt5.getText().isEmpty()||
                txt6.getText().isEmpty();
                
        if(checkData){
            JOptionPane.showMessageDialog(this,"ใส่ข้อมูลไม่ครบ","คำเตือน",JOptionPane.WARNING_MESSAGE);
        }else{
            insertData();
        }        
        
        clearTextBox();
    }//GEN-LAST:event_InsertActionPerformed

    private void InsertMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_InsertMouseClicked
        insertData();
    }//GEN-LAST:event_InsertMouseClicked

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed
        updateData();
    }//GEN-LAST:event_updateActionPerformed

    private void updateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateMouseClicked
        updateData();
    }//GEN-LAST:event_updateMouseClicked

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
        deleteData();
    }//GEN-LAST:event_deleteActionPerformed

    private void deleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteMouseClicked
        deleteData();
    }//GEN-LAST:event_deleteMouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        clearTextBox();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseClicked
        clearTextBox();
    }//GEN-LAST:event_jButton4MouseClicked

    private void tbcarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbcarMouseClicked
        clickTable();
        
        int select=tbcar.getSelectedRow();
        txt1.setText(tbcar.getValueAt(select, 0).toString());
        Combocus.setSelectedItem(tbcar.getValueAt(select, 1).toString());
        txt3.setText(tbcar.getValueAt(select, 2).toString());
        txt4.setText(tbcar.getValueAt(select, 3).toString());
        txt5.setText(tbcar.getValueAt(select, 4).toString());
        txt6.setText(tbcar.getValueAt(select, 5).toString());
        
        txt1.setEnabled(false);
        
        update.setEnabled(true);
        delete.setEnabled(true);
    }//GEN-LAST:event_tbcarMouseClicked

    private void searchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchFocusGained
        search.setBackground(Color.ORANGE);
    }//GEN-LAST:event_searchFocusGained

    private void searchFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchFocusLost
        search.setBackground(Color.WHITE);
    }//GEN-LAST:event_searchFocusLost

    private void txt1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt1KeyReleased
        try{
            String sql ="select * from car";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){
                String id = rs.getString("Car_registration");
                if(id.equalsIgnoreCase(txt1.getText())){
                    JOptionPane.showMessageDialog(this,"รหัสซ้ำ","แจ้งเตือน",JOptionPane.WARNING_MESSAGE);
                    clearTextBox();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_txt1KeyReleased

    private void searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchKeyReleased
        try{
            if(search.getText().isEmpty()){
                showCar();
            }else{
            String data = search.getText();
            String sql ="SELECT car.Car_registration AS ทะเบียนรถ ,customer.Cus_id AS รหัสลูกค้า,customer.Cus_name AS ชื่อลูกค้า \n" +
                        "FROM car INNER JOIN customer \n" +
                        "ON car.Cus_id=customer.Cus_id \n" +
                        "WHERE customer.Cus_id=?";//แสดงข้อมูลลูกค้ากับรถยนต์
            pst = con.prepareStatement(sql);
            pst.setString(1,data);
            rs = pst.executeQuery();
            tbcar.setModel(DbUtils.resultSetToTableModel(rs));
            System.out.print(sql);
            }    
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }//GEN-LAST:event_searchKeyReleased

    private void tbcarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbcarKeyReleased
        int select=tbcar.getSelectedRow();
        txt1.setText(tbcar.getValueAt(select, 0).toString());
        Combocus.setSelectedItem(tbcar.getValueAt(select, 1).toString());
        txt3.setText(tbcar.getValueAt(select, 2).toString());
        txt4.setText(tbcar.getValueAt(select, 3).toString());
        txt5.setText(tbcar.getValueAt(select, 4).toString());
        txt6.setText(tbcar.getValueAt(select, 5).toString());
        
        txt1.setEnabled(false);
        
        update.setEnabled(true);
    }//GEN-LAST:event_tbcarKeyReleased

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        txt1.setEnabled(true);
    }//GEN-LAST:event_formMouseClicked

    private void txt1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt1FocusGained
        txt1.setBackground(Color.yellow);
    }//GEN-LAST:event_txt1FocusGained

    private void txt3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt3FocusGained
        txt3.setBackground(Color.yellow);
    }//GEN-LAST:event_txt3FocusGained

    private void txt4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt4FocusGained
        txt4.setBackground(Color.yellow);
    }//GEN-LAST:event_txt4FocusGained

    private void txt5FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt5FocusGained
        txt5.setBackground(Color.yellow);
    }//GEN-LAST:event_txt5FocusGained

    private void txt6FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt6FocusGained
        txt6.setBackground(Color.yellow);
    }//GEN-LAST:event_txt6FocusGained

    private void txt1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt1FocusLost
        txt1.setBackground(Color.WHITE);
    }//GEN-LAST:event_txt1FocusLost

    private void txt3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt3FocusLost
        txt3.setBackground(Color.WHITE);
    }//GEN-LAST:event_txt3FocusLost

    private void txt4FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt4FocusLost
        txt4.setBackground(Color.WHITE);
    }//GEN-LAST:event_txt4FocusLost

    private void txt5FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt5FocusLost
        txt5.setBackground(Color.WHITE);
    }//GEN-LAST:event_txt5FocusLost

    private void txt6FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt6FocusLost
        txt6.setBackground(Color.WHITE);
    }//GEN-LAST:event_txt6FocusLost

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        Main main = new Main();
        main.setVisible(true);
        
        setVisible(false);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void nextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextActionPerformed
        Receipt rec = new Receipt();
        rec.setVisible(true);
        
        setVisible(false);
    }//GEN-LAST:event_nextActionPerformed

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        Employee emp = new Employee();
        emp.setVisible(true);
        
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
            java.util.logging.Logger.getLogger(Car.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Car.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Car.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Car.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Car().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> Combocus;
    private javax.swing.JButton Insert;
    private javax.swing.JButton back;
    private javax.swing.JButton delete;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton next;
    private javax.swing.JTextField search;
    private javax.swing.JTable tbcar;
    private javax.swing.JTextField txt1;
    private javax.swing.JTextField txt3;
    private javax.swing.JTextField txt4;
    private javax.swing.JTextField txt5;
    private javax.swing.JTextField txt6;
    private javax.swing.JButton update;
    // End of variables declaration//GEN-END:variables
}
