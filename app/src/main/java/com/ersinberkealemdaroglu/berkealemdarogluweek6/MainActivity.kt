package com.ersinberkealemdaroglu.berkealemdarogluweek6

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.strictmode.CredentialProtectedWhileLockedViolation
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ersinberkealemdaroglu.berkealemdarogluweek6.databinding.ActivityMainBinding
import com.ersinberkealemdaroglu.berkealemdarogluweek6.di.NetworkResponse
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var isFinish = MutableLiveData<Boolean>()
    private val scopeIO = CoroutineScope(Job() + Dispatchers.IO)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //infiniteLoop()
        coroutineScope()
    }

    private fun infiniteLoop(){
        while (true){
            for (i in 0 until Int.MAX_VALUE){
                binding.textView3.text = i.toString()
            }
        }
    }


    /**
     * Sunucudan gelen veriyi io dispatchers ile çağırdığımızda veriler main thread i bloklamadan,
     * arka planda yapılmaktadır. Bu sayede main Thread bloklanmadan verilerin gelmesini sağlayabiliriz.
     *
     * Lakin dispatcher io da bir sonsuz bir düngü kurarsak uygulama açılacaktır ama io nun görevi sonsuza kadar süreceği için main thread aktif olmayacaktır.
     *
     * sonsuz döngüyü coroutine dışına alırsak uygulama hiç açılmayacaktır. Kullanıcı Sürekli beyaz ekranda bekleyecektir. Kullanıcı sadece beyaz bir ekran görecektir.
     * Coroutine içine eklersek main de bulunan ui tasarımlarını kullanıcı görecektir ama etkileşime geçemeyecektir.
     */

    private fun coroutineScope() {
        CoroutineScope(Dispatchers.IO).launch {
            infiniteLoop()
            delay(2000)
            val answer = NetworkResponse().doNetworkCall()
            withContext(Dispatchers.Main) {
                binding.textView.text = answer
                binding.nextActivity.setOnClickListener {
                    val intent = Intent(this@MainActivity, DetailActivity::class.java)
                    startActivity(intent)
                }
            }
        }

    }

}