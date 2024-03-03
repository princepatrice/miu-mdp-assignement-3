package com.example.waterressource

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.EditText
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    private lateinit var nameEditText: EditText
    private lateinit var locationEditText: EditText
    private lateinit var typeEditText: EditText
    private lateinit var addRessourceButton: Button
    private lateinit var textViewError: TextView

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout)
        nameEditText = findViewById(R.id.nameEditText)
        locationEditText = findViewById(R.id.locationsEditText)
        typeEditText = findViewById(R.id.typeEditText)
        addRessourceButton = findViewById(R.id.addRessourceButton)
        textViewError = findViewById(R.id.textViewError)

        val tableLayout: TableLayout = findViewById(R.id.tableLayout)

        addHeader(tableLayout,"Water Ressource Name", "Location","Type")
        //adding sample data
        addRow(tableLayout,"Rivera Ganges","Varanasi, India","River")
        addRow(tableLayout,"Lake Michigan","Chicago, Illinois","Lake")
       // Remove spacing between rows
        tableLayout.showDividers = TableLayout.SHOW_DIVIDER_NONE
        addRessourceButton.setOnClickListener {
            val newLocation = locationEditText.text.toString()
            val newType = typeEditText.text.toString()
            val newName = nameEditText.text.toString()
            addRow(tableLayout,newName,newType,newLocation)

        }
    }

    private fun createTextView(text: String): TextView {
        val textView = TextView(this)
        textView.text = text
        textView.gravity = Gravity.START or Gravity.CENTER_VERTICAL // Paginate from the left
        return textView
    }
    private fun createHeaderTextView(text: String): TextView {
        val textView = TextView(this)
        textView.text = text
        textView.gravity = Gravity.CENTER
        textView.setTypeface(null, Typeface.BOLD)
        return textView
    }

    private fun addRow(tableLayout:TableLayout,newName:String,newLocation:String,newType:String) {
         if(newType.isNotEmpty() && newName.isNotEmpty() && newLocation.isNotEmpty()){
             //   ressourceList.add(WaterRessource(newType,newName, newLocation))
             val row = TableRow(this)
             // row.setPadding(16, 16, 16, 16)
             val nameTextView = createTextView(newName)
             val typeTextView = createTextView(newType)
             val locationTextView = createTextView(newLocation)

             nameTextView.setBackgroundResource(R.drawable.bordered_cell_background)
             typeTextView.setBackgroundResource(R.drawable.bordered_cell_background)
             locationTextView.setBackgroundResource(R.drawable.bordered_cell_background)


             row.addView(nameTextView)
             row.addView(locationTextView)
             row.addView(typeTextView)

             // Add the row to the table layout
             tableLayout.addView(row)

             locationEditText.setText("")
             nameEditText.setText("")
             typeEditText.setText("")
             textViewError.visibility = TextView.GONE
         }else{
             textViewError.visibility = TextView.VISIBLE
         }
    }
    private fun addHeader(tableLayout:TableLayout,newName:String,newLocation:String,newType:String) {
        if(newType.isNotEmpty() && newName.isNotEmpty() && newLocation.isNotEmpty()){
            val row = TableRow(this)
            val nameTextView = createHeaderTextView(newName)
            val typeTextView = createHeaderTextView(newType)
            val locationTextView = createHeaderTextView(newLocation)

            nameTextView.setBackgroundResource(R.drawable.bordered_cell_background)
            typeTextView.setBackgroundResource(R.drawable.bordered_cell_background)
            locationTextView.setBackgroundResource(R.drawable.bordered_cell_background)

            row.addView(nameTextView)
            row.addView(locationTextView)
            row.addView(typeTextView)

            // Add the row to the table layout
            tableLayout.addView(row)

        }
    }
}
