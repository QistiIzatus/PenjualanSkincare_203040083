package id.ac.unpas.functionalcompose.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class penjualanSkincare(
    @PrimaryKey val id: String,
    val nama_produk: String,
    val stok: String,
    val harga: String,
    val deskripsi: String
)
