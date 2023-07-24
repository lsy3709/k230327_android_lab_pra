package com.example.test10_11_12.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.test10_11_12.R
import com.example.test10_11_12.databinding.FragmentOneBinding


class OneFragment : Fragment() {
    // 현재 FragTestActivity : 액티비티에 출력이 될 예정.
    // 프래그먼트 -> 마치 액티비티와 비슷하게 동작을 합니다.
    // 예) 생명주기, 뷰 바인딩 등.
    lateinit var binding : FragmentOneBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_one, container, false)
        binding = FragmentOneBinding.inflate(inflater, container, false)
        return binding.root
    }

}