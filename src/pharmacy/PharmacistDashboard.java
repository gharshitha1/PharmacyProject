package pharmacy;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class PharmacistDashboard extends JFrame implements ActionListener {

    JFrame f;
    private String username = "";
    JButton pharmaddmedicine, pharmaprofile, pharmaviewmedicine, pharmaupdatemedicine, pharmalogout, pharmasellmedicine, pharmaviewbill, pharmaexit;

    public PharmacistDashboard(String tempUsername) {
        f = new JFrame("Home Page");
        f.setBackground(Color.BLACK);
        f.setBounds(0, 0, 700, 500);
        f.setLayout(null);

        JLabel pharmadash = new JLabel("DASHBOARD");
        pharmadash.setBounds(520, 80, 400, 60);
        pharmadash.setFont(new Font("Times New Roman", Font.BOLD, 40));
        pharmadash.setForeground(Color.ORANGE);
        f.add(pharmadash);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("images/addMedicine.png"));
        Image i2 = i1.getImage().getScaledInstance(50, 60, Image.SCALE_DEFAULT);
        pharmaddmedicine = new JButton("Add Medicine", new ImageIcon(i2));
        pharmaddmedicine.setBounds(300, 200, 250, 60);
        pharmaddmedicine.setFont(new Font("Times New Roman", Font.BOLD, 20));
        pharmaddmedicine.setBorder(BorderFactory.createLineBorder(Color.black));
        pharmaddmedicine.setFocusPainted(false);
        pharmaddmedicine.addActionListener(this);
        f.add(pharmaddmedicine);

        ImageIcon i5 = new ImageIcon(ClassLoader.getSystemResource("images/viewUser.png"));
        Image i6 = i5.getImage().getScaledInstance(50, 60, Image.SCALE_DEFAULT);
        pharmaviewmedicine = new JButton("View Medicine", new ImageIcon(i6));
        pharmaviewmedicine.setBounds(300, 300, 250, 60);
        pharmaviewmedicine.setFont(new Font("Times New Roman", Font.BOLD, 20));
        pharmaviewmedicine.setBorder(BorderFactory.createLineBorder(Color.black));
        pharmaviewmedicine.setFocusPainted(false);
        pharmaviewmedicine.addActionListener(this);
        //login.addActionListener(this);
        f.add(pharmaviewmedicine);

        ImageIcon i9 = new ImageIcon(ClassLoader.getSystemResource("images/updateUser.png"));
        Image i10 = i9.getImage().getScaledInstance(50, 60, Image.SCALE_DEFAULT);
        pharmaupdatemedicine = new JButton("Update Medicine", new ImageIcon(i10));
        pharmaupdatemedicine.setBounds(300, 400, 250, 60);
        pharmaupdatemedicine.setFont(new Font("Times New Roman", Font.BOLD, 20));
        pharmaupdatemedicine.setBorder(BorderFactory.createLineBorder(Color.black));
        pharmaupdatemedicine.setFocusPainted(false);
        pharmaupdatemedicine.addActionListener(this);
        f.add(pharmaupdatemedicine);

        ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("images/logout.png"));
        Image i8 = i7.getImage().getScaledInstance(50, 60, Image.SCALE_DEFAULT);
        pharmalogout = new JButton("Logout", new ImageIcon(i8));
        pharmalogout.setBounds(300, 500, 250, 60);
        pharmalogout.setFont(new Font("Times New Roman", Font.BOLD, 20));
        pharmalogout.setBorder(BorderFactory.createLineBorder(Color.black));
        pharmalogout.setFocusPainted(false);
        pharmalogout.addActionListener(this);
        f.add(pharmalogout);

        ImageIcon i11 = new ImageIcon(ClassLoader.getSystemResource("images/sellMedicine.png"));
        Image i12 = i11.getImage().getScaledInstance(50, 60, Image.SCALE_DEFAULT);
        pharmasellmedicine = new JButton("Sell Medicine", new ImageIcon(i12));
        pharmasellmedicine.setBounds(700, 200, 250, 60);
        pharmasellmedicine.setFont(new Font("Times New Roman", Font.BOLD, 20));
        pharmasellmedicine.setBorder(BorderFactory.createLineBorder(Color.black));
        pharmasellmedicine.setFocusPainted(false);
        pharmasellmedicine.addActionListener(this);
        f.add(pharmasellmedicine);

        ImageIcon i13 = new ImageIcon(ClassLoader.getSystemResource("images/viewBill.png"));
        Image i14 = i13.getImage().getScaledInstance(50, 60, Image.SCALE_DEFAULT);
        pharmaviewbill = new JButton("View Bill", new ImageIcon(i14));
        pharmaviewbill.setBounds(700, 300, 250, 60);
        pharmaviewbill.setFont(new Font("Times New Roman", Font.BOLD, 20));
        pharmaviewbill.setBorder(BorderFactory.createLineBorder(Color.black));
        pharmaviewbill.setFocusPainted(false);
        pharmaviewbill.addActionListener(this);
        //login.addActionListener(this);
        f.add(pharmaviewbill);

        ImageIcon i15 = new ImageIcon(ClassLoader.getSystemResource("images/profile.png"));
        Image i16 = i15.getImage().getScaledInstance(50, 60, Image.SCALE_DEFAULT);
        pharmaprofile = new JButton("Profile", new ImageIcon(i16));
        pharmaprofile.setBounds(700, 400, 250, 60);
        pharmaprofile.setFont(new Font("Times New Roman", Font.BOLD, 20));
        pharmaprofile.setBorder(BorderFactory.createLineBorder(Color.black));
        pharmaprofile.setFocusPainted(false);
        pharmaprofile.addActionListener(this);
        f.add(pharmaprofile);

        ImageIcon i17 = new ImageIcon(ClassLoader.getSystemResource("images/exit.png"));
        Image i18 = i17.getImage().getScaledInstance(50, 60, Image.SCALE_DEFAULT);
        pharmaexit = new JButton("Exit", new ImageIcon(i18));
        pharmaexit.setBounds(700, 500, 250, 60);
        pharmaexit.setFont(new Font("Times New Roman", Font.BOLD, 20));
        pharmaexit.setBorder(BorderFactory.createLineBorder(Color.black));
        pharmaexit.setFocusPainted(false);
        pharmaexit.addActionListener(this);
        f.add(pharmaexit);

        f.setVisible(true);//Setting the visibility of the Frame
        f.setSize(1300, 768);
        f.setLocation(90, 40);
        f.getContentPane().setBackground(Color.BLACK);

        username = tempUsername;
    }

    private void medicineName(String nameOrUniqueId) {

    }

    public void actionPerformed(ActionEvent ae) {

        String msg = ae.getActionCommand();
        if (msg.equals("Logout")) {
            f.setVisible(false);
            new Login();
        } else if (msg.equals("Exit")) {
            f.setVisible(false);
        } else if (msg.equals("Profile")) {

            new Profile(username).setVisible(true);
        } else if (msg.equals("Add Medicine")) {
            new AddMedicine().setVisible(true);
        } else if (msg.equals("View Medicine")) {
            new ViewMedicine(username).setVisible(true);
        } else if (msg.equals("Update Medicine")) {

            new UpdateMedicine().setVisible(true);
        } else if (msg.equals("Sell Medicine")) {
            new SellMedicine(username).setVisible(true);
        } else if (msg.equals("View Bill")) {
            new ViewBill().setVisible(true);
        }

    }

    public static void main(String args[]) {
        new PharmacistDashboard("pharmausername");
    }
}
