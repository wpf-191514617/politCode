package com.zodiac.polit.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.text.method.DigitsKeyListener;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.minilive.library.util.StringUtils;
import com.zodiac.polit.R;

import static com.zodiac.polit.R.id.tvInputStatus;

/**
 * Created by john on 2018/9/29.
 */

public class InputLayout extends LinearLayout {

    private EditText mEditText;
    private boolean editble;
    private boolean isEdit;
    private TextView tvTitle , tvInputStatus;

    public InputLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public InputLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    private void initView(Context context, @Nullable AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.InputLayout);
        String title = array.getString(R.styleable.InputLayout_title);
        String hnit = array.getString(R.styleable.InputLayout_eidtHnit);
        editble = array.getBoolean(R.styleable.InputLayout_editble , true);
        String text = array.getString(R.styleable.InputLayout_text);
        boolean isMobile = array.getBoolean(R.styleable.InputLayout_isMobile , false);
        boolean isIdCard = array.getBoolean(R.styleable.InputLayout_isIdCard , false);
        View view = LayoutInflater.from(context).inflate(R.layout.layout_input, this);
        boolean isMust = array.getBoolean(R.styleable.InputLayout_isMust, true);
        tvTitle = view.findViewById(R.id.tvTitle);
        mEditText = view.findViewById(R.id.etContent);
        tvInputStatus = view.findViewById(R.id.tvInputStatus);

        if (isMust){
            tvInputStatus.setVisibility(View.VISIBLE);
        } else {
            tvInputStatus.setVisibility(View.INVISIBLE);
        }

        if (isMobile){
            mEditText.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
        }

        if (isIdCard){
            mEditText.setKeyListener(DigitsKeyListener.getInstance("0123456789xX"));
        }

        setText(tvTitle, title);
        if (!StringUtils.isEmpty(hnit)) {
            mEditText.setHint(hnit);
        }

        if (!editble){
            mEditText.setKeyListener(null);
        }

        mEditText.setText(text);
    }


    public void setSelectListener(OnClickListener selectListener){
        mEditText.setFocusable(false);
        mEditText.setOnClickListener(selectListener);
    }

    public void setEditble(boolean editble) {
        this.editble = editble;
        mEditText.setEnabled(editble);
    }

    private void setText(TextView textView, String text) {
        if (StringUtils.isEmpty(text)) {
            text = "";
        }
        textView.setText(text);
    }


    public EditText getEditText(){
        return mEditText;
    }

    public String getText(){
        return mEditText.getText().toString();
    }

    public void setText(String text){
        setText(mEditText , text);
        mEditText.setSelection(mEditText.getText().toString().length());
    }

    public void setOnlyRead(){
        mEditText.setBackgroundResource(R.drawable.bg_et_dd);
    }

    public void setTitle(String lable){
        setText(tvTitle , lable);
    }

}
