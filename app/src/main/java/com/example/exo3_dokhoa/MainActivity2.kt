package com.example.exo3_dokhoa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley

data class Album (
    val titre : String,
    val cout : String,
    val anneeParution: String,
    val image: String,
)

class MainActivity2 : AppCompatActivity() {

    private var datasetAlbum : MutableList<Album> = mutableListOf()

    private lateinit var albumRV: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        this.albumRV = findViewById<RecyclerView>(R.id.rvAlbum)


        this.albumRV.layoutManager = LinearLayoutManager(this)

        this.albumRV.adapter = rvAlbumAdapter(this.datasetAlbum)

        getData()

    }

    private fun getData() {

        val url = "https://musicstoreapi.herokuapp.com/albums/"

        val queue = Volley.newRequestQueue(this@MainActivity2)

        val request =
            JsonArrayRequest(Request.Method.GET, url, null, { response ->
                try {
                    for (i in 0 until response.length()) {
                        val respObj = response.getJSONObject(i)
                        println(i)

                            val artiste = respObj.getJSONObject("artiste")
                            val nom = artiste.getString("nom")
                            val nomArtiste = this.intent.getStringExtra("artisteNom")

                            if(nomArtiste == nom) {
                                val titre = respObj.getString("titre")
                                val cout = respObj.getString("prix")
                                val anneeParution = respObj.getString("anneeParution")
                                val image = respObj.getString("cover")


                                datasetAlbum.add(Album(titre, cout, anneeParution, image))
                                albumRV.adapter!!.notifyDataSetChanged()
                            }

                    }

                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }, { error ->
                Toast.makeText(this@MainActivity2, "Fail to get response", Toast.LENGTH_SHORT)
                    .show()
            })
        queue.add(request)


    }
}