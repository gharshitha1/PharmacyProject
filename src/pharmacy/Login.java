package pharmacy;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import dao.ConnectionProvider;

public class Login extends JFrame implements ActionListener {

    JButton login, close;
    JTextField txtusername, txtpassword;
    JLabel l1;
    JFrame f;
  
    public Login() {
        
        f=new JFrame("Login Page");
        f.setBackground(Color.BLACK);
        f.setBounds(0, 0, 13680, 768);
        f.setLayout(null);
        
        l1=new JLabel();
        l1.setBounds(0, 0, 1368, 768);
        l1.setLayout(null);
        
        ImageIcon img=new ImageIcon(ClassLoader.getSystemResource("pharmacy/loginbackground.PNG"));
        Image i9=img.getImage().getScaledInstance(1368, 768, Image.SCALE_SMOOTH);
        ImageIcon img2=new ImageIcon(i9);
        //Inserting Image to Label1
        l1.setIcon(img2);
        
        
        JLabel logintitle = new JLabel("LOGIN");
        logintitle.setBounds(850, 150, 400, 46);
        logintitle.setFont(new Font("Times New Roman", Font.BOLD, 40));
        logintitle.setForeground(Color.ORANGE);
        l1.add(logintitle);

        JLabel lblusername = new JLabel("Username");
        lblusername.setBounds(700, 250, 200, 20);
        lblusername.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lblusername.setForeground(Color.ORANGE);
        l1.add(lblusername);

        txtusername = new JTextField();
        txtusername.setBounds(800, 250, 300, 20);
        txtusername.setBorder(BorderFactory.createLineBorder(Color.black));
        l1.add(txtusername);

        JLabel lblpassword = new JLabel("Password");
        lblpassword.setBounds(700, 350, 100, 20);
        lblpassword.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lblpassword.setForeground(Color.ORANGE);
        l1.add(lblpassword);

        txtpassword = new JTextField();
        txtpassword.setBounds(800, 350, 300, 20);
        txtpassword.setBorder(BorderFactory.createLineBorder(Color.black));
        l1.add(txtpassword);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("images/login.png"));
        Image i2 = i1.getImage().getScaledInstance(20,20 , Image.SCALE_DEFAULT);
        login = new JButton("Login",new ImageIcon(i2));
        login.setBounds(850, 450, 175, 30);
        login.setFont(new Font("Times New Roman", Font.BOLD, 24));
        login.setBorder(BorderFactory.createLineBorder(Color.black));
        login.addActionListener(this);
        login.setFocusPainted(false);
        l1.add(login);

        f.add(l1);//Adding Label1 to Frame
        f.setVisible(true);//Setting the visibility of the Frame
        f.setSize(1368, 768);
        f.setLocation(90, 40);//Location of the Frame


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
                    } else if(rs.getString("userRole").equals("Pharmacist")) {
                        f.setVisible(false);
                        //create jframe PharmacistDashBoard
                        new PharmacistDashboard(username).setVisible(true);
                   
                    }
                }
                if (temp == 0) {
                    JOptionPane.showMessageDialog(null, "Incorrect Username or Password");
                }
            } catch (Exception ee) {
                JOptionPane.showMessageDialog(null, ee);
            }
        }
    }

    public static void main(String args[]) {
        // TODO code application logic here
        new Login();
    }
}