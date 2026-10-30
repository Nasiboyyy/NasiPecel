
package com.nasipecel.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MenuFrame extends JFrame {

    private final Color hijau = new Color(40, 83, 57);
    private final Color latar = new Color(245, 242, 233);
    private final Color teks = new Color(45, 53, 43);

    private JTable tabel;
    private DefaultTableModel model;
    private JTextField txtCari;

    public MenuFrame() {
        setTitle("ERP Nasi Pecel - Kelola Menu");
        setSize(1050, 650);
        setMinimumSize(new Dimension(850, 550));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel utama = new JPanel(new BorderLayout(0, 20));
        utama.setBackground(latar);
        utama.setBorder(new EmptyBorder(28, 30, 28, 30));

        utama.add(buatHeader(), BorderLayout.NORTH);
        utama.add(buatKonten(), BorderLayout.CENTER);

        setContentPane(utama);
        isiDataContoh();
    }

    private JPanel buatHeader() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);

        JPanel judulPanel = new JPanel();
        judulPanel.setOpaque(false);
        judulPanel.setLayout(new BoxLayout(
                judulPanel, BoxLayout.Y_AXIS));

        JLabel judul = new JLabel("Kelola Menu");
        judul.setFont(new Font("SansSerif", Font.BOLD, 27));
        judul.setForeground(teks);

        JLabel sub = new JLabel(
                "Kelola nama, kategori, harga, dan ketersediaan menu");
        sub.setFont(new Font("SansSerif", Font.PLAIN, 12));
        sub.setForeground(Color.GRAY);

        judulPanel.add(judul);
        judulPanel.add(Box.createVerticalStrut(7));
        judulPanel.add(sub);

        JButton btnKembali = new JButton("Kembali");
        btnKembali.addActionListener(e -> dispose());

        panel.add(judulPanel, BorderLayout.WEST);
        panel.add(btnKembali, BorderLayout.EAST);

        return panel;
    }

    private JPanel buatKonten() {
        JPanel panel = new JPanel(new BorderLayout(0, 18));
        panel.setBackground(Color.WHITE);
        panel.setBorder(new EmptyBorder(22, 22, 22, 22));

        JPanel toolbar = new JPanel(new BorderLayout(12, 0));
        toolbar.setOpaque(false);

        txtCari = new JTextField();
        txtCari.setFont(new Font("SansSerif", Font.PLAIN, 13));
        txtCari.setPreferredSize(new Dimension(250, 38));

        JButton btnCari = new JButton("Cari");
        btnCari.addActionListener(e -> cariMenu());

        JPanel pencarian = new JPanel(new BorderLayout(8, 0));
        pencarian.setOpaque(false);
        pencarian.add(txtCari, BorderLayout.CENTER);
        pencarian.add(btnCari, BorderLayout.EAST);

        JButton btnTambah = new JButton("+ Tambah Menu");
        btnTambah.setBackground(hijau);
        btnTambah.setForeground(Color.WHITE);
        btnTambah.setFocusPainted(false);
        btnTambah.addActionListener(e -> tambahMenu());

        toolbar.add(pencarian, BorderLayout.WEST);
        toolbar.add(btnTambah, BorderLayout.EAST);

        String[] kolom = {
            "ID", "Nama Menu", "Kategori", "Harga", "Ketersediaan"
        };

        model = new DefaultTableModel(kolom, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tabel = new JTable(model);
        tabel.setRowHeight(35);
        tabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        tabel.getTableHeader().setFont(
                new Font("SansSerif", Font.BOLD, 12));
        tabel.getTableHeader().setBackground(
                new Color(232, 238, 229));
        tabel.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scroll = new JScrollPane(tabel);

        JPanel tombolAksi = new JPanel(
                new FlowLayout(FlowLayout.RIGHT, 10, 0));
        tombolAksi.setOpaque(false);

        JButton btnEdit = new JButton("Edit Menu");
        JButton btnHapus = new JButton("Hapus Menu");

        btnEdit.addActionListener(e -> editMenu());
        btnHapus.addActionListener(e -> hapusMenu());

        tombolAksi.add(btnEdit);
        tombolAksi.add(btnHapus);

        panel.add(toolbar, BorderLayout.NORTH);
        panel.add(scroll, BorderLayout.CENTER);
        panel.add(tombolAksi, BorderLayout.SOUTH);

        return panel;
    }

    private void isiDataContoh() {
        model.addRow(new Object[]{
            1, "Nasi Pecel Original", "Makanan",
            10000, "Tersedia"
        });

        model.addRow(new Object[]{
            2, "Nasi Pecel Telur", "Makanan",
            13000, "Tersedia"
        });

        model.addRow(new Object[]{
            3, "Nasi Pecel Ayam", "Makanan",
            15000, "Tersedia"
        });

        model.addRow(new Object[]{
            4, "Es Teh", "Minuman",
            4000, "Tersedia"
        });

        model.addRow(new Object[]{
            5, "Es Jeruk", "Minuman",
            5000, "Tidak Tersedia"
        });
    }

    private void tambahMenu() {
        JTextField nama = new JTextField();
        String[] kategori = {"Makanan", "Minuman"};
        JComboBox<String> cbKategori = new JComboBox<>(kategori);
        JTextField harga = new JTextField();

        JPanel form = new JPanel(new GridLayout(0, 2, 10, 10));
        form.add(new JLabel("Nama Menu:"));
        form.add(nama);
        form.add(new JLabel("Kategori:"));
        form.add(cbKategori);
        form.add(new JLabel("Harga (Rp):"));
        form.add(harga);

        int hasil = JOptionPane.showConfirmDialog(
                this, form, "Tambah Menu",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        if (hasil != JOptionPane.OK_OPTION) {
            return;
        }

        String namaMenu = nama.getText().trim();

        if (namaMenu.isEmpty()) {
            pesan("Nama menu wajib diisi.");
            return;
        }

        try {
            int hargaMenu = Integer.parseInt(harga.getText().trim());

            if (hargaMenu <= 0) {
                pesan("Harga harus lebih dari nol.");
                return;
            }

            int id = model.getRowCount() + 1;

            model.addRow(new Object[]{
                id, namaMenu, cbKategori.getSelectedItem(),
                hargaMenu, "Tersedia"
            });

        } catch (NumberFormatException ex) {
            pesan("Harga harus berupa angka, contoh: 10000.");
        }
    }

    private void editMenu() {
        int baris = tabel.getSelectedRow();

        if (baris == -1) {
            pesan("Pilih menu yang ingin diedit.");
            return;
        }

        JTextField nama = new JTextField(
                model.getValueAt(baris, 1).toString());

        String[] kategori = {"Makanan", "Minuman"};
        JComboBox<String> cbKategori = new JComboBox<>(kategori);
        cbKategori.setSelectedItem(
                model.getValueAt(baris, 2).toString());

        JTextField harga = new JTextField(
                model.getValueAt(baris, 3).toString());

        String[] status = {"Tersedia", "Tidak Tersedia"};
        JComboBox<String> cbStatus = new JComboBox<>(status);
        cbStatus.setSelectedItem(
                model.getValueAt(baris, 4).toString());

        JPanel form = new JPanel(new GridLayout(0, 2, 10, 10));
        form.add(new JLabel("Nama Menu:"));
        form.add(nama);
        form.add(new JLabel("Kategori:"));
        form.add(cbKategori);
        form.add(new JLabel("Harga (Rp):"));
        form.add(harga);
        form.add(new JLabel("Ketersediaan:"));
        form.add(cbStatus);

        int hasil = JOptionPane.showConfirmDialog(
                this, form, "Edit Menu",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        if (hasil != JOptionPane.OK_OPTION) {
            return;
        }

        if (nama.getText().trim().isEmpty()) {
            pesan("Nama menu wajib diisi.");
            return;
        }

        try {
            int hargaMenu = Integer.parseInt(harga.getText().trim());

            if (hargaMenu <= 0) {
                pesan("Harga harus lebih dari nol.");
                return;
            }

            model.setValueAt(nama.getText().trim(), baris, 1);
            model.setValueAt(cbKategori.getSelectedItem(), baris, 2);
            model.setValueAt(hargaMenu, baris, 3);
            model.setValueAt(cbStatus.getSelectedItem(), baris, 4);

        } catch (NumberFormatException ex) {
            pesan("Harga harus berupa angka.");
        }
    }

    private void hapusMenu() {
        int baris = tabel.getSelectedRow();

        if (baris == -1) {
            pesan("Pilih menu yang ingin dihapus.");
            return;
        }

        int konfirmasi = JOptionPane.showConfirmDialog(
                this,
                "Yakin ingin menghapus menu ini?",
                "Konfirmasi Hapus",
                JOptionPane.YES_NO_OPTION);

        if (konfirmasi == JOptionPane.YES_OPTION) {
            model.removeRow(baris);
        }
    }

    private void cariMenu() {
        String kataKunci = txtCari.getText()
                .trim().toLowerCase();

        for (int i = 0; i < model.getRowCount(); i++) {
            String nama = model.getValueAt(i, 1)
                    .toString().toLowerCase();

            if (nama.contains(kataKunci)) {
                tabel.setRowSelectionInterval(i, i);
                tabel.scrollRectToVisible(
                        tabel.getCellRect(i, 0, true));
                return;
            }
        }

        pesan("Menu tidak ditemukan.");
    }

    private void pesan(String isi) {
        JOptionPane.showMessageDialog(
                this, isi, "Informasi",
                JOptionPane.INFORMATION_MESSAGE);
    }
}
