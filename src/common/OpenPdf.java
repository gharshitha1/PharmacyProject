//package common;
//
//import java.io.File;
//import javax.swing.JOptionPane;
//import dao.PharmacyUtils;
//
//public class OpenPdf {
//
//    public static void openById(String id) {
//        try {
//            if ((new File(PharmacyUtils.billPath + id + ".pdf")).exists()) {
//                Process p = Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + PharmacyUtils.billPath + id + ".pdf");
//            } else {
//                JOptionPane.showMessageDialog(null, "File does not exist.");
//            }
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, e);
//        }
//    }
//}
package common;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;
import dao.PharmacyUtils;

public class OpenPdf {

    public static void openById(String id) {
        try {
            File file = new File(PharmacyUtils.billPath + id + ".pdf");
            if (file.exists()) {
                Desktop.getDesktop().open(file);
            } else {
                JOptionPane.showMessageDialog(null, "File does not exist.");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
}
