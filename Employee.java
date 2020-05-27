package Garage;
import java.sql.*;
import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.*;
import java.text.SimpleDateFormat;

public class Employee extends javax.swing.JFrame {
    private Connection con = null;
    private ResultSet rs = null;
    private PreparedStatement pst = null;
    DefaultTableModel model=new DefaultTableModel();
    Object[]data=new Object[0];
    
    public Employee() {
        initComponents();
        con = Connect.connectDB();
        model=(DefaultTableModel)tbemp.getModel();
        showEmployee();
    }
    
    public void showEmployee(){
        clearRow();
        String sql = "Select * from employee";
        try{
            Connection c=Connect.connectDB();//เชื่อมต่อกับฐานข้อมูล
            ResultSet rs=c.createStatement().executeQuery(sql);
            int row=0;//แถว
            while(rs.next()){
                model.addRow(data);//เพิ่มแถว
                model.setValueAt(rs.getString(1), row, 0);//รหัสพนักงาน
                model.setValueAt(rs.getString(2), row, 1);//ชื่อพนักงาน
                model.setValueAt(rs.getString(3), row, 2);//ที่อยู่
                model.setValueAt(rs.getString(4), row, 3);//เบอร์โทรศัพท์
                model.setValueAt(rs.getString(5), row, 4);//ตำแหน่ง
                model.setValueAt(rs.getString(6), row, 5);//เงินเดือน
                model.setValueAt(rs.getString(7), row, 6);//วันที่เริ่มงาน
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
        txt5.setText(""); 
        txt6.setText(""); 
        txt7.setText(""); 
        txt1.setEnabled(true);
    }
    
    public void clearRow(){
        int row=model.getRowCount()-1;
        while (row>-1){
            model.removeRow(row);
            row--;
        }
    }
    
    public void insertData(){//การบันทึกข้อมูล
        String empid = txt1.getText();
        String empname = txt2.getText();
        String empaddress = txt3.getText();
        String empphone = txt4.getText();
        String empvacancy = txt5.getText();
        String empsalary = txt6.getText();
        String empfirstdate = txt7.getText();
        //คำสั่ง sql
        String sql="insert into employee value('"+empid+"','"+empname+"',"
                + "'"+empaddress+"','"+empphone+"',"
                + "'"+empvacancy+"','"+empsalary+"','"+empfirstdate+"')";
        
        try{
            Connection c=Connect.connectDB();
            Statement stm=c.createStatement();
            if(stm.executeUpdate(sql)!=-1){
                JOptionPane.showMessageDialog(this,"บันทึกข้อมูลเรียบร้อย","แจ้งเตือน", JOptionPane.INFORMATION_MESSAGE);
                clearTextBox();
                showEmployee();
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void updateData(){
        String empid = txt1.getText();
        String empname = txt2.getText();
        String empaddress = txt3.getText();
        String empphone = txt4.getText();
        String empvacancy = txt5.getText();
        String empsalary = txt6.getText();
        String empfirstdate = txt7.getText();
        String sql = "update employee set Emp_name='"+empname+"',"
                + "Emp_address='"+empaddress+"',Emp_phone='"+empphone+"',"
                + "Emp_vacancy='"+empvacancy+"',Emp_salary='"+empsalary+"',Emp_firstdate='"+empfirstdate+"' "
                + "where Emp_id='"+empid+"'";
        try{
            Connection c=Connect.connectDB();
            Statement stm=c.createStatement();
            if(stm.executeUpdate(sql)!=-1){
                JOptionPane.showMessageDialog(this,"อัพเดตข้อมูลเรียบร้อย","แจ้งเตือน", JOptionPane.INFORMATION_MESSAGE);
                showEmployee();
                clearTextBox();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        
    }
    
    public void deleteData(){
        String empid=txt1.getText();
        String sql="delete from employee where Emp_id='"+empid+"'";
        try{
            Connection c=Connect.connectDB();
            Statement stm=c.createStatement();
            if(stm.executeUpdate(sql)!=-1){
                JOptionPane.showMessageDialog(this,"ลบข้อมูลเรียบร้อย","แจ้งเตือน", JOptionPane.INFORMATION_MESSAGE);
                showEmployee();
                clearTextBox();
            }
            
        }catch (Exception e){
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
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txt1 = new javax.swing.JTextField();
        txt2 = new javax.swing.JTextField();
        txt3 = new javax.swing.JTextField();
        txt4 = new javax.swing.JTextField();
        txt5 = new javax.swing.JTextField();
        txt6 = new javax.swing.JTextField();
        txt7 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbemp = new javax.swing.JTable();
        update = new javax.swing.JButton();
        Insert = new javax.swing.JButton();
        clear = new javax.swing.JButton();
        delete = new javax.swing.JButton();
        next = new javax.swing.JButton();
        back = new javax.swing.JButton();
        manu = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ข้อมูลพนักงาน");
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

        jLabel1.setText("รหัสพนักงาน :");

        jLabel2.setText("ชื่อพนักงาน :");

        jLabel3.setText("ที่อยู่ :");

        jLabel4.setText(" เบอร์โทรศัพท์ :");

        jLabel5.setText("ตำแหน่ง :");

        jLabel6.setText("เงินเดือน :");

        jLabel7.setText("วันที่เริ่มทำงาน :");

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

        txt6.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt6FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt6FocusLost(evt);
            }
        });

        txt7.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt7FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt7FocusLost(evt);
            }
        });

        tbemp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "รหัสพนักงาน", "ชื่อพนักงาน", "ที่อยู่", "เบอร์โทรศัพท์", "ตำแหน่ง", "เงินเดือน", "วันที่เริ่มทำงาน"
            }
        ));
        tbemp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbempMouseClicked(evt);
            }
        });
        tbemp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbempKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tbemp);

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

        manu.setText("เมนู");
        manu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel7)
                            .addGap(18, 18, 18)
                            .addComponent(txt7, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel2)
                                .addComponent(jLabel1)
                                .addComponent(jLabel3)
                                .addComponent(jLabel4)
                                .addComponent(jLabel5)
                                .addComponent(jLabel6))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txt1, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                                .addComponent(txt2)
                                .addComponent(txt3)
                                .addComponent(txt4)
                                .addComponent(txt5)
                                .addComponent(txt6))))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(delete, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(clear))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(Insert)
                            .addGap(18, 18, 18)
                            .addComponent(update))))
                .addGap(33, 33, 33)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(164, 164, 164)
                .addComponent(manu, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(back)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(next, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txt2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txt3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txt4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txt5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txt6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txt7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(update)
                            .addComponent(Insert))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(clear)
                            .addComponent(delete))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(manu)
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
                          txt5.getText().isEmpty()||
                          txt6.getText().isEmpty()||
                          txt7.getText().isEmpty();
        if(checkData){
            JOptionPane.showMessageDialog(this,"ใส่ข้อมูลไม่ครบ","คำเตือน",JOptionPane.WARNING_MESSAGE);
        }else{
            insertData();
        }
    }//GEN-LAST:event_InsertActionPerformed

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed
        updateData();
    }//GEN-LAST:event_updateActionPerformed

    private void tbempMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbempMouseClicked
        int select=tbemp.getSelectedRow();
        txt1.setText(tbemp.getValueAt(select, 0).toString());
        txt2.setText(tbemp.getValueAt(select, 1).toString());
        txt3.setText(tbemp.getValueAt(select, 2).toString());
        txt4.setText(tbemp.getValueAt(select, 3).toString());
        txt5.setText(tbemp.getValueAt(select, 4).toString());
        txt6.setText(tbemp.getValueAt(select, 5).toString());
        txt7.setText(tbemp.getValueAt(select, 6).toString());
        
        txt1.setEnabled(false);
        
        update.setEnabled(true);
        delete.setEnabled(true);
    }//GEN-LAST:event_tbempMouseClicked

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        txt1.setEnabled(true);
    }//GEN-LAST:event_formMouseClicked

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
        deleteData();
    }//GEN-LAST:event_deleteActionPerformed

    private void clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearActionPerformed
        clearTextBox();
    }//GEN-LAST:event_clearActionPerformed

    private void tbempKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbempKeyReleased
        int select=tbemp.getSelectedRow();
        txt1.setText(tbemp.getValueAt(select, 0).toString());
        txt2.setText(tbemp.getValueAt(select, 1).toString());
        txt3.setText(tbemp.getValueAt(select, 2).toString());
        txt4.setText(tbemp.getValueAt(select, 3).toString());
        txt5.setText(tbemp.getValueAt(select, 4).toString());
        txt6.setText(tbemp.getValueAt(select, 5).toString());
        txt7.setText(tbemp.getValueAt(select, 6).toString());
        
        txt1.setEnabled(false);
        
        update.setEnabled(true);
    }//GEN-LAST:event_tbempKeyReleased

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        Calendar c= Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        txt7.setText(df.format(c.getTime()));
    }//GEN-LAST:event_formWindowOpened

    private void txt1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt1KeyReleased
        try{
            String sql ="select * from employee";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){
                String id = rs.getString("Emp_id");
                if(id.equalsIgnoreCase(txt1.getText())){
                    JOptionPane.showMessageDialog(this,"รหัสซ้ำ","แจ้งเตือน",JOptionPane.WARNING_MESSAGE);
                    clearTextBox();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_txt1KeyReleased

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

    private void txt5FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt5FocusGained
        txt5.setBackground(Color.yellow);
    }//GEN-LAST:event_txt5FocusGained

    private void txt6FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt6FocusGained
        txt6.setBackground(Color.yellow);
    }//GEN-LAST:event_txt6FocusGained

    private void txt7FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt7FocusGained
        txt7.setBackground(Color.yellow);
    }//GEN-LAST:event_txt7FocusGained

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

    private void txt5FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt5FocusLost
        txt5.setBackground(Color.WHITE);
    }//GEN-LAST:event_txt5FocusLost

    private void txt6FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt6FocusLost
        txt6.setBackground(Color.WHITE);
    }//GEN-LAST:event_txt6FocusLost

    private void txt7FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt7FocusLost
        txt7.setBackground(Color.WHITE);
    }//GEN-LAST:event_txt7FocusLost

    private void nextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextActionPerformed
        Car car = new Car();
        car.setVisible(true);
        
        setVisible(false);
    }//GEN-LAST:event_nextActionPerformed

    private void manuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manuActionPerformed
        Main main = new Main();
        main.setVisible(true);
        
        setVisible(false);
    }//GEN-LAST:event_manuActionPerformed

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        Customer cus = new Customer();
        cus.setVisible(true);
        
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
            java.util.logging.Logger.getLogger(Employee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Employee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Employee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Employee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Employee().setVisible(true);
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
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton manu;
    private javax.swing.JButton next;
    private javax.swing.JTable tbemp;
    private javax.swing.JTextField txt1;
    private javax.swing.JTextField txt2;
    private javax.swing.JTextField txt3;
    private javax.swing.JTextField txt4;
    private javax.swing.JTextField txt5;
    private javax.swing.JTextField txt6;
    private javax.swing.JTextField txt7;
    private javax.swing.JButton update;
    // End of variables declaration//GEN-END:variables
}
