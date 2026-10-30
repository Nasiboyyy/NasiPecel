
package com.nasipecel.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TransaksiFrame extends JFrame {

    private final Color hijau = new Color(40, 83, 57);
    private final Color latar = new Color(245, 242, 233);

    private JComboBox<MenuItem> cbMenu;
    private JSpinner spJumlah;
    private JTable tabel;
    private DefaultTableModel model;
    private JTextField txtBayar;
    private JLabel lblTotal;
    private JLabel lblKembalian;

    private final List<MenuItem> daftarMenu = new ArrayList<>();
    private int total = 0;

    public TransaksiFrame() {
        setTitle("ERP Nasi Pecel - Transaksi Penjualan");
        setSize(1050, 680);
        setMinimumSize(new Dimension(850, 580));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        siapkanMenu();

        JPanel utama = new JPanel(new BorderLayout(0, 20));
        utama.setBackground(latar);
        utama.setBorder(new EmptyBorder(25, 28, 25, 28));

        utama.add(buatHeader(), BorderLayout.NORTH);
        utama.add(buatKonten(), BorderLayout.CENTER);

        setContentPane(utama);
    }

    private void siapkanMenu() {
        daftarMenu.add(new MenuItem(1, "Nasi Pecel Original", 10000));
        daftarMenu.add(new MenuItem(2, "Nasi Pecel Telur", 13000));
        daftarMenu.add(new MenuItem(3, "Nasi Pecel Ayam", 15000));
        daftarMenu.add(new MenuItem(4, "Es Teh", 4000));
        daftarMenu.add(new MenuItem(5, "Es Jeruk", 5000));
    }

    private JPanel buatHeader() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);

        JPanel judulPanel = new JPanel();
        judulPanel.setOpaque(false);
        judulPanel.setLayout(new BoxLayout(
                judulPanel, BoxLayout.Y_AXIS));

        JLabel judul = new JLabel("Transaksi Penjualan");
        judul.setFont(new Font("SansSerif", Font.BOLD, 27));

        JLabel sub = new JLabel(
                "Pencatatan penjualan dan pembayaran tunai");
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
        JPanel panel = new JPanel(new BorderLayout(0, 15));
        panel.setBackground(Color.WHITE);
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JPanel input = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        input.setOpaque(false);

        cbMenu = new JComboBox<>(
                daftarMenu.toArray(new MenuItem[0]));
        cbMenu.setPreferredSize(new Dimension(230, 35));

        spJumlah = new JSpinner(
                new SpinnerNumberModel(1, 1, 999, 1));
        spJumlah.setPreferredSize(new Dimension(70, 35));

        JButton btnTambah = new JButton("Tambah Menu");
        btnTambah.addActionListener(e -> tambahItem());

        input.add(new JLabel("Menu:"));
        input.add(cbMenu);
        input.add(new JLabel("Jumlah:"));
        input.add(spJumlah);
        input.add(btnTambah);

        String[] kolom = {
            "ID Menu", "Nama Menu", "Harga", "Jumlah", "Subtotal"
        };

        model = new DefaultTableModel(kolom, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tabel = new JTable(model);
        tabel.setRowHeight(32);

        JScrollPane scroll = new JScrollPane(tabel);

        JPanel pembayaran = new JPanel(new GridLayout(3, 2, 12, 12));
        pembayaran.setOpaque(false);

        lblTotal = new JLabel(formatRupiah(0));
        lblTotal.setFont(new Font("SansSerif", Font.BOLD, 19));
        lblTotal.setForeground(hijau);

        txtBayar = new JTextField();

        lblKembalian = new JLabel(formatRupiah(0));
        lblKembalian.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblKembalian.setForeground(hijau);

        pembayaran.add(new JLabel("Total Pembayaran:"));
        pembayaran.add(lblTotal);
        pembayaran.add(new JLabel("Uang Tunai (Rp):"));
        pembayaran.add(txtBayar);
        pembayaran.add(new JLabel("Kembalian:"));
        pembayaran.add(lblKembalian);

        JButton btnHapus = new JButton("Hapus Item");
        btnHapus.addActionListener(e -> hapusItem());

        JButton btnHitung = new JButton("Hitung Kembalian");
        btnHitung.addActionListener(e -> hitungKembalian());

        JButton btnSelesai = new JButton("Selesaikan Transaksi");
        btnSelesai.setBackground(hijau);
        btnSelesai.setForeground(Color.WHITE);
        btnSelesai.addActionListener(e -> selesaikanTransaksi());

        JPanel tombol = new JPanel(
                new FlowLayout(FlowLayout.RIGHT, 8, 0));
        tombol.setOpaque(false);
        tombol.add(btnHapus);
        tombol.add(btnHitung);
        tombol.add(btnSelesai);

        JPanel bawah = new JPanel(new BorderLayout(0, 12));
        bawah.setOpaque(false);
        bawah.add(pembayaran, BorderLayout.CENTER);
        bawah.add(tombol, BorderLayout.SOUTH);

        panel.add(input, BorderLayout.NORTH);
        panel.add(scroll, BorderLayout.CENTER);
        panel.add(bawah, BorderLayout.SOUTH);

        return panel;
    }

    private void tambahItem() {
        MenuItem menu = (MenuItem) cbMenu.getSelectedItem();

        if (menu == null) {
            pesan("Pilih menu terlebih dahulu.");
            return;
        }

        int jumlah = (Integer) spJumlah.getValue();

        // Jika menu sudah ada, tambahkan jumlahnya.
        for (int i = 0; i < model.getRowCount(); i++) {
            int id = Integer.parseInt(
                    model.getValueAt(i, 0).toString());

            if (id == menu.id) {
                int jumlahLama = Integer.parseInt(
                        model.getValueAt(i, 3).toString());

                int jumlahBaru = jumlahLama + jumlah;

                model.setValueAt(jumlahBaru, i, 3);
                model.setValueAt(
                        formatRupiah(menu.harga * jumlahBaru),
                        i, 4);

                hitungTotal();
                return;
            }
        }

        model.addRow(new Object[]{
            menu.id,
            menu.nama,
            formatRupiah(menu.harga),
            jumlah,
            formatRupiah(menu.harga * jumlah)
        });

        hitungTotal();
    }

    private void hitungTotal() {
        total = 0;

        for (int i = 0; i < model.getRowCount(); i++) {
            int id = Integer.parseInt(
                    model.getValueAt(i, 0).toString());

            int jumlah = Integer.parseInt(
                    model.getValueAt(i, 3).toString());

            MenuItem menu = cariMenu(id);

            if (menu != null) {
                total += menu.harga * jumlah;
            }
        }

        lblTotal.setText(formatRupiah(total));
        lblKembalian.setText(formatRupiah(0));
    }

    private MenuItem cariMenu(int id) {
        for (MenuItem menu : daftarMenu) {
            if (menu.id == id) {
                return menu;
            }
        }
        return null;
    }

    private void hitungKembalian() {
        if (model.getRowCount() == 0) {
            pesan("Tambahkan menu terlebih dahulu.");
            return;
        }

        try {
            long bayar = Long.parseLong(txtBayar.getText().trim());

            if (bayar < 0) {
                pesan("Uang bayar tidak boleh negatif.");
                return;
            }

            if (bayar < total) {
                lblKembalian.setText("-");
                pesan("Pembayaran kurang "
                        + formatRupiah(total - bayar));
                return;
            }

            lblKembalian.setText(formatRupiah(bayar - total));

        } catch (NumberFormatException e) {
            pesan("Masukkan uang bayar berupa angka tanpa titik atau koma.");
        }
    }

    private void hapusItem() {
        int baris = tabel.getSelectedRow();

        if (baris == -1) {
            pesan("Pilih item yang ingin dihapus.");
            return;
        }

        model.removeRow(baris);
        hitungTotal();
    }

    private void selesaikanTransaksi() {
        if (model.getRowCount() == 0) {
            pesan("Transaksi masih kosong.");
            return;
        }

        try {
            long bayar = Long.parseLong(txtBayar.getText().trim());

            if (bayar < total) {
                pesan("Uang pembayaran belum mencukupi.");
                return;
            }

            long kembalian = bayar - total;

            int pilihan = JOptionPane.showConfirmDialog(
                    this,
                    "Total: " + formatRupiah(total)
                    + "\nUang bayar: " + formatRupiah(bayar)
                    + "\nKembalian: " + formatRupiah(kembalian)
                    + "\n\nSelesaikan transaksi?",
                    "Konfirmasi Transaksi",
                    JOptionPane.YES_NO_OPTION);

            if (pilihan == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(
                        this,
                        "Transaksi demo berhasil.\n"
                        + "Total: " + formatRupiah(total)
                        + "\nKembalian: " + formatRupiah(kembalian));

                model.setRowCount(0);
                txtBayar.setText("");
                hitungTotal();
            }

        } catch (NumberFormatException e) {
            pesan("Masukkan uang bayar berupa angka tanpa titik atau koma.");
        }
    }

    private String formatRupiah(long nilai) {
        NumberFormat format = NumberFormat.getCurrencyInstance(
                new Locale("id", "ID"));
        format.setMaximumFractionDigits(0);
        format.setMinimumFractionDigits(0);
        return format.format(nilai);
    }

    private void pesan(String isi) {
        JOptionPane.showMessageDialog(
                this, isi, "Informasi",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private static class MenuItem {
        private final int id;
        private final String nama;
        private final int harga;

        private MenuItem(int id, String nama, int harga) {
            this.id = id;
            this.nama = nama;
            this.harga = harga;
        }

        @Override
        public String toString() {
            return nama + " - " + NumberFormat.getCurrencyInstance(
                    new Locale("id", "ID")).format(harga);
        }
    }
}
