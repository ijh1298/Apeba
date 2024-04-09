package com.example.apeba

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Slide
import android.util.Log
import android.view.View
import com.example.apeba.databinding.ActivityMainBinding
import com.ncorti.slidetoact.SlideToActView

class MainActivity : AppCompatActivity() {
    var isLockMode : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        updateLockModeText(binding)

        // 잠금 켜기 슬라이드 버튼
        val slide : SlideToActView = findViewById(R.id.main_LockBtn)
        slide.onSlideCompleteListener = object : SlideToActView.OnSlideCompleteListener {
            override fun onSlideComplete(view: SlideToActView) {
                Log.d("ijh", "Screen Lock Mode On")
                isLockMode = true
                binding.mainUnlockText.visibility = View.VISIBLE
                updateLockModeText(binding)
            }
        }
        // 잠금 해제 텍스트 클릭 이벤트
        binding.mainUnlockText.setOnClickListener {
            Log.d("ijh", "Screen Lock Mode Off")
            isLockMode = false
            binding.mainUnlockText.visibility = View.INVISIBLE
            slide.setCompleted(false, true)
            updateLockModeText(binding)
        }
    }

    // Test용 화면 텍스트
    private fun updateLockModeText(binding: ActivityMainBinding) {
        val testText = if (isLockMode) "켜짐" else "꺼짐"
        binding.mainTestText.text = testText
    }
}