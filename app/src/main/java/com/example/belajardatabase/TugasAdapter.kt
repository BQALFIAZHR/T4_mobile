package com.example.belajardatabase

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TugasAdapter : RecyclerView.Adapter<TugasAdapter.TugasViewHolder>() {

    private var daftarTugas = emptyList<Tugas>()

    class TugasViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvJudul: TextView = itemView.findViewById(R.id.tvJudulItem)
        val tvDeskripsi: TextView = itemView.findViewById(R.id.tvDeskripsiItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TugasViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_tugas, parent, false)
        return TugasViewHolder(view)
    }

    override fun onBindViewHolder(holder: TugasViewHolder, position: Int) {
        val tugasSaatIni = daftarTugas[position]
        holder.tvJudul.text = tugasSaatIni.judul
        holder.tvDeskripsi.text = tugasSaatIni.deskripsi
    }

    override fun getItemCount(): Int {
        return daftarTugas.size
    }

    fun setData(tugas: List<Tugas>) {
        this.daftarTugas = tugas
        notifyDataSetChanged()
    }
}