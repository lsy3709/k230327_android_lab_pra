package com.example.test10_11_12

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.test10_11_12.databinding.ActivityJetBinding

class JetActivity : AppCompatActivity() {

    lateinit var binding: ActivityJetBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJetBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 액션바(테마사용)-> 툴바로 대체 할 예정 기초
        // 메뉴 구성방법 1) 코드로 2) xml 구성하는 방식(현재 xml로 작업중. )
        //경로
        //test11/src/main/java/com/example/test11/MainActivity328.kt
        //뷰 경로
        //test11/src/main/res/menu/menu_328.xml

    }
}