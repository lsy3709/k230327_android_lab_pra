package com.example.test10_11_12.test11

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.test10_11_12.R
import com.example.test10_11_12.databinding.ActivityJetBinding

class JetActivity : AppCompatActivity() {

    lateinit var binding: ActivityJetBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJetBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //툴바를 직접 만들엇, 뷰에 붙이는 작업.
        // 뷰, 뒷단 코드 가져오기
        // 경로
        //test11/src/main/java/com/example/test11/MainActivity330.kt
        // 뷰
        //test11/src/main/res/layout/activity_main330.xml
        setSupportActionBar(binding.toolbar)

    }

    // 액션바(테마사용)-> 툴바로 대체 할 예정 기초
    // 메뉴 구성방법 1) 코드로 2) xml 구성하는 방식(현재 xml로 작업중. )
    //경로
    //test11/src/main/java/com/example/test11/MainActivity328.kt
    //뷰 경로
    //test11/src/main/res/menu/menu_328.xml : 참고 했지만, 파일 직접 만들어서 이름 확인.
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // 화면 출력하기 위한 객체 생성.
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.menu1 -> {
            Log.d("kkang", "menu1 click")
Toast.makeText(this@JetActivity,"menu1 클릭됨.",Toast.LENGTH_SHORT).show()

            true
        }
        R.id.menu2 -> {
Toast.makeText(this@JetActivity,"menu2 클릭됨.",Toast.LENGTH_SHORT).show()
            Log.d("kkang", "menu2 click")
            true
        }
        R.id.menu3 -> {
            Toast.makeText(this@JetActivity,"menu3 클릭됨.",Toast.LENGTH_SHORT).show()
            Log.d("kkang", "menu3 click")
            true
        }
        R.id.menu22 -> {
            Toast.makeText(this@JetActivity,"menu22 클릭됨.",Toast.LENGTH_SHORT).show()
            Log.d("kkang", "menu22 click")
            true
        }
        R.id.menu33 -> {
            Toast.makeText(this@JetActivity,"menu33 클릭됨.",Toast.LENGTH_SHORT).show()
            Log.d("kkang", "menu33 click")
            true
        }
        else -> super.onOptionsItemSelected(item)
    }
}