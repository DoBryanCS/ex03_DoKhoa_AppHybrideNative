package com.example.exo3_dokhoa

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class rvArtisteAdapter(private var datasetArtiste: MutableList<Artiste>) :
    RecyclerView.Adapter<rvArtisteAdapter.RecyclerViewViewHolder>() {

    class RecyclerViewViewHolder(val view: View) : RecyclerView.ViewHolder(view)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.artiste_items, parent, false) as View
        return RecyclerViewViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewViewHolder, position: Int) {
        holder.view.findViewById<TextView>(R.id.tvArtiste).text = this.datasetArtiste[position].nom
        holder.view.setOnClickListener() {
            val intent = Intent(holder.view.context, MainActivity2::class.java)
            intent.putExtra("artisteNom", this.datasetArtiste[position].nom)
            println(intent.extras)
            holder.view.context.startActivity(intent)
        }
    }

    override fun getItemCount()= this.datasetArtiste.size
    }

