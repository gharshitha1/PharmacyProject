package pharmacy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import dao.ConnectionProvider;
import java.util.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import javax.swing.JOptionPane;
import com.toedter.calendar.JDateChooser;
import java.text.SimpleDateFormat;

public class AddUser extends JFrame implements ActionListener {

    JTextField userrolee, usernameadd, userdob, userphn, useremail, userusername, userpsswd, useradrs;
    JButton addusersave, close;
    JFrame f;
    JLabel l1;
    JDateChooser dc;
    public String emailPattern = "^[a-zA-Z0-9]+[@]+[a-zA-Z0-9]+[.]+[a-zA-Z0-9]+$";
    public String mobileNumberPattern = "^[0-9]*$";
    public int checkUsername = 0;
    public String name;
    public String userRole;
    public String namee;
    public String dob;
    public String mobileNumber;
    public String email;
    public String username;
    public String password;
    public String address;

    public AddUser() {

        f = new JFrame("Add User");
        f.setBackground(Color.WHITE);
        f.setBounds(0, 0, 700, 500);
        f.setLayout(null);

        l1 = new JLabel();
        l1.setBounds(0, 0, 1368, 768);
        l1.setLayout(null);

        JLabel admindash = new JLabel("ADD USER");
        admindash.setBounds(350, 30, 300, 40);
        admindash.setFont(new Font("Times New Roman", Font.BOLD, 36));
        admindash.setForeground(Color.ORANGE);
        f.add(admindash);

        JLabel adduserrolee = new JLabel("User Role");
        adduserrolee.setBounds(130, 130, 170, 40);
        adduserrolee.setFont(new Font("Times New Roman", Font.BOLD, 20));

        //adduserrole.setForeground(Color.WHITE); 
        f.add(adduserrolee);

        userrolee = new JTextField();
        userrolee.setBounds(130, 180, 200, 20);
        userrolee.setBorder(BorderFactory.createLineBorder(Color.black));
        f.add(userrolee);

        JLabel addusername = new JLabel("Name");
        addusername.setBounds(130, 210, 170, 40);
        addusername.setFont(new Font("Times New Roman", Font.BOLD, 20));
        //adduserrole.setForeground(Color.WHITE); 
        f.add(addusername);

        usernameadd = new JTextField();
        usernameadd.setBounds(130, 260, 200, 20);
        f.add(usernameadd);

        JLabel adduserdob = new JLabel("DOB");
        adduserdob.setBounds(130, 290, 170, 40);
        adduserdob.setFont(new Font("Times New Roman", Font.BOLD, 20));
        //adduserrole.setForeground(Color.WHITE); 
        f.add(adduserdob);

        dc = new JDateChooser();
        dc.setBounds(130, 340, 200, 20);
        dc.setForeground(Color.BLACK);
        dc.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        f.add(dc);
        JLabel adduserphn = new JLabel("Phone Number");
        adduserphn.setBounds(130, 370, 170, 40);
        adduserphn.setFont(new Font("Times New Roman", Font.BOLD, 20));
        //adduserrole.setForeground(Color.WHITE); 
        f.add(adduserphn);

        userphn = new JTextField();
        userphn.setBounds(130, 420, 200, 20);
        f.add(userphn);

        JLabel adduseremail = new JLabel("Email");
        adduseremail.setBounds(500, 130, 170, 40);
        adduseremail.setFont(new Font("Times New Roman", Font.BOLD, 20));
        //adduserrole.setForeground(Color.WHITE); 
        f.add(adduseremail);

        useremail = new JTextField();
        useremail.setBounds(500, 180, 200, 20);
        f.add(useremail);

        JLabel adduserusername = new JLabel("Username");
        adduserusername.setBounds(500, 210, 170, 40);
        adduserusername.setFont(new Font("Times New Roman", Font.BOLD, 20));
        //adduserrole.setForeground(Color.WHITE); 
        f.add(adduserusername);

        userusername = new JTextField();
        userusername.setBounds(500, 260, 200, 20);
        f.add(userusername);

        JLabel adduserpsswd = new JLabel("Password");
        adduserpsswd.setBounds(500, 290, 170, 40);
        adduserpsswd.setFont(new Font("Times New Roman", Font.BOLD, 20));
        //adduserrole.setForeground(Color.WHITE); 
        f.add(adduserpsswd);

        userpsswd = new JTextField();
        userpsswd.setBounds(500, 340, 200, 20);
        f.add(userpsswd);

        JLabel adduseradrs = new JLabel("Address");
        adduseradrs.setBounds(500, 370, 170, 40);
        adduseradrs.setFont(new Font("Times New Roman", Font.BOLD, 20));
        f.add(adduseradrs);

        useradrs = new JTextField();
        useradrs.setBounds(500, 420, 200, 20);
        f.add(useradrs);

        addusersave = new JButton("Save");
        addusersave.setBounds(500, 480, 90, 30);
        addusersave.addActionListener(this);
        f.add(addusersave);

        f.add(l1);
        f.setVisible(true);
        f.setSize(900, 600);
        f.setLocation(300, 150);

    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == addusersave) {
            userRole = userrolee.getText();
            name = usernameadd.getText();
            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            dob = df.format(dc.getDate());
            //dob = userdob.getText();
            mobileNumber = userphn.getText();
            email = useremail.getText();
            username = userusername.getText();
            password = userpsswd.getText();
            address = useradrs.getText();
            if (name.equals("")) {
                JOptionPane.showMessageDialog(null, "Name field is required");
            } else if (dob.equals("")) {
                JOptionPane.showMessageDialog(null, "Date of Birth field is required.");
            } else if (mobileNumber.equals("")) {
                JOptionPane.showMessageDialog(null, "Mobile Number field is required.");
            } else if (!mobileNumber.matches(mobileNumberPattern) || mobileNumber.length() != 10) {
                JOptionPane.showMessageDialog(null, "Mobile Number field is invalid.");
            } else if (email.equals("")) {
                JOptionPane.showMessageDialog(null, "Email field is required.");
            } else if (!email.matches(emailPattern)) {
                JOptionPane.showMessageDialog(null, "Email field is invalid.");
            } else if (username.equals("")) {
                JOptionPane.showMessageDialog(null, "Username field is required.");
            } else if (checkUsername == 1) {
                JOptionPane.showMessageDialog(null, "Username already exist.");
            } else if (password.equals("")) {
                JOptionPane.showMessageDialog(null, "Password field is required.");
            } else if (address.equals("")) {
                JOptionPane.showMessageDialog(null, "Address field is required.");
            } else {
                try {
                    Connection con = ConnectionProvider.getCon();
                    PreparedStatement ps = con.prepareStatement("insert into appuser(userRole,name,dob,mobileNumber,email,username,password,address) values(?,?,?,?,?,?,?,?)");
                    ps.setString(1, userRole);
                    ps.setString(2, name);
                    ps.setString(3, dob);
                    ps.setString(4, mobileNumber);
                    ps.setString(5, email);
                    ps.setString(6, username);
                    ps.setString(7, password);
                    ps.setString(8, address);
                    ps.executeUpdate();
                    JOptionPane.showMessageDialog(null, "User Added Successfully");
                    setVisible(false);
                    new AddUser().setVisible(true);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        }

    }

    public static void main(String args[]) {
        new AddUser().setVisible(true);

    }
}
