
package com.nasipecel.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "produksi")
public class Produksi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nomor_produksi", nullable = false, unique = true, length = 30)
    private String nomorProduksi;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id", nullable = false)
    private Menu menu;

    @Column(name = "tanggal")
    private LocalDate tanggal;

    @Column(name = "jumlah_produksi", nullable = false)
    private Integer jumlahProduksi;

    @Column(name = "jumlah_terjual")
    private Integer jumlahTerjual = 0;

    @Column(name = "status", length = 30)
    private String status = "Berjalan";

    @Column(name = "keterangan", columnDefinition = "TEXT")
    private String keterangan;

    @OneToMany(mappedBy = "produksi",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<DetailProduksi> detailProduksi = new ArrayList<>();

    public Produksi() {
    }

    public void tambahDetail(DetailProduksi detail) {
        detailProduksi.add(detail);
        detail.setProduksi(this);
    }

    public void hapusDetail(DetailProduksi detail) {
        detailProduksi.remove(detail);
        detail.setProduksi(null);
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNomorProduksi() { return nomorProduksi; }
    public void setNomorProduksi(String nomorProduksi) {
        this.nomorProduksi = nomorProduksi;
    }

    public Menu getMenu() { return menu; }
    public void setMenu(Menu menu) { this.menu = menu; }

    public LocalDate getTanggal() { return tanggal; }
    public void setTanggal(LocalDate tanggal) {
        this.tanggal = tanggal;
    }

    public Integer getJumlahProduksi() { return jumlahProduksi; }
    public void setJumlahProduksi(Integer jumlahProduksi) {
        this.jumlahProduksi = jumlahProduksi;
    }

    public Integer getJumlahTerjual() { return jumlahTerjual; }
    public void setJumlahTerjual(Integer jumlahTerjual) {
        this.jumlahTerjual = jumlahTerjual;
    }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getKeterangan() { return keterangan; }
    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public List<DetailProduksi> getDetailProduksi() {
        return detailProduksi;
    }

    public void setDetailProduksi(List<DetailProduksi> detailProduksi) {
        this.detailProduksi = detailProduksi;
    }
}
