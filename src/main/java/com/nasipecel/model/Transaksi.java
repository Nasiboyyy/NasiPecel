
package com.nasipecel.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "transaksi")
public class Transaksi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nomor_transaksi", nullable = false, unique = true, length = 30)
    private String nomorTransaksi;

    @Column(name = "tanggal")
    private LocalDateTime tanggal;

    @Column(name = "total", precision = 15, scale = 2)
    private BigDecimal total = BigDecimal.ZERO;

    @Column(name = "pembayaran", length = 30)
    private String pembayaran;

    @Column(name = "status", length = 30)
    private String status = "Diproses";

    @Column(name = "keterangan", columnDefinition = "TEXT")
    private String keterangan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id")
    private Admin admin;

    @OneToMany(mappedBy = "transaksi",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<DetailTransaksi> detailTransaksi = new ArrayList<>();

    public Transaksi() {
    }

    public void tambahDetail(DetailTransaksi detail) {
        detailTransaksi.add(detail);
        detail.setTransaksi(this);
    }

    public void hapusDetail(DetailTransaksi detail) {
        detailTransaksi.remove(detail);
        detail.setTransaksi(null);
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNomorTransaksi() { return nomorTransaksi; }
    public void setNomorTransaksi(String nomorTransaksi) {
        this.nomorTransaksi = nomorTransaksi;
    }

    public LocalDateTime getTanggal() { return tanggal; }
    public void setTanggal(LocalDateTime tanggal) {
        this.tanggal = tanggal;
    }

    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }

    public String getPembayaran() { return pembayaran; }
    public void setPembayaran(String pembayaran) {
        this.pembayaran = pembayaran;
    }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getKeterangan() { return keterangan; }
    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public Admin getAdmin() { return admin; }
    public void setAdmin(Admin admin) { this.admin = admin; }

    public List<DetailTransaksi> getDetailTransaksi() {
        return detailTransaksi;
    }

    public void setDetailTransaksi(List<DetailTransaksi> detailTransaksi) {
        this.detailTransaksi = detailTransaksi;
    }
}
