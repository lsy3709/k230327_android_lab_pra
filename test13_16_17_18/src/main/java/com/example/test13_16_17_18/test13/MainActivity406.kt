package com.example.test13_16_17_18.test13

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.test13_16_17_18.R
import com.example.test13_16_17_18.databinding.ActivityMain406Binding

//경로
// test13/src/main/java/com/example/test13/MainActivity406.kt
// 뷰 설정하고, 실행 후 코드 리류 할 예정.
//

class MainActivity406 : AppCompatActivity() {
    lateinit var binding:ActivityMain406Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain406Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button1.setOnClickListener {
            val intent: Intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("data1", "hello")
            intent.putExtra("data2", 10)
//             후처리 함수. 요청 코드 : 10, 2번 디테일 액티비티에서 데이터를 가지고 올 경우
            // 요청 코드를 이용해 비교함.
            startActivityForResult(intent, 10)
        }
    }

    // 후처리, 즉, 디테일 화면에서 -> 원래 화면으로 데이터를 가지고 올경우, 자동 호출 함수.
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 10 && resultCode == Activity.RESULT_OK) {
            val result = data?.getStringExtra("resultData")
            Toast.makeText(this@MainActivity406,"후처리 결괏값 확인: $result",Toast.LENGTH_SHORT).show()
            binding.resultView.text = result
        }
    }
}