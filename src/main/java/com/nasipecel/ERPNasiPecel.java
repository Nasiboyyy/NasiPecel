
package com.nasipecel;

import com.nasipecel.view.DashboardFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class ERPNasiPecel {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(
                        UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            new DashboardFrame().setVisible(true);
        });
    }
}
