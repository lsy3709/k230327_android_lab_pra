package com.example.test10_11_12.test11

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import com.example.test10_11_12.R
import com.example.test10_11_12.databinding.ActivityDrawerTestBinding

class DrawerTestActivity : AppCompatActivity() {
    // 기존 뷰바인딩 선언.
    lateinit var binding: ActivityDrawerTestBinding
    // 추가.
    lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDrawerTestBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //경로
        //test11/src/main/java/com/example/test11/MainActivity357.kt

        //경로
        // 뷰 부분을 잘 보셔야합니다.
        // 드로워 태그 하위에 뷰 2개가 있고,
        // 첫번째 뷰가 메인 화면 뷰이고,
        // 두번째 뷰가 서랍 뷰라고 보시면(사이드에서 나오는 메뉴)
        //test11/src/main/res/layout/activity_main357.xml


        //ActionBarDrawerToggle 버튼 적용
        //경로
        // test11/src/main/res/values/strings.xml
        toggle = ActionBarDrawerToggle(this, binding.drawer,
            R.string.drawer_opened,
            R.string.drawer_closed
        )
        //액션바, 토클부분 연결시켜서, 버튼 클릭하면 , 서랍 화면이 보인다.
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toggle.syncState()

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //이벤트가 toggle 버튼에서 제공된거라면..
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
    }
