package com.example.test13_16_17_18.test17

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test13_16_17_18.R
import com.example.test13_16_17_18.databinding.ActivityMain3Binding
import com.example.test13_16_17_18.databinding.ActivityMainBinding

//1 기본 SQLite 살펴보기. -> pdf 에서 제공한 소스코드 이용함
//2 연습문제 17 살펴보기. -> 조금더 활용한 부분, 쓰기, 읽기 만
// 경로
// ch17_database/src/main/java/com/example/ch17_database
// 참고 파일.
// 1)AddActivity.kt 액티비티 클래스
// 변경사항 -> R.menu.menu_add 추가 되었음. 해당 파일 경로 표기했음.

//2)DBHelper.kt 일반 클래스
//
//3)MainActivity.kt 액티비티 클래스
//코드 ,뷰, 복사하기
//변경사항
// -1. R.menu.menu_main 추가 -> ch17_database/src/main/res/menu/menu_main.xml
// -2. 세팅 관련된 부분을 제거할 예정.
// SettingActivity 부분 주석 했음.

//4)MyAdapter.kt 일반 클래스

// 액션바를 붙여주세요. 테마에서 설정 변경함.

//3 crud 블로그에 샘플소스 살펴보기.
//4 제트팩 라이브러리에서, 구글에서 공식적으로 SQLite 보다, room 사용을 권장.
//5 샘플코드 소개 정도만 하겠음.

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMain3Binding
    //AddActivity에서 입력된 한줄의 텍스트들을 요소로 리스트에 보관.
    var datas: MutableList<String>? = null
    // 입력된 문자열 내용을 , 리사이클러뷰로 출력.
    lateinit var adapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMain3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // 입력 창으로 텍스트 입력 후, 저장 버튼을 누르면 여기로 돌아옴.
        val requestLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult())
        {
            // 돌아온 결과값은 it 라는 객체 Map 형태로 저장.
            // 키 : result 라는 부분의 값을 가지고 와서.
            // datas 라는 리스트에 add 담기.
            // 어댑터 객체의 함수중에 변경 사항을 알리는 함수를 수행에서.
            // 리사이클러뷰에 적용함.  adapter.notifyDataSetChanged()
            it.data!!.getStringExtra("result")?.let {
                datas?.add(it)
                adapter.notifyDataSetChanged()
            }
        }
        // 플로팅 액션바 버튼, 클릭 이벤트처리 -> 입력 액티비티로 이동.
        // 후처리를 하는 함수. requestLauncher
        // 입력 창에서 투두로 입력후 , 입력된 값을 가지고 되돌아옵니다.
        //  val requestLauncher = registerForActivityResult( 이부분으로 돌아옴.

        // AddActivity 에서는 데이터를 처리하는 세터 부분이 있음. 확인.
        binding.mainFab.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            requestLauncher.launch(intent)
        }

        // 변경 가능한 리스트 형식으로 객체 선언
        datas= mutableListOf<String>()

        //조회
        //  readableDatabase-> 읽기.
        val db = DBHelper(this).readableDatabase
        // 커서 cursor 쉽게, 조회된 결과를 테이블형식으로 저장된 객체.
        val cursor = db.rawQuery("select * from TODO_TB", null)
        // 테이블 형식으로 저장되어 있음.
        //
        cursor.run {
            // 반복문으로 커서 테이블에 데이터를 한행씩 불러와서, 해당 컬럼을 가져오기.
            // 커서는 1행부터 시작함, 원래 리스트 인덱스 0부터.
            while(moveToNext()){
                datas?.add(cursor.getString(1))
            }
        }
        // 디비 서버에서 조회된 내용을 -> 현재 메모리 datas라는 리스트에 다 담기.
        // 디비 사용을 반납.
        db.close()

        // 리사이클러뷰 적용하는 부분.
        val layoutManager = LinearLayoutManager(this)
        binding.mainRecyclerView.layoutManager=layoutManager
        // 디비 서버에서 받온 데이터를 -> 메모리 상의 임시 객체 datas 담아서
        // 어댑터 클래스에 연결 하는 부분.
        adapter=MyAdapter(datas)
        // 어댑터 클래스에 적용된 , 데이터 <-> 뷰 , 결과를 뷰에 적용하는 부분.
        binding.mainRecyclerView.adapter=adapter
        // 리사이클러뷰의 옵션 선 정도 생성.
        binding.mainRecyclerView.addItemDecoration(
            DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
        )
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId===R.id.menu_main_setting){
//            val intent = Intent(this, SettingActivity::class.java)
//            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }

}