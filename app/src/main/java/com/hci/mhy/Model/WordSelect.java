package com.hci.mhy.Model;

public class WordSelect extends Word {
    private boolean isSelected;

    public WordSelect() {
        this(null, null);
    }

    public WordSelect(String english, String chinese) {
        this(english, chinese, false);
    }

    public WordSelect(String english, String chinese, boolean isSelected) {
        super(english, chinese);
        this.isSelected = isSelected;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
