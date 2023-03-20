package id.ac.unpas.functionalcompose.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.benasher44.uuid.uuid4
import id.ac.unpas.functionalcompose.model.penjualanSkincare
import id.ac.unpas.functionalcompose.persistences.penjualanSkincareDao
import id.ac.unpas.functionalcompose.ui.theme.Purple700
import id.ac.unpas.functionalcompose.ui.theme.Teal200
import kotlinx.coroutines.launch

@Composable
fun FormpenjualanSkincare(penjualanSkincareDao: penjualanSkincareDao) {
    val nama_produk = remember { mutableStateOf(TextFieldValue("")) }
    val stok = remember { mutableStateOf(TextFieldValue("")) }
    val harga = remember { mutableStateOf(TextFieldValue("")) }
    val deskripsi = remember { mutableStateOf(TextFieldValue("")) }
    val scope = rememberCoroutineScope()
    Column(modifier = Modifier
        .padding(10.dp)
        .fillMaxWidth()) {
        OutlinedTextField(
            label = { Text(text = "nama_produk") },
            value = nama_produk.value,
            onValueChange = {
                nama_produk.value = it
            },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            placeholder = { Text(text = "Nama Produk") }
        )
        OutlinedTextField(
            label = { Text(text = "stok") },
            value = stok.value,
            onValueChange = {
                stok.value = it
            },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(capitalization =
            KeyboardCapitalization.Characters, keyboardType = KeyboardType.Text),
            placeholder = { Text(text = "Stok") }
        )
        OutlinedTextField(
            label = { Text(text = "harga") },
            value = harga.value,
            onValueChange = {
                harga.value = it
            },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType =
            KeyboardType.Decimal),
            placeholder = { Text(text = "harga") }
        )
        OutlinedTextField(
            label = { Text(text = "deskripsi") },
            value = deskripsi.value,
            onValueChange = {
                deskripsi.value = it
            },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType =
            KeyboardType.Decimal),
            placeholder = { Text(text = "Deskripsi") }
        )
        val loginButtonColors = ButtonDefaults.buttonColors(
            backgroundColor = Purple700,
            contentColor = Teal200
        )
        val resetButtonColors = ButtonDefaults.buttonColors(
            backgroundColor = Teal200,
            contentColor = Purple700
        )
        Row (modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()) {
            Button(modifier = Modifier.weight(5f), onClick = {
                val id = uuid4().toString()
                val item = penjualanSkincare(id, nama_produk.value.text,
                    stok.value.text, harga.value.text, deskripsi.value.text)
                scope.launch {
                    penjualanSkincareDao.insertAll(item)
                }
                nama_produk.value = TextFieldValue("")
                deskripsi.value = TextFieldValue("")
                stok.value = TextFieldValue("")
            }, colors = loginButtonColors) {
                Text(
                    text = "Simpan",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 18.sp
                    ), modifier = Modifier.padding(8.dp)
                )
            }
            Button(modifier = Modifier.weight(5f), onClick = {
                nama_produk.value = TextFieldValue("")
                deskripsi.value = TextFieldValue("")
                stok.value = TextFieldValue("")
            }, colors = resetButtonColors) {
                Text(
                    text = "Reset",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 18.sp
                    ), modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}




