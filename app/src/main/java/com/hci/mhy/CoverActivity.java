package com.hci.mhy;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;

import com.example.uit.databinding.ActivityCoverBinding;

public class CoverActivity extends AppCompatActivity {

    private final String TAG = "CoverActivity";

    ActivityCoverBinding binding;
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //取消标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //添加binding
        binding = ActivityCoverBinding.inflate(getLayoutInflater());
        view = binding.getRoot();
        setContentView(view);

        initView();
    }

    private void initView() {
        binding.coverTextViewTitle.setTypeface(Typeface.createFromAsset(view.getContext().getAssets(), "优设标题黑.ttf"));
        binding.coverTextViewTitleEn.setTypeface(Typeface.createFromAsset(view.getContext().getAssets(), "优设标题黑.ttf"));
        binding.textView3.setTypeface(Typeface.createFromAsset(view.getContext().getAssets(), "优设标题黑.ttf"));

        binding.coverButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CoverActivity.this, DictationSelectActivity.class));
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy ");
        binding = null;
        view = null;
    }
}