package com.example.myfinalapplication

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myfinalapplication.databinding.ActivityMemoBinding
import java.io.BufferedReader
import java.io.File
import java.io.OutputStreamWriter

class MemoActivity : AppCompatActivity() {
    lateinit var binding: ActivityMemoBinding
    var datas: MutableList<String>? = null
    lateinit var adapter: MyAdapter
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) { //MainActivity가 실행하면 자동으로 호출됨
        super.onCreate(savedInstanceState)
        binding= ActivityMemoBinding.inflate(layoutInflater)
        setContentView(binding.root) //매개변수에 지정한 내용을 엑티비티 화면에 출력함



        val requestlauncher : ActivityResultLauncher<Intent>
                = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            it.data!!.getStringExtra("result")?.let{
                datas?.add(it)
                adapter.notifyDataSetChanged()

                var db: SQLiteDatabase = DBHelper(this).writableDatabase
                // SQL : insert into todo_tbl (todo) values (it...)
                db.execSQL("insert into todo_tbl (todo) values (?)", arrayOf<String>(it))
                db.close()
            }
        }
        //Fab 누르면
        binding.mainFab.setOnClickListener {
            var intent = Intent(this, AddActivity2::class.java)
            intent.putExtra("data","오늘의 상태를 기록해주세요")
            requestlauncher.launch(intent)
        }
        /*
        datas = savedInstanceState?.let{
            it.getStringArrayList("datas")?.toMutableList()
        } ?: let{ mutableListOf<String>() }
        */
        // DB 읽기
        datas = mutableListOf<String>()
        val db: SQLiteDatabase = DBHelper(this).readableDatabase
        var cursor: Cursor = db.rawQuery("select * from todo_tbl",null)
        while(cursor.moveToNext()){
            datas?.add(cursor.getString(1))
        }
        db.close()

        val layoutManager = LinearLayoutManager(this)
        layoutManager.reverseLayout = true
        layoutManager.stackFromEnd = true
        binding.mainRecyclerView.layoutManager=layoutManager
        adapter=MyAdapter(datas)
        binding.mainRecyclerView.adapter=adapter
        binding.mainRecyclerView.addItemDecoration(
            DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
        )
    }

    //add...............................
    override fun onResume() { //화면이 다시 보여질 때마다
        super.onResume()
        adapter.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_memo, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val file: File = File(filesDir, "memo_list.txt")
        when(item.itemId){
            R.id.menu_save_file -> {
                val writeStream: OutputStreamWriter = file.writer()
                writeStream.write("Today memo\n")
                for(i in datas!!.indices)
                    writeStream.write(datas!![i] + "\n")
                writeStream.flush()
                return true
            }
            R.id.menu_read_file -> {
                val readStream: BufferedReader = file.reader().buffered() // InputStreamReader
                readStream.forEachLine { //한 줄씩 실행
                    Log.d("mobileApp","$it")
                }
                return true
            }
        }
        return false
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState.putStringArrayList("datas",ArrayList(datas))
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    }
}