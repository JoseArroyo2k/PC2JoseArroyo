package com.example.pc2josearroyo

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class RevisionEquiposActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_revision_equipos)

        val listViewEquipos: ListView = findViewById(R.id.listViewEquipos)
        val btnVolverRegistro: Button = findViewById(R.id.btnVolverRegistro)


        val db = FirebaseFirestore.getInstance()
        val collectionRef = db.collection("equipos")


        val equiposList = mutableListOf<String>()


        collectionRef.get()
            .addOnSuccessListener { result ->
                for (document in result) {

                    val nombre = document.getString("nombre")
                    val año = document.getString("año")
                    val numTitulos = document.getString("numTitulos")
                    val url = document.getString("url")


                    nombre?.let {
                        equiposList.add("$it - Año: $año, Títulos: $numTitulos, URL: $url")
                    }
                }


                val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, equiposList)


                listViewEquipos.adapter = adapter
            }
            .addOnFailureListener { exception ->

                println("Error obteniendo documentos: $exception")
            }


        btnVolverRegistro.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()  // Opcional, dependiendo de si deseas cerrar la actividad actual
        }
    }
}