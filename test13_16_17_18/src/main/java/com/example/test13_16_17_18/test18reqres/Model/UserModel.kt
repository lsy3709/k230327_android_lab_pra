package com.example.test13_16_17_18.test18reqres.Model

import android.graphics.Bitmap
import com.google.gson.annotations.SerializedName

data class UserModel (
    //data class 주생성자에 선언한 형식. 중괄호가 아니라, 소괄호 표기.
    var id: String,
    @SerializedName("first_name")
    var firstName: String,
    @SerializedName("last_name")
    var lastName: String,
    var avatar: String,
    // email 정보를 추가로 받아오기 같이 해보기.
    var email: String,
    var avatarBitmap: Bitmap
)