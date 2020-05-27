package Garage;
import java.sql.*;
import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;

public class ListRepair extends javax.swing.JFrame {
    private Connection con = null;
    private ResultSet rs = null;
    private PreparedStatement pst = null;
    DefaultTableModel model=new DefaultTableModel();
    Object[]data=new Object[0];
    
    public ListRepair() {
        initComponents();
        con = Connect.connectDB();
        model=(DefaultTableModel)tblist.getModel();
        showListRepair(); 
        getCombox();     
    }
    
    private void getCombox() {
        try {
            String sql1 = "SELECT * FROM employee";
            String sql2 = "SELECT * FROM car";
            String sql3 = "SELECT * FROM receipt";
            PreparedStatement pst1 = con.prepareStatement(sql1);
            ResultSet rs1 = pst1.executeQuery();
            PreparedStatement pst2 = con.prepareStatement(sql2);
            ResultSet rs2 = pst2.executeQuery();
            PreparedStatement pst3 = con.prepareStatement(sql3);
            ResultSet rs3 = pst3.executeQuery();
            while (rs1.next()) {
                String id1 = rs1.getString("Emp_id");
                Comboemp.addItem(id1);
            }
            while (rs2.next()) {
                String id2 = rs2.getString("Car_registration");
                Combocar.addItem(id2);
            }
            while (rs3.next()) {
                String id3 = rs3.getString("Rec_id");
                Comborec.addItem(id3);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error!", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public void showListRepair(){
        clearRow();
        String sql = "Select * from listrepair";
        try{
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            tblist.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void clearTextBox(){
        txt1.setText("");
        Comboemp.setSelectedIndex(0);
        Combocar.setSelectedIndex(0);
        Comborec.setSelectedIndex(0);
        txt2.setText("");
        txt3.setText("");
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
            int row = tblist.getSelectedRow();
            String selectId = tblist.getValueAt(row, 0).toString();
            String sql = "select * from listrepair where Lis_id = '" + selectId + "' ";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                String add1 = rs.getString("Lis_id");
                txt1.setText(add1);
                String add2 = rs.getString("Emp_id");
                Comboemp.setSelectedItem(add2);
                String add3 = rs.getString("Car_registration");
                Combocar.setSelectedItem(add3);
                String add4 = rs.getString("Rec_id");
                Comborec.setSelectedItem(add4);
                String add5 = rs.getString("Lis_name");
                txt2.setText(add5);
                String add6 = rs.getString("Lis_price");
                txt3.setText(add6);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void insertData(){//การบันทึกข้อมูล
        clearRow();
        try{
            String sql="insert into listrepair(Lis_id,Emp_id,Car_registration,Rec_id,Lis_name,Lis_price)value(?,?,?,?,?,?)";
            pst=con.prepareStatement(sql);
            pst.setString(1,txt1.getText());
            pst.setString(2,Comboemp.getSelectedItem().toString());
            pst.setString(3,Combocar.getSelectedItem().toString());
            pst.setString(4,Comborec.getSelectedItem().toString());
            pst.setString(5,txt2.getText());
            pst.setString(6,txt3.getText());
            pst.execute();
            JOptionPane.showMessageDialog(this, "บันทึกเรียบร้อย" ,"บันทึก", JOptionPane.INFORMATION_MESSAGE);
            clearTextBox();
            showListRepair();
            
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    private void updateData() {
        try {
            String sql = "update listrepair set Lis_id= ? ,"
                + "Emp_id= ? , Car_registration= ? ,"
                + "Rec_id= ? , Lis_name= ? , Lis_price= ? "
                + "where Lis_id"
                + "= '" + txt1.getText() + "'";
            pst = con.prepareStatement(sql);
            pst.setString(1,txt1.getText());
            pst.setString(2,Comboemp.getSelectedItem().toString());
            pst.setString(3,Combocar.getSelectedItem().toString());
            pst.setString(4,Comborec.getSelectedItem().toString());
            pst.setString(5,txt2.getText());
            pst.setString(6,txt3.getText());
            pst.execute();
            JOptionPane.showMessageDialog(this, "แก้ไข " + txt1.getText(), "แจ้งเตือน", JOptionPane.INFORMATION_MESSAGE);
            clearTextBox();
            showListRepair();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void deleteData() {
        try {
            String id_delete = txt1.getText();
            String sql = "DELETE FROM listrepair WHERE Lis_id = '" + id_delete + "'";
            pst = con.prepareStatement(sql);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "ลบ " + txt1.getText(), "แจ้งเตือน", JOptionPane.INFORMATION_MESSAGE);
            clearTextBox();
            showListRepair();
            
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
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt1 = new javax.swing.JTextField();
        txt2 = new javax.swing.JTextField();
        txt3 = new javax.swing.JTextField();
        Comboemp = new javax.swing.JComboBox<>();
        Combocar = new javax.swing.JComboBox<>();
        Comborec = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblist = new javax.swing.JTable();
        update = new javax.swing.JButton();
        Insert = new javax.swing.JButton();
        clear = new javax.swing.JButton();
        delete = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        search = new javax.swing.JTextField();
        manu = new javax.swing.JButton();
        next = new javax.swing.JButton();
        back = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("รายการซ่อม");
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        jLabel1.setText("รหัสรายการซ่อม :");

        jLabel2.setText("รหัสพนักงาน :");

        jLabel3.setText("ทะเบียนรถ :");

        jLabel4.setText("รหัสใบเสร็จ :");

        jLabel5.setText("ชื่อรายการซ่อม :");

        jLabel6.setText("ราคารายการซ่อม :");

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

        tblist.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "รหัสรายการซ่อม", "รหัสพนักงาน", "ทะเบียนรถ", "รหัสใบเสร็จ", "ชื่อรายการซ่อม", "ราคารายการซ่อม"
            }
        ));
        tblist.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblistMouseClicked(evt);
            }
        });
        tblist.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblistKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblist);

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
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt1)
                            .addComponent(txt2, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                            .addComponent(txt3, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                            .addComponent(Comboemp, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Combocar, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Comborec, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Insert)
                                .addGap(16, 16, 16))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(delete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(update, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(clear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 612, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(187, 187, 187)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(281, 281, 281)
                        .addComponent(manu)))
                .addContainerGap(27, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(back)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(next, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(Comboemp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(Combocar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(Comborec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txt2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txt3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(update)
                    .addComponent(Insert)
                    .addComponent(jLabel7)
                    .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(delete)
                    .addComponent(clear)
                    .addComponent(manu))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(back)
                    .addComponent(next))
                .addGap(23, 23, 23))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void InsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InsertActionPerformed
        boolean checkData=txt1.getText().isEmpty()||
                Comboemp.getSelectedItem().toString().isEmpty()||
                Combocar.getSelectedItem().toString().isEmpty()||
                txt2.getText().isEmpty()||
                txt3.getText().isEmpty(); 
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

    private void tblistMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblistMouseClicked
        clickTable();
        
        int select=tblist.getSelectedRow();
        txt1.setText(tblist.getValueAt(select, 0).toString());
        Comboemp.setSelectedItem(tblist.getValueAt(select, 1).toString());
        Combocar.setSelectedItem(tblist.getValueAt(select, 2).toString());
        Comborec.setSelectedItem(tblist.getValueAt(select, 3).toString());
        txt2.setText(tblist.getValueAt(select, 4).toString());
        txt3.setText(tblist.getValueAt(select, 5).toString());
        
        txt1.setEnabled(false);
        
        update.setEnabled(true);
        delete.setEnabled(true);
    }//GEN-LAST:event_tblistMouseClicked

    private void searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchKeyReleased
        
        try{
            if(search.getText().isEmpty()){
                showListRepair();
                clearRow();
            }else{
            String data = search.getText();
            String sql ="SELECT listrepair.Lis_id AS รหัสรายการซ่อม, listrepair.Car_registration AS ทะเบียนรถ , "
                    + "listrepair.Lis_name AS ชื่อรายการซ่อม , listrepair.Rec_id AS รหัสใบเสร็จ ,"
                    + "listrepair.Lis_price AS ราคาซ่อม\n" 
                    + "FROM listrepair INNER JOIN car \n" 
                    + "ON listrepair.Car_registration=car.Car_registration WHERE car.Car_registration=? ";//แสดงข้อมูลลูกค้ากับใบเสร็จ
            pst = con.prepareStatement(sql);
            pst.setString(1,data);
            rs = pst.executeQuery();
            tblist.setModel(DbUtils.resultSetToTableModel(rs));
            System.out.print(sql);
            }    
        }catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_searchKeyReleased

    private void searchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchFocusGained
        search.setBackground(Color.ORANGE);
    }//GEN-LAST:event_searchFocusGained

    private void searchFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchFocusLost
        search.setBackground(Color.WHITE);
    }//GEN-LAST:event_searchFocusLost

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        txt1.setEnabled(true);
    }//GEN-LAST:event_formMouseClicked

    private void tblistKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblistKeyReleased
        int select=tblist.getSelectedRow();
        txt1.setText(tblist.getValueAt(select, 0).toString());
        Comboemp.setSelectedItem(tblist.getValueAt(select, 1).toString());
        Combocar.setSelectedItem(tblist.getValueAt(select, 2).toString());
        Comborec.setSelectedItem(tblist.getValueAt(select, 3).toString());
        txt2.setText(tblist.getValueAt(select, 4).toString());
        txt3.setText(tblist.getValueAt(select, 5).toString());
        
        txt1.setEnabled(false);
        
        update.setEnabled(true);
    }//GEN-LAST:event_tblistKeyReleased

    private void txt1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt1KeyReleased
        try{
            String sql ="select * from listrepair";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){
                String id = rs.getString("Lis_id");
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

    private void txt1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt1FocusLost
        txt1.setBackground(Color.WHITE);
    }//GEN-LAST:event_txt1FocusLost

    private void txt2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt2FocusLost
        txt2.setBackground(Color.WHITE);
    }//GEN-LAST:event_txt2FocusLost

    private void txt3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt3FocusLost
        txt3.setBackground(Color.WHITE);
    }//GEN-LAST:event_txt3FocusLost

    private void manuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manuActionPerformed
        Main main = new Main();
        main.setVisible(true);
        
        setVisible(false);
    }//GEN-LAST:event_manuActionPerformed

    private void nextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextActionPerformed
        Spares spa = new Spares();
        spa.setVisible(true);
        
        setVisible(false);
    }//GEN-LAST:event_nextActionPerformed

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        Receipt rec = new Receipt();
        rec.setVisible(true);
        
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
            java.util.logging.Logger.getLogger(ListRepair.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListRepair.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListRepair.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListRepair.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ListRepair().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> Combocar;
    private javax.swing.JComboBox<String> Comboemp;
    private javax.swing.JComboBox<String> Comborec;
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
    private javax.swing.JTextField search;
    private javax.swing.JTable tblist;
    private javax.swing.JTextField txt1;
    private javax.swing.JTextField txt2;
    private javax.swing.JTextField txt3;
    private javax.swing.JButton update;
    // End of variables declaration//GEN-END:variables
}
