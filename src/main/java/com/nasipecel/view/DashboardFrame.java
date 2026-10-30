
package com.nasipecel.view;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class DashboardFrame extends JFrame {

    // PALET WARNA
    private final Color hijau = new Color(25, 65, 43);
    private final Color hijauAktif = new Color(49, 105, 68);
    private final Color hijauMuda = new Color(231, 241, 232);
    private final Color latar = new Color(246, 248, 243);
    private final Color putih = Color.WHITE;
    private final Color teks = new Color(30, 40, 32);
    private final Color abu = new Color(110, 120, 110);
    private final Color garis = new Color(225, 230, 222);

    public DashboardFrame() {

        setTitle("ERP Nasi Pecel - Dashboard Admin");
        setSize(1150, 720);
        setMinimumSize(new Dimension(950, 620));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel utama = new JPanel(new BorderLayout());
        utama.setBackground(latar);

        utama.add(buatSidebar(), BorderLayout.WEST);
        utama.add(buatKonten(), BorderLayout.CENTER);

        setContentPane(utama);
    }

    // ================= SIDEBAR =================

    private JPanel buatSidebar() {

        JPanel sidebar = new JPanel();

        sidebar.setPreferredSize(new Dimension(245, 0));
        sidebar.setBackground(hijau);
        sidebar.setLayout(new BoxLayout(
                sidebar, BoxLayout.Y_AXIS));

        sidebar.setBorder(
                new EmptyBorder(30, 18, 22, 18));

        JLabel logo = new JLabel("ERP NASI PECEL");
        logo.setFont(new Font(
                "SansSerif", Font.BOLD, 21));
        logo.setForeground(putih);

        JLabel subLogo = new JLabel("ADMIN MANAGEMENT SYSTEM");
        subLogo.setFont(new Font(
                "SansSerif", Font.PLAIN, 10));
        subLogo.setForeground(new Color(200, 220, 205));

        sidebar.add(logo);
        sidebar.add(Box.createVerticalStrut(8));
        sidebar.add(subLogo);
        sidebar.add(Box.createVerticalStrut(40));

        tambahMenuSidebar(sidebar, "Dashboard", true);
        tambahMenuSidebar(sidebar, "Kelola Menu", false);
        tambahMenuSidebar(sidebar, "Transaksi Penjualan", false);
        tambahMenuSidebar(sidebar, "Stok Bahan Baku", false);
        tambahMenuSidebar(sidebar, "Produksi", false);
        tambahMenuSidebar(sidebar, "Laporan", false);

        sidebar.add(Box.createVerticalGlue());

        JPanel adminPanel = new JPanel();
        adminPanel.setLayout(new BoxLayout(
                adminPanel, BoxLayout.Y_AXIS));
        adminPanel.setBackground(new Color(35, 78, 52));
        adminPanel.setBorder(
                new EmptyBorder(15, 14, 15, 14));
        adminPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        adminPanel.setMaximumSize(
                new Dimension(Integer.MAX_VALUE, 75));

        JLabel admin = new JLabel("ADMINISTRATOR");
        admin.setFont(new Font(
                "SansSerif", Font.BOLD, 13));
        admin.setForeground(putih);

        JLabel status = new JLabel("●  Status: Aktif");
        status.setFont(new Font(
                "SansSerif", Font.PLAIN, 11));
        status.setForeground(new Color(190, 225, 195));

        adminPanel.add(admin);
        adminPanel.add(Box.createVerticalStrut(7));
        adminPanel.add(status);

        sidebar.add(adminPanel);

        return sidebar;
    }

    // ================= MENU SIDEBAR =================

    private void tambahMenuSidebar(
            JPanel sidebar, String nama, boolean aktif) {

        JButton tombol = new JButton(nama);

        tombol.setFont(new Font(
                "SansSerif", Font.BOLD, 13));

        tombol.setForeground(putih);

        tombol.setBackground(
                aktif ? hijauAktif : hijau);

        tombol.setHorizontalAlignment(
                SwingConstants.LEFT);

        tombol.setBorder(
                new EmptyBorder(13, 14, 13, 8));

        tombol.setFocusPainted(false);
        tombol.setBorderPainted(false);
        tombol.setOpaque(true);

        tombol.setCursor(
                new Cursor(Cursor.HAND_CURSOR));

        tombol.setAlignmentX(Component.LEFT_ALIGNMENT);

        tombol.setMaximumSize(
                new Dimension(Integer.MAX_VALUE, 47));

        // EFEK HOVER
        tombol.addMouseListener(new java.awt.event.MouseAdapter() {

            @Override
            public void mouseEntered(
                    java.awt.event.MouseEvent e) {

                if (!aktif) {
                    tombol.setBackground(hijauAktif);
                }
            }

            @Override
            public void mouseExited(
                    java.awt.event.MouseEvent e) {

                if (!aktif) {
                    tombol.setBackground(hijau);
                }
            }
        });

        // KONEKSI HALAMAN
        tombol.addActionListener(e -> {

            switch (nama) {

                case "Dashboard":
                    break;

                case "Kelola Menu":
                    new MenuFrame().setVisible(true);
                    break;

                case "Transaksi Penjualan":
                    new TransaksiFrame().setVisible(true);
                    break;

case "Stok Bahan Baku":
    new StokBahanBakuFrame().setVisible(true);
    break;

case "Produksi":
    new ProduksiFrame().setVisible(true);
    break;

case "Laporan":
    new LaporanFrame().setVisible(true);
    break;
            }
        });

        sidebar.add(tombol);
        sidebar.add(Box.createVerticalStrut(9));
    }

    // ================= KONTEN DASHBOARD =================

    private JPanel buatKonten() {

        JPanel konten = new JPanel(
                new BorderLayout(0, 25));

        konten.setBackground(latar);

        konten.setBorder(
                new EmptyBorder(30, 30, 30, 30));

        // HEADER
        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);

        JPanel judulPanel = new JPanel();
        judulPanel.setOpaque(false);

        judulPanel.setLayout(new BoxLayout(
                judulPanel, BoxLayout.Y_AXIS));

        JLabel judul = new JLabel("Dashboard");

        judul.setFont(new Font(
                "SansSerif", Font.BOLD, 30));

        judul.setForeground(teks);

        JLabel subJudul = new JLabel(
                "Ringkasan operasional usaha nasi pecel");

        subJudul.setFont(new Font(
                "SansSerif", Font.PLAIN, 14));

        subJudul.setForeground(abu);

        judulPanel.add(judul);
        judulPanel.add(Box.createVerticalStrut(8));
        judulPanel.add(subJudul);

        JLabel labelAdmin = new JLabel(
                "● ADMINISTRATOR");

        labelAdmin.setFont(new Font(
                "SansSerif", Font.BOLD, 12));

        labelAdmin.setForeground(hijau);

        labelAdmin.setBorder(
                new CompoundBorder(
                        new LineBorder(garis, 1, true),
                        new EmptyBorder(10, 14, 10, 14)
                ));

        labelAdmin.setOpaque(true);
        labelAdmin.setBackground(putih);

        header.add(judulPanel, BorderLayout.WEST);
        header.add(labelAdmin, BorderLayout.EAST);

        // ISI DASHBOARD
        JPanel isi = new JPanel();

        isi.setOpaque(false);

        isi.setLayout(new BoxLayout(
                isi, BoxLayout.Y_AXIS));

        // KARTU BARIS PERTAMA
        JPanel baris1 = new JPanel(
                new GridLayout(1, 2, 18, 0));

        baris1.setOpaque(false);

        baris1.setMaximumSize(
                new Dimension(Integer.MAX_VALUE, 150));

        baris1.add(buatKartu(
                "PENJUALAN HARI INI",
                "Rp 850.000",
                "Total pendapatan"));

        baris1.add(buatKartu(
                "PORSI TERJUAL",
                "68 Porsi",
                "Jumlah penjualan hari ini"));

        // KARTU BARIS KEDUA
        JPanel baris2 = new JPanel(
                new GridLayout(1, 2, 18, 0));

        baris2.setOpaque(false);

        baris2.setMaximumSize(
                new Dimension(Integer.MAX_VALUE, 150));

        baris2.add(buatKartu(
                "JENIS BAHAN BAKU",
                "12 Bahan",
                "Total bahan tersedia"));

        baris2.add(buatKartu(
                "PRODUKSI HARI INI",
                "80 Porsi",
                "Jumlah produksi"));

        // INFORMASI SISTEM
        JPanel informasi = new JPanel();

        informasi.setLayout(new BoxLayout(
                informasi, BoxLayout.Y_AXIS));

        informasi.setBackground(putih);

        informasi.setBorder(
                new CompoundBorder(
                        new LineBorder(garis, 1, true),
                        new EmptyBorder(23, 23, 23, 23)
                ));

        informasi.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel infoJudul = new JLabel(
                "Informasi Sistem");

        infoJudul.setFont(new Font(
                "SansSerif", Font.BOLD, 19));

        infoJudul.setForeground(teks);

        informasi.add(infoJudul);
        informasi.add(Box.createVerticalStrut(20));

        String[] daftarInfo = {
            "Kelola menu makanan dan harga jual.",
            "Catat transaksi penjualan dan pembayaran tunai.",
            "Pantau persediaan bahan baku usaha.",
            "Catat jumlah produksi, porsi terjual, dan sisa porsi.",
            "Lihat laporan penjualan, persediaan, dan produksi."
        };

        for (String info : daftarInfo) {

            JLabel label = new JLabel(
                    "●  " + info);

            label.setFont(new Font(
                    "SansSerif", Font.PLAIN, 13));

            label.setForeground(abu);

            informasi.add(label);
            informasi.add(Box.createVerticalStrut(14));
        }

        // SUSUN KONTEN
        isi.add(baris1);
        isi.add(Box.createVerticalStrut(20));

        isi.add(baris2);
        isi.add(Box.createVerticalStrut(25));

        isi.add(informasi);

        JScrollPane scroll = new JScrollPane(isi);

        scroll.setBorder(null);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);

        scroll.getVerticalScrollBar().setUnitIncrement(16);

        konten.add(header, BorderLayout.NORTH);
        konten.add(scroll, BorderLayout.CENTER);

        return konten;
    }

    // ================= KARTU DASHBOARD =================

    private JPanel buatKartu(
            String judul, String nilai, String keterangan) {

        JPanel kartu = new JPanel();

        kartu.setLayout(new BoxLayout(
                kartu, BoxLayout.Y_AXIS));

        kartu.setBackground(putih);

        kartu.setBorder(
                new CompoundBorder(
                        new LineBorder(garis, 1, true),
                        new EmptyBorder(20, 22, 20, 22)
                ));

        JLabel labelJudul = new JLabel(judul);

        labelJudul.setFont(new Font(
                "SansSerif", Font.BOLD, 12));

        labelJudul.setForeground(abu);

        JLabel labelNilai = new JLabel(nilai);

        labelNilai.setFont(new Font(
                "SansSerif", Font.BOLD, 27));

        labelNilai.setForeground(hijau);

        JLabel labelKeterangan = new JLabel(keterangan);

        labelKeterangan.setFont(new Font(
                "SansSerif", Font.PLAIN, 12));

        labelKeterangan.setForeground(abu);

        kartu.add(labelJudul);
        kartu.add(Box.createVerticalStrut(13));

        kartu.add(labelNilai);
        kartu.add(Box.createVerticalStrut(8));

        kartu.add(labelKeterangan);

        return kartu;
    }
}
