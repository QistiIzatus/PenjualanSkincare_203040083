package id.ac.unpas.functionalcompose.persistences


import androidx.lifecycle.LiveData
import androidx.room.*
import id.ac.unpas.functionalcompose.model.penjualanSkincare

@Dao
interface penjualanSkincareDao {


    @Query("SELECT * FROM penjualanSkincare")
    fun loadAll(): LiveData<List<penjualanSkincare>>
    @Query("SELECT * FROM penjualanSkincare WHERE id = :id")
    fun find(id: String): penjualanSkincare?
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg items: penjualanSkincare)
    @Delete
    fun delete(item: penjualanSkincare)





}
