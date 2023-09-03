package com.example.myfinalapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.replace
import com.example.myfinalapplication.databinding.ActivityChartBinding

class ChartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_chart)
        val binding = ActivityChartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val lineFragment = LineFragment()
        val radarFragment = RadarFragment()

        supportFragmentManager.beginTransaction()
            .replace(R.id.container,lineFragment)
            .commit()

        binding.bottomNavigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.tab1 -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container,lineFragment)
                        .commit()
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.tab2 -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container,radarFragment)
                        .commit()
                    return@setOnNavigationItemSelectedListener true
                }
            }
            return@setOnNavigationItemSelectedListener false
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    }

}