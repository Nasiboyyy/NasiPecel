
package com.nasipecel.view;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ProduksiFrame extends JFrame {

    // ================= WARNA =================

    private final Color hijau = new Color(40, 83, 57);
    private final Color hijauMuda = new Color(239, 247, 241);
    private final Color hijauTerang = new Color(224, 239, 227);
    private final Color putih = Color.WHITE;
    private final Color teks = new Color(45, 60, 48);
    private final Color abu = new Color(110, 120, 112);
    private final Color garis = new Color(215, 228, 217);

    private JTable table;
    private DefaultTableModel model;

    private JLabel lblTotalProduksi;
    private JLabel lblTotalTerjual;
    private JLabel lblTotalSisa;

    private JTextField txtCari;

    private final ArrayList<DataProduksi> daftarProduksi =
            new ArrayList<>();

    private final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("dd-MM-yyyy");

    // ================= KONSTRUKTOR =================

    public ProduksiFrame() {
        setTitle("ERP Nasi Pecel - Produksi");
        setSize(1100, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setMinimumSize(new Dimension(950, 600));

        isiDataAwal();
        initComponents();
    }

    // ================= DATA AWAL =================

    private void isiDataAwal() {
        daftarProduksi.add(new DataProduksi(
                "PRD-001", LocalDate.now(), "Nasi Pecel Original",
                50, 35));

        daftarProduksi.add(new DataProduksi(
                "PRD-002", LocalDate.now(), "Nasi Pecel Telur",
                40, 28));

        daftarProduksi.add(new DataProduksi(
                "PRD-003", LocalDate.now(), "Nasi Pecel Komplit",
                30, 20));

        daftarProduksi.add(new DataProduksi(
                "PRD-004", LocalDate.now().minusDays(1),
                "Nasi Pecel Original", 45, 40));
    }

    // ================= TAMPILAN UTAMA =================

    private void initComponents() {

        JPanel utama = new JPanel(new BorderLayout(0, 20));
        utama.setBackground(hijauMuda);
        utama.setBorder(new EmptyBorder(25, 28, 25, 28));

        // HEADER
        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);

        JPanel informasi = new JPanel();
        informasi.setLayout(new BoxLayout(informasi, BoxLayout.Y_AXIS));
        informasi.setOpaque(false);

        JLabel judul = new JLabel("Manajemen Produksi");
        judul.setFont(new Font("SansSerif", Font.BOLD, 25));
        judul.setForeground(hijau);

        JLabel subjudul = new JLabel(
                "Kelola jumlah produksi dan pemantauan porsi nasi pecel");
        subjudul.setFont(new Font("SansSerif", Font.PLAIN, 13));
        subjudul.setForeground(abu);

        informasi.add(judul);
        informasi.add(Box.createVerticalStrut(7));
        informasi.add(subjudul);

        JLabel tanggal = new JLabel(
                "Tanggal: " + LocalDate.now().format(formatter));
        tanggal.setFont(new Font("SansSerif", Font.BOLD, 12));
        tanggal.setForeground(hijau);

        header.add(informasi, BorderLayout.WEST);
        header.add(tanggal, BorderLayout.EAST);

        // KARTU STATISTIK
        JPanel statistik = new JPanel(new GridLayout(1, 3, 18, 0));
        statistik.setOpaque(false);

        lblTotalProduksi = new JLabel("0 Porsi");
        lblTotalTerjual = new JLabel("0 Porsi");
        lblTotalSisa = new JLabel("0 Porsi");

        statistik.add(buatKartu(
                "Total Produksi", lblTotalProduksi,
                new Color(40, 100, 65)));

        statistik.add(buatKartu(
                "Total Terjual", lblTotalTerjual,
                new Color(45, 105, 160)));

        statistik.add(buatKartu(
                "Sisa Porsi", lblTotalSisa,
                new Color(190, 130, 45)));

        // PANEL TABEL
        JPanel panelTabel = new JPanel(new BorderLayout(0, 15));
        panelTabel.setBackground(putih);
        panelTabel.setBorder(new CompoundBorder(
                new LineBorder(garis, 1, true),
                new EmptyBorder(20, 20, 20, 20)));

        // TOOLBAR
        JPanel toolbar = new JPanel(new BorderLayout(10, 10));
        toolbar.setOpaque(false);

        JLabel labelData = new JLabel("Data Produksi");
        labelData.setFont(new Font("SansSerif", Font.BOLD, 17));
        labelData.setForeground(hijau);

        JPanel pencarian = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        pencarian.setOpaque(false);

        txtCari = new JTextField(17);
        txtCari.setPreferredSize(new Dimension(190, 36));
        txtCari.setFont(new Font("SansSerif", Font.PLAIN, 12));
        txtCari.setBorder(new CompoundBorder(
                new LineBorder(garis, 1, true),
                new EmptyBorder(5, 10, 5, 10)));

        JButton btnCari = buatTombol("Cari", new Color(80, 100, 85));

        pencarian.add(txtCari);
        pencarian.add(btnCari);

        toolbar.add(labelData, BorderLayout.WEST);
        toolbar.add(pencarian, BorderLayout.EAST);

        // TABEL
        String[] kolom = {
            "No", "Kode Produksi", "Tanggal", "Nama Menu",
            "Diproduksi", "Terjual", "Sisa", "Status"
        };

        model = new DefaultTableModel(kolom, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(model);
        table.setRowHeight(43);
        table.setFont(new Font("SansSerif", Font.PLAIN, 12));
        table.setForeground(teks);
        table.setBackground(putih);
        table.setSelectionBackground(hijauTerang);
        table.setSelectionForeground(hijau);
        table.setGridColor(garis);
        table.setShowVerticalLines(false);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setFillsViewportHeight(true);

        JTableHeader headerTabel = table.getTableHeader();
        headerTabel.setPreferredSize(new Dimension(0, 45));
        headerTabel.setBackground(hijau);
        headerTabel.setForeground(putih);
        headerTabel.setFont(new Font("SansSerif", Font.BOLD, 12));
        headerTabel.setOpaque(true);
        headerTabel.setReorderingAllowed(false);

        headerTabel.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(
                    JTable t, Object value, boolean selected,
                    boolean focus, int row, int column) {

                JLabel label = (JLabel) super.getTableCellRendererComponent(
                        t, value, selected, focus, row, column);

                label.setBackground(hijau);
                label.setForeground(putih);
                label.setFont(new Font("SansSerif", Font.BOLD, 12));
                label.setHorizontalAlignment(SwingConstants.CENTER);
                label.setOpaque(true);
                label.setBorder(new MatteBorder(
                        0, 0, 1, 1, new Color(75, 112, 83)));

                return label;
            }
        });

        // RENDERER STATUS
        table.getColumnModel().getColumn(7).setCellRenderer(
                new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(
                    JTable t, Object value, boolean selected,
                    boolean focus, int row, int column) {

                JLabel label = (JLabel) super.getTableCellRendererComponent(
                        t, value, selected, focus, row, column);

                label.setHorizontalAlignment(SwingConstants.CENTER);
                label.setFont(new Font("SansSerif", Font.BOLD, 11));

                if (!selected) {
                    String status = value.toString();

                    if (status.equals("Habis")) {
                        label.setForeground(new Color(190, 65, 65));
                    } else if (status.equals("Tersedia")) {
                        label.setForeground(new Color(35, 130, 75));
                    } else {
                        label.setForeground(new Color(180, 125, 35));
                    }
                }

                return label;
            }
        });

        JScrollPane scroll = new JScrollPane(table);
        scroll.setBorder(new LineBorder(garis, 1));
        scroll.getViewport().setBackground(putih);

        // TOMBOL AKSI
        JPanel tombolPanel = new JPanel(
                new FlowLayout(FlowLayout.LEFT, 10, 5));
        tombolPanel.setOpaque(false);

        JButton btnTambah = buatTombol(
                "+ Tambah Produksi", new Color(40, 110, 65));

        JButton btnEdit = buatTombol(
                "Edit", new Color(45, 105, 160));

        JButton btnHapus = buatTombol(
                "Hapus", new Color(190, 65, 65));

        JButton btnRefresh = buatTombol(
                "Refresh", new Color(100, 110, 105));

        tombolPanel.add(btnTambah);
        tombolPanel.add(btnEdit);
        tombolPanel.add(btnHapus);
        tombolPanel.add(btnRefresh);

        panelTabel.add(toolbar, BorderLayout.NORTH);
        panelTabel.add(scroll, BorderLayout.CENTER);
        panelTabel.add(tombolPanel, BorderLayout.SOUTH);

        // SUSUNAN UTAMA
        JPanel bagianAtas = new JPanel(new BorderLayout(0, 20));
        bagianAtas.setOpaque(false);
        bagianAtas.add(header, BorderLayout.NORTH);
        bagianAtas.add(statistik, BorderLayout.CENTER);

        utama.add(bagianAtas, BorderLayout.NORTH);
        utama.add(panelTabel, BorderLayout.CENTER);

        setContentPane(utama);

        // EVENT TOMBOL
        btnTambah.addActionListener(e -> tambahProduksi());
        btnEdit.addActionListener(e -> editProduksi());
        btnHapus.addActionListener(e -> hapusProduksi());
        btnRefresh.addActionListener(e -> {
            txtCari.setText("");
            refreshTabel();
        });

        btnCari.addActionListener(e -> refreshTabel());

        txtCari.addActionListener(e -> refreshTabel());

        refreshTabel();
    }

    // ================= KARTU STATISTIK =================

    private JPanel buatKartu(String judul, JLabel nilai, Color warna) {

        JPanel kartu = new JPanel(new BorderLayout(0, 10));
        kartu.setBackground(putih);
        kartu.setBorder(new CompoundBorder(
                new LineBorder(garis, 1, true),
                new EmptyBorder(18, 20, 18, 20)));

        JLabel labelJudul = new JLabel(judul);
        labelJudul.setFont(new Font("SansSerif", Font.BOLD, 13));
        labelJudul.setForeground(abu);

        nilai.setFont(new Font("SansSerif", Font.BOLD, 25));
        nilai.setForeground(warna);

        kartu.add(labelJudul, BorderLayout.NORTH);
        kartu.add(nilai, BorderLayout.CENTER);

        return kartu;
    }

    // ================= REFRESH TABEL =================

    private void refreshTabel() {

        model.setRowCount(0);

        String cari = txtCari.getText().trim().toLowerCase();

        int totalProduksi = 0;
        int totalTerjual = 0;
        int totalSisa = 0;
        int nomor = 1;

        for (DataProduksi data : daftarProduksi) {

            totalProduksi += data.diproduksi;
            totalTerjual += data.terjual;
            totalSisa += data.getSisa();

            if (!data.kode.toLowerCase().contains(cari)
                    && !data.menu.toLowerCase().contains(cari)
                    && !data.tanggal.format(formatter).contains(cari)) {
                continue;
            }

            model.addRow(new Object[]{
                nomor++,
                data.kode,
                data.tanggal.format(formatter),
                data.menu,
                data.diproduksi,
                data.terjual,
                data.getSisa(),
                data.getStatus()
            });
        }

        lblTotalProduksi.setText(totalProduksi + " Porsi");
        lblTotalTerjual.setText(totalTerjual + " Porsi");
        lblTotalSisa.setText(totalSisa + " Porsi");
    }

    // ================= TAMBAH PRODUKSI =================

    private void tambahProduksi() {

        JTextField txtKode = new JTextField(
                String.format("PRD-%03d", daftarProduksi.size() + 1));

        JTextField txtTanggal = new JTextField(
                LocalDate.now().format(formatter));

        JTextField txtMenu = new JTextField();
        JTextField txtProduksi = new JTextField();

        JPanel form = buatForm(
                txtKode, txtTanggal, txtMenu, txtProduksi);

        int hasil = JOptionPane.showConfirmDialog(
                this, form, "Tambah Data Produksi",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        if (hasil != JOptionPane.OK_OPTION) return;

        try {
            String kode = txtKode.getText().trim();
            LocalDate tanggal = LocalDate.parse(
                    txtTanggal.getText().trim(), formatter);
            String menu = txtMenu.getText().trim();
            int produksi = Integer.parseInt(
                    txtProduksi.getText().trim());

            if (kode.isEmpty() || menu.isEmpty() || produksi <= 0) {
                throw new IllegalArgumentException();
            }

            if (cariKode(kode) != null) {
                JOptionPane.showMessageDialog(this,
                        "Kode produksi sudah digunakan.");
                return;
            }

            daftarProduksi.add(new DataProduksi(
                    kode, tanggal, menu, produksi, 0));

            refreshTabel();

            JOptionPane.showMessageDialog(this,
                    "Data produksi berhasil ditambahkan.");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Periksa kembali data yang dimasukkan.\n"
                    + "Tanggal: dd-MM-yyyy\n"
                    + "Jumlah produksi harus berupa angka positif.",
                    "Data Tidak Valid", JOptionPane.WARNING_MESSAGE);
        }
    }

    // ================= EDIT PRODUKSI =================

    private void editProduksi() {

        int baris = table.getSelectedRow();

        if (baris == -1) {
            JOptionPane.showMessageDialog(this,
                    "Pilih data produksi yang ingin diedit.");
            return;
        }

        String kode = model.getValueAt(baris, 1).toString();
        DataProduksi data = cariKode(kode);

        if (data == null) return;

        JTextField txtKode = new JTextField(data.kode);
        JTextField txtTanggal = new JTextField(
                data.tanggal.format(formatter));
        JTextField txtMenu = new JTextField(data.menu);
        JTextField txtProduksi = new JTextField(
                String.valueOf(data.diproduksi));

        JTextField txtTerjual = new JTextField(
                String.valueOf(data.terjual));

        JPanel form = buatForm(
                txtKode, txtTanggal, txtMenu, txtProduksi);

        JPanel panelUtama = new JPanel(new BorderLayout(0, 10));
        panelUtama.setBackground(hijauMuda);

        panelUtama.add(form, BorderLayout.CENTER);

        JPanel tambahan = new JPanel(new GridLayout(1, 2, 12, 5));
        tambahan.setBackground(hijauMuda);
        tambahan.setBorder(new EmptyBorder(0, 12, 10, 12));

        JLabel labelTerjual = new JLabel("Jumlah Terjual");
        labelTerjual.setForeground(hijau);
        labelTerjual.setFont(new Font("SansSerif", Font.BOLD, 12));

        txtTerjual.setFont(new Font("SansSerif", Font.PLAIN, 12));
        txtTerjual.setBorder(new CompoundBorder(
                new LineBorder(garis, 1, true),
                new EmptyBorder(7, 9, 7, 9)));

        tambahan.add(labelTerjual);
        tambahan.add(txtTerjual);

        panelUtama.add(tambahan, BorderLayout.SOUTH);

        int hasil = JOptionPane.showConfirmDialog(
                this, panelUtama, "Edit Data Produksi",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        if (hasil != JOptionPane.OK_OPTION) return;

        try {
            String kodeBaru = txtKode.getText().trim();
            LocalDate tanggal = LocalDate.parse(
                    txtTanggal.getText().trim(), formatter);
            String menu = txtMenu.getText().trim();

            int produksi = Integer.parseInt(
                    txtProduksi.getText().trim());

            int terjual = Integer.parseInt(
                    txtTerjual.getText().trim());

            if (kodeBaru.isEmpty() || menu.isEmpty()
                    || produksi <= 0 || terjual < 0
                    || terjual > produksi) {
                throw new IllegalArgumentException();
            }

            DataProduksi duplikat = cariKode(kodeBaru);

            if (duplikat != null && duplikat != data) {
                JOptionPane.showMessageDialog(this,
                        "Kode produksi sudah digunakan.");
                return;
            }

            data.kode = kodeBaru;
            data.tanggal = tanggal;
            data.menu = menu;
            data.diproduksi = produksi;
            data.terjual = terjual;

            refreshTabel();

            JOptionPane.showMessageDialog(this,
                    "Data produksi berhasil diperbarui.");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Data tidak valid.\n"
                    + "Jumlah terjual tidak boleh melebihi produksi.",
                    "Kesalahan", JOptionPane.WARNING_MESSAGE);
        }
    }

    // ================= HAPUS PRODUKSI =================

    private void hapusProduksi() {

        int baris = table.getSelectedRow();

        if (baris == -1) {
            JOptionPane.showMessageDialog(this,
                    "Pilih data produksi yang ingin dihapus.");
            return;
        }

        String kode = model.getValueAt(baris, 1).toString();

        int konfirmasi = JOptionPane.showConfirmDialog(
                this,
                "Apakah Anda yakin ingin menghapus data "
                + kode + "?",
                "Konfirmasi Hapus",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);

        if (konfirmasi == JOptionPane.YES_OPTION) {

            DataProduksi data = cariKode(kode);

            if (data != null) {
                daftarProduksi.remove(data);
                refreshTabel();

                JOptionPane.showMessageDialog(this,
                        "Data produksi berhasil dihapus.");
            }
        }
    }

    // ================= CARI KODE =================

    private DataProduksi cariKode(String kode) {

        for (DataProduksi data : daftarProduksi) {
            if (data.kode.equalsIgnoreCase(kode)) {
                return data;
            }
        }

        return null;
    }

    // ================= FORM INPUT =================

    private JPanel buatForm(
            JTextField kode,
            JTextField tanggal,
            JTextField menu,
            JTextField produksi) {

        JPanel panel = new JPanel(new GridLayout(4, 2, 12, 13));
        panel.setBackground(hijauMuda);
        panel.setBorder(new EmptyBorder(18, 18, 18, 18));
        panel.setPreferredSize(new Dimension(440, 220));

        tambahForm(panel, "Kode Produksi", kode);
        tambahForm(panel, "Tanggal (dd-MM-yyyy)", tanggal);
        tambahForm(panel, "Nama Menu", menu);
        tambahForm(panel, "Jumlah Produksi", produksi);

        return panel;
    }

    private void tambahForm(
            JPanel panel, String judul, JTextField input) {

        JLabel label = new JLabel(judul);
        label.setFont(new Font("SansSerif", Font.BOLD, 12));
        label.setForeground(hijau);

        input.setFont(new Font("SansSerif", Font.PLAIN, 12));
        input.setBackground(putih);
        input.setForeground(teks);
        input.setCaretColor(hijau);
        input.setBorder(new CompoundBorder(
                new LineBorder(garis, 1, true),
                new EmptyBorder(7, 9, 7, 9)));

        panel.add(label);
        panel.add(input);
    }

    // ================= TOMBOL =================

    private JButton buatTombol(String nama, Color warna) {

        JButton tombol = new JButton(nama);

        tombol.setFont(new Font("SansSerif", Font.BOLD, 12));
        tombol.setForeground(putih);
        tombol.setBackground(warna);
        tombol.setFocusPainted(false);
        tombol.setBorderPainted(false);
        tombol.setContentAreaFilled(true);
        tombol.setOpaque(true);
        tombol.setBorder(new EmptyBorder(11, 17, 11, 17));
        tombol.setCursor(new Cursor(Cursor.HAND_CURSOR));

        Color warnaAsli = warna;

        tombol.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                tombol.setBackground(warnaAsli.brighter());
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                tombol.setBackground(warnaAsli);
            }
        });

        return tombol;
    }

    // ================= MODEL DATA =================

    private class DataProduksi {

        String kode;
        LocalDate tanggal;
        String menu;
        int diproduksi;
        int terjual;

        DataProduksi(String kode, LocalDate tanggal,
                String menu, int diproduksi, int terjual) {

            this.kode = kode;
            this.tanggal = tanggal;
            this.menu = menu;
            this.diproduksi = diproduksi;
            this.terjual = terjual;
        }

        int getSisa() {
            return diproduksi - terjual;
        }

        String getStatus() {
            if (getSisa() == 0) {
                return "Habis";
            } else if (terjual > 0) {
                return "Berjalan";
            } else {
                return "Tersedia";
            }
        }
    }
}
