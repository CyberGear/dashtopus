package com.example.octodashboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import com.example.octodashboard.Utils.avoidDropdownFocus
import com.example.octodashboard.Utils.enterImmersiveFullScreen
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import kotlinx.android.synthetic.main.layout_temperature.*
import kotlin.random.Random

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class FullscreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fullscreen)
        enterImmersiveFullScreen()

        //-----
        spinnerPreset.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, listOf("--", "PETG", "PLA", "PLA+"))
        spinnerPreset.avoidDropdownFocus()

        spinnerNozzle.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, listOf("-- °C", "240 °C", "215 °C", "225 °C", "Custom"))
        spinnerNozzle.avoidDropdownFocus()

        spinnerBed.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, listOf("-- °C", "65 °C", "60 °C", "60 °C", "Custom"))
        spinnerBed.avoidDropdownFocus()

        //-----
        val rnd = Random

        val data1 = List(100) { 38 + rnd.nextInt(2) }.withIndex().map { Entry(it.index.toFloat(), it.value.toFloat()) }
        val data2 = List(100) { 240 + rnd.nextInt(2) }.withIndex().map { Entry(it.index.toFloat(), it.value.toFloat()) }

        val elements = LineDataSet(data1, "Bed")
        elements.setDrawCircles(false)
        elements.setDrawValues(false)
        val elements1 = LineDataSet(data2, "Nozzle")
        elements1.setDrawCircles(false)
        elements1.setDrawValues(false)
        chart.data = LineData(listOf(elements, elements1))
        chart.invalidate()

        //-----
    }

}