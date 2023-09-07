package com.example.mybookmark;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.mybookmark.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //프래그먼트 3개 =-= 뷰페이저에 연결     뷰페이저는 어댑터와 상호작용
        //binding.viewPager.
    }
}