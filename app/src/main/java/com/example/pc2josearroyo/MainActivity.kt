package com.example.pc2josearroyo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    data class EquipoModel(
        val nombre: String,
        val año: String,
        val numTitulos: String,
        val url: String
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val db = FirebaseFirestore.getInstance()

        val txtNombre: EditText = findViewById(R.id.txtNombr)
        val txtAño: EditText = findViewById(R.id.txtAño)
        val txtNumTit: EditText = findViewById(R.id.txtNumeroTit)
        val txtUrl: EditText = findViewById(R.id.txtUrl)
        val btnRegist: Button = findViewById(R.id.btnGuardar)
        val collectionRef = db.collection("equipos")

        btnRegist.setOnClickListener {
            val nombre = txtNombre.text.toString()
            val año = txtAño.text.toString()
            val numTit = txtNumTit.text.toString()
            val url = txtUrl.text.toString()


            val equipoModel = EquipoModel(nombre, año, numTit, url)


            collectionRef.add(equipoModel)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Snackbar.make(
                            findViewById(android.R.id.content),
                            "Registro exitoso del equipo",
                            Snackbar.LENGTH_LONG
                        ).show()
                        val intent = Intent(this, RevisionEquiposActivity::class.java)
                        startActivity(intent)
                        finish()

                    } else {
                        Snackbar.make(
                            findViewById(android.R.id.content),
                            "Ocurrió un error al registrar el equipo",
                            Snackbar.LENGTH_LONG
                        ).show()
                    }
                }


        }
    }
}
