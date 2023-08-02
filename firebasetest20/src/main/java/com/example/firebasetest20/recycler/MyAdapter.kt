package com.example.firebasetest20.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.firebasetest20.MyApplication
import com.example.firebasetest20.databinding.ItemMainBinding
import com.example.firebasetest20.model.ItemData

// 리사이클러 뷰 -> 목록 형식으로 출력되는 뷰
// 뷰홀더 -> 뷰 객체들의 모음집.
// -> 해당 뷰 홀더의 주생성자의 매개변수에 ,바인딩 기법으로 객체가 선언됨.
// 그래서, 해당 매개변수로 , 전체 뷰 객체에 접근이 가능함.
// ( 뷰 바인딩 객체는, 목록의 아이템의 요소사용중.)

class MyViewHolder(val binding: ItemMainBinding) : RecyclerView.ViewHolder(binding.root)

// 어댑터 : 데이터 <-> 뷰 를 연결한다.
// 예) 데이터를 받아왔다면, 해당 바인딩으로 받아온 데이터를 연결한다.
// 주생성자의 매개변수에 val 형식으로 지정하면, 클래스 내부에서 전역처럼 사용 가능.
// Context -> 액티비티, 또는 프래그먼트 형식,
// itemList , 실제 데이터임. (공공데이터, 임의의 개발자가 정의한 데이터)
// 리사이클러뷰 구성 클래스들의 공통으로 모두, RecyclerView 관련 부모 클래스를 상속을 받습니다.
class MyAdapter(val context: Context, val itemList: MutableList<ItemData>): RecyclerView.Adapter<MyViewHolder>() {

    // 어댑터 클래스를 만들고, 재정의한 함수들임.
    // 인플레이터가 나왔다. 해당 뷰를 출력하기 위한 객체를 초기화하는 작업.
    // 초기화는 ( 참조형 변수에, 해당 메모리 위치 주솟값을 할당하는 것 말함. )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MyViewHolder(ItemMainBinding.inflate(layoutInflater))
    }

    // 해당 데이터 갯수를 이용해서 출력할 갯수를 알려주기.
    override fun getItemCount(): Int {
        return itemList.size
    }

    // 뷰 <-> 데이터 연결하는 부분.
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // 받은 데이터의 리스트의 인덱스로 , 해당 아이템 모델 객체에 접근.
        val data = itemList.get(position)

        // 뷰 바인딩으로 연결된 객체에 데이터에서 받아온 값을 할당.
        holder.binding.run {
            itemEmailView.text=data.email
            itemDateView.text=data.date
            itemContentView.text=data.content
        }

        //스토리지 이미지 다운로드........................
        //MyApplication . 매니페스트에 등록이 되어서, 앱이 시작시 이미 메모리에 로더 되어 있음.
        // 그래서, 이렇게 바로 사용 가능.
        // storage.reference.child : 스토리지의 객체이고,
        // 매개변수에 정의된 부분, images 상위폴더 우리가 만든 임의의 폴더.
        // /${data.docId}.jpg , 이미지의 파일명
        val imgRef = MyApplication.storage.reference.child("images/${data.docId}.jpg")
        //imgRef 이 객체를 이용해서, 업로드 및 다운로드 기능을 구현합니다.
        //여기서는 다운로드 예를 들고 있음.
        // 보통, 다운로드가 실행이 잘되면, 콜백으로 돌와와서 , 로직을 수행을 함.
        // downloadUrl 스토리지에서 이미지의 URL 주소만 가지고오는 기능.
        imgRef.downloadUrl.addOnCompleteListener{ task ->
            if(task.isSuccessful){
                 // 후처리, 스토리지의 이미지의 URL 주소를 잘 가지고 왔다면,
                // 글라이드를 이용해서, 이미지를 로드 불러오고,
                // into 를 통해서 , 해당 결과 이미지 뷰에 출력하는 코드.
                // 원래는, 안드로이드에서는 이미지의 객체 타입을 bitMap 구조로 변경하는 코드가 필요함.
                // 그런데, 여기서는 implementation 'com.firebaseui:firebase-ui-storage:8.0.0'
                // 이용해서, 한번에, 다운로드 받고, 이미지 바이트 처리해서, 출력을 한번에 해주는 기능.,
                // 라이브러리.
                Glide.with(context)
                    // 이미지를 불러오는 역할.
                    .load(task.result)
                    // 불러온 이미지를 ,결과 뷰에 출력하는 코드.
                    .into(holder.binding.itemImageView)
            }
        }
    }
}