package com.example.test10_11_12.adpater

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.test10_11_12.databinding.Item2342Binding


//Recycle2Activity -> 연결할 어댑터
// 사실은 같은 내용이면, 기존의 만들었던 어댑터를 재사용 가능.
// 연습 삼아서, 한번 더 같은 코드를 진행 할 예정.
// 주석은 MyAdapter 를 참고 하시고, 지금 전체 구성방식.

//뷰홀더 클래스도 같이 정의할 예정.
class MyViewHolder2(val binding: Item2342Binding): RecyclerView.ViewHolder(binding.root)

class MyAdapter2(val datas: MutableList<String>): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun getItemCount(): Int{
        Log.d("kkang", "init datas size: ${datas.size}")
        return datas.size
    }
    //
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
            = MyViewHolder2(Item2342Binding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.d("kkang","onBindViewHolder : $position")
        val binding=(holder as MyViewHolder2).binding

        binding.itemData.text= datas[position]
        binding.itemRoot.setOnClickListener{

            Log.d("kkang", "item root click : $position")
        }
    }
}