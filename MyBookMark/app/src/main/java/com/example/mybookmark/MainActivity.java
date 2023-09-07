package com.example.mybookmark;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.mybookmark.databinding.ActivityMainBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //프래그먼트 3개 =-= 뷰페이저에 연결     뷰페이저는 어댑터와 상호작용
        binding.viewPager.setAdapter(new ViewPageAdapter(this));

        //탭 연결
        TabLayoutMediator.TabConfigurationStrategy strategy = null;
        strategy = new TabLayoutMediator.TabConfigurationStrategy() {
            @Override                           //탭 번호             포지션
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                if (position == 0){
                    tab.setText("첫번째");
                }
                else if (position == 1){
                    tab.setText("두번째");
                }
                else if (position == 2){
                    tab.setText("세번째");
                }
            }
        };
        new TabLayoutMediator(binding.tabLayout, binding.viewPager, strategy).attach();


    }
}