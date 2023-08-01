package com.example.test13_16_17_18.test18reqres.adpater

import android.content.Context
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.test13_16_17_18.databinding.ItemRetrofitBinding
import com.example.test13_16_17_18.test18reqres.Model.UserModel
import com.example.test13_16_17_18.test18reqres.MyApplication
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyViewHolder(val binding: ItemRetrofitBinding): RecyclerView.ViewHolder(binding.root)

// 메인 액티비티 3번에서, MyAdapter 클래스 매개변수2개인 초기화 생성자 호출.
// 서버에서 받은 데이터를 val datas: List<UserModel>?
class MyAdapter(val context: Context, val datas: List<UserModel>?): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun getItemCount(): Int{
        return datas?.size ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
            = MyViewHolder(ItemRetrofitBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding=(holder as MyViewHolder).binding
        // datas -> List<UserModel> 한 요소가, 한 멤버의 객체입니다.
        val user = datas?.get(position)
        // 받아온 데이터 전부를 다 사용안했음.
        binding.id.text=user?.id
        binding.firstNameView.text=user?.firstName
        binding.lastNameView.text=user?.lastName
        binding.emailView.text=user?.email


        user?.avatar?.let {
            // 네트워크 통신을 이용해서, 이미지를 가져오는 작업.
            val avatarImageCall = (context.applicationContext as MyApplication).networkService.getAvatarImage(it)
            // 실제 통신이 시작.
            avatarImageCall.enqueue(object : Callback<ResponseBody> {
                // 성공하면.
                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    if (response.isSuccessful) {
                        if (response.body() != null) {
                            // 데이터를 받아서, 앱에서 사용 가능한 이미지 타입으로 변환해서 사용함.
                            val bitmap = BitmapFactory.decodeStream(response.body()!!.byteStream())
                            binding.avatarView.setImageBitmap(bitmap)
                        }
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    call.cancel()
                }
            })
        }



    }

}