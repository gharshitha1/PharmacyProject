package pharmacy;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.*;
import dao.ConnectionProvider;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class Profile extends JFrame implements ActionListener {

    JFrame f;
    JLabel imagee, adduserrolee, profilenum, profileemail, profiladdress;
    JTextField userrolee, profilephn, profilemail, profieadrs;
    JButton updatebutton, close;
    private String emailPattern = "^[a-zA-Z0-9]+[@]+[a-zA-Z0-9]+[.]+[a-zA-Z0-9]+$";
    private String mobileNumberPattern = "^[0-9]*$";
    private String username = "";

    public Profile(String tempUsername) {
        f = new JFrame("Home Page");
        f.setBackground(Color.BLACK);
        f.setBounds(0, 0, 700, 500);
        f.setLayout(null);

        JLabel admindash = new JLabel("PROFILE");
        admindash.setBounds(350, 30, 400, 60);
        admindash.setFont(new Font("Times New Roman", Font.BOLD, 45));
        admindash.setForeground(Color.ORANGE);
        f.add(admindash);

        ImageIcon i5 = new ImageIcon(ClassLoader.getSystemResource("images/profile.png"));
        Image i6 = i5.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT);
        imagee = new JLabel(new ImageIcon(i6));
        imagee.setBackground(Color.white);
        imagee.setForeground(Color.black);
        imagee.setBounds(150, 140, 150, 150);
        f.add(imagee);

        adduserrolee = new JLabel("Name");
        adduserrolee.setBounds(450, 110, 170, 40);
        adduserrolee.setFont(new Font("Times New Roman", Font.BOLD, 20));
        f.add(adduserrolee);

        userrolee = new JTextField();
        userrolee.setBounds(450, 160, 250, 20);
        userrolee.setBorder(BorderFactory.createLineBorder(Color.black));
        f.add(userrolee);

        profilenum = new JLabel("Mobile Number");
        profilenum.setBounds(450, 190, 170, 40);
        profilenum.setFont(new Font("Times New Roman", Font.BOLD, 20));
        f.add(profilenum);

        profilephn = new JTextField();
        profilephn.setBounds(450, 240, 250, 20);
        profilephn.setBorder(BorderFactory.createLineBorder(Color.black));
        f.add(profilephn);

        profileemail = new JLabel("Email");
        profileemail.setBounds(450, 270, 170, 40);
        profileemail.setFont(new Font("Times New Roman", Font.BOLD, 20));
        f.add(profileemail);

        profilemail = new JTextField();
        profilemail.setBounds(450, 320, 250, 20);
        profilemail.setBorder(BorderFactory.createLineBorder(Color.black));
        f.add(profilemail);

        profiladdress = new JLabel("Address");
        profiladdress.setBounds(450, 350, 170, 40);
        profiladdress.setFont(new Font("Times New Roman", Font.BOLD, 20));
        f.add(profiladdress);

        profieadrs = new JTextField();
        profieadrs.setBounds(450, 400, 250, 20);
        profieadrs.setBorder(BorderFactory.createLineBorder(Color.black));
        f.add(profieadrs);

        updatebutton = new JButton("Update");
        updatebutton.setBounds(620, 480, 90, 30);
        updatebutton.addActionListener(this);
        f.add(updatebutton);

        ImageIcon i3 = new ImageIcon(ClassLoader.getSystemResource("images/no.png"));
        Image i4 = i3.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        close = new JButton(new ImageIcon(i4));
        close.setBounds(800, 30, 20, 20);
        close.addActionListener(this);
        f.add(close);

        username = tempUsername;

        try {
            Connection con = ConnectionProvider.getCon();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from appuser where username='" + username + "'");
            while (rs.next()) {
                userrolee.setText(rs.getString("name"));
                profilephn.setText(rs.getString("mobileNumber"));
                profilemail.setText(rs.getString("email"));
                profieadrs.setText(rs.getString("address"));

            }
        } catch (Exception ee) {
            //JOptionPane.showMessageDialog(null, ee);
            ee.printStackTrace();
        }

        f.setVisible(true);
        f.setSize(900, 600);
        f.setLocation(300, 150);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == close) {
            f.setVisible(false);
        } else if (e.getSource() == updatebutton) {
            String name = userrolee.getText();
            String mobileNumber = profilephn.getText();
            String email = profilemail.getText();
            String address = profieadrs.getText();
            if (name.equals("")) {
                JOptionPane.showMessageDialog(null, "Name field is required");
            } else if (mobileNumber.equals("")) {
                JOptionPane.showMessageDialog(null, "Mobile Number field is required.");
            } else if (!mobileNumber.matches(mobileNumberPattern) || mobileNumber.length() != 10) {
                JOptionPane.showMessageDialog(null, "Mobile Number field is invalid.");
            } else if (email.equals("")) {
                JOptionPane.showMessageDialog(null, "Email field is required.");
            } else if (!email.matches(emailPattern)) {
                JOptionPane.showMessageDialog(null, "Email field is invalid.");
            } else if (address.equals("")) {
                JOptionPane.showMessageDialog(null, "Address field is required.");
            }else{
                try {
                    Connection con = ConnectionProvider.getCon();
                    PreparedStatement ps = con.prepareStatement("update appuser set name=?,mobileNumber=?,email=?,address=? where username=?");
                    ps.setString(1, name);
                    ps.setString(2, mobileNumber);
                    ps.setString(3, email);
                    ps.setString(4, address);
                    ps.setString(5, username);
                    ps.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Profile Updated Successfully");
                    setVisible(false);
                    new Profile(username).setVisible(true);
                } catch (Exception eeee) {
                    JOptionPane.showMessageDialog(null, eeee);
                }
            }
        }
    }

    public static void main(String args[]) {
        new Profile("profileusername");
    }
}
