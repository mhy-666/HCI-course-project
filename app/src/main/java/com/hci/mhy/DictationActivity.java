package com.hci.mhy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.uit.Model.Word;
import com.example.uit.databinding.ActivityDictationBinding;

import java.util.ArrayList;

public class DictationActivity extends AppCompatActivity {

    private ActivityDictationBinding binding;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDictationBinding.inflate(getLayoutInflater());
        view = binding.getRoot();
        setContentView(view);

        ArrayList<Word> words = (ArrayList<Word>) getIntent().getExtras().getSerializable("words");

        binding.scrollView.setWordList(words);
        binding.scrollView.init();

        binding.goNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.scrollView.goNext();
            }
        });

        binding.goPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.scrollView.goPrevious();
            }
        });
        binding.backDictation.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                startActivity(new Intent(DictationActivity.this, DictationSelectActivity.class));
            }
        });
    }
}