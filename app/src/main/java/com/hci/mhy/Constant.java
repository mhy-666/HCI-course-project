package com.hci.mhy;

import com.example.uit.Model.WordSelect;

import java.util.Arrays;
import java.util.List;

public class Constant {
    public static final String BASE_URL = "http://127.0.0.1:5000/";

    public static final List<WordSelect> words = Arrays.asList(
            new WordSelect("hello", "你好"),
            new WordSelect("world", "世界"),
            new WordSelect("whether", "是否"),
            new WordSelect("pencil", "铅笔"),
            new WordSelect("apple", "苹果"),
            new WordSelect("book", "书"),
            new WordSelect("pen", "钢笔"),
            new WordSelect("schoolbag", "书包"),
            new WordSelect("computer", "电脑"),
            new WordSelect("mouse", "鼠标"),
            new WordSelect("keyboard", "键盘"),
            new WordSelect("chair", "椅子"),
            new WordSelect("table", "桌子")
    );
}
