package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_TEXT = "text_to_display"
    }

    class AgeCalculator(private val anneeNaissance: Int) {
        private val anneeActu = 2025

        fun calculerAge(): Int {
            return anneeActu - anneeNaissance
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val textView: TextView = findViewById(R.id.BVN)
        val AfficherNom: TextView = findViewById(R.id.AfficheNom2)
        val BoutonValider: Button = findViewById(R.id.ValiderBTN)
        val SaisieNom: EditText = findViewById(R.id.SaisirNom)
        val SaisieAnnee: EditText = findViewById(R.id.AnneeNaissance)

        SaisieNom.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                AfficherNom.text = s.toString()
            }
        })

        BoutonValider.setOnClickListener {
            val nom = SaisieNom.text.toString()
            val anneeNaissance = SaisieAnnee.text.toString().toIntOrNull()

            if (anneeNaissance != null) {
                val ageCalculator = AgeCalculator(anneeNaissance)
                val age = ageCalculator.calculerAge()

                val intent = Intent(this@MainActivity, MainActivity2::class.java)
                intent.putExtra(EXTRA_TEXT, "Hello $nom vous avez $age ans.")
                startActivity(intent)
            }
        }
    }
}
