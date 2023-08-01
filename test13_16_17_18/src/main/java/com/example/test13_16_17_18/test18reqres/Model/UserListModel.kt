package com.example.test13_16_17_18.test18reqres.Model

import com.google.gson.annotations.SerializedName

data class UserListModel (
    // 실제로  https://reqres.in/
    // 사용하라고 하는 파라미터의 모델 타입이 명세서
    // 있음. 이름을 그대로 사용하면 됨.
    // 주의사항, 공공데이터의 모델링의 구조를 잘 파악해야함.

    var page: String,
    @SerializedName("per_page")
    var perPage: String,
    var total: String,
    @SerializedName("total_pages")
    var totalPages: String,

    // 실제 데이터 모델를 리스트 타입으로 제너릭으로해서 명시한다.
    var data: List<UserModel>?
)