package com.example.test10_11_12.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.test10_11_12.R
import com.example.test10_11_12.databinding.FragmentOneBinding
import com.example.test10_11_12.databinding.FragmentTwoBinding


class TwoFragment : Fragment() {

    lateinit var binding: FragmentTwoBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_two, container, false)
        binding = FragmentTwoBinding.inflate(inflater, container, false)
        return binding.root
    }


}