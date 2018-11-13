package com.zodiac.polit.bean;

import android.os.Parcel;
import android.os.Parcelable;

import cn.ittiger.indexlist.entity.BaseEntity;

/**
 * Created by john on 2018/11/3.
 */

public class CityEntity implements BaseEntity, Parcelable {

    private String code;
    private String name;


    @Override
    public String getIndexField() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.code);
        dest.writeString(this.name);
    }

    public CityEntity() {
    }

    protected CityEntity(Parcel in) {
        this.code = in.readString();
        this.name = in.readString();
    }

    public static final Parcelable.Creator<CityEntity> CREATOR = new Parcelable.Creator<CityEntity>() {
        @Override
        public CityEntity createFromParcel(Parcel source) {
            return new CityEntity(source);
        }

        @Override
        public CityEntity[] newArray(int size) {
            return new CityEntity[size];
        }
    };
}
