
package com.nasipecel.view;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class StokBahanBakuFrame extends JFrame {

    // ================= WARNA =================

    private final Color hijau = new Color(25, 65, 43);
    private final Color hijauAktif = new Color(49, 105, 68);
    private final Color hijauMuda = new Color(231, 241, 232);
    private final Color latar = new Color(246, 248, 243);
    private final Color putih = Color.WHITE;
    private final Color teks = new Color(35, 45, 37);
    private final Color abu = new Color(120, 130, 120);
    private final Color merah = new Color(190, 65, 65);
    private final Color biru = new Color(55, 120, 160);
    private final Color garis = new Color(225, 230, 222);

    private JTable table;
    private DefaultTableModel model;
    private JTextField txtCari;

    private JLabel lblTotal;
    private JLabel lblMenipis;
    private JLabel lblAman;

    private int nomorKode = 7;

    private final ArrayList<Object[]> dataStok = new ArrayList<>();

    // ================= CONSTRUCTOR =================

    public StokBahanBakuFrame() {

        setTitle("Stok Bahan Baku - ERP Nasi Pecel");
        setSize(1150, 740);
        setMinimumSize(new Dimension(950, 620));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        initData();
        initComponents();
    }

    // ================= DATA AWAL =================

    private void initData() {

        dataStok.add(new Object[]{"BB001", "Beras", 25, "Kg", 14000});
        dataStok.add(new Object[]{"BB002", "Kacang Tanah", 8, "Kg", 28000});
        dataStok.add(new Object[]{"BB003", "Cabai Rawit", 3, "Kg", 45000});
        dataStok.add(new Object[]{"BB004", "Sayuran", 12, "Kg", 10000});
        dataStok.add(new Object[]{"BB005", "Minyak Goreng", 10, "Liter", 18000});
        dataStok.add(new Object[]{"BB006", "Gula Merah", 2, "Kg", 22000});
    }

    // ================= KOMPONEN UTAMA =================

    private void initComponents() {

        JPanel main = new JPanel(new BorderLayout(0, 25));
        main.setBackground(latar);
        main.setBorder(new EmptyBorder(30, 30, 30, 30));

        setContentPane(main);

        main.add(buatHeader(), BorderLayout.NORTH);
        main.add(buatKonten(), BorderLayout.CENTER);
    }

    // ================= HEADER =================

    private JPanel buatHeader() {

        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);

        JPanel judulPanel = new JPanel();
        judulPanel.setOpaque(false);
        judulPanel.setLayout(new BoxLayout(
                judulPanel, BoxLayout.Y_AXIS));

        JLabel judul = new JLabel("Manajemen Stok Bahan Baku");

        judul.setFont(new Font(
                "Segoe UI", Font.BOLD, 28));

        judul.setForeground(hijau);

        JLabel subtitle = new JLabel(
                "Pantau dan kelola persediaan bahan baku usaha Nasi Pecel.");

        subtitle.setFont(new Font(
                "Segoe UI", Font.PLAIN, 13));

        subtitle.setForeground(abu);

        judulPanel.add(judul);
        judulPanel.add(Box.createVerticalStrut(7));
        judulPanel.add(subtitle);

        JButton btnKembali = buatTombol(
                "←  Dashboard", abu, putih);

        btnKembali.addActionListener(e -> dispose());

        header.add(judulPanel, BorderLayout.WEST);
        header.add(btnKembali, BorderLayout.EAST);

        return header;
    }

    // ================= KONTEN =================

    private JPanel buatKonten() {

        JPanel konten = new JPanel(new BorderLayout(0, 22));
        konten.setOpaque(false);

        // STATISTIK
        JPanel statistik = new JPanel(
                new GridLayout(1, 3, 18, 0));

        statistik.setOpaque(false);
        statistik.setPreferredSize(new Dimension(0, 125));

        lblTotal = new JLabel("0");
        lblMenipis = new JLabel("0");
        lblAman = new JLabel("0");

        statistik.add(buatKartu(
                "TOTAL BAHAN BAKU",
                lblTotal,
                "Jumlah jenis bahan terdaftar",
                "📦",
                hijau));

        statistik.add(buatKartu(
                "STOK MENIPIS",
                lblMenipis,
                "Persediaan 5 satuan atau kurang",
                "⚠",
                merah));

        statistik.add(buatKartu(
                "STOK TERSEDIA",
                lblAman,
                "Persediaan di atas batas minimum",
                "✓",
                biru));

        // PANEL TABEL
        JPanel panelTabel = new JPanel(new BorderLayout(0, 18));
        panelTabel.setBackground(putih);
        panelTabel.setBorder(new CompoundBorder(
                new LineBorder(garis, 1, true),
                new EmptyBorder(22, 22, 22, 22)));

        panelTabel.add(buatToolbar(), BorderLayout.NORTH);
        panelTabel.add(buatTabel(), BorderLayout.CENTER);
        panelTabel.add(buatAksi(), BorderLayout.SOUTH);

        konten.add(statistik, BorderLayout.NORTH);
        konten.add(panelTabel, BorderLayout.CENTER);

        return konten;
    }

    // ================= KARTU STATISTIK =================

    private JPanel buatKartu(
            String judul,
            JLabel nilai,
            String keterangan,
            String icon,
            Color warna) {

        JPanel kartu = new JPanel(new BorderLayout(12, 5));

        kartu.setBackground(putih);
        kartu.setBorder(new CompoundBorder(
                new LineBorder(garis, 1, true),
                new EmptyBorder(18, 20, 18, 20)));

        JPanel kiri = new JPanel();
        kiri.setOpaque(false);
        kiri.setLayout(new BoxLayout(
                kiri, BoxLayout.Y_AXIS));

        JLabel labelJudul = new JLabel(judul);
        labelJudul.setFont(new Font(
                "Segoe UI", Font.BOLD, 11));
        labelJudul.setForeground(abu);

        nilai.setFont(new Font(
                "Segoe UI", Font.BOLD, 29));
        nilai.setForeground(warna);

        JLabel ket = new JLabel(keterangan);
        ket.setFont(new Font(
                "Segoe UI", Font.PLAIN, 11));
        ket.setForeground(abu);

        kiri.add(labelJudul);
        kiri.add(Box.createVerticalStrut(9));
        kiri.add(nilai);
        kiri.add(Box.createVerticalStrut(5));
        kiri.add(ket);

        JLabel iconLabel = new JLabel(icon);
        iconLabel.setFont(new Font(
                "Segoe UI Emoji", Font.BOLD, 27));
        iconLabel.setForeground(warna);

        JPanel iconPanel = new JPanel(new GridBagLayout());
        iconPanel.setBackground(new Color(
                warna.getRed(),
                warna.getGreen(),
                warna.getBlue(),
                20));

        iconPanel.setPreferredSize(new Dimension(55, 55));
        iconPanel.setMaximumSize(new Dimension(55, 55));
        iconPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        iconPanel.add(iconLabel);

        kartu.add(kiri, BorderLayout.CENTER);
        kartu.add(iconPanel, BorderLayout.EAST);

        return kartu;
    }

    // ================= TOOLBAR =================

    private JPanel buatToolbar() {

        JPanel toolbar = new JPanel(new BorderLayout());
        toolbar.setOpaque(false);

        JPanel kiri = new JPanel();
        kiri.setOpaque(false);
        kiri.setLayout(new BoxLayout(
                kiri, BoxLayout.Y_AXIS));

        JLabel judul = new JLabel("Daftar Bahan Baku");
        judul.setFont(new Font(
                "Segoe UI", Font.BOLD, 18));
        judul.setForeground(teks);

        JLabel sub = new JLabel(
                "Informasi persediaan dan harga bahan baku");

        sub.setFont(new Font(
                "Segoe UI", Font.PLAIN, 12));
        sub.setForeground(abu);

        kiri.add(judul);
        kiri.add(Box.createVerticalStrut(5));
        kiri.add(sub);

        JPanel pencarian = new JPanel(
                new BorderLayout(7, 0));

        pencarian.setOpaque(false);

        txtCari = new JTextField();
        txtCari.setPreferredSize(new Dimension(230, 40));
        txtCari.setFont(new Font(
                "Segoe UI", Font.PLAIN, 13));

        txtCari.setBorder(new CompoundBorder(
                new LineBorder(garis, 1, true),
                new EmptyBorder(8, 12, 8, 12)));

        txtCari.setToolTipText("Cari nama atau kode bahan baku");

        txtCari.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                refreshTable(txtCari.getText());
            }
        });

        JButton btnTambah = buatTombol(
                "+  Tambah Bahan", hijau, putih);

        btnTambah.addActionListener(e -> tambahData());

        pencarian.add(txtCari, BorderLayout.CENTER);
        pencarian.add(btnTambah, BorderLayout.EAST);

        toolbar.add(kiri, BorderLayout.WEST);
        toolbar.add(pencarian, BorderLayout.EAST);

        return toolbar;
    }

    // ================= TABEL =================

    private JScrollPane buatTabel() {

        String[] kolom = {
            "KODE",
            "NAMA BAHAN BAKU",
            "JUMLAH STOK",
            "SATUAN",
            "HARGA / SATUAN",
            "STATUS"
        };

        model = new DefaultTableModel(kolom, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(model);

        table.setRowHeight(48);
        table.setFont(new Font(
                "Segoe UI", Font.PLAIN, 13));

        table.setForeground(teks);
        table.setBackground(putih);

        table.setSelectionBackground(hijauMuda);
        table.setSelectionForeground(hijau);

        table.setShowVerticalLines(false);
        table.setGridColor(new Color(240, 242, 238));
        table.setIntercellSpacing(new Dimension(0, 1));

        table.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION);

        table.setAutoCreateRowSorter(true);

        JTableHeader header = table.getTableHeader();

        header.setPreferredSize(new Dimension(0, 43));
        header.setFont(new Font(
                "Segoe UI", Font.BOLD, 11));

        header.setBackground(hijauMuda);
        header.setForeground(hijau);
        header.setBorder(new MatteBorder(
                0, 0, 1, 0, garis));

        // STATUS BERWARNA
        table.getColumnModel().getColumn(5)
                .setCellRenderer(new StatusRenderer());

        JScrollPane scroll = new JScrollPane(table);

        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.getViewport().setBackground(putih);

        return scroll;
    }

    // ================= STATUS RENDERER =================

    private class StatusRenderer extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(
                JTable table, Object value,
                boolean selected, boolean focus,
                int row, int column) {

            JLabel label = (JLabel) super
                    .getTableCellRendererComponent(
                            table, value, selected, focus,
                            row, column);

            label.setHorizontalAlignment(
                    SwingConstants.CENTER);

            label.setFont(new Font(
                    "Segoe UI", Font.BOLD, 11));

            if (!selected) {
                if ("Stok Menipis".equals(value)) {
                    label.setForeground(merah);
                    label.setBackground(new Color(255, 237, 237));
                } else {
                    label.setForeground(hijauAktif);
                    label.setBackground(new Color(232, 245, 233));
                }
            }

            label.setBorder(new EmptyBorder(5, 8, 5, 8));

            return label;
        }
    }

    // ================= TOMBOL AKSI =================

    private JPanel buatAksi() {

        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);

        JLabel info = new JLabel(
                "Pilih data pada tabel untuk mengubah atau menghapus.");

        info.setFont(new Font(
                "Segoe UI", Font.ITALIC, 11));
        info.setForeground(abu);

        JPanel tombolPanel = new JPanel(
                new FlowLayout(FlowLayout.RIGHT, 10, 0));

        tombolPanel.setOpaque(false);

        JButton btnEdit = buatTombol(
                "✎  Edit", biru, putih);

        JButton btnHapus = buatTombol(
                "Hapus", merah, putih);

        btnEdit.addActionListener(e -> editData());
        btnHapus.addActionListener(e -> hapusData());

        tombolPanel.add(btnEdit);
        tombolPanel.add(btnHapus);

        panel.add(info, BorderLayout.WEST);
        panel.add(tombolPanel, BorderLayout.EAST);

        return panel;
    }

    // ================= STYLE TOMBOL =================

    private JButton buatTombol(
            String nama, Color warna, Color warnaTeks) {

        JButton tombol = new JButton(nama);

        tombol.setFont(new Font(
                "Segoe UI", Font.BOLD, 12));

        tombol.setForeground(warnaTeks);
        tombol.setBackground(warna);

        tombol.setFocusPainted(false);
        tombol.setBorderPainted(false);
        tombol.setOpaque(true);

        tombol.setBorder(new EmptyBorder(
                12, 18, 12, 18));

        tombol.setCursor(new Cursor(
                Cursor.HAND_CURSOR));

        tombol.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                tombol.setBackground(warna.darker());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                tombol.setBackground(warna);
            }
        });

        return tombol;
    }

    // ================= FORMAT RUPIAH =================

    private String formatRupiah(int angka) {
        NumberFormat format = NumberFormat
                .getNumberInstance(new Locale("id", "ID"));

        return "Rp " + format.format(angka);
    }

    // ================= REFRESH DATA =================

    private void refreshTable(String keyword) {

        model.setRowCount(0);

        int total = 0;
        int menipis = 0;
        int aman = 0;

        for (Object[] item : dataStok) {

            String kode = item[0].toString();
            String nama = item[1].toString();

            if (kode.toLowerCase().contains(
                    keyword.trim().toLowerCase())
                    || nama.toLowerCase().contains(
                    keyword.trim().toLowerCase())) {

                int stok = (int) item[2];

                String status = stok <= 5
                        ? "Stok Menipis" : "Tersedia";

                if (stok <= 5) {
                    menipis++;
                } else {
                    aman++;
                }

                total++;

                model.addRow(new Object[]{
                    kode,
                    nama,
                    stok,
                    item[3],
                    formatRupiah((int) item[4]),
                    status
                });
            }
        }

        lblTotal.setText(String.valueOf(total));
        lblMenipis.setText(String.valueOf(menipis));
        lblAman.setText(String.valueOf(aman));
    }

    // ================= FORM INPUT =================

    private JPanel buatForm(JTextField nama,
            JTextField stok, JTextField satuan,
            JTextField harga) {

        JPanel panel = new JPanel(new GridLayout(4, 2, 12, 15));
        panel.setBackground(putih);
        panel.setBorder(new EmptyBorder(15, 15, 15, 15));

        tambahField(panel, "Nama Bahan Baku", nama);
        tambahField(panel, "Jumlah Stok", stok);
        tambahField(panel, "Satuan", satuan);
        tambahField(panel, "Harga per Satuan", harga);

        return panel;
    }

    private void tambahField(
            JPanel panel, String judul, JTextField field) {

        JLabel label = new JLabel(judul);

        label.setFont(new Font(
                "Segoe UI", Font.BOLD, 12));

        label.setForeground(teks);

        field.setFont(new Font(
                "Segoe UI", Font.PLAIN, 13));

        field.setBorder(new CompoundBorder(
                new LineBorder(garis, 1, true),
                new EmptyBorder(9, 10, 9, 10)));

        panel.add(label);
        panel.add(field);
    }

    // ================= TAMBAH DATA =================

    private void tambahData() {

        JTextField nama = new JTextField();
        JTextField stok = new JTextField();
        JTextField satuan = new JTextField();
        JTextField harga = new JTextField();

        JPanel form = buatForm(nama, stok, satuan, harga);

        int result = JOptionPane.showConfirmDialog(
                this,
                form,
                "Tambah Bahan Baku Baru",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        if (result != JOptionPane.OK_OPTION) return;

        try {

            String namaBahan = nama.getText().trim();
            int jumlah = Integer.parseInt(stok.getText().trim());
            String unit = satuan.getText().trim();
            int hargaBahan = Integer.parseInt(harga.getText().trim());

            if (namaBahan.isEmpty() || unit.isEmpty()
                    || jumlah < 0 || hargaBahan < 0) {
                throw new IllegalArgumentException();
            }

            for (Object[] item : dataStok) {
                if (item[1].toString().equalsIgnoreCase(namaBahan)) {
                    JOptionPane.showMessageDialog(this,
                            "Bahan baku sudah terdaftar!");
                    return;
                }
            }

            String kode = String.format(
                    "BB%03d", nomorKode++);

            dataStok.add(new Object[]{
                kode, namaBahan, jumlah, unit, hargaBahan
            });

            refreshTable(txtCari.getText());

            JOptionPane.showMessageDialog(this,
                    "Bahan baku berhasil ditambahkan!",
                    "Berhasil",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(this,
                    "Jumlah dan harga harus berupa angka!");

        } catch (IllegalArgumentException ex) {

            JOptionPane.showMessageDialog(this,
                    "Data wajib diisi dengan benar!");
        }
    }

    // ================= EDIT DATA =================

    private void editData() {

        int selected = table.getSelectedRow();

        if (selected == -1) {
            JOptionPane.showMessageDialog(this,
                    "Pilih bahan baku terlebih dahulu!");
            return;
        }

        int row = table.convertRowIndexToModel(selected);

        String kode = model.getValueAt(row, 0).toString();

        Object[] item = cariKode(kode);

        if (item == null) return;

        JTextField nama = new JTextField(item[1].toString());
        JTextField stok = new JTextField(item[2].toString());
        JTextField satuan = new JTextField(item[3].toString());
        JTextField harga = new JTextField(item[4].toString());

        JPanel form = buatForm(nama, stok, satuan, harga);

        int result = JOptionPane.showConfirmDialog(
                this,
                form,
                "Edit Bahan Baku",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        if (result != JOptionPane.OK_OPTION) return;

        try {

            String namaBaru = nama.getText().trim();
            int jumlah = Integer.parseInt(stok.getText().trim());
            String unit = satuan.getText().trim();
            int hargaBaru = Integer.parseInt(harga.getText().trim());

            if (namaBaru.isEmpty() || unit.isEmpty()
                    || jumlah < 0 || hargaBaru < 0) {
                throw new IllegalArgumentException();
            }

            for (Object[] data : dataStok) {

                if (!data[0].equals(kode)
                        && data[1].toString().equalsIgnoreCase(namaBaru)) {

                    JOptionPane.showMessageDialog(this,
                            "Nama bahan sudah digunakan!");
                    return;
                }
            }

            item[1] = namaBaru;
            item[2] = jumlah;
            item[3] = unit;
            item[4] = hargaBaru;

            refreshTable(txtCari.getText());

            JOptionPane.showMessageDialog(this,
                    "Data bahan baku berhasil diperbarui!",
                    "Berhasil",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(this,
                    "Jumlah dan harga harus berupa angka!");

        } catch (IllegalArgumentException ex) {

            JOptionPane.showMessageDialog(this,
                    "Data tidak boleh kosong atau negatif!");
        }
    }

    // ================= CARI KODE =================

    private Object[] cariKode(String kode) {

        for (Object[] item : dataStok) {
            if (item[0].equals(kode)) {
                return item;
            }
        }

        return null;
    }

    // ================= HAPUS DATA =================

    private void hapusData() {

        int selected = table.getSelectedRow();

        if (selected == -1) {

            JOptionPane.showMessageDialog(this,
                    "Pilih bahan baku terlebih dahulu!");

            return;
        }

        int row = table.convertRowIndexToModel(selected);

        String kode = model.getValueAt(row, 0).toString();

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Apakah kamu yakin ingin menghapus bahan baku ini?",
                "Konfirmasi Hapus",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);

        if (confirm == JOptionPane.YES_OPTION) {

            dataStok.removeIf(item ->
                    item[0].equals(kode));

            refreshTable(txtCari.getText());

            JOptionPane.showMessageDialog(this,
                    "Bahan baku berhasil dihapus!",
                    "Berhasil",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
