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
    private lateinit var fab: FloatingActionButton
    private lateinit var einkaeufe: ArrayList<String>
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

        lvListe.setOnItemLongClickListener({arg0, arg1, pos, id ->
            einkaeufe.removeAt(pos)
            itemAdapter.notifyDataSetChanged()
            Toast.makeText(applicationContext, "Einkauf gelöscht", Toast.LENGTH_SHORT).show()
            true
        })

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