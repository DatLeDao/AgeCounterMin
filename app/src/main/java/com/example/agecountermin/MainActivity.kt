package com.example.agecountermin

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnDate.setOnClickListener{
            selectDate()
        }
    }

    private fun selectDate() {
        Toast.makeText(this, "clicked OK", Toast.LENGTH_SHORT).show()
        val cal=Calendar.getInstance()
        Log.wtf("a",cal.toString())
        val year=cal.get(Calendar.YEAR)
        val month=cal.get(Calendar.MONTH)
        val day=cal.get(Calendar.DAY_OF_MONTH)
        Log.wtf("b","$day/${month+1}/$year")

        val dp = DatePickerDialog(this,DatePickerDialog.OnDateSetListener{
                view,sYear,sMonth,sDayOfMonth ->
            Toast.makeText(this,"$sDayOfMonth/${sMonth+1}/$sYear",Toast.LENGTH_SHORT).show()

            val dateBith = "$sDayOfMonth/${sMonth+1}/$sYear"
            txtDateSelected.text = dateBith

            val sdf = SimpleDateFormat("dd/MM/yy", Locale.US)
            val ngaySinh =sdf.parse(dateBith)
            ngaySinh?.let {
                val ngaySinhTheoPhut = ngaySinh.time / 60000

                val currDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                currDate?.let {
                    val currDateInMinute = currDate.time / 60000

                    val diff = currDateInMinute - ngaySinhTheoPhut
                    txtAgeInMinute.text = diff.toString()
                }
            }
        },year,month,day)

        dp.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dp.show()
    }
}