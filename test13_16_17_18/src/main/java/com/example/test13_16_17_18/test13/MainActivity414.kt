package com.example.test13_16_17_18.test13

import android.content.Intent
import android.net.Uri
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import com.example.test13_16_17_18.R
import com.example.test13_16_17_18.databinding.ActivityMain414Binding

//경로
// test13/src/main/java/com/example/test13/MainActivity414.kt
class MainActivity414 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMain414Binding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d("life","onCreate() 호출")

        //ACTION_EDIT : 시스템이 사용하는 문자열 이 아님.
        binding.button1.setOnClickListener {
            val intent = Intent()
//            intent.action = "ACTION_EDIT"
            intent.action = Intent.ACTION_VIEW
            intent.data = Uri.parse("http://www.google.com")
            startActivity(intent)
        }

        // 액션 문자열 : 시스템에서 제공하는 Intent.ACTION_VIEW
        // 웹주소, 웹브라우저 연결, 위도, 경도 값이면, 지도로 알아서 연결 합니다.
        binding.button2.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:37.7749,127.4194"))
            intent.setPackage("com.google.android.apps.maps")
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("life","onStart() 호출")
    }

    override fun onResume() {
        super.onResume()
        Log.d("life","onResume() 호출")
    }

    override fun onPause() {
        super.onPause()
        Log.d("life","onPause() 호출")
    }

    override fun onStop() {
        super.onStop()
        Log.d("life","onStop() 호출")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("life","onRestart() 호출")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("life","onDestroy() 호출")
    }


}