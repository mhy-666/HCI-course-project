package com.hci.mhy.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.uit.Model.Word;
import com.example.uit.Model.WordSelect;
import com.example.uit.R;

import java.util.List;

public class WordAdapter extends BaseAdapter {

    private List<WordSelect> words;
    private Context context;

    public WordAdapter(List<WordSelect> words, Context context) {
        this.context = context;
        this.words = words;
    }

    @Override
    public int getCount() {
        return words.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.word_of_words_lv, viewGroup, false);
            viewHolder.textView = view.findViewById(R.id.tv_word);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        Word word = words.get(i);
        viewHolder.textView.setText(word.getEnglish());

        return view;
    }

    private final class ViewHolder {
        TextView textView;
    }
}
