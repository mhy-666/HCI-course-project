package com.hci.mhy.Model;

import java.io.Serializable;

public class Word implements Serializable {
    private String english;
    private String chinese;

    public Word() {
    }

    public Word(String english, String chinese) {
        this.english = english;
        this.chinese = chinese;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getChinese() {
        return chinese;
    }

    public void setChinese(String chinese) {
        this.chinese = chinese;
    }
}
