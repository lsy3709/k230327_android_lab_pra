package com.example.test13_16_17_18.test18reqres.retrofit

import com.example.test13_16_17_18.test18reqres.Model.UserListModel
import com.example.test13_16_17_18.test18reqres.Model.UserModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

//레트로핏 구조 특성상, 전달을 인터페이스로 전달을 함.
// 인터페이스 -> 추상 함수들의 모음집.
interface INetworkService {
    // 실제 백엔드 서버와 주소를 하기위한  URL 주소
    // baseUrl("https://reqres.in/") : ex) localhost:8080
    // https://reqres.in/api/users : ex) https://localhost:8080/api/users백엔드 (스프링) ,
    // 레스트 컨트롤러 정의가 되어 있음.
    @GET("api/users")
    // baseurl : https://reqres.in/
    //https://reqres.in/api/users?page=2
    //예를 들어서 doGetUserList("2")

    // 레트로핏2 의 함수의 리턴의 타입은 Call 타입. -> 제너릭으로 설정이된 모델의 구조를 파악잘하기.
    fun doGetUserList(@Query("page") page: String): Call<UserListModel>
    @GET
    fun getAvatarImage(@Url url: String): Call<ResponseBody>

    //    @GET("users/list?sort=desc")
    @GET("api/users/2")
    fun test1(): Call<UserModel>
}