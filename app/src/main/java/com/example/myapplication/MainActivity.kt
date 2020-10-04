package com.example.myapplication

import android.annotation.TargetApi
import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.core.content.getSystemService
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.zapytanie.*
import kotlinx.android.synthetic.main.zapytanie.view.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val miesiace = arrayOf("Styczeń", "Luty", "Marzec", "Kwiecień", "Maj", "Czerwiec", "Lipiec", "Sierpień", "Lipiec", "Październik", "Listopad", "Grudzień")
        var mySummary = ""

        val now = Calendar.getInstance()

        var dzien = now.get(Calendar.DAY_OF_MONTH)
        var miesiac = now.get(Calendar.MONTH)
        var rok = now.get(Calendar.YEAR)


        var godzina = now.get(Calendar.HOUR)
        var minuta = now.get(Calendar.MINUTE)



        lateinit var alarmManager: AlarmManager
        lateinit var alarmIntent: PendingIntent

        alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

        alarmIntent = PendingIntent.getBroadcast(applicationContext,0,
            Intent(applicationContext,AlarmReceiver::class.java), 0)





        item1.setOnClickListener {
            val intentRead = Intent(this, Main2Activity::class.java)
            intentRead.putExtra(EXTRA_TITLE, mainActTitle.text.toString())
            intentRead.putExtra(EXTRA_SUMMARY, mySummary)
            intentRead.putExtra(EXTRA_HARI, mainActHari.text.toString())
            intentRead.putExtra(EXTRA_JAM, mainActJam.text.toString())

            startActivity(intentRead)

        }

        floating_action_button2.setOnClickListener {
            val reminderLayout = layoutInflater.inflate(R.layout.activity_main, null)



        }

        floating_action_button.setOnClickListener {
            val reminderLayout = layoutInflater.inflate(R.layout.zapytanie, null)
            val dialog = AlertDialog.Builder(this)

            dialog.setView(reminderLayout)
            val myreminderDialog = dialog.show()

            val myTitle = reminderLayout.findViewById<EditText>(R.id.title)
            val myCalendar = reminderLayout.findViewById<Button>(R.id.calendar)
            val myTime = reminderLayout.findViewById<Button>(R.id.alarm)

            myTitle.setText(mainActTitle.text)
            reminderLayout.findViewById<EditText>(R.id.summary).setText(mySummary)


         myCalendar.setText("    " + dzien.toString() + " " + miesiace[miesiac] + " " + rok)
            myCalendar.setOnClickListener { 
                val myDatePicker = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                    myCalendar.setText("        " + dayOfMonth.toString() + " " + month + " " + year)

                    dzien = dayOfMonth
                    miesiac = month
                    rok = year

                }, dzien, miesiac, rok)
                myDatePicker.show()
            }

            myTime.text = formatJam(godzina,minuta)
            myTime.setOnClickListener {
                val myTimePicker = TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                    myTime.text = formatJam(hourOfDay, minute)
                    godzina = hourOfDay
                    minuta = minute
                }, godzina, minuta, true)
                myTimePicker.show()
            }
            val save = reminderLayout.findViewById<Button>(R.id.save)
            save.setOnClickListener {
                if(myTitle.text.trim().isEmpty()){
                    reminderLayout.findViewById<TextView>(R.id.warningtitle).isVisible = true


                }
                else  {item1.isVisible = true
                    item1.setBackgroundColor(resources.getColor(R.color.classic_rose))
                    reminderLayout.findViewById<TextView>(R.id.warningtitle).isVisible = false
                    mainActTitle.text = myTitle.text
                    mySummary = reminderLayout.findViewById<EditText>(R.id.summary).text.toString()
                    mainActHari.text = myCalendar.text.removeRange(1, 5)
                    mainActJam.text = myTime.text.removeRange(1, 5)
                    Toast.makeText(this, "Zapisano", Toast.LENGTH_SHORT).show()



                    val date = java.util.Calendar.Builder()
                        .setDate(rok, miesiac, dzien)
                        .setTimeOfDay(godzina, minuta, 0)
                        .build()

                    alarmManager.set(AlarmManager.RTC_WAKEUP, date.timeInMillis ,alarmIntent)

                    myreminderDialog.dismiss()


                }

            }
            val cancel = reminderLayout.findViewById<Button>(R.id.cancel)
            cancel.setOnClickListener {
                myreminderDialog.dismiss()
            }

        }

        }




    fun formatJam(jam : Int, menit: Int) :String{
        var result:String = "     " + jam + "   :   " +  + menit
        if (jam < 10 && menit < 10) {result = "     0" + jam.toString() +  " : " + "0" + menit }
    else
    {
        if (jam < 10) result = "     0" + jam.toString() +  " : " + menit
        if (menit < 10) result = "     " + jam.toString() +  " : " + "0" + menit
    }

if(jam>12) result +=" PM"
        else result +=" AM"
    return result


    }

}