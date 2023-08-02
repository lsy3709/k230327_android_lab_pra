package com.example.firebasetest20

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firebasetest20.databinding.ActivityMainBinding
import com.example.firebasetest20.model.ItemData
import com.example.firebasetest20.recycler.MyAdapter
import com.example.firebasetest20.util.myCheckPermission

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 미디어 서버(이미지) 접근 권한 여부를 확인하는 함수이고.
        // 매 액티비티에서 사용 가능하게 , 파일 분리(리팩토링)
        myCheckPermission(this)
        // 플로팅 액션 버튼 클릭 이벤트 리스너
        binding.addFab.setOnClickListener {
            // 인증 여부 확인.
            if(MyApplication.checkAuth()){
                // 인증이 되면 -> AddActivity 이동.
                startActivity(Intent(this, AddActivity::class.java))
            }else {
                // 인증 안되면 -> 인증 해주세요. 문자열 토스트 출력.
                Toast.makeText(this, "인증진행해주세요..",Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun onStart() {
        super.onStart()
        // 인증안되면, 로그아웃 버튼 보이게.
        if(!MyApplication.checkAuth()){
            binding.logoutTextView.visibility= View.VISIBLE
            binding.mainRecyclerView.visibility= View.GONE
        }else {
            // 반대.
            binding.logoutTextView.visibility= View.GONE
            binding.mainRecyclerView.visibility= View.VISIBLE
            makeRecyclerView()
        }
    }

    // 메인 화면에, 액션바에 메뉴 옵션 설정하는 코드.
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    // 이벤트 리스터 액션바의 메뉴.
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        startActivity(Intent(this, AuthActivity::class.java))
        return super.onOptionsItemSelected(item)
    }

    // 리사이클러 뷰를 출력하는 함수를 만들어서 사용중.
    private fun makeRecyclerView(){
        // 파이어 스토어의 컬렉션 객체를 선택하는 함수.
        MyApplication.db.collection("news")
            .get()
            // 잘 가져왔다, 성공후 처리하는 로직.
            // news 라는 컬렉션(테이블)에서 모든 문서(행과 비슷)를 가져옴.
            .addOnSuccessListener {result ->
                // 빈 리스트 만들고, 임시로 저장할 공간이 필요하니까
                // DTO(=VO) , ItemData
                val itemList = mutableListOf<ItemData>()
                // 반복문으로 받아온 문서를 하나씩 꺼내어서, 작업.
                for(document in result){
                    // document.toObject , gson, jackson , 해당 모델 클래스에 자동으로 매핑.
                    // 받아온 데이터를 지정한 클래스 형으로 자동 변환(매핑)
                    val item = document.toObject(ItemData::class.java)
                    // 문서의 고유 아이디를 docId에 할당.
                    item.docId=document.id
                    // 각 ItemData 형으로 , 리스트에 담기
                    itemList.add(item)
                }
                // 리사이클러 뷰 출력을 리니어로 기본 세로 출력.
                binding.mainRecyclerView.layoutManager = LinearLayoutManager(this)
                // 리사이클러 뷰의 어댑터를 연결 하는 부분.
                //itemList -> 파이어 베이스에서 받아온 일반 데이터(문자열)
                binding.mainRecyclerView.adapter = MyAdapter(this, itemList)
            }
            .addOnFailureListener{exception ->
                // 파이어베이스 콘솔에 해당 서비스의 권한(규칙을 설정 안했을 때 나오는 문구.)
                Log.d("kkang", "error.. getting document..", exception)
                Toast.makeText(this, "서버 데이터 획득 실패", Toast.LENGTH_SHORT).show()
            }
    }
}