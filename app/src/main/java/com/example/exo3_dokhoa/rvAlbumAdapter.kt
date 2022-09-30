package com.example.exo3_dokhoa

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class rvAlbumAdapter(private val datasetAlbum: MutableList<Album>) :
RecyclerView.Adapter<rvAlbumAdapter.RecyclerViewViewHolder>() {

    class RecyclerViewViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.album_items, parent, false) as View
        return RecyclerViewViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewViewHolder, position: Int) {
        holder.view.findViewById<TextView>(R.id.titre).text = this.datasetAlbum[position].titre
        holder.view.findViewById<TextView>(R.id.cout).text = this.datasetAlbum[position].cout
        holder.view.findViewById<TextView>(R.id.date).text = this.datasetAlbum[position].anneeParution
        val imageView = holder.view.findViewById<ImageView>(R.id.image)
        Picasso.get().load(this.datasetAlbum[position].image).into(imageView)
    }

    override fun getItemCount() = this.datasetAlbum.size
}

