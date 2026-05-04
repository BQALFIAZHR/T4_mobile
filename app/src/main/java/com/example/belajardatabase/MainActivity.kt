package com.example.belajardatabase

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {

    private lateinit var db: AppDatabase
    private lateinit var adapter: TugasAdapter

    private var daftarTugasSaatIni = emptyList<Tugas>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etJudul = findViewById<EditText>(R.id.etJudul)
        val etDeskripsi = findViewById<EditText>(R.id.etDeskripsi)
        val btnSimpan = findViewById<Button>(R.id.btnSimpan)
        val btnBackup = findViewById<Button>(R.id.btnBackup)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        adapter = TugasAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        db = AppDatabase.getDatabase(this)


        db.tugasDao().ambilSemuaTugas().observe(this) { tugas ->
            adapter.setData(tugas)
            daftarTugasSaatIni = tugas
        }

        btnSimpan.setOnClickListener {
            val judulInput = etJudul.text.toString()
            val deskripsiInput = etDeskripsi.text.toString()

            if (judulInput.isNotEmpty() && deskripsiInput.isNotEmpty()) {
                val tugasBaru = Tugas(judul = judulInput, deskripsi = deskripsiInput)

                lifecycleScope.launch {
                    db.tugasDao().tambahTugas(tugasBaru)
                }

                etJudul.text.clear()
                etDeskripsi.text.clear()
                Toast.makeText(this, "Berhasil disimpan di Database!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Isi judul dan deskripsi dulu!", Toast.LENGTH_SHORT).show()
            }
        }

        btnBackup.setOnClickListener {
            backupKeFileTeks(daftarTugasSaatIni)
        }
    }

    private fun backupKeFileTeks(tugasList: List<Tugas>) {
        if (tugasList.isEmpty()) {
            Toast.makeText(this, "Tidak ada data untuk dibackup!", Toast.LENGTH_SHORT).show()
            return
        }

        val namaFile = "BackupCatatan.txt"
        val isiTeks = StringBuilder()

        isiTeks.append("=== DAFTAR CATATAN TUGAS ===\n\n")
        for (tugas in tugasList) {
            isiTeks.append("- Judul: ${tugas.judul}\n")
            isiTeks.append("  Deskripsi: ${tugas.deskripsi}\n\n")
        }

        try {

            openFileOutput(namaFile, Context.MODE_PRIVATE).use {
                it.write(isiTeks.toString().toByteArray())
            }
            Toast.makeText(this, "Berhasil backup ke file TXT!", Toast.LENGTH_LONG).show()
        } catch (e: Exception) {
            Toast.makeText(this, "Gagal backup: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }
}
