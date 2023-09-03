package com.example.myfinalapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.myfinalapplication.databinding.ActivityAuthBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthCredential
import com.google.firebase.auth.GoogleAuthProvider

class AuthActivity : AppCompatActivity() {
    lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        changeVisibility(intent.getStringExtra("data").toString())

        binding.textViewsignUp.setOnClickListener{
            // 회원가입
            changeVisibility("signin")
        }

        binding.signBtn.setOnClickListener {
            //이메일,비밀번호 회원가입........................
            val email:String = binding.authEmailEditView.text.toString()
            val password:String = binding.authPasswordEditView.text.toString()
            MyApplication.auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this){task ->
                    if(task.isSuccessful){
                        MyApplication.auth.currentUser?.sendEmailVerification()?.addOnCompleteListener {
                            sendTask ->
                                if(sendTask.isSuccessful){
                                    Toast.makeText(baseContext, "회원가입 성공..이메일 확인",Toast.LENGTH_LONG).show()
                                    changeVisibility("logout")
                                }
                                else{
                                    Toast.makeText(baseContext, "메일 전송 실패...",Toast.LENGTH_LONG).show()
                                    changeVisibility("logout")
                                }
                        }
                    }
                    else{
                        Toast.makeText(baseContext,"회원가입 실패", Toast.LENGTH_LONG).show()
                        changeVisibility("logout")
                    }
                    binding.authEmailEditView.text.clear()
                    binding.authPasswordEditView.text.clear()
                }
        }

        binding.buttonLogin.setOnClickListener {
            //이메일, 비밀번호 로그인.......................
            val email:String = binding.authEmailEditView.text.toString()
            val password:String = binding.authPasswordEditView.text.toString()
            MyApplication.auth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this){task ->
                    if(task.isSuccessful){
                        if(MyApplication.checkAuth()){
                            MyApplication.email = email
                            //changeVisibility("login")
                            finish()
                        }
                        else{
                            Toast.makeText(baseContext, "이메일 인증에 실패했습니다",Toast.LENGTH_LONG).show()
                        }
                    }
                    else{
                        Toast.makeText(baseContext, "로그인 실패...", Toast.LENGTH_LONG).show()
                        changeVisibility("logout")
                    }
                    binding.authEmailEditView.text.clear()
                    binding.authPasswordEditView.text.clear()
                }
        }

        binding.logoutBtn.setOnClickListener {
            //로그아웃...........
            MyApplication.auth.signOut()
            MyApplication.email = null
            //changeVisibility("logout")
            finish()
        }

        val requestLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ //구글 로그인 결과를 it로 받음
            val task = GoogleSignIn.getSignedInAccountFromIntent(it.data) //로그인 정보 받음
            // ApiException : Google Play 서비스 호출이 실패했을 때 태스크에서 반환할 예외
            try{
                val account = task.getResult(ApiException::class.java) //account정보 받음
                val credential = GoogleAuthProvider.getCredential(account.idToken, null) //account정보를 credential을 받는 방식으로 인증이 되었는가를 확인 -> 한번 로그인하면 로그아웃안해도 계속 유지됨
                MyApplication.auth.signInWithCredential(credential)
                    .addOnCompleteListener(this){task -> //인증된 결과를 task로 전달 받음
                        if(task.isSuccessful){ //인증이 성공적이라면
                            MyApplication.email = account.email
                            //changeVisibility("login")
                            Log.d("mobileApp","GoogleSignIn - Successful")
                            finish()
                        }
                        else{
                            changeVisibility("logout")
                            Log.d("mobileApp","GoogleSignIn - NOT Successful")
                        }
                    }
            }catch (e: ApiException){
                changeVisibility("logout")
                Log.d("mobileApp","GoogleSignIn - ${e.message}")
            }
        }
        binding.googlelogin.setOnClickListener {
            //구글 로그인....................
            val gso : GoogleSignInOptions = GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build() //이러한 형태를 유지하는 로그인 창을 만들겠다
            val signInIntent : Intent = GoogleSignIn.getClient(this, gso).signInIntent
            requestLauncher.launch(signInIntent)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    }

    fun changeVisibility(mode: String){
        if(mode.equals("signin")){
            binding.run {
                logoutBtn.visibility = View.GONE
                textViewsignUp.visibility = View.GONE
                googlelogin.visibility = View.GONE
                authEmailEditView.visibility = View.VISIBLE
                authPasswordEditView.visibility = View.VISIBLE
                signBtn.visibility = View.VISIBLE
                buttonLogin.visibility = View.GONE
                textViewsns.visibility = View.GONE
            }
        }else if(mode.equals("login")){
            binding.run {
                authMainTextView.visibility = View.VISIBLE
                authMainTextView.text = "${MyApplication.email} 님 로그아웃 하시겠어요?"
                logoutBtn.visibility= View.VISIBLE
                textViewsignUp.visibility= View.GONE
                googlelogin.visibility= View.GONE
                authEmailEditView.visibility= View.GONE
                authPasswordEditView.visibility= View.GONE
                signBtn.visibility= View.GONE
                buttonLogin.visibility= View.GONE
                textViewsns.visibility = View.GONE
            }

        }else if(mode.equals("logout")){
            binding.run {
                authMainTextView.visibility = View.GONE
                logoutBtn.visibility = View.GONE
                textViewsignUp.visibility = View.VISIBLE
                textViewsns.visibility = View.VISIBLE
                googlelogin.visibility = View.VISIBLE
                authEmailEditView.visibility = View.VISIBLE
                authPasswordEditView.visibility = View.VISIBLE
                signBtn.visibility = View.GONE
                buttonLogin.visibility = View.VISIBLE
            }
        }
    }
}