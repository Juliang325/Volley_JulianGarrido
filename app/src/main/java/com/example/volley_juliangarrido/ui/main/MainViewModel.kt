package com.example.volley_juliangarrido.ui.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.volley_juliangarrido.model.entities.Personaje

class MainViewModel(application: Application) :  AndroidViewModel(application) {
    val url = "https://rickandmortyapi.com/api/character"
    private val cola: RequestQueue = Volley.newRequestQueue(application)

    val _personajes: MutableLiveData<List<Personaje>> = MutableLiveData()
    val personajes: LiveData<List<Personaje>> = _personajes

    fun getPersonajes() {
        val solicitud = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                val documents = response.getJSONArray("data")
                val listaPersonajes = mutableListOf<Personaje>()
                for (i in 0 until documents.length()) {
                    val item = documents.getJSONObject(i)
                    listaPersonajes.add(
                        Personaje(
                            item.getString("displayName"),
                            item.getString("displaySpecies"),
                            item.getString("displayImagen"),
                        )
                    )
                    Log.d("Firestore", item.toString())
                }

                _personajes.value = listaPersonajes
            },
            { error ->
                Log.e("Firestore", "Error: $error")
            })
        cola.add(solicitud)
    }

}