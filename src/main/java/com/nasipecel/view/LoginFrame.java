
package com.nasipecel.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Arrays;

public class LoginFrame extends JFrame {

    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private boolean passwordVisible = false;

    // TEMA WARNA
    private final Color green = new Color(25, 65, 43);
    private final Color greenHover = new Color(49, 105, 68);
    private final Color cream = new Color(255, 255, 250);
    private final Color background = new Color(246, 244, 235);
    private final Color dark = new Color(30, 40, 32);
    private final Color muted = new Color(85, 94, 86);
    private final Color borderColor = new Color(205, 213, 201);

    public LoginFrame() {

        setTitle("ERP Nasi Pecel | Login Admin");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(960, 600);
        setMinimumSize(new Dimension(800, 540));
        setLocationRelativeTo(null);

        JPanel root = new JPanel(new GridBagLayout());
        root.setBackground(background);
        root.setBorder(new EmptyBorder(25, 25, 25, 25));

        JPanel card = new JPanel(new GridLayout(1, 2));
        card.setBackground(cream);
        card.setBorder(
                BorderFactory.createLineBorder(borderColor, 1)
        );

        card.add(createBrandPanel());
        card.add(createLoginPanel());

        root.add(card);
        setContentPane(root);
    }

    // PANEL BRANDING
    private JPanel createBrandPanel() {

        JPanel panel = new JPanel();

        panel.setBackground(green);
        panel.setLayout(new BoxLayout(
                panel, BoxLayout.Y_AXIS
        ));

        panel.setBorder(
                new EmptyBorder(42, 35, 30, 30)
        );

        JLabel badge = new JLabel(
                "  BUSINESS MANAGEMENT SYSTEM  "
        );

        badge.setOpaque(true);
        badge.setBackground(new Color(49, 105, 68));
        badge.setForeground(Color.WHITE);
        badge.setFont(new Font(
                "SansSerif", Font.BOLD, 11
        ));

        badge.setBorder(
                new EmptyBorder(10, 8, 10, 8)
        );

        badge.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel logo = new JLabel("NP");

        logo.setOpaque(true);
        logo.setBackground(new Color(241, 225, 184));
        logo.setForeground(green);
        logo.setFont(new Font(
                "SansSerif", Font.BOLD, 30
        ));

        logo.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        logo.setPreferredSize(new Dimension(70, 70));
        logo.setMinimumSize(new Dimension(70, 70));
        logo.setMaximumSize(new Dimension(70, 70));

        logo.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel title = new JLabel(
                "<html>ERP NASI<br>PECEL</html>"
        );

        title.setFont(new Font(
                "SansSerif", Font.BOLD, 38
        ));

        title.setForeground(Color.WHITE);
        title.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel description = new JLabel(
                "<html><div style='width:290px; line-height:1.6;'>"
                + "Kelola operasional usaha kuliner Anda "
                + "dengan lebih mudah, cepat, dan terorganisir."
                + "</div></html>"
        );

        description.setFont(new Font(
                "SansSerif", Font.PLAIN, 14
        ));

        description.setForeground(
                new Color(235, 242, 232)
        );

        description.setAlignmentX(Component.LEFT_ALIGNMENT);

        panel.add(badge);
        panel.add(Box.createVerticalStrut(32));
        panel.add(logo);
        panel.add(Box.createVerticalStrut(23));
        panel.add(title);
        panel.add(Box.createVerticalStrut(12));
        panel.add(description);

        panel.add(Box.createVerticalGlue());

        panel.add(createFeature("01", "Transaksi penjualan"));
        panel.add(Box.createVerticalStrut(18));

        panel.add(createFeature("02", "Persediaan bahan baku"));
        panel.add(Box.createVerticalStrut(18));

        panel.add(createFeature("03", "Laporan usaha harian"));
        panel.add(Box.createVerticalStrut(30));

        JLabel footer = new JLabel(
                "NASI PECEL MANAGEMENT"
        );

        footer.setFont(new Font(
                "SansSerif", Font.BOLD, 11
        ));

        footer.setForeground(
                new Color(220, 232, 215)
        );

        footer.setAlignmentX(Component.LEFT_ALIGNMENT);

        panel.add(footer);

        return panel;
    }

    // FITUR APLIKASI
    private JPanel createFeature(
            String number, String text) {

        JPanel row = new JPanel(
                new FlowLayout(FlowLayout.LEFT, 12, 0)
        );

        row.setOpaque(false);
        row.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel num = new JLabel(number);

        num.setFont(new Font(
                "SansSerif", Font.BOLD, 14
        ));

        num.setForeground(
                new Color(245, 218, 157)
        );

        JLabel label = new JLabel(text);

        label.setFont(new Font(
                "SansSerif", Font.PLAIN, 14
        ));

        label.setForeground(Color.WHITE);

        row.add(num);
        row.add(label);

        return row;
    }

    // PANEL LOGIN
    private JPanel createLoginPanel() {

        JPanel panel = new JPanel(new GridBagLayout());

        panel.setBackground(cream);

        panel.setBorder(
                new EmptyBorder(30, 35, 30, 35)
        );

        JPanel form = new JPanel();

        form.setBackground(cream);

        form.setLayout(new BoxLayout(
                form, BoxLayout.Y_AXIS
        ));

        JLabel title = new JLabel("Selamat datang!");

        title.setFont(new Font(
                "SansSerif", Font.BOLD, 29
        ));

        title.setForeground(dark);
        title.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel subtitle = new JLabel(
                "Silakan masuk ke akun administrator."
        );

        subtitle.setFont(new Font(
                "SansSerif", Font.PLAIN, 14
        ));

        subtitle.setForeground(muted);
        subtitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        // USERNAME
        txtUsername = new JTextField();

        txtUsername.setFont(new Font(
                "SansSerif", Font.PLAIN, 15
        ));

        txtUsername.setForeground(dark);
        txtUsername.setCaretColor(green);

        txtUsername.setBorder(
                new EmptyBorder(0, 12, 0, 12)
        );

        JPanel usernameBox = createInputBox();

        usernameBox.add(
                txtUsername, BorderLayout.CENTER
        );

        // PASSWORD
        txtPassword = new JPasswordField();

        txtPassword.setFont(new Font(
                "SansSerif", Font.PLAIN, 15
        ));

        txtPassword.setForeground(dark);
        txtPassword.setCaretColor(green);
        txtPassword.setEchoChar('\u2022');

        txtPassword.setBorder(
                new EmptyBorder(0, 12, 0, 5)
        );

        JPanel passwordBox = createInputBox();

        passwordBox.add(
                txtPassword, BorderLayout.CENTER
        );

        // TOMBOL LIHAT PASSWORD
        JButton toggle = new JButton("Lihat");

        toggle.setFont(new Font(
                "SansSerif", Font.BOLD, 12
        ));

        toggle.setForeground(green);
        toggle.setBackground(Color.WHITE);

        toggle.setBorder(
                new EmptyBorder(0, 8, 0, 10)
        );

        toggle.setFocusPainted(false);

        toggle.setCursor(
                new Cursor(Cursor.HAND_CURSOR)
        );

        toggle.addActionListener(e -> {

            passwordVisible = !passwordVisible;

            txtPassword.setEchoChar(
                    passwordVisible ? (char) 0 : '\u2022'
            );

            toggle.setText(
                    passwordVisible ? "Tutup" : "Lihat"
            );
        });

        passwordBox.add(
                toggle, BorderLayout.EAST
        );

        // TOMBOL LOGIN
        JButton loginButton = new JButton(
                "MASUK KE DASHBOARD  →"
        );

        loginButton.setFont(new Font(
                "SansSerif", Font.BOLD, 14
        ));

        loginButton.setForeground(Color.WHITE);
        loginButton.setBackground(green);

        loginButton.setFocusPainted(false);
        loginButton.setBorderPainted(false);

        loginButton.setCursor(
                new Cursor(Cursor.HAND_CURSOR)
        );

        loginButton.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        loginButton.setMaximumSize(
                new Dimension(Integer.MAX_VALUE, 49)
        );

        loginButton.setPreferredSize(
                new Dimension(300, 49)
        );

        loginButton.addMouseListener(
                new java.awt.event.MouseAdapter() {

            @Override
            public void mouseEntered(
                    java.awt.event.MouseEvent e) {

                loginButton.setBackground(greenHover);
            }

            @Override
            public void mouseExited(
                    java.awt.event.MouseEvent e) {

                loginButton.setBackground(green);
            }
        });

        loginButton.addActionListener(
                e -> prosesLogin()
        );

        txtPassword.addActionListener(
                e -> prosesLogin()
        );

        // LABEL FORM
        JLabel usernameLabel = createLabel("USERNAME");
        JLabel passwordLabel = createLabel("PASSWORD");

        JLabel note = new JLabel(
                "<html><div style='text-align:center;'>"
                + "Akses khusus administrator.<br>"
                + "Pastikan akun Anda terdaftar."
                + "</div></html>"
        );

        note.setFont(new Font(
                "SansSerif", Font.PLAIN, 12
        ));

        note.setForeground(muted);

        note.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        // SUSUN FORM
        form.add(title);
        form.add(Box.createVerticalStrut(9));

        form.add(subtitle);
        form.add(Box.createVerticalStrut(34));

        form.add(usernameLabel);
        form.add(Box.createVerticalStrut(10));

        form.add(usernameBox);
        form.add(Box.createVerticalStrut(22));

        form.add(passwordLabel);
        form.add(Box.createVerticalStrut(10));

        form.add(passwordBox);
        form.add(Box.createVerticalStrut(27));

        form.add(loginButton);
        form.add(Box.createVerticalStrut(24));

        form.add(note);

        panel.add(form);

        return panel;
    }

    // KOTAK INPUT
    private JPanel createInputBox() {

        JPanel box = new JPanel(new BorderLayout());

        box.setBackground(Color.WHITE);

        box.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                borderColor, 1
                        ),
                        new EmptyBorder(2, 3, 2, 3)
                )
        );

        box.setMaximumSize(
                new Dimension(Integer.MAX_VALUE, 48)
        );

        box.setPreferredSize(
                new Dimension(300, 48)
        );

        return box;
    }

    // LABEL FORM
    private JLabel createLabel(String text) {

        JLabel label = new JLabel(text);

        label.setFont(new Font(
                "SansSerif", Font.BOLD, 13
        ));

        label.setForeground(dark);

        label.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        return label;
    }

    // PROSES LOGIN
    private void prosesLogin() {

        String username = txtUsername.getText().trim();

        char[] password = txtPassword.getPassword();

        try {

            if (username.isEmpty() || password.length == 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Username dan password wajib diisi.",
                        "Data belum lengkap",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            JOptionPane.showMessageDialog(
                    this,
                    "Tampilan login berhasil digunakan.\n"
                    + "Autentikasi database akan dibuat pada tahap berikutnya.",
                    "Informasi",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } finally {

            Arrays.fill(password, '\0');
        }
    }
}
