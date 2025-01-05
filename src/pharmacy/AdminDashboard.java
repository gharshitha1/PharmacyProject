package pharmacy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

public class AdminDashboard extends JFrame implements ActionListener {

    JButton adminadduser, adminprofile, adminviewuser, adminlogout, adminexit, adminupdateuser;
    private String username = "";
    JFrame f;
    JLabel l1, l2;
    private AddUser addUserFrame;

    public AdminDashboard(String tempUsername) {
        f = new JFrame("Home Page");
        f.setBackground(Color.BLACK);
        f.setBounds(0, 0, 700, 500);
        f.setLayout(null);

        l1 = new JLabel();
        l2 = new JLabel();
        l1.setBounds(0, 0, 1366, 768);
        l1.setLayout(null);

        JLabel admindash = new JLabel("DASHBOARD");
        admindash.setBounds(520, 100, 400, 60);
        admindash.setFont(new Font("Times New Roman", Font.BOLD, 40));
        admindash.setForeground(Color.ORANGE);
        f.add(admindash);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("images/addUser.png"));
        Image i2 = i1.getImage().getScaledInstance(50, 60, Image.SCALE_DEFAULT);
        adminadduser = new JButton("Add User", new ImageIcon(i2));
        adminadduser.setBounds(300, 280, 250, 60);
        adminadduser.setFont(new Font("Times New Roman", Font.BOLD, 20));
        adminadduser.setBorder(BorderFactory.createLineBorder(Color.black));
        adminadduser.setFocusPainted(false);
        adminadduser.addActionListener(this);
        f.add(adminadduser);

        ImageIcon i3 = new ImageIcon(ClassLoader.getSystemResource("images/profile.png"));
        Image i4 = i3.getImage().getScaledInstance(50, 60, Image.SCALE_DEFAULT);
        adminprofile = new JButton(" Profile", new ImageIcon(i4));
        adminprofile.setBounds(700, 280, 250, 60);
        adminprofile.setFont(new Font("Times New Roman", Font.BOLD, 20));
        adminprofile.setBorder(BorderFactory.createLineBorder(Color.black));
        adminprofile.setFocusPainted(false);
        adminprofile.addActionListener(this);
        f.add(adminprofile);

        ImageIcon i5 = new ImageIcon(ClassLoader.getSystemResource("images/viewUser.png"));
        Image i6 = i5.getImage().getScaledInstance(50, 60, Image.SCALE_DEFAULT);
        adminviewuser = new JButton("View User", new ImageIcon(i6));
        adminviewuser.setBounds(300, 380, 250, 60);
        adminviewuser.setFont(new Font("Times New Roman", Font.BOLD, 20));
        adminviewuser.setBorder(BorderFactory.createLineBorder(Color.black));
        adminviewuser.setFocusPainted(false);
        adminviewuser.addActionListener(this);
        f.add(adminviewuser);

        ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("images/logout.png"));
        Image i8 = i7.getImage().getScaledInstance(50, 60, Image.SCALE_DEFAULT);
        adminlogout = new JButton("Logout", new ImageIcon(i8));
        adminlogout.setBounds(700, 380, 250, 60);
        adminlogout.setFont(new Font("Times New Roman", Font.BOLD, 20));
        adminlogout.setBorder(BorderFactory.createLineBorder(Color.black));
        adminlogout.setFocusPainted(false);
        adminlogout.addActionListener(this);
        f.add(adminlogout);

        ImageIcon i9 = new ImageIcon(ClassLoader.getSystemResource("images/updateUser.png"));
        Image i10 = i9.getImage().getScaledInstance(50, 60, Image.SCALE_DEFAULT);
        adminupdateuser = new JButton("Update User", new ImageIcon(i10));
        adminupdateuser.setBounds(300, 480, 250, 60);
        adminupdateuser.setFont(new Font("Times New Roman", Font.BOLD, 20));
        adminupdateuser.setBorder(BorderFactory.createLineBorder(Color.black));
        adminupdateuser.setFocusPainted(false);
        adminupdateuser.addActionListener(this);
        f.add(adminupdateuser);

        ImageIcon i11 = new ImageIcon(ClassLoader.getSystemResource("images/exit.png"));
        Image i12 = i11.getImage().getScaledInstance(50, 60, Image.SCALE_DEFAULT);
        adminexit = new JButton("Exit", new ImageIcon(i12));
        adminexit.setBounds(700, 480, 250, 60);
        adminexit.setFont(new Font("Times New Roman", Font.BOLD, 20));
        adminexit.setBorder(BorderFactory.createLineBorder(Color.black));
        adminexit.setFocusPainted(false);
        adminexit.addActionListener(this);
        f.add(adminexit);

        f.setVisible(true);
        f.setSize(1300, 768);
        f.setLocation(90, 40);
        f.getContentPane().setBackground(Color.BLACK);

        username = tempUsername;

    }

    public void actionPerformed(ActionEvent ae) {
        String msg = ae.getActionCommand();
        if (msg.equals("Logout")) {
            f.setVisible(false);
            new Login();
        } else if (msg.equals("Exit")) {
            f.setVisible(false);
            //new Login();
        } else if (msg.equals("Add User")) {
            new AddUser().setVisible(true);

        } else if (msg.equals("View User")) {

            new ViewUser(username).setVisible(true);

        } else if (msg.equals("Update User")) {

            new UpdateUser().setVisible(true);
        } else if (msg.equals(" Profile")) {

            new Profile(username).setVisible(true);
        }
    }

    public static void main(String args[]) {
        new AdminDashboard("adminUsername");
    }
}
