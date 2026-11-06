
package com.nasipecel.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "bahan_baku")
public class BahanBaku {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nama_bahan", nullable = false, length = 100)
    private String namaBahan;

    @Column(name = "stok", precision = 12, scale = 2)
    private BigDecimal stok = BigDecimal.ZERO;

    @Column(name = "satuan", length = 30)
    private String satuan;

    @Column(name = "stok_minimum", precision = 12, scale = 2)
    private BigDecimal stokMinimum = BigDecimal.ZERO;

    @Column(name = "harga_per_satuan", precision = 15, scale = 2)
    private BigDecimal hargaPerSatuan = BigDecimal.ZERO;

    @Column(name = "updated_at", insertable = false, updatable = false)
    private LocalDateTime updatedAt;

    public BahanBaku() {
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNamaBahan() { return namaBahan; }
    public void setNamaBahan(String namaBahan) {
        this.namaBahan = namaBahan;
    }

    public BigDecimal getStok() { return stok; }
    public void setStok(BigDecimal stok) { this.stok = stok; }

    public String getSatuan() { return satuan; }
    public void setSatuan(String satuan) { this.satuan = satuan; }

    public BigDecimal getStokMinimum() { return stokMinimum; }
    public void setStokMinimum(BigDecimal stokMinimum) {
        this.stokMinimum = stokMinimum;
    }

    public BigDecimal getHargaPerSatuan() { return hargaPerSatuan; }
    public void setHargaPerSatuan(BigDecimal hargaPerSatuan) {
        this.hargaPerSatuan = hargaPerSatuan;
    }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
}
