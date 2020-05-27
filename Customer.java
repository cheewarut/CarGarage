package Garage;
import java.sql.*;
import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;

public class Customer extends javax.swing.JFrame {
    private Connection con = null;
    private ResultSet rs = null;
    private PreparedStatement pst = null;
    DefaultTableModel model=new DefaultTableModel();
    Object[]data=new Object[0];
    
    public Customer() {
        initComponents();
        con = Connect.connectDB();
        model=(DefaultTableModel)tbcus.getModel();
        showCustomer();
    }
    
    public void showCustomer(){
        clearRow();
        String sql = "Select * from customer";
        try{
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            tbcus.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void insertData(){//การบันทึกข้อมูล
        String cusid = txt1.getText();
        String cusidcard = txt2.getText();
        String cusname = txt3.getText();
        String cusaddress = txt4.getText();
        String cusphone = txt5.getText();
        //คำสั่ง sql
        String sql="insert into customer value('"+cusid+"','"+cusidcard+"','"+cusname+"','"+cusaddress+"','"+cusphone+"')";
        
        try{
            Connection c=Connect.connectDB();
            Statement stm=c.createStatement();
            if(stm.executeUpdate(sql)!=-1){
                JOptionPane.showMessageDialog(this,"บันทึกข้อมูลเรียบร้อย","แจ้งเตือน", JOptionPane.INFORMATION_MESSAGE);
                clearTextBox();
                showCustomer();
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void clearTextBox(){
        txt1.setText("");
        txt2.setText("");
        txt3.setText("");
        txt4.setText("");  
        txt5.setText(""); 
        txt1.setEnabled(true);
    }
    
    public void clearRow(){//การลบแถวทั้งหมด
         int row=model.getRowCount()-1;
         while (row>-1){
             model.removeRow(row);
             row--;
         }
    }
    
    public void updateData(){
        String cusid = txt1.getText();
        String cusidcard = txt2.getText();
        String cusname = txt3.getText();
        String cusaddress = txt4.getText();
        String cusphone = txt5.getText();
        String sql = "update customer set Cus_IDcard='"+cusidcard+"',Cus_name='"+cusname+"',Cus_address='"+cusaddress+"',Cus_phone='"+cusphone+"' "
                + "where Cus_id='"+cusid+"'";
        try{
            Connection c=Connect.connectDB();
            Statement stm=c.createStatement();
            if(stm.executeUpdate(sql)!=-1){
                JOptionPane.showMessageDialog(this,"อัพเดตข้อมูลเรียบร้อย","แจ้งเตือน", JOptionPane.INFORMATION_MESSAGE);
                showCustomer();
                clearTextBox();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        
    }
    
    public void deleteData(){
        String cusid=txt1.getText();
        String sql="delete from customer where Cus_id='"+cusid+"'";
        try{
            Connection c=Connect.connectDB();
            Statement stm=c.createStatement();
            if(stm.executeUpdate(sql)!=-1){
                JOptionPane.showMessageDialog(this,"ลบข้อมูลเรียบร้อย","แจ้งเตือน", JOptionPane.INFORMATION_MESSAGE);
                showCustomer();
                clearTextBox();
            }
            
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tbcus = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt1 = new javax.swing.JTextField();
        txt2 = new javax.swing.JTextField();
        txt3 = new javax.swing.JTextField();
        txt4 = new javax.swing.JTextField();
        txt5 = new javax.swing.JTextField();
        Update = new javax.swing.JButton();
        Insert = new javax.swing.JButton();
        clear = new javax.swing.JButton();
        delete = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        search = new javax.swing.JTextField();
        manu = new javax.swing.JButton();
        next = new javax.swing.JButton();
        back = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ข้อมูลลูกค้า");
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        tbcus.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "รหัสลูกค้า", "เลขบัตรประชาชน", "ชื่อลูกค้า", "ที่อยู่", "เบอร์โทรศัพท์"
            }
        ));
        tbcus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbcusMouseClicked(evt);
            }
        });
        tbcus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbcusKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tbcus);
        if (tbcus.getColumnModel().getColumnCount() > 0) {
            tbcus.getColumnModel().getColumn(1).setPreferredWidth(100);
        }

        jLabel1.setText("รหัสลูกค้า :");

        jLabel2.setText("เลขบัตรประชาชน :");

        jLabel3.setText("ชื่อลูกค้า :");

        jLabel4.setText("ที่อยู่ :");

        jLabel5.setText("เบอร์โทรศัพท์ :");

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

        Update.setText("แก้ไข");
        Update.setEnabled(false);
        Update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateActionPerformed(evt);
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

        jLabel6.setText("ค้นหา :");

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
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt3, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt4, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt5, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(delete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(18, 18, 18)
                            .addComponent(clear))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(Insert)
                            .addGap(18, 18, 18)
                            .addComponent(Update))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(207, 207, 207)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 610, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(manu)
                                .addGap(297, 297, 297))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(back)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(next, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18))))))
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
                            .addComponent(txt4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txt5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Update)
                        .addComponent(Insert))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(clear)
                        .addComponent(delete))
                    .addComponent(manu))
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(back)
                    .addComponent(next))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void InsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InsertActionPerformed
        boolean checkData=txt1.getText().isEmpty()||
                txt2.getText().isEmpty()||
                txt3.getText().isEmpty()||
                txt4.getText().isEmpty()||
                txt5.getText().isEmpty();
        if(checkData){
            JOptionPane.showMessageDialog(this,"ใส่ข้อมูลไม่ครบ","คำเตือน",JOptionPane.WARNING_MESSAGE);
        }else{
            insertData();
        }
        
        clearTextBox();
    }//GEN-LAST:event_InsertActionPerformed

    private void UpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateActionPerformed
        updateData();
    }//GEN-LAST:event_UpdateActionPerformed

    private void tbcusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbcusMouseClicked
        int select=tbcus.getSelectedRow();
        txt1.setText(tbcus.getValueAt(select, 0).toString());
        txt2.setText(tbcus.getValueAt(select, 1).toString());
        txt3.setText(tbcus.getValueAt(select, 2).toString());
        txt4.setText(tbcus.getValueAt(select, 3).toString());
        txt5.setText(tbcus.getValueAt(select, 4).toString());
        
        txt1.setEnabled(false);
        
        Update.setEnabled(true);
        delete.setEnabled(true);
    }//GEN-LAST:event_tbcusMouseClicked

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        txt1.setEnabled(true);
    }//GEN-LAST:event_formMouseClicked

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
        deleteData();
    }//GEN-LAST:event_deleteActionPerformed

    private void clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearActionPerformed
        clearTextBox();
    }//GEN-LAST:event_clearActionPerformed

    private void txt1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt1FocusGained
        txt1.setBackground(Color.yellow);
    }//GEN-LAST:event_txt1FocusGained

    private void txt1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt1FocusLost
        txt1.setBackground(Color.WHITE);
    }//GEN-LAST:event_txt1FocusLost

    private void txt2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt2FocusGained
        txt2.setBackground(Color.yellow);
    }//GEN-LAST:event_txt2FocusGained

    private void txt2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt2FocusLost
        txt2.setBackground(Color.WHITE);
    }//GEN-LAST:event_txt2FocusLost

    private void txt3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt3FocusGained
        txt3.setBackground(Color.yellow);
    }//GEN-LAST:event_txt3FocusGained

    private void txt3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt3FocusLost
        txt3.setBackground(Color.WHITE);
    }//GEN-LAST:event_txt3FocusLost

    private void txt4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt4FocusGained
        txt4.setBackground(Color.yellow);
    }//GEN-LAST:event_txt4FocusGained

    private void txt4FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt4FocusLost
        txt4.setBackground(Color.WHITE);
    }//GEN-LAST:event_txt4FocusLost

    private void txt5FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt5FocusGained
        txt5.setBackground(Color.yellow);
    }//GEN-LAST:event_txt5FocusGained

    private void txt5FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt5FocusLost
        txt5.setBackground(Color.WHITE);
    }//GEN-LAST:event_txt5FocusLost

    private void tbcusKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbcusKeyReleased
        int select=tbcus.getSelectedRow();
        txt1.setText(tbcus.getValueAt(select, 0).toString());
        txt2.setText(tbcus.getValueAt(select, 1).toString());
        txt3.setText(tbcus.getValueAt(select, 2).toString());
        txt4.setText(tbcus.getValueAt(select, 3).toString());
        txt5.setText(tbcus.getValueAt(select, 3).toString());
        
        txt1.setEnabled(false);
        
        Update.setEnabled(true);
    }//GEN-LAST:event_tbcusKeyReleased

    private void txt1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt1KeyReleased
        try{
            String sql ="select * from customer";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){
                String id = rs.getString("Cus_id");
                if(id.equalsIgnoreCase(txt1.getText())){
                    JOptionPane.showMessageDialog(this,"รหัสซ้ำ","แจ้งเตือน",JOptionPane.WARNING_MESSAGE);
                    clearTextBox();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_txt1KeyReleased

    private void searchFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchFocusLost
        search.setBackground(Color.WHITE);
    }//GEN-LAST:event_searchFocusLost

    private void searchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchFocusGained
        search.setBackground(Color.ORANGE);
    }//GEN-LAST:event_searchFocusGained

    private void searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchKeyReleased
        try{
            if (search.getText().isEmpty()){
                showCustomer();
            }else{
            String data = search.getText();
            String sql ="SELECT car.Car_registration AS ทะเบียนรถ ,customer.Cus_id AS รหัสลูกค้า,customer.Cus_name AS ชื่อลูกค้า \n" +
                        "FROM car INNER JOIN customer \n" +
                        "ON car.Cus_id=customer.Cus_id \n" +
                        "WHERE customer.Cus_id=?";//แสดงข้อมูลลูกค้ากับรถยนต์
            pst = con.prepareStatement(sql);
            pst.setString(1,data);
            rs = pst.executeQuery();
            tbcus.setModel(DbUtils.resultSetToTableModel(rs));
            System.out.print(sql);
            }    
        }catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_searchKeyReleased

    private void manuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manuActionPerformed
        Main main = new Main();
        main.setVisible(true);
        
        setVisible(false);
    }//GEN-LAST:event_manuActionPerformed

    private void nextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextActionPerformed
        Employee emp = new Employee();
        emp.setVisible(true);
        
        setVisible(false);
    }//GEN-LAST:event_nextActionPerformed

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        Main main = new Main();
        main.setVisible(true);
        
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
            java.util.logging.Logger.getLogger(Customer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Customer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Customer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Customer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Customer().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Insert;
    private javax.swing.JButton Update;
    private javax.swing.JButton back;
    private javax.swing.JButton clear;
    private javax.swing.JButton delete;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton manu;
    private javax.swing.JButton next;
    private javax.swing.JTextField search;
    private javax.swing.JTable tbcus;
    private javax.swing.JTextField txt1;
    private javax.swing.JTextField txt2;
    private javax.swing.JTextField txt3;
    private javax.swing.JTextField txt4;
    private javax.swing.JTextField txt5;
    // End of variables declaration//GEN-END:variables
}
