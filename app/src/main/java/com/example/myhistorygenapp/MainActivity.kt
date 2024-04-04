package com.example.myhistorygenapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Contacts.People
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    // List of Historical People along with the age
    enum class HistoricalPeople (val age: Int, val description: String) {

        People_1(20, "You are round about the same age as Cameron Boyce"),
        People_2(22, "You are round about the same age as Aaliyah Haughton"),
        People_3(36, "You are round about the same age as Princess Diana"),
        People_4(39, "You are round about the same age as Martin Luther King Jr."),
        People_5(50, "You are round about the same age as Michael Jackson"),
        People_6(56, "You are round about the same age as Adolf Hitler"),
        People_7(68, "You are round about the same age as James Avery"),
        People_8(70, "You are round about the same age as Nina Simone"),
        People_9(86, "You are round about the same age as Queen Elizabeth II"),
        People_10(90, "You are round about the same age as Fidel Castro")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        //Get the components using the Id on the History Generator
        val btnResult = findViewById<Button>(R.id.btnResult)
        val btnClear = findViewById<Button>(R.id.btnClear)
        val edtAge = findViewById<EditText>(R.id.edtAge)
        val txtResult = findViewById<TextView>(R.id.txtResult)

        //If user presses the Generate History button
        btnResult?.setOnClickListener()
        {
            val Age = edtAge.text.toString().toInt()

            if (Age != null && Age in 20..100) {
                // Input the values of the ages in the list
                val age = HistoricalPeople.values().map { it.age }

                val historicalPeople = when (Age) {
                    // This statement will run if the Age matches exactly the year of Historical People death
                    in age -> {
                        val age = HistoricalPeople.values().find { it.age == Age }
                        listOf("${age?.description ?: "people"}")
                    }
                    //Map function will transform each enum constant into its corresponding year
                    //This statement wil run if the Age is three years before the Historical death age
                    in age.map { it - 3 } -> {
                        val age = HistoricalPeople.values().find { it.age == Age + 3 }
                        listOf(
                            "Your birth age is three years before the death age of Historian " +
                                    "${age?.description ?:"people"}"
                        )
                    }
                    // This statement will be run if the birth year is three years after the Historical death age
                    in age.map { it + 3 } -> {
                        val age = HistoricalPeople.values().find { it.age == Age - 3 }
                        listOf(
                            "Your birth year is three years after the death of Historian " +
                                    "${age?. description ?:"people"}"
                        )
                    }

                    else -> listOf("No Historical people found for $Age")
                }
                txtResult.text = historicalPeople.joinToString("\n")

            } else {
                txtResult.text = "No Historical People have been found from the input of your age"
            }
            //If user presses the Clear button
            btnClear?.setOnClickListener() {
                edtAge.text.clear()
                txtResult.text = ""
            }
        }
    }}






