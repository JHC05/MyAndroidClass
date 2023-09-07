package com.example.mybookmark;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebViewClient;

import com.example.mybookmark.databinding.FragmentWebViewBinding;


public class WebViewFragment extends Fragment {

    //웹뷰에 보낼 url을 받는다
    String url;
    FragmentWebViewBinding binding;
    public WebViewFragment(String url) {
        this.url = url;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentWebViewBinding.inflate(inflater);
        binding.webView.setWebViewClient(new WebViewClient());
        binding.webView.getSettings().setJavaScriptEnabled(true);
        binding.webView.loadUrl(url);

        return binding.getRoot();
    }
}