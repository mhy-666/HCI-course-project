package com.hci.mhy;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.uit.Adapter.WordAdapter;
import com.example.uit.Model.Word;
import com.example.uit.Model.WordSelect;
import com.example.uit.databinding.ActivityDictationSelectBinding;

import java.util.ArrayList;
import java.util.List;

public class DictationSelectActivity extends AppCompatActivity {

    private ActivityDictationSelectBinding binding;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDictationSelectBinding.inflate(getLayoutInflater());
        view = binding.getRoot();
        setContentView(view);

        List<WordSelect> words = com.example.uit.Constant.words;
        ListView listView = binding.lvDictationSelect;
        WordAdapter wordAdapter = new WordAdapter(words, this);
        listView.setAdapter(wordAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(!words.get(i).isSelected()){
                    view.setBackgroundColor(Color.RED);
                }else{
                    view.setBackgroundColor(Color.WHITE);
                }
                words.get(i).setSelected(!words.get(i).isSelected());
            }
        });

        binding.btnDictationSelect.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                ArrayList<Word> wordList = new ArrayList<>();
                for(WordSelect wordSelect : words){
                    wordList.add(new Word(wordSelect.getEnglish(), wordSelect.getChinese()));
                }
                Bundle bundle = new Bundle();
                bundle.putSerializable("words", wordList);
                Intent mIntent = new Intent(DictationSelectActivity.this, com.example.uit.DictationActivity.class);
                mIntent.putExtras(bundle);
                startActivity(mIntent);
            }
        });

    }
}