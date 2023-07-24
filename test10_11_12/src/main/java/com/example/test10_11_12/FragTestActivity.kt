package com.example.test10_11_12

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.test10_11_12.databinding.ActivityFragTestBinding

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





    }
}