
package com.nasipecel.view;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.awt.*;
import java.io.File;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class LaporanFrame extends JFrame {

    private final Color hijau = new Color(38, 82, 55);
    private final Color hijauTerang = new Color(55, 112, 76);
    private final Color hijauMuda = new Color(232, 243, 234);
    private final Color latar = new Color(246, 248, 246);
    private final Color putih = Color.WHITE;
    private final Color teks = new Color(43, 54, 46);
    private final Color abu = new Color(125, 135, 127);
    private final Color garis = new Color(225, 232, 226);
    private final Color merah = new Color(190, 65, 65);
    private final Color biru = new Color(48, 110, 165);
    private final Color oranye = new Color(215, 145, 48);

    private JTable table;
    private DefaultTableModel model;

    private JTextField txtCari;
    private JComboBox<String> cmbPeriode;

    private JLabel lblPenjualan;
    private JLabel lblTransaksi;
    private JLabel lblPorsi;
    private JLabel lblJudulPeriode;

    private final NumberFormat rupiah =
            NumberFormat.getCurrencyInstance(new Locale("id", "ID"));

    private final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("dd-MM-yyyy");

    private final ArrayList<Transaksi> daftarTransaksi = new ArrayList<>();

    private int nomorBerikutnya = 8;

    public LaporanFrame() {
        setTitle("Laporan ERP Nasi Pecel");
        setSize(1200, 760);
        setMinimumSize(new Dimension(950, 650));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        getContentPane().setBackground(latar);
        setLayout(new BorderLayout());

        isiDataContoh();
        initComponents();
        refreshTabel();
    }

    // ================= DATA TRANSAKSI =================

    private static class Transaksi {
        String nomor;
        LocalDate tanggal;
        int porsi;
        double total;
        String pembayaran;
        String status;

        Transaksi(String nomor, LocalDate tanggal, int porsi,
                  double total, String pembayaran, String status) {
            this.nomor = nomor;
            this.tanggal = tanggal;
            this.porsi = porsi;
            this.total = total;
            this.pembayaran = pembayaran;
            this.status = status;
        }
    }

    private void isiDataContoh() {
        LocalDate hariIni = LocalDate.now();

        daftarTransaksi.add(new Transaksi(
                "TRX-001", hariIni, 10, 150000, "Tunai", "Selesai"));

        daftarTransaksi.add(new Transaksi(
                "TRX-002", hariIni, 15, 225000, "QRIS", "Selesai"));

        daftarTransaksi.add(new Transaksi(
                "TRX-003", hariIni.minusDays(1), 8, 120000, "Tunai", "Selesai"));

        daftarTransaksi.add(new Transaksi(
                "TRX-004", hariIni.minusDays(2), 12, 180000, "Transfer", "Selesai"));

        daftarTransaksi.add(new Transaksi(
                "TRX-005", hariIni.minusDays(3), 20, 300000, "QRIS", "Selesai"));

        daftarTransaksi.add(new Transaksi(
                "TRX-006", hariIni.minusDays(8), 7, 105000, "Tunai", "Selesai"));

        daftarTransaksi.add(new Transaksi(
                "TRX-007", hariIni.minusDays(15), 18, 270000, "QRIS", "Selesai"));
    }

    // ================= TAMPILAN UTAMA =================

    private void initComponents() {
        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(latar);

        root.add(buatHeader(), BorderLayout.NORTH);

        JPanel konten = new JPanel();
        konten.setLayout(new BoxLayout(konten, BoxLayout.Y_AXIS));
        konten.setBackground(latar);
        konten.setBorder(new EmptyBorder(25, 30, 25, 30));

        konten.add(buatJudul());
        konten.add(Box.createVerticalStrut(22));
        konten.add(buatKartuRingkasan());
        konten.add(Box.createVerticalStrut(24));
        konten.add(buatPanelTabel());

        JScrollPane scroll = new JScrollPane(konten);
        scroll.setBorder(null);
        scroll.getViewport().setBackground(latar);
        scroll.getVerticalScrollBar().setUnitIncrement(16);

        root.add(scroll, BorderLayout.CENTER);
        add(root);
    }

    private JPanel buatHeader() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(putih);
        panel.setBorder(new CompoundBorder(
                new MatteBorder(0, 0, 1, 0, garis),
                new EmptyBorder(17, 30, 17, 30)
        ));

        JLabel logo = new JLabel("🌿  ERP NASI PECEL");
        logo.setFont(new Font("SansSerif", Font.BOLD, 19));
        logo.setForeground(hijau);

        JLabel admin = new JLabel("Administrator  ●");
        admin.setFont(new Font("SansSerif", Font.BOLD, 12));
        admin.setForeground(hijauTerang);

        panel.add(logo, BorderLayout.WEST);
        panel.add(admin, BorderLayout.EAST);

        return panel;
    }

    private JPanel buatJudul() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(latar);

        JPanel kiri = new JPanel();
        kiri.setLayout(new BoxLayout(kiri, BoxLayout.Y_AXIS));
        kiri.setBackground(latar);

        JLabel judul = new JLabel("Laporan Penjualan");
        judul.setFont(new Font("SansSerif", Font.BOLD, 27));
        judul.setForeground(teks);

        JLabel deskripsi = new JLabel(
                "Kelola, pantau, dan evaluasi seluruh transaksi penjualan."
        );
        deskripsi.setFont(new Font("SansSerif", Font.PLAIN, 13));
        deskripsi.setForeground(abu);

        kiri.add(judul);
        kiri.add(Box.createVerticalStrut(7));
        kiri.add(deskripsi);

        panel.add(kiri, BorderLayout.WEST);

        return panel;
    }

    // ================= KARTU RINGKASAN =================

    private JPanel buatKartuRingkasan() {
        JPanel panel = new JPanel(new GridLayout(1, 3, 17, 0));
        panel.setBackground(latar);
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 125));

        lblPenjualan = new JLabel("Rp0");
        lblTransaksi = new JLabel("0");
        lblPorsi = new JLabel("0");

        panel.add(buatKartu(
                "TOTAL PENJUALAN", lblPenjualan, "💰",
                new Color(232, 243, 234)));

        panel.add(buatKartu(
                "JUMLAH TRANSAKSI", lblTransaksi, "🧾",
                new Color(232, 240, 250)));

        panel.add(buatKartu(
                "TOTAL PORSI TERJUAL", lblPorsi, "🍽",
                new Color(250, 242, 228)));

        return panel;
    }

    private JPanel buatKartu(String judul, JLabel nilai,
                             String ikon, Color warnaIkon) {

        JPanel kartu = new JPanel(new BorderLayout(12, 10));
        kartu.setBackground(putih);
        kartu.setBorder(new CompoundBorder(
                new LineBorder(garis, 1, true),
                new EmptyBorder(18, 20, 18, 20)
        ));

        JLabel icon = new JLabel(ikon, SwingConstants.CENTER);
        icon.setFont(new Font("SansSerif", Font.PLAIN, 25));
        icon.setOpaque(true);
        icon.setBackground(warnaIkon);
        icon.setPreferredSize(new Dimension(52, 52));
        icon.setBorder(new EmptyBorder(8, 8, 8, 8));

        JPanel informasi = new JPanel();
        informasi.setLayout(new BoxLayout(informasi, BoxLayout.Y_AXIS));
        informasi.setBackground(putih);

        JLabel label = new JLabel(judul);
        label.setFont(new Font("SansSerif", Font.BOLD, 11));
        label.setForeground(abu);

        nilai.setFont(new Font("SansSerif", Font.BOLD, 22));
        nilai.setForeground(hijau);

        informasi.add(label);
        informasi.add(Box.createVerticalStrut(9));
        informasi.add(nilai);

        kartu.add(icon, BorderLayout.WEST);
        kartu.add(informasi, BorderLayout.CENTER);

        return kartu;
    }

    // ================= PANEL TABEL =================

    private JPanel buatPanelTabel() {
        JPanel panel = new JPanel(new BorderLayout(0, 17));
        panel.setBackground(putih);
        panel.setBorder(new CompoundBorder(
                new LineBorder(garis, 1, true),
                new EmptyBorder(22, 22, 22, 22)
        ));

        panel.setPreferredSize(new Dimension(1000, 480));

        JPanel bagianAtas = new JPanel(new BorderLayout());
        bagianAtas.setBackground(putih);

        JPanel judulPanel = new JPanel();
        judulPanel.setLayout(new BoxLayout(judulPanel, BoxLayout.Y_AXIS));
        judulPanel.setBackground(putih);

        JLabel judul = new JLabel("Riwayat Transaksi");
        judul.setFont(new Font("SansSerif", Font.BOLD, 18));
        judul.setForeground(teks);

        lblJudulPeriode = new JLabel("Semua periode");
        lblJudulPeriode.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblJudulPeriode.setForeground(abu);

        judulPanel.add(judul);
        judulPanel.add(Box.createVerticalStrut(6));
        judulPanel.add(lblJudulPeriode);

        bagianAtas.add(judulPanel, BorderLayout.WEST);

        JPanel filter = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        filter.setBackground(putih);

        cmbPeriode = new JComboBox<>(new String[]{
            "Semua Periode", "Harian", "Mingguan", "Bulanan"
        });
        cmbPeriode.setFont(new Font("SansSerif", Font.PLAIN, 12));
        cmbPeriode.setPreferredSize(new Dimension(140, 36));
        cmbPeriode.addActionListener(e -> refreshTabel());

        txtCari = new JTextField();
        txtCari.setPreferredSize(new Dimension(190, 36));
        txtCari.setFont(new Font("SansSerif", Font.PLAIN, 12));
        txtCari.setBorder(new CompoundBorder(
                new LineBorder(garis, 1, true),
                new EmptyBorder(5, 10, 5, 10)
        ));
        txtCari.setToolTipText("Cari nomor transaksi atau pembayaran");
        txtCari.addActionListener(e -> refreshTabel());

        JButton btnCari = buatTombol("Cari", biru);
        btnCari.addActionListener(e -> refreshTabel());

        filter.add(cmbPeriode);
        filter.add(txtCari);
        filter.add(btnCari);

        bagianAtas.add(filter, BorderLayout.EAST);

        JPanel tombolAksi = new JPanel(new FlowLayout(FlowLayout.LEFT, 9, 0));
        tombolAksi.setBackground(putih);

        JButton btnTambah = buatTombol("+ Tambah Transaksi", hijau);
        JButton btnEdit = buatTombol("✎ Edit", biru);
        JButton btnHapus = buatTombol("Hapus", merah);
        JButton btnCetak = buatTombol("Cetak", new Color(105, 112, 105));
        JButton btnEkspor = buatTombol("Ekspor CSV", new Color(48, 115, 105));

        btnTambah.addActionListener(e -> tambahTransaksi());
        btnEdit.addActionListener(e -> editTransaksi());
        btnHapus.addActionListener(e -> hapusTransaksi());
        btnCetak.addActionListener(e -> cetakLaporan());
        btnEkspor.addActionListener(e -> eksporCSV());

        tombolAksi.add(btnTambah);
        tombolAksi.add(btnEdit);
        tombolAksi.add(btnHapus);
        tombolAksi.add(btnCetak);
        tombolAksi.add(btnEkspor);

        JPanel atas = new JPanel(new BorderLayout(0, 18));
        atas.setBackground(putih);
        atas.add(bagianAtas, BorderLayout.NORTH);
        atas.add(tombolAksi, BorderLayout.SOUTH);

        String[] kolom = {
            "No.", "Tanggal", "No. Transaksi",
            "Jumlah Porsi", "Total Penjualan",
            "Pembayaran", "Status"
        };

        model = new DefaultTableModel(kolom, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(model);
        table.setRowHeight(45);
        table.setFont(new Font("SansSerif", Font.PLAIN, 12));
        table.setForeground(teks);
        table.setGridColor(garis);
        table.setShowVerticalLines(false);
        table.setSelectionBackground(hijauMuda);
        table.setSelectionForeground(teks);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

JTableHeader header = table.getTableHeader();
header.setPreferredSize(new Dimension(0, 48));
header.setBackground(new Color(31, 70, 46));
header.setForeground(Color.WHITE);
header.setFont(new Font("SansSerif", Font.BOLD, 13));
header.setOpaque(true);
header.setBorder(new MatteBorder(
        0, 0, 2, 0, new Color(105, 155, 112)
));

header.setDefaultRenderer(new DefaultTableCellRenderer() {
    @Override
    public Component getTableCellRendererComponent(
            JTable table, Object value,
            boolean selected, boolean focus,
            int row, int column) {

        JLabel label = (JLabel) super.getTableCellRendererComponent(
                table, value, selected, focus, row, column
        );

        label.setBackground(new Color(31, 70, 46));
        label.setForeground(Color.WHITE);
        label.setFont(new Font("SansSerif", Font.BOLD, 13));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setOpaque(true);
        label.setBorder(new MatteBorder(
                0, 0, 1, 1, new Color(75, 112, 83)
        ));

        return label;
    }
});

        DefaultTableCellRenderer tengah = new DefaultTableCellRenderer();
        tengah.setHorizontalAlignment(SwingConstants.CENTER);

        for (int i : new int[]{0, 1, 2, 3, 5}) {
            table.getColumnModel().getColumn(i).setCellRenderer(tengah);
        }

        table.getColumnModel().getColumn(4).setCellRenderer(
                new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(
                    JTable t, Object value, boolean selected,
                    boolean focus, int row, int column) {

                JLabel label = (JLabel) super.getTableCellRendererComponent(
                        t, value, selected, focus, row, column);

                label.setHorizontalAlignment(SwingConstants.RIGHT);
                label.setBorder(new EmptyBorder(0, 0, 0, 15));
                label.setForeground(hijau);
                label.setFont(new Font("SansSerif", Font.BOLD, 12));

                if (selected) {
                    label.setBackground(hijauMuda);
                }

                return label;
            }
        });

        table.getColumnModel().getColumn(6).setCellRenderer(
                new StatusRenderer());

        JScrollPane scroll = new JScrollPane(table);
        scroll.setBorder(new LineBorder(garis, 1));
        scroll.getViewport().setBackground(putih);
        scroll.getVerticalScrollBar().setUnitIncrement(14);

        panel.add(atas, BorderLayout.NORTH);
        panel.add(scroll, BorderLayout.CENTER);

        return panel;
    }

    // ================= STATUS TABEL =================

    private class StatusRenderer extends DefaultTableCellRenderer {
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

                if (status.equals("Selesai")) {
                    label.setForeground(hijau);
                    label.setBackground(hijauMuda);
                } else if (status.equals("Diproses")) {
                    label.setForeground(new Color(160, 105, 25));
                    label.setBackground(new Color(250, 242, 220));
                } else {
                    label.setForeground(merah);
                    label.setBackground(new Color(250, 232, 232));
                }
            }

            return label;
        }
    }

    // ================= REFRESH DATA =================

    private void refreshTabel() {
        if (model == null) return;

        model.setRowCount(0);

        String periode = cmbPeriode.getSelectedItem().toString();
        String cari = txtCari.getText().trim().toLowerCase();

        LocalDate sekarang = LocalDate.now();

        double totalPenjualan = 0;
        int totalPorsi = 0;
        int jumlahTransaksi = 0;

        ArrayList<Transaksi> hasil = new ArrayList<>();

        for (Transaksi trx : daftarTransaksi) {
            boolean sesuaiPeriode = true;

            switch (periode) {
                case "Harian":
                    sesuaiPeriode = trx.tanggal.equals(sekarang);
                    break;

                case "Mingguan":
                    sesuaiPeriode =
                            !trx.tanggal.isBefore(sekarang.minusDays(6))
                            && !trx.tanggal.isAfter(sekarang);
                    break;

                case "Bulanan":
                    sesuaiPeriode =
                            trx.tanggal.getMonth() == sekarang.getMonth()
                            && trx.tanggal.getYear() == sekarang.getYear();
                    break;
            }

            boolean sesuaiCari =
                    trx.nomor.toLowerCase().contains(cari)
                    || trx.pembayaran.toLowerCase().contains(cari);

            if (sesuaiPeriode && sesuaiCari) {
                hasil.add(trx);
            }
        }

        // Tampilkan transaksi terbaru lebih dahulu.
        for (int i = hasil.size() - 1; i >= 0; i--) {
            Transaksi trx = hasil.get(i);

            model.addRow(new Object[]{
                hasil.size() - i,
                trx.tanggal.format(formatter),
                trx.nomor,
                trx.porsi,
                rupiah.format(trx.total),
                trx.pembayaran,
                trx.status
            });

            totalPenjualan += trx.total;
            totalPorsi += trx.porsi;
            jumlahTransaksi++;
        }

        lblPenjualan.setText(rupiah.format(totalPenjualan));
        lblTransaksi.setText(String.valueOf(jumlahTransaksi));
        lblPorsi.setText(String.valueOf(totalPorsi));
        lblJudulPeriode.setText("Periode: " + periode
                + "  |  " + jumlahTransaksi + " transaksi");
    }

    // ================= TAMBAH TRANSAKSI =================

    private void tambahTransaksi() {
        JTextField txtNomor = new JTextField("TRX-" +
                String.format("%03d", nomorBerikutnya));
        JTextField txtTanggal = new JTextField(
                LocalDate.now().format(formatter));
        JTextField txtPorsi = new JTextField();
        JTextField txtTotal = new JTextField();

        JComboBox<String> cmbPembayaran = new JComboBox<>(
                new String[]{"Tunai", "QRIS", "Transfer"});

        JComboBox<String> cmbStatus = new JComboBox<>(
                new String[]{"Selesai", "Diproses", "Dibatalkan"});

        JPanel form = buatForm(
                txtNomor, txtTanggal, txtPorsi, txtTotal,
                cmbPembayaran, cmbStatus);

        int hasil = JOptionPane.showConfirmDialog(
                this, form, "Tambah Transaksi",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        if (hasil != JOptionPane.OK_OPTION) return;

        try {
            String nomor = txtNomor.getText().trim();
            LocalDate tanggal = LocalDate.parse(
                    txtTanggal.getText().trim(), formatter);
            int porsi = Integer.parseInt(txtPorsi.getText().trim());
            double total = Double.parseDouble(
                    txtTotal.getText().trim().replace(".", ""));

            if (nomor.isEmpty() || porsi <= 0 || total < 0) {
                throw new IllegalArgumentException();
            }

            if (cariNomor(nomor) != null) {
                JOptionPane.showMessageDialog(this,
                        "Nomor transaksi sudah digunakan.");
                return;
            }

            daftarTransaksi.add(new Transaksi(
                    nomor, tanggal, porsi, total,
                    cmbPembayaran.getSelectedItem().toString(),
                    cmbStatus.getSelectedItem().toString()
            ));

            nomorBerikutnya++;
            refreshTabel();

            JOptionPane.showMessageDialog(this,
                    "Transaksi berhasil ditambahkan.");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Periksa kembali data transaksi.\n"
                    + "Tanggal: dd-MM-yyyy\n"
                    + "Jumlah porsi dan total harus berupa angka.",
                    "Data Tidak Valid", JOptionPane.WARNING_MESSAGE);
        }
    }

    // ================= EDIT TRANSAKSI =================

    private void editTransaksi() {
        int baris = table.getSelectedRow();

        if (baris == -1) {
            JOptionPane.showMessageDialog(this,
                    "Pilih transaksi yang ingin diedit terlebih dahulu.");
            return;
        }

        String nomorLama = model.getValueAt(baris, 2).toString();
        Transaksi trx = cariNomor(nomorLama);

        if (trx == null) return;

        JTextField txtNomor = new JTextField(trx.nomor);
        JTextField txtTanggal = new JTextField(
                trx.tanggal.format(formatter));
        JTextField txtPorsi = new JTextField(
                String.valueOf(trx.porsi));
        JTextField txtTotal = new JTextField(
                String.valueOf((long) trx.total));

        JComboBox<String> cmbPembayaran = new JComboBox<>(
                new String[]{"Tunai", "QRIS", "Transfer"});
        cmbPembayaran.setSelectedItem(trx.pembayaran);

        JComboBox<String> cmbStatus = new JComboBox<>(
                new String[]{"Selesai", "Diproses", "Dibatalkan"});
        cmbStatus.setSelectedItem(trx.status);

        JPanel form = buatForm(
                txtNomor, txtTanggal, txtPorsi, txtTotal,
                cmbPembayaran, cmbStatus);

        int hasil = JOptionPane.showConfirmDialog(
                this, form, "Edit Transaksi",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        if (hasil != JOptionPane.OK_OPTION) return;

        try {
            String nomorBaru = txtNomor.getText().trim();
            LocalDate tanggal = LocalDate.parse(
                    txtTanggal.getText().trim(), formatter);
            int porsi = Integer.parseInt(txtPorsi.getText().trim());
            double total = Double.parseDouble(
                    txtTotal.getText().trim().replace(".", ""));

            if (nomorBaru.isEmpty() || porsi <= 0 || total < 0) {
                throw new IllegalArgumentException();
            }

            Transaksi duplikat = cariNomor(nomorBaru);
            if (duplikat != null && duplikat != trx) {
                JOptionPane.showMessageDialog(this,
                        "Nomor transaksi sudah digunakan.");
                return;
            }

            trx.nomor = nomorBaru;
            trx.tanggal = tanggal;
            trx.porsi = porsi;
            trx.total = total;
            trx.pembayaran = cmbPembayaran.getSelectedItem().toString();
            trx.status = cmbStatus.getSelectedItem().toString();

            refreshTabel();

            JOptionPane.showMessageDialog(this,
                    "Data transaksi berhasil diperbarui.");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Data tidak valid. Periksa kembali isian Anda.",
                    "Kesalahan", JOptionPane.WARNING_MESSAGE);
        }
    }

    // ================= HAPUS TRANSAKSI =================

    private void hapusTransaksi() {
        int baris = table.getSelectedRow();

        if (baris == -1) {
            JOptionPane.showMessageDialog(this,
                    "Pilih transaksi yang ingin dihapus.");
            return;
        }

        String nomor = model.getValueAt(baris, 2).toString();

        int konfirmasi = JOptionPane.showConfirmDialog(
                this,
                "Apakah Anda yakin ingin menghapus transaksi "
                + nomor + "?",
                "Konfirmasi Hapus",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        if (konfirmasi == JOptionPane.YES_OPTION) {
            Transaksi trx = cariNomor(nomor);

            if (trx != null) {
                daftarTransaksi.remove(trx);
                refreshTabel();

                JOptionPane.showMessageDialog(this,
                        "Transaksi berhasil dihapus.");
            }
        }
    }

    private Transaksi cariNomor(String nomor) {
        for (Transaksi trx : daftarTransaksi) {
            if (trx.nomor.equalsIgnoreCase(nomor)) {
                return trx;
            }
        }
        return null;
    }

    // ================= FORM INPUT =================

    private JPanel buatForm(
            JTextField nomor, JTextField tanggal,
            JTextField porsi, JTextField total,
            JComboBox<String> pembayaran,
            JComboBox<String> status) {

        JPanel panel = new JPanel(new GridLayout(6, 2, 12, 13));
        panel.setBackground(putih);
        panel.setBorder(new EmptyBorder(12, 12, 12, 12));
        panel.setPreferredSize(new Dimension(420, 270));

        tambahForm(panel, "Nomor Transaksi", nomor);
        tambahForm(panel, "Tanggal (dd-MM-yyyy)", tanggal);
        tambahForm(panel, "Jumlah Porsi", porsi);
        tambahForm(panel, "Total Penjualan (angka)", total);
        tambahForm(panel, "Metode Pembayaran", pembayaran);
        tambahForm(panel, "Status Transaksi", status);

        return panel;
    }

    private void tambahForm(JPanel panel, String judul, JComponent input) {
        JLabel label = new JLabel(judul);
        label.setFont(new Font("SansSerif", Font.BOLD, 12));
        label.setForeground(teks);

        input.setFont(new Font("SansSerif", Font.PLAIN, 12));

        if (input instanceof JTextField) {
            input.setBorder(new CompoundBorder(
                    new LineBorder(garis, 1, true),
                    new EmptyBorder(7, 9, 7, 9)
            ));
        }

        panel.add(label);
        panel.add(input);
    }

    // ================= CETAK LAPORAN =================

    private void cetakLaporan() {
        try {
            boolean berhasil = table.print();

            if (berhasil) {
                JOptionPane.showMessageDialog(this,
                        "Laporan berhasil dicetak.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Gagal mencetak laporan: " + e.getMessage());
        }
    }

    // ================= EKSPOR CSV =================

    private void eksporCSV() {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Ekspor Laporan Penjualan");
        chooser.setSelectedFile(new File("Laporan_Penjualan.csv"));

        if (chooser.showSaveDialog(this) != JFileChooser.APPROVE_OPTION) {
            return;
        }

        File file = chooser.getSelectedFile();

        if (!file.getName().toLowerCase().endsWith(".csv")) {
            file = new File(file.getAbsolutePath() + ".csv");
        }

        try (PrintWriter writer = new PrintWriter(file, "UTF-8")) {
            writer.println("sep=;");

            for (int i = 0; i < model.getColumnCount(); i++) {
                writer.print(model.getColumnName(i));
                writer.print(i == model.getColumnCount() - 1 ? "\n" : ";");
            }

            for (int i = 0; i < model.getRowCount(); i++) {
                for (int j = 0; j < model.getColumnCount(); j++) {
                    String isi = model.getValueAt(i, j).toString()
                            .replace(";", ",");

                    writer.print(isi);
                    writer.print(j == model.getColumnCount() - 1 ? "\n" : ";");
                }
            }

            JOptionPane.showMessageDialog(this,
                    "Laporan berhasil diekspor:\n" + file.getAbsolutePath());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Gagal mengekspor laporan: " + e.getMessage());
        }
    }

    // ================= TOMBOL =================

    private JButton buatTombol(String nama, Color warna) {
        JButton tombol = new JButton(nama);

        tombol.setFont(new Font("SansSerif", Font.BOLD, 11));
        tombol.setForeground(putih);
        tombol.setBackground(warna);
        tombol.setFocusPainted(false);
        tombol.setBorder(new EmptyBorder(10, 15, 10, 15));
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
}
