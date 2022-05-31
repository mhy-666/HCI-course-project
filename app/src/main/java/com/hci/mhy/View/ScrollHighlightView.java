package com.hci.mhy.View;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.uit.Model.Word;
import com.example.uit.R;

import java.util.List;

public class ScrollHighlightView extends View {

    private List<Word> wordList;

    private static final int defaultHighLineColor = R.color.green;
    private static final int defaultScrollColor = android.R.color.darker_gray;
    private static final int defaultTextSize = 36;
    private static final int defaultHighLineTextSize = 40;

    private Paint gPaint;
    private Paint hPaint;
    private int highLineColor;
    private int scrollColor;
    private int width = 0, height = 0;
    private MediaPlayer player;
    private int currentPosition = 0;
//    private int lastPosition = 0;

    public void setWordList(List<Word> wordList) {
        this.wordList = wordList;
    }

    public void setHighLineColor(int highLineColor) {
        this.highLineColor = highLineColor;
    }

    public void setScrollColor(int scrollColor) {
        this.scrollColor = scrollColor;
    }

    public void setPlayer(MediaPlayer player) {
        this.player = player;
    }

    // 构造函数会在new的时候调用
    public ScrollHighlightView(Context context) {
        this(context, null);
    }

    // 在布局layout中使用（调用）
    public ScrollHighlightView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    // 在布局layout中使用（调用），可以获取自定义属性
    public ScrollHighlightView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ScrollHighlightView);
        highLineColor = typedArray.getColor(R.styleable.ScrollHighlightView_highLineColor, getResources().getColor(defaultHighLineColor));
        scrollColor = typedArray.getColor(R.styleable.ScrollHighlightView_scrollColor, getResources().getColor(defaultScrollColor));
        typedArray.recycle();

        gPaint = new Paint();
        gPaint.setAntiAlias(true);
        gPaint.setColor(scrollColor);
        gPaint.setTextSize(defaultTextSize);
        gPaint.setTextAlign(Paint.Align.CENTER);
        hPaint = new Paint();
        hPaint.setAntiAlias(true);
        hPaint.setColor(highLineColor);
        hPaint.setTextSize(defaultHighLineTextSize);
        hPaint.setTextAlign(Paint.Align.CENTER);
    }

    public void init() {
        currentPosition = 0;
//        lastPosition = 0;
        setScrollY(0);
        invalidate();
    }

    /**
     * 自定义view测量方法
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (width == 0 || height == 0) {
            width = getMeasuredWidth();
            height = getMeasuredHeight();
        }
        if (wordList == null || wordList.size() == 0) {
            canvas.drawText("暂无歌词", width / 2, height / 2, gPaint);
            return;
        }

        drawWord(canvas);
        float v = currentPosition * 80;
        setScrollY((int) v);
        postInvalidateDelayed(100);
    }

    public void drawWord(Canvas canvas) {
        for (int i = 0; i < wordList.size(); i++) {
            if (i == currentPosition) {
                canvas.drawText(wordList.get(i).getEnglish() + " " + wordList.get(i).getChinese(), width / 2, height / 2 + 80 * i, hPaint);
            } else {
                canvas.drawText(wordList.get(i).getEnglish() + " " + wordList.get(i).getChinese(), width / 2, height / 2 + 80 * i, gPaint);
            }
        }
    }

    public void goNext() {
        if (currentPosition < wordList.size() - 1) {
            currentPosition++;
        }else{
            currentPosition = 0;
        }
        invalidate();
    }

    public void goPrevious() {
        if (currentPosition > 0) {
            currentPosition--;
        }else{
            currentPosition = wordList.size() - 1;
        }
        invalidate();
    }

    public void goIndex(int index) {
        if (index < wordList.size()) {
            currentPosition = index;
            invalidate();
        }
    }

}
