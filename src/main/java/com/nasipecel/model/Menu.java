
package com.nasipecel.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "menu")
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nama_menu", nullable = false, length = 100)
    private String namaMenu;

    @Column(name = "harga", nullable = false, precision = 15, scale = 2)
    private BigDecimal harga;

    @Column(name = "kategori", length = 50)
    private String kategori;

    @Column(name = "status_aktif")
    private Boolean statusAktif = true;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    public Menu() {
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNamaMenu() { return namaMenu; }
    public void setNamaMenu(String namaMenu) { this.namaMenu = namaMenu; }

    public BigDecimal getHarga() { return harga; }
    public void setHarga(BigDecimal harga) { this.harga = harga; }

    public String getKategori() { return kategori; }
    public void setKategori(String kategori) { this.kategori = kategori; }

    public Boolean getStatusAktif() { return statusAktif; }
    public void setStatusAktif(Boolean statusAktif) {
        this.statusAktif = statusAktif;
    }

    public LocalDateTime getCreatedAt() { return createdAt; }
}
