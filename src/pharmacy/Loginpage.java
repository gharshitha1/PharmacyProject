package pharmacy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import dao.ConnectionProvider;

public class Loginpage extends JFrame implements ActionListener {

    JButton login, close, signup;
    JTextField txtusername, txtpassword;
    JLabel backgroundLabel,l1;
    JFrame f;
    public Loginpage() {
        
        f=new JFrame("Login Page");
        f.setBackground(Color.BLACK);
        f.setBounds(0, 0, 700, 500);
        f.setLayout(null);
        
        l1=new JLabel();
        l1.setBounds(0, 0, 1000, 768);
        l1.setLayout(null);
        

        
        JLabel titlee = new JLabel("Pharmacy Management System");
        titlee.setBounds(120, 50, 600, 40);
        titlee.setFont(new Font("Times New Roman", Font.BOLD, 40));
        titlee.setForeground(Color.ORANGE);
        l1.add(titlee);
        
        JLabel logintitle = new JLabel("LOGIN");
        logintitle.setBounds(320, 150, 300, 40);
        logintitle.setFont(new Font("Times New Roman", Font.BOLD, 32));
        logintitle.setForeground(Color.ORANGE);
        l1.add(logintitle);

        JLabel lblusername = new JLabel("Username");
        lblusername.setBounds(200, 250, 100, 20);
        lblusername.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lblusername.setForeground(Color.ORANGE);
        l1.add(lblusername);

        txtusername = new JTextField();
        txtusername.setBounds(300, 250, 300, 20);
        l1.add(txtusername);

        JLabel lblpassword = new JLabel("Password");
        lblpassword.setBounds(200, 350, 100, 20);
        lblpassword.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lblpassword.setForeground(Color.ORANGE);
        l1.add(lblpassword);

        txtpassword = new JTextField();
        txtpassword.setBounds(300, 350, 300, 20);
        l1.add(txtpassword);

        login = new JButton("Login");
        login.setBounds(200, 450, 100, 20);
        login.addActionListener(this);
        l1.add(login);

        close = new JButton("Close");
        close.setBounds(400, 450, 100, 20);
        close.addActionListener(this);
        l1.add(close);
        
        f.add(l1);//Adding Label1 to Frame
        f.setVisible(true);//Setting the visibility of the Frame
        f.setSize(800, 600);
        f.setLocation(300, 100);//Location of the Frame
        f.getContentPane().setBackground(Color.BLACK);

    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == login) {
            String username = txtusername.getText();
            String password = txtpassword.getText();
            int temp = 0;

            try {
                Connection con = ConnectionProvider.getCon();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("select * from appuser where username='" + username + "' and password='" + password + "'");
                while (rs.next()) {
                    temp = 1;
                    if (rs.getString("userRole").equals("Admin")) {
                        f.setVisible(false);
                        //create jframe admindashboard
                        new AdminDashboard(username).setVisible(true);
                    } else {
                        setVisible(false);
                        //create jframe PharmacistDashBoard
                        new PharmacistDashboard().setVisible(true);
                    }
                }
                if (temp == 0) {
                    JOptionPane.showMessageDialog(null, "Incorrect Username or Password");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
        else if (ae.getSource() == close){
            f.setVisible(false);
        }
    }

    public static void main(String args[]) {
        // TODO code application logic here
        new Loginpage();
    }
}