package com.example.firebasetest20.util

import android.Manifest
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import java.text.SimpleDateFormat
import java.util.Date

// 해당, 코틀린 파일인데, 함수를 리팩토링, 파일로 따로 분리해서,
// 자주 사용하는 기능을 분리.
// 매개변수에 타입이 액티비티로 사용이되는 부분 확인.
fun myCheckPermission(activity: AppCompatActivity) {

    // 인텐트 , 후처리하는 함수
    // 권한 여부를 확인 하는 후처리 기능. ->ActivityResultContracts.RequestPermission()
    val requestPermissionLauncher = activity.registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {
        if (it) {// 앱이 시작할 때, 미디어 이미지 저장소에 접근이 가능하면, 시작시 토스트 메세지
            // 확인 가능함.
            Toast.makeText(activity, "권한 승인", Toast.LENGTH_SHORT).show()
        } else {
            // 33버전 이후는 세분화된 미디어 권한 확인 부탁함.
            Toast.makeText(activity, "권한 거부", Toast.LENGTH_SHORT).show()
        }
    }
//권한 체크 부분에서 변경했음. -> Manifest.permission.READ_MEDIA_IMAGES
    // 이미지에 접근 권한 물어봄.
    if (ContextCompat.checkSelfPermission(
            activity,
            Manifest.permission.READ_MEDIA_IMAGES
        ) !== PackageManager.PERMISSION_GRANTED
    ) {
        requestPermissionLauncher.launch(Manifest.permission.READ_MEDIA_IMAGES)
    }
}

// 지정한 날짜 형식의 문자열 -> 데이트 타입으로 변환하는 함수.
fun dateToString(date: Date): String {
    val format = SimpleDateFormat("yyyy-MM-dd")
    return format.format(date)
}

// 이미지를 압축하는 기능이 없음.
// 이미지를 압축하는 함수를 가져와서, 여기에 선언해서, 해당 액티비티에서 사용함.
