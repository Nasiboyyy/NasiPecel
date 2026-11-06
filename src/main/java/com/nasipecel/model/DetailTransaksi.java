
package com.nasipecel.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "detail_transaksi")
public class DetailTransaksi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transaksi_id", nullable = false)
    private Transaksi transaksi;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id", nullable = false)
    private Menu menu;

    @Column(name = "jumlah", nullable = false)
    private Integer jumlah;

    @Column(name = "harga_satuan", precision = 15, scale = 2)
    private BigDecimal hargaSatuan;

    @Column(name = "subtotal", precision = 15, scale = 2)
    private BigDecimal subtotal;

    public DetailTransaksi() {
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Transaksi getTransaksi() { return transaksi; }
    public void setTransaksi(Transaksi transaksi) {
        this.transaksi = transaksi;
    }

    public Menu getMenu() { return menu; }
    public void setMenu(Menu menu) { this.menu = menu; }

    public Integer getJumlah() { return jumlah; }
    public void setJumlah(Integer jumlah) { this.jumlah = jumlah; }

    public BigDecimal getHargaSatuan() { return hargaSatuan; }
    public void setHargaSatuan(BigDecimal hargaSatuan) {
        this.hargaSatuan = hargaSatuan;
    }

    public BigDecimal getSubtotal() { return subtotal; }
    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }
}
