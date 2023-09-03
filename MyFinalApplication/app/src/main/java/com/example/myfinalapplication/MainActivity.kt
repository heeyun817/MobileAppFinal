package com.example.myfinalapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myfinalapplication.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    var authMenuItem: MenuItem? = null
    class MyFragmentAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity){ //Adapter : 여러개의 fragment를 관리
        val fragments : List<Fragment>
        init{
            fragments = listOf(OneFragment(), TwoFragment(), RetrofitFragment())
        }
        override fun getItemCount(): Int { //항목의 개수를 구함
            return fragments.size
        }

        override fun createFragment(position: Int): Fragment { //프래그먼트 객체를 얻음
            return fragments[position] //반환하는 프래그먼트 객체가 각 항목에 출력됨
        }
    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)

        authMenuItem = menu!!.findItem(R.id.menu_auth)
        if(MyApplication.checkAuth()){
            authMenuItem!!.title = "${MyApplication.email}님"
        }else{
            authMenuItem!!.title = "인증"
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onStart(){
        // Intent에서 finish() 돌아올 때 실행
        // onCreate -> onStart -> onCreateOptionsMenu
        super.onStart()
        if(authMenuItem != null){
            if(MyApplication.checkAuth()){
                authMenuItem!!.title = "${MyApplication.email}님"
            }else{
                authMenuItem!!.title = "인증"
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //뷰페이저에 어댑터 적용(^2)
        binding.viewpager2.adapter = MyFragmentAdapter(this)

        //탭 레이아웃과 뷰 페이저 연동
        TabLayoutMediator(binding.tabs, binding.viewpager2){ //뷰 페이저의 화면이 3개이면 탭 버튼도 자동으로 3개 나옴
                tab, position ->
            val tabTitles = arrayOf("홈", "게시판", "화장품 검색")
            tab.text = tabTitles[position]
        }.attach()


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId === R.id.menu_auth){
            val intent = Intent(this,AuthActivity::class.java)
            if(authMenuItem!!.title!!.equals("인증")){
                intent.putExtra("data","logout")
            }else{
                intent.putExtra("data","login")
            }
            startActivity(intent)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }
        else if(item.itemId === R.id.menu_main_setting){
            val intent = Intent(this, SettingActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }

        return super.onOptionsItemSelected(item)
    }

}
