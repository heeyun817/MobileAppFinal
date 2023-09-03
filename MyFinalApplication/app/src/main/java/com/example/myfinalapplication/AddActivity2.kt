package com.example.myfinalapplication

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.RadioButton
import androidx.activity.result.contract.ActivityResultContracts
import com.example.myfinalapplication.databinding.ActivityAdd2Binding
import java.text.SimpleDateFormat
import java.util.*

class AddActivity2 : AppCompatActivity() {
    lateinit var binding: ActivityAdd2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdd2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val mystr = intent.getStringExtra("data")
        binding.myData.text = mystr

        binding.radioButton1.isChecked = true

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_add, menu)
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_add_save) {
            val memoText = binding.addEditView.text.toString()
            val currentDate = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
            val memoWithDateTime = "$memoText \n $currentDate"

            val selectedNumber = binding.radioButtonGroup.checkedRadioButtonId
            val radioButton: RadioButton = findViewById(selectedNumber)
            val selectedText = radioButton.text.toString()

            val memoWithNumber = "$memoWithDateTime \n 오늘의 점수 : $selectedText"

            val resultIntent = Intent()
            resultIntent.putExtra("result", memoWithNumber)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
            return true
        }


        return false
    }
}