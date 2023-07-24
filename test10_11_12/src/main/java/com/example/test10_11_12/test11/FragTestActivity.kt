package com.example.test10_11_12.test11

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.test10_11_12.R
import com.example.test10_11_12.databinding.ActivityFragTestBinding
import com.example.test10_11_12.fragment.OneFragment

class FragTestActivity : AppCompatActivity() {
    lateinit var binding: ActivityFragTestBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFragTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //프래그먼트 기본 테스트,
        // 방법 1) xml 구성하는 방법. 2) 코드로 구성하는 방법
        //경로
        // 방법1)
        //    test11/src/main/res/layout/activity_main337.xml
        // 출력방식이,  액티비티에 name 으로 해당 프래그먼트를 지정해서, 출력

        //방법2)
        // 액티비티 코드에서, 해당 프래그먼트를 호출 출력하는 방법.
        // 프래그먼트 또 만들기.
// 경로
        //test11/src/main/java/com/example/test11/MainActivity338.kt
        // TwoFragment.kt 만들어서 구현중.

        val fragmentManager: FragmentManager = supportFragmentManager
        val transaction: FragmentTransaction = fragmentManager.beginTransaction()
        val fragment = OneFragment()
        transaction.add(R.id.fragment_content, fragment)
        //백스텝을 설정시 커밋 이전 함수에서 설정 하면됨.
        // 뒤로가기 버튼을 클릭식 해당 액티비티를 종료하는게 아니라.
        // 메모리상에 있는 프래그먼트를 재사용합니다.
        // 옵션 설정이 없으면, 프래그먼트 소멸 후, 다시 재생성 및 시작을 해서,
        // 자원 소모가 발생함.
        transaction.addToBackStack(null)
        transaction.commit()



    }
}