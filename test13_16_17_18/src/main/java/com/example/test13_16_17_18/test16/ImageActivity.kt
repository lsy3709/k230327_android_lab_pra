package com.example.test13_16_17_18.test16

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import com.example.test13_16_17_18.R
import com.example.test13_16_17_18.databinding.ActivityImageBinding
import com.example.test13_16_17_18.databinding.ActivityMainBinding
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date

// 갤러리와, 카메라를 연동해서, 인텐트의 후처리 작업을 이용해서,
// 비트맵 또는 drawable 타입으로 이미지 처리하는 부분 봅니다.
// 주의 사항, 미디어 서버에 접근하는 허가 부분이 조금 변경이 되어서 소개 후 사용하고,
// 콘텐츠 프로바이더 부분의 authorities 부분 주의해서 작업 따라 하시면 됩니다.
// 내용은 그대로 재사용, 코드 리뷰 할 때 설명 잘 보시면 됩니다.

//경로
// ch16_provider/src/main/java/com/example/ch16_provider/MainActivity.kt
// 변경사항
// 바인딩 변경. : ActivityImageBinding
// 매니페스트 권한 설정,->
// 사이즈 변경 부분에 res에서 가져와서 사용하는 부분.
// 경로
// ch16_provider/src/main/res/values/dimens.xml
// SimpleDateFormat 임포트
// Date 임포트

// 임의의 프로필 사진 한장 준비 교체.
//  val photoURI: Uri = FileProvider.getUriForFile(  이부분의
// 변경하기. com.example.test13_16_17_18.test16.fileprovider

// 테스트 1) 갤러리 , 2)카메라 촬영한 사진 가져오기.
class ImageActivity : AppCompatActivity() {

    lateinit var binding: ActivityImageBinding
    lateinit var filePath: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityImageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //전화 앱 호출 버튼.
        binding.callButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW,Uri.parse("tel:02-120"))
            startActivity(intent)
        }

        //지도 맵 호출 버튼.
        binding.mapButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:" +
                    "37.5662952,126.9779451"))
            startActivity(intent)
        }


        //gallery request launcher..................
        val requestGalleryLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult())
        {
            try {
                Log.d("kkang", "응답은 받음. ")
                val calRatio = calculateInSampleSize(
                    it.data!!.data!!,
                    resources.getDimensionPixelSize(R.dimen.imgSize),
                    resources.getDimensionPixelSize(R.dimen.imgSize)
                )
                Log.d("kkang","원본의 사진을 얼마나 줄일 지 비율값(calRatio):$calRatio  ")
                val option = BitmapFactory.Options()
                option.inSampleSize = calRatio

                Log.d("kkang", "inputStream 하기전")
                var inputStream = contentResolver.openInputStream(it.data!!.data!!)
                Log.d("kkang", "inputStream 하기후")
                val bitmap = BitmapFactory.decodeStream(inputStream, null, option)
                inputStream!!.close()
                inputStream = null

                bitmap?.let {
                    Log.d("kkang", "결과 뷰에 적용 전")
                    // 결과 뷰에 갤러리에서 가져온 사진을 할당 부분.
                    binding.userImageView.setImageBitmap(bitmap)
                    Log.d("kkang", "결과 뷰에 적용 후")
                } ?: let{
                    Log.d("kkang", "bitmap null")
                }
            }catch (e: Exception){
                Log.d("kkang", "응답 시작 부터 오류")
                e.printStackTrace()
            }
        }


        //갤러리에서
        binding.galleryButton.setOnClickListener {
            //gallery app........................
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            intent.type = "image/*"
            requestGalleryLauncher.launch(intent)
        }

        //camera request launcher................. 후처리를 하는 함수.
        val requestCameraFileLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()){
            // calRatio 임의로 만든 변수. 이미지 처리시 크기 문제는 메모리 부족 현상이 생겨서
            // 크기를 조정 하는 함수.
            //calculateInSampleSize 함수 : 저자가 임의로 만든 원본의 크기를 줄이는 비율을
            // 구하는 식.
            // 로직은, 원본의 크기를 반으로 줄여서, 비율 조사를 해나가는 방식.
            // 결론, 크기를 줄여나가는 함수이다.
            // 정수 값으로 , 예 ) 3,4 반환함.
            val calRatio = calculateInSampleSize(
                // 원본 데이터.
                Uri.fromFile(File(filePath)),
                // 출력할 이미지의 크기를 임의의 지정,
                // 현재 리소스 폴더에 150dp 로 지정.
                resources.getDimensionPixelSize(R.dimen.imgSize),
                resources.getDimensionPixelSize(R.dimen.imgSize)
            )
            Log.d("kkang","원본의 사진을 얼마나 줄일 지 비율값(calRatio):$calRatio  ")
            // BitmapFactory 비트맵 타입으로 이미지를 그대로 처리시 문제가 됨. OOM 메모리 누수
            // 옵션을 정해서, 이미지를 처리해야함.
            val option = BitmapFactory.Options()
            // calRatio , 원본의 사진을 특정 비율에 맞게 줄인 결과 값.
            // ex) option.inSampleSize = 4 ,
            // 12MB -> 3MB 사이즈로 줄여나감(크기가 조정됨.)
            option.inSampleSize = calRatio
            val bitmap = BitmapFactory.decodeFile(filePath, option)
            bitmap?.let {
                binding.userImageView.setImageBitmap(bitmap)
            }
        }


        // 카메라 이용
        binding.cameraButton.setOnClickListener {
            //camera app......................
            //파일 준비...............파일의 이름.
            val timeStamp: String =
                SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
            // 안드로이드 시스템에서 정하는 DIRECTORY_PICTURES 정해져 있음.
            val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
            // JPEG_${timeStamp}_.jpg 파일 준비. 물리 파일 생성.
            val file = File.createTempFile(
                "JPEG_${timeStamp}_",
                ".jpg",
                storageDir
            )
            // 물리 파일의 실제 경로
            filePath = file.absolutePath
            // 카메라에서 찍은 사진에 접근 하기위해서, 콘텐츠 프로바이더에 요청.
            // 요청시, 매니페스트에서 정한 같은 문자열을 사용합니다.
            // "com.example.test13_16_17_18.fileprovider",
            val photoURI: Uri = FileProvider.getUriForFile(
                this,
                "com.example.test13_16_17_18.fileprovider",
                file
            )
            // 현재 앱 -> 외부 앱으로 가기위해서 , 시스템에게 인텐트로 전달.
            // 인텐트의 메세지 내용은, 액션의 문자열 카메라 앱,
            // 데이터의 내용은 사진의 출력(카메라로 찍은 사진) , photoURI 에 담기.
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
            // 후처리 함수를 호출하면 , 위에 정된 후처리 작업하는 함수로 이동.
            // 카메라 촬영후, 체크 한다음, 되돌올 때, 작업은 위에 함수에서 처리.
            requestCameraFileLauncher.launch(intent)

        }
    }

    // 이미지의 크기를 줄이는 로직.
    // 첫 매개변수: 사진 원본 데이터
    // 두번째, 세번째 매개변수 : 원하는 가로 세로 크기 (출력 원하는 사진의 크기)
    private fun calculateInSampleSize(fileUri: Uri, reqWidth: Int, reqHeight: Int): Int {
        //비트맵 객체 그대로 사용하면, 사진 원본을 그대로 사용해서 메모리 부족 현상 생김.
        // 그래서, 옵션이라는 속성을 사용.
        val options = BitmapFactory.Options()
        // 실제 비트맵 객체를 생성하는 것 아니고, 옵션 만 설정하겠다라는 의미.
        options.inJustDecodeBounds = true
        try {
            // 실제 원본 사진의 물리 경로에 접근해서, 바이트로 읽음.
            // 사진을 읽은 바이트 단위.
            var inputStream = contentResolver.openInputStream(fileUri)

            //inJustDecodeBounds 값을 true 로 설정한 상태에서 decodeXXX() 를 호출.
            //로딩 하고자 하는 이미지의 각종 정보가 options 에 설정 된다.
            BitmapFactory.decodeStream(inputStream, null, options)
            // 읽었던 원본의 사진의 메모리 사용은 반납.
            inputStream!!.close()
            // 객체를 null 초기화,
            inputStream = null
        } catch (e: Exception) {
            e.printStackTrace()
        }
        //비율 계산........................
        val (height: Int, width: Int) = options.run { outHeight to outWidth }
        var inSampleSize = 1
        //inSampleSize 비율 계산
        //height ,width 원본의 가로 세로 크기.
        // reqHeight, reqWidth 원하는 크기 사이즈,
        // 이것보다 크면 원본의 사이즈를 반으로 줄이는 작업을 계속 진행.
        if (height > reqHeight || width > reqWidth) {

            val halfHeight: Int = height / 2
            val halfWidth: Int = width / 2

            while (halfHeight / inSampleSize >= reqHeight && halfWidth / inSampleSize >= reqWidth) {
                inSampleSize *= 2
            }
        }
        return inSampleSize
    }

}