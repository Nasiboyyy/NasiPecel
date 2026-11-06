
package com.nasipecel.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "detail_produksi")
public class DetailProduksi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produksi_id", nullable = false)
    private Produksi produksi;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bahan_id", nullable = false)
    private BahanBaku bahan;

    @Column(name = "jumlah_digunakan", precision = 12, scale = 2)
    private BigDecimal jumlahDigunakan;

    @Column(name = "satuan", length = 30)
    private String satuan;

    public DetailProduksi() {
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Produksi getProduksi() { return produksi; }
    public void setProduksi(Produksi produksi) {
        this.produksi = produksi;
    }

    public BahanBaku getBahan() { return bahan; }
    public void setBahan(BahanBaku bahan) {
        this.bahan = bahan;
    }

    public BigDecimal getJumlahDigunakan() {
        return jumlahDigunakan;
    }

    public void setJumlahDigunakan(BigDecimal jumlahDigunakan) {
        this.jumlahDigunakan = jumlahDigunakan;
    }

    public String getSatuan() { return satuan; }
    public void setSatuan(String satuan) { this.satuan = satuan; }
}
