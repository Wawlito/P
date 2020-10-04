package com.example.myapplication

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.zapytanie.*

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val intent_GetRequest = intent
        val myTitle = intent_GetRequest.getStringExtra(EXTRA_TITLE)
        val mySummary = intent_GetRequest.getStringExtra(EXTRA_SUMMARY)
        val myHari = intent_GetRequest.getStringExtra(EXTRA_HARI)
        val myJam = intent_GetRequest.getStringExtra(EXTRA_JAM)


        act2Title.text = myTitle
        act2Summary.text = mySummary
        act2Hari.text ="     " + myHari
        act2Jam.text = "      " + myJam




    }}
