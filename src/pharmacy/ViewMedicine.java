package pharmacy;

import dao.ConnectionProvider;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import javax.swing.*;
import javax.swing.table.*;

public class ViewMedicine extends JFrame implements ActionListener {

    JFrame f;
    JTable t;
    JButton close;
    private String username = "";
    public String usernameTable;

    public ViewMedicine(String tempUsername) {

        f = new JFrame("Stock Details");
        f.setBackground(Color.WHITE);
        f.setBounds(0, 0, 800, 600);
        f.setLayout(null);

        JLabel viewuser = new JLabel("VIEW MEDICINES");
        viewuser.setBounds(400, 30, 500, 50);
        viewuser.setFont(new Font("Times New Roman", Font.BOLD, 36));
        viewuser.setForeground(Color.ORANGE);
        f.add(viewuser);

        DefaultTableModel modell = new DefaultTableModel();
        t = new JTable(modell);
        t.setFont(new Font("Arial", Font.PLAIN, 15));
        t.setRowHeight(20);

        JScrollPane scrollPane = new JScrollPane(t); // Add the JTable to a JScrollPane
        scrollPane.setBounds(40, 100, 1100, 450);

        JTableHeader header = t.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 15));

        try {
            Connection con = ConnectionProvider.getCon();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from medicine");
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                modell.addColumn(metaData.getColumnName(i));
            }
            while (rs.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    row[i - 1] = rs.getObject(i);
                }
                modell.addRow(row);
            }

        } catch (Exception ee) {
            JOptionPane.showMessageDialog(null, ee);
        }

//        ImageIcon i3 = new ImageIcon(ClassLoader.getSystemResource("images/no.png"));
//        Image i4 = i3.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
//        close = new JButton(new ImageIcon(i4));
//        close.setBorder(BorderFactory.createLineBorder(Color.black));
//        close.setBounds(1050, 30, 20, 20);
//        close.addActionListener(this);
//        f.add(close);

        JLabel deleteuser = new JLabel("Click on row to Delete Medicine");
        deleteuser.setBounds(400, 580, 400, 40);
        deleteuser.setFont(new Font("Times New Roman", Font.BOLD, 25));
        deleteuser.setForeground(Color.ORANGE);
        f.add(deleteuser);

        t.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                jTableMouseClicked(e);
            }
        });

        f.setSize(1200, 700);
        f.setLocation(100, 120);
        f.setVisible(true);
        f.add(scrollPane);

    }

    public void actionPerformed(ActionEvent e) {
//        if (e.getSource() == close) {
//            f.setVisible(false);
//        }
    }

    public void jTableMouseClicked(java.awt.event.MouseEvent evt) {
        int index = t.getSelectedRow();
        TableModel model = t.getModel();
        String id = model.getValueAt(index, 0).toString();
        usernameTable = model.getValueAt(index, 6).toString();
//        if (username.equals(usernameTable)) {
//            JOptionPane.showMessageDialog(null, "You can't delete your own account");
//        } else {
            int a = JOptionPane.showConfirmDialog(null, "Do you want to delete this medicine", "Select", JOptionPane.YES_NO_OPTION);
            if (a == 0) {
                try {
                    Connection con = ConnectionProvider.getCon();
                    PreparedStatement ps = con.prepareStatement("DELETE FROM medicine where medicine_pk=?");
                    ps.setString(1, id);
                    ps.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Medicine Successfully Deleted");
                    setVisible(false);
                    new ViewMedicine(username).setVisible(true);
                } catch (Exception eee) {
                    JOptionPane.showMessageDialog(null, eee);
                }
            }
        
    }

    public static void main(String args[]) {
        new ViewMedicine("Username");
    }
}
