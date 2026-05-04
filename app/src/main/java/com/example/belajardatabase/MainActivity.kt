package com.example.belajardatabase

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var db: AppDatabase
    private lateinit var adapter: TugasAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etJudul = findViewById<EditText>(R.id.etJudul)
        val etDeskripsi = findViewById<EditText>(R.id.etDeskripsi)
        val btnSimpan = findViewById<Button>(R.id.btnSimpan)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        adapter = TugasAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        db = AppDatabase.getDatabase(this)

        db.tugasDao().ambilSemuaTugas().observe(this) { tugas ->
            adapter.setData(tugas)
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

                Toast.makeText(this, "Berhasil disimpan!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Isi judul dan deskripsi dulu!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}