package com.example.test10_11_12.test12

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.test10_11_12.R
import com.example.test10_11_12.databinding.ActivityMain385Binding
import com.example.test10_11_12.fragment.OneFragment
import com.example.test10_11_12.fragment.ThreeFragment
import com.example.test10_11_12.fragment.TwoFragment
import com.google.android.material.tabs.TabLayout

//경로
//test12/src/main/java/com/example/test12/MainActivity385.kt
//경로
//test12/src/main/res/layout/activity_main385.xml

class MainActivity385 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMain385Binding.inflate(layoutInflater)
        setContentView(binding.root)

        //탭 레이아웃 기본 설정.
        val tabLayout = binding.tabs

        // 탭 레이아웃 과 프래그먼트 연동하는 설정.
        // 시작시, 이 영역에 : 프레임 레이아웃 :R.id.tabContent -> OneFragment 프래그먼트 출력하겠다.
        supportFragmentManager.beginTransaction().add(R.id.tabContent, OneFragment()).commit()

        // 탭 클릭시 이벤트 핸들러 설정 부분.
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            //탭 버튼 선택시 이벤트
            // 탭을 매개변수로 정의했음.
            // 탭 클릭시 , 해당 문자열을 기준으로 조건문 동작.
            override fun onTabSelected(tab: TabLayout.Tab?) {
                // 프래그먼트를 코드상으로 작업 하겠다라는 의미.
                val transaction = supportFragmentManager.beginTransaction()
                when(tab?.text){
                    // replace : 첫 매개변수 영역을 -> 두번째 영역으로 교체
                    "Tab1"-> transaction.replace(R.id.tabContent, OneFragment())
                    "Tab2"-> transaction.replace(R.id.tabContent, TwoFragment())
                    "Tab3"-> transaction.replace(R.id.tabContent, ThreeFragment())
                }
                // 이 부분이 수행이 되어야 동작을 합니다.
                transaction.commit()
            }
            //선택된 탭 버튼을 다시 선택하는 경우
            override fun onTabReselected(tab: TabLayout.Tab?) {
                Toast.makeText(this@MainActivity385,"onTabReselected",Toast.LENGTH_SHORT).show()
                Log.d("kkang", "onTabReselected........")
            }
            //선택된 탭버튼이 다른 탭 버튼을 눌러 선택 해제 되는 경우
            override fun onTabUnselected(tab: TabLayout.Tab?) {
                Toast.makeText(this@MainActivity385,"onTabUnselected........",Toast.LENGTH_SHORT).show()
                Log.d("kkang", "onTabUnselected........")
            }
        })
    }


}