package pharmacy;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import dao.ConnectionProvider;
import java.util.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import javax.swing.JOptionPane;
import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

public class AddMedicine extends JFrame implements ActionListener {

    JFrame f;
    JTextField medid, nameadd, companyname, quantityadd, priceunit, expirydate;
    JDateChooser dc;
    JButton save, close;
    public String numberPattern = "^[0-9]*$";

    public AddMedicine() {
        f = new JFrame("Add Medicine");
        f.setBackground(Color.WHITE);
        f.setBounds(0, 0, 700, 500);
        f.setLayout(null);

        JLabel admindash = new JLabel("ADD MEDICINE");
        admindash.setBounds(350, 30, 300, 40);
        admindash.setFont(new Font("Times New Roman", Font.BOLD, 36));
        admindash.setForeground(Color.ORANGE);
        f.add(admindash);

        JLabel addmedid = new JLabel("Medicine ID");
        addmedid.setBounds(130, 130, 170, 40);
        addmedid.setFont(new Font("Times New Roman", Font.BOLD, 20));
        f.add(addmedid);

        medid = new JTextField();
        medid.setBounds(130, 180, 200, 20);
        medid.setBorder(BorderFactory.createLineBorder(Color.black));
        f.add(medid);

        JLabel addname = new JLabel("Name");
        addname.setBounds(130, 210, 170, 40);
        addname.setFont(new Font("Times New Roman", Font.BOLD, 20));
        //adduserrole.setForeground(Color.WHITE); 
        f.add(addname);

        nameadd = new JTextField();
        nameadd.setBounds(130, 260, 200, 20);
        f.add(nameadd);

        JLabel addcompany = new JLabel("Company Name");
        addcompany.setBounds(130, 290, 170, 40);
        addcompany.setFont(new Font("Times New Roman", Font.BOLD, 20));
        //adduserrole.setForeground(Color.WHITE); 
        f.add(addcompany);

        companyname = new JTextField();
        companyname.setBounds(130, 340, 200, 20);
        f.add(companyname);

        JLabel addquant = new JLabel("Quantity");
        addquant.setBounds(130, 370, 170, 40);
        addquant.setFont(new Font("Times New Roman", Font.BOLD, 20));
        f.add(addquant);

        quantityadd = new JTextField();
        quantityadd.setBounds(130, 420, 200, 20);
        f.add(quantityadd);

        JLabel addpriceunit = new JLabel("Price per Unit");
        addpriceunit.setBounds(500, 130, 170, 40);
        addpriceunit.setFont(new Font("Times New Roman", Font.BOLD, 20));
        //adduserrole.setForeground(Color.WHITE); 
        f.add(addpriceunit);

        priceunit = new JTextField();
        priceunit.setBounds(500, 180, 200, 20);
        f.add(priceunit);

        JLabel addexpiry = new JLabel("Expiry Date");
        addexpiry.setBounds(500, 210, 170, 40);
        addexpiry.setFont(new Font("Times New Roman", Font.BOLD, 20));
        //adduserrole.setForeground(Color.WHITE); 
        f.add(addexpiry);

        dc = new JDateChooser();
        dc.setBounds(500, 260, 200, 20);
        dc.setForeground(Color.BLACK);
        dc.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        f.add(dc);

        save = new JButton("Save");
        save.setBounds(500, 480, 90, 30);
        save.addActionListener(this);
        f.add(save);


        f.setVisible(true);
        f.setSize(900, 600);
        f.setLocation(300, 150);

    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == save) {
            String uniqueId = medid.getText();
            String name = nameadd.getText();
            String companyName = companyname.getText();
            String quantity = quantityadd.getText();
            String price = priceunit.getText();
            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            String expiryDate = df.format(dc.getDate());
            if (uniqueId.equals("")) {
                JOptionPane.showMessageDialog(null, "Medicine ID field is required.");
            } else if (name.equals("")) {
                JOptionPane.showMessageDialog(null, "Name field is required.");
            } else if (companyName.equals("")) {
                JOptionPane.showMessageDialog(null, "CompanyName field is required.");
            } else if (quantity.equals("")) {
                JOptionPane.showMessageDialog(null, "Quantity field is required.");
            } else if (!quantity.matches(numberPattern)) {
                JOptionPane.showMessageDialog(null, "Quantity field is invalid.");
            } else if (price.equals("")) {
                JOptionPane.showMessageDialog(null, "Price per unit field is required.");
            } else if (!price.matches(numberPattern)) {
                JOptionPane.showMessageDialog(null, "Price per unit field is invalid.");
            } else {
                try {
                    Connection con = ConnectionProvider.getCon();
                    PreparedStatement ps = con.prepareStatement("insert into medicine(uniqueId,name,companyName,quantity,price,expiryDate) values(?,?,?,?,?,?)");
                    ps.setString(1, uniqueId);
                    ps.setString(2, name);
                    ps.setString(3, companyName);
                    ps.setString(4, quantity);
                    ps.setString(5, price);
                    ps.setString(6, expiryDate);
                    ps.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Medicine Added Succesfully");
                    setVisible(true);
                    new AddMedicine().setVisible(true);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
            }
        }
        

    }

    public static void main(String args[]) {

        new AddMedicine().setVisible(true);
    }
}
