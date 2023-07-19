package com.example.test6_lsy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.test6_lsy.databinding.ActivityMainBinding

//액티비티 : 화면을 그려주는 역할.
class MainActivity : AppCompatActivity() {
    //최초에 한번 화면에 출력을 하는 역할.

    var status : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        // 스위치 효과를 주기 위해서, 상태 패턴의 변수를 이용해서
        // 이미지 보여주고, 안 보여주기.


        //뷰 바인딩 적용1
        // 시스템에서 자동으로 생성해준다. ActivityMainBinding
        // 여기 코드 , 설정 코드이기때문에, 디비 연결 하는 Connection 객체 용도가 비슷.
        // binding 작업 하는 뷰가 모두 들어가 있다.
        val binding = ActivityMainBinding.inflate(layoutInflater)

        // savedInstanceState: Bundle? -> 임시 데이터 저장 객체,
        super.onCreate(savedInstanceState)
        // 레이아웃 샘플로 리니어 사용하는 중, 이유? 제약 조건 설정이 간단해서.
        // 뷰만 선택 해서 배치만하면, 기본 세로 , 가로방향으로 배치가 간단함.

        //뷰 바인딩 적용2
        setContentView(binding.root)

        //버튼 클릭 -> 이미지 ->show / hide
        // 버튼 에 이벤트 클릭 리스너 설정 후 -> 결과로 이미지의 속성 중에
        // visibility 속성으로 show/ hide 테스트 .
        // binding 객체 안에, 사용하는 뷰의 모든 객체가 다 담아져 있습니다.
        // 추가로
        // 버튼 하나로 토글처럼(스위치처럼) => 상태 패턴, 상태를 나타내는 변수를 하나 정해야함.
        binding.btn1.setOnClickListener{

            if(status == 0) {
                binding.img1.visibility = View.GONE
                status = 1
            } else if(status == 1) {
                binding.img1.visibility = View.VISIBLE
                status = 0
            }
            println("status 값 확인 : $status")

        }
        //버튼 2개로 하는 경우.
        binding.btn2.setOnClickListener {
            binding.img1.visibility = View.VISIBLE
        }
    }
}