package com.example.test13_16_17_18.test18

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.test13_16_17_18.R
import com.example.test13_16_17_18.databinding.ActivityMainBinding
import com.example.test13_16_17_18.test13.DetailActivity

//경로 , 뷰 부분은 layout 참고하기.
//test13/src/main/java/com/example/test13/MainActivity.kt
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button1.setOnClickListener {
            val intent: Intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("data1", "hello")
            intent.putExtra("data2", 10)
            startActivity(intent)
        }

    }
}