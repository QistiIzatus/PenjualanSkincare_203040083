package id.ac.unpas.functionalcompose.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.room.Room
import id.ac.unpas.functionalcompose.model.penjualanSkincare
import id.ac.unpas.functionalcompose.persistences.AppDatabase

@Composable
fun PengelolaanpenjualanScreen() {
    val context = LocalContext.current
    val db = Room.databaseBuilder(
        context,
        AppDatabase::class.java, "penjualan-Skincare"
    ).build()
    val penjualanSkincareDao = db.penjualanSkincareDao()
    val list : LiveData<List<penjualanSkincare>> =  penjualanSkincareDao.loadAll()
    val items: List<penjualanSkincare> by list.observeAsState(initial = listOf())

    Column(modifier = Modifier.fillMaxWidth()) {
        FormpenjualanSkincare(penjualanSkincareDao)
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(items = items, itemContent = { item ->
                Row(modifier = Modifier
                    .padding(15.dp)
                    .fillMaxWidth()) {

                    Column(modifier = Modifier.weight(3f)) {
                        Text(text = "nama_produk", fontSize = 14.sp)
                        Text(text = item.nama_produk, fontSize = 16.sp,
                            fontWeight = FontWeight.Bold)
                    }
                    Column(modifier = Modifier.weight(3f)) {
                        Text(text = "stok", fontSize = 14.sp)
                        Text(text = item.stok, fontSize = 16.sp, fontWeight =
                        FontWeight.Bold)
                    }
                    Column(modifier = Modifier.weight(3f)) {
                        Text(text = "harga", fontSize = 14.sp)
                        Text(text = "${item.harga} ", fontSize = 16.sp,
                            fontWeight = FontWeight.Bold)
                    }
                    Column(modifier = Modifier.weight(3f)) {
                        Text(text = "deskripsi", fontSize = 14.sp)
                        Text(text = "${item.deskripsi} ", fontSize = 16.sp,
                            fontWeight = FontWeight.Bold)
                    }
                }
                Divider(modifier = Modifier.fillMaxWidth())
            })
        }
    }
}




















