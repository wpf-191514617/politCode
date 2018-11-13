package com.zodiac.polit.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.minilive.library.util.StringUtils;
import com.zodiac.polit.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by john on 2018/9/22.
 */

public class RowView extends RelativeLayout {

    private LinearLayout layoutRowView;
    private TextView tvValue;
    private ImageView civPhoto;

    public RowView(Context context) {
        super(context);
    }

    public RowView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }


    public RowView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.RowView);
        Drawable iconDrawable = array.getDrawable(R.styleable.RowView_icon);
        String lable = array.getString(R.styleable.RowView_lable);
        Drawable nextDrawable = array.getDrawable(R.styleable.RowView_nextIcon);

        int paddingLeft = array.getDimensionPixelSize(R.styleable.RowView_paddingLeft, 0);
        float lableSize = array.getFloat(R.styleable.RowView_lableSize, 0);
        int lableMargin = array.getDimensionPixelSize(R.styleable.RowView_lableMargin,
                0);

        String valueText = array.getString(R.styleable.RowView_valueText);
        int valueTextColor = array.getColor(R.styleable.RowView_valueTextColor , getResources().getColor(R.color.gray33));

        array.recycle();

        View view = LayoutInflater.from(context).inflate(R.layout.layout_rowview, this);
        ImageView iconView = view.findViewById(R.id.ivRowIcon);
        TextView tvLable = view.findViewById(R.id.tvRowLable);
        ImageView ivNext = view.findViewById(R.id.ivNext);
        tvValue = view.findViewById(R.id.tvValue);
        layoutRowView = view.findViewById(R.id.layoutView);
        civPhoto = view.findViewById(R.id.civPhoto);
        if (paddingLeft !=0){
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(iconView.getLayoutParams());
            params.setMargins(paddingLeft , 0 , 0 ,0);
            iconView.setLayoutParams(params);
        }

        if (lableMargin != 0) {
            tvLable.setPadding(lableMargin, 0, 0, 0);
        }
        if (lableSize != 0) {
            tvLable.setTextSize(lableSize);
        }
        if (iconDrawable == null) {
            iconView.setVisibility(View.GONE);
        } else {
            iconView.setImageDrawable(iconDrawable);
        }

        if (StringUtils.isEmpty(lable)) {
            tvLable.setVisibility(View.GONE);
        } else {
            tvLable.setText(lable);
        }
        if (nextDrawable == null) {
            ivNext.setVisibility(View.GONE);
        } else {
            ivNext.setImageDrawable(nextDrawable);
        }

        if (!StringUtils.isEmpty(valueText)){
            tvValue.setText(valueText);
        }

        if (valueTextColor != 0){
            tvValue.setTextColor(valueTextColor);
        }

    }

    public LinearLayout getLayoutRowView() {
        return layoutRowView;
    }

    public void setTextValue(String value) {
        if (StringUtils.isEmpty(value)) {
            value = "";
        }
        if (tvValue != null) {
            tvValue.setText(value);
        }
    }

    public void setTextValueColor(int colorResource){
        if (colorResource != 0 && tvValue != null){
            tvValue.setTextColor(getResources().getColor(colorResource));
        }
    }

    public String getValueText(){
        return tvValue.getText().toString();
    }

    public void setTextValue(int resourceID) {
        if (resourceID != 0) {
            setTextValue(getResources().getString(resourceID));
        }
    }


    public ImageView getCircleImageView() {
        civPhoto.setVisibility(View.VISIBLE);
        return civPhoto;
    }


    public void setImageUrl(Context context, String url) {
        civPhoto.setVisibility(View.VISIBLE);
        Glide.with(context).load(url).apply(RequestOptions.centerCropTransform().skipMemoryCache(true) // 不使用内存缓存
                .diskCacheStrategy(DiskCacheStrategy.NONE) )// 不使用磁盘缓存
                .into(civPhoto);
    }


}
