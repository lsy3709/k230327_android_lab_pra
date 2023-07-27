package com.example.test13_16_17_18.test17sharedpreference

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.test13_16_17_18.R
import com.example.test13_16_17_18.databinding.ActivityMain7Binding
import com.example.test13_16_17_18.databinding.ActivityMainBinding
import com.example.test13_16_17_18.test17sharedpreference.DetailActivity

//코드 재사용 test13/MainActivity, DetailActivity 재사용.
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMain7Binding.inflate(layoutInflater)
        setContentView(binding.root)

        //버튼 클릭해서, 정방향, 메인 -> 디테일 화면으로 이동 + 데이터 전달.
        binding.button1.setOnClickListener {
            //sharedPreference 작업, 전역으로
            //여기서 데이터 세터 하고, 디테일 화면에서 게터
            //앱 전체의 데이터 저장
            // my_prefs 파일에 Map 형태로 값이 저장됩니다. xml 형태.
            val sharedPref = getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
            // 공유 파일 처럼 해당 데이터 저장.
            // 공유 파일 my_prefs 저장위치
            // data/data/패키지명/shared_prefs/ my_prefs.xml 저장됨.
            // 만약 안보이면, 우클릭, 한번더 동기화 클릭해보세요.
            sharedPref.edit().run {
                putString("data3", "이상용 프리퍼런스테스트중")
                putInt("data2", 10)
                commit()
            }

//            //::class.java 클래스 레퍼런스 타입으로 전달은 보통, 내부 앱에서 전달하는 방식.
            val intent: Intent = Intent(this, DetailActivity::class.java)
//            // Map 처럼 키와 value 의 형태로 데이터를 설정 및 가져오기 작업을 할 예정.
//            intent.putExtra("data1", "hello")
//            intent.putExtra("data2", 10)
//            // 시스템에게 설정한 인텐트를 전달함.
            startActivity(intent)
        }

    }
}