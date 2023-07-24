package com.example.test10_11_12.test11

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test10_11_12.adpater.MyAdapter
import com.example.test10_11_12.databinding.ActivityRecycleBinding

// 리사이클러 뷰를 출력을 해주는 빈 도화지.
class RecycleActivity : AppCompatActivity() {

    lateinit var binding: ActivityRecycleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecycleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //경로
        //AndroidLab/test11/src/main/java/com/example/test11
        ///MainActivity342.kt
        //
        //순서 구성요소
        // 1) 뷰 홀더 생성 : 어댑터 내부에 정의가 되었음. 예) MyViewHolder
        // 2) 어댑터 생성 : 기존 샘플 코드는 액티비티 내부 클래스로 구성
        //예 MyAdapter
        // 저는 따로 분리.
        // 3) 설정한 리사이클러 뷰를 메인(RecycleActivity) 에 적용한
        // 4) 임시 데이터(리스트), 원래 공공 데이터등 디비에서 받아온 데이터 사용.

        // 임시 더미 데이터 , 현재 텍스트 뷰만 9개 데이터
        val datas = mutableListOf<String>()
        for(i in 1..20){
            datas.add("Item $i")
        }

        // RecycleActivity 여기의 xml에 , 출력할 리사이클러뷰를 설정.
        // 참고 코드
        //경로
        //test11/src/main/res/layout/activity_main342.xml

        // 만들었던 리사이클러 뷰를 , 현재 액티비티에 적용
        // 순서대로, 세로방향 출력.

        //옵션에서, 출력의 모양을 정하는 부분.
        // 보통, 리니어 세로, 가로 방향으로 출력하고,
        // 표 형식, 지그재그로 표현 , 옵션이 있음.
        // 구현 옵션만 변경해서 확인.
        val layoutManager =LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL

        //기존 코드
//        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        // 변경 코드.
        binding.recyclerView.layoutManager = layoutManager

        // 리사이클러 뷰 의 어댑터를 내가 만든 어댑터 연결
        // 뷰와 데이터를 연결 해주는 역할,.
        binding.recyclerView.adapter = MyAdapter(datas)
        // 목록의 요소를 꾸며주는 역할. 이미지를 넣거나, 구분선을 넣거나.
        // 목록의요소의 크기 및 배치 , 색깔등 정할수 있음.
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(this,
                LinearLayoutManager.VERTICAL)
        )

    }
}