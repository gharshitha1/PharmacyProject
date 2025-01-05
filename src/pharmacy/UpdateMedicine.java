package pharmacy;

import com.toedter.calendar.JDateChooser;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.text.SimpleDateFormat;
import dao.ConnectionProvider;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.*;
import java.util.Date;

public class UpdateMedicine extends JFrame implements ActionListener{

    JFrame f;
    JDateChooser dc;
    JTextField medicineupdate, nameupdate, companyupdate, priceunit, updatequantity;
    JButton search, updatebutton;

    public UpdateMedicine() {
        f = new JFrame("Update User");
        f.setBackground(Color.WHITE);
        f.setBounds(0, 0, 700, 500);
        f.setLayout(null);

        JLabel updateuser = new JLabel("UPDATE MEDICINE");
        updateuser.setBounds(320, 30, 400, 40);
        updateuser.setFont(new Font("Times New Roman", Font.BOLD, 36));
        updateuser.setForeground(Color.ORANGE);
        f.add(updateuser);

        JLabel updatemedicine = new JLabel("Medicine ID");
        updatemedicine.setBounds(250, 140, 170, 20);
        updatemedicine.setFont(new Font("Times New Roman", Font.BOLD, 20));
        f.add(updatemedicine);

        medicineupdate = new JTextField();
        medicineupdate.setBounds(380, 140, 200, 24);
        medicineupdate.setBorder(BorderFactory.createLineBorder(Color.black));
        f.add(medicineupdate);

        ImageIcon i5 = new ImageIcon(ClassLoader.getSystemResource("images/search.png"));
        Image i6 = i5.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);
        search = new JButton("Search", new ImageIcon(i6));
        search.setBackground(Color.white);
        search.setForeground(Color.black);
        search.setBounds(600, 140, 120, 24);
        search.setFont(new Font("Arial", Font.BOLD, 15));
        search.setFocusPainted(false);
        search.setBorder(BorderFactory.createLineBorder(Color.black));
        search.addActionListener(this);
        f.add(search);

        JLabel updatename = new JLabel("Name");
        updatename.setBounds(200, 230, 170, 40);
        updatename.setFont(new Font("Times New Roman", Font.BOLD, 20));
        f.add(updatename);

        nameupdate = new JTextField();
        nameupdate.setBounds(200, 280, 200, 20);
        nameupdate.setBorder(BorderFactory.createLineBorder(Color.black));
        f.add(nameupdate);

        JLabel updatecompnay = new JLabel("Company Name");
        updatecompnay.setBounds(200, 310, 170, 40);
        updatecompnay.setFont(new Font("Times New Roman", Font.BOLD, 20));
        f.add(updatecompnay);

        companyupdate = new JTextField();
        companyupdate.setBounds(200, 360, 200, 20);
        f.add(companyupdate);

        JLabel updatequant = new JLabel("Quantity");
        updatequant.setBounds(200, 390, 170, 40);
        updatequant.setFont(new Font("Times New Roman", Font.BOLD, 20));
        f.add(updatequant);

        updatequantity = new JTextField();
        updatequantity.setBounds(200, 440, 200, 20);
        f.add(updatequantity);

        JLabel updateprice = new JLabel("Price per unit");
        updateprice.setBounds(570, 230, 170, 40);
        updateprice.setFont(new Font("Times New Roman", Font.BOLD, 20));
        f.add(updateprice);

        priceunit = new JTextField();
        priceunit.setBounds(570, 280, 200, 20);
        f.add(priceunit);

        JLabel updatexp = new JLabel("Expiry Date");
        updatexp.setBounds(570, 310, 170, 40);
        updatexp.setFont(new Font("Times New Roman", Font.BOLD, 20));
        //adduserrole.setForeground(Color.WHITE); 
        f.add(updatexp);

        dc = new JDateChooser();
        dc.setBounds(570, 360, 200, 20);
        dc.setForeground(Color.BLACK);
        dc.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        f.add(dc);

        updatebutton = new JButton("Update");
        updatebutton.setBounds(620, 480, 90, 30);
        updatebutton.addActionListener(this);
        f.add(updatebutton);

        f.setSize(1000, 650);
        f.setLocation(300, 120);
        f.setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == search) {
            int checkMedicineExist = 0;
            String uniqueId =medicineupdate.getText();
            if (uniqueId.equals("")) {
                JOptionPane.showMessageDialog(null, "Medicine ID is required.");
            } else {
                SimpleDateFormat dFormat = new SimpleDateFormat("dd-mm-yyyy");
                try {
                    Connection con = ConnectionProvider.getCon();
                    Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery("select * from medicine where uniqueId='" + uniqueId + "'");
                    while (rs.next()) {
                        medicineupdate.setEditable(false);
                        checkMedicineExist= 1;
                        nameupdate.setText(rs.getString("name"));
                        companyupdate.setText(rs.getString("companyName"));
                        updatequantity .setText(rs.getString("quantity"));
                        priceunit.setText(rs.getString("price"));
                        String exp = rs.getString("expiryDate");
                        if (exp != "") {
                            try {
                                dc.setDate(dFormat.parse(exp));
                            } catch (Exception eee) {
                            }
                        }
                    }
                } catch (Exception ee) {
                    JOptionPane.showMessageDialog(null, ee);
                }
                if (checkMedicineExist == 0) {
                    JOptionPane.showMessageDialog(null, "Username does not exist.");
                }
            }
        } else if (ae.getSource() == updatebutton) {
            String uniqueId = medicineupdate.getText();
            String name = nameupdate.getText();
            String companyName = companyupdate.getText();
            String quantity = updatequantity.getText();
            String price = priceunit.getText();
            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            String expiryDate = df.format(dc.getDate());

            if (uniqueId.equals("")) {
                JOptionPane.showMessageDialog(null, "Medicine ID is required.");
            } else if (name.equals("")) {
                JOptionPane.showMessageDialog(null, "Name is required.");
            } else if (companyName.equals("")) {
                JOptionPane.showMessageDialog(null, "Company Name is required.");
            } else if (quantity.equals("")) {
                JOptionPane.showMessageDialog(null, "Quantity is required.");
            } else if (price.equals("")) {
                JOptionPane.showMessageDialog(null, "Price per unit is required.");
            } else if (expiryDate.equals("")) {
                JOptionPane.showMessageDialog(null, "Expiry Date is required.");
            } else {
                try {
                    Connection con = ConnectionProvider.getCon();
                    PreparedStatement ps = con.prepareStatement("update medicine set name=?, companyName=?, quantity=?, price=?, expiryDate=? where uniqueId=?");
                    ps.setString(1, name);
                    ps.setString(2, companyName);
                    ps.setString(3, quantity);
                    ps.setString(4, price);
                    ps.setString(5, expiryDate);
                    ps.setString(6, uniqueId);
                    int rowsUpdated = ps.executeUpdate();

                    if (rowsUpdated > 0) {
                        JOptionPane.showMessageDialog(null, "Medicine Updated Successfully");
                    } else {
                        JOptionPane.showMessageDialog(null, "Medicine not found or update failed.");
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        }
    }

    public static void main(String args[]) {
        new UpdateMedicine();
    }
}
