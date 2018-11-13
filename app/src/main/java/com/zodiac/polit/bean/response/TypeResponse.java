package com.zodiac.polit.bean.response;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by john on 2018/10/5.
 */

public class TypeResponse implements Parcelable {


    /**
     * isNewRecord : true
     * pageNo : 0
     * pageSize : 0
     * value : 1
     * label : 汉族
     * type : sys_nation
     */

    private boolean isNewRecord;
    private int pageNo;
    private int pageSize;
    private String value;
    private String label;
    private String type;

    public boolean isIsNewRecord() {
        return isNewRecord;
    }

    public void setIsNewRecord(boolean isNewRecord) {
        this.isNewRecord = isNewRecord;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.isNewRecord ? (byte) 1 : (byte) 0);
        dest.writeInt(this.pageNo);
        dest.writeInt(this.pageSize);
        dest.writeString(this.value);
        dest.writeString(this.label);
        dest.writeString(this.type);
    }

    public TypeResponse() {
    }

    protected TypeResponse(Parcel in) {
        this.isNewRecord = in.readByte() != 0;
        this.pageNo = in.readInt();
        this.pageSize = in.readInt();
        this.value = in.readString();
        this.label = in.readString();
        this.type = in.readString();
    }

    public static final Parcelable.Creator<TypeResponse> CREATOR = new Parcelable.Creator<TypeResponse>() {
        @Override
        public TypeResponse createFromParcel(Parcel source) {
            return new TypeResponse(source);
        }

        @Override
        public TypeResponse[] newArray(int size) {
            return new TypeResponse[size];
        }
    };
}
