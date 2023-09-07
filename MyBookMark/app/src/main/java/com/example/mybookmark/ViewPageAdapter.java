package com.example.mybookmark;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPageAdapter extends FragmentStateAdapter {

    public ViewPageAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) { //프레그먼트 순서(0 1 2)
        if (position == 0){
            //Fragment_1 f1 = new Fragment_1();
            WebViewFragment f1 = new WebViewFragment("https://www.naver.com"); //url수정
            return f1;
        }
        else if (position == 1){
            //Fragment_2 f2 = new Fragment_2();
            WebViewFragment f2 = new WebViewFragment("https://m.google.com"); //url수정
            return f2;
        }
        else if (position == 2){
            Fragment_3 f3 = new Fragment_3();
            return f3;
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return 3; //!= 프레그먼트 개수 =!
    }
}
