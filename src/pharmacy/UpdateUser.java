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

public class UpdateUser extends JFrame implements ActionListener {

    JFrame f;
    JDateChooser dc;
    JButton search, updatebutton;
    JTextField usernameenter, usernameadd, userphn, useradrs, useremail,userrolee;
    private String emailPattern = "^[a-zA-Z0-9]+[@]+[a-zA-Z0-9]+[.]+[a-zA-Z0-9]+$";
    private String mobileNumberPattern = "^[0-9]*$";

    public UpdateUser() {
        f = new JFrame("Update User");
        f.setBackground(Color.WHITE);
        f.setBounds(0, 0, 700, 500);
        f.setLayout(null);

        JLabel updateuser = new JLabel("UPDATE USER");
        updateuser.setBounds(320, 30, 300, 40);
        updateuser.setFont(new Font("Times New Roman", Font.BOLD, 36));
        updateuser.setForeground(Color.ORANGE);
        f.add(updateuser);

        JLabel updateusername = new JLabel("Username");
        updateusername.setBounds(280, 140, 170, 20);
        updateusername.setFont(new Font("Times New Roman", Font.BOLD, 20));
        //adduserrole.setForeground(Color.WHITE); 
        f.add(updateusername);

        usernameenter = new JTextField();
        usernameenter.setBounds(380, 140, 200, 24);
        usernameenter.setBorder(BorderFactory.createLineBorder(Color.black));
        f.add(usernameenter);

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

        JLabel adduserrolee = new JLabel("User Role");
        adduserrolee.setBounds(200, 230, 170, 40);
        adduserrolee.setFont(new Font("Times New Roman", Font.BOLD, 20));
        f.add(adduserrolee);

        userrolee = new JTextField();
        userrolee.setBounds(200, 280, 200, 20);
        userrolee.setBorder(BorderFactory.createLineBorder(Color.black));
        f.add(userrolee);

        JLabel addusername = new JLabel("Name");
        addusername.setBounds(200, 310, 170, 40);
        addusername.setFont(new Font("Times New Roman", Font.BOLD, 20)); 
        f.add(addusername);

        usernameadd = new JTextField();
        usernameadd.setBounds(200, 360, 200, 20);
        f.add(usernameadd);

        JLabel adduserdob = new JLabel("DOB");
        adduserdob.setBounds(200, 390, 170, 40);
        adduserdob.setFont(new Font("Times New Roman", Font.BOLD, 20));
        f.add(adduserdob);

        dc = new JDateChooser();
        dc.setBounds(200, 440, 200, 20);
        dc.setForeground(Color.BLACK);
        dc.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        f.add(dc);

        JLabel adduserphn = new JLabel("Phone Number");
        adduserphn.setBounds(570, 230, 170, 40);
        adduserphn.setFont(new Font("Times New Roman", Font.BOLD, 20));
        f.add(adduserphn);

        userphn = new JTextField();
        userphn.setBounds(570, 280, 200, 20);
        f.add(userphn);

        JLabel adduseremail = new JLabel("Email");
        adduseremail.setBounds(570, 310, 170, 40);
        adduseremail.setFont(new Font("Times New Roman", Font.BOLD, 20));
        //adduserrole.setForeground(Color.WHITE); 
        f.add(adduseremail);

        useremail = new JTextField();
        useremail.setBounds(570, 360, 200, 20);
        f.add(useremail);

        JLabel adduseradrs = new JLabel("Address");
        adduseradrs.setBounds(570, 390, 170, 40);
        adduseradrs.setFont(new Font("Times New Roman", Font.BOLD, 20));
        f.add(adduseradrs);

        useradrs = new JTextField();
        useradrs.setBounds(570, 440, 200, 20);
        f.add(useradrs);

        updatebutton = new JButton("Update");
        updatebutton.setBounds(620, 480, 90, 30);
        updatebutton.addActionListener(this);
        f.add(updatebutton);

        f.setSize(1000, 650);
        f.setLocation(300, 120);
        //f.getContentPane().setBackground(Color.WHITE);
        f.setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == search) {
            int checkUserExist = 0;
            String username = usernameenter.getText();
            if (username.equals("")) {
                JOptionPane.showMessageDialog(null, "Username field is required.");
            } else {
                SimpleDateFormat dFormat = new SimpleDateFormat("dd-mm-yyyy");
                try {
                    Connection con = ConnectionProvider.getCon();
                    Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery("select * from appuser where username='" + username + "'");
                    while (rs.next()) {
                        usernameenter.setEditable(false);
                        checkUserExist = 1;
                        usernameadd.setText(rs.getString("name"));
                        userphn.setText(rs.getString("mobileNumber"));
                        useremail.setText(rs.getString("email"));
                        useradrs.setText(rs.getString("address"));
                        String dob = rs.getString("dob");
                        if (dob != "") {
                            try {
                                dc.setDate(dFormat.parse(dob));
                            } catch (Exception eee) {
                            }
                        }
//                        if (rs.getString("userRole").equals("Admin")) {
//                            comboUserRole.removeAllItems();
//                            comboUserRole.addItem("Admin");
//                            comboUserRole.addItem("Pharmacist");
//                        } else {
//                            comboUserRole.removeAllItems();
//                            comboUserRole.addItem("Pharmacist");
//                            comboUserRole.addItem("Admin");
//                        }
                    }
                } catch (Exception ee) {
                    JOptionPane.showMessageDialog(null, ee);
                }
                if (checkUserExist == 0) {
                    JOptionPane.showMessageDialog(null, "Username does not exist.");
                }
            }
        } else if (e.getSource() == updatebutton) {
            String userRole = userrolee.getText();
            String name = usernameadd.getText();
            SimpleDateFormat dFormat = new SimpleDateFormat("dd-mm-yyyy");
            Date date = dc.getDate();
            String dob = "";
            if (date != null) {
                dob = dFormat.format(dc.getDate());
            }
            String mobileNumber = userphn.getText();
            String email = useremail.getText();
            String username = usernameenter.getText();
            String address = useradrs.getText();

            if (name.equals("")) {
                JOptionPane.showMessageDialog(null, "Name field is required.");
            } else if (dob.equals("")) {
                JOptionPane.showMessageDialog(null, "Date of Birth field is required");
            } else if (mobileNumber.equals("")) {
                JOptionPane.showMessageDialog(null, "Mobile Number field is required");
            } else if (!mobileNumber.matches(mobileNumberPattern) || mobileNumber.length() != 10) {
                JOptionPane.showMessageDialog(null, "Mobile Number field is invalid.");
            } else if (email.equals("")) {
                JOptionPane.showMessageDialog(null, "Email field is required");
            } else if (!email.matches(emailPattern)) {
                JOptionPane.showMessageDialog(null, "Email field is invalid.");
            } else if (username.equals("")) {
                JOptionPane.showMessageDialog(null, "Username field is required");
            } else if (address.equals("")) {
                JOptionPane.showMessageDialog(null, "Address field is required");
            } else {
                try {
                    Connection con = ConnectionProvider.getCon();
                    PreparedStatement ps = con.prepareStatement("update appuser set userRole=?,name=?,dob=?,mobileNumber=?,email=?,address=? where username=?");
                    ps.setString(1, userRole);
                    ps.setString(2, name);
                    ps.setString(3, dob);
                    ps.setString(4, mobileNumber);
                    ps.setString(5, email);
                    ps.setString(6, address);
                    ps.setString(7, username);
                    ps.executeUpdate();
                    JOptionPane.showMessageDialog(null, "User Updated Successfully");
                    setVisible(false);
                    new UpdateUser().setVisible(true);
                } catch (Exception eeee) {
                    JOptionPane.showMessageDialog(null, eeee);
                }
            }
        }
    }

    public static void main(String args[]) {
        new UpdateUser();
    }
}
