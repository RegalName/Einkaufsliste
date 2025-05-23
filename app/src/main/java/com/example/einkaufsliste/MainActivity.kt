package com.example.einkaufsliste

import android.os.Bundle
import android.text.InputType
import android.widget.Adapter
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.einkaufsliste.databinding.ActivityMainBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var lvListe: ListView
    private lateinit var fab: FloatingActionButton //fügt Einkäufe hinzu
    private lateinit var einkaeufe: ArrayList<String> //durch User erstellte Einkäufe
    private lateinit var itemAdapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lvListe = findViewById(R.id.lvListe)
        fab = findViewById(R.id.fabAddElement)
        einkaeufe = ArrayList()

        itemAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, einkaeufe)
        lvListe.adapter = itemAdapter

        // Einträge löschen
        lvListe.setOnItemLongClickListener({arg0, arg1, pos, id ->
            //builder für Löschbestätigung
            var builder = AlertDialog.Builder(this)
            builder.setTitle("Eintrag löschen?")
            builder.setMessage("Dann ist er weg!")

            builder.setPositiveButton("OK") { dialog, which ->
                //löscht Einträge
                einkaeufe.removeAt(pos)
                itemAdapter.notifyDataSetChanged()
                Toast.makeText(applicationContext, "Eintrag gelöscht", Toast.LENGTH_SHORT).show()
            }

            builder.setNegativeButton("Abbrechen") { dialog, which ->}

            builder.show()

            true
        })

        //Einkäufe über den FLoatingActionButton zur Liste hinzufügen
        fab.setOnClickListener {
            var builder = AlertDialog.Builder(this)
            builder.setTitle("Hinzufügen")

            var input = EditText(this)
            input.hint = "Einkauf eingeben"
            input.inputType = InputType.TYPE_CLASS_TEXT
            builder.setView(input)

            builder.setPositiveButton("OK") { dialog, which ->
                einkaeufe.add(input.text.toString())
            }

            builder.setNegativeButton("Abbrechen") { dialog, which ->
                Toast.makeText(applicationContext, "Abgebrochen", Toast.LENGTH_SHORT).show()

            }

            builder.show()
        }

    }

}