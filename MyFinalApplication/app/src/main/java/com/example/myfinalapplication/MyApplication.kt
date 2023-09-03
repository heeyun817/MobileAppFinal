package com.example.myfinalapplication

import android.app.Application
import androidx.multidex.MultiDexApplication
import com.bumptech.glide.Glide.init
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class MyApplication: MultiDexApplication() { //Application() : MainActivity가 표시되기 전에 전역 상태를 초기화하는 데 사용

    companion object {
        lateinit var db : FirebaseFirestore
        lateinit var storage : FirebaseStorage

        lateinit var auth : FirebaseAuth
        var email:String? = null
        fun checkAuth(): Boolean{
            var currentuser = auth.currentUser
            return currentuser?.let{
                email = currentuser.email
                if(currentuser.isEmailVerified) true
                else false
            } ?: false
        }

        var networkService : NetworkService
        val retrofit: Retrofit
            get() = Retrofit.Builder()
                .baseUrl("http://apis.data.go.kr/1471057/FcssJdgmnPrdlstInforService02/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        init {
            networkService = retrofit.create(NetworkService::class.java) // 초기화
        }
    }

    override fun onCreate() {
        super.onCreate()
        auth = Firebase.auth

        db = FirebaseFirestore.getInstance()
        storage = Firebase.storage
    }
}

