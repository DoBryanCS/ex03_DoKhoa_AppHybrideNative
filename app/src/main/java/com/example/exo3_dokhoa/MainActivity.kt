package com.example.exo3_dokhoa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley

data class Artiste(val nom:String)

class MainActivity : AppCompatActivity() {

    private var datasetArtiste : MutableList<Artiste> = mutableListOf()

    private lateinit var artisteRV: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.artisteRV = findViewById<RecyclerView>(R.id.rvArtiste)

        this.artisteRV.layoutManager = LinearLayoutManager(this)

        this.artisteRV.adapter = rvArtisteAdapter(this.datasetArtiste)

        getData()

    }

    private fun getData() {
        val url = "https://musicstoreapi.herokuapp.com/artistes"

        val queue = Volley.newRequestQueue(this@MainActivity)

        val request =
            JsonArrayRequest(Request.Method.GET, url, null, { response ->
                try {
                    for (i in 0 until response.length()) {

                        val respObj = response.getJSONObject(i)

                        val artisteNom = respObj.getString("nom")

                        datasetArtiste.add(Artiste(artisteNom))
                        artisteRV.adapter!!.notifyDataSetChanged()


                    }

                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }, { error ->
                Toast.makeText(this@MainActivity, "Fail to get response", Toast.LENGTH_SHORT)
                    .show()
            }
            )
        queue.add(request)

    }
}