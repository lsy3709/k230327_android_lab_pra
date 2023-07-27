package com.example.test13_16_17_18.test17

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.test13_16_17_18.R
import com.example.test13_16_17_18.databinding.ActivityAddBinding

// 코드와 뷰 부분 복사하기.
// 추가하기.
// R.menu.menu_add
// 경로 ch17_database/src/main/res/menu/menu_add.xml

class AddActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_add, menu)
        return super.onCreateOptionsMenu(menu)
    }

    // 액션바의 메뉴 아이팀의 클릭 리스너 부분에 처리.
    override fun onOptionsItemSelected(item: MenuItem): Boolean = when(item.itemId){
        R.id.menu_add_save ->{
            //add........................
            // 입력창에서 입력된 값 가져오기.
            val inputData = binding.addEditView.text.toString()
            // DBHelper : 일반 클래스, 매개변수로 현재 액티비티 this로 사용하고,
            // writableDatabase 문자열을 이용해서 insert 작업을 할 예정.
            val db = DBHelper(this).writableDatabase
            // 기본 함수 execSQL 함수를 이용해서, insert를 진행함.
            // 첫 매개변수는 sql 문법 insert 사용
            // 두번째 매개변수는 ? 동적 매개변수 부분의 값으로 할당 될 값을 배열의 요소로 제공함.
            db.execSQL(
                "insert into TODO_TB (todo) values (?)",
                arrayOf<String>(inputData)
            )
//            반드시 디비 다 사용하고 자원반납
            db.close()
            // 후처리 부분
            // 메인 플로팅 액션 버튼 클릭 -> 현재 add 입력창에와서 텍스트 입력후
            // 입력된 데이터를 인텐트로 다시 돌려 보냅니다. 데이터를 세터해서.
            val intent = intent
            intent.putExtra("result", inputData)
            // 응답 메세지를 보냅니다.(성공 , 마치 200 비슷)
            setResult(Activity.RESULT_OK, intent)
            // 현재 액티비티 종료함.
            finish()
            true
        }
        else -> true
    }
}