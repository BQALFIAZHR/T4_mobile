package com.example.belajardatabase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TugasDao {
    @Insert
    suspend fun tambahTugas(tugas: Tugas)

    @Query("SELECT * FROM tabel_tugas ORDER BY id DESC")
    fun ambilSemuaTugas(): LiveData<List<Tugas>>
}